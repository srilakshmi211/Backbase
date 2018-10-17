# Backbase Computer Database Automation Framework

This reprository contains regression tests for computer database application: http://computer-database.herokuapp.com/computers.

They are written using on selenium webdriver, java and testng. The IDE used for developing is IntelliJ IDEA.

To execute tests:

1. Clone the project 
2. Import the project in IntelliJ IDEA as a gradle project.
3. Run testng.xml or RegressionTests.java to execute regression test suite.

The testcases are run on chrome browser, takes screenshot at the end of every testcase and stores it in the /screenshots folder for future reference and test report is generated in /test-output folder.
