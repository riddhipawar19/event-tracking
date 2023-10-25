package com.example.demo.influxEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class InfluxEntity {
	String user;
	String eventType;
	String eventSource;
	String browserName;
	String platform;
	String timestamp;
	int demo_field;
}
