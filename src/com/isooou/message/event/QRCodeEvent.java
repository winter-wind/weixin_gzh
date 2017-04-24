/*
 * 扫描带参数二维码事件
 * 
 * */


package com.isooou.message.event;

public class QRCodeEvent extends BaseEvent{

	//事件key值
	private String EventKeyl;
	//用于换取二维码图片
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
