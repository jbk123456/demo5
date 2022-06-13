package io.openliberty.guides.event.resources;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ejb.*;


// @MessageDriven(mappedName="jms/JmsQueue", activationConfig = {
//     @ActivationConfigProperty(propertyName = "acknowledgeMode",
//         propertyValue = "Auto-acknowledge"),
//     @ActivationConfigProperty(propertyName = "destinationType",
//         propertyValue = "javax.jms.Queue")
// })

@MessageDriven(name = "SampleListenerMDB")
public class JmsMessageReader implements MessageListener {

public static String lastMsg = "";

@Override
public void onMessage(Message message) {


    TextMessage textMessage = (TextMessage) message;
    try {
        System.out.println("Message arrived: " + textMessage.getText());
	lastMsg = textMessage.getText();
    } catch (JMSException e) {
        System.err.println(e.getMessage());
    }

}
}
