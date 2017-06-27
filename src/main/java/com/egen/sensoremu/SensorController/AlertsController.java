package com.egen.sensoremu.SensorController;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.sensoremu.SensorModel.Alert;
import com.egen.sensoremu.SensorService.AlertsService;

@RestController
@RequestMapping("/alerts")
public class AlertsController {

		@Autowired
		private AlertsService aservice;
		
		@RequestMapping(value="/read", method = RequestMethod.GET)
		public ResponseEntity<List<Alert>> getAllMetrics()
		{
			List<Alert> allAlerts = aservice.getAllAlerts();

			if(allAlerts.isEmpty()){
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Alert>>(allAlerts,HttpStatus.OK);
		}
		
		@RequestMapping(value="/readTimeStampRange", method = RequestMethod.GET)
		public ResponseEntity<List<Alert>> getAlertsByTimeRange(@RequestParam long startTimeStamp, @RequestParam long endTimeStamp)
		{
			List<Alert> alerts = aservice.getAlertsByRange(startTimeStamp, endTimeStamp);

			if(alerts.isEmpty()){
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Alert>>(alerts,HttpStatus.OK);
		}
	
}

