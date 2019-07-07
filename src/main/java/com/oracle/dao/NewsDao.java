package com.oracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.entity.Information;
import com.oracle.entity.News;
import com.oracle.entity.PageBean;
import com.oracle.util.ConnUtil;

public class NewsDao {

	public static List<News> getNews() {
		List<News> news = new ArrayList<News>();
		Connection conn = null;
		News ns = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from news";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				ns = new News();
				ns.setId(rs.getInt("id"));
				ns.setTitle(rs.getString("title"));
				ns.setTime(rs.getString("time"));
				ns.setContext(rs.getString("context"));
				news.add(ns);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return news;
	}

	public static int deleteNews(int id) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "delete from news where id = ?";
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

	public static int addnnews(String title, String time, String context) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "insert into news values(null,?,?,?)";
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

	public static News getById(int id) {
		Connection conn = null;
		News ns1 = null;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "select * from news where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				ns1 = new News();
				ns1.setId(rs.getInt("id"));
				ns1.setTitle(rs.getString("title"));
				ns1.setTime(rs.getString("time"));
				ns1.setContext(rs.getString("context"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return ns1;
	}

	public static int updateN(News nss) {
		Connection conn = null;
		int count = 0;
		//��ȡ����
		try {
			conn = ConnUtil.getConn();
			String sql = "update news set title = ?,time=?,context=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, nss.getTitle());
			pstm.setString(2, nss.getTime());
			pstm.setString(3, nss.getContext());
			pstm.setInt(4, nss.getId());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ݿ�
		return count;
	}

	public static List<News> getFiveNews() {
		//ctrl+shift+o
				List<News> newsss = new ArrayList<News>();
				Connection conn = null;
				News ns1 = null;
				//��ȡ����
				try {
					conn = ConnUtil.getConn();
					//limit 1,5  1Ϊ��һҳ   5Ϊÿҳ��ʾ5����¼
					String sql = "select * from news limit 1,5";
					PreparedStatement pstm = conn.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()){
						ns1 = new News();
						ns1.setId(rs.getInt("id"));
						ns1.setTitle(rs.getString("title"));
						ns1.setTime(rs.getString("time"));
						ns1.setContext(rs.getString("context"));
						newsss.add(ns1);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//�������ݿ�
				return newsss;
	}

	public static PageBean<News> getNewsBypage(int pc, int ps, String title) {
		int len = findNum();
		//ʵ������ǰҳ�����
		PageBean<News> pn = new PageBean<News>();
		pn.setPc(pc);//��ǰҳ�� ���� ��һҳ Ϊ 1,�ڶ�ҳ Ϊ 2 
		pn.setPs(ps);//ÿҳ��¼�� ����ÿҳ��ʾ 5 ��
		pn.setTr(len);//�ܼ�¼��  ����  �ܹ���Ϊ 9 ��
		String sql = "";
		List<News> newssn = new ArrayList<News>();
		News ne = null;
		if(title == null){
			sql = "select * from news limit ?,?";
		}else{
			sql = "select * from news where title like ? limit ?,?";
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
				ne = new News();
				ne.setId(rs.getInt("id"));
				ne.setTitle(rs.getString("title"));
				ne.setTime(rs.getString("time"));
				ne.setContext(rs.getString("context"));
				newssn.add(ne);
			}
			pn.setBeanList(newssn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pn;
	}
	
	public static int findNum() {
		Connection conn = null;
		int count = 0;
		try {
			conn = ConnUtil.getConn();
			String sql = "select count(*) as count from news";
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
