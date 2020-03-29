package test.Runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src\\test\\java\\test\\Features" ,
glue= {"test.Scripts"} ,
dryRun = false ,
monochrome = true ,
format = {"pretty","html:target/Test_Reports"}
)

public class Test_HC_Booking {
	
}
