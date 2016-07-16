package info.rajeshg.domain;

import java.nio.ByteBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;

@RestController
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/message")
	public void message(@RequestParam(value="payload") String payload){
		
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
