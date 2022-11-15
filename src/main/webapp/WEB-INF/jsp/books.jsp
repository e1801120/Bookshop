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

    <h1>View books</h1>

    <c:if test="${not empty error }">
        <p class="error">${error} </p>
    </c:if>

    <form class="form-inline" action="Bookshop" method="get">
	
    <div class="form-group">
        <label for="choice">Choose:</label>
        <select name="choice" class="form-control" id="choice">
            <c:forEach var="b" items="${books }">
                <c:if test="${b.bookid eq param.choice }">
                    <option value="${b.bookid }" selected >${b.bookname }</option> 
                </c:if>
                <c:if test="${b.bookid ne param.choice }">
                    <option value="${b.bookid }" >${b.bookname }</option>
                </c:if>			
            </c:forEach>
        </select>
    </div>
    
    <button type="submit" class="btn btn-default">Submit</button>
	
    </form>
    
    
	
    <p>
	
    <c:if test="${not empty abook }">
	
    <table class="table">
        <tr><td>ID: </td><td>${abook.bookid }</td></tr>
        <tr><td>Name: </td><td>${abook.bookname }</td></tr>
        <tr><td>Pages: </td><td>${abook.pages }</td></tr>
        <tr><td>Price: </td>
        <td><fmt:formatNumber value=" ${abook.price }" minFractionDigits="2" maxFractionDigits="2" /> €</td></tr>
    
    
    
    </table>
	
    </c:if>
	
    </p>
</div>

<c:if test="${not empty abook }">
<c:if test="${not empty user }">
<form action="Cart" method="get">
<input type="hidden" name ="action" value="add">
	
        <div class="form-group">
            <label for="quantity">Amount (1-10):</label>
            <input type="number"  id="quantity" name="quantity" min="1" max="10" value="${abook.quantity}">
            <input type="hidden" name ="id" value="${abook.bookid}">
        </div>
        
        <button type="submit" class="btn btn-default">Add to cart</button>
		
    </form>
</c:if>
</c:if>
</body>
</html>