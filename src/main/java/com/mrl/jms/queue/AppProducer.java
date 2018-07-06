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
 *生产者
 */
public class AppProducer {
	
	private static final String URL = "tcp://127.0.0.1:61616";
	private static final String queueName = "queueTest";
	
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
		Destination destination = session.createQueue(queueName);
		//6.创建生产者
		MessageProducer producer = session.createProducer(destination);
		
		//循环向生产者发送消息
		for(int i=0;i<100;i++){
			//7.创建消息
			TextMessage txMessage = session.createTextMessage("test消息:"+i);
			//8.发送消息
			producer.send(txMessage);
			//日志
			System.out.println("消息发送成功:"+txMessage.getText());
		}
		
		//9.关闭连接
//		connection.close();
		
	}
}
