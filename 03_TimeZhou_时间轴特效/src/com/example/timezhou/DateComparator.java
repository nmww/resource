package com.example.timezhou;

import java.util.Comparator;
//�����л��õ�һ���Ƚ�ʱ����Ĺ�����
public class DateComparator implements Comparator<DateText> {

    @Override
    public int compare(DateText lhs, DateText rhs) {
            return rhs.getDate().compareTo(lhs.getDate());
    }

}
