package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class BaseDao {
	public int executeUpdate(String	sql,Object...params){
		PreparedStatement pstmt=null;
		Connection conn=null;
		int result=0;
		
		try {
			conn=ConnectionUtils.getConn();
			pstmt=conn.prepareStatement(sql);
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1,params[i]);
				}
			}
			result=pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionUtils.close(conn,pstmt);
		}
		return result;
	}
	
	public abstract Object TableToClass(ResultSet rs);
}
