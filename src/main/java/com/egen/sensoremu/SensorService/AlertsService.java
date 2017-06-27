package com.egen.sensoremu.SensorService;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.sensoremu.SensorDao.AlertsDao;
import com.egen.sensoremu.SensorModel.Alert;

@Service
public class AlertsService {
	
	@Autowired
	private AlertsDao aDao;
	
	public List<Alert> getAllAlerts(){
		List<Alert> allAlerts = aDao.getAllAlerts();
		return allAlerts;
	}
	
	public List<Alert> getAlertsByRange(long startTimeStamp,long endTimeStamp){
		List<Alert> alertsByRange = aDao.getAlertsByRange(startTimeStamp, endTimeStamp);
		return alertsByRange;
	}
}

