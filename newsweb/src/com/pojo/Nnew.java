package com.pojo;

import java.util.Date;

public class Nnew {
	private Integer nid;
	private String ntitle;
	private String nauthor;
	private Date ndate;
	private String ncontent;
	private Integer tid;
	private String npic;
	private int num1;
	private int num2;
	
	
	public Nnew(){}
	
	public Nnew(Integer tid,String ntitle,String nauthor,Date ndate,String ncontent){
		this.tid=tid;
		this.ntitle=ntitle;
		this.nauthor=nauthor;
		this.ndate=ndate;
		this.ncontent=ncontent;
	}
	public Nnew(Integer nid,Integer tid,String ntitle,String nauthor,Date ndate,String ncontent){
		this.nid=nid;
		this.tid=tid;
		this.ntitle=ntitle;
		this.nauthor=nauthor;
		this.ndate=ndate;
		this.ncontent=ncontent;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public Date getNdate() {
		return ndate;
	}
	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getNpic() {
		return npic;
	}
	public void setNpic(String npic) {
		this.npic = npic;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	@Override
	public String toString() {
		return "Nnew [nid=" + nid + ", ntitle=" + ntitle + ", nauthor="
				+ nauthor + ", ndate=" + ndate + ", ncontent=" + ncontent
				+ ", tid=" + tid + ", npic=" + npic + ", num1=" + num1
				+ ", num2=" + num2 + "]";
	}
	
	
}
