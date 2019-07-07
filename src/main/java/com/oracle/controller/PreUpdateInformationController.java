package com.oracle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.entity.Information;
import com.oracle.entity.Information;
import com.oracle.service.InformationService;

@WebServlet("/preupdateinformationservlet")
public class PreUpdateInformationController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ����deleteinfomationservlet?id=${infomation.id }
		int id = Integer.parseInt(req.getParameter("id"));
		//����ҵ���
		InformationService is = new InformationService(); 
		//��ȡ��ǰ�ļ�¼
		Information info = is.getInfomationById(id);
		//�����޸ĵ�ҳ�沢����ʾ����
		req.setAttribute("info", info);
		req.getRequestDispatcher("updateinformation.jsp").forward(req, resp);
		
		
	}

}
