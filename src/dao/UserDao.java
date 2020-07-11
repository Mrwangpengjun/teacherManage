package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import utils.DBUtils;

public class UserDao {
	/**
	 * 登录方法的实现
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password,int type) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select * from user where username=? and password=? and type = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			pstm.setInt(3, type);
			rs = pstm.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setType(rs.getInt("type"));
			}
			pstm.close();
			rs.close();
			conn.close();
		} catch (SQLException s) {
			s.printStackTrace();
			
		}
		return user;
	}
	
	public boolean save(User entity) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtils.getConn();
			String sql = " insert into user(username,password,type) values(?,?,?) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, entity.getUsername());
			pstm.setString(2, entity.getPassword());
			pstm.setInt(3, entity.getType());
			boolean flag = pstm.executeUpdate()>0;
			pstm.close();
			conn.close();
			return flag;				
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}
	
	public boolean update(User entity) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtils.getConn();
			String sql = " update user set username = ?,password = ?,type = ? where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, entity.getUsername());
			pstm.setString(2, entity.getPassword());
			pstm.setInt(3, entity.getType());
			pstm.setInt(4, entity.getId());
			boolean flag = pstm.executeUpdate()>0;
			pstm.close();
			conn.close();
			return flag;				
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtils.getConn();
			String sql = " delete from user where id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			boolean flag = pstm.executeUpdate()>0;
			pstm.close();
			conn.close();
			return flag;				
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}
	
	public User getById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User entity = null;
		try {
			conn = DBUtils.getConn();
			String sql = " select * from user where id=";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				//获取实体类信息
				entity = new User();
				entity.setId(rs.getInt("id"));
				entity.setUsername(rs.getString("username"));
				entity.setPassword(rs.getString("password"));
				entity.setType(rs.getInt("type"));
			}
			pstm.close();
			rs.close();
			conn.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return entity;
	}
	
	public List<User> queryAllByPage(String username,int type) {
		List<User> list= new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " select * from user where 1=1 ";
			if(username!="") {
				sql+=" and username = ?";
			}
			if(type!=0) {
				sql+=" and type = ?";
			}
			pstm = conn.prepareStatement(sql);	
			if(username!="") {
				pstm.setString(1, username);
				if(type!=0) {
					pstm.setInt(2, type);
				}
			} else {
				if(type!=0) {
					pstm.setInt(1, type);
				}
			}
			sql += " order by id asc";
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				User entity = new User();
				entity.setId(rs.getInt("id"));
				entity.setUsername(rs.getString("username"));
				entity.setPassword(rs.getString("password"));
				entity.setType(rs.getInt("type"));
				list.add(entity);
			}
			pstm.close();
			rs.close();
			conn.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return list;
	}
}
