import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import org.json.simple.JSONObject;


public class VetAPIMethods {
    private static final String baseURL = "https://api-journal.herokuapp.com";
    private static String authToken;
    private static final String INVALID_AUTH_TOKEN = "Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5kMHduZXIiLCJyb2xlcyI6WyJBRE1JTiJdLCJpYXQiOjE1NzQ1Mzc0NDcsImV4cCI6MTU3NTUzNzQ0N30.XnSTmUnm_iNVDakDrGjJtH8wI0_dbRAw1yDxiD_Zn9_";

    public static Boolean isBodyHasKey(Response response, String toHasString)
    {
        JsonPath jsonPathValidator = response.jsonPath();
        ResponseBody body = response.getBody();
        return jsonPathValidator.get(toHasString) != null ? true : false;

    }
    public static  String takeKeyValue(Response response, String key)
    {
        JsonPath jsonPathValidator = response.jsonPath();
        return jsonPathValidator.get(key).toString();
    }
    public static String getJSONBodyForSuccessfulAuthorization(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","mind0wner");
        requestLoginBody.put("password","123");
        System.out.println(requestLoginBody.toJSONString());
        return requestLoginBody.toJSONString();
    }
    public static String getJSONBodyForInvalidAuthorization(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","mind0wner");
        requestLoginBody.put("password","321");
        System.out.println(requestLoginBody.toJSONString());
        return requestLoginBody.toJSONString();
    }

    public static Boolean setAuthToken(String token) {
        try {
            authToken = token;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }

    }

    public static String getAuthToken()
    {
        return authToken;
    }

    public static String targetURL(String endpoint) {
        return baseURL + endpoint;
    }

    public static String getInvalidAuthToken() {
        return INVALID_AUTH_TOKEN;
    }

    public static boolean isBodyNotHasKey(Response response, String notToHasString) {
        JsonPath jsonPathValidator = response.jsonPath();
        ResponseBody body = response.getBody();
        return jsonPathValidator.get(notToHasString) == null ? true : false;
    }
}