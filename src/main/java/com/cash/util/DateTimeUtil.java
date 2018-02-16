package com.cash.util;

import java.util.Calendar;

public class DateTimeUtil {

    static final String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };

    public static String getCurrentMonthName(){
        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        return month;
    }

}
