package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.UserDao;
import com.pojo.User;
import com.utils.BaseDao;
import com.utils.ConnectionUtils;

public class UserDaoImpl extends BaseDao implements UserDao {
	
	public int insert(User user){
		String sql="insert into n_user values(null,?,?,?,?)";
		java.sql.Date date=new java.sql.Date(user.getBirthday().getTime());
		return executeUpdate(sql,user.getUsername(),user.getUserpwd(),date,user.getEmail());
	}
	public int delete(Integer id) {
		String sql = "delete from n_user where uid=?";
		// Object[] params=new Object[]{id};
		return executeUpdate(sql, id);
	}
	public int update(User user) {
		String sql = "update n_user set username=?,userpwd=?,birthday=?,email=? where uid=?";
		java.sql.Date date = new java.sql.Date(user.getBirthday().getTime());
		return executeUpdate(sql, user.getUsername(), user.getUserpwd(), date, user.getEmail(), user.getUid());

	}
	public User login(String name, String pwd) {
		String sql = "select* from n_user where username=? and userpwd=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=ConnectionUtils.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setBirthday(new Date(rs.getDate("birthday").getTime()));
				user.setEmail(rs.getString("email"));
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtils.close(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List<User> findAll() {
		String sql = "select * from n_user";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		List<User> list = new ArrayList<>();
		Connection conn=ConnectionUtils.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setBirthday(new Date(rs.getDate("birthday").getTime()));
				user.setEmail(rs.getString("email"));
				list.add(user);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, pstmt,rs);
		}
		return list;
	}

	
	
	
	public User findById(Integer id) {
		String sql = "select * from n_user where uid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		Connection conn=ConnectionUtils.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setBirthday(new Date(rs.getDate("birthday").getTime()));
				user.setEmail(rs.getString("email"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, pstmt,rs);
		}
		return user;
	}
	
	public int getRows() {
		String sql = "select count(*) from n_user";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num=0;
		Connection conn=ConnectionUtils.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num=rs.getInt(1);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, pstmt,rs);
		}
		return num;
	}

	@Override
	public Object TableToClass(ResultSet rs) {
		return null;
	}

}
