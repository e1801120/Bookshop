<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Bookshop</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="Bookshop">Home</a></li>
      <li><a href="InsertBook">Insert</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
     
      
         <c:if test="${empty user }">
         <li>	<a href="Register"><span class="glyphicon glyphicon-user"></span> Sign Up</a> </li>
      	 <li>	<a href="Login"><span class="glyphicon glyphicon-log-in"></span> Login</a> </li>
         </c:if>
         <c:if test="${not empty user }">
      	  <li> <a href="UserInfo"><span class="glyphicon glyphicon-user"></span> ${user.username}</a> </li>
      	  <li> <a href="Cart"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a> </li>
      	  <li> <a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a> </li>
      	  
         </c:if>
         
      
    </ul>
  </div>
</nav> 