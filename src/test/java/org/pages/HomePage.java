package org.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.util.WebDriverUtil;


public class HomePage {

	public WebDriver webDriver;


	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Customers')]")
	private static WebElement customersLink;

		
	public HomePage(WebDriver driver) {
		super();
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(how = How.XPATH, using = "//input[@id='global-search-input']")
	private static WebElement searchKeyword;
	
	
	
	@FindBy(how = How.XPATH, using = "//body/div[1]/div[1]/div[1]/div[1]/section[1]/section[1]/div[2]/div[1]/div[3]/div[2]/div[1]/form[1]/button[3]/span[1]/img[1]")
	private static WebElement searchButton;
	
	
	public void searchPage(String keyword) throws InterruptedException {
		
		searchKeyword.sendKeys(keyword);
		Thread.sleep(1000);
		searchKeyword.clear();
		Thread.sleep(1000);
		searchButton.click();
		Thread.sleep(3000);

	}
	
	
	
	
	
	

	
	

	

	
	


}
