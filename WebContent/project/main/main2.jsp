<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

String cp = request.getContextPath();
%>
<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인화면</title>
<style type="text/css">
</style>
<link rel="stylesheet" href="<%=cp%>/project/css/main.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/list.css" />
</head>
<body style="padding: 0px; margin: 0px;">
	<!-- style="flex-direction: row;" div에서 일자로 배치할 수 있음  -->
	<div id="header_menu">
		<div class="header_menuinner">
			<div class="inner">


				<form id="searchBarForm" name="top" action="/product/search.html"
					method="get" target="_self" enctype="multipart/form-data">
					<input id="banner_action" name="banner_action" value=""
						type="hidden">
					<div class="xans-element- xans-layout xans-layout-searchheader ">
						<!--
                $product_page=/product/detail.html
                $category_page=/product/list.html
            -->
						<fieldset>
							<legend>검색</legend>
							<input id="keyword" name="keyword"  fw-label="검색어"
								fw-msg="" class="inputTypeText" placeholder=""
								onmousedown="SEARCH_BANNER.clickSearchForm(this)" value="main/main.jsp"
								type="text"><input type="image"
								src="<%=cp %>/project/image/search(2).png" alt="검색"
								onclick="SEARCH_BANNER.submitSearchBanner(this); return false;">
						</fieldset>
					</div>
				</form>
				<div id="top_right">

					<div
						class="xans-element- xans-layout xans-layout-orderbasketcount cart_img ">
						<a href="<%=cp %>/basket/basket.do"
							class="xans-element- xans-layout xans-layout-orderbasketcount "><img
							src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/btn_basket.png"
							alt="장바구니"></a> <span class="cart_count">0</span>
					</div>

				</div>




				<div class="xans-element- xans-layout xans-layout-statelogoff ">
					<ul class="memberoff">
						<li><a href="<%=cp %>/pj/login.do" class="log">로그인</a></li>
						<li class="menu_join"><a href="<%=cp%>/pj/join.do">회원가입
								<div class="joinpoint" style="top: 22.3129px; left: 25px;">
									+ 2,000
									<div class="tri"></div>
								</div>
						</a></li>
						<li><a href="<%=cp%>/purchased/purchased.do/">주문조회</a></li>
						<li><a href="<%=cp%>/pj/mypage.do">마이페이지</a></li>

					</ul>
				</div>





				<div id="snstop">

					<a href="https://www.instagram.com/salad_market/" target="_blank"><i
						class="xi-instagram"></i></a> <a
						href="https://twitter.com/salad_market" target="_blank"><i
						class="xi-twitter"></i></a> <a
						href="https://blog.naver.com/saladmarket_official/"
						target="_blank"><i class="xi-naver-square"></i></a> <a
						href="https://www.youtube.com/c/saladmarket" target="_blank"><i
						class="xi-youtube-play"></i></a> <a
						href="https://www.facebook.com/saladmarket.aka/" target="_blank"><i
						class="xi-facebook"></i></a>
				</div>



				<div style="clear: both"></div>

				<div class="xans-element- xans-layout xans-layout-logotop logo_top ">
					<a href="<%=cp %>/view/main.do"><img
						src="<%=cp %>/project/image/logo.png"></a>
				</div>
				<div style="clear: both"></div>
			</div>
		</div>
	</div>



	<%-- <!-- <div style="background-color: #ffffff; width: 1000; height: 155; margin-left: auto;
   		 margin-right: auto;"> -->
   		 <div >
			<!-- <div style="display: flex; align-content: center; justify-content: center; margin-left: 850px;"> -->
			<div style="border: thick;">
				<div class="logo">
					<img src="<%=cp%>/project/image/logo.png" width="159" height="120" />
				</div>
			</div>
		</div> --%>

	<!--Category_ListCategory_ListCategory_ListCategory_ListCategory_ListCategory_ListCategory_ListCategory_ListCategory_ListCategory_ListCategory_List  -->
	<div align="center">
		<form action="" method="post" name="searchForm">
		<ul
			style="background-color: none; flex-direction: row; justify-content: space-around;">
			<span class="cate_allmenu"> <span class="mypage_open">
					<img src="<%=cp %>/project/image/menu.png" alt="">
			</span> <span class="mypage_close"> <img
					src="<%=cp %>/project/image/allmenu_close.png" alt="">
			</span>
			</span>


			<li class="number"
				style="vertical-align: middle; display: inline-block; z-index: 10;  font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif;
				 padding-right:20px; line-height: 50px; height: 50px; background-color: #ffffff;"><a href="<%=cp%>/img/list.do">전체상품</a>

			</li>
			<li class="number" id="searchValue"
				style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif;
				 padding-right:20px; line-height: 50px; height: 50px; background-color: #ffffff;; float: center">
				 <input type="hidden" id="searchKey" name="searchKey" value="쿠로미">
				 <a href="" onclick="sendIt();">쿠로미</a>
				 <input type="hidden" id="searchKey" name="searchKey" value="쿠로미">
			</li>
			
			<li class="number"
				style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif;
				 padding-right:20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">폼폼푸린

			</li>
			<li class="number"
				style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif;
				 padding-right:20px; line-height: 50px; height: 50px; background-color: #FFFFFF; text-align: center;">
				헬로키티</li>
			<li class="number"
				style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif;
				 padding-right:20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">시나모롤

			</li>
			<li class="number"
				style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif;
				 padding-right:20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">포차코

			</li>
				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">
					<a href="<%=cp%>/question/faqlist.do">자주묻는 질문</a>
				</li>

				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">
					<a href="<%=cp%>/not/list.do">공지사항</a>
				</li>


		</ul>
		</form>
	</div>


	<!-- contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents -->
		<div style="top:50%; right: 5%; z-index: 1000; margin-right: 50px; height: 269px; width: 42.5px; position: fixed; margin-right: 20px;">
			<li><a href="#top"> <img
					src="<%=cp %>/project/image/gotop.png" alt="" style="height: 42px; width: 42px; margin-bottom: 60px;"></a></li>
			<li>navi2</li>
			<li><a href="#tag1"> <img
					src="<%=cp %>/project/image/godown.png" alt="" style="height: 42px; width: 42px; margin-top: 60px;"></a></li>
		</div>
	<div id="contents">
		<div style="height: 650px;">
			<div
				style="width: 550px; height: 550px; margin: auto; background-color: #e47195;">

				<img alt="외않되" src="<%=cp%>/project/image/test.jpg"
					style="height: 80px; width: 100%; height: 100%; object-fit: cover;">

			</div>
		</div>

		<div
			style="margin: auto; font-size: 34px; line-height: 1.2em; font-weight: 670; letter-spacing: 0.1px; font-family: 'TTTtangsbudaejjigaeB'; color: #e47195; text-align: center; background-color: #FCECF0;">
			<h2>
				<span style="font-size: 27px;">따끈따끈 신상템
					<p>매일 업데이트 ! 귀욤뽀짝 신상 라인업</p>
			</h2>
		</div>

		<div style="height: auto;">
			<div
				style=" width: 1090px; height: auto; margin: auto;">
				<div>


					<table width="560" align="center" class="boxTA">
    <c:set var="n" value="0" />
    <c:forEach var="dto1" items="${lists1}" varStatus="status1">
        <c:forEach var="dto2" items="${lists2}" varStatus="status2">
            <c:if test="${status1.index == status2.index}">
                <c:if test="${n == 0}">
                    <tr>
                </c:if>
                <c:if test="${n != 0 && n % 3 == 0}">
                    </tr>
                    <tr>
                </c:if>

                <td width="190" align="center">
                    <a href="${imagePath}/${dto1.saveFileName}">
                        <img src="${imagePath}/${dto1.saveFileName}" width="180" height="180">
                    </a>
                    <br />
                    <b>${dto2.pname} </b>
                    <br />
                    <b>${dto2.price} 원</b>
                </td>


                <c:set var="n" value="${n + 2}" />

                <c:if test="${n % 3 == 1}">
                    <td></td>
                </c:if>

                <c:if test="${n % 3 != 0}">
                    <td></td>
                </c:if>

                <c:if test="${n != 0 && n % 3 == 0}">
                    </tr>
                </c:if>
            </c:if>
        </c:forEach>
    </c:forEach>

    <c:if test="${n != 0 && n % 3 != 0}">
        </tr>
    </c:if>

    <c:if test="${dataCount != 0}">
        <tr bgcolor="#FFFFFF">
            <td align="center" height="30" colspan="3">${pageIndexList}</td>
        </tr>
    </c:if>

    <c:if test="${dataCount == 0}">
        <tr bgcolor="#FFFFFF">
            <td align="center" colspan="3" height="30">등록된 자료가 없습니다.</td>
        </tr>
    </c:if>
