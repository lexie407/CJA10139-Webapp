<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.toiukha.groupactivity.entity.GroupActivityVO"%>

<%
GroupActivityVO actVO = (GroupActivityVO) request.getAttribute("actVO");
%>

<html>
<head>
<title>活動詳細資料 - listOneAct.jsp</title>

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
	width: 100%;
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

<table id="table-1">
	<tr><td>
		 <h3>查詢活動資料 - listOneAct.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	<tr>
		<td><%=actVO.getActId()%></td>
		<td><%=actVO.getActName()%></td>
		<td><%=actVO.getActDesc()%></td>
		<td><%=actVO.getImgPath()%></td>
		<td><%=actVO.getItnId()%></td>
		<td><%=actVO.getHostId()%></td>
		<td><%=actVO.getSignupStart()%></td>
		<td><%=actVO.getSignupEnd()%></td>
		<td><%=actVO.getMaxCap()%></td>
		<td><%=actVO.getActStart()%></td>
		<td><%=actVO.getActEnd()%></td>
		<td><%=actVO.getIsPublic()%></td>
		<td><%=actVO.getAllowCancel()%></td>
		<td><%=actVO.getRecruitStatus()%></td>
	</tr>
</table>

</body>
</html>