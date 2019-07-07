package com.oracle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.entity.Employee;
import com.oracle.entity.PageBean;
import com.oracle.service.EmployeeService;

@WebServlet("/searchemployeeServlet")
public class SearchEmployeeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		//���������ѯ���� ������
		String name = request.getParameter("name");

		// ��������ҳ����,��һ������Ĭ������Ϊ��һҳ
		String s = request.getParameter("pc");
		if (s == null || s.trim().isEmpty()) {
			s = "1";
		}
		int pc = Integer.valueOf(s);
		
		// ����ps��ֵ��ÿҳ��ʾ������
		int ps = 5;

		PageBean<Employee> pb = null;
		if (name == null) {// ��ѯ����Ա��
			pb = new EmployeeService().getEmployeePageByParam(pc, ps,null);
		}else{
			String nameParam = "%"+name+"%";
			pb = new EmployeeService().getEmployeePageByParam(pc, ps, nameParam);
			//ת����ҳ��
			request.setAttribute("name", name);
		}
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("searchemployee.jsp").forward(request, response);
	}
	
	

}

