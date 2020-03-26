package com.zghd.entity.BaiDu;


import org.apache.commons.codec.binary.Base64;


/**
 * 加密/解密工具
 */
public class EncryptUtil {
 
	
	/**
	 * 初始化
	 * @param deSkey	密钥
	 * @throws Exception
	 */
	public EncryptUtil(String deSkey, String charset) {
	}
	
	/**
	 * 加密
	 * @author ershuai
	 * @date 2017年4月19日 上午9:40:53
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String encode(String data) throws Exception {
		Base64 ba = new Base64();
		return ba.encodeToString(data.getBytes());
	}
	
	/**
	 * 解密
	 * @author ershuai
	 * @date 2017年4月19日 上午9:41:01
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String decode(String data) throws Exception {
		Base64 ba = new Base64();
		return new String(ba.decode(data.getBytes()), "UTF-8");
	}
	
	public static void main(String[] args) {
		try {
			EncryptUtil des = new EncryptUtil("", "utf-8");
			System.out.println("加密后的字符：" + des.encode("aaaaaa"));
			System.out.println("解密后的字符：" + des.decode("NTg3ODU1NSZ0aW1lPTIwMTgtMDktMTAgMTg6MjE6MzI="));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}