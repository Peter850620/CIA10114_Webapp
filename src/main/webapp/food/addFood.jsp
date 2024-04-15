<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.food.model.*"%>

<%
//見com.food.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
FoodVO foodVO = (FoodVO) request.getAttribute("foodVO");
%>
--<%=foodVO == null%>--${foodVO.food_id}--
<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>食物資料新增 - addFood.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>食物資料新增 - addFoodEmp.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="food.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>食物名稱:</td>
				<td><input type="TEXT" name="food_name"
					value="<%=(foodVO == null) ? "吳永志" : foodVO.getFood_name()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>食物內容:</td>
				<td><input type="TEXT" name="food_details"
					value="<%=(foodVO == null) ? "MANAGER" : foodVO.getFood_details()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>食物圖片:</td>
				<td><input type="file" name="food_pic"
					size="45" /></td>
			</tr>
			<tr>
				<td>食物價格:</td>
				<td><input type="TEXT" name="food_price"
					value="<%=(foodVO == null) ? "100" : foodVO.getFood_price()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>食物狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="food_status">
						<option value="上架">上架
						<option value="下架">下架
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert">
			 <input type="submit" value="送出新增">
	</FORM>

</body>


</html>