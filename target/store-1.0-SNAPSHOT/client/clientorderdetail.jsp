<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>订单明细</title>
  </head>
  
  <body style="text-align:center;">
  	<h3>订单明细</h3>
    <table border="1" width="50%" align="center" style="text-align: center;">
    	<tr>
    		<td>书名</td>
    		<td>售价</td>
    		<td>数量</td>
    		<td>应收货款</td>
    	</tr>
		<c:set var="totalPrice" value="0" /> <!-- 初始化总价为0 -->
    	<c:forEach var="orderitem" items="${orderItem }">
    	<tr>
    		<td>${orderitem.name }</td>
    		<td>${orderitem.price }</td>
    		<td>${orderitem.num }</td>
    		<td>${orderitem.price * orderitem.num}</td>
			<c:set var="totalPrice" value="${totalPrice + orderitem.price * orderitem.num}" />
    	</tr>
    	</c:forEach>
    	
    	<tr>
    		<td>订单总价</td>
    		<td colspan="3">${totalPrice}</td>
    	</tr>
    	
    </table>
    
    <h3>收货人详细信息</h3>
    <table border="1" width="50%" align="center" style="text-align: center;">  
    	<tr>
    		<td>用户</td>
    		<td>电话</td>
    		<td>手机</td>
    		<td>地址</td>
    		<td>邮箱</td>
    	</tr>
    	<tr>
    		<td>${user.username }</td>
    		<td>${user.phone }</td>
    		<td>${user.cellphone }</td>
    		<td>${user.address }</td>
    		<td>${user.email }</td>
    	</tr>
	</table>
  </body>
</html>
