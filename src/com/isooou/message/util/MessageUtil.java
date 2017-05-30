/*
 * ��Ϣ��������
 * 
 * 
 * */

package com.isooou.message.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.isooou.message.req.LocationMessage;
import com.isooou.message.resp.Article;
import com.isooou.message.resp.ImageMessage;
import com.isooou.message.resp.MusicMessage;
import com.isooou.message.resp.NewsMessage;
import com.isooou.message.resp.TextMessage;
import com.isooou.message.resp.VideoMessage;
import com.isooou.message.resp.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.core.util.QuickWriter;

public class MessageUtil {

	//������Ϣ
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";//������Ϣ���ͣ��ı�
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";//ͼƬ
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";//����
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";//��Ƶ
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";//����λ�ã����ʲ�Ҫƴ��
	public static final String REQ_MESSAGE_TYPE_LINK = "link";//����
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";//�¼�����
	
	//�¼�����
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";//�¼����ͣ�����
	public static final String EVENT_TYPE_NUSUBSCRIBE = "nusubscribe";//ȡ������
	public static final String EVENT_TYPE_SCAN = "scan";//��ע�û�ɨ��������Ķ�ά��
	public static final String EVENT_TYPE_LOCATION = "LOCATION";//�ϱ�����λ��(ע���Сд)
	public static final String EVENT_TYPE_CLICK = "CLICK";//�Զ���˵�(ע���Сд)
	
	//��Ӧ��Ϣ
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";//��Ӧ��Ϣ���ͣ��ı�
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";//ͼƬ
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";//����
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";//��Ƶ
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";//����
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";//ͼ��

	/*
	 * ����΢�ŷ���������(xml)
	 * */
	public static Map<String,String> parseXml(HttpServletRequest request)throws Exception{
		
		//����������洢��HashMap(����ֵ��)��
		Map<String,String> map = new HashMap<String,String>();
		//��request��ȡ��������
		InputStream inputStream = request.getInputStream();
		//��ȡ������
		SAXReader reader = new SAXReader();//��ȡ��������
		Document document = reader.read(inputStream);//�õ���ȡ�������Ԫ�ص���
		
		//�õ�XML��Ԫ��
		Element root = document.getRootElement();
		//�õ���Ԫ�ص�������Ԫ��
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		
		//����������Ԫ��
		for(Element e : list){
			map.put(e.getName(),e.getText());//����map����
		}
		
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	/*
	 * ��չxstreamʹ��֧��CDATA 
	 * */
	
	
	private static XStream xstream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out) {
				boolean cdata = true;
				
				@SuppressWarnings("rawtypes")
				public void startNode(String name,Class clazz){
					super.startNode(name,clazz);
				}
				protected void writeText(QuickWriter writer,String text){
					if(cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
			};
		}
	});
	
	/*
	 * �ı���Ϣ����ת����XML
	 * */
	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml",textMessage.getClass());//�����������Ϊxml
		return xstream.toXML(textMessage);//���������л�ΪXML�ַ�����
	}
	/*
	 * ͼƬ��Ϣ����ת����XML
	 * */
	public static String messageToXml(ImageMessage imageMessage){
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/*
	 * ������Ϣ����ת����XML
	 * */
	public static String messageToXml(VoiceMessage voiceMessage){
		xstream.alias("xml",voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
	/*
	 * ��Ƶ��Ϣ����ת����XML
	 * */
	public static String messageToXml(VideoMessage videoMessage){
		xstream.alias("xml",videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	/*
	 * ������Ϣ����ת����XML
	 * */
	public static String messageToXml(MusicMessage musicMessage){
		xstream.alias("xml",musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/*
	 * ͼ����Ϣ����ת����XML
	 * */
	public static String messageToXml(NewsMessage newsMessage){
		xstream.alias("xml",newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
}
