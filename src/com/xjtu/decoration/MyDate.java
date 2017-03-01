package com.xjtu.decoration;

import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;
//��Date���ѹ�ʱ��toLocaleString������ǿ
public class MyDate {
 private Date date;
 public MyDate(Date date){
	 this.date = date;
 }
 public String toLocaleString(){
	String msg = null;
	DateFormat dFormat = DateFormat.getDateTimeInstance(
			DateFormat.FULL, 
			DateFormat.MEDIUM, 
			Locale.CHINA);
	msg = dFormat.format(date);
	return msg;
 }
}
