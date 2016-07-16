package info.rajeshg.domain;

import java.nio.ByteBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import com.datastax.driver.core.utils.UUIDs;

@Configuration
public class JMSListener {
	
	@Autowired
	MessageService messageService;
	
	@JmsListener(destination="spring.boot.cassandra.queue")
	public void message(String payload){
		
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
		
	}

}
