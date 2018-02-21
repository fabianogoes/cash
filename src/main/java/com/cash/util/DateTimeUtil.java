package com.cash.util;

import java.util.Arrays;
import java.util.Calendar;

public class DateTimeUtil {

    static final String[] monthNameList = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };

    public static String getCurrentMonthName(){
        Calendar cal = Calendar.getInstance();
        String month = monthNameList[cal.get(Calendar.MONTH)];
        return month;
    }

    public static int getMonthNumber(String montnName) {
        return Arrays.asList(monthNameList).lastIndexOf(montnName);
    }

    public static String getMonthName(int monthNumber) {
        return monthNameList[monthNumber];
    }

}
