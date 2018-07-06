package com.mrl.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author lwqMR
 * ������
 */
public class AppConsumer {
	private static final String URL = "tcp://127.0.0.1:61616";
	private static final String topicName = "topic-test";
	
	public static void main(String[] args) throws JMSException {
		//1.����ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		//2.��������
		Connection connection = connectionFactory.createConnection();
		//3.��������
		connection.start();
		//4.�����Ự  ����������1���Ƿ��������д���2.Ӧ��ģʽ���Զ�Ӧ��
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.ָ��Ŀ�ĵ�,Ŀ��
		Destination destination = session.createTopic(topicName);
		//6.����������
		MessageConsumer consumer = session.createConsumer(destination);
		
		//7.����һ��������
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage txMessage = (TextMessage) message;
				try {
					System.out.println("������Ϣ��"+txMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
//		connection.close();
		
	}
}
