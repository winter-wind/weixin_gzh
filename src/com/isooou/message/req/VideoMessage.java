/*
 * ��Ƶ��Ϣ
 * 
 * */


package com.isooou.message.req;

public class VideoMessage extends BaseMessage{
	
	//��Ƶ��Ϣý��ID
	private String MediaId;
	//��Ƶ��Ϣ����ͼ��ý��ID
	private String ThumbMessage;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMessage() {
		return ThumbMessage;
	}
	public void setThumbMessage(String thumbMessage) {
		ThumbMessage = thumbMessage;
	}
	
	

}
