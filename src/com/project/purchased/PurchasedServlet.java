package com.project.purchased;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.membership.CustomInfo;
import com.util.DBConn;
import com.util.MyPage;

public class PurchasedServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String cp=req.getContextPath();
		
		Connection conn = DBConn.getConnection();
		PurchasedDAO purDao = new PurchasedDAO(conn);
		
		
		MyPage myPage = new MyPage();
		
		String uri = req.getRequestURI();
		String url;
		
		String root = getServletContext().getRealPath("/");
		String path = root + "pds" + File.separator + "imgFile";
				
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
			
		}
		
		HttpSession session = req.getSession();
		
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		
		//로그인하지 않아 session에 올려진 로그인 정보가 없는 경우 메인페이지로 이동.
		if (info == null) {
			url = "/project/membership/login.jsp";
			forward(req, resp, url);
			return;
		}
		
		String id = info.getId();
		
		
		
		//String id = (String) session.getAttribute("id");	//세션으로 아이디 받아온다 가정.
															//delete에는 아이디가 필요없지만 구매에는 필요하다.
		
		//String fid = "suzi123";			//지금은 세션에서 받아올 id가 없으니 임시로 지정.

		
		if(uri.indexOf("purchased.do")!=-1) {
			
			//화면에서 기간을 정해서 select 하기 위해 switch case문을 썼다.
			
			
			//daycheck는 웹브라우저에서 오늘, 1주일전, 1개월전, 3개월전, 6개월전
			//어느 것을 선택했느냐에 따라 넘어오는 값이 달라진다.
			
			String dayCheck = req.getParameter("daySelect");
			List<PurchasedDTO> lists = null;
			
			String pageNum=req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null){
				
				currentPage = Integer.parseInt(pageNum);
				
			}
			
			
			//전체 데이터 갯수
			int dataCount =  0;
			
			//한페이지에 출력할 데이터의 갯수
			int numPerPage = 10;
			
			int totalPage = 0;
			
			//System.out.print(totalPage); <-페이지 개수 중간에 확인
			

			
			//데이터베이스에서 가져올 rownum의 시작과 끝위치
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			//=int end = start + numPerPage - 1;
			
			
			//---------------------------------
			//데이체크가 있다면, 즉 원하는 기간을 선택했다면
			//daycheck 값이 존재하고, null 값이 아니게 된다.
			//반대로 처음 들어갔을 경우, daycheck 값이 존재하지 않기 때문에
			//평범하게 selectAll을 실행한다.
			
			if (dayCheck != null && !dayCheck.isEmpty()) {
				
				int daySelect = Integer.parseInt(dayCheck);
				String dayMessage;
				
				switch (daySelect) {
				
				case 1:
					//그 날 구매한 물건을 보기 위함
					dayMessage = "TRUNC(buyday) = TRUNC(SYSDATE)";
					lists = purDao.getListsDay(dayMessage,id,start,end);
					dataCount =  purDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);
					
					
					break;
					
				case 2:
					//일주일 전까지의 물건을 보기 위함
					dayMessage = "buyday >= TRUNC(SYSDATE - 7)";
					lists = purDao.getListsDay(dayMessage,id,start,end);
					dataCount =  purDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);
					
					break;
					
				case 3:
					//한달 전
					dayMessage = "buyday >= TRUNC(ADD_MONTHS(SYSDATE, -1))";
					lists = purDao.getListsDay(dayMessage,id,start,end);
					dataCount =  purDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);
					
					break;
					
				case 4:
					//석달 전
					dayMessage = "buyday >= TRUNC(ADD_MONTHS(SYSDATE, -3))";
					lists = purDao.getListsDay(dayMessage,id,start,end);
					dataCount =  purDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);
					
					break;
					
				case 5:
					//여섯달 전
					dayMessage = "buyday >= TRUNC(ADD_MONTHS(SYSDATE, -6))";
					lists = purDao.getListsDay(dayMessage,id,start,end);
					dataCount =  purDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);
					
					break;
					
					
				default:
					//전체
					dataCount =  purDao.getDataCount(id);
					lists = purDao.getLists(id,start,end);
					totalPage = myPage.getPageCount(numPerPage, dataCount);
					
				}
				
				
			}
			
			else {
				//처음 들어가서 dayselect 값이 올라오지 않았을 경우.
				lists = purDao.getLists(id,start,end);
				dataCount =  purDao.getDataCount(id);
				totalPage = myPage.getPageCount(numPerPage, dataCount);
				
			}
			
			//전체페이지 수보다 표시할 페이지가 큰 경우
			//표시할 페이지를 전체 페이지로 만들기 위한 코딩
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			
			
			//페이징 처리
			String listUrl = cp + "/purchased/purchased.do";
			String pageIndexList = myPage.pageIndexList(currentPage, totalPage, listUrl);
			
			
			String imagePath = cp + "/pds/imgFile";
			req.setAttribute("imagePath", imagePath);
			
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", currentPage);
			
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", currentPage);
			
			
			req.setAttribute("lists", lists);
			
			
			url = "/project/purchased.jsp";
			forward(req, resp, url);
			
			
			
		}else if(uri.indexOf("buyCancel.do")!=-1) {
			
			//구매 취소를 눌렀을 때, 구매내역 table인 purchased에서 데이터가
			//취소내역 table인 cancel 로 넘어가고, purchased의 데이터는 삭제된다.
			int pid = Integer.parseInt(req.getParameter("pid"));
			
			int result = purDao.cancled(pid);
			
			url = cp+"/purchased/purchased.do";
			resp.sendRedirect(url);
			
			
		}else if(uri.indexOf("browseDate.do")!=-1) {
			
			//달력을 통해 날짜를 직접 지정해서 그 기간의 데이터를 뽑아낼 때 쓰는
			//코딩이다. startDate와 endDate를 받아와서
			//이를 바탕으로 buyday의 범위를 지정해서 데이터를 select 해온다.
			String startDateStr = req.getParameter("startDate");
			String endDateStr = req.getParameter("endDate");
			
			
			List<PurchasedDTO> lists = null;
			
			String pageNum=req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null){
				
				currentPage = Integer.parseInt(pageNum);
				
			}
			
			//전체 데이터 갯수
			int dataCount =  purDao.getDataCount(id, startDateStr, endDateStr);
			
			//한페이지에 출력할 데이터의 갯수
			int numPerPage = 10;
			
			int totalPage = myPage.getPageCount(numPerPage, dataCount);
			
			//System.out.print(totalPage); <-페이지 개수 중간에 확인
			
			//전체페이지 수보다 표시할 페이지가 큰 경우
			//표시할 페이지를 전체 페이지로 만들기 위한 코딩
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			
			//데이터베이스에서 가져올 rownum의 시작과 끝위치
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			//=int end = start + numPerPage - 1;
			
			
			lists = purDao.getListsDay(startDateStr, endDateStr, id, start, end);
			
			//페이징 처리
			String listUrl = cp + "/purchased/purchased.do";
			String pageIndexList = myPage.pageIndexList(currentPage, totalPage, listUrl);
			
			
			String imagePath = cp + "/pds/imgFile";
			req.setAttribute("imagePath", imagePath);
			
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", currentPage);
			
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", currentPage);
			
			req.setAttribute("lists", lists);
			
			req.setAttribute("startDateStr", startDateStr);
			req.setAttribute("endDateStr", endDateStr);
			
			
			url = "/project/purchased.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("buyProduct.do")!=-1) {
			
			
			//이미지 게시판이 완성되면 쓰게 될 코딩.
			//session으로 받아올 때와 href로 받아올 때 두 경우를 대비해서
			//두 경우의 수 모두 코딩해 놨다.
			//형님의 선택에 달렸다.
			
			String pnumReq = req.getParameter("pnum");
			String imgNameReq = req.getParameter("imgName");
			String countReq = req.getParameter("quantity");
		    
			System.out.println(countReq);
		    
			int pnum = Integer.parseInt(pnumReq);
			int count = Integer.parseInt(countReq);
			
			int result = purDao.buyProduct(pnum, imgNameReq, count, id);
			
			System.out.println(result);
			
			url = cp+"/purchased/purchased.do";
			resp.sendRedirect(url);
			
			
		}
		
		
	}

}
