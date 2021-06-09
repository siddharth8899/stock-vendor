package com.example.demo.service;


import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.example.demo.pojo.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
public class SmsService {
    private final String ACCOUNT_SID = "AC553035a4bc0999f46ac594b777776491";
    private final String AUTH_TOKEN = "4a2bf824f7b9e0700491659b83220a3e";
    private final String FROM_NUMBER = "+14243451414";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage()).create();
        System.out.println("here is my id:" + message.getSid());// Unique resource ID created to manage this transaction
    }
    public void receive(MultiValueMap<String, String> smscallback) {
    }
}