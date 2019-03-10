package com.pojo;

public class Type {
	private Integer tid;
	private  String tname;
	private Integer tpid;
	
	
	//构造方法
	public Type(){}
	
	public Type(String name){
		super();
		this.tname=name;
	}
	
	public Type(Integer tid, String tname, Integer tpid){
		super();
		this.tid=tid;
		this.tname=tname;
		this.tpid=tpid;
	}
	
	public Type( String tname, Integer tpid){
		super();
		
		this.tname=tname;
		this.tpid=tpid;
	}

	
	

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getTpid() {
		return tpid;
	}

	public void setTpid(Integer tpid) {
		this.tpid = tpid;
	}

	@Override
	public String toString() {
		return "Type [tid=" + tid + ", tname=" + tname + ", tpid=" + tpid + "]";
	}
	
	
	
	
}
