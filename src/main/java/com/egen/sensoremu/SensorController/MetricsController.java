package com.egen.sensoremu.SensorController;

import java.io.*;
import java.util.*;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.sensoremu.SensorModel.Metric;
import com.egen.sensoremu.SensorService.MetricsService;

import Rules.RuleOwt;
import Rules.RuleUwt;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
	
	@Autowired
	private MetricsService mservice;
	
	int defaultWeight;
	
	@RequestMapping(value="/create",method = RequestMethod.POST)
	public ResponseEntity<String> createMetric(@RequestBody Metric metric){
		long timeStamp = metric.getTimeStamp();
		int value = metric.getValue();
		
		if(defaultWeight == 0){
			defaultWeight = value;
		}
		mservice.createMetric(metric);
		RuleOwt ruleOwt = new RuleOwt(metric,defaultWeight);
		RuleUwt ruleUwt = new RuleUwt(metric,defaultWeight);
		RulesEngine alertRulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
		alertRulesEngine.registerRule(ruleOwt);
		alertRulesEngine.registerRule(ruleUwt);
		alertRulesEngine.fireRules();
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public ResponseEntity<List<Metric>> getAllMetrics()
	{
		List<Metric> allMetrics = mservice.getAllMetrics();

		if(allMetrics.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Metric>>(allMetrics,HttpStatus.OK);
	}
	
	@RequestMapping(value="/readTimeStampRange", method = RequestMethod.GET)
	public ResponseEntity<List<Metric>> getMetricsByTimeRange(@RequestParam long startTimeStamp, @RequestParam long endTimeStamp)
	{
		List<Metric> metricsByRange = mservice.getMetricsByRange(startTimeStamp, endTimeStamp);

		if(metricsByRange.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Metric>>(metricsByRange,HttpStatus.OK);
	}

}

