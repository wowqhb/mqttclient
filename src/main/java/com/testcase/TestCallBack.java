package com.testcase;

import org.eclipse.paho.client.mqttv3.*;

/**
 * Created by axe on 2016/6/7.
 */
public class TestCallBack implements MqttCallback {

    public void connectionLost(Throwable cause) {
        System.out.println("connectionLost = { cause = [" + cause + "] }");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("messageArrived = { topic = [" + topic + "], message = [" + message + "] }");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete = { token = [" + token.getClient().getClientId() + "|" + token.getTopics()[0] + "] }");
    }
}
