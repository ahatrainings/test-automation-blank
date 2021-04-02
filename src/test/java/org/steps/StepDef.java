package org.steps;


import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.openqa.selenium.WebDriver;
import org.pages.HomePage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDef extends BaseSteps {

	public HomePage homePage;
	
	
	protected WebDriver webDriver;


	@Before
	public void setup(Scenario scenario) throws MalformedURLException, UnknownHostException, InterruptedException {
		super.createWebDriver();	
		this.scenario = scenario;
		homePage = super.getHomePageInstance();
		//expediaPage = new ExpediaPage(webDriver);
	}

	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	@After
	public void destroyScenario(Scenario scenario) {
		super.tearDown(scenario);
	}

	//Step Def methods 

	@Given("^Open the application \\\"([^\\\"]*)\\\"$")
	public void openApplication(String browser) {
		System.out.println("Open application" + browser);
	}

	@Then("^I Click on Media$")
	public void clickOnMedia() throws Throwable {
		//homePage.clickMedia();
	}




	@Then("^test the application$")
	public void testApplication() throws Throwable {
		System.out.println("Open application");
	}

	@Then("^I close the Browser$")
	public void i_close_the_Browser() throws Throwable {
		super.closeDriver();
	}


	@Then("^validate my data$")
	public void validateMyData() {
	}


	@Then("^write the data \\\"([^\\\"]*)\\\"$")
	public void writeTheData(String db) {
		System.out.println("writing some data to "+ db);
	}

	
	
	
	
	@Then("^search keyword  \\\"([^\\\"]*)\\\"$")
	public void searchTheData(String keyword) throws InterruptedException {
		System.out.println("Searching with keyword "+ keyword);
		homePage.searchPage(keyword);
		Thread.sleep(2000);
		
	}
	
	@Then("^search cars$")
	public void searchCars() throws InterruptedException {
		//homePage.searchCars();
		Thread.sleep(2000);
		
	}
	
	
	







}
