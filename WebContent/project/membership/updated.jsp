<%@page import="com.project.membership.MembershipDTO"%>
<%@page import="com.project.membership.MembershipDAO"%>
<%@page import="com.util.DBConn"%>
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
<title>회원 정보 수정</title>

<link rel="stylesheet" href="<%=cp%>/project/css/project.css"
	type="text/css">

<script type="text/javascript" src="<%=cp%>/project/js/util.js"></script>

<script type="text/javascript">

	function sendIt() {
		
		var f = document.myForm;		
		
	    var pwdError = document.getElementById('pwdError');
	    var pwd2Error = document.getElementById('pwd2Error');
 	    var nameError = document.getElementById('nameError');
	    var addr1Error = document.getElementById('addr1Error');
	    var addr2Error = document.getElementById('addr2Error');
	    var addr3Error = document.getElementById('addr3Error');
	    var telError = document.getElementById('telError');
	    var emailError = document.getElementById('emailError');
	    var birthError = document.getElementById('birthError');
	    
	    pwdError.textContent = '';
	    pwd2Error.textContent = '';
	    nameError.textContent = '';
	    addr1Error.textContent = '';
	    addr2Error.textContent = '';
	    addr3Error.textContent = '';
	    telError.textContent = '';
	    emailError.textContent = '';
	    birthError.textContent = '';

		str = f.pwd.value;
		str = str.trim();
		if(!str){
            pwdError.textContent = '패스워드를 입력하세요.';
            pwdError.style.display = 'block';
			f.pwd.focus();
			return;
		}
		f.pwd.value = str;
		
        var pwd = document.myForm["pwd"].value;

        // 비밀번호 길이 검사
        if (pwd.length < 8 || pwd.length > 16) {
            pwdError.textContent = '비밀번호는 8자 이상 16자 이하여야 합니다.';
            pwdError.style.display = 'block';
            f.pwd.focus();
            return false;
        }

        // 정규 표현식들
        var hasUpperCase = /[A-Z]/.test(pwd);
        var hasLowerCase = /[a-z]/.test(pwd);
        var hasNumbers = /[0-9]/.test(pwd);
        var hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(pwd);

        // 사용된 문자 종류의 개수
        var typeCount = 0;
        if (hasUpperCase) typeCount++;
        if (hasLowerCase) typeCount++;
        if (hasNumbers) typeCount++;
        if (hasSpecialChars) typeCount++;

        if (typeCount < 3) {
            pwdError.textContent = '비밀번호는 영문 대소문자, 숫자, 특수문자 중 세 가지 이상을 포함해야 합니다.';
            pwdError.style.display = 'block';
            f.pwd.focus();
            return;
        }
		
		str = f.pwd2.value;
		str = str.trim();
		if(!str){
            pwd2Error.textContent = '패스워드를 한 번 더 입력하세요.';
            pwd2Error.style.display = 'block';
			f.pwd2.focus();
			return;
		}
		f.pwd2.value = str;
		
		 var pwd = document.myForm["pwd"].value;
         var pwd2 = document.myForm["pwd2"].value;

         if (pwd !== pwd2) {
             pwd2Error.textContent = '비밀번호가 일치하지 않습니다.';
             pwd2Error.style.display = 'block';
             f.pwd2.focus();
             return;
        }

		
		str = f.name.value;
		str = str.trim();
		if(!str){
            nameError.textContent = '이름을 입력하세요.';
            nameError.style.display = 'block';
			f.name.focus();
			return;
		}
		f.name.value = str;
		
		
		str = f.addr1.value;
		str = str.trim();
		if(!str){
            addr1Error.textContent = '주소를 입력하세요.';
            addr1Error.style.display = 'block';
			f.addr1.focus();
			return;
		}
		f.addr1.value = str;
		
		
		str = f.addr2.value;
		str = str.trim();
		if(!str){
            addr2Error.textContent = '주소를 입력하세요.';
            addr2Error.style.display = 'block';
			f.addr2.focus();
			return;
		}
		f.addr2.value = str;
		
		
		str = f.addr3.value;
		str = str.trim();
		if(!str){
            addr3Error.textContent = '주소를 입력하세요.';
            addr3Error.style.display = 'block';
			f.addr3.focus();
			return;
		}
		f.addr3.value = str;
		
		
		str = f.tel.value;
		str = str.trim();
		if(!str){
            telError.textContent = '전화번호를 입력하세요.';
            telError.style.display = 'block';
			f.tel.focus();
			return;
		}
		f.tel.value = str;
		
        var tel = document.myForm["tel"].value;
        var regex = /^\d{11}$/;

        if (!regex.test(tel)) {
            telError.textContent = '11자리 숫자로 입력해주세요.';
            telError.style.display = 'block';
            f.tel.focus();
            return;
        }
		
		
 		str = f.email.value;
		str = str.trim();
		if(!str){
            emailError.textContent = '이메일을 입력하세요.';
            emailError.style.display = 'block';
			f.email.focus();
			return;
			
		}else if(!isValidEmail(str)){
            emailError.textContent = '정상적인 E-Mail을 입력하세요.';
            emailError.style.display = 'block';
			f.email.focus();
			return;	
		}
		f.email.value = str;
		
		
		str = f.birth.value;
		str = str.trim();
		if(!str){
            birthError.textContent = '생일을 입력하세요.';
            birthError.style.display = 'block';
			f.birth.focus();
			return;
		}
		f.birth.value = str;
		
        var birth = document.myForm["birth"].value;
        var regex = /^\d{8}$/;

        if (!regex.test(birth)) {
            birthError.textContent = '8자리 숫자로 입력해주세요.';
            birthError.style.display = 'block';
            f.birth.focus();
            return;
        }

		
        alert("회원정보가 수정되었습니다.");
		f.action = "<%=cp%>/pj/updated_ok.do";
		f.submit();

	}
	
	
	function deleteIt() {
		
		var f = document.myForm;
		
        if (confirm("정말로 탈퇴하시겠습니까?")) {
            
            f.action = "<%=cp%>/pj/deleted_ok.do"; 
            f.submit();
        }

	}
