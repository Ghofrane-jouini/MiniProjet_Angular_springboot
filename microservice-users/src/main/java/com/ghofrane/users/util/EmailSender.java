package com.ghofrane.users.util;

public interface EmailSender {
    void sendEmail(String toEmail, String body);
}