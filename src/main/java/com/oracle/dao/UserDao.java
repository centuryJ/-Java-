package com.oracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.entity.News;
import com.oracle.entity.PageBean;
import com.oracle.entity.Users;
import com.oracle.util.ConnUtil;
//���ݷ��ʲ�
public class UserDao {
	
	public Users login(String username,String password){
		Connection conn = null;
		Users usr = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from users where username = ? and password = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				usr = new Users();
				usr.setId(rs.getInt("id"));
				usr.setUsername(rs.getString("username"));
				usr.setRealname(rs.getString("realname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return usr;
	}
	
	//ע��
	public int registerUser(String username,String password,String realname){
		
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "insert into users values(null,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			pstm.setString(3, realname);
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;	
	}


	public int deleteUser(int uid) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "delete from users where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;
	}
	
	public int deleteInfo(int id) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "delete from information where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;
	}

	public List<Users> findusers() {
		List<Users> users = new ArrayList<Users>();
		Connection conn = null;
		Users usr = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from users";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				usr = new Users();
				usr.setId(rs.getInt("id"));
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
				usr.setRealname(rs.getString("realname"));
				users.add(usr);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return users;
	}

	public Users finduserbyid(int uid) {
		Connection conn = null;
		Users usr = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from users where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				usr = new Users();
				usr.setId(rs.getInt("id"));
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
				usr.setRealname(rs.getString("realname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return usr;
	}

	public int updateuser(Users users) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "update users set username = ?,password=?,realname=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, users.getUsername());
			pstm.setString(2, users.getPassword());
			pstm.setString(3, users.getRealname());
			pstm.setInt(4, users.getId());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;
	}

	public static PageBean<Users> getUserBypage(int pc, int ps, String username) {
		int len = findNum();
		//ʵ������ǰҳ�����
		PageBean<Users> pu = new PageBean<Users>();
		pu.setPc(pc);//��ǰҳ�� ���� ��һҳ Ϊ 1,�ڶ�ҳ Ϊ 2 
		pu.setPs(ps);//ÿҳ��¼�� ����ÿҳ��ʾ 5 ��
		pu.setTr(len);//�ܼ�¼��  ����  �ܹ���Ϊ 9 ��
		String sql = "";
		List<Users> userh = new ArrayList<Users>();
		Users ue = null;
		if(username == null){
			sql = "select * from users limit ?,?";
		}else{
			sql = "select * from users where username like ? limit ?,?";
		}
		Connection conn = null;
		try {
			conn = ConnUtil.getConn();
			PreparedStatement pstm = conn.prepareStatement(sql);
			if(username == null){
				pstm.setInt(1,(pc-1)*ps);
				pstm.setInt(2,ps);
			}else{
				pstm.setString(1, username);
				pstm.setInt(2,(pc-1)*ps);
				pstm.setInt(3,ps);
			}
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				ue = new Users();
				ue.setId(rs.getInt("id"));
				ue.setPassword(rs.getString("password"));
				ue.setUsername(rs.getString("username"));
				ue.setRealname(rs.getString("realname"));
				userh.add(ue);
			}
			pu.setBeanList(userh);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pu;
	}

	
	public static int findNum() {
		Connection conn = null;
		int count = 0;
		try {
			conn = ConnUtil.getConn();
			String sql = "select count(*) as count from users";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