</script>



</head>
<body>

	<jsp:include page="../header.jsp" />
	<div
		style="top: 50%; right: 5%; z-index: 1000; margin-right: 50px; height: 269px; width: 42.5px; position: fixed; margin-right: 20px;">
		<li><a href="#top"> <img
				src="<%=cp%>/project/image/gotop.png" alt=""
				style="height: 42px; width: 42px; margin-bottom: 60px; background-color: #e47195; border-radius: 10%;"></a></li>
		<li
			style="background-color: #e47195; text-align: center; border-radius: 50%;"><a
			href="<%=cp%>/rev/list.do">리&nbsp;뷰</a></li>

		<li><a href="#scrollbottom"> <img
				src="<%=cp%>/project/image/godown.png" alt=""
				style="height: 42px; width: 42px; margin-top: 60px; background-color: #e47195; border-radius: 10%;"></a></li>
	</div>
	<div id="container">
		<div id="contents_sub">

			<div class="path">
				<ol>
					<li><a href="<%=cp%>/view/main.do">홈</a></li>
					<li title="현재 위치"><strong>회원 정보 수정</strong></li>
				</ol>
			</div>

			<div class="titleArea">
				<h2>회원 정보 수정</h2>
			</div>

			<div class="xans-element- xans-myshop xans-myshop-asyncbenefit">
				<div class="ec-base-box typeMember gMessage ">
					<div class="information">
						<p class="thumbnail">
							<img
								src="//img.echosting.cafe24.com/skin/base_ko_KR/member/img_member_default.gif"
								class="myshop_benefit_group_image_tag">
						</p>
						<div class="description">
							<p>
								저희 쇼핑몰을 이용해 주셔서 감사합니다. <strong class="txtEm"> <span>
										<span class="xans-member-var-name">${dto.id }</span>
								</span></strong> 님은 <strong>[<span class="xans-member-var-group_name">오리엔탈</span>
									<span class="myshop_benefit_ship_free_message"></span>]
								</strong> 회원이십니다.
							</p>
						</div>
					</div>
				</div>
			</div>
			<br />
			<br />

			<form name="myForm" action="" method="get"
				enctype="multipart/form-data">

				<input type="hidden" name="id" value="${dto.id}">

				<h3>&nbsp;&nbsp;기본정보</h3>
				<p class="required " align="right">
					<img
						src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
						style="overflow-clip-margin: content-box; overflow: clip; vertical-align: middle;">
					필수입력사항
				</p>

				<div class="ec-base-table typeWrite">
					<table border="1">
						<colgroup>
							<col style="width: 150px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th>아이디 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"></th>
								<td>${dto.id }</td>
							</tr>

							<tr>
								<th>비밀번호 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td>
									<div class="eTooltip">
										<input name="pwd" maxlength="16" type="password"> (영문
										대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자) <span id="pwdError"
											class="error-message"></span>
									</div>
								</td>
							</tr>

							<tr>
								<th>비밀번호 확인 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input name="pwd2" maxlength="16" type="password">
									<span id="pwd2Error" class="error-message"></span></td>
							</tr>

							<tr>
								<th id="nameTitle">이름 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input type="text" name="name" id="name" maxlength="20"
									value="${dto.name }"><span id="nameError"
									class="error-message"></span></td>
							</tr>

							<tr class="">
								<th>주소 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"></th>
								<td><ul class="ec-address">
										<li id="join_detailAddr_wrap"><input id="addr1"
											name="addr1" type="text" size="60" maxlength="100"
											value="${dto.addr1 }"><span id="addr1Error"
											class="error-message"></span></li>
										<li id="join_detailAddr_wrap"><input id="addr2"
											name="addr2" type="text" size="60" maxlength="100"
											value="${dto.addr2 }"><span id="addr2Error"
											class="error-message"></span></li>
										<li id="join_detailAddr_wrap"><input id="addr3"
											name="addr3" type="text" size="60" maxlength="100"
											value="${dto.addr3 }"><span id="addr3Error"
											class="error-message"></span></li>
									</ul></td>
							</tr>

							<tr>
								<th scope="row">휴대전화 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									class="" alt="필수"></th>
								<td><input name="tel" maxlength="11" type="text"
									value="${dto.tel }">
									<p class="txtWarn gBlank5 displaynone"
										id="result_send_verify_mobile_fail">
										<span id="telError" class="error-message"></span>
									</p></td>
							</tr>

							<tr>
								<th>이메일 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input name="email" type="text" value="${dto.email }"><span
									id="emailError" class="error-message"></span></td>
							</tr>

							<tr>
								<th>평생회원</th>
								<td><input name="chFlag" value="T" type="radio"> <label
									for="chFlag">동의함</label> <input name="chFlag" value="F"
									type="radio" checked="checked"> <label for="chFlag">동의안함</label>
									<ul class="txtDesc">
										<li>평생회원은 <strong>산리오마켓</strong> 회원 탈퇴시까지 휴면회원으로 전환되지
											않습니다.
										</li>
									</ul></td>
							</tr>

						</tbody>
					</table>
				</div>

				<br />

				<h3>&nbsp;&nbsp;추가정보</h3>
				<div class="ec-base-table typeWrite ">
					<table border="1">

						<!-- <caption>회원 추가정보</caption> -->
						<colgroup>
							<col style="width: 150px;">
							<col style="width: auto;">
						</colgroup>

						<tbody>
							<tr class="">
								<th>생년월일 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"></th>
								<td><input name="birth" class="inputTypeText" maxlength="8"
									type="text" value="${dto.birth }"> <span
									id="birthError" class="error-message"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<br />
				<br />
				<br />


				<div>
					<h3
						style="display: block; padding: 0; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px; font-weight: bold; unicode-bidi: isolate;">&nbsp;&nbsp;개인정보
						수집 및 이용 동의 (선택)</h3>
					<div class="ec-base-box typeThinBg agreeArea"
						style="display: block; unicode-bidi: isolate; color: #747474; line-height: 20px; border-width: 1px; border-color: #d7d5d5; background-color: #fbfafa;">
						<div class="content">
							<div class="fr-view fr-view-privacy-optional"
								style="overflow-y: scroll; height: 100px; background-color: #fff; border: ridge;">
								1. 개인정보 수집목적 및 이용목적<br>회원가입 시 입력받는 선택 항목의 이용 목적 <br>:
								마케팅 활용<br> <br>2. 수집하는 개인정보 항목<br>회원가입 시 입력받는 선택
								항목 기재<br>: 생년월일<br> <br>3. 개인정보의 보유기간 및 이용기간<br>회원탈퇴
								또는 회원정보 수정을 통해 해당 항목 삭제 시 지체없이 파기합니다.<br> <br>※ 동의를
								거부하시는 경우에도 서비스는 이용이 가능합니다.
							</div>
						</div>
						<p class="check">
							<span>개인정보 수집 및 이용에 동의하십니까?</span> <input
								id="agree_privacy_optional_check0"
								name="agree_privacy_optional_check[]" value="T" type="checkbox">
						</p>
					</div>
				</div>
				<br />

				<div class="ec-base-button justify">

					<a href="#" class="btnSubmitFix sizeM" onclick="sendIt();">회원정보수정</a>
					<a href="<%=cp%>/project/main.jsp" class="btnEmFix sizeM">취소</a> <input
						type="hidden" name="id" value="${dto.id}"> <span
						class="gRight"> <a href="#" class="btnNormal sizeS"
						onclick="deleteIt();">회원탈퇴</a>
					</span>
				</div>
				<br />
			</form>
		</div>
	</div>

	<jsp:include page="../footer.jsp" />

</body>
</html>