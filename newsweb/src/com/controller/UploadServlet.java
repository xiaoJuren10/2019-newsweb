package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回web程序在tomcat中物理路径
		String savePath=getServletContext().getRealPath("/")+"upload/";
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		//1.创建一个工厂
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//2.创建一个上传文件解析器
		ServletFileUpload upload=new ServletFileUpload();
		//3.字符编码
		upload.setHeaderEncoding("utf-8");
		upload.setFileSizeMax(1024*1024*100);
		InputStream in=null;
		FileOutputStream fout=null;
		//4.判断表单中是否存在上传文件，没有就退出
		if(!upload.isMultipartContent(request)){
			return;
		}
		String desc=null;
		String name;
		String newFileName="";//上传文件的新名字
		String fileName="";//上传文件原名
		try {
			//5.得到表单中每一个表单元素[2个元素  type=text   type=file]
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list){
				if(item.isFormField()){//true  表示这个item为表单元素
					  
					System.out.println(item.getFieldName()+","+item.getString("UTF-8"));
					if("desc".equals(item.getFieldName())){
						desc=item.getString("UTF-8");
					}
					if("name".equals(item.getFieldName())){
						name=item.getString("UTF-8");
					}
				}else{//如果上传文件  aaa.jpg
					 fileName=item.getName();//得到上传那文件名称
					 String fileExt=fileName.substring(fileName.lastIndexOf(".")+1);
					 newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					//根据可保存的目录来构造一个文件
					//在savePath创建一个空的文件，文件名就是上传的文件
					File dfile=new File(savePath,newFileName);
					try {
						item.write(dfile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//item.delete();
					//得到dfile上的一个文件输出流 fout.write(xxxx);
					fout=new FileOutputStream(dfile);
					in=item.getInputStream();
					byte[] byts=new byte[1024];
					int len=0;
					while((len=in.read(byts))!=-1){
						fout.write(byts, 0, len);
					}
					fout.close();
					in.close();
				}
				
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将相关数据增加到数据库中
		
		request.setAttribute("fileName", newFileName);
		request.getRequestDispatcher("uploadsuccess.jsp").forward(request, response);
		
	}


}


