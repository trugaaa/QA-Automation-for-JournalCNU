package api.body;

import org.json.JSONObject;

public class RegistrationData {

    public  JSONObject getJSONForSuccessRegistation()
    {
        JSONObject body = new JSONObject();
        body.put("email", "testvalid@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "12345678Zz");
        body.put("username", "testUsername");
        return body;
    }

    public  JSONObject getJSONWithInUniqueUsernameForRegistration()
    {
        JSONObject body = new JSONObject();
        body.put("email", "testvalid1@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "12345678Zz");
        body.put("username", "mind0wner");
        return body;
    }

    public  JSONObject getJSONWithInvalidPassForRegistration()
    {

        JSONObject body = new JSONObject();
        body.put("email", "testvalid2@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "12345678xx");
        body.put("username", "testUsername1");
        return body;
    }

    public  JSONObject getJSONWithInUniqueEmailForRegistration()
    {
        JSONObject body = new JSONObject();
        body.put("email", "testvalid@gmail.com");
        body.put("firstName", "Name");
        body.put("lastName", "Lastname");
        body.put("password", "XXxx1123456");
        body.put("username", "testUsername2");
        return body;
    }

}
