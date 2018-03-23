package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber {

  public static void a(String[] args) throws MqttException {

    System.out.println("== START SUBSCRIBER TOPIC IOT_GPS ==");
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient client=new MqttClient("tcp://172.28.11.11:1883", MqttClient.generateClientId(), persistence);
     
    client.setCallback( new SimpleMqttCallBack() );
    client.connect();

    client.subscribe("iot_gps");

  }

}