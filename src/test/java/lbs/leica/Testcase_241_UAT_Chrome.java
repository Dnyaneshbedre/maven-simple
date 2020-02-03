package lbs.leica;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase_241_UAT_Chrome extends  ExtentReport_UAT
{

	WebDriver driver;
	
	@BeforeTest
    public void initialization()
    {
		
		 test = extent.createTest("Country Selector Testing");
		
   	 	 System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.navigate().to("https://shop-lbs.mediatis.de");  
	     System.out.println(driver.getTitle());
	     
	     reportLog(driver.getTitle());
	     
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   }
	
	@Test
	public void E2E() throws IOException, InterruptedException
	{
		driver.findElement(By.xpath("//div[@id='country_selection']")).click();
		Thread.sleep(2000);

		Select sel = new Select(driver.findElement(By.xpath("//select[@id='countrySelectionSelect']")));
		List<WebElement> dropdown_eles = sel.getOptions();

		for (int i = 0; i < dropdown_eles.size(); i++) 
		{
			String country = dropdown_eles.get(i).getText();
			sel.selectByVisibleText(country);
			Thread.sleep(4000);

			List<WebElement> language_radio_buttons = driver.findElements(By.xpath("//label[@class='radio']"));

			for (int j = 0; j < language_radio_buttons.size(); j++) 
			{
				if (language_radio_buttons.get(j).isDisplayed()) 
				{
					String language = language_radio_buttons.get(j).getText();
					language_radio_buttons.get(j).click();
					driver.findElement(By.xpath("//span[@id='countrySelectionSaveBtn']")).click();
					Thread.sleep(5000);
					System.out.println("title for country ->" + country + " <- and for language -> " + language + "-> is -> " + driver.getTitle());
					
					reportLog("title for country ->" + country + " <- and for language -> " + language + "-> is -> " + driver.getTitle());
					
					Thread.sleep(2000);
					
					// Click on Product
					driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
					Thread.sleep(2000);				
					
					// Mouse hover on Histology Consumables
					Actions Hist_Co = new Actions(driver);
					WebElement HC = driver.findElement(By.xpath("//*[@class='nav']/li[3]/span//a"));
					Hist_Co.moveToElement(HC).perform();
					Thread.sleep(3000);
					System.out.println("Done Mouse hover on 'Histology Consumables' from Menu");
					
					reportLog("Done Mouse hover on 'Histology Consumables' from Menu ");

					// Click on Cassettes link
					driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[3]/div/ul/div[1]/li/div/ul/li[3]/a")).click();
					Thread.sleep(2000);
					
					reportLog("Clicked on Cassettes link ");				
				}
				
				driver.findElement(By.xpath("//div[@id='country_selection']")).click();
				Thread.sleep(3000);
				language_radio_buttons = driver.findElements(By.xpath("//label[@class='radio']"));
			}
			
			sel = new Select(driver.findElement(By.xpath("//select[@id='countrySelectionSelect']")));
			dropdown_eles = sel.getOptions();
		}

	}
	
	@AfterTest
	  public void aftertest()
		{
			extent.flush();
			driver.close();
		}
}