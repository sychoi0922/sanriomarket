package com.project.faq;

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

import com.util.MyPage;
import com.project.membership.CustomInfo;
import com.util.DBConn;

public class FaqServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void forward (HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {

		RequestDispatcher rd =  req.getRequestDispatcher(url);  
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cp = req.getContextPath();

		Connection conn = DBConn.getConnection();

		FaqDAO  dao = new FaqDAO(conn);

		String uri = req.getRequestURI();
		String url;
		
		
		//------------------------------------
		HttpSession session = req.getSession();
		
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		
		int loginCheck;
		
		
		if (info == null) {
			loginCheck = 0;
		}else {
			loginCheck = 1;
		}
		
		req.setAttribute("loginCheck", loginCheck);
		//---------------------------------------

		if(uri.indexOf("faqlist.do")!=-1) {   


			//ī�װ��� �˻� --- 
			String category = req.getParameter("category");
			
			if(category!=null){

				if(req.getMethod().equalsIgnoreCase("GET")){
					category = URLDecoder.decode(category, "UTF-8");
				}		

			}else{
				
				category = null;
			}
			
		
			//ī�װ��� �˻�end --- 

			int dataCount = dao.getDataCount(); 

			List<FaqDTO> lists  = dao.getLists(category);

			String listUrl = cp+"/question/faqlist.do";  
		
			req.setAttribute("listUrl", listUrl);
			req.setAttribute("lists", lists);
			req.setAttribute("dataCount",dataCount);


			url = "/project/faq/faqlist.jsp";
			forward(req, resp, url);

		}




	}//doPost end



}
