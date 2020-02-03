package lbs.leica;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase_240_UAT_Chrome extends  ExtentReport_UAT
{
	WebDriver driver;

	public static String cardNumber = "4111-1111-1111-1111";
	public static String Expiry = "01/29";
	
	@BeforeTest
	public void initialization() 
	{
		test = extent.createTest("Order tests in UAT plethora of different order tests to verify tax, shipping, customer pricing, list pricing");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://shop-lbs.mediatis.de"); 
		System.out.println(driver.getTitle());
		
		reportLog(driver.getTitle());
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void VerifyPrices() throws IOException, InterruptedException 
	{
			driver.findElement(By.xpath("//*[@id='login-menu_login_title']")).click();
			driver.findElement(By.xpath("(//*[@id='ShopLoginForm_Login'])[2]")).clear();
			driver.findElement(By.xpath("(//*[@id='ShopLoginForm_Login'])[2]")).sendKeys("fjsim@buffalo.edu");
			driver.findElement(By.xpath("(//*[@id='ShopLoginForm_Password'])[2]")).sendKeys("!InterShop00!");
			
			reportLog("Entered username and password");

			// Click on login in button
			driver.findElement(By.xpath("(//*[@class='account-sign-in btn btn-primary'])[2]")).click();
			Thread.sleep(2000);
			
			reportLog("Clicked on Login ");
			
			// Click on Quick Order
			driver.findElement(By.xpath("//*[@class='quickorder-li hidden-xs']")).click();
			Thread.sleep(2000);
			
			reportLog("Clicked on Quick Order ");
			

			// Click on Copy and paste
			driver.findElement(By.xpath("(//*[text()='Copy and paste'])[1]")).click();
			Thread.sleep(2000);
			
			reportLog("Clicked on Copy and paste ");
			
			
			//Enter Codes in text box
			String[] voucherCodes = {"DS9800 1", "PA0515 2", "AR0222 3", "PA0027 4", "PA0571 5", "PA0813 6", "3801800 1", "3803650 1", "3800680 1", "3801815 1", "3800675 1", "3800080 1", "3800050CL 1", "3800161 1"};
			WebElement input = driver.findElement(By.id("addToCartCopyAndPaste_textArea"));
			for (String voucher : voucherCodes) 
			{
				input.sendKeys(voucher+"\n");
			}
			
			reportLog("Entered Codes ");
		
			
			//Click on Add to cart
			driver.findElement(By.id("addToCartCopyAndPaste")).click();
			Thread.sleep(2000);
			
			reportLog("Clicked on Add to cart ");

			
			//Verify and Print price
			 List<WebElement> VerifyPrice  = driver.findElements(By.xpath("//*[@class=' col-xs-4 col-sm-offset-1 col-sm-2 list-item column-price single-price']"));

			    for(WebElement price:VerifyPrice) 
			    {
			        System.out.println(price.getText());  
			        reportLog(price.getText());
			    }   
		        	System.out.println(); 
			    
			     
		   //Verify and Print Product						 
			 List<WebElement> VerifyProduct  = driver.findElements(By.xpath("//*[@class='product-title']"));
			 
				 for(WebElement product:VerifyProduct) 
				  {			    	
				    System.out.println(product.getText());  
				    reportLog(product.getText());
				  }     
				    System.out.println(); 

				 
		   //Click Checkout on shopping cart page
			 driver.findElement(By.xpath("//*[@class='btn btn-block btn-primary']")).click();
			 Thread.sleep(2000);
			 
			 reportLog("Clicked Checkout on shopping cart page ");
			
			 
		   //Select shipping address
			 driver.findElement(By.xpath("//*[@class='btn dropdown-toggle btn-default']")).click();
			 driver.findElement(By.xpath("//*[@class='dropdown-menu inner']/li[1]")).click();
			 
			 reportLog("Selected first address ");
			
			 
		  // Click Checkout on Address
			driver.findElement(By.xpath("//*[@class='cost-summary']/a")).click();
			Thread.sleep(2000);
			
			reportLog("Clicked Checkout on Address page ");
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;		 			    
			jse.executeScript("window.scrollTo(0, 1500)");
			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@class='btn btn-block btn-primary']")).click();
			Thread.sleep(5000);


			//Credit card
			 WebElement cardNumberField = driver.findElement(By.xpath("((//*[@class='propertygroup-editor-value form-control'])[1])"));
			 cardNumberField.click();
			 cardNumberField.sendKeys(cardNumber);
			 Thread.sleep(3000);
			 
			 reportLog("Entered credit card number  ");
			 
			//Enter Exp Date
			 WebElement ExpField= driver.findElement(By.xpath("(//*[@class='propertygroup-editor-value form-control'])[2]"));
			 ExpField.click();
			 ExpField.sendKeys(Expiry);
			 
			 reportLog("Entered Expiry date");
			 
			 //Enter CVV
			 driver.findElement(By.xpath("(//*[@class='propertygroup-editor-value form-control'])[3]")).sendKeys("1234");
			 Thread.sleep(2000);
			 
			 reportLog("Entered CVV number");
			 
			 //Uncheck save radio button
			 driver.findElement(By.xpath("//*[@class='col-sm-offset-4 col-sm-8']//input")).click();
			 Thread.sleep(2000);
			 
			 reportLog("Unchecked save radio button");
			 
			 //Click on Checkout button
			 driver.findElement(By.xpath("//*[@class='cost-summary']/button")).click();
			 Thread.sleep(2000);
			 
			 reportLog("Clicked Checkout ");
			 
			 
			//Verify Text for final  prices					 
			 List<WebElement> Shippingtax  = driver.findElements(By.xpath("//*[@class='dl-horizontal']/dt"));
			 
			 for(WebElement product:Shippingtax) 
			  {			    	
				 System.out.println(product.getText()); 
				 reportLog(product.getText());
			  } 
			 	System.out.println();
				
			 
		//Verify final prices
			 List<WebElement> final_prices  = driver.findElements(By.xpath("//*[@class='dl-horizontal']/dd"));
				 
			 for(WebElement product:final_prices) 
			 {
				 System.out.println(product.getText()); 					 
				 reportLog(product.getText());
			 }   
			 	System.out.println();

			 
			 //Click on Submit order on review your order page
			 driver.findElement(By.xpath("//*[@class='cost-summary']/button")).click();
			 Thread.sleep(2000);
			 
			 reportLog("Clicked on Submit order on review your order page");
			 
			 if(driver.findElements(By.xpath("//*[text()='Thank you for your order! ']")) != null)
		     {
		    	 System.out.println("We are on Thank you page");
		     }
		     else
		     {
		    	 System.out.println("Error");
		     }
			
			 driver.findElement(By.xpath("(//*[text()='View my account'])[2]")).click();
			 Thread.sleep(5000);
			 
			 driver.findElement(By.xpath("//*[text()='View complete order history']")).click();
			 Thread.sleep(10000);
			 
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;		 			    
			jse1.executeScript("window.scrollTo(0, 1500)");
			Thread.sleep(2000);
			jse1.executeScript("window.scrollTo(1500, 0)");
			Thread.sleep(3000);
		}

	

	@AfterTest
	public void aftertest() 
	{
		extent.flush();
		driver.close();
	}
}

