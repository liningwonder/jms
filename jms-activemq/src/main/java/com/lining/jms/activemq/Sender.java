package com.lining.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * description:
 * date 2018-04-20
 *
 * @author lining1
 * @version 1.0.0
 */
public class Sender {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("This is a test.");
            Thread.sleep(1000);
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
