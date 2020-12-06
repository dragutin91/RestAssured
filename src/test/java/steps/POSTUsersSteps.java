package steps;

import com.google.gson.Gson;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import models.users.Users;
import org.junit.Assert;
import utility.ConfigProperties;
import utility.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POSTUsersSteps extends Request {

    private Gson gson = new Gson();
    Map<String,Object> headerMap = new HashMap<String,Object>();
    Users request;
    Response response;

    @Given("^I create /api/register request without body$")
    public void iCreateApiRegisterRequestWithoutBody() {

    }

    @When("^I send POST request to the \"([^\"]*)\"$")
    public void iSendPOSTRequestToThe(String path) {
        response=postRequest(ConfigProperties.ENV+path,"",headerMap);
    }

    @Then("^I get status \"([^\"]*)\"$")
    public void iGetStatus(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());
    }

    @And("^I get the error \"([^\"]*)\"$")
    public void iGetTheError(String errorMsg) {
       String error=response.then().extract().path("error");

       Assert.assertEquals(errorMsg,error);
    }

    @When("^I send POST request to the \"([^\"]*)\" with JSON Body$")
    public void iSendPOSTRequestToTheWithJSONBody(String path,String body){
        body=gson.toJson(body);
        response=postRequest(ConfigProperties.ENV+path,body,headerMap);
    }

    @When("^I send DELETE request to the \"([^\"]*)\"$")
    public void iSendDELETERequestToThe(String path) {
        response = deleteRequest(ConfigProperties.ENV + path, headerMap);
    }

}
