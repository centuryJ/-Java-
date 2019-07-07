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
import com.oracle.service.InformationService;
import com.oracle.service.NewsService;

@WebServlet("/searchNewsServlet")
public class SearchNewsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		//���������ѯ���� ������
		String title = request.getParameter("title");

		// ��������ҳ����,��һ������Ĭ������Ϊ��һҳ
		String s = request.getParameter("pc");
		if (s == null || s.trim().isEmpty()) {
			s = "1";
		}
		int pc = Integer.valueOf(s);
		
		// ����ps��ֵ��ÿҳ��ʾ������
		int ps = 5;

		PageBean<News> pb = null;
		if (title == null) {// ��ѯ����Ա��
			pb = new NewsService().getNewsPageByParam(pc, ps,null);
		}else{
			String titleParam = "%"+title+"%";
			pb = new NewsService().getNewsPageByParam(pc, ps, titleParam);
			//ת����ҳ��
			request.setAttribute("title", title);
		}
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("searchnews.jsp").forward(request, response);
	}
	
	

}

