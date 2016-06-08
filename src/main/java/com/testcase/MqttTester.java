package com.testcase;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by axe on 2016/6/7.
 */
public class MqttTester {

    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://127.0.0.1:1884", "test@client");

        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        options.setCleanSession(false);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);
        options.setUserName("test");
        options.setPassword("test".toCharArray());

      /*  client.connect(options);
        client.setCallback(new TestCallBack());

        client.subscribe("topic@test@client");*/
//        Timer timer = new Timer(true);
        TestTask task = new TestTask(client, options);
//        timer.schedule(task, 0, 25000);


        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(task, 0, 25, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (client.isConnected()) {
            for (int i = 0; i < 10000; i++) {
                try {
                    client.publish("topic@test@client", ("哈哈哈哈" + i).getBytes(), 1, false);
                } catch (Exception e) {
                    System.err.println("main is Exception : " + e.getMessage());
                }
            }
        }


        while (true) {
            if (client == null) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
