package com.project.review;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
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

public class ReviewServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		
		RequestDispatcher rd = 
				req.getRequestDispatcher(url);
		rd.forward(req, resp);
		
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath(); 
		
		Connection conn = DBConn.getConnection();
		ReviewDAO dao = new ReviewDAO(conn);
		
		MyPage myPage = new MyPage();
		
		String uri = req.getRequestURI(); 
		String url;		
		
		//----------------------------------
		HttpSession session = req.getSession();
		
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		
		int loginCheck;
		
		
		if (info == null) {
			loginCheck = 0;
		}else {
			loginCheck = 1;
		}
		
		req.setAttribute("loginCheck", loginCheck);
		//-----------------------------------------
		
		
		
//rev/created.do
		if(uri.indexOf("created.do")!=-1) {
			

/*###################################################
			//�α��� �ϰ� �Խ��� �ø��� �� �� �ֵ��� �ڵ�  - �ּ� �����ϰ� �����ּ���
			
			HttpSession session = req.getSession();
			
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
			*/
			if(info==null) {
				
				url = "/project/membership/login.jsp";
				forward(req, resp, url);
				return;
			}

			String id = info.getId();
			
			req.setAttribute("id", id);
			
			url = "/project/review/created.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("created_ok.do")!=-1) {
		
			ReviewDTO dto = new ReviewDTO();
			
			int maxNum = dao.getMaxNum();
			
			dto.setRvnum(maxNum + 1);
			dto.setTitle(req.getParameter("title"));
			dto.setId(req.getParameter("id"));
			dto.setPname(req.getParameter("pname"));
			dto.setContent(req.getParameter("content"));
			dto.setRpwd(req.getParameter("rpwd"));
			
			 
			dao.insertData(dto);

			url = cp + "/rev/list.do";	
			resp.sendRedirect(url);
//list.do			
		}else if(uri.indexOf("list.do")!=-1) {
			
			
			String pageNum = req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null){
				currentPage = Integer.parseInt(pageNum);
			}
		
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null){
			
				if(req.getMethod().equalsIgnoreCase("GET")){
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}		
			
			}else{
				searchKey = "title";
				searchValue = "";
			}
			int dataCount = dao.getDataCount(searchKey,searchValue);
			int numPerPage = 10;
			int totalPage = myPage.getPageCount(numPerPage, dataCount);
			                   
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage* numPerPage;
			
			List<ReviewDTO> lists = dao.getLists(start,end,searchKey,searchValue);
				
			// �˻� --------------------------------------
			String param = "";
			if(!searchValue.equals("")){
				
				param = "?searchKey=" + searchKey;
				param+= "&searchValue=" + 
						URLEncoder.encode(searchValue, "UTF-8");	
			}	
			//�˻�end --------------------------------------
			
		  
			String listUrl = cp + "/rev/list.do" + param;
			String pageIndexList = 
					myPage.pageIndexList(currentPage, totalPage, listUrl);	
			
			//article  �ּ�����    
			String articleUrl = cp  + "/rev/article.do";
			
			if(param.equals("")){
				articleUrl += "?pageNum=" + currentPage;	
			}else{
				articleUrl += param + "&pageNum=" + currentPage;
			}
			
			     
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			
			url = "/project/review/list.jsp";			
			forward(req, resp, url);
			
//article.do			
		}else if(uri.indexOf("article.do")!=-1){
			
			int rvnum = Integer.parseInt(req.getParameter("rvnum"));	
			String pageNum = req.getParameter("pageNum");
			
			// -----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null){
			
				
				if(req.getMethod().equalsIgnoreCase("GET")){
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}		
			
			}else{
				searchKey = "title";
				searchValue = "";
			}
			// -----------------------------------
			           
			ReviewDTO dto = dao.getReadData(rvnum);
			
			if(dto==null){
				url = cp + "/rev/list.do";
				resp.sendRedirect(url);
			}
			
			
			int lineSu = dto.getContent().split("\n").length;
			
			     
			dto.setContent(dto.getContent().replaceAll("\n", "<br/>"));
			
			// --------------------------------------
			String param = "pageNum=" + pageNum;
			if(!searchValue.equals("")){
				
				param+= "&searchKey=" + searchKey;
				param+= "&searchValue=" + 
						URLEncoder.encode(searchValue, "UTF-8");
			}	
			// --------------------------------------
			
			req.setAttribute("dto", dto);
			req.setAttribute("params", param);
			req.setAttribute("lineSu", lineSu);
			req.setAttribute("pageNum", pageNum);			
			
			url = "/project/review/article.jsp";	
			forward(req, resp, url);

//updated.do		
		}else if(uri.indexOf("updated.do")!=-1){
			
			

/*###################################################
			//�α��� �ϰ� �Խ��� �ø��� �� �� �ֵ��� �ڵ�  - �ּ� �����ϰ� �����ּ���
			
			HttpSession session = req.getSession();
			
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
			*/
			if(info==null) {
				
				url = "/project/membership/login.jsp";
				forward(req, resp, url);
				return;
			}

			
			int rvnum = Integer.parseInt(req.getParameter("rvnum"));
			String pageNum = req.getParameter("pageNum");
			
			// -----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null){
			
				// GET
				if(req.getMethod().equalsIgnoreCase("GET")){
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}		
			
			}else{
				searchKey = "title";
				searchValue = "";
			}
			// -----------------------------------
			
			ReviewDTO dto = dao.getReadData(rvnum);
			
			// --------------------------------------
			String param = "pageNum=" + pageNum;
			if(!searchValue.equals("")){
				
				param+= "&searchKey=" + searchKey;
				param+= "&searchValue=" + 
						URLEncoder.encode(searchValue, "UTF-8");	
			}	
			// --------------------------------------	
			
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", param);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			
			url = "/project/review/updated.jsp";
			forward(req, resp, url);
			
			
			
		}else if(uri.indexOf("updated_ok.do")!=-1){
			
			
			String pageNum = req.getParameter("pageNum");
			
			// -----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null){
			
				//      GET    
				if(req.getMethod().equalsIgnoreCase("GET")){
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}		
			
			}else{
				searchKey = "title";
				searchValue = "";
			}
			// -----------------------------------
						
			ReviewDTO dto = new ReviewDTO();
			
			dto.setRvnum(Integer.parseInt(req.getParameter("rvnum")));
			dto.setPname(req.getParameter("pname"));
			dto.setTitle(req.getParameter("title"));
			dto.setContent(req.getParameter("content"));
			dto.setRpwd(req.getParameter("rpwd"));

			dao.updateData(dto);
			
			// --------------------------------------
			String param = "pageNum=" + pageNum;
			if(!searchValue.equals("")){
				
				param+= "&searchKey=" + searchKey;
				param+= "&searchValue=" + 
						URLEncoder.encode(searchValue, "UTF-8");	
			}	
			// --------------------------------------
			
			url = cp + "/rev/list.do?" + param;
			resp.sendRedirect(url);		

		
		
		}
		
		
		
		
		
	}//doPost end
	


}

