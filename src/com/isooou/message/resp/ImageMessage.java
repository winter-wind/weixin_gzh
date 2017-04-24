/*
 * 图片消息
 * 
 * */


package com.isooou.message.resp;

public class ImageMessage extends BaseMessage{

	//图片
	private Image Image;//查看Image.java

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
	
	
}
