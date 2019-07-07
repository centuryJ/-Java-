package com.oracle.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.entity.Information;
import com.oracle.entity.PageBean;
import com.oracle.entity.Users;
import com.oracle.util.ConnUtil;

public class InformationDao {

	public static int addInformation(String title, String time, String context) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "insert into information values(null,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, time);
			pstm.setString(3, context);
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;	
	}

	public static List<Information> getInformation() {
		List<Information> infors = new ArrayList<Information>();
		Connection conn = null;
		Information infor = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from information";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				infor = new Information();
				infor.setId(rs.getInt("id"));
				infor.setTitle(rs.getString("title"));
				infor.setTime(rs.getString("time"));
				infor.setContext(rs.getString("context"));
				infors.add(infor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return infors;
	}

	public static int deleteInfo(int id) {
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

	public static Information getInfomation(int id) {
		Connection conn = null;
		Information info = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from information where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				info = new Information();
				info.setId(rs.getInt("id"));
				info.setTitle(rs.getString("title"));
				info.setTime(rs.getString("time"));
				info.setContext(rs.getString("context"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return info;
	}

	public static int UpdateInfo(Information info) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "update information set title = ?,time=?,context=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, info.getTitle());
			pstm.setString(2, info.getTime());
			pstm.setString(3, info.getContext());
			pstm.setInt(4, info.getId());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;
	}

	public static List<Information> getFiveInfos() {
		//ctrl+shift+o
		List<Information> infomations = new ArrayList<Information>();
		Connection conn = null;
		Information info = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			//limit 1,5  1Ϊ��һҳ   5Ϊÿҳ��ʾ5����¼
			String sql = "select * from information limit 1,5";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				info = new Information();
				info.setId(rs.getInt("id"));
				info.setTitle(rs.getString("title"));
				info.setTime(rs.getString("time"));
				info.setContext(rs.getString("context"));
				infomations.add(info);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return infomations;
	}


	

public static int findNum() {
	Connection conn = null;
	int count = 0;
	try {
		conn = ConnUtil.getConn();
		String sql = "select count(*) as count from information";
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

public static PageBean<Information> getInfoBypage(int pc, int ps, String title) {
	int len = findNum();
	//ʵ������ǰҳ�����
	PageBean<Information> pb = new PageBean<Information>();
	pb.setPc(pc);//��ǰҳ�� ���� ��һҳ Ϊ 1,�ڶ�ҳ Ϊ 2 
	pb.setPs(ps);//ÿҳ��¼�� ����ÿҳ��ʾ 5 ��
	pb.setTr(len);//�ܼ�¼��  ����  �ܹ���Ϊ 9 ��
	String sql = "";
	List<Information> infomations = new ArrayList<Information>();
	Information info = null;
	if(title == null){
		sql = "select * from information limit ?,?";
	}else{
		sql = "select * from information where title like ? limit ?,?";
	}
	Connection conn = null;
	try {
		conn = ConnUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		if(title == null){
			pstm.setInt(1,(pc-1)*ps);
			pstm.setInt(2,ps);
		}else{
			pstm.setString(1, title);
			pstm.setInt(2,(pc-1)*ps);
			pstm.setInt(3,ps);
		}
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			info = new Information();
			info.setId(rs.getInt("id"));
			info.setTitle(rs.getString("title"));
			info.setTime(rs.getString("time"));
			info.setContext(rs.getString("context"));
			infomations.add(info);
		}
		pb.setBeanList(infomations);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return pb;
}
}