</table>

				</div>

			</div>
		</div>
		content
	</div>
	
	<!-- footerfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooter -->
	
	<div class="footer"
		style="background-color: #FCECF0; height: 80px; position: relative; align-content: center; justify-content: center; display: flex;">

		<div style="background-color: #FCECF0; width: 1090px; " >
			<!-- 로고 -->

			<img alt="나와라요" src="<%=cp%>/project/image/logo.png" width="93;"
				height="80px;">

		</div>

	</div>

	<div
		style="background-color: #FCECF0; height: 200px; position: relative; align-content: center; justify-content: center; display: flex;">
		<a id="tag1"></a>
		<div
			style="line-height: 12px; float: left; width: 250px; height: 245px; margin-top: 20px; margin-left: 230px; padding-right: 40px;">
			<ul style="text-align: left; padding-left: 5%;">
				<h2 style="font-size: 15px; color: #636363;">고객센터</h2>
				<li class="number" style="list-style: none; font-size: 22px;">055-637-9910</li>
				<li style="font-size: 13px; list-style: none; color: #636363"><strong>월요일~금요일</strong>
					/ 10:00 - 17:00</li>
				<li style="font-size: 13px; list-style: none; color: #636363"><strong>점심시간</strong>
					/ 12:00 - 13:00</li>
				<li class="off" style="list-style: none;">주말 공휴일 휴무</li>
				<li
					style="list-style: none; text-decoration: none; font-size: 13px; font-weight: 600; letter-spacing: 0.9px; color: #636363; padding: 5px 0px 0px 0px; line-height: 24px; font: 0.75em;">
					<img src="<%=cp %>/project/image/kakao.png"> <a
					href="https://pf.kakao.com/_UtcxnV"
					style="text-decoration: none; align-items: left; color: #636363"
					target="_blank">카카오톡 플러스친구 : 쿠로미마켓</a>
				</li>

				<li
					style="list-style: none; text-decoration: none; font-size: 13px; font-weight: 600; letter-spacing: 0.9px; color: #636363; padding: 5px 0px 0px 0px; line-height: 24px; font: 0.75em;">
					<img src="<%=cp %>/project/image/naver_talk.png"><a
					href="https://talk.naver.com/wc9ybt"
					style="text-decoration: none; align-items: left; color: #636363"
					target="_blank">네이버톡톡으로 문의하세요</a>
				</li>
			</ul>
		</div>
		<!-- 고객센터 -->

		<div
			style="line-height: 12px; float: left; width: 532px; height: 179px; margin-top: 20px; padding-right: 10px;">
			<ul
				style="text-align: left; padding-left: 5%; font-size: 15px; color: #636363;">
				<h2 style="font-size: 15px;">회사정보</h2>
				<p>
					<span style="font-size: 13px; padding-right: 10px;"><strong>법인명(상호):</strong>산리오마켓(sanrio
						market)</span> <span style="font-size: 13px; padding-right: 10px;"><strong>대표자(성명):</strong>무야호</span>
					<span style="font-size: 13px;"><strong>전화:</strong>055-555-1548</span>
				</p>
				<p>
					<span style="font-size: 13px; padding-right: 20px;"><strong>주소:</strong>아이티윌
						4층 6강의실</span>
				</p>
				<p>
					<span style="font-size: 13px; padding-right: 20px;"><strong>사업자
							등록번호 안내:</strong>있을리가 있겠습니까~</span>
				</p>
				<p>
					<span style="font-size: 13px; padding-right: 20px;"><strong>통신판매업
							신고 :</strong>제2024-인천동춘-1199호</span>
				</p>
				<p>
					<span style="font-size: 13px; padding-right: 20px;"><strong>개인정보보호책임자
							:</strong>취업 하죠우리(getAJob@naver.com)</span>
				</p>
			</ul>
		</div>
		<!-- 회사 정보 -->

		<div 
			style="line-height: 12px; float: left; width: 250px; height: 100px; margin-top: 20px; padding-right: 10px;">
			<ul
				style="text-align: left; padding-left: 5%; font-size: 15px; color: #636363;">
				<h2 style="font-size: 15px;">입금계좌</h2>
				<p>
					<span style="font-size: 13px; padding-right: 10px;"><strong>국민:</strong>282202-042186733</span>
				</p>
				<p>
					<span style="font-size: 13px; padding-right: 10px;"><strong>예금
							주:</strong>조준영</span> <span style="font-size: 13px;">
				</p>
				<p>
					<strong>전화:</strong>055-555-1548</span>
				</p>
			</ul>
		</div>
		<!-- 계좌 정보 -->

		<div
			style="line-height: 12px; float: left; width: 532px; height: 179px; margin-top: 20px; padding-right: 10px;">
			<ul
				style="text-align: left; padding-left: 5%; font-size: 15px; color: #636363;">
				<h2 style="font-size: 15px;">SNS</h2>
				<a href="https://www.instagram.com/salad_market/" target="_blank"><img
					src="<%=cp %>/project/image/instagram.png" ></a>
				<a href="https://twitter.com/salad_market" target="_blank"><img
					src="<%=cp %>/project/image/twitter.png"></a>
				<a href="https://blog.naver.com/saladmarket_official/"
					target="_blank"><img src="<%=cp %>/project/image/blog.png"></a>
				<a href="https://www.youtube.com/c/saladmarket" target="_blank"><img
					src="<%=cp %>/project/image/youtube.png"></a>
				<a href="https://www.youtube.com/c/saladmarket" target="_blank"><img
					src="<%=cp %>/project/image/facebook.png"></a>
			</ul>

			<ul
				style="text-align: left; padding-left: 5%; font-size: 15px; color: #636363;">
				<h2 style="font-size: 15px;">교환,반품주소</h2>
				<li
					style="list-style: none; text-decoration: none; font-size: 13px; font-weight: 600; letter-spacing: 0.9px; color: #636363; padding: 5px 0px 0px 0px; line-height: 24px; font: 0.75em;">경상남도
					거제시 거제중앙로31길 24-3 (고현동) 3층</li>
				<li
					style="list-style: none; text-decoration: none; font-size: 13px; font-weight: 600; letter-spacing: 0.9px; color: #636363; padding: 5px 0px 0px 0px; line-height: 24px; font: 0.75em;">(
					반품 전 반드시 고객센터로 연락주세요 )</li>
			</ul>
		</div>
		<!-- sns -->


	</div>
	<!-- footer -->
</body>

</html>