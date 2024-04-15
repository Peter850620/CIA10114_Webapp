package com.food.model;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Photo
 */
@MultipartConfig
@WebServlet("/photo")
public class Photo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		Part photo = request.getPart("photo");
		String filename = photo.getSubmittedFileName();

		try (InputStream in = photo.getInputStream();
				OutputStream out1 = new FileOutputStream("C:\\workspace\\output_file\\" + filename);) {
			out1.write(in.readAllBytes());
		} catch (Exception e) {
			e.getStackTrace();
		}

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Show Headers</title>");
		out.print("</head>");
		out.print("<body>");

		out.println("文件名稱:  " + filename);
		out.println("上傳成功");

		out.print("</body>");
		out.print("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
