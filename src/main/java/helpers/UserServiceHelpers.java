package helpers;

import constants.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Root;
import model.UserData;
import org.testng.Assert;
import utils.ApiCalls;
import utils.ConfigManager;

import java.util.List;


public class UserServiceHelpers {

	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
	private static final ApiCalls apicalls = new ApiCalls();

	public UserServiceHelpers() {
		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
	}

	public Root getUserData(String userId) {
		Response response = apicalls.doGetRequest(
				String.format(EndPoints.GET_USER, userId))
				.andReturn();
		Assert.assertEquals(response.statusCode(), 200);
		Root userData = response.as(Root.class);
		return userData;
	}

	public List<UserData> getAllUsers(){
		Response response = apicalls.doGetRequest(EndPoints.GET_LIST_USER)
				.then().extract().response();
		List<UserData> listOfUsers = response.jsonPath().getList("data");
		Assert.assertEquals(response.statusCode(), 200);
		return listOfUsers;
	}

	public Response createUser(String name, String job){
		Response response = apicalls.doPostRequest(name, job, EndPoints.POST_CREATE_USER);
		Assert.assertEquals(response.statusCode(), 201);
		System.out.println(response.body().asString());
		return response.andReturn();
	}

	public Response deleteUser(String userId){
		return apicalls.doDeleteRequest(
				String.format(EndPoints.DELETE_USER, userId));
	}
}