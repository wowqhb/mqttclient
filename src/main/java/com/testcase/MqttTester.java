package com.testcase;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;

import java.util.Properties;

/**
 * Created by axe on 2016/6/7.
 */
public class MqttTester {
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://127.0.0.1:1884", "test@client");
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("cause = [" + cause + "]");
            }

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("topic = [" + topic + "], message = [" + message + "]");
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("token = [" + token.getClient().getClientId() + "|" + token.getTopics()[0] + "]");
            }
        });
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        options.setCleanSession(false);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);
        options.setUserName("test");
        options.setPassword("test".toCharArray());
        client.connect(options);

        client.subscribe("topic@test@client");

        client.publish("topic@test@client", "哈哈哈哈".getBytes(), 1, false);
    }
}
