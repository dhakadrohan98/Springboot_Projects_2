package com.codeship.autosysjob.jilfilegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JilFileGenerator {

    //creates a JIL file dynamically with the job definition
    public static void generateJilFile(String jobName, String command) throws IOException {
        String jilFilePath = jobName + ".jil";

        String jilFileContent = """
                insert_job: %s
                job_type: CMD
                command: "%s"
                machine: localhost
                owner: user@localhost
                permission: gx,wx
                std_out_file: "tmp/%s.out"
                std_out_err: "tmp/%s.err"
                date_conditions: 1
                days_of_week: all
                start_times: now
                run_calendar: no_calendar
                description: "Run AutoSys job immediately"
                """.formatted(jobName, command, jobName, jobName);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(jilFilePath))) {
            writer.write(jilFileContent);
        }
        System.out.println("JIL file created: " + jilFilePath);
    }
}
