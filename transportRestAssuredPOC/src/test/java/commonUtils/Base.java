package commonUtils;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Base {

    ReadProperties readProperties = new ReadProperties();

    @BeforeSuite(alwaysRun = true)
    public void setBaseUrl(){
        RestAssured.baseURI = readProperties.getPropertyValue("endpoint");
    }

    //Can add something like this to add headers and send the headers to each request being made
    //This is not being used now
    //ENHANCEMENT
    public static RequestSpecification requestHeaders(Map<String, String> headers) {

        RequestSpecification request = RestAssured.given();
        List<Header> list = new ArrayList<Header>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            Header header = new Header(entry.getKey(), entry.getValue());
            list.add(header);
        }
        Headers header = new Headers(list);
        return request.headers(header);
    }

    //Add something like this to enhance it to call type of requests made
    //This is not being used now
    //ENHANCEMENT
    public static Response response(Method method, String url, RequestSpecification rs) {

        switch (method) {
            case POST:
                return rs.post(url);
            case GET:
                return rs.get(url);
            case PUT:
                return rs.put(url);
            case PATCH:
                return rs.patch(url);
            case DELETE:
                return rs.delete(url);
            default:
                return rs.head(url);
        }
    }
}
