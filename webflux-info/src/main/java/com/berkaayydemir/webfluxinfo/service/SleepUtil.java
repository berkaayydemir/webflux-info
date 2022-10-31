package com.berkaayydemir.webfluxinfo.service;

public class SleepUtil {

    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds * 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
