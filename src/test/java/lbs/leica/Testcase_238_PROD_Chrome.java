package lbs.leica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase_238_PROD_Chrome extends  ExtentReport_PROD
{

WebDriver driver;
	
	@BeforeTest
    public void initialization()
    {
		 test = extent.createTest("404 Error Test Navigation ");
		 
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
		public void CheckAllLinks() throws IOException, InterruptedException
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
						System.out.println("title for country ->" + country + " <- and for language -> " + language + "-> is -> " + driver.getTitle());
						
						reportLog("title for country ->" + country + " <- and for language -> " + language + "-> is -> " + driver.getTitle());
						
						driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
						Thread.sleep(2000);
	     
	     
											/*  Histology Equipment  */
	     
						Actions Hist_Eq = new Actions(driver); 
						WebElement HE = driver.findElement(By.xpath("//*[@class='nav']/li[2]/span//a"));	     
						Hist_Eq.moveToElement(HE).perform();
						System.out.println("Done Mouse hover on 'Histology Equipment' from Menu");
	     
						reportLog("Done Mouse hover on 'Histology Equipment' from Menu");
						
						List<String> hrefs = new ArrayList<String>();
						List<WebElement> anchors = driver.findElements(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[2]/div/ul//li/a"));
	     
						for(int k=0;k<anchors.size();k++)
						{
							System.out.println(anchors.get(k).getText());
							
							reportLog(anchors.get(k).getText());
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors ) 
						{
							hrefs.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs )
						{
							driver.get(href); 
							Thread.sleep(1000); 
			
							
					//Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
								
							driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
					        
							Actions Hist_Eq1 = new Actions(driver); 	
							WebElement HE1 = driver.findElement(By.xpath("//*[@class='nav']/li[2]/span//a"));
							Hist_Eq1.moveToElement(HE1).perform();
							Thread.sleep(2000);
						}
	    

											/*   Histology Consumables  */
	     
						Actions Hist_Co = new Actions(driver);   	     
						WebElement HC = driver.findElement(By.xpath("//*[@class='nav']/li[3]/span//a")); 	      
						Hist_Co.moveToElement(HC).perform();
						Thread.sleep(3000);
						System.out.println("Done Mouse hover on 'Histology Consumables' from Menu");
						
						reportLog("Done Mouse hover on 'Histology Consumables' from Menu");
	     
						List<String> hrefs1 = new ArrayList<String>();
						List<WebElement> anchors1 = driver.findElements(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[3]/div//a"));
	    
						for(int k=0;k<anchors1.size();k++)
						{
							System.out.println(anchors1.get(k).getText());
							
							reportLog(anchors1.get(k).getText());
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors1 ) 
						{
							hrefs1.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs1 )
						{
							driver.get(href);   
							Thread.sleep(1000);
							
							
					//Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
								
							driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
		        
							Actions Hist_Co1 = new Actions(driver);		     				     
							WebElement HC1 = driver.findElement(By.xpath("//*[@class='nav']/li[3]/span//a"));		     
							Hist_Co1.moveToElement(HC1).perform();
							Thread.sleep(2000);  
						}
		
		                     						/*  IHC  */ 
	     
						Actions IHC = new Actions(driver);	 	     
						WebElement ISH = driver.findElement(By.xpath("//*[@class='nav']/li[4]/span//a"));	     
						IHC.moveToElement(ISH).perform();
						Thread.sleep(3000);
						System.out.println("Done Mouse hover on 'IHC, ISH, FISH' from Menu");
						
						reportLog("Done Mouse hover on 'IHC, ISH, FISH' from Menu");
	     
						List<String> hrefs2 = new ArrayList<String>();
						List<WebElement> anchors2 = driver.findElements(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[4]/div//a"));
	    
						for(int k=0;k<anchors2.size();k++)
						{
							System.out.println(anchors2.get(k).getText());
							reportLog(anchors2.get(k).getText());
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors2 ) 
						{
							hrefs2.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs2 )
						{
							driver.get(href);  
							Thread.sleep(1000); 
							
							
					//Code to check if link is  showing 404 error				
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1)  
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
							
							driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
		        
							Actions IHC1 = new Actions(driver);		     		     				     
							WebElement ISH1 = driver.findElement(By.xpath("//*[@class='nav']/li[4]/span//a"));		     
							IHC1.moveToElement(ISH1).build().perform();
							Thread.sleep(2000);     
						}
	
		
		                    					/*  Digital Pathology  */   
	     
						Actions DP = new Actions(driver);	 	     
						WebElement Digi = driver.findElement(By.xpath("//*[@class='nav']/li[5]/span//a"));	     
						DP.moveToElement(Digi).perform();
						Thread.sleep(3000);
						System.out.println("Done Mouse hover on 'Digital pathology' from Menu");
						
						reportLog("Done Mouse hover on 'Digital pathology' from Menu");
	     
						List<String> hrefs3 = new ArrayList<String>();
						List<WebElement> anchors3 = driver.findElements(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[5]/div//a"));
	    
						for(int k=0;k<anchors3.size();k++)
						{
							System.out.println(anchors3.get(k).getText());
							reportLog(anchors3.get(k).getText());
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors3 ) 
						{
							hrefs3.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs3 )
						{
							driver.get(href);   
							Thread.sleep(1000);
							
				    //Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
							
							driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
		        
							Actions DP1 = new Actions(driver);		 											     
							WebElement Digi1 = driver.findElement(By.xpath("//*[@class='nav']/li[5]/span//a"));		     
							DP1.moveToElement(Digi1).build().perform();
							Thread.sleep(2000);	     
						}
		 
		 
											/*  Clinical Microscopy   */
		 
						Actions CM = new Actions(driver);	  		     
						WebElement CLI = driver.findElement(By.xpath("//*[@class='nav']/li[6]/span//a"));	     
						CM.moveToElement(CLI).perform();
						Thread.sleep(3000);
						System.out.println("Done Mouse hover on 'Clinical Microscopy' from Menu");
						
						reportLog("Done Mouse hover on 'Clinical Microscopy' from Menu");
	     
						List<String> hrefs4 = new ArrayList<String>();
						List<WebElement> anchors4 = driver.findElements(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[6]/div//a"));
	    
						for(int k=0;k<anchors4.size();k++)
						{
							System.out.println(anchors4.get(k).getText());
							reportLog(anchors4.get(k).getText());
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors4 ) 
						{
							hrefs4.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs4 )
						{
							driver.get(href);  
							Thread.sleep(1000); 
													
					//Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
							
							driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();	
	         
							Actions CM1 = new Actions(driver);		     		     					     
							WebElement CLI1 = driver.findElement(By.xpath("//*[@class='nav']/li[6]/span//a"));    		     		          		     
							CM1.moveToElement(CLI1).build().perform();
							Thread.sleep(2000);     
						}
	     
		
		
		                   						/*   Research   */
		
						Actions R = new Actions(driver);	   	     
						WebElement RE = driver.findElement(By.xpath("//*[@class='nav']/li[7]/span//a"));	     
						R.moveToElement(RE).perform();
						Thread.sleep(3000);
						System.out.println("Done Mouse hover on 'Research' from Menu");
						
						reportLog("Done Mouse hover on 'Research' from Menu");
	     
						List<String> hrefs5 = new ArrayList<String>();
						List<WebElement> anchors5 = driver.findElements(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/div/div/ul/li[7]/div//a"));
	    
						for(int k=0;k<anchors5.size();k++)
						{
							System.out.println(anchors5.get(k).getText());
							reportLog(anchors5.get(k).getText());
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors5 ) 
						{
							hrefs5.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs5 )
						{
							driver.get(href);  
							Thread.sleep(1000); 
	         
				 //Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
							
							driver.findElement(By.xpath("//*[@id=\"lbs-main-menu\"]/li[1]/a")).click();
		        
							Actions R1 = new Actions(driver);		     		     		  		     
							WebElement RE1 = driver.findElement(By.xpath("//*[@class='nav']/li[7]/span//a"));		     
							R1.moveToElement(RE1).build().perform();
							Thread.sleep(2000);     
						}
		 
		 	 
		 
												/* Service and Support Link */
		
						driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[2]/a")).click();
						Thread.sleep(2000);
		 
						List<String> hrefs6 = new ArrayList<String>();
						List<WebElement> anchors6 = driver.findElements(By.xpath("//*[@id='lbs-main-menu']/li[2]/div//a"));
	     
						for(int k=0;k<anchors6.size();k++)
						{
							System.out.println(anchors6.get(k).getText());
							reportLog(anchors6.get(k).getText());
							Thread.sleep(1000);
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors6 ) 
						{
							hrefs6.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs6 )
						{
							driver.get(href);   
							Thread.sleep(1000);
	
					//Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
							
							driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[2]/a")).click();
							Thread.sleep(2000);
						}
	     
	     
	     											/*	Knowledge Pathway  */

						driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[3]/a")).click();
						Thread.sleep(2000);
	     
						List<String> hrefs7 = new ArrayList<String>();
						List<WebElement> anchors7 = driver.findElements(By.xpath("//*[@id='lbs-main-menu']/li[3]/div//a"));
	     
						for(int k=0;k<anchors7.size();k++)
						{
							System.out.println(anchors7.get(k).getText());
							reportLog(anchors7.get(k).getText());
							Thread.sleep(1000);
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors7 ) 
						{
							hrefs7.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs7 )
						{
							driver.get(href);   
							Thread.sleep(1000);
							
							
					//Code to check if link is  showing 404 error
							
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}

							driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[3]/a")).click();
							Thread.sleep(2000);
						}

		 
		 
		 				 									/*  News & Events  */ 
	     
						driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[4]/a")).click();
						Thread.sleep(3000);
		 
		 
		 
		 													/*	About  */
		 
						driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[5]/a")).click();
						Thread.sleep(2000);
		 
						List<String> hrefs8 = new ArrayList<String>();
						List<WebElement> anchors8 = driver.findElements(By.xpath("//*[@id='lbs-main-menu']/li[5]/div//a"));
	     
						for(int k=0;k<anchors8.size();k++)
						{
							System.out.println(anchors8.get(k).getText());
							reportLog(anchors8.get(k).getText());
							Thread.sleep(1000);
						}
						System.out.println(); 
	     
						for ( WebElement anchor : anchors8 ) 
						{
							hrefs8.add(anchor.getAttribute("href"));
						}
	     
						for ( String href : hrefs8 )
						{
							driver.get(href);   
							Thread.sleep(1000);
							
							//Code to check if link is  showing 404 error
							List<WebElement> Error = driver.findElements(By.xpath("//*[contains(text(),'Whoops! That page cannot be found.')]"));
							if(Error.size() == 1) 
							{
								try 
								{
									System.out.println("404 error shown for link "+ href);

									reportLog("404 error shown for link "+ href);
									
									Assert.fail("404 error shown for link "+ href);
								} 
								catch(AssertionError ae) 
								{
									continue;
								}
							}
							
							driver.navigate().back();
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[5]/a")).click();
							Thread.sleep(1000);
						}
		
											/*  Contact Us  */
						
						driver.findElement(By.xpath("//*[@id='lbs-main-menu']/li[6]/a")).click();
						Thread.sleep(3000);
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

