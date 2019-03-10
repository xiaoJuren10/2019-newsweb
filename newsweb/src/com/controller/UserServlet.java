package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		if("insert".equals(method)){
			this.doInsert(request,response);
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
		if("list".equals(method)){
			this.doList(request, response);
		}
		
	}
	
	
	protected void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("name");
		if("get".equalsIgnoreCase(request.getMethod())){
			name=new String(name.getBytes("ios-8895-1"),"utf-8");
		}
		
		String pwd=request.getParameter("pwd");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			 date=spf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user=new User(name,pwd,date,email);
		UserDao userDao=new UserDaoImpl();
		int num=userDao.insert(user);
		if(num==1){
			response.sendRedirect("UserServlet?method=list");
		}else{
			PrintWriter out=response.getWriter();
			out.println("<script>alert('Î´Ìí¼Ó³É¹¦£¡');window.location.href='inserUser.jsp'</script>");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int uid=Integer.parseInt(request.getParameter("uid"));
		UserDao userDao=new UserDaoImpl();
		userDao.delete(uid);
		response.sendRedirect("UserServlet?method=list");
	}


	protected void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("uid"));
		String name=request.getParameter("username");
		if("get".equalsIgnoreCase(request.getMethod())){
			name=new String(name.getBytes("ios-8895-1"),"utf-8");
		}
		String pwd=request.getParameter("userpwd");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date=spf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user=new User(id,name,pwd,date,email);
		UserDao userDao=new UserDaoImpl();
		userDao.update(user);
		response.sendRedirect("UserServlet?method=list");
	}
	
	
	
	protected void doFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int uid=Integer.parseInt(request.getParameter("uid"));
		UserDao userDao=new UserDaoImpl();
		User user=userDao.findById(uid);
		request.setAttribute("user", user);
		request.getRequestDispatcher("users/updateUser.jsp").forward(request, response);
		
	}
	
	protected void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		UserDao userDao=new UserDaoImpl();
		List<User> list=userDao.findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("users/list.jsp").forward(request, response);
	}

}