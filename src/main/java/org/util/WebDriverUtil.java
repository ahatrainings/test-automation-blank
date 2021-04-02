package org.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WebDriverUtil {

    public static void highLightElement(WebDriver driver, WebElement element, String color) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid " + color + "'", element);
        }
    }

    public static boolean isElementPresent(WebDriver driver, WebElement element) {
        try {
            waitforElementPresent(driver, element);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return element.isDisplayed();

    }

    public static void waitForPageToGetLoaded(WebDriver driver) {
        new WebDriverWait(driver, 10).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void sendKeys(WebDriver driver, WebElement element, String testData) {
        waitforElementPresent(driver, element);
        highLightElement(driver, element, "red");
        element.clear();
        element.sendKeys(testData);
    }

    public static void clickElement(WebDriver driver, WebElement element) {
        boolean flag = waitforElementPresent(driver, element);
        highLightElement(driver, element, "red");
        element.click();
    }


    public static void downloadFile() throws InterruptedException, AWTException {
        Thread.sleep(2000);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public static String getText(WebDriver driver, WebElement element) {
        waitforElementPresent(driver, element);
        return element.getText();

    }

    public static boolean waitforElementPresent(WebDriver driver, WebElement element) {
        boolean elementPresent = false;
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed())
                elementPresent = true;
        } catch (NoSuchElementException nsee) {
            System.out.println("No Such Element present exception occured");
        } catch (Exception e) {
            System.out.println("Generic Exception occured : " + element);
        }

        return elementPresent;
    }


    public static void selectFromDpDn(WebDriver driver, WebElement element, String typeValue) throws InterruptedException {
        highLightElement(driver, element, "red");
        Select select = new Select(element);
        select.selectByVisibleText(typeValue);

    }
    public static void selectFromDpDnByValue(WebDriver driver, WebElement element, String typeValue) throws InterruptedException {
        highLightElement(driver, element, "red");
        Select select = new Select(element);
        select.selectByValue(typeValue);

    }

    public static String getAttribute(WebDriver webDriver,
                                      WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }


    public static String getInnerText(WebDriver driver) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sText = js.executeScript("return document.documentElement.innerText;").toString();
        return sText;

    }

    public static boolean isElementEnabled(WebDriver driver, WebElement element) {
        waitforElementPresent(driver, element);
        return element.isEnabled();
    }

    public static void switchToAlert(WebDriver driver, String alertName) {
        try {
            Alert alert = driver.switchTo().alert();
            String AlertText = alert.getText();
            System.out.println("Alert Text is " + AlertText);
            if (alertName.contains(AlertText)) {
                alert.accept();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int random_int(int Min, int Max) {
        return (int) (Math.random() * (Max - Min)) + Min;
    }

    public static void selectFromDpDnByIndex(WebDriver webDriver, WebElement element, int i) {
        try {
            Select select = new Select(element);
            //select.deselectAll();
            select.selectByIndex(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectFromDpDnByContainsValue(WebDriver webDriver, WebElement element, String value) {
        try {
            Select select = new Select(element);
            List<WebElement> list = select.getOptions();

            for(int i=0;i<list.size();i++){
                if(list.get(i).getText().contains(value)){
                    select.selectByIndex(i);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectFromDpDnByIgnoringFirstCharacters(WebDriver webDriver, WebElement element, String value,int numOfcharactersToIgnore) {
        try {
            Select select = new Select(element);
            List<WebElement> list = select.getOptions();

            for(int i=0;i<list.size();i++){
                if(list.get(i).getText().length() > numOfcharactersToIgnore && list.get(i).getText().substring(numOfcharactersToIgnore).equalsIgnoreCase(value)){
                    select.selectByIndex(i);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleAlert(WebDriver webDriver) {

        try {
            Alert alert = webDriver.switchTo().alert();
            String str = alert.getText();
            System.out.println(str);
            alert.accept();
        } catch (Exception ex) {
            System.out.println("Execption occured during alert handle");
        }
    }


    public static void scrolDown(WebDriver webDriver) {
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    /*
     *  if (WebDriverHelper.isElementPresent(webDriver, duplicateElement)) {
List<WebElement> tr_collection = webDriver
.findElements(By.xpath("//md-select[contains(@aria-label,'Ad Copy Duplicate Reason')]"));




WebDriverHelper.clickElement(webDriver, adCopySearchBtn);


WebDriverHelper.sendKeys(webDriver, adCopySearchInput, adCopyId);

webDriver.switchTo().frame("WarningBanner");
webDriver.switchTo().defaultContent();

    @FindBy(how = How.ID, using = "phoneNumber")
    private static WebElement phoneNumberElement;



  Alert alert1 = webDriver.switchTo().alert();
        if (alert1.getText().contains("Credit Card Saved Successfully")) {
            alert1.accept();
        }


webDriver.findElement(By.cssSelector(".dropdown_button > span")).click();
businessName.clear();


Select dropdown1 = new Select(webDriver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
Thread.sleep(2000);
dropdown1.selectByVisibleText(testData.getMonth());

webDriver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'"+testData.getStartDate()+"')]")).click();


currentElement = webDriver.switchTo().activeElement();



Actions actions = new Actions(webDriver);
actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();


WebElement header = webDriver.findElement(By.xpath("//*[@id=\"modalPopupComponent\"]/div[1]/div/div[3]/div/button[2]"));
Actions actions = new Actions(webDriver);
actions.moveToElement(header).perform();


public void uploadImage() throws AWTException, InterruptedException {
//To Delay execution for 10 sec. as to view the resize browser
webDriver.findElement(By.cssSelector(".browse-btn")).click();
Thread.sleep(2000);
Actions act = new Actions(webDriver);
act.sendKeys(Keys.ESCAPE).build().perform();
Actions actions = new Actions(webDriver);
actions.moveToElement(webDriver.findElement(By.cssSelector(".browse-btn")));
actions.click();
actions.sendKeys("SOME DATA");
actions.build().perform();


Robot robot = new Robot();

robot.keyPress(KeyEvent.VK_C);
robot.keyRelease(KeyEvent.VK_C);

//     uploadFile("C:\\ad\\demo.mp4");
WebElement currentElement = webDriver.switchTo().activeElement();
currentElement.sendKeys("C:\\ad\\demo.mp4");
}


Alert alert = webDriver.switchTo().alert();
alert.accept();
     */
}
