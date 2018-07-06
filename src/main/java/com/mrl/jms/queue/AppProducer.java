package com.mrl.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author lwqMR
 *������
 */
public class AppProducer {
	
	private static final String URL = "tcp://127.0.0.1:61616";
	private static final String queueName = "queueTest";
	
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
		Destination destination = session.createQueue(queueName);
		//6.����������
		MessageProducer producer = session.createProducer(destination);
		
		//ѭ���������߷�����Ϣ
		for(int i=0;i<100;i++){
			//7.������Ϣ
			TextMessage txMessage = session.createTextMessage("test��Ϣ:"+i);
			//8.������Ϣ
			producer.send(txMessage);
			//��־
			System.out.println("��Ϣ���ͳɹ�:"+txMessage.getText());
		}
		
		//9.�ر�����
//		connection.close();
		
	}
}
