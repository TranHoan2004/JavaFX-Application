/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.he180030.agentmanagement.utils;


/**
 * @author 84941
 */
public class InputValidation {

    public static String removeUnnecessaryBlank(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    public static String removeAllBlank(String input) {
        return input.trim().replaceAll("\\s+", "");
    }

    public static String normalFormName(String input) {
        input = removeUnnecessaryBlank(input);
        String[] temp = input.split(" ");
        input = "";
        for (int i = 0; i < temp.length; i++) {
            input = String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                input += " ";
            }
        }
        return input;
    }

    public static String getNonEmptyString(String string) throws Exception {
        string = removeUnnecessaryBlank(string);
        if (string.equalsIgnoreCase("")) {
            throw new Exception("Please input Non-Empty String!!!");
        }
        return string;
    }

    // add a space after dot if the following sentence is connect with the dot
    public static String normalFormStringAfterDot(String input) {
        StringBuilder string = new StringBuilder();
        string.append(removeAllBlank(string.toString()));
        input = removeUnnecessaryBlank(input);
        input = String.valueOf(input.charAt(0)).toUpperCase() + input.substring(1);
        input = input.replaceAll("\\.\\s+", ".");
        string.append(input.charAt(0));
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == '.' && Character.isAlphabetic(input.charAt(i))) {
                string.append(" ");
                string.append(Character.toUpperCase(input.charAt(i)));
            } else {
                string.append(input.charAt(i));
            }
        }
        return string.toString();
    }

    public static int getInt(String string, String error, int min, int max) throws Exception {
        int input = Integer.parseInt(getStringByRegex(string, "[0-9]+", error));
        if (input >= min && input <= max) {
            return input;
        }
        throw new Exception("Out of range!");
    }

    public static String getStringByRegex(String string, String regex, String err) throws Exception {
        string = removeUnnecessaryBlank(string);
        if (string.isEmpty()) {
            throw new Exception("Not null!");
        } else if (string.matches(regex)) {
            return string;
        }
        throw new Exception(err);
    }

    public static String getEmail(String string) throws Exception {
        String regex = "^[A-Za-z](.*)([@]{1})(.{2,})(\\.)(.{2,})"; // beginning with an alphabet character
        return getStringByRegex(string, regex, "Please enter email with format <account name>@<domain>");
    }

    public static String getPhone(String string) throws Exception {
        String regex = "[0-9 ]+";
        String phoneNum = getStringByRegex(string, regex, "Please enter number only!!").replaceAll("\\s+", "");
        if (phoneNum.length() >= 10 && phoneNum.length() <= 11) {
            return phoneNum;
        }
        throw new Exception("Phone number must be at least 10 characters and at most 11 characters");
    }

    public static double getDouble(String string, String error, double min, double max) throws Exception {
        double input = Double.parseDouble(getStringByRegex(string, "[0-9]*\\.?[0-9]+", error));
        if (input < min || input > max) {
            throw new Exception("Out of range!");
        }
        return input;
    }

    public static boolean getFormattedPassword(String password) throws Exception {
        password = removeUnnecessaryBlank(password);
        if (password.length() < 8 || password.length() > 32) {
            throw new Exception("Password must have length between 8 and 32 characters");
        }
        return true;
    }

    public static void isRightEmailFormat(String email) throws Exception {
        if (!email.matches("^[A-Za-z](.*)([@]{1})(.{2,})(\\.)(.{2,})")) {
            throw new Exception("Must follow the format <account name>@<domain");
        }
    }

}
