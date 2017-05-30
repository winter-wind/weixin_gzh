/*
 * ��ȡaccess_token����
 * 1.ֱ������ַ����
 * 2.�ó����ȡ������
 * */

package com.isooou.getaccesstoken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


import com.weixin.x509.MyX509TrustManager;

import net.sf.json.JSONObject;


public class TokenDemo {

	public String TokenStr() throws Exception{
		//��ȡ�ĵ�ַ
		//String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_"
				//+ "credential&appid=appid&secret=secret";
		//TEST
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_"
				+ "credential&appid=appid&secret=secret";
		
		URL url = new URL(tokenUrl);
		//�������󣬴�����
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		
		//ʹ���Զ�������ι�����
		TrustManager[] tm = {new MyX509TrustManager()};//����֤������
		
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
													//������Э��ı�׼���� ���ṩ������
		//��ʼ��������           ��֤��Կ��null��ͬλ����֤���ξ���Դ�� null���ṩǿ���������������
		sslContext.init(null, tm, new java.security.SecureRandom());
		
		//���ش������ĵ�SocketFactory
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		//Ϊ��ȫHTTPS URL���Ӵ����׽���ʱʹ��SocketFactory
		conn.setSSLSocketFactory(ssf);
		
		//ʹ��URL���ӽ��������������
		conn.setDoInput(true);
		
		//����ʽ
		conn.setRequestMethod("GET");
		
		//��ȡ������
		InputStream inputStream = conn.getInputStream();
		
		//�ֽ������ַ���
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//��ȡ��Ӧ����
		StringBuffer buffer = new StringBuffer();
		String str = null;
		
		while((str = bufferedReader.readLine()) != null){
			buffer.append(str);
		}
		
		//�رղ��ͷ���Դ
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		
		//���������� 
		//System.out.println(buffer);
		
											//ת��json��ʽ
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		
		//��ȡ��" "�������key
		String accessToken = jsonObject.getString("access_token");
		int expiresIn = jsonObject.getInt("expires_in");
		
		//System.out.println("accessToken::"+accessToken);
		//System.out.println("expiresIn  ::"+expiresIn);
		return accessToken;
	}
}
