package com.treasure.hunt.framework.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

public class AES128Util {
	// 算法名
	public static final String KEY_NAME = "AES";
	// 加解密算法/模式/填充方式
	// ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个iv
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

	/**
	 * 微信 数据解密<br/>
	 * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充<br/>
	 * 对称解密的目标密文:encrypted=Base64_Decode(encryptData)<br/>
	 * 对称解密秘钥:key = Base64_Decode(session_key),aeskey是16字节<br/>
	 * 对称解密算法初始向量:iv = Base64_Decode(iv),同样是16字节<br/>
	 *
	 * @param encrypted 目标密文
	 * @param session_key 会话ID
	 * @param iv 加密算法的初始向量
	 */
	public static String wxDecrypt(String encrypted, String session_key, String iv) {
		String json = null;
		byte[] encrypted64 = Base64.decodeBase64(encrypted);
		byte[] key64 = Base64.decodeBase64(session_key);
		byte[] iv64 = Base64.decodeBase64(iv);
		try {
			init();
			AlgorithmParameters params = generateIV(iv64);
			String localChartSet = System.getProperty("file.encoding");
			System.out.println("localChartSet>>>>"+localChartSet);
			json = new String(decrypt(encrypted64, key64, params));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 初始化密钥
	 */
	public static void init() throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyGenerator.getInstance(KEY_NAME).init(128);
	}

	/**
	 * 生成iv
	 */
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		// iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
		// Arrays.fill(iv, (byte) 0x00);
		System.out.println(iv.length);
		AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_NAME);
		params.init(new IvParameterSpec(iv));
		return params;
	}

	/**
	 * 生成解密
	 */
	public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv)
			throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecretKeySpec key = new SecretKeySpec(keyBytes, KEY_NAME);
		// 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return cipher.doFinal(encryptedData);
	}

	public static void main(String[] args) {
		// 微信demo中的测试数据
		String encrypted = "X/tIL7XdAe2WbqzZ1YrlC/5/uhU1eQ87JnLaB01+gHEXobiPAwII2hNG9BNQFSX6YeNXL6nabZSDpQBxjhGEj3GkG1jm7uxlMGB1AzXqi6ZY0/LUC0FUumt5mjKtkyoAhe9g9UYbeQ6nh5c6OQUbRfY+ZMn9snZofsefC0fRRUpOLp/l61DXtkoMcsNr06A9PqfYTVifnT9PoHdenWuLpndJBINc+h/EEh700wXY7nWLA/CCfjGMBTDQnS4c1RDJTIv3er4OOOmlq2zAFUTTuBKMESYxE8Fo7b7HeLxduaRJnH+DFgu6naiKrQFbSjX4W6vLXoAcbtnDLtedZ1g1ijH0G1o83CR3vIpE24+VHvVHAiTzHDJUbad555NIT7+WPgDRpUw4Du9xOiOe12X63H/5/BbMWf5p2xL30IxQdF67fr3tYP02iuRrNAu9Ycd0Mj6muzSdvIsTUxD+ha0GhQdfs8CXB3tdI0JiLAz8lNR05C25n8kJbzaxWvo5EQBneaRiNCDfOBPaMvqxG5YffEiLFbb4TJg0c5kZtu9uvO0=";
		String session_key = "ALlo4Be/Xx6vXemiDeOU3Q==";
		String iv = "y4VkclrjzW4dOFf366+IRg==";
		String json = wxDecrypt(encrypted, session_key, iv);
		System.out.println(json);
	}
}