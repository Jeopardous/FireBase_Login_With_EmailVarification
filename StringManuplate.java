package com.example.adarsh.lovelyface.Extra;

public class StringManuplate {
    public static  String expendUsername(String username)
    {
        return username.replace("."," ");
    }
    public static  String condenseUsername(String username)
    {
        return username.replace(" ",".");
    }
}
