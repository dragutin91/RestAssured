package steps;

import com.google.gson.Gson;
import cucumber.api.DataTable;
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
import java.util.concurrent.TimeUnit;

public class GETUsersSteps extends Request {

    private Gson gson = new Gson();
    Map<String,Object> headerMap = new HashMap<String,Object>();
    Users request;
    Response response;

    @Given("^I create /api/users request$")
    public void iCreateApiUsersRequest() {
        headerMap = new HashMap<String,Object>();
        headerMap.put("Content", "application/json");

    }

    @When("^I send GET request to the \"([^\"]*)\"$")
    public void iSendGETRequestToThe(String path)  {
        response=getRequest(ConfigProperties.ENV+path,headerMap,200);
    }

    @Then("^I validate the JSON response$")
    public void iValidateTheResponse(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        String page=list.get(0).get("page");
        String total=list.get(0).get("total");
        String email=list.get(0).get("email");
        String firstName=list.get(0).get("first_name");
        String lastName=list.get(0).get("last_name");

        Users users=response.then().extract().as(Users.class);

        for (int i=0;i< list.size();i++) {
             page=list.get(i).get("page");
             total=list.get(i).get("total");
             email=list.get(i).get("email");
             firstName=list.get(i).get("first_name");
             lastName=list.get(i).get("last_name");

            Assert.assertEquals(page,users.page.toString());
            Assert.assertEquals(total,users.total.toString());
            Assert.assertEquals(email,users.data.get(i).email);
            Assert.assertEquals(firstName,users.data.get(i).firstName);
            Assert.assertEquals(lastName,users.data.get(i).lastName);
        }
    }

    @When("^I send GET request to the \"([^\"]*)\" with query parameter \"([^\"]*)\"$")
    public void iSendGETRequestToTheWithQueryParameter(String path, String queryParams) throws Throwable {
        response=getRequest(ConfigProperties.ENV+path,headerMap,200,queryParams);
    }

    @Then("^I validate that delay is less than \"([^\"]*)\"$")
    public void iValidateThatDelayIsLessThan(long delayTime){
        long responseTime=response.timeIn(TimeUnit.SECONDS);
        Assert.assertTrue(responseTime<=delayTime);
    }

}
