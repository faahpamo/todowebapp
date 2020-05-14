/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.faimoh.todoapp.actions;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class Utilities {
    public static String parseRequestParameterWithDefault(String parameter, String def) {
        if(parameter == null || parameter.isBlank() || parameter.isEmpty()) {
            return def;
        }else {
            return parameter;
        }
    }
     public static int parseWithDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static Timestamp parseDateAndTime(String strDate, String strTime) {
        try {
           Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate+" "+strTime);
           return new Timestamp(dateTime.getTime());
        } catch (ParseException pe) {
            return null;
        }        
    }
}
