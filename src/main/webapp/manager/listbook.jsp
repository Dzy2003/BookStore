<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>显示所有书籍</title>
  </head>
  
  <body style="text-align:center;">
    <h2>图书信息</h2>
    <table border="1" width="60%" align="center">
    	<tr style="text-align:center">
  			<td>图书名称</td>
  			<td>作者</td>
  			<td>价格</td>
  			<td>图片</td>
  			<td>描述</td>
  			<td>操作</td>
  		</tr>
  		<c:forEach var="book" items="${page.rows}">
  			<tr style="text-align:center">
  				<td>${book.name}</td>
	  			<td>${book.author}</td>
	  			<td>${book.price}</td>
	  			<td>
					<img src="${pageContext.request.contextPath }/images/${book.image}" alt="50px">
				</td>
	  			<td>${book.description }</td>
	  			<td>
	  				<a href="#">修改</a>
	  				<a href="#">删除</a>
	  			</td>
  			</tr>
		</c:forEach>

		<div id="page" style="margin-top:20px; text-align:center;">
			<form  action="${pageContext.request.contextPath}/manager/BookServlet?method=list" method="post" >
			<table >
			<tr>
			<c:forEach var="pages" begin="1" end="${page.totalPages}">
				<td>
					<button style="text-align:center" type="submit" name="pageNow" value="${pages}">
							${pages}
					</button>
				</td>
			</c:forEach>
			</tr>
			</table>
			<p style="text-align: center">总共[${page.totalPages}]页，共[${page.totalCount }]条记录</p>
			</form>
		</div>

	</table>
    <br>
  </body>
</html>
