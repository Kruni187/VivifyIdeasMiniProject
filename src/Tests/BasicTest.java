package Tests;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
public class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor executor;
	protected String baseUrl = "https://gallery-app.vivifyideas.com/";
	protected String email = "testtest@yahoo.com";
	protected String password = "test12345";
	
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"driver-lib\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		this.wait = new WebDriverWait(driver, 30);
		this.executor = (JavascriptExecutor) driver;
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void afterTest(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			File ss = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.png'").format(new Date());
			File save = new File ("screenshots/" + fileName);
			FileHandler.copy(ss, save);
		}
		this.driver.manage().deleteAllCookies();
		this.driver.navigate().refresh();
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.quit();
	}
}
