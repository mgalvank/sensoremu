package com.egen.sensoremu.SensorDao;

import java.io.*;
import java.util.*;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.egen.sensoremu.SensorModel.Alert;
import com.egen.sensoremu.SensorModel.Metric;
import com.mongodb.MongoClient;


@Configuration
public class DbConfig {
	
	@Autowired
	private MongoClient mdbClient;
	
	@Bean
	public Datastore dStore(){
		Datastore dStore = null;
		
		String dbName = "egen";
		Morphia morphia = new Morphia();
		morphia.map(Metric.class);
		morphia.map(Alert.class);
		dStore = morphia.createDatastore(mdbClient, dbName);
		
		return dStore;
	}
	 	
}

