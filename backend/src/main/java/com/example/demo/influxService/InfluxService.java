package com.example.demo.influxService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.influxEntity.InfluxEntity;
import com.google.gson.Gson;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

@Service
public class InfluxService {
	String token = "ZvWavRyRiHp8pjTSM5sHqOaGOyfi912lsh1O0_lVztxX1-WCoHEoi1HlvsIa6nwGDoSKTCVqjcjVQrmOmpsGBw==";
    String bucket = "data";
    String org = "star-health";

    InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
    
    Gson gson = new Gson(); 
    InfluxEntity entity[] = null;
    Point point;
    
    public void saveDataToInfluxDB(List<String> list) throws ParseException {
    	
    	for(String str : list) {
    		entity = gson.fromJson(str, InfluxEntity[].class); 
    	}
    	
    	for(InfluxEntity lst : entity) {
    		System.out.println(lst);
    	}
    	
    	Timestamp time;
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        Date parsedDate; 
 
    	
    	for(int i=0; i<entity.length; i++) {
    		
    		parsedDate = dateFormat.parse(entity[i].getTimestamp());
    		time = new Timestamp(parsedDate.getTime());
    		
    		point = Point
    			.measurement("event-data")
    			.addField("user", entity[i].getUser())
    			.addField("eventType", entity[i].getEventType())
    			.addField("eventSource", entity[i].getEventSource())
    			.addField("browserName", entity[i].getBrowserName())
    			.addField("platform", entity[i].getPlatform())
    			.addField("timestamp", entity[i].getTimestamp())
    		.addField("demo_field", entity[i].getDemo_field());

    	try (WriteApi writeApi = client.getWriteApi()) {
    		writeApi.writePoint(bucket, org, point);
    	}
    	
    	}
    }
}
