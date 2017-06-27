package com.egen.sensoremu.SensorDao;

import java.io.*;
import java.util.*;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

import com.egen.sensoremu.SensorModel.Alert;
import com.egen.sensoremu.SensorModel.Metric;

@Repository
public class AlertsDao {

	@Autowired
	private Datastore dStore;
	
	public void createAlert(Alert alert){
		dStore.save(alert);
	}
	
	public List<Alert> getAllAlerts(){
		Query<Alert> query = dStore.createQuery(Alert.class);
		List<Alert> allMetrics = query.asList();
		return allMetrics;
	}
	
	
	public List<Alert> getAlertsByRange(long startTimeStamp,long endTimeStamp){
		
		Query<Alert> query = dStore.createQuery(Alert.class).field("timeStamp").greaterThanOrEq(startTimeStamp)
				.field("timeStamp").lessThan(endTimeStamp);
		List<Alert> alertsByRange = query.asList();
		
		return alertsByRange;
	}
}

