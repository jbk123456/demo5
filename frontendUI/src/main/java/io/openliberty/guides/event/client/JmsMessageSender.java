package io.openliberty.guides.event.client;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsMessageSender {

    public JmsMessageSender(ConnectionFactory jmsFactory, Queue jmsQueue) {
        this.jmsFactory = jmsFactory;
        this.jmsQueue = jmsQueue;
    }
    private ConnectionFactory jmsFactory;

    private Queue jmsQueue;

    public void send() {

        TextMessage message;

        try (Connection connection = jmsFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(jmsQueue)) {
    
            message = session.createTextMessage();
            message.setText("Hello World!");
    
            // Don't pass in destination again since you set it in createProducer()
            producer.send(message);   
	    System.out.println("messge sent");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
