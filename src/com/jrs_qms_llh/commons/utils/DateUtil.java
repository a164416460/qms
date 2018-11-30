package com.jrs_qms_llh.commons.utils;

import java.util.Calendar;

public final class DateUtil {
    /**
     * TODO 注意：月份从零开始
     * 获得一个月有多少天的方法
     *
     * @param year
     * @param month
     * @return
     */
    public static final int monthHasDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getActualMaximum(Calendar.DATE);
    }
}
