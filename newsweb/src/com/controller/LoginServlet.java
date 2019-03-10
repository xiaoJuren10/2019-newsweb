package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.pojo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 //哈哈，改下看看——张三	
    public LoginServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("username");
		if("get".equalsIgnoreCase(request.getMethod())){
			name=new String(name.getBytes("ios-8859-1"),"UTF-8");
		}
		
		String pwd=request.getParameter("pwd");
		UserDao userDao=new UserDaoImpl();
		User user=userDao.login(name, pwd);
		if(user!=null){
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("UserServlet?method=list");
		}else{
			PrintWriter out=response.getWriter();
			out.print("<script>alert('ÓÃ»§Ãñ»òÃÜÂë´íÎó£¡');window.location.href='login.jsp';</script>");
		}
		
	}

}
