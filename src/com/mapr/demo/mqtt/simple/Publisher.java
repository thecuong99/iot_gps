package com.mapr.demo.mqtt.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {

  public static void b(String[] args) throws MqttException {

    String messageString = "Hello World from Java!";
    FileInputStream fin;
    if (args.length == 2 ) {
      messageString = args[1];
    }
    MemoryPersistence persistence = new MemoryPersistence();

    System.out.println("== START PUBLISHER TOPIC IOT_GPS ==");
    MqttClient client = new MqttClient("tcp://172.28.11.11:1883", MqttClient.generateClientId(), persistence);
    client.connect();
    System.out.println("Type string GPS to send to TOPIC IOT_GPS.");
    MqttMessage message = new MqttMessage();
    
    File file = new File("D:\\Master_UoT_Khoa2017_Dot1\\Subject_ComputerNetwork_4TC\\data\\test.txt");
    FileReader fileReader;
    try {
        fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
          
        while ( messageString != null) {
            try {
                messageString = bufferedReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
            }
            message.setPayload(messageString.getBytes());
            client.publish("iot_gps", message);
            System.out.println("\tMessage '"+ messageString +"' to 'iot_gps'");
        }
          
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    client.disconnect();

    System.out.println("== END PUBLISHER ==");

  }


}

/*
BufferedReader  user=new  BufferedReader(new InputStreamReader(System.in));
while(!"exit".equals(messageString)) {
    MqttMessage message = new MqttMessage();
        try {
            messageString = user.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    message.setPayload(messageString.getBytes());
    client.publish("iot_gps", message);
    System.out.println("\tMessage '"+ messageString +"' to 'iot_gps'");
  }
StringBuffer stringBuffer = new StringBuffer();
*/   