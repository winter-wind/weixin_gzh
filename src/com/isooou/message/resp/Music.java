/*
 * ����model
 * 
 * */


package com.isooou.message.resp;

public class Music {

	//���ֱ���
	private String Title;
	//��������
	private String Description;
	//��������
	private String MusicUrl;
	//�������������ӣ�WI-FI ��������ʹ�ø����Ӳ�������
	private String HQMusicUrl;
	//����ͼ��ý��ID��ͨ���ϴ���ý�� �ļ��õ���ID
	private String ThumbMediaId;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
}
