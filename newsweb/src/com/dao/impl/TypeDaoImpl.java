package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.TypeDao;
import com.pojo.Type;
import com.pojo.User;
import com.utils.BaseDao;
import com.utils.ConnectionUtils;
public class TypeDaoImpl extends BaseDao implements TypeDao{


	public int insert(Type t) {
		String sql="insert into n_type values(null,?,?)";
		return executeUpdate(sql,t.getTname(),t.getTpid());
	}


	public int update(Type t) {
		String sql="update n_type set tname=?,tpid=? where tid=?";
		
		return executeUpdate(sql,t.getTname(),t.getTpid(),t.getTid());
	}


	public int delete(Integer id) {
		String sql="delete from n_type where tid=? ";
		return executeUpdate(sql,id);
	}

	
	public Type findById(Integer id) {
		String sql="select* from n_type where tid=?";
		PreparedStatement pstmt=null;
		ResultSet rt=null;
		Type t=null;
		Connection conn=ConnectionUtils.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rt=pstmt.executeQuery();
			if(rt.next()){
				 t=new Type();
				 t.setTid(rt.getInt("tid"));
				 t.setTname(rt.getString("tname"));
				 t.setTpid(rt.getInt("tpid"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;
	}

	
	public List<Type> findAll() {
		String sql="select* from n_type";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Type t=null;
		List<Type> list=new ArrayList<>();
		Connection conn=ConnectionUtils.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				t=new Type();
				t.setTid(rs.getInt("tid"));
				t.setTname(rs.getString("tname"));
				t.setTpid(rs.getInt("tpid"));
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	public Object TableToClass(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	
} 
