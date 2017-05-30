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
		btn1.setName("���ո�����");
		btn1.setType("click");
		btn1.setKey("V1001_TODAY_MUSIC");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("163��飡");
		btn2.setType("view");
		btn2.setUrl("http://www.163.com");
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("hello word��");
		btn31.setType("click");
		btn31.setKey("V1001_HELLO_WORD");
		
		ClickButton btn32 = new ClickButton();
		btn32.setName("��һ�����ǣ�");
		btn32.setType("click");
		btn32.setKey("V1001_GOOD");
		
		//���ϰ�ť��������click���͵İ�ť
		ComplexButton btn3 = new ComplexButton();
		btn3.setName("�˵���");
		btn3.setSub_button(new Button[] {btn31,btn32});
		
		//�����˵�����
		Menu menu = new Menu();
		menu.setButton(new Button[] {btn1,btn2,btn3});
		
		//���˵�����ת����json�ַ���
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
	
		//-------------------------------------------------------------------------
		//�������󽫲˵��ṹ�ύ��΢�ŷ��������˵������ӿڵ�ַ
		String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ new TokenDemo().TokenStr();
		//��������
		URL url = new URL(menuCreateUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
		//ʹ���Զ������ƾ������
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		httpUrlConn.setSSLSocketFactory(ssf);
		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		//��������ʽ
		httpUrlConn.setRequestMethod("POST");
				
		//�������д�˵��ṹ
		OutputStream outputStream = httpUrlConn.getOutputStream();
		outputStream.write(jsonMenu.getBytes("UTF-8"));
		outputStream.close();
		
		//ȡ��������
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		//��ȡ��Ӧ������
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while((str = bufferedReader.readLine()) != null){
			buffer.append(str);
		}
		//�ر�/�ͷ���Դ
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		httpUrlConn.disconnect();
		//����˵��������
		//System.out.println("����� "+buffer);
	}
}
