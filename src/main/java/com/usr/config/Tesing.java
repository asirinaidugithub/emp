package com.usr.config;

public class Tesing {
    public static void main(String[] args) {
        String name="welcome tojungleWelcomeWELCOME".toUpperCase();
        String input="Welcome".toUpperCase();
        int strCount=0;
        int recentIndex=0;
        while (recentIndex!=-1){
            recentIndex=name.indexOf(input, recentIndex);
            if(recentIndex!=-1){
                strCount++;
                recentIndex+=input.length();
            }
        }

        System.out.println(input+" count "+strCount);

    }
}
