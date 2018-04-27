package com.example.timezhou;

import java.util.Comparator;
//主类中还用到一个比较时间戳的工具类
public class DateComparator implements Comparator<DateText> {

    @Override
    public int compare(DateText lhs, DateText rhs) {
            return rhs.getDate().compareTo(lhs.getDate());
    }

}
