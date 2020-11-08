package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.UserData;

import static io.restassured.RestAssured.given;

public class ApiCalls {

    public Response doGetRequest(String endpoint){
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(endpoint);
    }

    public Response doPostRequest(String name, String job, String endpoint){
        UserData user =  new UserData();
        user.setName(name);
        user.setJob(job);
        RequestSpecification req = RestAssured.given();
        req.body(user);
       return req.post(endpoint);
    }

    public Response doDeleteRequest(String endpoint){
       return given().log().all().delete(endpoint);
    }

}
