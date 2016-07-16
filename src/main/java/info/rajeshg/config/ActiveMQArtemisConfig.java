package info.rajeshg.config;

import javax.jms.Queue;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class ActiveMQArtemisConfig {
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("spring.boot.cassandra.queue");
	}

}
