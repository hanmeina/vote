package com.xjtu.test;


import com.xjtu.decoration.MyDate;

public class DateTest {
 
 public static void main(String[] args) {
	 MyDate myDate  = new MyDate(new java.util.Date());
	 System.out.println(myDate.toLocaleString());
}
}
