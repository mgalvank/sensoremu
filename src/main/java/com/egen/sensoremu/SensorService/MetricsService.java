package com.egen.sensoremu.SensorService;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.sensoremu.SensorDao.AlertsDao;
import com.egen.sensoremu.SensorDao.MetricsDao;
import com.egen.sensoremu.SensorModel.Alert;
import com.egen.sensoremu.SensorModel.Metric;

@Service
public class MetricsService {

	@Autowired
	private MetricsDao mDao;
	
	public void createMetric(Metric metric){
		mDao.createMetric(metric);
	}
	
	public List<Metric> getAllMetrics(){
		List<Metric> allMetrics = mDao.getAllMetrics();
		return allMetrics;
	}
	
	public List<Metric> getMetricsByRange(long startTimeStamp,long endTimeStamp){
		List<Metric> metricsByRange = mDao.getMetricsByRange(startTimeStamp, endTimeStamp);
		return metricsByRange;
	}
}

