package com.yiibai;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// String dir = System.getProperty("user.dir")+"/uploads";
		String dir = request.getSession().getServletContext().getRealPath("");
		System.out.println("Save to path: " + dir);
		MultipartRequest mr = new MultipartRequest(request, dir);
		// ���������ļ��������͵�����
		Enumeration files = mr.getFileNames();
		String fileName = "";
		String filePath = "";
		while (files.hasMoreElements()) {
			fileName = (String) files.nextElement();
			System.out.println("FileName = " + fileName);
			// �ô˷����õ��ϴ��ļ����������ļ����������fileNameָ�ļ��������͵�����
			filePath = mr.getFilesystemName(fileName);
			System.out.println("FilePath = " + filePath);
			// �˷����õ�һ���ļ����󣬴������ڷ������ϵ�fileName�ļ�
			File f = mr.getFile(fileName);
			if (null == f) {
				throw new ServletException("file is not exist");
			}
		}
		out.print("successfully uploaded");
		out.close();
	}

}
