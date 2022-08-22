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
public class ReplyDto {
	private Integer rno;
	private Integer bno;
	private Integer prno;
	private String reply;
	private String replier;
	private Date reg_date;
	private Date up_date;

	public ReplyDto(Integer bno, Integer prno, String reply, String replier) {
		super();
		this.bno = bno;
		this.prno = prno;
		this.reply = reply;
		this.replier = replier;
	}

}
