package tests;

import helpers.UserServiceHelpers;
import io.restassured.response.Response;
import model.Root;
import model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class UserTest {

    private UserServiceHelpers userServiceHelpers;
    private  Response response;
    @BeforeClass
    public void init(){
        userServiceHelpers = new UserServiceHelpers();
        }

    @Test
    public void createUser(){
        response = userServiceHelpers.createUser("adam", "noga");
        Assert.assertNotNull(response.body(), "User has not been created");
    }

    @Test
    public void getUser() {
        Root userData = userServiceHelpers.getUserData("2");
        Assert.assertNotNull(userData, "UserData list is not empty");
        Assert.assertEquals(userData.getData().email, "janet.weaver@reqres.in");
    }

    @Test
    public void getAllUsers(){
        List<UserData> userData = userServiceHelpers.getAllUsers();
        Assert.assertEquals(userData.size(),6);
    }

    @Test
    public void deleteUser(){
        response = userServiceHelpers.deleteUser("2");
        Assert.assertEquals(response.statusCode(), 204);
    }

}
