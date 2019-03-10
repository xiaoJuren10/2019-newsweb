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
		//����web������tomcat������·��
		String savePath=getServletContext().getRealPath("/")+"upload/";
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		//1.����һ������
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//2.����һ���ϴ��ļ�������
		ServletFileUpload upload=new ServletFileUpload();
		//3.�ַ�����
		upload.setHeaderEncoding("utf-8");
		upload.setFileSizeMax(1024*1024*100);
		InputStream in=null;
		FileOutputStream fout=null;
		//4.�жϱ����Ƿ�����ϴ��ļ���û�о��˳�
		if(!upload.isMultipartContent(request)){
			return;
		}
		String desc=null;
		String name;
		String newFileName="";//�ϴ��ļ���������
		String fileName="";//�ϴ��ļ�ԭ��
		try {
			//5.�õ�����ÿһ����Ԫ��[2��Ԫ��  type=text   type=file]
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list){
				if(item.isFormField()){//true  ��ʾ���itemΪ��Ԫ��
					  
					System.out.println(item.getFieldName()+","+item.getString("UTF-8"));
					if("desc".equals(item.getFieldName())){
						desc=item.getString("UTF-8");
					}
					if("name".equals(item.getFieldName())){
						name=item.getString("UTF-8");
					}
				}else{//����ϴ��ļ�  aaa.jpg
					 fileName=item.getName();//�õ��ϴ����ļ�����
					 String fileExt=fileName.substring(fileName.lastIndexOf(".")+1);
					 newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					//���ݿɱ����Ŀ¼������һ���ļ�
					//��savePath����һ���յ��ļ����ļ��������ϴ����ļ�
					File dfile=new File(savePath,newFileName);
					try {
						item.write(dfile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//item.delete();
					//�õ�dfile�ϵ�һ���ļ������ fout.write(xxxx);
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
		
		//������������ӵ����ݿ���
		
		request.setAttribute("fileName", newFileName);
		request.getRequestDispatcher("uploadsuccess.jsp").forward(request, response);
		
	}


}


