package com.project.cancel;

import java.io.File;
import java.io.IOException;
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

public class CancelServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


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

		
		//��ҳ��� ���̺��� ���ų��� ���̺�� ���� 80~90�� ��ġ�Ѵ�.
		//�׷��� �뷫���� ������ ���ų��� table, purchased�� dao�� ������ ����.
		
		req.setCharacterEncoding("UTF-8");
		String cp=req.getContextPath();
		
		Connection conn = DBConn.getConnection();
		CancelDAO canDao = new CancelDAO(conn);

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
		
		//�α������� �ʾ� session�� �÷��� �α��� ������ ���� ��� ������������ �̵�.
		if (info == null) {
			url = "/project/membership/login.jsp";
			forward(req, resp, url);
			return;
		}
		
		String id = info.getId();

		//String id = (String) session.getAttribute("id");	//�������� ���̵� �޾ƿ´� ����.
		//delete���� ���̵� �ʿ������ ���ſ��� �ʿ��ϴ�.

		//String fid = "suzi123";		//�ӽ� ���̵�. ��ĥ �� ���� fid�� id�� �ٲ�����.
		



		if(uri.indexOf("cancel.do")!=-1) {

			String dayCheck = req.getParameter("daySelect");
			List<CancelDTO> lists = null;
			
			String pageNum=req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null){
				
				currentPage = Integer.parseInt(pageNum);
				
			}
			
			//��ü ������ ����
			int dataCount =  0;
			
			//���������� ����� �������� ����
			int numPerPage = 10;
			
			int totalPage = 0;
			
			//System.out.print(totalPage); <-������ ���� �߰��� Ȯ��
			
			//�����ͺ��̽����� ������ rownum�� ���۰� ����ġ
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			//=int end = start + numPerPage - 1;
			

			if (dayCheck != null && !dayCheck.isEmpty()) {

				int daySelect = Integer.parseInt(dayCheck);
				String dayMessage;

				switch (daySelect) {

				case 1:

					dayMessage = "TRUNC(buyday) = TRUNC(SYSDATE)";
					lists = canDao.getListsDay(dayMessage,id,start,end);
					dataCount =  canDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);

					break;

				case 2:

					dayMessage = "buyday >= TRUNC(SYSDATE - 7)";
					lists = canDao.getListsDay(dayMessage,id,start,end);
					dataCount =  canDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);

					break;

				case 3:

					dayMessage = "buyday >= TRUNC(ADD_MONTHS(SYSDATE, -1))";
					lists = canDao.getListsDay(dayMessage,id,start,end);
					dataCount =  canDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);

					break;

				case 4:

					dayMessage = "buyday >= TRUNC(ADD_MONTHS(SYSDATE, -3))";
					lists = canDao.getListsDay(dayMessage,id,start,end);
					dataCount =  canDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);

					break;

				case 5:

					dayMessage = "buyday >= TRUNC(ADD_MONTHS(SYSDATE, -6))";
					lists = canDao.getListsDay(dayMessage,id,start,end);
					dataCount =  canDao.getDataCount(id, dayMessage);
					totalPage = myPage.getPageCount(numPerPage, dataCount);

					break;


				default:

					lists = canDao.getLists(id,start,end);
					dataCount =  canDao.getDataCount(id);
					totalPage = myPage.getPageCount(numPerPage, dataCount);

				}


			}

			else {

				lists = canDao.getLists(id,start,end);
				dataCount =  canDao.getDataCount(id);
				totalPage = myPage.getPageCount(numPerPage, dataCount);


			}
			
			//��ü������ ������ ǥ���� �������� ū ���
			//ǥ���� �������� ��ü �������� ����� ���� �ڵ�
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			
			
			//����¡ ó��
			String listUrl = cp + "/cancel/cancel.do";
			String pageIndexList = myPage.pageIndexList(currentPage, totalPage, listUrl);


			String imagePath = cp + "/pds/imgFile";
			req.setAttribute("imagePath", imagePath);
			
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", currentPage);
			
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", currentPage);

			req.setAttribute("lists", lists);
			

			url = "/project/cancel.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("browseDate.do")!=-1) {
			
			String startDateStr = req.getParameter("startDate");
			String endDateStr = req.getParameter("endDate");
			
			String pageNum=req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null){
				
				currentPage = Integer.parseInt(pageNum);
				
			}
			
			//��ü ������ ����
			int dataCount =  canDao.getDataCount(id, startDateStr, endDateStr);
			
			//���������� ����� �������� ����
			int numPerPage = 10;
			
			int totalPage = myPage.getPageCount(numPerPage, dataCount);
			
			//System.out.print(totalPage); <-������ ���� �߰��� Ȯ��
			
			//��ü������ ������ ǥ���� �������� ū ���
			//ǥ���� �������� ��ü �������� ����� ���� �ڵ�
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			
			//�����ͺ��̽����� ������ rownum�� ���۰� ����ġ
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			//=int end = start + numPerPage - 1;
			
			
			List<CancelDTO> lists = null;
			
			lists = canDao.getListsDay(startDateStr, endDateStr, id, start, end);
			
			//����¡ ó��
			String listUrl = cp + "/cancel/cancel.do";
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
			
			
			url = "/project/cancel.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("cancelDelete.do")!=-1) {
			
			int pid = Integer.parseInt(req.getParameter("pid"));
			
			
			int result = canDao.cancelDelete(pid);
			
			
			url = cp+"/cancel/cancel.do";
			resp.sendRedirect(url);
			
			
		}



	}

}

