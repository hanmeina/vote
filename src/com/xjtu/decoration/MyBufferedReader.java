package com.xjtu.decoration;

import java.io.BufferedReader;
import java.io.IOException;
//装饰器模式,对BufferedReader进行包装
public class MyBufferedReader {
 private BufferedReader br;
 int index = 0;
 public MyBufferedReader(BufferedReader br){
	 this.br = br;
 }
 
 public String readLine() throws IOException{
	 String readLine  = null;
	 if((readLine = br.readLine())!=null){
		 index++;
		 readLine =   index+":" + readLine ;		 
	 }
	 return readLine;
 }
 public void close() throws IOException{
	 br.close();
 }
}
