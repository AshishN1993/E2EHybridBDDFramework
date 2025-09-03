package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


 public class TestHooks {

        private static String baseUrl;


        @Before
        public void beforeScenario(Scenario scenario) {
// initialize driver for each scenario (or per-thread)
            DriverFactory.initDriver();
            //ExtentManager.createTest(scenario.getName());


// env mapping moved here
            String env = System.getProperty("env", "dev");
            switch (env.toLowerCase()) {
                case "qa":
                    baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
                    break;
                case "staging":
                    baseUrl = "https://staging.example.com";
                    break;
                case "prod":
                    baseUrl = "https://prod.example.com";
                    break;
                case "dev":
                default:
                    baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
            }
            scenario.log("Running on environment: " + env + " | Base URL: " + baseUrl);
        }


        public static String getBaseUrl() {
            return baseUrl;
        }


        @After
        public void afterScenario(Scenario scenario) {
            if (DriverFactory.getDriver() != null) {
                DriverFactory.quitDriver();
            }
           // ExtentManager.flushReports();
        }
    }