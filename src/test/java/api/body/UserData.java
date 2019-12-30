package api.body;

import org.json.JSONObject;

public class UserData {
    public  JSONObject getJSONForEditingUser()
    {
        JSONObject body = new JSONObject();
        body.put("email", "somevalid@gmail.com");
        body.put("username", "TupaUsernameKakoita");
        body.put("role","ADMIN");
        return body;
    }
    public  JSONObject getJSONForEditingUserInUniqueUsername()
    {
        JSONObject body = new JSONObject();
        body.put("email", "somevalid@gmail.com");
        body.put("username", "mind0wner");
        body.put("role","ADMIN");
        return body;
    }

    public  JSONObject getJSONForEditingUserInUniqueEmail()
    {
        JSONObject body = new JSONObject();
        body.put("email", "email@gmail.com");
        body.put("username", "asdfghjkl");
        body.put("role","ADMIN");
        return body;
    }

    public  JSONObject getJSONForEditingUserIncorectRole()
    {
        JSONObject body = new JSONObject();
        body.put("email", "dadadadaadaswq@gmail.com");
        body.put("username", "asdfghjkl");
        body.put("role","VITALIK");
        return body;
    }
}
