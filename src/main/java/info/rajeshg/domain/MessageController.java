package info.rajeshg.domain;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;

@RestController
public class MessageController {
	
	Logger log = Logger.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/message")
	public void message(@RequestParam(value="payload") String payload){
		log.info("Web request received with payload:"+payload);
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
		log.info("Web request saved - message:"+message);
		
	}

}
