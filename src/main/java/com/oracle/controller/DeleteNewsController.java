package com.oracle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.service.InformationService;
import com.oracle.service.NewsService;

@WebServlet("/deletenewsservlet")
public class DeleteNewsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		//����ҵ���
		NewsService dns = new NewsService();
		dns.deleteNewsById(id);
		//��ȡɾ�������������
		req.setAttribute("news", dns.getAllNews());
		req.getRequestDispatcher("listnews.jsp").forward(req, resp);
	}

}
