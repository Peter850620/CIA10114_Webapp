package com.food.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.food.model.FoodJDBCDAO;
import com.food.model.FoodService;
import com.food.model.FoodVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/*************************** 開始查詢資料 ****************************************/
			FoodJDBCDAO dao = new FoodJDBCDAO();
			List<FoodVO> list = dao.getAll();

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list); // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/emp/listAllEmp2_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("food_id");
			if (str == null || (str.trim()).length() == 0) { // --> p60
				errorMsgs.add("請輸入食物編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer food_id = null;
			try {
				food_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("食物編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FoodJDBCDAO dao = new FoodJDBCDAO();
			FoodVO foodVO = dao.findByPrimaryKey(Integer.valueOf(req.getParameter("food_id")));
			if (foodVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("foodVO", foodVO); // 資料庫取出的empVO物件,存入req
			String url = "/food/listOneFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFood.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer food_id = Integer.valueOf(req.getParameter("food_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			FoodService foodSvc = new FoodService();
			FoodVO foodVO = foodSvc.getOneFood(food_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("foodVO", foodVO); // 資料庫取出的foodVO物件,存入req
			String url = "/food/update_food_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer food_id = Integer.valueOf(req.getParameter("food_id").trim());

			String food_name = req.getParameter("food_name");
			String food_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
			if (food_name == null || food_name.trim().length() == 0) {
				errorMsgs.add("食物名稱: 請勿空白");
			} else if (!food_name.trim().matches(food_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食物名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
			}

			String food_details = req.getParameter("food_details").trim();
			if (food_details == null || food_details.trim().length() == 0) {
				errorMsgs.add("食物描述請勿空白");
			}

			// 修改圖片			
			byte[] food_pic = null;
			Part photo = req.getPart("food_pic");
			InputStream in = photo.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			food_pic = outputStream.toByteArray();
			
			Integer food_price = null;
			try {
				food_price = Integer.valueOf(req.getParameter("food_price").trim());
			} catch (NumberFormatException e) {
				food_price = 0;
				errorMsgs.add("價格請填數字.");
			}

			String food_status = req.getParameter("food_status");

			FoodVO foodVO = new FoodVO();
			foodVO.setFood_id(food_id);
			foodVO.setFood_name(food_name);
			foodVO.setFood_details(food_details);
			foodVO.setFood_pic(food_pic);
			foodVO.setFood_price(food_price);
			foodVO.setFood_status(food_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("foodVO", foodVO); // 含有輸入格式錯誤的foodVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/food/update_food_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			FoodService foodSvc = new FoodService();
			foodVO = foodSvc.updateFood(food_id, food_name, food_details, food_price, null, food_status);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("foodVO", foodVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/food/listOneFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFood.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addFoodEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String food_name = req.getParameter("food_name");
			String food_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
			if (food_name == null || food_name.trim().length() == 0) {
				errorMsgs.add("食物名稱: 請勿空白");
			} else if (!food_name.trim().matches(food_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食物名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
			}

			String food_details = req.getParameter("food_details").trim();
			if (food_details == null || food_details.trim().length() == 0) {
				errorMsgs.add("食物描述請勿空白");
			}

			// 新增圖片
			byte[] food_pic = null;
			Part photo = req.getPart("food_pic");
			InputStream in = photo.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			food_pic = outputStream.toByteArray();

			Integer food_price = null;
			try {
				food_price = Integer.valueOf(req.getParameter("food_price").trim());
			} catch (NumberFormatException e) {
				food_price = 0;
				errorMsgs.add("價格請填數字." + req.getParameter("food_pic"));
			}

			String food_status = req.getParameter("food_status");

			FoodVO foodVO = new FoodVO();
			foodVO.setFood_name(food_name);
			foodVO.setFood_details(food_details);
			foodVO.setFood_pic(food_pic);
			foodVO.setFood_price(food_price);
			foodVO.setFood_status(food_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("foodVO", foodVO); // 含有輸入格式錯誤的foodVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/food/addFood.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			FoodService foodSvc = new FoodService();
			foodVO = foodSvc.addFood(food_name, food_details, food_price, food_pic, food_status);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/food/listAllFood1_byDAO.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFood.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer food_id = Integer.valueOf(req.getParameter("food_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			FoodService foodSvc = new FoodService();
			foodSvc.deleteFood(food_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/food/listAllFood1_byDAO.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
