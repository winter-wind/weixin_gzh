/*
 * 获取access_token方法
 * 1.直接在网址输入
 * 2.用程序获取：如下
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
		//获取的地址
		//String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_"
				//+ "credential&appid=appid&secret=secret";
		//TEST
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_"
				+ "credential&appid=appid&secret=secret";
		
		URL url = new URL(tokenUrl);
		//生成请求，打开链接
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		
		//使用自定义的信任管理器
		TrustManager[] tm = {new MyX509TrustManager()};//信任证书数组
		
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
													//所请求协议的标准名称 ，提供者名称
		//初始化上下文           验证密钥或null，同位体验证信任决策源或 null，提供强加密随机数生成器
		sslContext.init(null, tm, new java.security.SecureRandom());
		
		//返回此上下文的SocketFactory
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		//为安全HTTPS URL连接创建套接字时使用SocketFactory
		conn.setSSLSocketFactory(ssf);
		
		//使用URL连接进行输入输出操作
		conn.setDoInput(true);
		
		//请求方式
		conn.setRequestMethod("GET");
		
		//获取输入流
		InputStream inputStream = conn.getInputStream();
		
		//字节流向字符流
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//读取响应内容
		StringBuffer buffer = new StringBuffer();
		String str = null;
		
		while((str = bufferedReader.readLine()) != null){
			buffer.append(str);
		}
		
		//关闭并释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		
		//输出返回如果 
		//System.out.println(buffer);
		
											//转成json格式
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		
		//获取与" "相关联的key
		String accessToken = jsonObject.getString("access_token");
		int expiresIn = jsonObject.getInt("expires_in");
		
		//System.out.println("accessToken::"+accessToken);
		//System.out.println("expiresIn  ::"+expiresIn);
		return accessToken;
	}
}
