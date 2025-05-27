<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.toiukha.groupactivity.entity.GroupActivityVO"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
	GroupActivityVO actVO = (GroupActivityVO) request.getAttribute("actVO");

	//格式化四個時間欄位
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String signupStartStr = (actVO != null && actVO.getSignupStart() != null) ? sdf.format(actVO.getSignupStart()) : "";
	String signupEndStr   = (actVO != null && actVO.getSignupEnd()   != null) ? sdf.format(actVO.getSignupEnd())   : "";
	String actStartStr    = (actVO != null && actVO.getActStart()    != null) ? sdf.format(actVO.getActStart())    : "";
	String actEndStr      = (actVO != null && actVO.getActEnd()      != null) ? sdf.format(actVO.getActEnd())      : "";
%>
<%-- 檢查是否有成功輸入物件 --%>
--<%= actVO==null %>--${actVO.itnId}--<!--顯示行程編號-->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團活動新增 - addActEmp.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>揪團活動新增 - addAct.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/groupactivity/act.do" name="form1">
<table>
	<tr>
		<td>活動名稱:</td>
		<td><input type="text" name="actName" value="<%= (actVO==null)? "台北七星山健走團" : actVO.getActName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>活動描述:</td>
		<td><input type="text" name="actDesc"   value="<%= (actVO==null)? "適合闔家大小一起來賞芒草" : actVO.getActDesc()%>" size="45"/></td>
	</tr>
	<tr>
		<td>活動圖片:</td>
		<td><input type="text" name="imgPath"   value="<%= (actVO==null)? "images/back1.gif" : actVO.getImgPath()%>" size="45"/></td>
	</tr>
	<tr>
		<td>行程編號:</td>
		<td><input type="text" name="itnId"   value="<%= (actVO==null)? "19" : actVO.getItnId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>團主編號:</td>
		<td><input type="text" name="hostId"   value="<%= (actVO==null)? "29" : actVO.getHostId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>招募開始時間:</td>
		<td><input name="signupStart" class="datetimepicker" type="text" value="<%= signupStartStr %>"></td>
	</tr>
	<tr>
		<td>招募結束時間:</td>
		<td><input name="signupEnd" class="datetimepicker" type="text" value="<%= signupEndStr %>"></td>
	</tr>
	<tr>
		<td>需求人數:</td>
		<td><input type="text" name="maxCap" value="<%= (actVO==null)? "3" : actVO.getMaxCap()%>" size="45"/></td>
	</tr>
	<tr>
		<td>已報名人數:</td>
		<td><input type="text" name="signupCnt" value="<%= (actVO==null)? "0" : actVO.getSignupCnt()%>" size="45"/></td>
	</tr>
	<tr>
		<td>活動開始時間:</td>
		<td><input name="actStart" class="datetimepicker" type="text" value="<%= actStartStr %>"></td>
	</tr>
	<tr>
		<td>活動結束時間:</td>
		<td><input name="actEnd" class="datetimepicker" type="text" value="<%= actEndStr %>"></td>
	</tr>
	<tr>
		<td>是否公開:</td>
		<td><input type="text" name="isPublic"   value="<%= (actVO==null)? "0" : actVO.getIsPublic()%>" size="45"/></td>
	</tr>
	<tr>
		<td>是否允許退出:</td>
		<td><input type="text" name="allowCancel"   value="<%= (actVO==null)? "0" : actVO.getAllowCancel()%>" size="45"/></td>
	</tr>
	<tr>
		<td>招募狀態:</td>
		<td><input type="text" name="recruitStatus"   value="<%= (actVO==null)? "0" : actVO.getRecruitStatus()%>" size="45"/></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('.datetimepicker').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,        //timepicker:true,//要不要加時間
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',   //format:'Y-m-d H:i:s',
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>