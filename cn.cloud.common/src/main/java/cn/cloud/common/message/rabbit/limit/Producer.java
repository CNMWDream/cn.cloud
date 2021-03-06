package cn.cloud.common.message.rabbit.limit;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Dream
 *  限流   ack  producer
 */
public class Producer {
	public static Channel getChannel() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		return channel;
	}
	public static void confirmSend(Channel channel) throws IOException, TimeoutException {
		String exChangeName = "qos-ex";
		String routingKey = "qos.save";
		String msg = " 消息确认模式";
		channel.basicPublish(exChangeName, routingKey, null, msg.getBytes());
		System.out.println( " 发送 消息 确认模式1");
		
		channel.basicPublish(exChangeName, routingKey, null, (msg+" 1").getBytes());
		System.out.println( " 发送 消息 确认模式2");
		
	}
	
	public static void testACKSend(Channel channel) throws IOException, TimeoutException {
		String exChangeName = "ack-ex";
		String routingKey = "ack.save";
		String msg = " 消息 测试ACK模式  ::::   ";
		HashMap< String, Object> headers = new HashMap<>();
		headers.put("test", "ack");
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
				.deliveryMode(2)
				.contentEncoding("UTF-8")
				.headers(headers)
				.build();
		channel.basicPublish(exChangeName, routingKey, true, properties,msg.getBytes());
		System.out.println( " 发送 消息 " + msg + headers.get("test") );
		

		HashMap<String, Object> headers1 = new HashMap<>();
		headers1.put("test", "ack1");
		AMQP.BasicProperties properties1 = new AMQP.BasicProperties.Builder()
				.deliveryMode(2)
				.contentEncoding("UTF-8")
				.headers(headers1)
				.build();
		String msg2 = "消息 测试ACK模式  ::::  11111   ";
		channel.basicPublish(exChangeName, routingKey, true, properties1,msg2.getBytes());
		System.out.println( " 发送 消息   " + msg2 + headers.get("test"));
	
	}
	public static void main(String[] args) throws IOException, TimeoutException {
		//confirmSend(getChannel());
		testACKSend(getChannel());
		
	}

}
