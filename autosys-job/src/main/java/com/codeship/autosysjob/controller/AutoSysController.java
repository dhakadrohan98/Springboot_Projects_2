package com.codeship.autosysjob.controller;

import com.codeship.autosysjob.jilfilegenerator.JilFileGenerator;
import com.codeship.autosysjob.jilfilegenerator.JilJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/autosys")
public class AutoSysController {

    private static final Logger log = LoggerFactory.getLogger(AutoSysController.class);

    @PostMapping("/submit")
    public String submitJilJob(@RequestParam("jobName") String jobName, @RequestParam("command")
                               String command) {
        try {
            log.info("jobName: " + jobName);
            log.info("command: " + command);
            //generate a jil file
            JilFileGenerator.generateJilFile(jobName, command);
            //execute a jil file
            JilJobExecutor.executeJilJob(jobName + ".jil", jobName, command);
            return "Job submitted successfully: " + jobName;
        } catch (IOException e) {
            return "Error submitting job: " + e.getMessage();
        } catch (InterruptedException e) {
            return "Error submitting job: " + e.getMessage();
        }
    }
}
