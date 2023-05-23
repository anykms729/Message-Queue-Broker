package com.learn.examplemqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SampleMqttSubscriber {

	public static void main(String[] args) {
		String broker = "tcp://localhost:1883";
		String clientID = "Subscriber";
		
//		MemoryPersistence persistence = new MemoryPersistence();
		
		try {
			MqttClient sampleClient = new MqttClient(broker, clientID);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			
			connOpts.setCleanSession(true);
			sampleClient.setCallback(new SampleSubscriber());
			
			System.out.println("Connecting to broker : " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected..");
			
			sampleClient.subscribe("/house/water");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

class SampleSubscriber implements MqttCallback{

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		System.out.println("Connection is lost " + cause.getStackTrace());
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new String(message.getPayload()) + " arrived from topic " + topic + " with qos " + message.getQos() );
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		System.out.println("delivery is complete " +  token.isComplete());
	}
	
}
