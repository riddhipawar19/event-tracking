package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
	
	@Autowired
	private final MongoTemplate mongoTemplate;

    public MongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> findAllDocuments(String collectionName) {
    	Query query = new Query();
        List<Document> documents = mongoTemplate.find(query, Document.class, collectionName);
        return documents;
    }
}
