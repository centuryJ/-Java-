package com.oracle.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.oracle.entity.Information;
import com.oracle.entity.News;
import com.oracle.entity.Users;
import com.oracle.service.InformationService;
import com.oracle.service.NewsService;
import com.oracle.service.UserService;
import com.oracle.util.ConnUtil;

public class MyTest {
	UserService us = new UserService();
	
	//ʹ�õ�Ԫע��,��������
	@Ignore//ע��ĺ���Ϊ�÷���test1()������
	@Test
	public void test1(){
		try {
			System.out.println(ConnUtil.getConn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//���Ե�¼
	@Ignore
	@Test
	public void test2(){
		
		System.out.println(us.login("zs", "123"));
		
	}
	//���Ի�ȡ���е��û�����
	@Ignore
	@Test
	public void test3(){
		List<Users> urs = us.findAllUsers();
		for (Users u : urs) {
			System.out.println(u.getId()+u.getUsername()+u.getPassword()+u.getRealname());
		}
		
	}

	@Ignore
	@Test
	public void test4(){
		InformationService is = new InformationService();
		List<Information> infos = is.getFiveInfos();
		for (Information u : infos) {
			System.out.println(u.getId());
		}
	}
	
	@Test
	public void test5(){
		NewsService test = new NewsService();
		List<News> testnews = test.getFiveInfos();
		for (News u : testnews) {
			System.out.println(u.getTime()+u.getId());
		}
	}
}
