package com.example.demo.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consumer.MessageConsumer;
import com.example.demo.influxEntity.InfluxEntity;
import com.example.demo.influxService.InfluxService;
import com.example.demo.producer.MessageProducer;
import com.example.demo.service.MongoService;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class KafkaController {
	
	@Autowired
	MongoService service;
	
	@Autowired
	private MessageProducer messageProducer;
	
	@Autowired
	MessageConsumer consumer;
	
	@Autowired
	InfluxService influxService;
	
	List<String> list = new ArrayList<>();
	
	@GetMapping("/consume")
    public String consumeFromKafka() throws ParseException {
		list = consumer.getData();
		influxService.saveDataToInfluxDB(list);
		System.out.println("Data saved..");
		return "Consumed data from Kafka topic.";
    }
	
	Gson gson = new Gson(); 
    InfluxEntity entity[] = null;
    
	@GetMapping("/list")
    public void list(){
		list = consumer.getData();
		for(String str : list) {
    		entity = gson.fromJson(str, InfluxEntity[].class); 
    	}
    	
    	for(InfluxEntity lst : entity) {
    		System.out.println(lst.getEventSource());
    	}
    }

	@PostMapping("/send")
	public String sendMessage(@RequestBody String message) {
		messageProducer.sendMessage("topic-1", message);
		return "Message sent: ";
	}
	
}
