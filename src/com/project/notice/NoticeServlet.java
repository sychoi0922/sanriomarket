package com.project.notice;

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



public class NoticeServlet extends HttpServlet {  //검색기능 (-)

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}


	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException { //자주쓰는 것은 따로 뺌 

		RequestDispatcher rd =  req.getRequestDispatcher(url);  
		rd.forward(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");   // web.xml 에 encoding 필터 넣긴함    
		String cp = req.getContextPath();

		// System.out.println(cp);  //cp 확인 =/projectSan


		Connection conn = DBConn.getConnection();

		NoticeDAO  dao = new NoticeDAO(conn);

		MyPage myPage = new MyPage();

		String uri = req.getRequestURI();
		String url;
		
		//----------------------------------
		HttpSession session = req.getSession();
		
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		
		int loginCheck;
		
		//session에 올려진 로그인 정보가 없는 경우 loginCheck 값이 0, 로그인 정보가 있다면 1.
		//이를 바탕으로 jsp 파일에서 if문을 통해 0인 경우 로그인 기능이 있는 헤더를,
		//1인 경우 로그아웃 기능이 있는 헤더를 열도록 해놨습니다.
		
		//이건 제 재량으로 넣은 기능이니 더 좋은 방법이 있다면 수정하셔도 무방합니다.
		if (info == null) {
			loginCheck = 0;
		}else {
			loginCheck = 1;
		}
		
		req.setAttribute("loginCheck", loginCheck);
		//-----------------------------------------
		
		


//not/list.do		
		if(uri.indexOf("list.do")!=-1) {   


			String pageNum = req.getParameter("pageNum");

			int currentPage = 1; 

			if(pageNum!=null){ 

				currentPage = Integer.parseInt(pageNum);
			}


			//검색------------------------------------------------------------------
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

			//검색end------------------------------------------------------------------//end

			int dataCount = dao.getDataCount(searchKey,searchValue); 

			int numPerPage = 10;

			int totalPage = myPage.getPageCount(numPerPage,dataCount);	

			if(currentPage >totalPage){  											// <이전 11 만 보임 즉, 11페이지 게시물이 안뜸 
				currentPage = totalPage;
			}

			int start = (currentPage -1)*numPerPage+1;
			int end = currentPage * numPerPage;

			List<NoticeDTO> lists = dao.getLists(start,end,searchKey,searchValue); //한페이지당 게시물 갯수


			//검색------------------------------------------------------------------여기 부분은 방법이 여러개
			String param = "";
			if(!searchValue.equals("")){

				param = "?searchKey=" + searchKey;
				param+= "&searchValue=" + 
						URLEncoder.encode(searchValue, "UTF-8");	
			}
			//검색end------------------------------------------------------------------//end 

			// 페이징 처리 -   여기 부분도 방법이 여러개 
			String listUrl = cp+"/not/list.do"+ param ;  //param은 검색을 위함 // 만약 검색을 안했다면, "list.jsp"만 넘어감 

			String pageIndexList = 
					myPage.pageIndexList(currentPage,totalPage,listUrl); 

			// arictle 주소 정리 
			String articleUrl = cp  +"/not/article.do";

			if(param.equals("")){
				articleUrl += "?pageNum=" + currentPage;	
			}else{
				articleUrl += param + "&pageNum=" + currentPage;
			}


			// 포워딩 페이지에 넘길 데이터 
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList",pageIndexList);
			req.setAttribute("dataCount",dataCount);
			req.setAttribute("articleUrl",articleUrl);

			url = "/project/notice/noticelist.jsp";
			forward(req, resp, url);


//not/article.do
		}else if (uri.indexOf("article.do")!=-1) {

			int nnum = Integer.parseInt(req.getParameter("nnum"));
			String pageNum = req.getParameter("pageNum"); 

			
			//검색------------------------------------------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue"); 

			if(searchValue !=null){ 

				//넘어온 값이 GET방식 일때 
				if(req.getMethod().equalsIgnoreCase("GET")){ 

					searchValue = URLDecoder.decode(searchValue, "UTF-8") ;//여기서는 받는 거라서 decoding 

				}

			}else{

				searchKey = "title";
				searchValue = "";

			}
			//검색end------------------------------------------------------------------

			@SuppressWarnings("unused")//컴파일러에게 "unused" 경고를 무시하라고 지시
			int dataCount = dao.getDataCount(searchKey,searchValue); 

			
			//데이터 읽기
			NoticeDTO dto = dao.getReadData(nnum);
			
			if(dto==null){
				url=cp+"/not/list.do";

				resp.sendRedirect(url);

			}

			//검색------------------------------------------------------------------

			String param ="pageNum="+pageNum;  //검색후 게시물 눌러서 수정할 경우를 위해

			if(!searchValue.equals("")){

				param += "&searchKey=" + searchKey; //<input type="button" value="리스트"... 할때> 붙여야 해서 &로 시작
				param+= "&searchValue=" +
						URLEncoder.encode(searchValue, "UTF-8"); //URLEncoder를 java.net.URLEncoder으로 해야함 
			}
			//검색end------------------------------------------------------------------
			
			
			//이전글, 다음글 넘어가기 위한  arictle 주소 정리 
			String artNeviUrl = cp  +"/not/article.do?";

			if(param.equals("")){
				
				artNeviUrl += "pageNum="+pageNum 	;
				
			}else{
				artNeviUrl += param ;
			}
			//System.out.println(artNeviUrl); 

			req.setAttribute("artNeviUrl", artNeviUrl);
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", param);
			req.setAttribute("dataCount",dataCount);

			url = "/project/notice/noticearticle.jsp";
			forward(req, resp, url);

		}





	}//doPost end

}