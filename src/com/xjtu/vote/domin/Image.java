package com.xjtu.vote.domin;

//头像
public class Image {
	//头像编号
	private int id;
	//头像路径
	private String path;
	//头像关联的候选人详细信息
	private Content content;
	public Image(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
}
