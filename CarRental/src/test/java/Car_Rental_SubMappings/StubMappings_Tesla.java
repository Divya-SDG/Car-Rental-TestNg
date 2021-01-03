package Car_Rental_SubMappings;

import org.testng.annotations.BeforeTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;


public class StubMappings_Tesla  extends TestBase2{
@BeforeTest
public void  getmappingsRequestAndResponseForRentalCars() {
	wireMockserver.stubFor(
			       get(urlEqualTo("/getcars"))
			       .withHeader("Content-Type", equalTo("application/json; charset=UTF-8"))
			       .willReturn(
			    		   aResponse()
			    		   .withStatus(200)
			    		   .withHeader("Content-Type", "application/json; charset=UTF-8")
			    		    .withBodyFile("RentalCarsSchema.json")
			    	  ));
	           
     }

}
