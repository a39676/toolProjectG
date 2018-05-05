package job_test.rong360.utils;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import job_test.rong360.client.ClientManager;
import job_test.rong360.client.RongClient;

/**
 * @author guocong
 *         Created by Administrator on 2016/3/31.
 */
public class RequestUtil {
    public static String request(String method, Map<String, Object> bizData) throws Exception {
        Map<String, String> params = new HashMap<String,String>();
        params.put("method", method);
        params.put("biz_data", JSONObject.fromObject(bizData).toString());

        RongClient client = ClientManager.createClient();
        return client.execute(params);
    }
}
