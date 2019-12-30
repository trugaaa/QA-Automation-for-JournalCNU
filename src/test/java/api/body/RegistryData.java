package api.body;

import org.json.JSONArray;
import org.json.JSONObject;

public class RegistryData {
    public  JSONObject getJSONForRegistrySending()
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

}
