package Car_Rental_SubMappings;



	import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
	import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Car_Rental_Utilities.TeslaCar_Utilities;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.text.SimpleDateFormat;
import java.util.Date;


	public class TestBase2 {
		protected static ExtentReports report;
		protected static ExtentTest logger;
		public static int iport= 8088;
		public WireMockServer wireMockserver;
		//public static  TeslaCar_Utilities  utilities = new TeslaCar_Utilities();
		 @BeforeSuite
		 public void setUpWireMockServer() throws Exception{
			 wireMockserver = new WireMockServer(iport);
			 WireMock wireMock1= new WireMock("localhost", 9999);
			 wireMockserver.start();
			 //
		 }
		 
		 @AfterSuite
		 public void teardown() {
			 wireMockserver.stop();
		 }
		 @BeforeSuite
			public static void 	InitializeExtentReport() {
				String filename= new SimpleDateFormat("'RentalCarTestReport_'yyyyMMddHHmm'.html'").format(new Date());
				//String path = "C:\\Users\\divya\\eclipse-workspace\\CarRental\\TestReport\\" +  filename;
				String path=  "C:\\Users\\divya\\Documents\\RentalCar_Reports\\" + filename;
				System.out.println(System.getProperty("user.dir")+ "//CarRental//TestReport//"+ filename );
				//String path= System.getProperty("user.dir")+ "\\TestReport\\"+ filename ;
				report= new ExtentReports(path);
				
				}

			@AfterSuite
			public void done() {
			//public void report(Scenario scenario) throws InterruptedException, IOException {
				/*Thread.sleep(3000);
				 if (scenario.isFailed()) {
				      // Take a screenshot
					 
				      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				      String filename=new SimpleDateFormat("'failed_'yyyyMMddHHmmSS'.png'").format(new Date());
					File DestFile=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+filename);
					FileUtils.copyFile(screenshot, DestFile);
						
				    }*/
				
				report.endTest(logger);
				report.flush();
			
				

			}
		
		

	}

