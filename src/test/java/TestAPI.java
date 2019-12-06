import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class TestAPI {

    @POST
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
    @Test (groups = "registration-controller", dependsOnMethods = "registrationSuccess")
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

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller", dependsOnMethods = "registrationSuccess")
    public void loginUserSuccess() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONForSuccessfulUserAuthorization().toString()).post(APIMethods.targetURL("/login"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForSuccessfulUserAuthorization());
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data.token"));
        assertTrue(APIMethods.setAuthUserToken(APIMethods.takeKeyValue(response,"data.token")));
    }
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginMonitorSuccess() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getSuccessfulMonitorAuthorization().toString()).post(APIMethods.targetURL("/login"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getSuccessfulMonitorAuthorization());
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data.token"));
        assertTrue(APIMethods.setAuthMonitorToken(APIMethods.takeKeyValue(response,"data.token")));
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginAdminSuccess() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONForSuccessfulAdminAuthorization().toString()).post(APIMethods.targetURL("/login"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForSuccessfulAdminAuthorization());
        assertEquals(response.getStatusCode(),200);
        assertTrue(APIMethods.isBodyHasKey(response,"data.token"));
        assertTrue(APIMethods.setAuthAdminToken(APIMethods.takeKeyValue(response,"data.token")));
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test (groups = "login-controller")
    public void loginInvalid() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(JSONObjectAPI.getJSONForInvalidAuthorization().toString()).post(APIMethods.targetURL("/login"));
        APIMethods.requestResponseWrite(response,JSONObjectAPI.getJSONForInvalidAuthorization());
        assertEquals(response.getStatusCode(),401);
    }

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
    @Test (groups = "registry-controller", dependsOnMethods = "loginAdminSuccess")
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
    @Test (groups = "registry-controller")
    public void getSubjectsWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", APIMethods.getInvalidAuthToken())
                .get(APIMethods.targetURL("/subjects"));
        APIMethods.responseWrite(response);
        assertEquals(response.getStatusCode(),401);
    }

    //TODO POST /users/id

    //TODO DELETE/users/id
}
