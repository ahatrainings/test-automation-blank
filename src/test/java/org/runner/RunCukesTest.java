package org.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        format = {"pretty", "html:target/mrm-html-report-cucumber", "json:target/mrm-json-report-cucumber.json"},
        glue = {"org.steps"},
        features = {"src/test/java/org/features/."},
        tags = {"@ExpediaSearch"},
        monochrome = true,  
        strict = true)



public class RunCukesTest {
}

