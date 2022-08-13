package com.bootcamp.induk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BoardDto {
	private Integer bno;
	private String title;
	private String contents;
	private String writer;
	private int view_cnt;	// 조회수
	private int reply_cnt;	// 댓글 개수
	private Date reg_date;
	
	public BoardDto(String title, String contents, String writer) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
	}
}
