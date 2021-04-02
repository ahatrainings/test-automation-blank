package org.steps;


import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.constants.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.pages.HomePage;
import org.slf4j.LoggerFactory;

import cucumber.api.Scenario;

public class BaseSteps {
	
    protected WebDriver webDriver;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BaseSteps.class);

    static {
    	//System.setProperty("webdriver.chrome.driver", "PathtoIE");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "main" +File.separatorChar + "resources" + File.separatorChar + "chromedriver");
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver createWebDriver() throws MalformedURLException, InterruptedException  {
    	File directory = new File("./");
    	LOGGER.debug("Get Absolute Path : "+directory.getAbsolutePath());

    	setLocalWebDriver();

    	return webDriver;
    }

    
   

    private void setLocalWebDriver() throws InterruptedException {
        setWebDriver(new ChromeDriver());
        setCapabilities();
        setApplicationURL();
    }
    
    private void setApplicationURL() throws InterruptedException {
    	webDriver.get("https://"+Constants.APP_URL);
        //webDriver.manage().window().maximize();
    }
    

    private void setCapabilities() {

        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
       
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
    }

    
    
    public HomePage getHomePageInstance() {
        return new HomePage(webDriver);
    }
    
   

    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed() && webDriver != null) {		
          // Take a screenshot...
          final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
          scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
          webDriver.manage().deleteAllCookies();
        }
	}
}
