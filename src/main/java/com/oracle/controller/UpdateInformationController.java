package com.oracle.controller;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.entity.Information;
import com.oracle.service.InformationService;

@WebServlet("/updateInfoServlet")
public class UpdateInformationController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		//��ȡҳ��Ĳ���
		int id = Integer.parseInt(req.getParameter("id"));
		String title =req.getParameter("title");
		String time =req.getParameter("time");
		String context =req.getParameter("context");
		Information info  = new Information();
		InformationService is = new InformationService();
		info.setId(id);
		info.setTitle(title);
		info.setTime(time);
		info.setContext(context);
		//�޸Ĵ�ҵ��̬
		is.updateInfo(info);
		//��ȡ���µ����ݣ���ʾ
		List<Information> infomations = is.getAllInfomation();
		req.setAttribute("infomations", infomations);
		req.getRequestDispatcher("listinformation.jsp").forward(req, resp);
		
	}

}
