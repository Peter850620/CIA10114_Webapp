<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Food: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Food: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Food: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFood1_byDAO.jsp'>List</a> all Foods    <h4>(byFoodJDBCDAO).         </h4></li>
  
  <li>
    <FORM METHOD="post" ACTION="food.do" >
        <b>��J�����s�� (�p101):</b>
        <input type="text" name="food_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">                   <h4>(��Ʈ榡����  by Controller ).</h4> 
    </FORM>
  </li> 

  <jsp:useBean id="dao" scope="page" class="com.food.model.FoodJDBCDAO" />
   
  <li>	
     <FORM METHOD="post" ACTION="food.do" >
       <b>��ܭ����s��:</b>
       <select size="1" name="food_id">
         <c:forEach var="foodVO" items="${dao.all}" > 
          <option value="${foodVO.food_id}">${foodVO.food_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="food.do" >
       <b>��ܭ����m�W:</b>
       <select size="1" name="food_id">
         <c:forEach var="foodVO" items="${dao.all}" > 
          <option value="${foodVO.food_id}">${foodVO.food_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�����޲z</h3>

<ul>
  <li><a href='addFood.jsp'>Add</a> a new Food.</li>
</ul>

</body>
</html>