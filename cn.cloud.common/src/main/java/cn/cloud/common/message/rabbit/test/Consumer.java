
package cn.cloud.common.message.rabbit.test;



import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;



public class Consumer {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();		
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		
		
		Connection connection =  connectionFactory.newConnection();

		Channel channel = connection.createChannel();
		com.rabbitmq.client.Consumer consumer = new simpleConsumer(channel);
		//testreciver(channel,consumer);
		directExchange(channel, consumer);

	}
	
	
	public static void testreciver(Channel channel,com.rabbitmq.client.Consumer consumer) throws IOException {
		String queue = "test01";
		channel.queueDeclare(queue, true, false, false, null);
		channel.basicConsume(queue, true, consumer);
	}
	public static void directExchange(Channel channel,com.rabbitmq.client.Consumer consumer) throws IOException {
		String exchangeName = "test_direct_ex";
		String queueName =channel.queueDeclare().getQueue();
		String routingKey  = "test_direct";
		channel.queueBind(queueName, exchangeName, routingKey);
		channel.basicConsume(queueName, true, consumer);
	}
	public static void topicExcheng(Channel channel,com.rabbitmq.client.Consumer consumer) throws IOException {
		
		String exchengeName = "topic_ex";
		String exchengeType ="topic";
		String queueName = channel.queueDeclare().getQueue();
	}
	
	
	
	
	private static class simpleConsumer extends DefaultConsumer {

		public simpleConsumer(Channel channel) {
			super(channel);
		}
		
		 @Override
         public void handleDelivery(String consumerTag, Envelope envelope,
                                    AMQP.BasicProperties properties, byte[] body)
                 throws IOException {
             String message = new String(body, "UTF-8");
             System.out.println("Customer Received '" + message + "'" +  consumerTag);
        
         }
		
	}

}

