/*
 * ɨ���������ά���¼�
 * 
 * */


package com.isooou.message.event;

public class QRCodeEvent extends BaseEvent{

	//�¼�keyֵ
	private String EventKeyl;
	//���ڻ�ȡ��ά��ͼƬ
	private String Ticket;
	public String getEventKeyl() {
		return EventKeyl;
	}
	public void setEventKeyl(String eventKeyl) {
		EventKeyl = eventKeyl;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
	
}
