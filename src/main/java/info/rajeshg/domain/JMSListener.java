package info.rajeshg.domain;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.transaction.annotation.Transactional;

import com.datastax.driver.core.utils.UUIDs;

import info.rajeshg.annotation.TimeIt;

@Configuration
public class JMSListener {

	@Autowired
	MessageService messageService;

	Logger log = Logger.getLogger(JMSListener.class);

	@TimeIt
	@JmsListener(destination = "spring.boot.cassandra.queue", containerFactory = "myJmsContainerFactory")
	@Transactional()
	public void message(String payload) {
		try {
			log.info("JMS Payload received:" + payload);

			if (payload.equals("exception-beforesave")) {
				throw new Exception("Exception before saving message");
			}
			Message message = new Message();

			message.setDestination("DEST");
			message.setUuid(UUIDs.random());
			message.setPayload(ByteBuffer.allocate(payload.length()).put(payload.getBytes()));
			message.setPayloadType("iso.pain.113");
			message.setPayloadFormat("json");
			message.setPayloadChannel("jms");
			message.setValueDate("20160716");
			message.setStatus("NEW");
			message.setWhenReceived(UUIDs.timeBased());
			message.setSource("INT");

			messageService.processMessage(message);
			log.info("Message saved:" + message);
			
			if (payload.equals("exception-aftersave")) {
				throw new Exception("Exception after saving message");
			}
		} catch (Exception ex) {
			throw new RuntimeException("Runtime Exception:"+ex.getMessage());
		}
	}

}
