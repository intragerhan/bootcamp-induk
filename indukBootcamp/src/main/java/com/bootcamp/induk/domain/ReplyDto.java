package com.bootcamp.induk.domain;

import java.util.Date;
import java.util.Objects;

public class ReplyDto {
	private Integer cno;
	private Integer bno;
	private Integer pcno;
	private String reply;
	private String replier;
	private Date reg_date;
	private Date up_date;
	
	public ReplyDto() {}
	public ReplyDto(Integer bno, Integer pcno, String reply, String replier) {
		super();
		this.bno = bno;
		this.pcno = pcno;
		this.reply = reply;
		this.replier = replier;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bno, cno, reply, replier, pcno);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ReplyDto other = (ReplyDto) obj;
		return Objects.equals(cno, other.cno) 
				&& Objects.equals(bno, other.bno)
				&& Objects.equals(pcno, other.pcno)
				&& Objects.equals(reply, other.reply)
				&& Objects.equals(replier, other.replier);
	}
	
	@Override
	public String toString() {
		return "ReplyDto [" +
				"cno=" + cno +
				", bno=" + bno +
				", pcno=" + pcno + 
				", reply=" + reply + 
				", replier=" + replier +
				", reg_date=" + reg_date +
				", up_date=" + up_date +
				']';
	}
	
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public Integer getPcno() {
		return pcno;
	}
	public void setPcno(Integer pcno) {
		this.pcno = pcno;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplier() {
		return replier;
	}
	public void setReplier(String replier) {
		this.replier = replier;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	
}
