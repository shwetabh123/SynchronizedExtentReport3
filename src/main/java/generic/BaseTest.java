package generic;



import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import com.pulse.Page.HomePage;
import com.pulse.Page.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.LogStatus;


import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;




public abstract class BaseTest implements IAutoConst{


	
	public   WebDriver driver=null;
	
       //public   RemoteWebDriver driver=null;
	
	public static int REPEAT_MINUS = 100;
	
Logger APP_LOGS = Logger.getLogger("BaseTest");
     
	
	public static String scrshotFolderLoc;

//	public static String path ="D:\\ShwetabhWorkspace\\SynchronizedExtentReport3\\data\\preCondInput.xlsx";

	
	
	
	 final static String workingDir = System.getProperty("user.dir");

	  final static String filePath = "\\data\\preCondInput.xlsx";

	//public static String path ="C:\\Users\\Admin\\workspace\\SynchronizedExtentReport5\\data\\preCondInput.xlsx";

	  public static String path =workingDir+filePath;
	
	
	
	public static String appURL ="";
	
	
	
	
	static int testRunId=000000;
	
	  public static String logfiletimestamp;

		 
		 public static String downloadPath = null;
		
		public  static ExtentReports extent;
		public  static  ExtentTest extentTest; 
		  private static ThreadLocal parentTest = new ThreadLocal();
		    private static ThreadLocal test = new ThreadLocal(); 
		
//	public 	static ExtentHtmlReporter htmlReporter;
//	public 	static ExtentReports extent;
//	public 	static ExtentTest extentTest;
	

//    private static ThreadLocal parentTest = new ThreadLocal();
//    private static ThreadLocal test = new ThreadLocal(); 
	    
		static  Excel eLib = new Excel();

		String browserType = eLib.getCellValue(path,"PreCon", 1, 1);
		
		
		 int time = Integer.parseInt((eLib.getCellValue(path,"PreCon", 1, 2)).replace(".0", ""));
		
		
		 
			@BeforeSuite
			public void beforeSuite(){
		

				extent = ExtentManager.getExtentReport();
	
				
				
			}
		 
		  @BeforeClass
		    public synchronized void beforeClass() {
		   
			//  ExtentTestManager.startTest(getClass().getSimpleName());
					
		    }

		 
			
/*			   @BeforeMethod
			   
			   @Parameters({"browser","appURL"})
				
			    public synchronized void beforeMethod(Method method,String browser,String appURL ) throws Exception
			   {

				  

			
				 
			 //      ExtentTestManager.getTest(method.getName()).log(LogStatus.INFO, "Test Started");
			  
			   	
			       ExtentTestManager.getTest(getClass().getSimpleName()).log(LogStatus.INFO, "TEST CASE STARTED IS -> "+method.getName()); //to add name in extent report

				     
					//  ExtentManager.getExtentReport().endTest(ExtentTestManager.getTest());        
				      
					  ExtentManager.getExtentReport().flush();
					driver = getDriver(browser);
					
					
					driver.manage().window().maximize();
					        
			              driver.navigate().to(appURL);
				    
				    System.out.println("Opening URL--> "+appURL);
			    }
			  
	
	


		public static RemoteWebDriver getDriver(String browser) throws Exception {

		
		

			 String fileName=null;
			   
	    	 Date d = new Date();
	    	 
	    	 
	 	    SimpleDateFormat format = new SimpleDateFormat(  "dd.MMMMM.yyyy.hh.mm");
	 	   

	 	   logfiletimestamp = "AutomationLog_TestRunId_"+format.format(d);
	 	   
	 	   
	 	   
		    System.setProperty("autologname",logfiletimestamp);

		      PropertyConfigurator.configure("Log4j.properties");
		    
        	String browserType = eLib.getCellValue(path,"PreCon", 1, 1);
	

		
			
        	return new RemoteWebDriver(new URL("http://192.168.2.6:4444/wd/hub"), getBrowserCapabilities(browser));
	      //return new RemoteWebDriver(new URL("http://10.111.108.157:4444/wd/hub"), getBrowserCapabilities(browser));
			
			
		}
		
		public static DesiredCapabilities getBrowserCapabilities(String browser) throws MalformedURLException
		
		{
			

            DesiredCapabilities dr=null;

            
            RemoteWebDriver driver=null;
            
			switch (browser) 
			
			
			
			{
			case "firefox":
				System.out.println("Opening firefox driver");

			//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\workspace\\SynchronizedExtentReport3\\driver\\chromedriver.exe");
			
				
				 DesiredCapabilities capability = new DesiredCapabilities().firefox();
		            capability.setBrowserName("firefox");
		            capability.setPlatform(Platform.WINDOWS);
		            
		         //   DesiredCapabilities capabillities = new DesiredCapabilities("firefox", "3.6.", Platform.WINDOWS);
		            capability.setCapability("job-name", "Fancy Firefox profile");
		            FirefoxProfile profile = new FirefoxProfile();
		            profile.setPreference("network.http.phishy-userpass-length", 255);
		            capability.setCapability(FirefoxDriver.PROFILE, profile);
		            
		            
		            
		            
				return DesiredCapabilities.firefox();
				
				
				
			case "chrome":
				System.out.println("Opening chrome driver");
			
		
				    DesiredCapabilities capability1 = DesiredCapabilities.chrome();
				    capability1.setBrowserName("chrome");
				    capability1.setPlatform(Platform.ANY);


				    ChromeOptions options = new ChromeOptions();
		          //  options.addArguments("--start-maximized");

				    return DesiredCapabilities.chrome();
					
				
				
				
				
			case "IE":
				System.out.println("Opening IE driver");
				return DesiredCapabilities.internetExplorer();
			default:
				System.out.println("browser : " + browser + " is invalid, Launching Firefox as browser of choice..");
				return DesiredCapabilities.firefox();
				
				
			
			
			}
			
	
		
		}
		*/
	
		
		
