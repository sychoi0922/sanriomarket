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
<title>회원 가입</title>

<link rel="stylesheet" href="<%=cp%>/project/css/project.css" type="text/css">
<script type="text/javascript" src="<%=cp%>/project/js/util.js"></script>	

<script type="text/javascript">

	function sendIt() {
		
		var f = document.myForm;
		
	    var idError = document.getElementById('idError');
	    var pwdError = document.getElementById('pwdError');
	    var pwd2Error = document.getElementById('pwd2Error');
 	    var nameError = document.getElementById('nameError');
	    var addr1Error = document.getElementById('addr1Error');
	    var addr2Error = document.getElementById('addr2Error');
	    var addr3Error = document.getElementById('addr3Error');
	    var telError = document.getElementById('telError');
	    var emailError = document.getElementById('emailError');
	    var birthError = document.getElementById('birthError');
	    
	    idError.textContent = '';
	    pwdError.textContent = '';
	    pwd2Error.textContent = '';
	    nameError.textContent = '';
	    addr1Error.textContent = '';
	    addr2Error.textContent = '';
	    addr3Error.textContent = '';
	    telError.textContent = '';
	    emailError.textContent = '';
	    birthError.textContent = '';


		str = f.id.value;
		str = str.trim();
		if(!str){
            idError.textContent = '아이디를 입력하세요.';
            idError.style.display = 'block';
			f.id.focus();
			return;
		}
		f.id.value = str;
		
 		var id = document.myForm["id"].value;
        var regex = /^[a-z0-9]{4,16}$/;
        

        if (!regex.test(id)) {
            idError.textContent = '아이디는 영문 소문자와 숫자로만 구성되며, 4자에서 16자 사이여야 합니다.';
            idError.style.display = 'block';
            f.id.focus();
            return;
        }
		
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
		
        
 	    var agree1 = document.getElementById("agree1");
	    var agree2 = document.getElementById("agree2");

	    if (!agree1.checked) {
	        alert("필수 이용약관에 동의해주세요.");
	        agree1.focus();
	        return;
	    }
	    f.agree1.value = agree1;
	    
	    if (!agree2.checked) {
	        alert("필수 이용약관에 모두 동의해주세요.");
	        agree2.focus();
	        return;
	    }
	    f.agree2.value = agree2;
	   
		
        alert("회원가입이 완료되었습니다.");
		f.action = "<%=cp%>/pj/join_ok.do";
		f.submit();

	}
</script>

