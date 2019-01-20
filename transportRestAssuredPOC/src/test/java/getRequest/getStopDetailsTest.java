package getRequest;

import commonUtils.Base;
import commonUtils.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class getStopDetailsTest extends Base {

    ReadProperties readProperties = new ReadProperties();
    String expectedStopName;
    String expectedClass;
    String path;
    String stop;

    @BeforeClass
    public void setReadProperties(){
        //Setting all the property values
         expectedStopName = readProperties.getPropertyValue("expectedStopName");
         expectedClass = readProperties.getPropertyValue("expectedClass");
         path = readProperties.getPropertyValue("stopExtensionEndpoint");
         stop = readProperties.getPropertyValue("path");
    }

    @Test
    public void testResponse()
    {
        //Read the path and replace the stop which we want to run api
        path = path.replace("STOPVAL", stop);
        RequestSpecification request = RestAssured.given();
        //Get method on the path with the stop specified
        Response response = request.get(path);
        //Verify the response
        int code= response.getStatusCode();
        Assert.assertEquals(code,200);

        //Verify the stop name
        String actualStopName = response.jsonPath().get("locations.name").toString().replace("[","").replace("]","");
        Assert.assertEquals(actualStopName, expectedStopName);
        //Verify their is more than one node
        String actualClass = response.jsonPath().get("locations.modes").getClass().toString();
        Assert.assertTrue(actualClass.contains(expectedClass));

    }

}