		@BeforeMethod
		
		public void preCondition(Method method) throws Exception
		
		{
			
			 //      ExtentTestManager.getTest(method.getName()).log(LogStatus.INFO, "Test Started");
			  
		   	
		       ExtentTestManager.getTest(getClass().getSimpleName()).log(LogStatus.INFO, "TEST CASE STARTED IS -> "+method.getName()); //to add name in extent report

			     
				//  ExtentManager.getExtentReport().endTest(ExtentTestManager.getTest());        
			      
				  ExtentManager.getExtentReport().flush();
		
			
			
				extentTest =extent.startTest(method.getName());
				
			 String fileName=null;
			   
	    	 Date d = new Date();
	    	 
	    	 
	 	    SimpleDateFormat format = new SimpleDateFormat(  "dd.MMMMM.yyyy.hh.mm");
	 	   

	 	  // logfiletimestamp = "AutomationLog_TestRunId-"+b.getlastRunid()+"_"+format.format(d);
	 	   
	 	   logfiletimestamp = "AutomationLog_TestRunId-_"+format.format(d);
		 	 
	 	   
	 	  
		    System.setProperty("autologname",logfiletimestamp);

		      PropertyConfigurator.configure("Log4j.properties");
		    
		    	 
		    	 
			//scrshotFolderLoc=BasePage.createFolder();
			    
				Excel eLib = new Excel();
				
			//	String url = eLib.getCellValue(path,"PreCon", 1, 0);
				
			
				
				String browserType = eLib.getCellValue(path,"PreCon", 1, 1);
				
				
				int time = Integer.parseInt((eLib.getCellValue(path,"PreCon", 1, 2)).replace(".0", ""));
				
				
				
		
				//downloadPath=b.preInitialize();
				
				
				if(browserType.equalsIgnoreCase("GC"))
				{
					//System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject6\\driver\\chromedriver.exe");
				
					
					  
					//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\workspace\\SynchronizedExtentReport4\\driver\\chromedriver.exe");
					
					String path = System.getProperty("user.dir");

                                        System.setProperty("webdriver.chrome.driver",path+"\\driver\\chromedriver.exe");

					
					
					
				     HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
				     chromePrefs.put("profile.default_content_settings.popups", 0);  
				     
				 	
				   //  chromePrefs.put("browser.download.folderList", 2);
				     
			//	    chromePrefs.put("browser.download.manager.showWhenStarting", false);
				     
				     chromePrefs.put("download.default_directory", downloadPath);  
				     
				     
				     

				     chromePrefs.put("safebrowsing.enabled", true);  
				     
				     
				 	ChromeOptions options = new ChromeOptions();


				     HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();  
				     
				  //   options.setExperimentalOption("prefs", chromePrefs);	   
			        // options.addArguments("--test-type");
			         
			         
						
						DesiredCapabilities cap = DesiredCapabilities.chrome();

						
						cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
					    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						cap.setCapability(ChromeOptions.CAPABILITY, options);
						
						
						
						
				   driver = new ChromeDriver(cap); 
				     
				     
				     
				     
				     //***************george ////////////////

					/*	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
						chromePrefs.put("profile.default_content_settings.popups", 2);
						chromePrefs.put("download.default_directory", downloadPath);
						chromePrefs.put("plugins.always_open_pdf_externally", true);
						//Enable Flash
//						chromePrefs.put("profile.default_content_setting_values.plugins", 1);
						chromePrefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
						chromePrefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
						
						// Hide save credentials prompt
						chromePrefs.put("credentials_enable_service", false);
						chromePrefs.put("profile.password_manager_enabled", false);
						
							
						
						ChromeOptions options = new ChromeOptions();

						HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
						options.setExperimentalOption("prefs", chromePrefs);
						options.addArguments("--test-type");
						options.addArguments("chrome.switches","--disable-extensions");
						options.addArguments(Arrays.asList("allow-running-insecure-content", "ignore-certificate-errors"));
						options.addArguments("disable-infobars");
					
						options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
						
						
//						if(dr.IsProxy==1)
//						 {
					//	 options.setCapability(CapabilityType.PROXY, dr.seleniumProxy); //Capabilities to run driver with seleniumproxy
//						 }
					

//						 options.addArguments("--start-maximized");
					//	 options.addArguments("--start-fullscreen");
				//		 options.addArguments("--proxy-server=10.32.120.120:8080");
				//		 options.addArguments("--no-proxy-server");

						// taking fullscreenshot testing
						//options.setExperimentalOption("useAutomationExtension", false);
						
//						DesiredCapabilities cap = DesiredCapabilities.chrome();
//						cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
//						cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//						cap.setCapability(ChromeOptions.CAPABILITY, options);
//						// cap.setCapability("chrome.switches", "--start-maximized");
//						cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

						  ExtractJSError method KR-15726
						LoggingPreferences logPrefs = new LoggingPreferences();
						logPrefs.enable(LogType.BROWSER,Level.ALL);
						logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//						cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
						options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
						 added for Js error collector testing 

						driver = new ChromeDriver(options);
						
				     
	*/			     
				     
				     
				     
						APP_LOGS.info("Chrome Browser opened");

					
					//**************************************			
								
								
								
								
								
												
					
			/*		
//			selenium 3.9
			
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 1);
			chromePrefs.put("download.default_directory", downloadPath);
			chromePrefs.put("plugins.always_open_pdf_externally", true);
			//Enable Flash
//			chromePrefs.put("profile.default_content_setting_values.plugins", 1);
			chromePrefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			chromePrefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			
			// Hide save credentials prompt
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			
			ChromeOptions options = new ChromeOptions();

			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		//	options.setExperimentalOption("prefs", chromePrefs);
		//	options.addArguments("--test-type");
		//	options.addArguments("chrome.switches","--disable-extensions");
		//	options.addArguments(Arrays.asList("allow-running-insecure-content", "ignore-certificate-errors"));
		//	options.addArguments("disable-infobars");
			
			
//			 options.addArguments("--start-maximized");
		//	 options.addArguments("--start-fullscreen");
			// options.addArguments("--proxy-server=10.32.120.120:8080");

			// taking fullscreenshot testing
			//options.setExperimentalOption("useAutomationExtension", false);
			
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			// cap.setCapability("chrome.switches", "--start-maximized");
			cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

			  ExtractJSError method KR-15726
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER,Level.ALL);
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			 added for Js error collector testing 

			driver = new ChromeDriver(cap);

			// driver = new ChromeDriver(options);
			// driver = new ChromeDriver();

					*/
					
					
					
					
					
					
		//**************************************			
					
					
					
					
					
					
					
					
					
					
					
					
		//			old selenium 2.45
					
					/*
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 1);
					chromePrefs.put("download.default_directory", downloadPath);

					ChromeOptions options = new ChromeOptions();

					HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
					//options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("--test-type");
					options.addArguments("chrome.switches","--disable-extensions");
					options.addArguments(Arrays.asList("allow-running-insecure-content", "ignore-certificate-errors"));
					options.addArguments("disable-infobars");
					// options.addArguments("--start-maximized");
					// options.addArguments("--proxy-server=10.32.120.120:8080");

					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options);
					// cap.setCapability("chrome.switches", "--start-maximized");
					cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

					  ExtractJSError method KR-15726
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER,Level.ALL);
					cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
					 added for Js error collector testing 

					driver = new ChromeDriver(cap);
					
					
				     
				     
				     
				     
				     
				     */
				     
				     
				     
						/*	
						HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
						chromePrefs.put("profile.default_content_settings.popups", 0);
						chromePrefs.put("download.default_directory", downloadPath);
						ChromeOptions options = new ChromeOptions();
						options.setExperimentalOption("prefs", chromePrefs);
						DesiredCapabilities cap = DesiredCapabilities.chrome();
						cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						cap.setCapability(ChromeOptions.CAPABILITY, options);
						WebDriver driver = new ChromeDriver(cap);
						
					
				     
				     
				     
					
			//		**************************************				
					
					/*HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadPath);
					ChromeOptions options = new ChromeOptions();
					//options.setExperimentalOption("prefs", chromePrefs);
					
					
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options);
					WebDriver driver = new ChromeDriver(cap);
					*/
					
					
						
						
						
						
					
					//**************************************				
					
					
				/*	Map<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);
					ChromeOptions options = new ChromeOptions();
					Map<String, Object> chromeOptionsMap = new HashMap<String, Object>();
					options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("--test-type");
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options);
					WebDriver driver = new ChromeDriver(cap);
					*/
					
					
					//**************************************		
					
					
				/*	Map<String, Object> prefs = new HashMap<String, Object>();
					prefs.put("download.default_directory", downloadPath);
					
					prefs.put("download.prompt_for_download",false);
					
					DesiredCapabilities caps = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", prefs);
					options.addArguments("--disable-extensions");
					caps.setCapability(ChromeOptions.CAPABILITY, options);
					WebDriver driver = new ChromeDriver(caps);*/
					
					
					
					
					
					//**************************************			
					
					
					
					
					
					APP_LOGS.info("Chrome Browser opened");
				}
				
				
				
				
				else if(browserType.equalsIgnoreCase("FF"))
				{
					
					
				   //   File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	                  
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject6\\driver\\geckodriver.exe");
					//System.setProperty("webdriver.firefox.marionette","C:\\Users\\ssrivastava4\\workspace\\PulseProject6\\driver\\geckodriver.exe");
					
					
	             //      downloadPath=b.preInitialize();
			
			
	         //        File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	                   
	                   
	                  // FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
	                   
	                   
	                                      
	                  
					 
					FirefoxProfile profile = new FirefoxProfile();
				
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.download.manager.showWhenStarting", false);
					profile.setPreference("browser.download.dir", downloadPath);
					
					
			//		profile.setPreference("browser.helperApps.neverAsk.openFile",
				//			"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
					
					
					
					
			//		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
			//"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation");
					
					
					profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
							"application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation");
									
					
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
					profile.setPreference("browser.download.manager.focusWhenStarting", false);
					profile.setPreference("browser.download.manager.useWindow", false);
					profile.setPreference("browser.download.manager.showAlertOnComplete", false);
					profile.setPreference("browser.download.manager.closeWhenDone", false);
				
					
					DesiredCapabilities capabilities = new DesiredCapabilities();
				    capabilities.setBrowserName("firefox");
				    capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
				    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				    driver = new FirefoxDriver(capabilities);
				    
				    
				    
			   		 
					 FirefoxOptions options = new FirefoxOptions();
					 options.setProfile(profile);
					 
				//	 driver = new FirefoxDriver(options);
					
				    

				    
	              //driver = new FirefoxDriver(profile);
					
					APP_LOGS.info("FireFox Browser opened");
					
					
				}
				
				
				
				
				
