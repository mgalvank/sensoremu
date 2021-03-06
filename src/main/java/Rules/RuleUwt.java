package Rules;

import java.io.*;
import java.util.*;

import org.bson.Document;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.egen.sensoremu.SensorModel.Metric;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//Underweight Rule
@Rule
public class RuleUwt {

	private Metric metric;
	private int defaultWeight;
	private int current;
	
	public RuleUwt(Metric metric,int defaultWeight){
		this.metric = metric;
		this.defaultWeight = defaultWeight;
	}
	
	@Condition
	public boolean ifUnderWeight(){
		boolean flag = false;
		current = this.metric.getValue();
		if(defaultWeight - (defaultWeight/10) < current){
			flag = true;
		}
		return flag;
	}
	
	@Action
	public void UwtAlert(){
		MongoDatabase dB = null;
		MongoClient mClient = null;
		String DB_NAME = "egen";
		String DB_URL = "localhost";
		int DB_PORT = 27017;
		mClient = new MongoClient(DB_URL, DB_PORT);
		dB = mClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = dB.getCollection("alerts");
		Document document = new Document("_id",this.metric.getTimeStamp())
								.append("value", this.metric.getValue())
								.append("type", "Underweight");	
		collection.insertOne(document);
	}
}

