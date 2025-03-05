package com.project.basket;

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

public class BasketServlet extends HttpServlet{
	
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
		
		req.setCharacterEncoding("UTF-8");
		String cp=req.getContextPath();
		
		Connection conn = DBConn.getConnection();
		BasketDAO basDao = new BasketDAO(conn);
		
		
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
		
		
		
		if(uri.indexOf("basket.do")!=-1) {
			
			//평범한 웹브라우저에 리스트 출력시키는 서블릿
			
			List<BasketDTO> lists = basDao.getLists();
			
			int totalPrice = 0;
			
			for (BasketDTO basDto : lists) {
				
				totalPrice += basDto.getBasketCount() * basDto.getPrice();
				
			}
			
			String imagePath = cp + "/pds/imgFile";
			req.setAttribute("imagePath", imagePath);
			
			req.setAttribute("lists", lists);
			
			int size = lists.size();
			req.setAttribute("size", size);
			req.setAttribute("totalPrice", totalPrice);
			
			url = "/project/basket.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("deleteBasketCheck.do")!=-1) {
			
			
			//체크박스로 선택한 물품들의 pnum을 받아와서 배열 형태로 저장한다.
			//이를 바탕으로 조건문을 설정해 선택된 데이터만 장바구니에서 지운다.
			String[] selectedPnum = req.getParameterValues("check");
			int result = basDao.deleteBasketCheck(selectedPnum);
			
			url = cp+"/basket/basket.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("deleteBasketAll.do")!=-1) {
			
			
			//그냥 별다를 것 없이 싹 다 지우는 거다.
			int result = basDao.deleteBasketAll();
			
			url = cp+"/basket/basket.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("deleteBasketOne.do")!=-1) {
			
			
			//하나의 데이터의 pnum만 가져와서 삭제한다.
			String pnumStr = req.getParameter("pnum");
			
			int pnum = Integer.parseInt(pnumStr);
			
			int result = basDao.deleteBasketOne(pnum);
			
			url = cp+"/basket/basket.do";
			resp.sendRedirect(url);
			
			
		}else if(uri.indexOf("buyBasketCheck.do")!=-1) {
			
			
			//역시 체크박스로 선택한 물품들의 pnum을 받아와서
			//배열형태로 저장, 이를 바탕으로 조건문을 적어
			//데이터를 가공해 purchased로 이동한다.
			String[] selectedPnum = req.getParameterValues("check");
			
			int result = basDao.buyBasketCheck(selectedPnum ,id);
			
			url = cp+"/purchased/purcased.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("buyBasketAll.do")!=-1) {
			
			//그냥 모든 데이터를 가공해 purchased로 이동시킨다.
			int result = basDao.buyBasketAll(id);
			
			url = cp+"/purchased/purchased.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("buyBasketOne.do")!=-1) {
			
			
			//한 개의 데이터의 pnum만을 받아서 조건문을 걸고 데이터를 가공해 
			//purchased로 이동시킨다.
			String pnumStr = req.getParameter("pnum");
			
			int pnum = Integer.parseInt(pnumStr);
			
			int result = basDao.buyBasketOne(pnum,id);
			
			url = cp+"/purchased/purchased.do";
			resp.sendRedirect(url);
			
			
		}else if(uri.indexOf("countChange.do")!=-1) {
			
			//장바구니 수량 변경을 위한 서블릿.
			//변경된 수량의 값과 그 데이터의 pnum을 받아와서
			//조건문을 바탕으로 장바구니 테이블의 데이터를 업데이트한다.
			String countStr = req.getParameter("count");
			String pnumStr = req.getParameter("pnum");
			
			int count = Integer.parseInt(countStr);
			int pnum = Integer.parseInt(pnumStr);
			
			int result = basDao.countChange(count, pnum);
			
			url = cp+"/basket/basket.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("moveBasket")!=-1) {
			
			//아직은 쓰지 않는 서블릿
			//이미지게시판에서 장바구니 넣기를 눌렀을 때 실행될 서블릿이다.
			//역시 session 과 href 두 경우의 수 모두를 상정해 일단 저장해놨다.
			//int pnumSession = (Integer)session.getAttribute("pnumSession");
			//int inumSession = (Integer)session.getAttribute("inumSession");
			//int countSession = (Integer)session.getAttribute("countSession");
			
			
			
			int pnumReq = Integer.parseInt(req.getParameter("pnum"));
			String imgName = req.getParameter("imgName");
			int countReq = Integer.parseInt(req.getParameter("quantity"));
			
			
			
			int result = basDao.moveBasket(pnumReq, imgName, countReq, id);
			
			System.out.println(result);
			
			url = cp+"/basket/basket.do";
			resp.sendRedirect(url);
			
			
		}
		
		
		
		
		
	}

}