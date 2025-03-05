package com.project.pInformation;

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
import com.util.SearchProd;

public class PinformationServlet extends HttpServlet{

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
		PinformationDAO dao = new PinformationDAO(conn);
		SearchProd sep = new SearchProd(conn);
		
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
		
		
		
		if(uri.indexOf("write.do")!=-1) {
			
			url = "/project/pInformation/write.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("write_ok.do")!=-1) {
			
			
			String encType = "UTF-8";
			int maxSize = 100*1024*1024;
			
			MultipartRequest mr = 
					new MultipartRequest(req, path,maxSize,encType,
							new DefaultFileRenamePolicy());
			
			
			if(mr.getFile("upload")!=null) {
				
				int maxPnum=dao.getMaxPnum();
				PinformationDTO dto = new PinformationDTO();
				
				dto.setPnum(maxPnum+1);
				dto.setPname(mr.getParameter("pname"));
				dto.setCategory(mr.getParameter("category"));
				dto.setPrice(Integer.parseInt(mr.getParameter("price")));
				dto.setDay(mr.getParameter("day"));
				dto.setPcount(Integer.parseInt(mr.getParameter("pcount")));
				dto.setSales(Integer.parseInt(mr.getParameter("sales")));
				dto.setInum(Integer.parseInt(mr.getParameter("inum")));
				dto.setSaveFileName(mr.getFilesystemName("upload"));
				dto.setOriginalFileName(mr.getOriginalFileName("upload"));
				
				
				dao.insertData(dto);
			}
			url = cp + "/pinfo/list.do";
			resp.sendRedirect(url);
			
			
		}else if(uri.indexOf("list.do")!=-1) {
			
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			String pageNum = req.getParameter("pageNum");
			String imgName = sep.searchImg(pnum);
			
			int currentPage=1;
			if(pageNum!=null) {
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=dao.getDataCount();
			
			int numPerPage=4;
			
			int totalPage = myPage.getPageCount(numPerPage,dataCount);
			
			if(currentPage>totalPage) {
				currentPage = totalPage;
			}
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			

			String listUrl = cp + "/pinfo/list.do";
			String pageIndexList = 
					myPage.pageIndexList(currentPage, totalPage, listUrl);
			
			String detailUrl = cp + "/pinfo/detail.do";
			
			List<PinformationDTO> lists = dao.getlList(start,end);
			
			String downloadPath=cp+"/pinfo/download.do";
			String deletePath=cp+"/pinfo/delete.do";
			
			
			String imagePath=cp+"/pds/imgFile";
			
			req.setAttribute("imagePath", imagePath);

			req.setAttribute("lists", lists);
			req.setAttribute("downloadPath", downloadPath);
			req.setAttribute("deletePath", deletePath);
			
			
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("pageNum", currentPage);
			req.setAttribute("detailUrl", detailUrl);
			req.setAttribute("imgName", imgName);
			
			
			url = "/project/pInformation/detail.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("detail.do")!=-1){
			
			
			
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			String pageNum = req.getParameter("pageNum");
			String imgName = sep.searchImg(pnum);
			//detail에서 리스트띄우기
			int currentPage=1;
			if(pageNum!=null) {
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=dao.getDataCount();
			
			int numPerPage=4;
			
			int totalPage = myPage.getPageCount(numPerPage,dataCount);
			
			if(currentPage>totalPage) {
				currentPage = totalPage;
			}
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			

			String listUrl = cp + "/pinfo/list.do";
			String pageIndexList = 
					myPage.pageIndexList(currentPage, totalPage, listUrl);
			
			List<PinformationDTO> lists = dao.getlList(start,end);
			
			
			String downloadPath=cp+"/pinfo/download.do";
			String deletePath=cp+"/pinfo/delete.do";
			String imagePath=cp+"/pds/imgFile";
			
			req.setAttribute("imagePath", imagePath);

			req.setAttribute("lists", lists);
			req.setAttribute("downloadPath", downloadPath);
			req.setAttribute("deletePath", deletePath);
			
			
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("pageNum", currentPage);
			req.setAttribute("detailUrl", cp + "/pinfo/detail.do");
			req.setAttribute("imgName", imgName);
			
			//detail에서 리스트띄우기
		
			
			
			//글 가져오기
			PinformationDTO dto = dao.getReadData(pnum);
			
			imgName = sep.searchImg(pnum);
			
			
	
			
		/*	String downloadPath=cp+"/pinfo/download.do";
			String imagePath=cp+"/pds/imgFile";
			String detailUrl = cp + "/pinfo/detail.do";
		*/	
			if(dto==null) {
				url = cp + "pinfo/list.do";
				resp.sendRedirect(url);
				return;
			}

			
			
			req.setAttribute("dto", dto);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("detailUrl", cp + "pinfo/detail.do");
			req.setAttribute("downloadPath", downloadPath);
			req.setAttribute("imgName", imgName);
			
			req.setAttribute("pnum", pnum);
			
			
			url = "/project/pInformation/detail.jsp";
			forward(req, resp, url);

		}
		
		else if(uri.indexOf("delete.do")!=-1) {//DB
			
			String pageNum = req.getParameter("pageNum");
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			
			
			PinformationDTO dto=dao.getReadData(pnum);
			
			
			
			FileManager.doFileDelete(dto.getSaveFileName(), path);
			
			
			
			dao.deleteData(pnum);
			
			url = cp + "/pinfo/list.do?pageNum=" + pageNum;
			resp.sendRedirect(url);
			
		}
	}
}