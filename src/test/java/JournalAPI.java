import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class JournalAPI {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void loginSuccess() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(VetAPIMethods.getJSONBodyForSuccessfulAuthorization()).post(VetAPIMethods.targetURL("/login"));
        assertEquals(response.getStatusCode(),200);
        assertTrue(VetAPIMethods.isBodyHasKey(response,"data.token"));
        assertTrue(VetAPIMethods.setAuthToken(VetAPIMethods.takeKeyValue(response,"data.token")));
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void loginInvalid() {
        Response response = RestAssured.given().contentType(ContentType.JSON).body(VetAPIMethods.getJSONBodyForInvalidAuthorization()).post(VetAPIMethods.targetURL("/login"));
        assertEquals(response.getStatusCode(),403);
        assertTrue(VetAPIMethods.isBodyHasKey(response,"data.token"));
        assertFalse(VetAPIMethods.setAuthToken(VetAPIMethods.takeKeyValue(response,"data.token")));
    }

    @GET
    @Path("/schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(dependsOnMethods = "loginSuccess")
    public void getSchedule()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", VetAPIMethods.getAuthToken())
                .get(VetAPIMethods.targetURL("/schedule"));

        assertEquals(response.getStatusCode(),200);
        assertTrue(VetAPIMethods.isBodyHasKey(response,"data"));

    }

    @GET
    @Path("/schedule/time")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(dependsOnMethods = "loginSuccess")
    public void getTimeSchedule()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", VetAPIMethods.getAuthToken())
                .get(VetAPIMethods.targetURL("/schedule/time"));
        assertEquals(response.getStatusCode(),200);
        assertTrue(VetAPIMethods.isBodyHasKey(response,"data"));

    }

    @GET
    @Path("/schedule/time")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void getTimeScheduleInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", VetAPIMethods.getInvalidAuthToken())
                .get(VetAPIMethods.targetURL("/schedule/time"));
        assertEquals(response.getStatusCode(),403);
        assertTrue(VetAPIMethods.isBodyNotHasKey(response,"data"));
    }

    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test(dependsOnMethods = "loginSuccess")
    public void getAllAvailableUsers()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", VetAPIMethods.getAuthToken())
                .get(VetAPIMethods.targetURL("/users"));
        assertEquals(response.getStatusCode(),200);
        assertTrue(VetAPIMethods.isBodyHasKey(response,"data"));
    }

    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void getAllAvailableUsersWithInvalidToken()
    {
        Response response = RestAssured.given().contentType(ContentType.JSON).header("Authorization", VetAPIMethods.getInvalidAuthToken())
                .get(VetAPIMethods.targetURL("/users"));
        assertEquals(response.getStatusCode(),403);
        assertTrue(VetAPIMethods.isBodyNotHasKey(response,"data"));
    }
}
