package com.bootcamp.induk.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
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
}
