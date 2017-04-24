package com.isooou.weixin.menu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.isooou.getaccesstoken.TokenDemo;
import com.weixin.x509.MyX509TrustManager;

import net.sf.json.JSONObject;

public class MenuDemo {

	public static void main(String[] args)throws Exception{
		ClickButton btn1 = new ClickButton();
		btn1.setName("今日歌曲！");
		btn1.setType("click");
		btn1.setKey("V1001_TODAY_MUSIC");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("163简介！");
		btn2.setType("view");
		btn2.setUrl("http://www.163.com");
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("hello word！");
		btn31.setType("click");
		btn31.setKey("V1001_HELLO_WORD");
		
		ClickButton btn32 = new ClickButton();
		btn32.setName("赞一下我们！");
		btn32.setType("click");
		btn32.setKey("V1001_GOOD");
		
		//复合按钮包含二个click类型的按钮
		ComplexButton btn3 = new ComplexButton();
		btn3.setName("菜单！");
		btn3.setSub_button(new Button[] {btn31,btn32});
		
		//创建菜单对象
		Menu menu = new Menu();
		menu.setButton(new Button[] {btn1,btn2,btn3});
		
		//将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
	
		//-------------------------------------------------------------------------
		//发起请求将菜单结构提交到微信服务器。菜单创建接口地址
		String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ new TokenDemo().TokenStr();
		//建立连接
		URL url = new URL(menuCreateUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
		//使用自定义的任凭管理器
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		httpUrlConn.setSSLSocketFactory(ssf);
		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		//设置请求方式
		httpUrlConn.setRequestMethod("POST");
				
		//向输出流写菜单结构
		OutputStream outputStream = httpUrlConn.getOutputStream();
		outputStream.write(jsonMenu.getBytes("UTF-8"));
		outputStream.close();
		
		//取得输入流
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		//读取响应的内容
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while((str = bufferedReader.readLine()) != null){
			buffer.append(str);
		}
		//关闭/释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		httpUrlConn.disconnect();
		//输出菜单创建结果
		//System.out.println("结果： "+buffer);
	}
}
