import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;

public class APIMethods {
    private static final String baseURL = "https://api-journal.herokuapp.com";
    private static String authAdminToken;
    private static final String INVALID_AUTH_TOKEN = "Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5kMHduZXIiLCJyb2xlcyI6WyJBRE1JTiJdLCJpYXQiOjE1NzQ1Mzc0NDcsImV4cCI6MTU3NTUzNzQ0N30.XnSTmUnm_iNVDakDrGjJtH8wI0_dbRAw1yDxiD_Zn9_";
    private static String authUserToken;

    private static final String DATE_OF_REGISTRY = "?date=2019-12-06";

    public static Boolean isBodyHasKey(Response response, String toHasString)
    {
        JsonPath jsonPathValidator = response.jsonPath();
        return jsonPathValidator.get(toHasString) != null;

    }

    public static  String takeKeyValue(Response response, String key)
    {
        JsonPath jsonPathValidator = response.jsonPath();
        return jsonPathValidator.get(key).toString();
    }

    public static Boolean setAuthAdminToken(String token) {
        try {
            authAdminToken = token;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public static String getAuthAdminToken()
    {
        return authAdminToken;
    }

    public static String targetURL(String endpoint) {
        return baseURL + endpoint;
    }

    public static String getInvalidAuthToken() {
        return INVALID_AUTH_TOKEN;
    }

    public static boolean isBodyNotHasKey(Response response, String notToHasString) {
        JsonPath jsonPathValidator = response.jsonPath();
        return jsonPathValidator.get(notToHasString) == null;
    }

    public static String getAuthUserToken() {
        return authUserToken;
    }

    public static Boolean setAuthUserToken(String authUserToken) {
       try {
           APIMethods.authUserToken = authUserToken;
           return true;
       }
        catch (Exception ex)
        {
            return false;
        }
    }

    public static String getDateOfRegistry() {
        return DATE_OF_REGISTRY;
    }

    public static void requestResponseWrite(Response response, JSONObject input) {
        System.out.println("Input: " + input.toString());
        System.out.println("Output:" + response.body().asString());
    }
    public static void responseWrite(Response response) {
        System.out.println("Output:" + response.body().asString());
    }
}