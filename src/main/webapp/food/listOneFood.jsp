<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.food.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
FoodVO foodVO = (FoodVO) request.getAttribute("foodVO"); //FoodServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneFood.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - listOneEmp.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�����s��</th>
			<th>�����m�W</th>
			<th>�����y�z</th>
			<th>�����Ϥ�</th>
			<th>����</th>
			<th>�W/�U�[</th>
		</tr>
		<tr>
			<td>${foodVO.food_id}</td>
			<td>${foodVO.food_name}</td>
			<td>${foodVO.food_details}</td>
			<td> <img src="data:image/jpg;base64,${foodVO.food_pic}}"/></td>
			<td>${foodVO.food_price}</td>
			<td>${foodVO.food_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/food/food.do" style="margin-bottom: 0px;" enctype="multipart/form-data">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="food_id"  value="${foodVO.food_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/food/food.do" style="margin-bottom: 0px;" enctype="multipart/form-data">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="food_id"  value="${foodVO.food_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</table>

</body>
</html>