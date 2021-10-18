package com.example.library.utill;

import java.util.regex.Pattern;

public class Validation {

    public boolean isValidTitle(String title) {
        String ePattern = "^.*([a-z0-9A-Z]+)";
        Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(title);
        return m.matches();
    }

    public boolean isValidAuthor(String author) {
        String ePattern = "^.*([a-z0-9A-Z]+)";
        Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(author);
        return m.matches();
    }

    public boolean isValidQuantity(int quantity) {
        String ePattern = "^.*([a-z0-9A-Z]+)";
        Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(String.valueOf(quantity));
        return m.matches();
    }

    public boolean isValidName(String name) {
        String ePattern = "^.*([a-z0-9A-Z_-]+)";
        Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(String.valueOf(name));
        return m.matches();
    }

    public boolean isValidSurname(String surname) {
        String ePattern = "^.*([a-z0-9A-Z_-]+)";
        Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(String.valueOf(surname));
        return m.matches();
    }


    public boolean isValidPhone(int phone) {
        String ePattern = "^.*([a-z0-9A-Z_-]+)";
        Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(String.valueOf(phone));
        return m.matches();
    }

}
