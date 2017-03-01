package com.xjtu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.xjtu.decoration.MyBufferedReader;

public class MyBufferedReaderTest {
  public static void main(String[] args) {
	
	try {
		  Reader reader = new FileReader(new File("d:\\1.txt"));
		  BufferedReader bufferedReader = new BufferedReader(reader);
		  MyBufferedReader myBufferedReader = new MyBufferedReader(bufferedReader);
		  try {
			  String line = null;
			while((line = myBufferedReader.readLine()) !=null){
				 
				System.out.println(line);
				  
			  }
			
			myBufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
}
}
