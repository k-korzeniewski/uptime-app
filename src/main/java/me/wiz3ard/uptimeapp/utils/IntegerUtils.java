package me.wiz3ard.uptimeapp.utils;

public class IntegerUtils {
    public static int getFirstInteger(int integer){
        return Integer.parseInt(String.valueOf(integer).substring(0,1));
    }
}
