# botreports
Generate csv and html test reports for java based (selenium/appium) automation scripts

Usage:
Download Reporting.java inside the project's source directory.
"Reports" directory will be created inside the project directory. Find the generated reports inside this directory.

Methods:
- validateCondition(boolean condition, String testSummary): Mark a test PASS/FAIL based on a condition. Returns nothing.

-- condition: Test condition to be validated. PASS will be marked in the report if the condition if true. FAIL will be marked in the report if the condition is false.

-- testSummary: String entered here will be will be used for test summary in the report.


- setTestCasePass(String testSummary): Explicitly mark a test PASS. Returns nothing.

-- testSummary: String entered here will be will be used for test summary in the report.


- setTestCaseFail(String testSummary): Explicitly mark a test FAIL. Returns nothing.

-- testSummary: String entered here will be will be used for test summary in the report.


- setTestCaseBlocked(String testSummary): Explicitly mark a test BLOCKED. Returns nothing.

-- testSummary: String entered here will be will be used for test summary in the report.


Executing any/all of the above methods will write the results in a csv file.


- generateHTMLReport(): Call this method after the above methods have been executed, to generate the html report. Returns nothing.


Refer SampleTestCase.java for sample implementation.

Refer SampleCSVReport.csv and SampleHTMLReport.html for sample reports.