</head>
<body>

	<jsp:include page="../header.jsp" />

	<div id="container">
		<div id="contents_sub" style="width: 60%;">

			<div class="path">
				<span>현재 위치</span>
				<ol>
					<li><li><a href="<%=cp%>/view/main.do">홈</a></li>
					<li title="현재 위치"><strong>회원 가입</strong></li>
				</ol>
			</div>

			<div class="titleArea">
				<h2>회원 가입</h2>
			</div>


			<form name="myForm" action="" method="post" nctype="multipart/form-data">

				<div class="ec-base-table typeWrite">
					<table border="1" summary="">
						<caption>회원가입</caption>
						<colgroup>
							<col style="width: 150px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr class="">
								<th scope="row">회원구분 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input id="member_type0" name="member_type" value="p"
									type="radio" checked="checked">
									<label for="member_type0">개인회원</label> 
									<input id="member_type2" name="member_type" value="f" 
									type="radio">
									<label for="member_type2">외국인회원(foreigner)</label></td>
							</tr>
						</tbody>
					</table>
				</div>

				<br /> <br />

				<h3 class=" ">&nbsp;&nbsp;기본정보</h3>
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
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input name="id" class="inputTypeText" type="text"> (영문소문자/숫자, 4~16자)
								<span id="idError" class="error-message"></span></td>
							</tr>
							
							<tr>
								<th>비밀번호 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td>
									<div class="eTooltip">
										<input name="pwd" maxlength="16" type="password"> (영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자)
									<span id="pwdError" class="error-message"></span></div>
								</td>
							</tr>
							
							<tr>
								<th>비밀번호 확인 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input name="pwd2" maxlength="16" type="password"> <span id="pwd2Error" class="error-message"></span></td>
							</tr>

							<tr>
								<th id="nameTitle">이름 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									alt="필수"></th>
								<td><input type="text" name="name" id="name" maxlength="20"><span id="nameError" class="error-message"></span></td>
							</tr>

							<tr class="">
								<th>주소 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									></th>
								<td><ul class="ec-address">
										<li id="join_detailAddr_wrap">
										<input id="addr1" name="addr1" placeholder="시, 군/구" type="text" size="60" maxlength="100"><span id="addr1Error" class="error-message"></span></li>
										<li id="join_detailAddr_wrap">
										<input id="addr2" name="addr2" placeholder="도로명 주소" type="text" size="60" maxlength="100"><span id="addr2Error" class="error-message"></span></li>
										<li id="join_detailAddr_wrap">
										<input id="addr3" name="addr3" placeholder="상세 주소" type="text" size="60" maxlength="100"><span id="addr3Error" class="error-message"></span></li>
									</ul></td>
							</tr>

							<tr>
								<th scope="row">휴대전화 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									class="" alt="필수"></th>
								<td><input name="tel" maxlength="11"
									placeholder="'-' 제외, 11자리 입력" type="text">
									<p class="txtWarn gBlank5 displaynone"
										id="result_send_verify_mobile_fail"><span id="telError" class="error-message"></span></p></td>
							</tr>
							
							<tr>
							<th>이메일 <img
								src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
								alt="필수"></th>
							<td><input name="email" type="text"><span id="emailError" class="error-message"></span></td>
							</tr>

							<tr>
								<th>평생회원</th>
								<td><input name="chFlag" value="T" type="radio">
								<label for="chFlag">동의함</label> 
								<input name="chFlag" value="F" type="radio" checked="checked">
								<label for="chFlag">동의안함</label>
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
					<table border="1" >
					
						<!-- <caption>회원 추가정보</caption> -->
						<colgroup>
							<col style="width: 150px;">
							<col style="width: auto;">
						</colgroup>
						
						<tbody>
							<tr class="">
								<th >생년월일 <img
									src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/ico_required_blue.png"
									></th>
								<td><input name="birth" class="inputTypeText" placeholder="ex.19900101"
									maxlength="8" type="text"><span id="birthError" class="error-message"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<br/><br/><br/>

				<div class="ec-base-box typeThinBg agreeArea"
					style="display: block; unicode-bidi: isolate;">
					<h3>[필수] 이용약관 동의</h3>
					<div class="content">
						<div class="fr-view fr-view-mall-agreement"
							style="word-wrap: break-word; display: block; unicode-bidi: isolate;">
							<p
								style="padding: 0 0 17px; overflow-y: scroll; height: 200px; margin: 0; background-color: #fff; border: ridge; display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px; unicode-bidi: isolate; word-wrap: break-word;">
								제1조(목적) <br>이 약관은 샐러드마켓(전자상거래 사업자)가 운영하는 샐러드마켓 사이버 몰(이하
								“몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의
								권리․의무 및 책임사항을 규정함을 목적으로 합니다. <br>※「PC통신, 무선 등을 이용하는 전자상거래에
								대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다.」 <br>제2조(정의) <br>①
								“몰”이란 샐러드마켓이 재화 또는 용역(이하 “재화 등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등
								정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는
								사업자의 의미로도 사용합니다. <br>② “이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는
								서비스를 받는 회원 및 비회원을 말합니다. <br>③ ‘회원’이라 함은 “몰”에 회원등록을 한 자로서,
								계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다. <br>④ ‘비회원’이라 함은 회원에
								가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다. <br>제3조 (약관 등의 명시와 설명
								및 개정) <br>① “몰”은 이 약관의 내용과 상호 및 대표자 성명, 영업소 소재지 주소(소비자의 불만을
								처리할 수 있는 곳의 주소를 포함), 전화번호․모사전송번호․전자우편주소, 사업자등록번호, 통신판매업 신고번호,
								개인정보관리책임자등을 이용자가 쉽게 알 수 있도록 00 사이버몰의 초기 서비스화면(전면)에 게시합니다. 다만,
								약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다. <br>② “몰은 이용자가 약관에
								동의하기에 앞서 약관에 정하여져 있는 내용 중 청약철회․배송책임․환불조건 등과 같은 중요한 내용을 이용자가 이해할
								수 있도록 별도의 연결화면 또는 팝업화면 등을 제공하여 이용자의 확인을 구하여야 합니다. <br>③
								“몰”은 「전자상거래 등에서의 소비자보호에 관한 법률」, 「약관의 규제에 관한 법률」, 「전자문서 및
								전자거래기본법」, 「전자금융거래법」, 「전자서명법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」,
								「방문판매 등에 관한 법률」, 「소비자기본법」 등 관련 법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
								<br>④ “몰”이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 몰의 초기화면에
								그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게 약관내용을 변경하는 경우에는
								최소한 30일 이상의 사전 유예기간을 두고 공지합니다. &nbsp;이 경우 "몰“은 개정 전 내용과 개정 후 내용을
								명확하게 비교하여 이용자가 알기 쉽도록 표시합니다. <br>⑤ “몰”이 약관을 개정할 경우에는 그
								개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의
								약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에
								의한 개정약관의 공지기간 내에 “몰”에 송신하여 “몰”의 동의를 받은 경우에는 개정약관 조항이 적용됩니다. <br>⑥
								이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률, 약관의
								규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는 상관례에
								따릅니다. <br>제4조(서비스의 제공 및 변경) <br>① “몰”은 다음과 같은 업무를
								수행합니다. <br>1. 재화 또는 용역에 대한 정보 제공 및 구매계약의 체결 <br>2.
								구매계약이 체결된 재화 또는 용역의 배송 <br>3. 기타 “몰”이 정하는 업무 <br>②
								“몰”은 재화 또는 용역의 품절 또는 기술적 사양의 변경 등의 경우에는 장차 체결되는 계약에 의해 제공할 재화 또는
								용역의 내용을 변경할 수 있습니다. 이 경우에는 변경된 재화 또는 용역의 내용 및 제공일자를 명시하여 현재의 재화
								또는 용역의 내용을 게시한 곳에 즉시 공지합니다. <br>③ “몰”이 제공하기로 이용자와 계약을 체결한
								서비스의 내용을 재화등의 품절 또는 기술적 사양의 변경 등의 사유로 변경할 경우에는 그 사유를 이용자에게 통지
								가능한 주소로 즉시 통지합니다. <br>④ 전항의 경우 “몰”은 이로 인하여 이용자가 입은 손해를
								배상합니다. 다만, “몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다. <br>제5조(서비스의
								중단) <br>① “몰”은 컴퓨터 등 정보통신설비의 보수점검․교체 및 고장, 통신의 두절 등의 사유가
								발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다. <br>② “몰”은 제1항의 사유로
								서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상합니다. 단, “몰”이
								고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다. <br>③ 사업종목의 전환, 사업의 포기,
								업체 간의 통합 등의 이유로 서비스를 제공할 수 없게 되는 경우에는 “몰”은 제8조에 정한 방법으로 이용자에게
								통지하고 당초 “몰”에서 제시한 조건에 따라 소비자에게 보상합니다. 다만, “몰”이 보상기준 등을 고지하지 아니한
								경우에는 이용자들의 마일리지 또는 적립금 등을 “몰”에서 통용되는 통화가치에 상응하는 현물 또는 현금으로 이용자에게
								지급합니다. <br>제6조(회원가입) <br>① 이용자는 “몰”이 정한 가입 양식에 따라
								회원정보를 기입한 후 이 약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다. <br>② “몰”은
								제1항과 같이 회원으로 가입할 것을 신청한 이용자 중 다음 각 호에 해당하지 않는 한 회원으로 등록합니다. <br>1.
								가입신청자가 이 약관 제7조제3항에 의하여 이전에 회원자격을 상실한 적이 있는 경우, 다만 제7조제3항에 의한
								회원자격 상실 후 3년이 경과한 자로서 “몰”의 회원재가입 승낙을 얻은 경우에는 예외로 한다. <br>2.
								등록 내용에 허위, 기재누락, 오기가 있는 경우 <br>3. 기타 회원으로 등록하는 것이 “몰”의 기술상
								현저히 지장이 있다고 판단되는 경우 <br>③ 회원가입계약의 성립 시기는 “몰”의 승낙이 회원에게 도달한
								시점으로 합니다. <br>④ 회원은 회원가입 시 등록한 사항에 변경이 있는 경우, 상당한 기간 이내에
								“몰”에 대하여 회원정보 수정 등의 방법으로 그 변경사항을 알려야 합니다. <br>제7조(회원 탈퇴 및
								자격 상실 등) <br>① 회원은 “몰”에 언제든지 탈퇴를 요청할 수 있으며 “몰”은 즉시 회원탈퇴를
								처리합니다. <br>② 회원이 다음 각 호의 사유에 해당하는 경우, “몰”은 회원자격을 제한 및 정지시킬
								수 있습니다. <br>1. 가입 신청 시에 허위 내용을 등록한 경우 <br>2. “몰”을 이용하여
								구입한 재화 등의 대금, 기타 “몰”이용에 관련하여 회원이 부담하는 채무를 기일에 지급하지 않는 경우 <br>3.
								다른 사람의 “몰” 이용을 방해하거나 그 정보를 도용하는 등 전자상거래 질서를 위협하는 경우 <br>4.
								“몰”을 이용하여 법령 또는 이 약관이 금지하거나 공서양속에 반하는 행위를 하는 경우 <br>③ “몰”이
								회원 자격을 제한․정지 시킨 후, 동일한 행위가 2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는
								경우 “몰”은 회원자격을 상실시킬 수 있습니다. <br>④ “몰”이 회원자격을 상실시키는 경우에는
								회원등록을 말소합니다. 이 경우 회원에게 이를 통지하고, 회원등록 말소 전에 최소한 30일 이상의 기간을 정하여
								소명할 기회를 부여합니다. <br>제8조(회원에 대한 통지) <br>① “몰”이 회원에 대한
								통지를 하는 경우, 회원이 “몰”과 미리 약정하여 지정한 전자우편 주소로 할 수 있습니다. <br>②
								“몰”은 불특정다수 회원에 대한 통지의 경우 1주일이상 “몰” 게시판에 게시함으로서 개별 통지에 갈음할 수
								있습니다. 다만, 회원 본인의 거래와 관련하여 중대한 영향을 미치는 사항에 대하여는 개별통지를 합니다. <br>제9조(구매신청
								및 개인정보 제공 동의 등) <br>① “몰”이용자는 “몰”상에서 다음 또는 이와 유사한 방법에 의하여
								구매를 신청하며, “몰”은 이용자가 구매신청을 함에 있어서 다음의 각 내용을 알기 쉽게 제공하여야 합니다. <br>&nbsp;
								1. 재화 등의 검색 및 선택 <br>&nbsp; 2. 받는 사람의 성명, 주소, 전화번호,
								전자우편주소(또는 이동전화번호) 등의 입력 <br>&nbsp; 3. 약관내용, 청약철회권이 제한되는
								서비스, 배송료․설치비 등의 비용부담과 관련한 내용에 대한 확인 <br>&nbsp; 4. 이 약관에
								동의하고 위 3.호의 사항을 확인하거나 거부하는 표시 <br>&nbsp; &nbsp; &nbsp;
								&nbsp; &nbsp; &nbsp; &nbsp; (예, 마우스 클릭) <br>&nbsp; 5. 재화등의
								구매신청 및 이에 관한 확인 또는 “몰”의 확인에 대한 동의 <br>&nbsp; 6. 결제방법의 선택 <br>②
								“몰”이 제3자에게 구매자 개인정보를 제공할 필요가 있는 경우 1) 개인정보를 제공받는 자, 2)개인정보를 제공받는
								자의 개인정보 이용목적, 3) 제공하는 개인정보의 항목, 4) 개인정보를 제공받는 자의 개인정보 보유 및 이용기간을
								구매자에게 알리고 동의를 받아야 합니다. (동의를 받은 사항이 변경되는 경우에도 같습니다.) <br>③
								“몰”이 제3자에게 구매자의 개인정보를 취급할 수 있도록 업무를 위탁하는 경우에는 1) 개인정보 취급위탁을 받는
								자, 2) 개인정보 취급위탁을 하는 업무의 내용을 구매자에게 알리고 동의를 받아야 합니다. (동의를 받은 사항이
								변경되는 경우에도 같습니다.) 다만, 서비스제공에 관한 계약이행을 위해 필요하고 구매자의 편의증진과 관련된 경우에는
								「정보통신망 이용촉진 및 정보보호 등에 관한 법률」에서 정하고 있는 방법으로 개인정보 취급방침을 통해 알림으로써
								고지절차와 동의절차를 거치지 않아도 됩니다. <br>제10조 (계약의 성립) <br>①
								&nbsp;“몰”은 제9조와 같은 구매신청에 대하여 다음 각 호에 해당하면 승낙하지 않을 수 있습니다. 다만,
								미성년자와 계약을 체결하는 경우에는 법정대리인의 동의를 얻지 못하면 미성년자 본인 또는 법정대리인이 계약을 취소할
								수 있다는 내용을 고지하여야 합니다. <br>1. 신청 내용에 허위, 기재누락, 오기가 있는 경우 <br>2.
								미성년자가 담배, 주류 등 청소년보호법에서 금지하는 재화 및 용역을 구매하는 경우 <br>3. 기타
								구매신청에 승낙하는 것이 “몰” 기술상 현저히 지장이 있다고 판단하는 경우 <br>② “몰”의 승낙이
								제12조제1항의 수신확인통지형태로 이용자에게 도달한 시점에 계약이 성립한 것으로 봅니다. <br>③
								“몰”의 승낙의 의사표시에는 이용자의 구매 신청에 대한 확인 및 판매가능 여부, 구매신청의 정정 취소 등에 관한
								정보 등을 포함하여야 합니다. <br>제11조(지급방법) “몰”에서 구매한 재화 또는 용역에 대한
								대금지급방법은 다음 각 호의 방법중 가용한 방법으로 할 수 있습니다. 단, “몰”은 이용자의 지급방법에 대하여 재화
								등의 대금에 어떠한 명목의 수수료도 &nbsp;추가하여 징수할 수 없습니다. <br>1. 폰뱅킹,
								인터넷뱅킹, 메일 뱅킹 등의 각종 계좌이체 <br>2. 선불카드, 직불카드, 신용카드 등의 각종 카드 결제
								<br>3. 온라인무통장입금 <br>4. 전자화폐에 의한 결제 <br>5. 수령 시
								대금지급 <br>6. 마일리지 등 “몰”이 지급한 포인트에 의한 결제 <br>7. “몰”과 계약을
								맺었거나 “몰”이 인정한 상품권에 의한 결제 &nbsp; <br>8. 기타 전자적 지급 방법에 의한 대금
								지급 등 <br>제12조(수신확인통지․구매신청 변경 및 취소) <br>① “몰”은 이용자의
								구매신청이 있는 경우 이용자에게 수신확인통지를 합니다. <br>② 수신확인통지를 받은 이용자는 의사표시의
								불일치 등이 있는 경우에는 수신확인통지를 받은 후 즉시 구매신청 변경 및 취소를 요청할 수 있고 “몰”은 배송 전에
								이용자의 요청이 있는 경우에는 지체 없이 그 요청에 따라 처리하여야 합니다. 다만 이미 대금을 지불한 경우에는
								제15조의 청약철회 등에 관한 규정에 따릅니다. <br>제13조(재화 등의 공급) <br>①
								“몰”은 이용자와 재화 등의 공급시기에 관하여 별도의 약정이 없는 이상, 이용자가 청약을 한 날부터 7일 이내에
								재화 등을 배송할 수 있도록 주문제작, 포장 등 기타의 필요한 조치를 취합니다. 다만, “몰”이 이미 재화 등의
								대금의 전부 또는 일부를 받은 경우에는 대금의 전부 또는 일부를 받은 날부터 3영업일 이내에 조치를 취합니다.
								&nbsp;이때 “몰”은 이용자가 재화 등의 공급 절차 및 진행 사항을 확인할 수 있도록 적절한 조치를 합니다. <br>②
								“몰”은 이용자가 구매한 재화에 대해 배송수단, 수단별 배송비용 부담자, 수단별 배송기간 등을 명시합니다. 만약
								“몰”이 약정 배송기간을 초과한 경우에는 그로 인한 이용자의 손해를 배상하여야 합니다. 다만 “몰”이 고의․과실이
								없음을 입증한 경우에는 그러하지 아니합니다. <br>제14조(환급) “몰”은 이용자가 구매신청한 재화 등이
								품절 등의 사유로 인도 또는 제공을 할 수 없을 때에는 지체 없이 그 사유를 이용자에게 통지하고 사전에 재화 등의
								대금을 받은 경우에는 대금을 받은 날부터 3영업일 이내에 환급하거나 환급에 필요한 조치를 취합니다. <br>제15조(청약철회
								등) <br>① “몰”과 재화등의 구매에 관한 계약을 체결한 이용자는 「전자상거래 등에서의 소비자보호에
								관한 법률」 제13조 제2항에 따른 계약내용에 관한 서면을 받은 날(그 서면을 받은 때보다 재화 등의 공급이 늦게
								이루어진 경우에는 재화 등을 공급받거나 재화 등의 공급이 시작된 날을 말합니다)부터 7일 이내에는 청약의 철회를 할
								수 있습니다. 다만, 청약철회에 관하여 「전자상거래 등에서의 소비자보호에 관한 법률」에 달리 정함이 있는 경우에는
								동 법 규정에 따릅니다. <br>② 이용자는 재화 등을 배송 받은 경우 다음 각 호의 1에 해당하는
								경우에는 반품 및 교환을 할 수 없습니다. <br>1. 이용자에게 책임 있는 사유로 재화 등이 멸실 또는
								훼손된 경우(다만, 재화 등의 내용을 확인하기 위하여 포장 등을 훼손한 경우에는 청약철회를 할 수 있습니다) <br>2.
								이용자의 사용 또는 일부 소비에 의하여 재화 등의 가치가 현저히 감소한 경우 <br>3. 시간의 경과에
								의하여 재판매가 곤란할 정도로 재화등의 가치가 현저히 감소한 경우 <br>4. 같은 성능을 지닌 재화
								등으로 복제가 가능한 경우 그 원본인 재화 등의 포장을 훼손한 경우 <br>③ 제2항제2호 내지 제4호의
								경우에 “몰”이 사전에 청약철회 등이 제한되는 사실을 소비자가 쉽게 알 수 있는 곳에 명기하거나 시용상품을 제공하는
								등의 조치를 하지 않았다면 이용자의 청약철회 등이 제한되지 않습니다. <br>④ 이용자는 제1항 및
								제2항의 규정에 불구하고 재화 등의 내용이 표시·광고 내용과 다르거나 계약내용과 다르게 이행된 때에는 당해 재화
								등을 공급받은 날부터 3월 이내, 그 사실을 안 날 또는 알 수 있었던 날부터 30일 이내에 청약철회 등을 할 수
								있습니다. <br>제16조(청약철회 등의 효과) <br>① “몰”은 이용자로부터 재화 등을
								반환받은 경우 3영업일 이내에 이미 지급받은 재화 등의 대금을 환급합니다. 이 경우 “몰”이 이용자에게 재화등의
								환급을 지연한때에는 그 지연기간에 대하여 「전자상거래 등에서의 소비자보호에 관한 법률 시행령」제21조의2에서 정하는
								지연이자율을 곱하여 산정한 지연이자를 지급합니다. <br>② “몰”은 위 대금을 환급함에 있어서 이용자가
								신용카드 또는 전자화폐 등의 결제수단으로 재화 등의 대금을 지급한 때에는 지체 없이 당해 결제수단을 제공한 사업자로
								하여금 재화 등의 대금의 청구를 정지 또는 취소하도록 요청합니다. <br>③ 청약철회 등의 경우 공급받은
								재화 등의 반환에 필요한 비용은 이용자가 부담합니다. “몰”은 이용자에게 청약철회 등을 이유로 위약금 또는
								손해배상을 청구하지 않습니다. 다만 재화 등의 내용이 표시·광고 내용과 다르거나 계약내용과 다르게 이행되어 청약철회
								등을 하는 경우 재화 등의 반환에 필요한 비용은 “몰”이 부담합니다. <br>④ 이용자가 재화 등을
								제공받을 때 발송비를 부담한 경우에 “몰”은 청약철회 시 그 비용을 &nbsp;누가 부담하는지를 이용자가 알기
								쉽도록 명확하게 표시합니다. <br>제17조(개인정보보호) <br>① “몰”은 이용자의 개인정보
								수집시 서비스제공을 위하여 필요한 범위에서 최소한의 개인정보를 수집합니다. <br>② “몰”은 회원가입시
								구매계약이행에 필요한 정보를 미리 수집하지 않습니다. 다만, 관련 법령상 의무이행을 위하여 구매계약 이전에
								본인확인이 필요한 경우로서 최소한의 특정 개인정보를 수집하는 경우에는 그러하지 아니합니다. <br>③
								“몰”은 이용자의 개인정보를 수집·이용하는 때에는 당해 이용자에게 그 목적을 고지하고 동의를 받습니다. <br>④
								“몰”은 수집된 개인정보를 목적외의 용도로 이용할 수 없으며, 새로운 이용목적이 발생한 경우 또는 제3자에게
								제공하는 경우에는 이용·제공단계에서 당해 이용자에게 그 목적을 고지하고 동의를 받습니다. 다만, 관련 법령에 달리
								정함이 있는 경우에는 예외로 합니다. <br>⑤ “몰”이 제2항과 제3항에 의해 이용자의 동의를 받아야
								하는 경우에는 개인정보관리 책임자의 신원(소속, 성명 및 전화번호, 기타 연락처), 정보의 수집목적 및 이용목적,
								제3자에 대한 정보제공 관련사항(제공받은자, 제공목적 및 제공할 정보의 내용) 등 「정보통신망 이용촉진 및 정보보호
								등에 관한 법률」 제22조제2항이 규정한 사항을 미리 명시하거나 고지해야 하며 이용자는 언제든지 이 동의를 철회할
								수 있습니다. <br>⑥ 이용자는 언제든지 “몰”이 가지고 있는 자신의 개인정보에 대해 열람 및 오류정정을
								요구할 수 있으며 “몰”은 이에 대해 지체 없이 필요한 조치를 취할 의무를 집니다. 이용자가 오류의 정정을 요구한
								경우에는 “몰”은 그 오류를 정정할 때까지 당해 개인정보를 이용하지 않습니다. <br>⑦ “몰”은 개인정보
								보호를 위하여 이용자의 개인정보를 취급하는 자를 &nbsp;최소한으로 제한하여야 하며 신용카드, 은행계좌 등을
								포함한 이용자의 개인정보의 분실, 도난, 유출, 동의 없는 제3자 제공, 변조 등으로 인한 이용자의 손해에 대하여
								모든 책임을 집니다. <br>⑧ “몰” 또는 그로부터 개인정보를 제공받은 제3자는 개인정보의 수집목적 또는
								제공받은 목적을 달성한 때에는 당해 개인정보를 지체 없이 파기합니다. <br>⑨ “몰”은 개인정보의
								수집·이용·제공에 관한 동의 란을 미리 선택한 것으로 설정해두지 않습니다. 또한 개인정보의 수집·이용·제공에 관한
								이용자의 동의거절시 제한되는 서비스를 구체적으로 명시하고, 필수수집항목이 아닌 개인정보의 수집·이용·제공에 관한
								이용자의 동의 거절을 이유로 회원가입 등 서비스 제공을 제한하거나 거절하지 않습니다. <br>제18조(“몰“의
								의무) <br>① “몰”은 법령과 이 약관이 금지하거나 공서양속에 반하는 행위를 하지 않으며 이 약관이
								정하는 바에 따라 지속적이고, 안정적으로 재화․용역을 제공하는데 최선을 다하여야 합니다. <br>②
								“몰”은 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보(신용정보 포함)보호를 위한 보안
								시스템을 갖추어야 합니다. <br>③ “몰”이 상품이나 용역에 대하여 「표시․광고의 공정화에 관한 법률」
								제3조 소정의 부당한 표시․광고행위를 함으로써 이용자가 손해를 입은 때에는 이를 배상할 책임을 집니다. <br>④
								“몰”은 이용자가 원하지 않는 영리목적의 광고성 전자우편을 발송하지 않습니다. <br>제19조(회원의 ID
								및 비밀번호에 대한 의무) <br>① 제17조의 경우를 제외한 ID와 비밀번호에 관한 관리책임은 회원에게
								있습니다. <br>② 회원은 자신의 ID 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다. <br>③
								회원이 자신의 ID 및 비밀번호를 도난당하거나 제3자가 사용하고 있음을 인지한 경우에는 바로 “몰”에 통보하고
								“몰”의 안내가 있는 경우에는 그에 따라야 합니다. <br>제20조(이용자의 의무) 이용자는 다음 행위를
								하여서는 안 됩니다. <br>1. 신청 또는 변경시 허위 내용의 등록 <br>2. 타인의 정보
								도용 <br>3. “몰”에 게시된 정보의 변경 <br>4. “몰”이 정한 정보 이외의 정보(컴퓨터
								프로그램 등) 등의 송신 또는 게시 <br>5. “몰” 기타 제3자의 저작권 등 지적재산권에 대한 침해 <br>6.
								“몰” 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위 <br>7. 외설 또는 폭력적인 메시지,
								화상, 음성, 기타 공서양속에 반하는 정보를 몰에 공개 또는 게시하는 행위 <br>제21조(연결“몰”과
								피연결“몰” 간의 관계) <br>① 상위 “몰”과 하위 “몰”이 하이퍼링크(예: 하이퍼링크의 대상에는
								문자, 그림 및 동화상 등이 포함됨)방식 등으로 연결된 경우, 전자를 연결 “몰”(웹 사이트)이라고 하고 후자를
								피연결 “몰”(웹사이트)이라고 합니다. <br>② 연결“몰”은 피연결“몰”이 독자적으로 제공하는 재화 등에
								의하여 이용자와 행하는 거래에 대해서 보증 책임을 지지 않는다는 뜻을 연결“몰”의 초기화면 또는 연결되는 시점의
								팝업화면으로 명시한 경우에는 그 거래에 대한 보증 책임을 지지 않습니다. <br>제22조(저작권의 귀속 및
								이용제한) <br>① “몰“이 작성한 저작물에 대한 저작권 기타 지적재산권은 ”몰“에 귀속합니다. <br>②
								이용자는 “몰”을 이용함으로써 얻은 정보 중 “몰”에게 지적재산권이 귀속된 정보를 “몰”의 사전 승낙 없이 복제,
								송신, 출판, 배포, 방송 기타 방법에 의하여 영리목적으로 이용하거나 제3자에게 이용하게 하여서는 안됩니다. <br>③
								“몰”은 약정에 따라 이용자에게 귀속된 저작권을 사용하는 경우 당해 이용자에게 통보하여야 합니다. <br>제23조(분쟁해결)
								<br>① “몰”은 이용자가 제기하는 정당한 의견이나 불만을 반영하고 그 피해를 보상처리하기 위하여
								피해보상처리기구를 설치․운영합니다. <br>② “몰”은 이용자로부터 제출되는 불만사항 및 의견은 우선적으로
								그 사항을 처리합니다. 다만, 신속한 처리가 곤란한 경우에는 이용자에게 그 사유와 처리일정을 즉시 통보해 드립니다.
								<br>③ “몰”과 이용자 간에 발생한 전자상거래 분쟁과 관련하여 이용자의 피해구제신청이 있는 경우에는
								공정거래위원회 또는 시·도지사가 의뢰하는 분쟁조정기관의 조정에 따를 수 있습니다. <br>제24조(재판권
								및 준거법) <br>① “몰”과 이용자 간에 발생한 전자상거래 분쟁에 관한 소송은 제소 당시의 이용자의
								주소에 의하고, 주소가 없는 경우에는 거소를 관할하는 지방법원의 전속관할로 합니다. 다만, 제소 당시 이용자의 주소
								또는 거소가 분명하지 않거나 외국 거주자의 경우에는 민사소송법상의 관할법원에 제기합니다. <br>②
								“몰”과 이용자 간에 제기된 전자상거래 소송에는 한국법을 적용합니다. &nbsp; &nbsp;2. 등록 내용에
								허위, 기재누락, 오기가 있는 경우 <br>&nbsp; &nbsp; 3. 기타 회원으로 등록하는 것이
								“몰”의 기술상 현저히 지장이 있다고 판단되는 경우 <br>&nbsp; ③ 회원가입계약의 성립 시기는
								“몰”의 승낙이 회원에게 도달한 시점으로 합니다. <br>&nbsp; ④ 회원은 회원가입 시 등록한 사항에
								변경이 있는 경우, 상당한 기간 이내에 “몰”에 대하여 회원정보 수정 등의 방법으로 그 변경사항을 알려야 합니다.
							</p>
						</div>
					</div>
					<p class="check">
						<span>이용약관에 동의하십니까?</span> <input id="agree1"
							name="agree1" class="ec-base-chk" value="T" type="checkbox"
							style="vertical-align: middle;">
					</p>
				</div>
				
				<div class="ec-base-box typeThinBg agreeArea">
					<h3>[필수] 개인정보 수집 및 이용 동의</h3>
					<div class="content">
						<div class="fr-view fr-view-privacy-required"
							style="padding: 0 0 17px; overflow-y: scroll; height: 200px; margin: 0; background-color: #fff; border: ridge; display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px; unicode-bidi: isolate; word-wrap: break-word;">
							<p>1. 개인정보 수집목적 및 이용목적</p>
							<p>가. 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산</p>
							<p>콘텐츠 제공 , 구매 및 요금 결제 , 물품배송 또는 청구지 등 발송 , 금융거래 본인 인증 및 금융
								서비스</p>
							<p>나. 회원 관리</p>
							<p>회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불량회원의 부정 이용 방지와 비인가 사용 방지 ,
								가입 의사 확인 , 연령확인 , 불만처리 등 민원처리 , 고지사항 전달</p>
							<p>2. 수집하는 개인정보 항목 : 이름 , 로그인ID , 비밀번호 , 이메일,&nbsp;14세미만 가입자의
								경우 법정대리인의 정보</p>
							<p>3. 개인정보의 보유기간 및 이용기간</p>
							<p>원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 다음의
								정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.</p>
							<p>가. 회사 내부 방침에 의한 정보 보유 사유</p>
							<p>o 부정거래 방지 및 쇼핑몰 운영방침에 따른 보관 : 5년</p>
							<p>나. 관련 법령에 의한 정보보유 사유</p>
							<p>o 계약 또는 청약철회 등에 관한 기록</p>
							<p>-보존이유 : 전자상거래등에서의소비자보호에관한법률</p>
							<p>-보존기간 : 5년</p>
							<p>o 대금 결제 및 재화 등의 공급에 관한 기록</p>
							<p>-보존이유: 전자상거래등에서의소비자보호에관한법률</p>
							<p>-보존기간 : 5년</p>
							<p>o 소비자 불만 또는 분쟁처리에 관한 기록</p>
							<p>-보존이유 : 전자상거래등에서의소비자보호에관한법률</p>
							<p>-보존기간 : 3년</p>
							<p>o 로그 기록</p>
							<p>-보존이유: 통신비밀보호법</p>
							<p>-보존기간 : 3개월</p>
							<p>※ 동의를 거부할 수 있으나 거부시 회원 가입이 불가능합니다.</p>
							<br> <span style="font-size: 12px; color: rgb(0, 0, 0);">■
								수집한 개인정보의 위탁<br>회사는 서비스 이행을 위해 아래와 같이 외부 전문업체에 위탁하여 운영하고
								있습니다.<br>o&nbsp;위탁 대상자 : 우체국 택배<br>o 위탁업무 내용 : <span
								style="font-family: &amp; amp; amp; amp; amp; quot; Noto Sans KR&amp;amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; amp; amp; quot; , Dotum , 돋움, arial, verdana, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: center; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;">상품
									배송 업무</span> <br>o 위탁 대상자 : <span
								style="font-family: 돋움, dotum,&amp; amp; amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; quot; malgun gothic&amp;amp; amp; amp; amp; quot; , helvetica , &amp;amp; amp; amp; amp; quot; Apple SD Gothic Neo&amp;amp; amp; amp; amp; quot; , sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;">이니시스</span>
								<br>o 위탁업무 내용 : <span
								style="font-family: &amp; amp; amp; amp; amp; quot; Noto Sans KR&amp;amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; amp; amp; quot; , Dotum , 돋움, arial, verdana, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: center; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;">결제
									및 에스크로 업무</span> <br>o 위탁 대상자 : <span
								style="font-family: &amp; amp; amp; amp; amp; quot; Malgun Gothic&amp;amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; amp; amp; quot; , 돋움 , dotum, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;">카페24(또는
									루나소프트, 카카오)</span> <br>o 위탁업무 내용 : 카카오톡 &nbsp;메세지 발송 업무<br>o
								위탁 대상자 : <span
								style="font-family: &amp; amp; amp; amp; amp; quot; Noto Sans KR&amp;amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; amp; amp; quot; , Dotum , 돋움, arial, verdana, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: center; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;">(주)카페24</span>

								<br>o 위탁업무 내용 :
							</span><span
								style="color: rgb(0, 0, 0); font-family: &amp; amp; amp; amp; amp; quot; Noto Sans KR&amp;amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; amp; amp; quot; , Dotum , 돋움, arial, verdana, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: center; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;">쇼핑몰
								DB시스템 운영</span>
						</div>
					</div>
					<p class="check">
						<span>개인정보 수집 및 이용에 동의하십니까?</span> 
						<input id="agree2" name="agree2"
							class="ec-base-chk" value="1"
							type="checkbox" style="vertical-align: middle;">
							
					</p>
				</div>
				<div class="ec-base-box typeThinBg agreeArea ">
					<h3>[선택] 개인정보 수집 및 이용 동의</h3>
					<div class="content">
						<div class="fr-view fr-view-privacy-optional"
							style="padding: 0 0 17px; overflow-y: scroll; height: 200px; margin: 0; background-color: #fff; border: ridge; display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px; unicode-bidi: isolate; word-wrap: break-word;">
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
							name="agree_privacy_optional_check[]" class="ec-base-chk"
							value="T" type="checkbox" style="vertical-align: middle;">
					</p>
				</div>

				<div class="ec-base-box typeThinBg agreeArea ">
					<h3>[선택] 쇼핑정보 수신 동의</h3>
					<div class="content"
						style="padding: 0 0 17px; overflow-y: scroll; height: 50px; margin: 0; background-color: #fff; border: ridge; display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px; unicode-bidi: isolate; word-wrap: break-word;">
						<p>할인쿠폰 및 혜택, 이벤트, 신상품 소식 등 쇼핑몰에서 제공하는 유익한 쇼핑정보를 SMS나 이메일로
							받아보실 수 있습니다.</p>
						<p>단, 주문/거래 정보 및 주요 정책과 관련된 내용은 수신동의 여부와 관계없이 발송됩니다.</p>
						<p>선택 약관에 동의하지 않으셔도 회원가입은 가능하며, 회원가입 후 회원정보수정 페이지에서 언제든지 수신여부를
							변경하실 수 있습니다.</p>
					</div>
					<ul class="check">
						<li class=""><span>SMS 수신을 동의하십니까?</span> <input id="is_sms0"
							name="is_sms" class="ec-base-chk" value="T" type="checkbox" style="vertical-align: middle;">
							</li>
					</ul>
				</div><br/>

				<div class="ec-base-button">
					<a href="#" class="btnSubmitFix sizeM" onclick="sendIt();">회원가입</a>

				</div><br/>

			</form>
		</div>
	</div>

<jsp:include page="../footer.jsp" /> 

</body>
</html>