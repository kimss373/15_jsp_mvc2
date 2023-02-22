package boardBasic.dto;

import java.util.Date;

public class BoardDTO {

	private int num;
	private String writer;
	private String email;
	private String subject;
	private String password;
	private Date enrollDt;
	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", writer=" + writer + ", email=" + email + ", subject=" + subject
				+ ", password=" + password + ", enrollDt=" + enrollDt + ", readCnt=" + readCnt + ", content=" + content
				+ "]";
	}
	private int readCnt;
	private String content;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
