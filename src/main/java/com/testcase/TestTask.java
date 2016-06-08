package com.testcase;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.TimerTask;

/**
 * Created by axe on 2016/6/8.
 */
public class TestTask extends TimerTask {
    private MqttClient client;
    private MqttConnectOptions options;

    public TestTask(MqttClient client, MqttConnectOptions options) {
        this.client = client;
        this.options = options;
    }

    public void run() {
        System.out.println("client == null ? " + (this.client == null) + "; client.isConnected() ? " + client.isConnected());
        if (!this.client.isConnected()) {
            System.out.println("connect...");
            try {
                client.connect(options);
                client.setCallback(new TestCallBack());
                client.subscribe("topic@test@client");
                System.out.println("connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
