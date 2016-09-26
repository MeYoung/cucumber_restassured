package com.run;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by vidorh on 8/18/2016.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        features={"src/test/resources/demo"},
        glue = {"com.tools.steps"},
        tags = {"~@unimplemented"})
public class RunnerBoseTest {
}
