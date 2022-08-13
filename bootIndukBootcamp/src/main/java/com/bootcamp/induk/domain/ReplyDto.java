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
	private Integer pcno;
	private String reply;
	private String replier;
	private Date reg_date;
	private Date up_date;

	public ReplyDto(Integer bno, Integer pcno, String reply, String replier) {
		super();
		this.bno = bno;
		this.pcno = pcno;
		this.reply = reply;
		this.replier = replier;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bno, rno, reply, replier, pcno);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ReplyDto other = (ReplyDto) obj;
		return Objects.equals(rno, other.rno)
				&& Objects.equals(bno, other.bno)
				&& Objects.equals(pcno, other.pcno)
				&& Objects.equals(reply, other.reply)
				&& Objects.equals(replier, other.replier);
	}

}
