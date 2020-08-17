<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var trObj = $("#table1").append("<tr></tr>");
			trObj.append("<td>1</td>");
			trObj.append("<td>斗破苍穹</td>");
			trObj.append("<td>斗宗强者</td>");
			trObj.append("<td>15</td>");
			trObj.append("<td>1</td>");
			trObj.append("<td>哈哈哈哈</td>");
			trObj.append("<td>历史类</td>");
			trObj.append("<td>删除 修改</td>");
		})
	</script>
</head>
<body>
	<table id="table1">
		<tr>
			<td>书籍编号</td>
			<td>书籍名称</td>
			<td>书籍卖点</td>
			<td>书籍价格</td>
			<td>书籍图片</td>
			<td>书籍描述</td>
			<td>书籍分类</td>
			<td>操作
				<a href="">删除</a>
			</td>
			
		</tr>
	</table>
</body>
</html>