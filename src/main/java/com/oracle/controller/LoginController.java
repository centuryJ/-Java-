package com.oracle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.entity.Users;
import com.oracle.service.UserService;
//��ͼ��login.jsp ���������ݸ�������
//����servlet ������,ע��
@WebServlet(name="login",urlPatterns="/loginservlet")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������������
		request.setCharacterEncoding("utf-8");
		//��ȡҳ��login.jsp���ݵĲ���
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("���Բ����Ƿ���ȷ");
		System.out.println(username + password);
		//����������ҵ���
		UserService us = new UserService();
		Users u = us.login(username, password);
		if(u!=null){
			request.getSession().setAttribute("u", u);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			request.setAttribute("info", "�û��˺Ż��������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	
	

}
