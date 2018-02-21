package com.cash.util;

import org.junit.Assert;
import org.junit.Test;

public class DateTimeUtilTest {


    @Test
    public void testGetMonthNumber() {
        Assert.assertEquals(0,  DateTimeUtil.getMonthNumber("January"));
        Assert.assertEquals(1,  DateTimeUtil.getMonthNumber("February"));
        Assert.assertEquals(2,  DateTimeUtil.getMonthNumber("March"));
        Assert.assertEquals(3,  DateTimeUtil.getMonthNumber("April"));
        Assert.assertEquals(4,  DateTimeUtil.getMonthNumber("May"));
        Assert.assertEquals(5,  DateTimeUtil.getMonthNumber("June"));
        Assert.assertEquals(6,  DateTimeUtil.getMonthNumber("July"));
        Assert.assertEquals(7,  DateTimeUtil.getMonthNumber("August"));
        Assert.assertEquals(8,  DateTimeUtil.getMonthNumber("September"));
        Assert.assertEquals(9,  DateTimeUtil.getMonthNumber("October"));
        Assert.assertEquals(10,  DateTimeUtil.getMonthNumber("November"));
        Assert.assertEquals(11,  DateTimeUtil.getMonthNumber("December"));
    }
    @Test
    public void testGetMonthName() {
        Assert.assertEquals("January",  DateTimeUtil.getMonthName(0));
        Assert.assertEquals("February",  DateTimeUtil.getMonthName(1));
        Assert.assertEquals("March",  DateTimeUtil.getMonthName(2));
        Assert.assertEquals("April",  DateTimeUtil.getMonthName(3));
        Assert.assertEquals("May",  DateTimeUtil.getMonthName(4));
        Assert.assertEquals("June",  DateTimeUtil.getMonthName(5));
        Assert.assertEquals("July",  DateTimeUtil.getMonthName(6));
        Assert.assertEquals("August",  DateTimeUtil.getMonthName(7));
        Assert.assertEquals("September",  DateTimeUtil.getMonthName(8));
        Assert.assertEquals("October",  DateTimeUtil.getMonthName(9));
        Assert.assertEquals("November",  DateTimeUtil.getMonthName(10));
        Assert.assertEquals("December",  DateTimeUtil.getMonthName(11));
    }

}
