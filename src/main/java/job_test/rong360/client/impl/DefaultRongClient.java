package job_test.rong360.client.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import job_test.rong360.client.RongClient;
import job_test.rong360.utils.Base64Utils;
import job_test.rong360.utils.CommonUtil;
import job_test.rong360.utils.HttpTools;
import job_test.rong360.utils.RSAUtils;

public class DefaultRongClient implements RongClient {
		
    private String serverUrl;
    private String appId;
    private String version = "1.0.0";
    private String privateKey;
    private String format = "json";
    private String signType = "RSA";
    private String publicKey;
    private String charset;
    private int connectTimeout = 3000;
    private int readTimeout    = 15000;
    
    public DefaultRongClient(String serverUrl, String appId, String privateKey, String format,
        String charset) {
        this.serverUrl = serverUrl;
        this.appId = appId;
        this.privateKey = privateKey;
        this.publicKey = null;
        this.format = format;
        this.charset = charset;
    }
    
    /**
     * 重载方法
     * @throws Exception 
     */
    public String execute(Map<String,String> params) throws Exception {
        if(params==null){
            params = new HashMap<String,String>();
        }
        params.put("app_id", this.appId);
        params.put("version", this.version);
        params.put("sign_type", this.signType);
        params.put("format", this.format);
        params.put("timestamp", String.valueOf(new Date().getTime()));
        
        //sign处理 RSA加密
        String paramsStr = CommonUtil.getSortParams(params);
        //System.out.println("待签名数据："+paramsStr);
        byte[] bytes = RSAUtils.generateSHA1withRSASigature(paramsStr, this.privateKey);
        String sign = Base64Utils.encode(bytes);
        //System.out.println("签名后数据："+sign);
        params.put("sign", sign);
        String result = HttpTools.post(this.serverUrl, params, this.connectTimeout, this.readTimeout);
        return result;
    }

    
}