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



public class NoticeServlet extends HttpServlet {  //�˻���� (-)

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}


	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException { //���־��� ���� ���� �� 

		RequestDispatcher rd =  req.getRequestDispatcher(url);  
		rd.forward(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");   // web.xml �� encoding ���� �ֱ���    
		String cp = req.getContextPath();

		// System.out.println(cp);  //cp Ȯ�� =/projectSan


		Connection conn = DBConn.getConnection();

		NoticeDAO  dao = new NoticeDAO(conn);

		MyPage myPage = new MyPage();

		String uri = req.getRequestURI();
		String url;
		
		//----------------------------------
		HttpSession session = req.getSession();
		
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		
		int loginCheck;
		
		//session�� �÷��� �α��� ������ ���� ��� loginCheck ���� 0, �α��� ������ �ִٸ� 1.
		//�̸� �������� jsp ���Ͽ��� if���� ���� 0�� ��� �α��� ����� �ִ� �����,
		//1�� ��� �α׾ƿ� ����� �ִ� ����� ������ �س����ϴ�.
		
		//�̰� �� �緮���� ���� ����̴� �� ���� ����� �ִٸ� �����ϼŵ� �����մϴ�.
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


			//�˻�------------------------------------------------------------------
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

			//�˻�end------------------------------------------------------------------//end

			int dataCount = dao.getDataCount(searchKey,searchValue); 

			int numPerPage = 10;

			int totalPage = myPage.getPageCount(numPerPage,dataCount);	

			if(currentPage >totalPage){  											// <���� 11 �� ���� ��, 11������ �Խù��� �ȶ� 
				currentPage = totalPage;
			}

			int start = (currentPage -1)*numPerPage+1;
			int end = currentPage * numPerPage;

			List<NoticeDTO> lists = dao.getLists(start,end,searchKey,searchValue); //���������� �Խù� ����


			//�˻�------------------------------------------------------------------���� �κ��� ����� ������
			String param = "";
			if(!searchValue.equals("")){

				param = "?searchKey=" + searchKey;
				param+= "&searchValue=" + 
						URLEncoder.encode(searchValue, "UTF-8");	
			}
			//�˻�end------------------------------------------------------------------//end 

			// ����¡ ó�� -   ���� �κе� ����� ������ 
			String listUrl = cp+"/not/list.do"+ param ;  //param�� �˻��� ���� // ���� �˻��� ���ߴٸ�, "list.jsp"�� �Ѿ 

			String pageIndexList = 
					myPage.pageIndexList(currentPage,totalPage,listUrl); 

			// arictle �ּ� ���� 
			String articleUrl = cp  +"/not/article.do";

			if(param.equals("")){
				articleUrl += "?pageNum=" + currentPage;	
			}else{
				articleUrl += param + "&pageNum=" + currentPage;
			}


			// ������ �������� �ѱ� ������ 
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

			
			//�˻�------------------------------------------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue"); 

			if(searchValue !=null){ 

				//�Ѿ�� ���� GET��� �϶� 
				if(req.getMethod().equalsIgnoreCase("GET")){ 

					searchValue = URLDecoder.decode(searchValue, "UTF-8") ;//���⼭�� �޴� �Ŷ� decoding 

				}

			}else{

				searchKey = "title";
				searchValue = "";

			}
			//�˻�end------------------------------------------------------------------

			@SuppressWarnings("unused")//�����Ϸ����� "unused" ��� �����϶�� ����
			int dataCount = dao.getDataCount(searchKey,searchValue); 

			
			//������ �б�
			NoticeDTO dto = dao.getReadData(nnum);
			
			if(dto==null){
				url=cp+"/not/list.do";

				resp.sendRedirect(url);

			}

			//�˻�------------------------------------------------------------------

			String param ="pageNum="+pageNum;  //�˻��� �Խù� ������ ������ ��츦 ����

			if(!searchValue.equals("")){

				param += "&searchKey=" + searchKey; //<input type="button" value="����Ʈ"... �Ҷ�> �ٿ��� �ؼ� &�� ����
				param+= "&searchValue=" +
						URLEncoder.encode(searchValue, "UTF-8"); //URLEncoder�� java.net.URLEncoder���� �ؾ��� 
			}
			//�˻�end------------------------------------------------------------------
			
			
			//������, ������ �Ѿ�� ����  arictle �ּ� ���� 
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