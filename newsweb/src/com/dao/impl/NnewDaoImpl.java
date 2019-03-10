package com.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.NnewDao;
import com.pojo.Nnew;
import com.utils.BaseDao;
import com.utils.ConnectionUtils;

public class NnewDaoImpl extends BaseDao implements NnewDao {

	@Override
	public int insert(Nnew t) {
		String sql="insert into n_news (ntitle,nauthor,ndate,ncontent,"
				+ "tid) values (?,?,?,?,?)";
		java.sql.Date date=new java.sql.Date(t.getNdate().getTime());
		return  executeUpdate(sql,t.getNtitle(),t.getNauthor(),date,t.getNcontent(),t.getTid());
	}


	@Override
	public int update(Nnew t) {
		String sql="update n_news set ntitle=?,nauthor=?,ndate=?,ncontent=?"
				+ "tid=? where nid=?";
		java.sql.Date date=new java.sql.Date(t.getNdate().getTime());
		
		return executeUpdate(sql,t.getNtitle(),t.getNauthor(),date,t.getNcontent(),t.getTid(),t.getNid());
	}

	@Override
	public int delete(Integer id) {
		String sql="delete from n_news where nid=?";
		return executeUpdate(sql,id);
	}

	@Override
	public Nnew findById(Integer id) {
		String sql="select* from n_news where nid=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection conn=ConnectionUtils.getConn();
		Nnew n=null;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				n=new Nnew();
				n.setNtitle(rs.getString("ntitle"));
				n.setNauthor(rs.getString("nauthor"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNdate(new Date(rs.getDate("ndate").getTime()));
				n.setTid(rs.getInt("tid"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			ConnectionUtils.close(conn, pstmt, rs);
		}
		
		return n;
	}

	@Override
	public List<Nnew> findAll() {
		String sql="select* from n_news ";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Nnew> list=new ArrayList<>();
		Connection conn=ConnectionUtils.getConn();
		Nnew n=null;
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				n=new Nnew();
				n.setNid(rs.getInt("nid"));
				n.setNtitle(rs.getString("ntitle"));
				n.setNauthor(rs.getString("nauthor"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNdate(new Date(rs.getDate("ndate").getTime()));
				n.setTid(rs.getInt("tid"));
				n.setNum1(rs.getInt("num1"));
				n.setNum2(rs.getInt("num2"));
				list.add(n);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			ConnectionUtils.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public Object TableToClass(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
