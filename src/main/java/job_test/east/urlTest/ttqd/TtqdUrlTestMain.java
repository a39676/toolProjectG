package job_test.east.urlTest.ttqd;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import ioHandle.FileUtilCustom;
import job_test.east.urlTest.commonAuxiliary.Auxiliary;
import job_test.east.urlTest.commonAuxiliary.UrlTestConstant;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;
import job_test.east.urlTest.ttjk.TtjkUrlTestConstant;
import net.sf.json.JSONObject;
import tool_package.http_tools.EastHttpTool;

public class TtqdUrlTestMain {
	
	private static TtqdAuxiliary eta = new TtqdAuxiliary();
	private static EastHttpTool eHttpTool = new EastHttpTool();
	
	private boolean sendLoginSms() {
		UrlTestDomain et = eta.getTestUrl(TtqdUrlTestConstant.sendLoginSms);
		String result = sendPost(et);
		return eta.isStatus0(result);
	}
	
	private boolean userLogin() throws UnsupportedEncodingException {
		if (!sendLoginSms()) {
			return false;
		}
		
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.login);
		insertKeyValue(ed, "mobile", UrlTestConstant.DEFAULTUSERMOBILE);
		insertKeyValue(ed, "vcode", UrlTestConstant.DEFAULTVCODE);
		String result = sendPost(ed);
		eta.saveIdToken(result);
		
		return eta.isStatus0(result);
	}

	private String getIndexAdTagList() {
		UrlTestDomain et = eta.getTestUrl(TtqdUrlTestConstant.getIndexAdTagList);
		insertUserId(et);
		return sendPost(et);
	}
	
	private String getDetailInfo(Long productId) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.getDetailInfo);
		insertKeyValue(ed, "productId", productId.toString());
		return sendPost(ed);
	}
	
	private String getApplyUrl(Long productId) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.getApplyUrl);
		insertUserId(ed);
		insertKeyValue(ed, "productId", productId.toString());
		return sendPost(ed);
	}
	
	/**
	 * 收藏/取消收藏产品
	 * @param productId
	 * @param type 1:收藏, 2:取消收藏
	 * @return
	 */
	private String collect(Long productId, Integer type) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.collect);
		insertUserId(ed);
		insertKeyValue(ed, "productId", productId.toString());
		insertKeyValue(ed, "type", type.toString());
		return sendPost(ed);
	}

	private String getStartupAd() {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.getStartupAd);
		return sendGet(ed);
	}
	
	private String uploadADPicture(byte[] pictureBase64) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.uploadPayoutPicture);
//		insertUserId(ed);
		ed.insertKeyValue("image", new String(pictureBase64))
		.insertKeyValue("phone", "13800138001")
		.insertKeyValue("platformName", "平台1");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	private void insertUserId(UrlTestDomain ed) {
		JSONObject json = JSONObject.fromObject(ed.getParamData());
		json.put("userId", eta.getId());
		json.put("token", eta.getToken());
		ed.setParamData(json.toString());
	}
	
	private void insertKeyValue(UrlTestDomain ed, String key, String value) {
		JSONObject json = JSONObject.fromObject(ed.getParamData());
		json.put(key, value);
		ed.setParamData(json.toString());
	}
	
	private String sendPost(UrlTestDomain ed) {
		return eHttpTool.sendPost(ed.getUrl(), ed.getParamData());
	}
	
	private String sendGet(UrlTestDomain ed) {
		return eHttpTool.sendGet(ed.getUrl());
	}
	
	
	public static void main(String[] args) throws Exception {
		TtqdAuxiliary.setProject(UrlTestConstant.ttqd);
		TtqdUrlTestMain em = new TtqdUrlTestMain();
//		em.userLogin();
////		
//		System.out.println(em.collect(2L, 2));
//		System.out.println(em.getApplyUrl(1L));
//		System.out.println(em.getStartupAd());
//		
//		EastUrlTestDomain ed = new EastUrlTestDomain();
//		ed.setUrl("http://localhost:8080/operation/task/getTaskList.action");
//		ed.setParamData("{\"pageNo\":\"1\", \"pageSize\":\"10\"}");
//		em.insertUserId(ed);
		
		UrlTestDomain ed2 = new UrlTestDomain();
//		ed2.setUrl("http://localhost:8080/system/getMenuList.action");
//		ed2.setParamData("{\"managerId\":-1,\"token\":\"593a0366413938b3f77991252799529e\",\"userId\":\"1\"}");
//		em.insertUserId(ed2);
		ed2.setUrl("http://localhost:8080/xkmall-admin/operate/test.action");
//		ed2.setParamData("{\"managerId\":-1,\"token\":\"593a0366413938b3f77991252799529e\",\"userId\":\"1\"}");
		
		System.out.println(em.sendGet(ed2));
		
		FileUtilCustom iou = new FileUtilCustom();
		byte[] fileByte = iou.getByteFromFile("D:\\auxiliary\\tmp\\icon.jpg");
		
		System.out.println(em.uploadADPicture(Base64.encodeBase64(fileByte)));
		
	}

}
