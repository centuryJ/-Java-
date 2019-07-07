package com.oracle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/backlogoutservlet")
public class BackLogoutController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���session���ֵ���Ϣ,ת��¼��̨��ҳ��
		req.getSession().invalidate();
		resp.sendRedirect("backlogin.jsp");
	}
	

}
