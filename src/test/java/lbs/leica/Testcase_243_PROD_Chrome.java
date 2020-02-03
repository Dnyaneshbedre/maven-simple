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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase_243_PROD_Chrome extends  ExtentReport_PROD
{
	    WebDriver driver;
	    XSSFWorkbook workbook;
	    XSSFSheet sheet;
	    XSSFCell cell;
	 
	    @BeforeTest
	     public void initialization()
	    {
	    	 test = extent.createTest("Search Shop US/Global at scale ");
	    	
	    	 System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			 driver=new ChromeDriver();
			 driver.manage().window().maximize();
			 driver.navigate().to("https://shop.leicabiosystems.com");  
		     System.out.println(driver.getTitle());
		     
		     reportLog(driver.getTitle());
		     
			 driver.manage().deleteAllCookies();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    }
	       
	    @Test
	    public void Search() throws IOException, InterruptedException
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
						Thread.sleep(3000);
						System.out.println("title for country ->" + country + " <- and for language -> " + language+ "-> is -> " + driver.getTitle());
						
						reportLog("title for country ->" + country + " <- and for language -> " + language + "-> is -> " + driver.getTitle());
					
						//Code to take input from Sheet
						 File src=new File("Search-Terms_20191206.xlsx");   
					   	 FileInputStream fis = new FileInputStream(src);
					   	 workbook = new XSSFWorkbook(fis);
					   	 sheet= workbook.getSheetAt(1); 	
					   	 
					   	 
					   	for(int k=1; k<=sheet.getLastRowNum(); k++) 
					 	{
					 	cell = sheet.getRow(k).getCell(0);
					 	System.out.println(cell.getStringCellValue());
					 	
					 	reportLog(cell.getStringCellValue());
					 	
					 	//Check if Shop option is present
						 driver.findElement(By.xpath("//*[@id=\"navbar-search-selector\"]/option[2]")).click();
						 if(driver.findElements(By.xpath("//*[@id=\"navbar-search-selector\"]/option[2]")) != null)
					     {
					    	 System.out.println("Shop option is Present");
					    	 
					    	 reportLog("Shop option is Present");
					     }
					     else
					     {
					    	 System.out.println("Shop option is Absent");
					    	 
					    	 reportLog("Shop option is Absent");
					     }

					    driver.findElement(By.xpath("//*[@id='solr-search']")).clear();
					    driver.findElement(By.xpath("//*[@id='solr-search']")).sendKeys(cell.getStringCellValue());
					    
					    driver.findElement(By.xpath("(//*[@class='fa fa-search'])[2]")).click();
					    Thread.sleep(2000);
					    
					    
					    List<WebElement> No_Result = driver.findElements(By.xpath("//*[@class='no-search-result-title']"));
						if(No_Result.size() == 1) 
						{
							try 
							{
								System.out.println("No results found for keyword "+cell.getStringCellValue());

								reportLog("No results found for keyword "+cell.getStringCellValue());
								
								Assert.fail("No results found for keyword "+cell.getStringCellValue());
							} 
							catch(AssertionError ae) 
							{
								driver.navigate().back();
								continue;
							}
						}
						driver.navigate().back();
					    
					   
					    fis.close();
					 	}
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