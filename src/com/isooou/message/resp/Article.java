/*
 * ͼ��model
 * 
 * 
 * */


package com.isooou.message.resp;

public class Article {

	//ͼ����Ϣ����
	private String Title;
	//ͼ����Ϣ����
	private String Description;
	//ͼƬ���ӣ�֧��JPG,PNP��ʽ���Ϻõ�Ч��Ϊ(����)��ͼ640X320��Сͼ80X80
	private String PicUrl;
	//���ͼ����Ϣ��ת����
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return null == Description ? "" : Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return null == Url ? "" : Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	
}