package com.xjtu.vote.domin;

//投票IP和时间
public class Info {
	 private int id;
	 private String ip;
	 private java.sql.Timestamp votetime;
	 public Info(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public java.sql.Timestamp getVotetime() {
		return votetime;
	}
	public void setVotetime(java.sql.Timestamp votetime) {
		this.votetime = votetime;
	}
}
