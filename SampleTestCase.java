import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SampleTestCase {
	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", "/Users/gaurav.gulati/Documents/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://google.com");
		Reporting.validateCondition(driver.getTitle().equals("Google"), "Validating page title");

		if (driver.findElement(By.cssSelector("div.gb_f:nth-child(2) > a:nth-child(1)")).getText().equals("Images"))
			Reporting.setTestCasePass("Validating images link text");
		else
			Reporting.setTestCaseFail("Validating images link text");
		
		if (driver.findElements(By.linkText("Drive")).size()!=0)
			Reporting.setTestCasePass("Validating presence of google drive link on google.com page");
		else
			Reporting.setTestCaseFail("Validating presence of google drive link on google.com page");
		
		if (driver.findElements(By.id("doodle")).size()!=0)
			Reporting.setTestCasePass("Checking the presence of doodle on google.com page");
		else
			Reporting.setTestCaseBlocked("Checking the presence of doodle on google.com page");

		driver.quit();
		Reporting.generateHTMLReport();

	}

}
