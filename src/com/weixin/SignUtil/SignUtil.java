/*
 * ΢������У�鹤����
 * */

package com.weixin.SignUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {

	//�뿪��ģʽ�ӿ�������Ϣ�е�Token����һ��
	private static String token = "xxx";
	
	/*
	 * signature:΢�ż���ǩ��
	 * timestamp:ʱ���
	 * nonce:�����
	 * */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		
		String[] paramArr = new String[] {token,timestamp,nonce};
		//��token,timestamp,nonce���ֵ�����
		Arrays.sort(paramArr);
		//�������Ľ��ƴ�ӳ�һ���ַ���
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		
		String ciphertext = null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			//��ƴ�Ӻ���ַ�������sha-1����
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		//��sha-1���ܺ���ַ�����signature���жԱ�
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;

	}

	/*
	 * ���ֽ�����ת����ʮ�������ַ���
	 * */
	private static String byteToStr(byte[] digest) {
		String strDigest = "";
		for(int i=0;i<digest.length;i++){
			strDigest += byteToHexStr(digest[i]);
		}
		
		return strDigest;
	}

	/*
	 * ���ֽ�ת��Ϊʮ�������ַ���
	 * */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		
		String s = new String(tempArr);
		return s;
	}
}
