<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

<link rel="stylesheet" href="<%=cp%>/project/css/project.css" type="text/css">

<script type="text/javascript">

	function login() {
		
		var f = document.myForm;
		
		if(!f.id.value){
			alert("아이디를 입력하세요.");		
			f.id.focus();
			return;
		}
		
		if(!f.pwd.value){
			alert("패스워드를 입력하세요.");		
			f.pwd.focus();
			return;
		}

		f.action = "<%=cp%>/pj/login_ok.do";
		f.submit();
		
	}
	
	function message() {
		
		alert("비회원으로는 주문조회가 불가합니다. 회원가입 후 이용해주세요.")
		
	}

	
</script>

</head>
<body>

	<jsp:include page="../header.jsp" />

	<div class="path" style="width: 70%;">
		<span>현재 위치</span>
		<ol>
			<li><li><a href="<%=cp%>/view/main.do">홈</a></li>
			<li title="현재 위치"><strong>로그인</strong></li>
		</ol>
	</div>

	<div class="titleArea">
		<h2>로그인</h2>
	</div>

	<form name="myForm" method="get" enctype="multipart/form-data">
		<div class="xans-element- xans-member xans-member-login ">
			<div class="LOGIN_tab">
				<ul class="btn">
					<li class="on"><a href="<%=cp%>/project/login.jsp">회원로그인</a></li>
					<li><a href="#" onclick="message();">비회원 주문조회</a></li>
				</ul>
			</div>

			<div class="login">
				<fieldset>
					<legend>회원로그인</legend>
					<label class="id ePlaceholder" title="아이디">
					<input name="id" placeholder="아이디" type="text"></label> 
					<label class="password ePlaceholder" title="패스워드">
					<input name="pwd" type="password" placeholder="패스워드"></label>
					<p class="security">
						<img
							src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_access.png"
							alt="보안접속"> 보안접속
					</p>
					<a href="#" onclick="login();">로그인</a>
					<ul>
						<li><a href="<%=cp%>/pj/findid.do">아이디 찾기</a></li>
						<li><a href="<%=cp%>/pj/findpw.do">비밀번호 찾기</a></li>
						<li><a href="<%=cp%>/pj/join.do">회원가입</a></li>
					</ul>


<c:if test="${!empty message }">
<font color="red" style="margin: auto;"><b>${message }</b></font>
</c:if>


					<p class="joinus">
						<a href="<%=cp%>/pj/join.do">회원가입</a> 회원가입을 하시면 다양하고 특별한 혜택이
						준비되어 있습니다.
					</p>

				</fieldset>
			</div>
		</div>
	</form>

	<jsp:include page="../footer.jsp" />

</body>
</html>