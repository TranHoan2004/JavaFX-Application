package com.he180030.agentmanagement.utils;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidation {
    @NotNull
    public static Date convertToDate(@NotNull String string) throws ParseException {
        // ensure the input's size follows the format dd//MM/yyyy
        String[] analysis = string.split("/");
        analysis[0] = analysis[0].length() == 1 ? analysis[0] = "0" + analysis[0] : analysis[0];
        analysis[1] = analysis[1].length() == 1 ? analysis[1] = "0" + analysis[1] : analysis[1];
        string = analysis[0] + "/" + analysis[1] + "/" + analysis[2];

        // ensure the input follows the format dd//MM/yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        return formatter.parse(string);
    }

    @NotNull
    public static String convertDateToString(@NotNull Date date) throws ParseException {
        // ensure the input follows the format dd//MM/yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        return formatter.format(date);
    }
}
