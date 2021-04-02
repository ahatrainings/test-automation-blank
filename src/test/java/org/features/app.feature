Feature: App

  @FirstScenario @AllScenarios
  Scenario Outline:  Test the page load
   	 Given Open the application "<browser>"
   	 Then test the application
   	 Then I close the Browser
   	 
   	 
    Examples:
      | browser | environment | 
      | Chrome  | Automation  | 
       
 
  
  
  @SecondScenario @AllScenarios
  Scenario Outline:  Do some activity on the browser
   	 Given Open the application "<browser>"
   	 Then search keyword  "<search>"
   	 Then I close the Browser
   	 
   	 Examples:
      | browser | environment | db | search |
      | IE      | Automation  | Oracle | computers |
       

 