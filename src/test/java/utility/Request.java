package utility;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request {


    public Response postRequest(String URI, String body, Map headerMap)
    {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification httpRequest = given();
        Response response;

        response = httpRequest
                .headers(headerMap)
                .body(body)
                .when()
                .post(URI);

        return response;
    }


    public Response getRequest(String URI,Map headerMap,int statusCode)
    {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification httpRequest = given();
        Response response;

        response = httpRequest
                .headers(headerMap)
                .when()
                .get(URI);
        Assert.assertEquals(statusCode,response.statusCode());

        return response;
    }

    public Response getRequest(String URI,Map headerMap,int statusCode,String queryParams)
    {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification httpRequest = given();
        Response response;

        response = httpRequest
                .headers(headerMap)
                .param(queryParams)
                .when()
                .get(URI);


        Assert.assertEquals(statusCode,response.statusCode());

        return response;
    }

    public Response deleteRequest(String URI,Map headerMap)
    {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification httpRequest = given();
        httpRequest.delete(URI);
        Response response;

        response = httpRequest
                .headers(headerMap)
                 .when()
                .delete(URI);

        return response;
    }
}
