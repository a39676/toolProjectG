package job_test.rong360.sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import job_test.rong360.utils.Base64Utils;
import job_test.rong360.utils.RsaEncrypt;
import net.sf.json.JSONObject;

public class ReceiveClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String fileName  =  System.getProperty("user.dir")  + "/demo/request.txt";
		StringBuilder result = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String s= null;
			while((s=br.readLine()) != null){
				result.append(s);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.toString());
		//这里就是http post过来的值
		String request = result.toString();
		JSONObject jsonRet = JSONObject.fromObject(request);
		String signNative = jsonRet.getString("sign");
		String bizData = jsonRet.getString("biz_data");

		RsaEncrypt RsaEncrypt = new RsaEncrypt();
		try {
			RsaEncrypt.loadPublicKey(RsaEncrypt.RONG_PUBLIC_KEY);
		} catch (Exception e) {
		}

		String encryptStr = bizData;
		try {
			// 签名验证
			Boolean isok = RsaEncrypt.doCheck(encryptStr, Base64Utils.decode(signNative),
					RsaEncrypt.getPublicKey());
			System.out.println("请求验证：" + isok);


		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}