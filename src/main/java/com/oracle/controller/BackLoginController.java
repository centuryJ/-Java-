package com.oracle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.entity.Employee;
import com.oracle.service.EmployeeService;
//��ͼ��login.jsp ���������ݸ�������
//����servlet ������,ע��
@WebServlet(name="backlogin",urlPatterns="/backloginservlet")
public class BackLoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������������
		request.setCharacterEncoding("utf-8");
		//��ȡҳ��login.jsp���ݵĲ���
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//����������ҵ���
		EmployeeService es = new EmployeeService();
		Employee emp = es.login(name, password);
		if(emp!=null){
			request.getSession().setAttribute("emp", emp);
			request.getRequestDispatcher("backframe.jsp").forward(request, response);

		}else{
			request.setAttribute("info", "Ա���˺Ż��������");
			request.getRequestDispatcher("backlogin.jsp").forward(request, response);
		}
		
	}
	
	

}

