package Car_Rental_Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class TeslaCar_Utilities {
	protected static ExtentReports report;
	protected static ExtentTest logger;
	@BeforeMethod
	public static void 	InitializeExtentReport() {
		String filename= new SimpleDateFormat("'CarTest_'yyyyMMddHHmm'.html'").format(new Date());
		//String path =System.getProperty("user.dir") + "\\Redfin_SelenuimAssignment\\src\\main\\java\\reports\\" +  "REDFIN.html";
		String path=  "C:\\Users\\divya\\Documents\\RentalCar_Reports\\" + "CarTest.html";
		report= new ExtentReports(path);
		
		}

	@AfterMethod
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
