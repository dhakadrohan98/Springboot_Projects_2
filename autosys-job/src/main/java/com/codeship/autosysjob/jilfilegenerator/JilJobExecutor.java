package com.codeship.autosysjob.jilfilegenerator;

import com.fasterxml.jackson.core.util.BufferRecycler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JilJobExecutor {
    public static void executeJilJob(String jilFilePath, String jobName, String command) throws IOException, InterruptedException {
        // Register the JIL job
        ProcessBuilder registerJob = new ProcessBuilder("cmd.exe", "/c", "jil < " + jilFilePath);
        registerJob.redirectErrorStream(true);
        Process registerProcess = registerJob.start();
        registerProcess.waitFor();

        // Trigger the job to run immediately
        ProcessBuilder runJob = new ProcessBuilder("cmd.exe", "/c", "sendevent -E FORCE_STARTJOB -J " + jobName);
        runJob.redirectErrorStream(true);
        Process runProcess = runJob.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));

        String line;
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("AutoSys job started:" + jobName);
    }
}
