package job_test.rong360.sample;

import java.util.Map;

import job_test.rong360.request.TianjiRequest;
import job_test.rong360.utils.RequestUtil;
import job_test.rong360.utils.Rong360Util;
import net.sf.json.JSONObject;
import tool_package.Tools;

public class TianjiSample {
	
	public static void main(String[] args) throws Exception {
//		testCollectUser();
//		testDetail();
		// 西瓜分 测试
		xgScoreTaskCreator();
	}

    /**
     * 用户采集信息接口
     * <link>https://tianji.rong360.com/doc/crawl/tianjireport_collectuser.html</link>
     * @param params 接口请求的业务参数，授权参数请到<pre>ClientManager</pre>中进行修改
     * @return 返回结果的JSON对象，基本格式为
     * {"error": 200, "msg": "success", "tianji_api_tianjireport_collectuser_response": {}}
     * @throws Exception 当请求失败，或者返回的结果不是合法的json字符串时，会抛出异常
     */
    private JSONObject taskCreatorDetail(Map<String, Object> params, String method) throws Exception {
        String result = RequestUtil.request(method, params);
        if (result == null || result.length() == 0) {
            throw new Exception("Request tianji collectUser interface returns null");
        }
        JSONObject jsonRet = JSONObject.fromObject(result);
        if (jsonRet == null) {
            throw new Exception("Request tianji collectUser interface got a non-json result");
        }
        return jsonRet;
    }

    /**
     * 用户获取生成的报告详情接口
     * <link>https://tianji.rong360.com/doc/crawl/tianjireport_collectuser.html</link>
     * @param params 接口请求的业务参数，授权参数请到<pre>ClientManager</pre>中进行修改
     * @return 返回结果的JSON对象，基本格式为
     * {"error": 200, "msg": "success", "tianji_api_tianjireport_detail_response": {}}
     * @throws Exception 当请求失败，或者返回的结果不是合法的json字符串时，会抛出异常
     */
    private JSONObject getReportDetail(Map<String, Object> params, String method) throws Exception {
        String result = RequestUtil.request(method, params);
        if (result == null || result.length() == 0) {
            throw new Exception("Request tianji collectUser interface returns null");
        }
        JSONObject jsonRet = JSONObject.fromObject(result);
        if (jsonRet == null) {
            throw new Exception("Request tianji collectUser interface got a non-json result");
        }
        return jsonRet;
    }

    /**
     * 收集用户信息测试
     * @throws Exception
     */
    public static void testCollectUser() throws Exception {
        TianjiSample sample = new TianjiSample();
        TianjiRequest collectRequest = new TianjiRequest();
        String transationId = String.valueOf(System.currentTimeMillis());
        System.out.println();
        collectRequest.putParam("userId", "9999")
            .putParam("outUniqueId", transationId)
            .putParam("type", "mobile") // 参见type字段列表
            .putParam("platform", "web") // 如果是H5接入则设置为web
            .putParam("name", "陈大文")
            .putParam("phone", "18902254712")
            .putParam("idNumber", "320202198502137029")
            .putParam("notifyUrl", "https://m.hzed.com/api/inlet/rong360")
            .putParam("returnUrl", "https://m.hzed.com/api/inlet/rong360")
            .putParam("emergencyName1", "联系人1")
            .putParam("emergencyRelation1", "friend")
            .putParam("emergencyPhone1", "13121366114")
            .putParam("emergencyName2", "联系人2")
            .putParam("emergencyRelation2", "father")
            .putParam("emergencyPhone2", "13121366115");
        JSONObject ret = sample.taskCreatorDetail(collectRequest.getParams(), Rong360Util.contactsCollector);
        System.out.println("return: " + ret.toString());
        System.out.println(((JSONObject)ret.get("tianji_api_tianjireport_collectuser_response")).get("redirectUrl"));
    }
    
    /**
     * 西瓜分测试样板
     */
    public static void xgScoreTaskCreator() throws Exception {
        TianjiSample sample = new TianjiSample();
        TianjiRequest collectRequest = new TianjiRequest();
        String transationId = String.valueOf(System.currentTimeMillis());
        System.out.println();
        collectRequest.putParam("userId", 9999)
        .putParam("outUniqueId", transationId)
        .putParam("type", "mobile") // 参见type字段列表
        .putParam("platform", "web") // 如果是H5接入则设置为web
        .putParam("name", "测试名")
        .putParam("phone", "18902254712")
        .putParam("idNumber", "440184198712081218")
        .putParam("notifyUrl", "")
        .putParam("returnUrl", "")
        .putParam("opReport", "1") // 运营商报告 0不返回 1返回
        .putParam("midScore", "1") // 生成西瓜分的中间分数 0不返回 1返回
        .putParam("umReport", "1") // 友盟报告 0不返回 1返回
        ;
        JSONObject ret = sample.taskCreatorDetail(collectRequest.getParams(), Rong360Util.xgScorePlus);
        System.out.println(collectRequest.getParams());
        System.out.println("return: " + ret.toString());
    }
    
    

    /**
     * 获取报告详情测试
     * @throws Exception
     */
    public static void testDetail() throws Exception {
        TianjiSample sample = new TianjiSample();
        TianjiRequest detailRequest = new TianjiRequest();
        detailRequest.putParam("outUniqueId", "1493016537765")
            .putParam("userId", "9999")
//            .putParam("test", "测试中英文混合English")
            .putParam("reportType", "html"); // 如果需要下载HTML文件，则设置此参数
        JSONObject ret = sample.getReportDetail(detailRequest.getParams(), Rong360Util.contactsReport);
        System.out.println(ret.toString());
    }    
}
