package info.rajeshg.domain;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import com.datastax.driver.core.utils.UUIDs;

@Configuration
public class JMSListener {
	
	@Autowired
	MessageService messageService;
	
	Logger log=Logger.getLogger(JMSListener.class);
	
	@JmsListener(destination="spring.boot.cassandra.queue", containerFactory="myJmsContainerFactory")
	public void message(String payload){
		
		log.info("JMS Payload received:"+payload);
		
		Message message = new Message();
		
		message.setDestination("DEST");
		message.setUuid(UUIDs.random());
		message.setPayload(ByteBuffer.allocate(payload.length()).put(payload.getBytes()));
		message.setPayloadType("iso.pain.113");
		message.setPayloadFormat("json");
		message.setPayloadChannel("web");
		message.setValueDate("20160716");
		message.setStatus("NEW");
		message.setWhenReceived(UUIDs.timeBased());
		message.setSource("INT");
		
		messageService.processMessage(message);
		log.info("Message saved:"+message);
		
	}

}
