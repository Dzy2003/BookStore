<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>首页体</title>
  </head>
  
  <body style="text-align:center;">
    <div id="content" style="margin:0 auto;width:840px;">
    	<div id="category" style="float:left; width:200px; border:1px solid red; text-align:left; height:300px;"> 
    		分类列表：
    		<ul>		
	    		<c:forEach var="category" items="${categories }">
	    			<li>
	    				<a href="${pageContext.request.contextPath }/client/IndexServlet?method=listBookWithCategory&cid=${category.id}&pageNow=1">${category.name }</a>
	    			</li>
	    		</c:forEach>
				<%--显示所有图书--%>
				<a href="${pageContext.request.contextPath }/client/IndexServlet?method=getAll&pageNow=1">显示全部</a>
    		</ul>	
    	</div>
    	<div id="bookandpage" style="float:left; margin-left:30px;">   		
    		<div id="books">
    			<c:forEach var="book" items="${page.rows }">
    				<div id="book" style="height:150px; margin-top:20px;">
    					<div id="image" style="float:left;">
    						<img src="${pageContext.request.contextPath }/images/${book.image}" height=150 width=100>
    					</div>
    					<div id="bookinfo" style="float:left; text-align:left;">
    						<ul>
    							<li>${book.name }</li>
    							<li>作者：${book.author }</li>
    							<li>售价：${book.price }</li>
    							<li>
    								<a href="${pageContext.request.contextPath }/client/ListCartServlet?bookid=${book.id}">加入购物车</a>
    							</li>
    						</ul>
    					</div>
    				</div>
    				<div style="clear:both"></div><!-- 做div浮动后，为了不影响后续页面的显示，在这里清楚浮动效果 -->
    			</c:forEach>
    		</div>
    		<div style="clear:both"></div><!-- 做div浮动后，为了不影响后续页面的显示，在这里清楚浮动效果 -->
			<div id="page" style="margin-top:20px; text-align:center;">
<%--				分页页码--%>
				<form  action="${pageContext.request.contextPath}/client/IndexServlet?method=listBookWithCategory&cid=${cid}&" method="post" >
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
    	</div>
    </div>
  </body>
</html>
