package com.example.timezhou;
//adapter里面用到了一个时间戳转为指定格式的工具类
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {

	  public static String format(String format, String time) {
          String result = "";
          //格式化日期
          SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
          try {
                  Date date = df.parse(time);
                  SimpleDateFormat df1 = new SimpleDateFormat(format);
                  result = df1.format(date);
          } catch (ParseException e) {
                  e.printStackTrace();
          }
          return result;
  }
}
