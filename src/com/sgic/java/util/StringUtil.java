package com.sgic.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    // Method to validate email address

    public static String validateEmail(String email) {

        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (email.matches(regex)) {

            return null; // Email is valid

        } else {

            return "Email is Invalid";
        }
    }

    // Method to change null to empty string

    public static String changeNullToString(String input) {
      
        return (input == null) ? "" : input;
    
    }

    // Method to convert Date in String to Date in 'DD-MM-YYYY' format

    public static Date convertStringToDate(String dateString) throws ParseException {
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
        return dateFormat.parse(dateString);
    
    }

    // Generic method to add two numbers of any numeric type
    
    @SuppressWarnings("unchecked")
    
    public static <T extends Number> T addNumbers(T num1, T num2) {
    
        if (num1 instanceof Integer) {
    
            return (T) Integer.valueOf(num1.intValue() + num2.intValue());
    
        } else if (num1 instanceof Double) {
    
            return (T) Double.valueOf(num1.doubleValue() + num2.doubleValue());
    
        } else if (num1 instanceof Float) {
    
            return (T) Float.valueOf(num1.floatValue() + num2.floatValue());
    
        } else if (num1 instanceof Long) {
    
            return (T) Long.valueOf(num1.longValue() + num2.longValue());
    
        } else {
    
            throw new IllegalArgumentException("Unsupported numeric type");
    
        }
    
    }

    public static void main(String[] args) {
    
        // Testing the code
    
        //check the email validate
        System.out.println(validateEmail("testex@ample.com"));
        System.out.println(validateEmail("testexample.com"));

        //test string or empty
        System.out.println(changeNullToString("hello"));
        System.out.println(changeNullToString(""));
        
        // date
        try {
            Date date = convertStringToDate("19-02-2024");
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // add two numbers
        int sumInt = addNumbers(5, 10);
        System.out.println("Sum of integers: " + sumInt);

        double sumDouble = addNumbers(3.5, 7.2);
        System.out.println("Sum of doubles: " + sumDouble);
    }
}
