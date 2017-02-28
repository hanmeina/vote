package com.xjtu.vote.domin;

//候选人
public class Vote {
	 //编号
	 private int id;
	 //姓名
	 private String content;
	 //票数
	 private int ticket = 1;
	 public Vote(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
}
