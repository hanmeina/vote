package com.xjtu.vote.domin;

//ͶƱIP��ʱ��
public class Info {
	 private int id;
	 private String ip;
	 private java.sql.Timestamp votetime;
	 private Address address;
	 public Info(){}
	 
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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
