package info.rajeshg.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
	
	@Autowired
	MessageRepository messageRepository;

	public void processMessage(Message message) {
		// TODO message processing snippet
		messageRepository.save(message);
		
	}

}
