package com.project.notice;

public class NoticeDTO {
	
	private int nnum;

	private String title;
	private String writer;
	private String content;
	private String created;  
	
	private int preNum;  //�����Խù� ��ȣ 
	private String preTitle; //�����Խù� ���� 
	private int nextNum;	//�����Խù� ��ȣ 
	private String nextTitle; //���� �Խù� ���� 
	
	

	public int getPreNum() {
		return preNum;
	}
	public void setPreNum(int preNum) {
		this.preNum = preNum;
	}
	public int getNextNum() {
		return nextNum;
	}
	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}
	public String getPreTitle() {
		return preTitle;
	}
	public void setPreTitle(String preTitle) {
		this.preTitle = preTitle;
	}
	public String getNextTitle() {
		return nextTitle;
	}
	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}
	
	
	
	public int getNnum() {
		return nnum;
	}
	public void setNnum(int nnum) {
		this.nnum = nnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

	
	
	

}
