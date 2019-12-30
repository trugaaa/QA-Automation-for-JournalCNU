package api.data;

import api.APIMethods;
import api.body.LoginData;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;


public class ApiData {

    public JSONObjectAPI DataHeaders(String endpoint) {
        JSONObjectAPI apiHeaders = new JSONObjectAPI();
        apiHeaders.putAccept(MediaType.APPLICATION_JSON);
        apiHeaders.putRequest(MediaType.APPLICATION_JSON);
        apiHeaders.putEndpoint(endpoint);
        return apiHeaders;
    }

    public JSONObjectAPI DataHeadersToken(String endpoint, String tokenType) {
        HashMap<String,Object> headersMap = new HashMap<>();
        JSONObjectAPI apiHeaders = new ApiData().DataHeaders(endpoint);
        headersMap.put("Authorization", new ApiData().settingToken(tokenType));
        apiHeaders.putHeaders(headersMap);
        return apiHeaders;
    }
    
    public String settingToken(String tokenType)
    {
        if (tokenType == "Admin") {
            return getValidAdminTokenHeader();
        } else if (tokenType == "User") {
           return getValidUserTokenHeader();
        } else if (tokenType == "Invalid") {
            return  getInvalidTokenHeader();
        } else if (tokenType=="Monitor")
            return getValidMonitorTokenHeader();
        else {
            return getExpiredAdminTokenHeader();
        }
    }

    public String getValidAdminTokenHeader()
    {
        JSONObjectAPI headers=DataHeaders("/accounts/jsonLogin");
        JSONObject data=new LoginData().loginAdmin();
        HashMap<String, Object> myHeaders = new HashMap<>();
        String ret = "";
        try {
            JSONObject object = new JSONObject(new APIMethods().post(headers, data, 200, false).readEntity(String.class)).getJSONObject("data");
            ret = object.getString("data.token");
        }catch(Exception ignored){
        }
        return ret;
    }

    public String getValidUserTokenHeader()
    {
        JSONObjectAPI headers=DataHeaders("/accounts/jsonLogin");
        JSONObject data=new LoginData().loginUser();
        String ret = "";
        try {
            JSONObject object = new JSONObject(new APIMethods().post(headers, data, 200, false).readEntity(String.class)).getJSONObject("data");
            ret = object.getString("data.token");
        }catch(Exception ignored){
        }
        return ret;
    }

    public String getInvalidTokenHeader()
    {
        HashMap<String,Object> myHeaders=new HashMap<>();
        String invalidToken = "";
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/test/java/api/resources/configAPI.properties");
            property.load(fis);
            invalidToken = property.getProperty("INVALID_AUTH_TOKEN");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return invalidToken;
    }
    public String getExpiredAdminTokenHeader()
    {
        JSONObjectAPI headers=DataHeaders("/accounts/jsonLogin");
        JSONObject data=new LoginData().loginAdmin();
        String ret = "";
        try {
            JSONObject object = new JSONObject(new APIMethods().post(headers, data,
                    200, false).readEntity(String.class)).getJSONObject("data");
            new JSONObject(new APIMethods().post(headers, data,200, false));
            ret = object.getString("data.token");
        }catch(Exception ignored){
        }
        return ret;
    }

    public String getValidMonitorTokenHeader()
    {
        JSONObjectAPI headers=DataHeaders("/accounts/jsonLogin");
        JSONObject data=new LoginData().loginMonitor();
        HashMap<String, Object> myHeaders = new HashMap<>();
        String ret = "";
        try {
            JSONObject object = new JSONObject(new APIMethods().post(headers, data, 200, false).readEntity(String.class)).getJSONObject("data");
            ret = object.getString("data.token");
        }catch(Exception ignored){
        }
        return ret;
    }
}