				else if(browserType.equalsIgnoreCase("IE"))
				{
					System.setProperty("webdriver.ie.driver", "C:\\Users\\ssrivastava4\\workspace\\PulseProject\\driver\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					
					APP_LOGS.info("IE Browser opened");
				}
				else
				{
					System.out.println("Browser input is invalid. Please go and select the browser properly");
					
					APP_LOGS.info("Browser input is invalid. Please go and select the browser properly");
				}
				
							
			
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

				APP_LOGS.info("Implicit wait given");
			      
				 

	         	String url = eLib.getCellValue(path,"PreCon", 1, 0);
				 
				driver.manage().window().maximize();
				driver.get(url);
				
				APP_LOGS.info("Url opened");
				
				
				
		
	/*   b.preInitialize();
	        
			
			b.insertsql(method.getName());
		
			b.getallmethodlist();

			b.executesqlandverifytext();
			
			
			b.getlastRunid();
			
			
			
		
		    System.out.println("Executing Test Case id  : "+b.getlastRunid());
		       
		       APP_LOGS.info("Executing Test Case id is : "+	b.getlastRunid());
		       
		       
		       */
		       
		       
		   

				    
		}

		
		
	/*	public static String getScreenshot(RemoteWebDriver driver, String screenshotName) throws IOException
		{
			
			
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// after execution, you could see a folder "FailedTestsScreenshots"
			// under src folder
			String destination = System.getProperty("user.dir")+"/screenshots/"+screenshotName+dateName+".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}
*/
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
			
			
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// after execution, you could see a folder "FailedTestsScreenshots"
			// under src folder
			String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName
					+ ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}

		

