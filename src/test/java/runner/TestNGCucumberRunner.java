package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


    @CucumberOptions(
            features = "C:\\Users\\nehar\\IdeaProjects\\E2EHybridBDDFramework\\src\\test\\java\\resounces\\HomePage.feature",
            glue = {"stepsDefination", "hooks"},
            plugin = {"pretty", "json:target/cucumber.json"},
            monochrome = true
    )
    public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
        // If parallel parameter is set to true via -Dparallel=true we will enable parallel scenarios
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            String parallel = System.getProperty("parallel", "true");
            if (parallel.equalsIgnoreCase("true")) {
                return super.scenarios(); // DataProvider(parallel=true)
            }
            return super.scenarios();
        }
    }

