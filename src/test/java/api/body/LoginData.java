package api.body;

import org.json.JSONObject;

public class LoginData {
    public  JSONObject loginAdmin(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","mind0wner");
        requestLoginBody.put("password","123");
        return requestLoginBody;
    }

    public  JSONObject loginUser(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","testUsername");
        requestLoginBody.put("password","12345678Zz");
        return requestLoginBody;
    }
    public  JSONObject loginMonitor()
    {
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","u1");
        requestLoginBody.put("password","qwe");
        return requestLoginBody;
    }

    public  JSONObject loginInvalidPass(){
        JSONObject requestLoginBody = new JSONObject();
        requestLoginBody.put("username","mind0wner");
        requestLoginBody.put("password","321");
        return requestLoginBody;
    }
}
