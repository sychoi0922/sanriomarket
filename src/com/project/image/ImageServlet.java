package com.project.image;

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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.membership.CustomInfo;
import com.util.DBConn;
import com.util.FileManager;
import com.util.MyPage;

public class ImageServlet extends HttpServlet{

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
		ImageDAO dao = new ImageDAO(conn);
		
		
		MyPage myPage = new MyPage();
		
		String uri = req.getRequestURI();
		String url;
		
		String root = getServletContext().getRealPath("/");
		String path = root + "pds" + File.separator + "imgFile";
		
		File f = new File(path);
		if(!f.exists() ) {
			f.mkdirs();
		}
		
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
		
		if(uri.indexOf("write.do")!=-1) {
			
			url = "/project/image/write.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("write_ok.do")!=-1) {
			
			
			String encType = "UTF-8";
			int maxSize = 100*1024*1024;
			
			MultipartRequest mr = 
					new MultipartRequest(req, path,maxSize,encType,
							new DefaultFileRenamePolicy());
			
			
			if(mr.getFile("upload")!=null) {
				
				int maxInum=dao.getMaxInum();
				ImageDTO dto = new ImageDTO();
				dto.setInum(maxInum+1);
				dto.setSubject(mr.getParameter("subject"));
				dto.setSaveFileName(mr.getFilesystemName("upload"));
				dto.setOriginalFileName(mr.getOriginalFileName("upload"));
				
				dao.insertData(dto);
			}
			url = cp + "/img/list.do";
			resp.sendRedirect(url);
			
			
		}else if(uri.indexOf("list.do")!=-1) {
			
			String pageNum = req.getParameter("pageNum");
			int currentPage=1;
			if(pageNum!=null) {
				currentPage=Integer.parseInt(pageNum);
			}
			
			//
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null) {//
				
				//
				if(req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue,"UTF-8");
				}
			}else {
				searchKey = "subject";
				
			
				searchValue = "";
			}
			

			String category = req.getParameter("category");
			
			
			int dataCount;
			
			int numPerPage = 9;
			
			List<ImageDTO> lists = null;
			
			
			if (category != null && !category.isEmpty()) {
	            
				dataCount=dao.getDataCount(category, searchKey,searchValue);
				
				
				
	        } else {
	        	
	        	dataCount=dao.getDataCount(searchKey,searchValue);
	        	
	        	
	        }
			
						
			
			int totalPage = myPage.getPageCount(numPerPage,dataCount);
			
			if(currentPage>totalPage) {
				currentPage = totalPage;
			}
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			if (category != null && !category.isEmpty()) {
	            
				lists = dao.getlList(start,end,category,searchKey,searchValue);//
				
				
				
	        } else {
	        	
	        	lists = dao.getlList(start,end,searchKey,searchValue);//
	        	
	        	
	        }
			
			
			//
			String param ="";
			if(!searchValue.equals("")) {//
				
				param ="?searchKey=" + searchKey;
				param+="&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); ;
			}
			//
			
			String listUrl = cp + "/img/list.do";
			String pageIndexList = 
					myPage.pageIndexList(currentPage, totalPage, listUrl);
			
			
			
			String downloadPath=cp+"/img/download.do";
			String deletePath=cp+"/img/delete.do";
			
			
			String imagePath=cp+"/pds/imgFile";
			req.setAttribute("imagePath", imagePath);
			
			
			
			
			req.setAttribute("lists", lists);
			req.setAttribute("downloadPath", downloadPath);
			req.setAttribute("deletePath", deletePath);
			
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("pageNum", currentPage);
			
			
			
			url = "/project/image/list.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("delete.do")!=-1) {//DB
			
			String pageNum = req.getParameter("pageNum");
			int inum = Integer.parseInt(req.getParameter("inum"));
			ImageDTO dto=dao.getReadData(inum);
			
			
			
			FileManager.doFileDelete(dto.getSaveFileName(), path);
			
			
			
			dao.deleteData(inum);
			
			url = cp + "/img/list.do?pageNum=" + pageNum;
			resp.sendRedirect(url);
			
		}
	}
}