@AfterMethod

	
		
		public void tearDown(ITestResult result) throws IOException
	{
			
		
		
			if(result.getStatus()==ITestResult.FAILURE){
	
				 ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed"+ result.getThrowable());
	

				   	
					String imagePath=	getScreenshot(driver,result.getName());
		ExtentTestManager.getTest(getClass().getSimpleName()).log(LogStatus.FAIL,ExtentTestManager.getTest(getClass().getSimpleName()).addScreencast(imagePath));   
	
			
			
			
			}
			else if(result.getStatus()==ITestResult.SKIP){
		
				ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());

			}
			
			
			else if(result.getStatus()==ITestResult.SUCCESS){
				
				 //  ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");

				   
				   	
			       ExtentTestManager.getTest(getClass().getSimpleName()).log(LogStatus.PASS, "TEST CASE PASSED IS -> "+result.getName());

			   	
						String imagePath=	getScreenshot(driver,result.getName());
			ExtentTestManager.getTest(getClass().getSimpleName()).log(LogStatus.PASS,ExtentTestManager.getTest(getClass().getSimpleName()).addScreencast(imagePath));   
		
			
			
			}
	
			  ExtentManager.getExtentReport().endTest(ExtentTestManager.getTest());        
		        ExtentManager.getExtentReport().flush();
		        
		        driver.quit();
	
	}
	
	


	
	
@AfterSuite
public void testDown() {
   // extent.flush();
}

@AfterTest
public void afterClass() {
	// extent.flush();
	
//	 extent.close();

	//driver.quit();
}
			

				
		
		
		
	

		
		
			
}	





