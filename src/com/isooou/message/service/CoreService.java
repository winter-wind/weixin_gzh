/*
 * ���ķ�����
 * */


package com.isooou.message.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.isooou.message.resp.TextMessage;
import com.isooou.message.util.MessageUtil;

public class CoreService {
	/*
	 * ����΢�ŷ���������
	 * */
	public static String processRequest(HttpServletRequest request){
		//xml��ʽ����Ϣ����
		String respXml = null;
		//Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "δ֪����Ϣ���ͣ�";
		try{
			//����parseXml��������������Ϣ
			Map<String,String> requestMap = MessageUtil.parseXml(request);
			//���ͷ��˺�
			String fromUserName = requestMap.get("FromUserName");
			//������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			//��Ϣ����
			String msgType = requestMap.get("MsgType");
			
			//�ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			//�ı���Ϣ
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				respContent = "�����͵����ı���Ϣ��";
			}
			//ͼƬ��Ϣ
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			//������Ϣ
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				respContent = "�����͵���������Ϣ��";
			}
			//��Ƶ��Ϣ
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			//����λ����Ϣ
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			//������Ϣ
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "�����͵���������Ϣ��";
			}
			//�¼�����
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				//�¼�����
				String eventType = requestMap.get("Event");
				//��ע�¼�
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respContent = "лл���Ĺ�ע��";
				}
				//ȡ����ע
				else if(eventType.equals(MessageUtil.EVENT_TYPE_NUSUBSCRIBE)){
					//TODO ȡ����ע���û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				//ɨ���������ά��
				else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					//TODO ����ɨ���������ά���¼�
				}
				//�ϱ�����λ��
				else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					//TODO �����ϱ�����λ��
				}
				//�Զ���˵�
				else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					respContent = "���ˣ�";
					//�¼�����
					//String eventType_ = requestMap.get("Event");
					//�Զ���˵������(CLICK)��
				//	if(eventType_.equals("CLICK")){
						//�¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
						String eventKey = requestMap.get("EventKey");
						System.out.println(eventKey);
						//����keyֵ�ж��û�����İ�ť
						if(eventKey.equals("V1001_TODAY_MUSIC")){
							respContent = "�û�����ˡ����ո�������ť";
						}else if(eventKey.equals("V1001_HELLO_WORD")){
							respContent = "�û�����ˡ�hello word����ť";
						}else if(eventKey.equals("V1001_GOOD")){
							respContent = "�û�����ˡ���һ�����ǡ���ť";
						}
					//}
				}
			}
			//�����ı���Ϣ����
			textMessage.setContent(respContent);
			//���ı���Ϣ����ת����xml
			respXml = MessageUtil.messageToXml(textMessage);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return respXml;
	}
}
