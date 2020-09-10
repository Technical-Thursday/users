package com.pracore.user.services;

public class SentryLog implements LogService {

    public SentryLog() {
        System.out.println(" Sentry: No Args Constructor");
    }

    public void log() {
        System.out.println("Sentry Logged");
    }

}
