package com.bootcamp.induk.domain;

import java.util.Date;
import java.util.Objects;

public class BoardDto {
	private Integer bno;
	private String title;
	private String contents;
	private String writer;
	private int view_cnt;	// 조회수
	private int reply_cnt;	// 댓글 개수
	private Date reg_date;	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		BoardDto boardDto = (BoardDto) obj;
		return Objects.equals(bno, boardDto.bno) 
				&& Objects.equals(title, boardDto.title)
				&& Objects.equals(contents, boardDto.contents)
				&& Objects.equals(writer, boardDto.writer);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bno, title, contents, writer);
	}
	
	public BoardDto() { this("", "", ""); }
	public BoardDto(String title, String contents, String writer) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
	}
	
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "BoardDto "
				+ "[bno=" + bno 
				+ ", title=" + title + '\''
				+ ", contents=" + contents + '\''
				+ ", writer=" + writer + '\''
				+ ", view_cnt=" + view_cnt 
				+ ", reply_cnt=" + reply_cnt 
				+ ", reg_date=" + reg_date 
				+ ']';
	}
}
