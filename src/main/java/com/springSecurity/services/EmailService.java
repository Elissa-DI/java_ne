package com.springSecurity.services;

public interface EmailService {
    public  void sendSimpleEmailMessage(String to ,String subject, String  text);
}
