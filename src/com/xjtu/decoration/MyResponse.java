package com.xjtu.decoration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

//��response�����װ��/��װ
public class MyResponse extends HttpServletResponseWrapper{
	private HttpServletResponse response;
	//����
	private ByteArrayOutputStream bout = new ByteArrayOutputStream();
	private PrintWriter pw;
	public MyResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	//��д���෽��,Ŀ���ǽ��ֽ������������ȥ[�ֽ�]
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyServletOutputStream(bout);
	}
	//��д���෽��,Ŀ���ǽ��ַ������������ȥ[�ַ�]
	public PrintWriter getWriter() throws IOException {
		pw = new PrintWriter(new OutputStreamWriter(bout,"UTF-8"));
		return pw;
	}
	//ȡ�û����е�����
	public byte[] getBuffer(){
		if(pw!=null){
			pw.flush();
		}
		return bout.toByteArray();
	}
}
//���л��湦��ServletOutputStream
 class MyServletOutputStream extends ServletOutputStream{
	private ByteArrayOutputStream bout;
	public MyServletOutputStream(ByteArrayOutputStream bout) {
		this.bout = bout;
	}
	public void write(int b) throws IOException {
	}
	public void write(byte[] bytes) throws IOException {
		//���ֽ����������д�뻺��
		bout.write(bytes);
		//ȷ�������ֽ��������ݽ��뻺��
		bout.flush();
	}
	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setWriteListener(WriteListener listener) {
		// TODO Auto-generated method stub
		
	}
	
}






