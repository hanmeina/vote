package com.xjtu.vote.domin;

//ͷ��
public class Image {
	//ͷ����
	private int id;
	//ͷ��·��
	private String path;
	//ͷ������ĺ�ѡ����ϸ��Ϣ
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
