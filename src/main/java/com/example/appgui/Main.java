package com.example.appgui;




public class Main {
    public static void main(String[] args) {
        String a = "packet 11";
        String[] s = a.split("packet ");
        System.out.println(s[0]);
        System.out.println(s[1]);
    }
}