package com.oracle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oracle.entity.Information;
import com.oracle.entity.News;
import com.oracle.entity.PageBean;
import com.oracle.entity.Users;
import com.oracle.service.InformationService;
import com.oracle.service.NewsService;
import com.oracle.service.UserService;

@WebServlet("/searchUserServlet")
public class SearchUserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		//���������ѯ���� ������
		String username = request.getParameter("username");

		// ��������ҳ����,��һ������Ĭ������Ϊ��һҳ
		String s = request.getParameter("pc");
		if (s == null || s.trim().isEmpty()) {
			s = "1";
		}
		int pc = Integer.valueOf(s);
		
		// ����ps��ֵ��ÿҳ��ʾ������
		int ps = 5;

		PageBean<Users> pb = null;
		if (username == null) {// ��ѯ����Ա��
			pb = new UserService().getUserPageByParam(pc, ps,null);
		}else{
			String usernameParam = "%"+username+"%";
			pb = new UserService().getUserPageByParam(pc, ps, usernameParam);
			//ת����ҳ��
			request.setAttribute("username", username);
		}
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("searchuser.jsp").forward(request, response);
	}
	
	

}

