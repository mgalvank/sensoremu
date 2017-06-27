package com.egen.sensoremu.SensorDao;

import java.io.*;
import java.util.*;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

import com.egen.sensoremu.SensorModel.Metric;

@Repository
public class MetricsDao {
	@Autowired
	private Datastore dStore;
	
	public void createMetric(Metric metric){
		dStore.save(metric);
	}
	
	public List<Metric> getAllMetrics(){
		Query<Metric> query = dStore.createQuery(Metric.class);
		List<Metric> allMetrics = query.asList();
		return allMetrics;
	}
	
	public List<Metric> getMetricsByRange(long startTimeStamp,long endTimeStamp){
		
		Query<Metric> query = dStore.createQuery(Metric.class).field("timeStamp").greaterThanOrEq(startTimeStamp)
				.field("timeStamp").lessThan(endTimeStamp);
		List<Metric> metricsByRange = query.asList();
		
		return metricsByRange;
	}
}

