<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 


<title>Bookshop</title>
</head>
<body>


<!-- otetaan mukaan sivulle header-osa -->
<c:import url="header.jsp" charEncoding="UTF-8"></c:import>

<div class="container-fluid">

    <h2>Cart</h2>
    
    <c:if test="${empty cart }">
    <p>Cart is empty</p>
    </c:if>
    
    <c:if test="${not empty cart }">
    <table class="table">
    <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Price</th>
    <th>Amount</th>
    <th>Book total</th>
    </tr>
    
    <c:set var="total" value="0"></c:set>
    <c:set var="totalAmount" value="0"></c:set>
     <c:forEach var="item" items="${cart }">
     
<tr>
    
    <c:set var="total" value="${total + item.price * item.quantity }"></c:set>
    <c:set var="totalAmount" value="${totalAmount + item.quantity }"></c:set>
    <td>${item.bookid }</td>
    <td>${item.bookname }</td>
    <td>${item.price } €</td>
    <td>${item.quantity } kpl</td>
    <td>${item.price * item.quantity } €</td>
</tr>  
     
     </c:forEach>
<tr>
	<td></td>
	<td></td>
	<td></td>
   	<td>Total Amount: <b>${totalAmount } kpl</b></td>
   	<td>Total price: <b>${total } €</b></td>
   	
   
</tr>
    </table>
    </c:if>
    
    
    <c:if test="${not empty cart }">
    <form action="Cart" method="get">
	<input type="hidden" name ="action" value="clear">
	<input type="submit" value="Clear cart!">
	
	</form>
	</c:if>
    
    </div>

</body>
</html>