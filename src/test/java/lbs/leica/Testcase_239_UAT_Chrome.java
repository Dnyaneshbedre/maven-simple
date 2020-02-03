package lbs.leica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Testcase_239_UAT_Chrome extends  ExtentReport_UAT
{
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;


	@BeforeTest
	public void initialization() 
	{
		test = extent.createTest("Login checks to investigate issues with user accounts");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://shop-lbs.mediatis.de"); 
		System.out.println(driver.getTitle());
		
		reportLog(driver.getTitle());
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void LoginChecks() throws IOException, InterruptedException 
	{
		File src = new File("AllIntershopCustomers_20191206.xlsx");
		FileInputStream fis = new FileInputStream(src);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++)
		{
			cell = sheet.getRow(i).getCell(0);
			System.out.println(cell.getStringCellValue());
			
			reportLog(cell.getStringCellValue());
			
			driver.findElement(By.xpath("//*[@id='login-menu_login_title']")).click();
			driver.findElement(By.xpath("(//*[@id='ShopLoginForm_Login'])[2]")).clear();
			driver.findElement(By.xpath("(//*[@id='ShopLoginForm_Login'])[2]")).sendKeys(cell.getStringCellValue());
			driver.findElement(By.xpath("(//*[@id='ShopLoginForm_Password'])[2]")).sendKeys("!InterShop00!");

			// Click on login in button
			driver.findElement(By.xpath("(//*[@class='account-sign-in btn btn-primary'])[2]")).click();
			Thread.sleep(2000);
			
			// Click on Quick Order
			driver.findElement(By.xpath("//*[@class='quickorder-li hidden-xs']")).click();
			Thread.sleep(2000);
			
			System.out.println(">>>>>>"+driver.getCurrentUrl());
			
			List<WebElement> LogIn = driver.findElements(By.xpath("//*[text()='Log In']"));
			if(LogIn.size() == 4) 
			{
				try 
				{
					System.out.println("The page has unexpectedly logged out for the user "+cell.getStringCellValue());
					
					reportLog("The page has unexpectedly logged out for the user "+cell.getStringCellValue());
					
					Assert.fail("The page has unexpectedly logged out for the user "+cell.getStringCellValue());
				} 
				catch(AssertionError ae) 
				{
					continue;
				}
			}

			// Click on Product
			driver.findElement(By.xpath("//*[text()='Product']")).click();
			Thread.sleep(3000);

			// Mouse hover on Histology Consumables
			Actions Hist_Co = new Actions(driver);
			WebElement HC = driver.findElement(By.xpath("(//*[text()='Histology Consumables'])[1]"));
			Hist_Co.moveToElement(HC).perform();
			Thread.sleep(5000);
			System.out.println("Done Mouse hover on 'Histology Consumables' from Menu");
			
			reportLog("Done Mouse hover on 'Histology Consumables' from Menu ");

			// Click on Cassettes link
			driver.findElement(By.xpath("(//*[text()='Cassettes'])[1]")).click();
			Thread.sleep(3000);
			
			reportLog("Clicked on Cassettes link ");
			
			
			// Click on Service and Support Link
			driver.findElement(By.xpath("(//*[text()='Service & Support'])[1]")).click();
			Thread.sleep(3000);

			reportLog("Clicked on Service and Support Link ");
			
			// Click on Training Link
			driver.findElement(By.xpath("//*[text()='Training']")).click();
			Thread.sleep(3000);
			
			reportLog("Clicked on Training Link");
			
			// Click on Product Link
			driver.findElement(By.xpath("//*[text()='Product']")).click();
			Thread.sleep(3000);
			
			reportLog("Clicked on Product Link");

			// Mouse hover on IHC
			Actions IHC = new Actions(driver);
			WebElement ISH = driver.findElement(By.xpath("(//*[text()='IHC, ISH, FISH'])[1]"));
			IHC.moveToElement(ISH).perform();
			Thread.sleep(2000);
			System.out.println("Done Mouse hover on 'IHC, ISH, FISH' from Menu");
			
			reportLog("Done Mouse hover on 'IHC, ISH, FISH' from Menu");

			// Click on Specialized Link
			driver.findElement(By.xpath("//*[text()='Specialized']")).click();
			Thread.sleep(3000);
			
			reportLog("Clicked on Specialized Link");
			
			
			//Click on Logout
			driver.findElement(By.xpath("(//*[@id='login-menu_login_title'])[1]")).click();
			WebElement logoutElement =driver.findElement(By.xpath("//*[@href='https://shop-lbs.mediatis.de/us/logout']"));
			logoutElement.click();
			
			reportLog("Clicked on Logout Link");

			fis.close();
		}
	}

	@AfterTest
	public void aftertest() 
	{
		extent.flush();
		driver.close();
	}
}
