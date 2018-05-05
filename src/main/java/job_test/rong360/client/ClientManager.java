package job_test.rong360.client;

import job_test.rong360.client.impl.DefaultRongClient;

public class ClientManager {

	public static RongClient createClient() {
		/** 测试环境时，请在hosts文件中添加：  59.151.86.29 openapi.rong360.com **/
		/**联调时，请机构修改为测试环境地址**/
		// 测试环境
//		String url = "https://openapisandbox.rong360.com/gateway";
		// 正式环境
		String url = "https://openapi.rong360.com/gateway";

		/** 融360开放平台分配的商户id 值与biz_data中merchant_id一样(注意修改CrawlerRequest中的merchantId的值) */
		/**请机构替换**/
		// 测试环境
//		String appId = "1000130";
		// 正式环境
		String appId = "2010190";
		
		/** 暂时只支持json返回格式 */
		String format = "json";
//
		/** 配置创建的RSA私钥   java的必须是pkcs8格式*/
		/**请机构替换**/
		// pkcs8
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALoz4YWSGHMn1Qs1XDZCIYc04hV9l/YRmj5sLHKGmRc2GOpBdndj7LJGdQMFXjXVJ6qedJYcaO4Mw0/L7LBi66GIOqKacuuP/IKB3x8mp7JmuLQiYPt4Z9Zjl2dxRBkhH2j9FJYoAOz/AE4IgeqzwO/VIyNclOf7dN8UIiEMkgRxAgMBAAECgYAj92+BcHXekHwbXSRBqsZ1KmbdRdQ7iKzlj9mKCau6iw2G+jwa1adbzDY/7iKDfXfbAh58/GHcUYTMfhRrAcCHrxKRyqUR1PTMEl6h1gRzYc8A/19DS2jgcMi9DYJx6QNVHv3wva22JJGtCNqnzO8WQyj0Z83MX6ITUaBZCnTubQJBAPgayF4P1f3kAGlsxzqLPcBG+SEp0etbSVJlE/TCuPJA6+oGZvtEGm5XBmiFhNKIqnmaJREb7f1XwgjSo+0OYM8CQQDAIM5CehaPotTSzjDrcCEf+av96fViAvLBDNYe0s3nDZrbI2IVSk36nKs2GxU4LhMehMaLp88SOe6O3chqmha/AkEAits6fuJwCru7u0VFL6/UbLOMJRJpyUZ6/+FQTPFaX0MUhhL1OfWAsXaFQKRRlR8mAg7SZgPyq5KdJyD02+zrIwJAcu7tFJ1+g5yQHi+U2jMwzbUJK5Na5LAI0m3AZZ4L6M2dtrwa/8t8jJBOZ8aZ0grWCyq4r3DKkgev6y1HqGXXdwJAATVqOl+DtZ1TYk9IgCXoS9UJdEZNe22Vr+ScxPfzt2wHY4JvXvvtBrTjMMhHlsA0JbyJLOA2n0w/RyvDjWMaMQ==";
//		String privateKey = "MIICXAIBAAKBgQC6M+GFkhhzJ9ULNVw2QiGHNOIVfZf2EZo+bCxyhpkXNhjqQXZ3Y+yyRnUDBV411SeqnnSWHGjuDMNPy+ywYuuhiDqimnLrj/yCgd8fJqeyZri0ImD7eGfWY5dncUQZIR9o/RSWKADs/wBOCIHqs8Dv1SMjXJTn+3TfFCIhDJIEcQIDAQABAoGAI/dvgXB13pB8G10kQarGdSpm3UXUO4is5Y/ZigmruosNhvo8GtWnW8w2P+4ig3132wIefPxh3FGEzH4UawHAh68SkcqlEdT0zBJeodYEc2HPAP9fQ0to4HDIvQ2CcekDVR798L2ttiSRrQjap8zvFkMo9GfNzF+iE1GgWQp07m0CQQD4GsheD9X95ABpbMc6iz3ARvkhKdHrW0lSZRP0wrjyQOvqBmb7RBpuVwZohYTSiKp5miURG+39V8II0qPtDmDPAkEAwCDOQnoWj6LU0s4w63AhH/mr/en1YgLywQzWHtLN5w2a2yNiFUpN+pyrNhsVOC4THoTGi6fPEjnujt3IapoWvwJBAIrbOn7icAq7u7tFRS+v1GyzjCUSaclGev/hUEzxWl9DFIYS9Tn1gLF2hUCkUZUfJgIO0mYD8quSnScg9Nvs6yMCQHLu7RSdfoOckB4vlNozMM21CSuTWuSwCNJtwGWeC+jNnba8Gv/LfIyQTmfGmdIK1gsquK9wypIHr+stR6hl13cCQAE1ajpfg7WdU2JPSIAl6EvVCXRGTXttla/knMT387dsB2OCb1777Qa04zDIR5bANCW8iSzgNp9MP0crw41jGjE=";
		
		return new DefaultRongClient(url, appId, privateKey, format, "utf-8");
	}

}
