/*
 * 视频消息
 * 
 * */


package com.isooou.message.req;

public class VideoMessage extends BaseMessage{
	
	//视频消息媒体ID
	private String MediaId;
	//视频消息缩略图的媒体ID
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
