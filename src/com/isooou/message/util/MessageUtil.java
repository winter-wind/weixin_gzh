/*
 * 消息处理工具类
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

	//请求消息
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";//请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";//图片
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";//语音
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";//视频
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";//地理位置（单词不要拼错）
	public static final String REQ_MESSAGE_TYPE_LINK = "link";//链接
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";//事件推送
	
	//事件类型
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";//事件类型：订阅
	public static final String EVENT_TYPE_NUSUBSCRIBE = "nusubscribe";//取消订阅
	public static final String EVENT_TYPE_SCAN = "scan";//关注用户扫描带参数的二维码
	public static final String EVENT_TYPE_LOCATION = "LOCATION";//上报地理位置(注意大小写)
	public static final String EVENT_TYPE_CLICK = "CLICK";//自定义菜单(注意大小写)
	
	//响应消息
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";//响应消息类型：文本
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";//图片
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";//语音
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";//视频
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";//音乐
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";//图文

	/*
	 * 解析微信发来的请求(xml)
	 * */
	public static Map<String,String> parseXml(HttpServletRequest request)throws Exception{
		
		//将解析结果存储在HashMap(键，值对)中
		Map<String,String> map = new HashMap<String,String>();
		//从request中取得输入流
		InputStream inputStream = request.getInputStream();
		//读取输入流
		SAXReader reader = new SAXReader();//获取解析对象
		Document document = reader.read(inputStream);//得到获取和输出根元素的流
		
		//得到XML根元素
		Element root = document.getRootElement();
		//得到根元素的所有子元素
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		
		//遍历所有子元素
		for(Element e : list){
			map.put(e.getName(),e.getText());//存入map集合
		}
		
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	/*
	 * 扩展xstream使其支持CDATA 
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
	 * 文本消息对象转换成XML
	 * */
	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml",textMessage.getClass());//将这个类命名为xml
		return xstream.toXML(textMessage);//将对象序列化为XML字符串。
	}
	/*
	 * 图片消息对象转换成XML
	 * */
	public static String messageToXml(ImageMessage imageMessage){
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/*
	 * 语音消息对象转换成XML
	 * */
	public static String messageToXml(VoiceMessage voiceMessage){
		xstream.alias("xml",voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
	/*
	 * 视频消息对象转换成XML
	 * */
	public static String messageToXml(VideoMessage videoMessage){
		xstream.alias("xml",videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	/*
	 * 音乐消息对象转换成XML
	 * */
	public static String messageToXml(MusicMessage musicMessage){
		xstream.alias("xml",musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/*
	 * 图文消息对象转换成XML
	 * */
	public static String messageToXml(NewsMessage newsMessage){
		xstream.alias("xml",newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
}
