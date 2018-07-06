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
 * 订阅者
 */
public class AppConsumer {
	private static final String URL = "tcp://127.0.0.1:61616";
	private static final String topicName = "topic-test";
	
	public static void main(String[] args) throws JMSException {
		//1.创建ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		//2.创建连接
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话  两个参数：1，是否在事务中处理，2.应答模式：自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.指定目的地,目标
		Destination destination = session.createTopic(topicName);
		//6.创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		
		//7.创建一个监听器
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage txMessage = (TextMessage) message;
				try {
					System.out.println("接收消息："+txMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
//		connection.close();
		
	}
}
