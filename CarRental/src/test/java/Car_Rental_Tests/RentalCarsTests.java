package Car_Rental_Tests;
import static org.hamcrest.CoreMatchers.*;
import Car_Rental_Utilities.TeslaCar_Utilities;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import io.restassured.response.*;
import Car_Rental_SubMappings.StubMappings_Tesla;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RentalCarsTests  extends StubMappings_Tesla   {
	/*protected static ExtentReports report;
	protected static ExtentTest logger;
	@BeforeMethod
	public static void 	InitializeExtentReport() {
		//String filename= new SimpleDateFormat("'CarTest_'yyyyMMddHHmm'.html'").format(new Date());
		//String path =System.getProperty("user.dir") + "\\Redfin_SelenuimAssignment\\src\\main\\java\\reports\\" +  "REDFIN.html";
		String path=  "C:\\Users\\divya\\Documents\\RentalCar_Reports\\" + "CarTest.html";
		report= new ExtentReports(path);
		report.endTest(logger);
		report.flush();
		
		}*/
	 @Test(priority=1)
	public void  getRequest_ListOfCars() throws Exception{
		 logger= report.startTest("getRequest_ListOfCars");
		
		 String hostName= "http://localhost:8088";
		 String URI= "/getcars";
		 String URL= hostName+ URI;
		 RestAssured.baseURI=URL;
		 Response  response= 	RestAssured.given().contentType("application/json").get();
		 System.out.println(response.asString());
		 System.out.println(response.statusCode());
		 logger.log(LogStatus.PASS, "Response code is  : " +  response.statusCode() );
		 String responsebody = response.getBody().asString();
		 assertThat(responsebody, containsString("Tesla"));
		 logger.log(LogStatus.PASS, "The response body contains tesla car");
	 }
	 
	 @Test(priority=2)
	 public void  getRequest_TeslaBlueAndNotes() throws Exception{
		 logger= report.startTest("getRequest_TeslaBlueAndNotes");
		 String hostName= "http://localhost:8088";
		 String URI= "/getcars";
		 String URL= hostName+ URI;
		 RestAssured.baseURI=URL;
		 String make="Tesla";
		 String color="Blue";
		 
		 Response  response= 	RestAssured.given().contentType("application/json").get();
		 System.out.println(response.asString());
		 System.out.println(response.statusCode());
		 logger.log(LogStatus.PASS, "Response code is  : " +  response.statusCode() );
		 String responsebody = response.getBody().asString();
		 List<String > carmake= response.jsonPath().getList("Car.make");
		 System.out.println(carmake);
		 for(int i=0;i<carmake.size();i++) {
			 if(carmake.get(i).equalsIgnoreCase(make)) {
				// setIndex=i;
				 String carcolor= response.jsonPath().getString("Car["+i+"].metadata.Color");
				 if(carcolor.equalsIgnoreCase(color)) {
					 System.out.println("Car with blue and tesla" + carmake.get(i));
				 }
				 assertThat(carcolor, containsString("Blue"));
				 logger.log(LogStatus.PASS, "The response body contains tesla car with COLOR: BLUE");
				 String carnotes= response.jsonPath().getString("Car["+i+"].metadata.Notes");
				 System.out.println(make+ ":" + carcolor + ":" + carnotes);
				 logger.log(LogStatus.PASS , make+ ":" + carcolor + ":" + carnotes );
			 }
		 }
		 
		 
	 }
	 
	 

	@Test(priority=3 )
		public void  getRequest_CarWithLowestRent() throws Exception{
		 logger= report.startTest("getRequest_CarWithLowestRent");
			 String hostName= "http://localhost:8088";
			 String URI= "/getcars";
			 String URL= hostName+ URI;
			 RestAssured.baseURI=URL;
			 Response  response= 	RestAssured.given().contentType("application/json").get();
			 System.out.println(response.asString());
			 System.out.println(response.statusCode());
			 logger.log(LogStatus.PASS, "Response code is  : " +  response.statusCode() );
			 String responsebody = response.getBody().asString();
			 List<Float > perDayRentList= response.jsonPath().getList("Car.perdayrent");
			 ArrayList<PerDayRent> perDayRentArrList= new   ArrayList<PerDayRent>();
			 ArrayList<PerDayRentDiscount> perDayRentAfterDiscountArrList= new ArrayList<PerDayRentDiscount>();
			// ArrayList<>
			 System.out.println("Per day  rent of cars is" + perDayRentList);
			 for(int i=0;i<perDayRentList.size();i++) {
				 String vin_number= response.jsonPath().getString("Car["+i+"].vin");
				     Float perDayRent = response.jsonPath().getFloat("Car["+i+"].perdayrent.Price");
					 Float perDayRentDiscount = response.jsonPath().getFloat("Car["+i+"].perdayrent.Discount");
					 Float perDayRentAfterDiscount = (perDayRent-(perDayRent*perDayRentDiscount/100));
					 perDayRentArrList.add(new PerDayRent(vin_number, perDayRent));
					// perDayRentAfterDiscountArrList .add(new PerDayRentDiscount(vin_number,perDayRentAfterDiscount));
					 
				 }
			 Collections.sort(perDayRentArrList);
			 Collections.sort(perDayRentAfterDiscountArrList);
			 System.out.println("Cars with lowest rent are");
			 Iterator<PerDayRent> it=perDayRentArrList.iterator();
			 while(it.hasNext()) {
				 PerDayRent obj= it.next();
				 System.out.println(obj.vin + " : price= " + obj.price);
			 }
			System.out.println("Cars with lowest rent after  discount  in order are"); 
			 logger.log(LogStatus.PASS, "Cars with lowest rent after  discount  in order are");
			Iterator<PerDayRentDiscount> its=perDayRentAfterDiscountArrList.iterator();
			 while(its.hasNext()) {
				 PerDayRentDiscount obj= its.next();
				 System.out.println(obj.vin2 + " : Discount price= " + obj.perDayRentADiscount2);
				 logger.log(LogStatus.INFO, obj.vin2 + " : Discount price= " + obj.perDayRentADiscount2 );
			 }
			 
			 
			 }
	
	
	@Test(priority= 4)
	public void  getRequest_CarWithLowestRevenue() throws Exception{
		 logger= report.startTest("getRequest_CarWithLowestRevenue");
	String hostName= "http://localhost:8088";
	 String URI= "/getcars";
	 String URL= hostName+ URI;
	 RestAssured.baseURI=URL;
	 Response  response= 	RestAssured.given().contentType("application/json").get();
	// System.out.println(response.asString());
	 System.out.println(response.statusCode());
	 logger.log(LogStatus.PASS, "Response code is  : " +  response.statusCode() );
	 String responsebody = response.getBody().asString();
	 logger.log(LogStatus.PASS, "Response   : " +  response.statusCode() );
	 System.out.println(responsebody);
	 List<Float > carMetricsList= response.jsonPath().getList("Car.metrics");
	 ArrayList<CarRevenue> CarRevenueArrList= new   ArrayList<CarRevenue>();
	 
	
	 System.out.println("Per day  rent of cars is" );
	 for(int i=0;i<carMetricsList.size();i++) {
		 String vin_number= response.jsonPath().getString("Car["+i+"].vin");
		 Float depreciation  = response.jsonPath().getFloat("Car["+i+"].metrics.depreciation");
		 Float yoymaintenancecost = response.jsonPath().getFloat("Car["+i+"].metrics.yoymaintenancecost");
		 Float yeartodate = response.jsonPath().getFloat("Car["+i+"].metrics.rentalcount.yeartodate");
		Float perDayRent = response.jsonPath().getFloat("Car["+i+"].perdayrent.Price");
		Float perDayRentDiscount = response.jsonPath().getFloat("Car["+i+"].perdayrent.Discount");
	    Float perDayRentAfterDiscount = (perDayRent-(perDayRent*perDayRentDiscount/100));
			 Float carRevenue=( (yeartodate *perDayRentAfterDiscount)-(depreciation+yoymaintenancecost));
			 CarRevenueArrList.add(new CarRevenue(vin_number,carRevenue)); 
		 }
	 Collections.sort(CarRevenueArrList, Collections.reverseOrder());
	 System.out.println("Highest revenue car is");
	 logger.log(LogStatus.PASS, "Highest revenue car in the descending order" );
	 Iterator<CarRevenue> it= CarRevenueArrList.iterator();
	 while(it.hasNext()) {
		 CarRevenue cr= it.next();
		 System.out.println("The vin_number is " + cr.vin + " car revenue is :" + cr.car_revenue);
		 logger.log(LogStatus.INFO, "The vin_number is " + cr.vin + " car revenue is :" + cr.car_revenue );
	 }
			 
		 }
	}


	 
	 


