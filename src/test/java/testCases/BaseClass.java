package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	public static WebDriver driver;
	
	public XSSFWorkbook wbook;
	public XSSFSheet sheet;


//	@BeforeMethod(alwaysRun=true)
//	public void SetUpDriver() throws IOException {
//		
//String browser = System.getProperty("Browser");
//		
//		if (browser.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver();
//		}else if(browser.equalsIgnoreCase("remote-chrome")) {
//			
//			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setPlatform(Platform.WIN11);
//			cap.setBrowserName("chrome");
//
//			URL hub = new URL("http://localhost:4444/");
//			driver = new RemoteWebDriver(hub, cap);
//			
//		}
//		else {
//			driver = new ChromeDriver();
//		}
//		
//		driver.get("https://simplilearn.com/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//	}
//
//
//
//	@AfterMethod(alwaysRun=true)
//	public void TearDown() {
//
//		driver.quit();
//	}
//
//	@BeforeTest(alwaysRun=true)
//	public void SetUpExcel1() throws IOException  {
//		FileInputStream fis = new FileInputStream("exceldata.xlsx");
//		wbook = new XSSFWorkbook(fis);
//		sheet = wbook.getSheet("Sheet1");
//
//	}
//
//	@AfterTest(alwaysRun=true)
//	public void CloseUpExcel() throws IOException {
//		wbook.close();
//	}
	//========
	private ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	public void SetDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}
	
	public WebDriver GetDriver() {

		return threadLocalDriver.get();

	}


	@BeforeMethod(alwaysRun=true)
	public void SetUpDriver() throws MalformedURLException {
		
		String browser = System.getProperty("Browser");
		//String browser = "chrome";
		
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("remote-chrome")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.WIN10);
			cap.setBrowserName("chrome");

			//URL hub = new URL("http://localhost:4444/");
			URL hub = new URL("http://192.168.31.131:4444");
			driver = new RemoteWebDriver(hub, cap);
			
		}
		else {
			driver = new ChromeDriver();
		}
		
		SetDriver(driver);
		GetDriver().get("https://simplilearn.com/");
		GetDriver().manage().window().maximize();
		GetDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void TearDown() {
		
		GetDriver().quit();
	}



}
