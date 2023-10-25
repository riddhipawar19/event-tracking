package com.example.demo.consumer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

	List<String> list = new ArrayList<>();
	
	@KafkaListener(topics = "topic-1", groupId = "my-group-id")
    public void listen(String message) {
		list.add(message);
        System.out.println("Received message: " + message);
    }
	
	public List<String> getData(){
		return list;
	}
}
