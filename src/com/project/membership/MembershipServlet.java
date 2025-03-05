package com.project.membership;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBConn;
import com.util.MyPage;

public class MembershipServlet extends HttpServlet {

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

		Connection conn = DBConn.getConnection();
		MembershipDAO dao = new MembershipDAO(conn);

		String cp = req.getContextPath();
		String uri = req.getRequestURI();

		String url;
		
		HttpSession session = req.getSession();
		

		if(uri.indexOf("join.do")!=-1) {

			url = "/project/membership/join.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("join_ok.do")!=-1) {

			MembershipDTO dto = new MembershipDTO();

			
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setName(req.getParameter("name"));
			dto.setAddr1(req.getParameter("addr1"));
			dto.setAddr2(req.getParameter("addr2"));
			dto.setAddr3(req.getParameter("addr3"));
			dto.setTel(req.getParameter("tel"));
			dto.setEmail(req.getParameter("email"));
			dto.setBirth(req.getParameter("birth"));
			dto.setChFlag(req.getParameter("chFlag"));

			dao.insertData(dto);

			url = cp + "/pj/login.do";
			resp.sendRedirect(url);



		}else if(uri.indexOf("login.do")!=-1) {

			url = "/project/membership/login.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("login_ok.do")!=-1) {

			String id = req.getParameter("id"); //로그인창에서 입력된 값을 받아냄
			String pwd = req.getParameter("pwd");

			//아이디로 셀렉트 해봐야 함
			MembershipDTO dto = dao.getReadData(id);

			if(dto==null || (!dto.getPwd().equals(pwd))) {//id가 잘못되었으면 or pw가 일치하지 않으면

				req.setAttribute("message",
						"아이디 또는 패스워드를 정확히 입력하세요.");

				url = "/project/membership/login.jsp"; //여기로 데이터를 넘겨 포워드 시킴
				forward(req, resp, url);
				return;

			} 
			//id,pwd가 일치할 때 (id, name을 세션에 하나의 덩어리(클래스)로 만들어서 올리고 Redirect시켜서 cp에 올림)
			//클래스에는 세션이 독자적으로 없음 -> 세션을 만들어야 함 (HttpSession가 가지고있음)

			

			CustomInfo info = new CustomInfo();

			info.setId(dto.getId());
			info.setPwd(dto.getPwd());
			info.setName(dto.getName());
			info.setAddr1(dto.getAddr1());
			info.setAddr2(dto.getAddr2());
			info.setAddr3(dto.getAddr3());
			info.setTel(dto.getTel());
			info.setEmail(dto.getEmail());
			info.setBirth(dto.getBirth());
			info.setChFlag(dto.getChFlag());

			//이 info를 session에 올림
			session.setAttribute("customInfo", info); //이름은 customInfo로

			url = cp + "/project/main/main.jsp";
			resp.sendRedirect(url);	

		}else if(uri.indexOf("logout.do")!=-1) {//세션의 있는걸 지우면 됨 (세션을 또 만들어내야 함)

			

			session.removeAttribute("customInfo"); //변수(info)의 내용(id,name)을 지우는 것
			session.invalidate(); //변수자체를 지우는 것

			url = cp + "/pj/login.do";
			resp.sendRedirect(url);

		}else if(uri.indexOf("findid.do")!=-1) {

			url = "/project/membership/findid.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("findid_ok.do")!=-1) {

			String name = req.getParameter("name");
			String tel = req.getParameter("tel");
			
			MembershipDTO dto = dao.findId(name);

			if(dto==null || (!dto.getTel().equals(tel))) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/project/membership/findid.jsp"; 
				forward(req, resp, url);
				return;

			}else {

				req.setAttribute("message",
						"회원님의 아이디는 [" + dto.getId() + "] 입니다");

				url = "/project/membership/findid.jsp";
				forward(req, resp, url);
				return;
			}

		}else if(uri.indexOf("findpw.do")!=-1) {

			url = "/project/membership/findpw.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("findpw_ok.do")!=-1) {

			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String email = req.getParameter("email");

			MembershipDTO dto = dao.getReadData(id);

			if(dto==null || (!dto.getName().equals(name)) || (!dto.getEmail().equals(email))) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/project/membership/findpw.jsp";
				forward(req, resp, url);
				return;

			}else {

				req.setAttribute("message",
						"회원님의 비밀번호는 [" + dto.getPwd() + "] 입니다");

				url = "/project/membership/findpw.jsp";
				forward(req, resp, url);
				return;

			}

		}else if(uri.indexOf("updated.do")!=-1) {

			
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");

			MembershipDTO dto = dao.getReadData(info.getId());
			
			

			if(dto==null){
				url = cp;
				resp.sendRedirect(url);
			}

			req.setAttribute("dto", dto);

			url = "/project/membership/updated.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("updated_ok.do")!=-1) {

			MembershipDTO dto = new MembershipDTO();

			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setName(req.getParameter("name"));
			dto.setAddr1(req.getParameter("addr1"));
			dto.setAddr2(req.getParameter("addr2"));
			dto.setAddr3(req.getParameter("addr3"));
			dto.setTel(req.getParameter("tel"));
			dto.setEmail(req.getParameter("email"));
			dto.setBirth(req.getParameter("birth")); 
			dto.setChFlag(req.getParameter("chFlag"));

			dao.updateData(dto);

			url = cp + "/pj/mypage.do";
			resp.sendRedirect(url);

		}else if(uri.indexOf("mypage.do")!=-1) {
			
			
		    CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		    
		    if (info == null) {
		    	url = "/project/membership/login.jsp";
		        forward(req, resp, url);
		        return;
		    }
			

			url = "/project/membership/mypage.jsp";
			forward(req, resp, url);

			
		}else if(uri.indexOf("deleted_ok.do")!=-1) {

			String id = req.getParameter("id");
			
			

			dao.deleteData(id);

			url = cp + "/pj/login.do";
			resp.sendRedirect(url);


		}
	}
	
}