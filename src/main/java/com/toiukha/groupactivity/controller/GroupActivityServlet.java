package com.toiukha.groupactivity.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.toiukha.groupactivity.entity.GroupActivityVO;
import com.toiukha.groupactivity.service.*;

@WebServlet("/groupactivity/act.do")
public class GroupActivityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		
		doPost(req, res);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res ) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getAll_For_Display".equals(action)) { // 來自select_page.jsp的請求

				/***************************2.開始查詢資料*****************************************/
				GroupActivityService actSvc = new GroupActivityServiceImpl(); 
				List<GroupActivityVO> actList = actSvc.getAll();
				System.out.println("查詢筆數：" + actList.size());

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actList", actList); // 資料庫取出的empVO物件,存入req
				String url = "/groupactivity/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupactivity/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer actId = null;
				try {
					actId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupactivity/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				GroupActivityService actSvc = new GroupActivityServiceImpl(); 
				GroupActivityVO actVO = actSvc.getByPK(actId);
				if (actVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupactivity/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/groupactivity/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer actId = Integer.valueOf(req.getParameter("actId"));
				
				/***************************2.開始查詢資料****************************************/
				GroupActivityServiceImpl actSvc = new GroupActivityServiceImpl();
				GroupActivityVO actVO = actSvc.getByPK(actId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actVO", actVO);         // 資料庫取出的empVO物件,存入req
				String url = "/groupactivity/updateAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自updateAct.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actId = Integer.valueOf(req.getParameter("actId").trim()); //SQL語言的where條件要使用的
				
				String actName = req.getParameter("actName").trim();
				if (actName == null || actName.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}
				
				String actDesc = req.getParameter("actDesc").trim();
			
				String imgPath = req.getParameter("imgPath");//應該可以用上傳得路徑取代手動輸入
			
				Integer itnId = Integer.valueOf(req.getParameter("itnId").trim()); //應該可以自動帶入已收藏的資料
			
				Integer hostId = Integer.valueOf(req.getParameter("hostId").trim()); //應該可以自動帶入會員資料
				
				Timestamp signupStart = null;
				Timestamp signupEnd = null;
				try {
				    String signupStartStr = req.getParameter("signupStart").trim();
				    String signupEndStr = req.getParameter("signupEnd").trim();

				    signupStart = Timestamp.valueOf(signupStartStr.replace("T", " ") + ":00");
				    signupEnd = Timestamp.valueOf(signupEndStr.replace("T", " ") + ":00");
				} catch (IllegalArgumentException | NullPointerException e) {
				    signupStart = new Timestamp(System.currentTimeMillis());
				    signupEnd = new Timestamp(System.currentTimeMillis());
				    errorMsgs.add("請輸入正確的報名開始與結束時間（格式 yyyy-MM-ddTHH:mm）");
				}
				
				Integer maxCap = null;
				try {
					maxCap = Integer.valueOf(req.getParameter("maxCap").trim());
					
				} catch (NumberFormatException e) {
					maxCap = 2;
					errorMsgs.add("請填數字.");
				}
				
				Integer signupCnt = Integer.valueOf(req.getParameter("signupCnt").trim());
				
				Timestamp actStart = null;
				Timestamp actEnd = null;
				try {
				    String actStartStr = req.getParameter("actStart").trim();
				    String actEndStr = req.getParameter("actEnd").trim();

				    actStart = Timestamp.valueOf(actStartStr.replace("T", " ") + ":00");
				    actEnd = Timestamp.valueOf(actEndStr.replace("T", " ") + ":00");
				} catch (IllegalArgumentException | NullPointerException e) {
				    actStart = new Timestamp(System.currentTimeMillis());
				    actEnd = new Timestamp(System.currentTimeMillis());
				    errorMsgs.add("請輸入正確的報名開始與結束時間（格式 yyyy-MM-ddTHH:mm）");
				}

				Byte isPublic = null;
				try {
				    isPublic = Byte.valueOf(req.getParameter("isPublic").trim());
				    if (isPublic < 0 || isPublic > 2) {
				        errorMsgs.add("狀態設置有誤（請選擇 0～2）");
				    }
				} catch (NumberFormatException | NullPointerException e) {
				    errorMsgs.add("請選擇有效的公開狀態");
				}
				
				Byte allowCancel = null;
				try {
					allowCancel = Byte.valueOf(req.getParameter("allowCancel").trim());
				    if (allowCancel < 0 || allowCancel > 1) {
				        errorMsgs.add("狀態設置有誤（請選擇 0～1）");
				    }
				} catch (NumberFormatException | NullPointerException e) {
				    errorMsgs.add("請選擇是否退出");
				}
				
				Byte recruitStatus = null;
				try {
					recruitStatus = Byte.valueOf(req.getParameter("recruitStatus").trim());
				    if (recruitStatus < 0 || recruitStatus > 5) {
				        errorMsgs.add("狀態設置有誤（請選擇 0～5）");
				    }
				} catch (NumberFormatException | NullPointerException e) {
				    errorMsgs.add("請選擇有效的招募狀態");
				}
				
				GroupActivityVO actVO = new GroupActivityVO();
				actVO.setActId(actId);
				actVO.setActName(actName);
				actVO.setActDesc(actDesc);
				actVO.setImgPath(imgPath);
				actVO.setItnId(itnId);
				actVO.setHostId(hostId);
				actVO.setSignupStart(signupStart);
				actVO.setSignupEnd(signupEnd);
				actVO.setMaxCap(maxCap);
				actVO.setSignupCnt(signupCnt);
				actVO.setActStart(actStart);
				actVO.setActEnd(actEnd);
				actVO.setIsPublic(isPublic);
				actVO.setAllowCancel(allowCancel);
				actVO.setRecruitStatus(recruitStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { 
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req(保留原本寫輸入的東西，讓使用者不會重打)
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupactivity/updateAct.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GroupActivityServiceImpl actSvc = new GroupActivityServiceImpl();
				actVO = actSvc.updateAct(actId, actName, actDesc, imgPath, itnId, hostId, signupStart, signupEnd, maxCap, signupCnt, actStart, actEnd, isPublic, allowCancel, recruitStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/groupactivity/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			String actName = req.getParameter("actName").trim();
			if (actName == null || actName.trim().length() == 0) {
				errorMsgs.add("活動名稱請勿空白");
			}
			
			String actDesc = req.getParameter("actDesc").trim();
		
			String imgPath = req.getParameter("imgPath");//應該可以用上傳得路徑取代手動輸入
		
			Integer itnId = Integer.valueOf(req.getParameter("itnId").trim()); //應該可以自動帶入已收藏的資料
		
			Integer hostId = Integer.valueOf(req.getParameter("hostId").trim()); //應該可以自動帶入會員資料
			
			Timestamp signupStart = null;
			Timestamp signupEnd = null;
			try {
			    String signupStartStr = req.getParameter("signupStart").trim();
			    String signupEndStr = req.getParameter("signupEnd").trim();

			    signupStart = Timestamp.valueOf(signupStartStr.replace("T", " ") + ":00");
			    signupEnd = Timestamp.valueOf(signupEndStr.replace("T", " ") + ":00");
			} catch (IllegalArgumentException | NullPointerException e) {
			    signupStart = new Timestamp(System.currentTimeMillis());
			    signupEnd = new Timestamp(System.currentTimeMillis());
			    errorMsgs.add("請輸入正確的報名開始與結束時間（格式 yyyy-MM-ddTHH:mm）");
			}
			
			Integer maxCap = null;
			try {
				maxCap = Integer.valueOf(req.getParameter("maxCap").trim());
				
			} catch (NumberFormatException e) {
				maxCap = 2;
				errorMsgs.add("請填數字.");
			}
			
			Integer signupCnt = Integer.valueOf(req.getParameter("signupCnt").trim());
			
			Timestamp actStart = null;
			Timestamp actEnd = null;
			try {
			    String actStartStr = req.getParameter("actStart").trim();
			    String actEndStr = req.getParameter("actEnd").trim();

			    actStart = Timestamp.valueOf(actStartStr.replace("T", " ") + ":00");
			    actEnd = Timestamp.valueOf(actEndStr.replace("T", " ") + ":00");
			} catch (IllegalArgumentException | NullPointerException e) {
			    actStart = new Timestamp(System.currentTimeMillis());
			    actEnd = new Timestamp(System.currentTimeMillis());
			    errorMsgs.add("請輸入正確的報名開始與結束時間（格式 yyyy-MM-ddTHH:mm）");
			}

			Byte isPublic = null;
			try {
			    isPublic = Byte.valueOf(req.getParameter("isPublic").trim());
			    if (isPublic < 0 || isPublic > 2) {
			        errorMsgs.add("狀態設置有誤（請選擇 0～2）");
			    }
			} catch (NumberFormatException | NullPointerException e) {
			    errorMsgs.add("請選擇有效的公開狀態");
			}
			
			Byte allowCancel = null;
			try {
				allowCancel = Byte.valueOf(req.getParameter("allowCancel").trim());
			    if (allowCancel < 0 || allowCancel > 1) {
			        errorMsgs.add("狀態設置有誤（請選擇 0～1）");
			    }
			} catch (NumberFormatException | NullPointerException e) {
			    errorMsgs.add("請選擇是否退出");
			}
			
			Byte recruitStatus = null;
			try {
				recruitStatus = Byte.valueOf(req.getParameter("recruitStatus").trim());
			    if (recruitStatus < 0 || recruitStatus > 5) {
			        errorMsgs.add("狀態設置有誤（請選擇 0～5）");
			    }
			} catch (NumberFormatException | NullPointerException e) {
			    errorMsgs.add("請選擇有效的招募狀態");
			}
			
			GroupActivityVO actVO = new GroupActivityVO();
				actVO.setActName(actName);
				actVO.setActDesc(actDesc);
				actVO.setImgPath(imgPath);
				actVO.setItnId(itnId);
				actVO.setHostId(hostId);
				actVO.setSignupStart(signupStart);
				actVO.setSignupEnd(signupEnd);
				actVO.setMaxCap(maxCap);
				actVO.setSignupCnt(signupCnt);
				actVO.setActStart(actStart);
				actVO.setActEnd(actEnd);
				actVO.setIsPublic(isPublic);
				actVO.setAllowCancel(allowCancel);
				actVO.setRecruitStatus(recruitStatus);

				// Send the use back to the form, if there were errors只要以上有任何錯誤就無法送出
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupactivity/addAct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GroupActivityServiceImpl actSvc = new GroupActivityServiceImpl();
				actVO = actSvc.addAct(actName, actDesc, imgPath, itnId, hostId, signupStart, signupEnd, maxCap, signupCnt, actStart, actEnd, isPublic, allowCancel, recruitStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/groupactivity/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));//送出資料預設是字串，要轉型為數字
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
	}

}
