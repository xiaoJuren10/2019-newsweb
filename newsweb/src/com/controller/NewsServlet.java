package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NnewDao;
import com.dao.TypeDao;
import com.dao.impl.NnewDaoImpl;
import com.dao.impl.TypeDaoImpl;
import com.pojo.Nnew;
import com.pojo.Type;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		
		if("toInsert".equals(method)){
			this.doToInsert(request, response);
		}
		if("insert".equals(method)){
			this.doInsert(request, response);
		}
		if("list".equals(method)){
			this.doList(request, response);
		}
		if("delete".equals(method)){
			this.doDelete(request, response);
		}
		if("find".equals(method)){
			this.doFind(request, response);
		}
		if("update".equals(method)){
			this.doUpdate(request, response);
		}
	}
	
	protected void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tid=Integer.parseInt(request.getParameter("tid"));
		String title=request.getParameter("ntitle");
		String ndate=request.getParameter("ndate");
		String author=request.getParameter("nauthor");
		String content=request.getParameter("ncontent");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			 date=sdf.parse(ndate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		Nnew n=new Nnew(tid,title,author,date,content);
		NnewDao nnewDao=new NnewDaoImpl();
		nnewDao.insert(n);
		response.sendRedirect("NewsServlet?method=list");
	}
	
	protected void doToInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TypeDao typeDao=new TypeDaoImpl();
		List<Type> tlist=typeDao.findAll();
		request.setAttribute("tlist", tlist);
		request.getRequestDispatcher("news/inserNews.jsp").forward(request, response);
	}
	
	protected void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NnewDao newsDao=new NnewDaoImpl();
		List<Nnew> list=newsDao.findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("news/list.jsp").forward(request, response);
	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nid=Integer.parseInt(request.getParameter("nid"));
		NnewDao newsDao=new NnewDaoImpl();
		newsDao.delete(nid);
		response.sendRedirect("NewsServlet?method=list");
	
	}
	
	protected void doFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nid=Integer.parseInt(request.getParameter("nid"));
		NnewDao newsDao=new NnewDaoImpl();
		TypeDao typeDao=new TypeDaoImpl();
		Nnew news=newsDao.findById(nid);
		request.setAttribute("tlist", typeDao.findAll());
		request.setAttribute("news", news);
		request.getRequestDispatcher("news/updateNews.jsp").forward(request, response);
	
	}
	
	protected void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int nid=Integer.parseInt(request.getParameter("nid"));
		int tid=Integer.parseInt(request.getParameter("tid"));
		String title=request.getParameter("ntitle");
		String ndate=request.getParameter("ndate");
		String author=request.getParameter("nauthor");
		String content=request.getParameter("ncontent");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			 date=sdf.parse(ndate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		Nnew n=new Nnew(tid,title,author,date,content);
		//n.setNid(nid);
		NnewDao nnewDao=new NnewDaoImpl();
		nnewDao.update(n);
		response.sendRedirect("NewsServlet?method=list");
	}
}
