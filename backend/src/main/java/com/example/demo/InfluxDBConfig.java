package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class InfluxDBConfig {

	@Value("${influxdb.url}")
    private String influxDBUrl;

    @Value("${influxdb.username}")
    private String username;

    @Value("${influxdb.password}")
    private String password;

//    @Bean
//    public InfluxDB influxDB() {
//        InfluxDB influxDB = InfluxDBFactory.connect(influxDBUrl, username, password);
//        influxDB.enableGzip();
//        return influxDB;
//    }
}
