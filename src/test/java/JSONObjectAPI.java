import org.json.*;

public class JSONObjectAPI {

    public static JSONObject getJSONForSuccessfulAdminAuthorization(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","mind0wner");
        requestLoginBody.put("password","123");
        return requestLoginBody;
    }

    public static JSONObject getJSONForSuccessfulUserAuthorization(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","testUsername");
        requestLoginBody.put("password","12345678Zz");
        return requestLoginBody;
    }
    public static JSONObject getSuccessfulMonitorAuthorization()
    {
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","u1");
        requestLoginBody.put("password","qwe");
        return requestLoginBody;
    }

    public static JSONObject getJSONForInvalidAuthorization(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","mind0wner");
        requestLoginBody.put("password","321");
        return requestLoginBody;
    }

    public static JSONObject getJSONForSuccessRegistation()
    {
        JSONObject body = new JSONObject();
        body.put("email", "testvalid@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "12345678Zz");
        body.put("username", "testUsername");
        return body;
    }

    public static JSONObject getJSONWithInUniqueUsernameForRegistration()
    {
        JSONObject body = new JSONObject();
        body.put("email", "testvalid1@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "12345678Zz");
        body.put("username", "mind0wner");
        return body;
    }

    public static JSONObject getJSONWithInvalidPassForRegistration()
    {

        JSONObject body = new JSONObject();
        body.put("email", "testvalid2@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "12345678xx");
        body.put("username", "testUsername1");
        return body;
    }

    public static JSONObject getJSONWithInUniqueEmailForRegistration()
    {
        JSONObject body = new JSONObject();
        body.put("email", "testvalid@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "XXxx1123456");
        body.put("username", "testUsername2");
        return body;
    }

    public static JSONObject getJSONForRegistrySending()
    {
        JSONArray registryArr = new JSONArray();
        JSONObject registry = new JSONObject();
        registry.put("present", false);
        registry.put("userId", 4);
        registryArr.put(registry);
        JSONObject body = new JSONObject();
        body.put("subjectId", 1);
        body.put("registry",registryArr);
        return body;
    }

    public static JSONObject getJSONForEditingUser()
    {
        JSONObject body = new JSONObject();
        body.put("email", "somevalid@gmail.com");
        body.put("username", "TupaUsernameKakoita");
        body.put("role","ADMIN");
        return body;
    }
    public static JSONObject getJSONForEditingUserInUniqueUsername()
    {
        JSONObject body = new JSONObject();
        body.put("email", "somevalid@gmail.com");
        body.put("username", "mind0wner");
        body.put("role","ADMIN");
        return body;
    }

    public static JSONObject getJSONForEditingUserInUniqueEmail()
    {
        JSONObject body = new JSONObject();
        body.put("email", "email@gmail.com");
        body.put("username", "asdfghjkl");
        body.put("role","ADMIN");
        return body;
    }

    public static JSONObject getJSONForEditingUserIncorectRole()
    {
        JSONObject body = new JSONObject();
        body.put("email", "dadadadaadaswq@gmail.com");
        body.put("username", "asdfghjkl");
        body.put("role","VITALIK");
        return body;
    }
}
