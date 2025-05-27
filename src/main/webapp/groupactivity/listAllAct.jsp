<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.toiukha.groupactivity.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有揪團活動 - listAllAct.jsp</title>
<style>
table#table-1 {
	border: 1px solid black; background-color : #CCCCFF;
	border: 2px solid black;
	text-align: center;
	background-color: #CCCCFF;
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
	width: 800px;
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
<body>
	<table id="table-1">
		<tr><td>
			<h3>所有揪團活動 - ListAll.jsp</h3>
			<h4>
			<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</h4>
		</td></tr>
	</table>

	<h1>揪團活動清單</h1>
	<table>
			<tr>
				<th>活動編號</th>
				<th>活動名稱</th>
				<th>活動說明</th>
				<th>活動圖片</th>
				<th>行程編號</th>
				<th>團主編號</th>
				<th>報名開始時間</th>
				<th>報名截止時間</th>
				<th>活動人數需求</th>
				<th>已報名人數</th>
				<th>活動開始時間</th>
				<th>活動結束時間</th>
				<th>公開狀態</th>
				<th>是否允許退出</th>
				<th>招募狀態</th>
			</tr>
<%-- 		<%@ include file="page1.file" %> begin="${pageIndex}" end="${pageEnd}" --%>
		<c:forEach var="actVO" items="${actList}" >

			<tr>
				<td>${actVO.actId}</td>
				<td>${actVO.actName}</td>
				<td>${actVO.actDesc}</td>
				<td>${actVO.imgPath}</td>
				<td>${actVO.itnId}</td>
				<td>${actVO.hostId}</td>
				<td>${actVO.signupStart}</td>
				<td>${actVO.signupEnd}</td>
				<td>${actVO.maxCap}</td>
				<td>${actVO.actStart}</td>
				<td>${actVO.actEnd}</td>
				<td>${actVO.isPublic}</td>
				<td>${actVO.allowCancel}</td>
				<td>${actVO.recruitStatus}</td>
				<td>
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupactivity/act.do" style="margin-bottom: 0px;">
			     	<input type="submit" value="修改">
			    	 <input type="hidden" name="actId"  value="${actVO.actId}">
			    	 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td>
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupactivity/act.do" style="margin-bottom: 0px;">
			    	 <input type="submit" value="刪除">
			     	<input type="hidden" name="actId"  value="${actVO.actId}">
			     	<input type="hidden" name="action" value="delete"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- <%@ include file="page2.file" %> --%>
</body>
</html>