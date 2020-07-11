package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.User;
import utils.DBUtils;

public class CourseDao {
	
	public boolean save(Course entity) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtils.getConn();
			String sql = " insert into course(name,time,userid) values(?,?,?) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, entity.getName());
			pstm.setString(2, entity.getTime());
			pstm.setInt(3, entity.getUserid());
			boolean flag = pstm.executeUpdate()>0;
			pstm.close();
			conn.close();
			return flag;				
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}
	
	public boolean update(Course entity) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtils.getConn();
			String sql = " update course set name = ?,time = ?,userid = ? where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, entity.getName());
			pstm.setString(2, entity.getTime());
			pstm.setInt(3, entity.getUserid());
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
			String sql = " delete from course where id= ?";
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
	
	public Course getById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Course entity = null;
		try {
			conn = DBUtils.getConn();
			String sql = " select * from course where id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(2, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				//获取实体类信息
				entity = new Course();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setTime(rs.getString("time"));
				entity.setUserid(rs.getInt("userid"));
			}
			pstm.close();
			rs.close();
			conn.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return entity;
	}
	
	public List<Course> queryAllByPage(int userid) {
		List<Course> list= new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " select c.*,u.username from course c left join user u on c.userid = u.id where 1=1 ";
			if(userid!=0) {
				sql+=" and userid = ?";
			}
			pstm = conn.prepareStatement(sql);	
			if(userid!=0) {
				pstm.setInt(1, userid);
			}
			sql += " order by id asc";
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Course entity = new Course();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setTime(rs.getString("time"));
				entity.setUserid(rs.getInt("userid"));
				entity.setUsername(rs.getString("username"));
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
