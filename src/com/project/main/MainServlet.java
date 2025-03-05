package com.project.main;

import java.io.File;
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

import com.project.image.ImageDTO;
import com.project.membership.CustomInfo;
import com.project.pInformation.PinformationDTO;
import com.util.DBConn;
import com.util.SearchProd;

public class MainServlet extends  HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void forward(HttpServletRequest req, HttpServletResponse resp,String url) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath();

		Connection conn = DBConn.getConnection();

		MainDAO mainDao = new MainDAO(conn);


		String uri = req.getRequestURI();
		String url;

		HttpSession session = req.getSession();

		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");

		String root = getServletContext().getRealPath("/");
		String path = root + "pds" + File.separator + "imgFile";

		File f = new File(path);
		if(!f.exists() ) {
			f.mkdirs();
		}


		if(uri.indexOf("main.do")!=-1) {
			List<ImageDTO> imageLists = mainDao.getRanImageData();
			List<PinformationDTO> pInfoLists = mainDao.getRanPinfoData();
			SearchProd sp = new SearchProd(conn);


			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			if(searchValue!=null) {// ˻        

				//      
				if(req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue,"UTF-8");
				}

				int currentPage=1;

				int dataCount= mainDao.getImageDataCount(searchKey, searchValue);






				// ˻ ---------------------------------------------------1
				String param ="";
				if(!searchValue.equals("")) {//

					param ="?searchKey=" + searchKey;
					param+="&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); ;
				}
				// ˻ ---------------------------------------------------1
				
				
				
				

				String imagePath = cp+"/pds/imgFile";


				
				req.setAttribute("imageLists", imageLists);
				req.setAttribute("pInfoLists", pInfoLists);

				req.setAttribute("dataCount", dataCount);
				req.setAttribute("pageNum", currentPage);


				url = "/project/main/main.jsp";
				forward(req,resp,url);
			}

			String imagePath=cp+"/pds/imgFile";
			
			
			
			req.setAttribute("sp", sp);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("imageLists", imageLists);
			req.setAttribute("pInfoLists", pInfoLists);
			

			url= "/project/main/main.jsp";
			forward(req, resp, url);
		}

	}
}
