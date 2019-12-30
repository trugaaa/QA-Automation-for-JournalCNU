package api;

import static org.testng.Assert.*;

import api.body.LoginData;
import api.body.RegistrationData;
import api.body.RegistryData;
import api.body.UserData;
import api.data.ApiData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class TestAPI {
ApiData apiData;
LoginData loginData;
RegistrationData registrationData;
RegistryData registryData;
UserData userData;

@BeforeClass
public void setUp()
{
    apiData = new ApiData();
    loginData = new LoginData();
    registrationData = new RegistrationData();
            registryData = new RegistryData();
            userData = new UserData();
}
 /*   @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registration-controller")
    public void registrationSuccess () {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONForSuccessRegistation().toString())
                .post(APIMethods.targetURL("/registration"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForSuccessRegistation());
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.bodyKeyEqualsValue(response,"data", "true"));
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registration-controller")
    public void registrationWithInvalidPass () {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONWithInvalidPassForRegistration().toString())
                .post(APIMethods.targetURL("/registration"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONWithInvalidPassForRegistration());
        assertEquals(response.getStatusCode(),400);
      
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registration-controller")
    public void registrationWithInUniqueEmail() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONWithInUniqueEmailForRegistration().toString())
                .post(APIMethods.targetURL("/registration"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONWithInUniqueEmailForRegistration());
        assertEquals(response.getStatusCode(),400);
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registration-controller")
    public void registrationWithInUniqueUsername() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONWithInUniqueUsernameForRegistration().toString())
                .post(APIMethods.targetURL("/registration"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONWithInUniqueUsernameForRegistration());
        assertEquals(response.getStatusCode(),400);
    }
*/
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginUserSuccess() {
        Assert.assertEquals(new APIMethods().post(apiData.DataHeaders("/login"),
                loginData.loginUser(),200,true).getStatus(),200);
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginMonitorSuccess() {
        Assert.assertEquals(new APIMethods().post(apiData.DataHeaders("/login"),
                loginData.loginMonitor(),200,true).getStatus(),200);
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginAdminSuccess() {
        Assert.assertEquals(new APIMethods().post(apiData.DataHeaders("/login"),
                loginData.loginAdmin(),200,true).getStatus(),200);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginInvalid() {
        Assert.assertEquals(new APIMethods().post(apiData.DataHeaders("/login"),
                loginData.loginInvalidPass(),401,true).getStatus(),401);
    }
 /*
    @GET
    @Path("/schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(groups = "schedule-controller", dependsOnMethods = "loginAdminSuccess")
    public void getSchedule()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getAuthAdminToken())
                .get(APIMethods.targetURL("/schedule"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));
    }

    @GET
    @Path("/schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(groups = "schedule-controller", dependsOnMethods = "loginAdminSuccess")
    public void getScheduleWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/schedule"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));

    }
    @GET
    @Path("/schedule/time")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(groups = "schedule-controller", dependsOnMethods = "loginAdminSuccess")
    public void getTimeSchedule()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getAuthAdminToken())
                .get(APIMethods.targetURL("/schedule/time"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));

    }

    @GET
    @Path("/schedule/time")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(groups = "schedule-controller")
    public void getTimeScheduleInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/schedule/time"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
        assertTrue(APIMethods.isBodyNotHasKey(response,"data"));
    }

    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller", dependsOnMethods = "loginAdminSuccess")
    public void getAllAvailableUsers()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getAuthAdminToken())
                .get(APIMethods.targetURL("/users"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));
    }

    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller")
    public void getAllAvailableUsersWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/users"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
        assertTrue(APIMethods.isBodyNotHasKey(response,"data"));
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller", dependsOnMethods = "loginAdminSuccess")
    public void getCurrentUser()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getAuthAdminToken())
                .get(APIMethods.targetURL("/users/current"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller")
    public void getCurrentUserWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/users/current"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));
    }

@GET
@Path("/registry")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Test (groups = "registry-controller", dependsOnMethods = "loginMonitorSuccess")
public void getRegistrySuccess()
{
    Response response = RestAssured.given().contentType(ContentType.JSON)
            .header("Authorization", APIMethods.getAuthMonitorToken())
            .get(APIMethods.targetURL("/registry"));
    APIMethods.responseWrite(response);
    assertEquals(response.getStatusCode(),200);
    assertTrue(APIMethods.isBodyHasKey(response,"data"));
}

    @POST
    @Path("/registry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registry-controller", dependsOnMethods = "loginAdminSuccess")
    public void sendRegistryWithInvalidRole()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .body(JSONObjectAPI.getJSONForRegistrySending().toString())
                .post(APIMethods.targetURL("/registry"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForRegistrySending());
        assertEquals(response.getStatusCode(),403);
    }

    @POST
    @Path("/registry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registry-controller")
    public void sendRegistrySuccess()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .body(JSONObjectAPI.getJSONForRegistrySending().toString())
                .post(APIMethods.targetURL("/registry"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForRegistrySending());
        assertEquals(response.getStatusCode(),200);
    }

    @POST
    @Path("/registry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registry-controller")
    public void sendRegistryInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .body(JSONObjectAPI.getJSONForRegistrySending().toString())
                .post(APIMethods.targetURL("/registry"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForRegistrySending());
        assertEquals(response.getStatusCode(),401);
    }

    @GET
    @Path("/registry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registry-controller", dependsOnMethods = "loginAdminSuccess")
    public void getRegistryTodaySuccess()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .get(APIMethods.targetURL("/registry"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data"));
    }

    @GET
    @Path("/registry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "registry-controller")
    public void getRegistryTodayInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/registry"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
    }

    @GET
    @Path("/subjects")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "subject-controller", dependsOnMethods = "loginAdminSuccess")
    public void getSubjectsSuccess()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .get(APIMethods.targetURL("/subjects"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
    }

    @GET
    @Path("/subjects")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(groups = "subject-controller")
    public void getSubjectsWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/subjects"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess")
    public void updateUserSuccess()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .body(JSONObjectAPI.getJSONForEditingUser().toString())
                .post(APIMethods.targetURL("/users/4"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForEditingUser());
        assertEquals(response.getStatusCode(),200);
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess")
    public void updateUserWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .body(JSONObjectAPI.getJSONForEditingUser().toString())
                .post(APIMethods.targetURL("/users/4"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForEditingUser());
        assertEquals(response.getStatusCode(),200);
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess")
    public void updateUserWithInUniqueEmail()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .body(JSONObjectAPI.getJSONForEditingUserInUniqueEmail().toString())
                .post(APIMethods.targetURL("/users/4"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForEditingUserInUniqueEmail());
        assertEquals(response.getStatusCode(),400);
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess")
    public void updateUserWithInUniqueUsername()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .body(JSONObjectAPI.getJSONForEditingUserInUniqueUsername().toString())
                .post(APIMethods.targetURL("/users/4"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForEditingUserInUniqueUsername());
        assertEquals(response.getStatusCode(),400);
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess")
    public void updateUserWithIncorrectRole()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .body(JSONObjectAPI.getJSONForEditingUserIncorectRole().toString())
                .post(APIMethods.targetURL("/users/4"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForEditingUserIncorectRole());
        assertEquals(response.getStatusCode(),400);
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess")
    public void updatingUserNotExists()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .body(JSONObjectAPI.getJSONForEditingUserIncorectRole().toString())
                .post(APIMethods.targetURL("/users/400000000000"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForEditingUser());
        assertEquals(response.getStatusCode(),404);
    }

    @DELETE
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller")
    public void deleteUserInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .delete(APIMethods.targetURL("/users/4"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),403);
    }

    @DELETE
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "user-controller",dependsOnMethods = "loginAdminSuccess",priority=4)
    public void deleteUserSuccess()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getAuthAdminToken())
                .delete(APIMethods.targetURL("/users/4"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),200);
    }
*/
}
