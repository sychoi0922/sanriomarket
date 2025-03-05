<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>

<link rel="stylesheet" href="<%=cp%>/project/css/main.css"
	type="text/css">	
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/list.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/project.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/optimiuser.css" />

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
							<input id="keyword" name="keyword" fw-label="검색어" fw-msg=""
								class="inputTypeText" placeholder=""
								onmousedown="SEARCH_BANNER.clickSearchForm(this)"
								value="main/main.jsp" type="text"><input type="image"
								src="<%=cp%>/project/image/search(2).png" alt="검색"
								onclick="SEARCH_BANNER.submitSearchBanner(this); return false;">
						</fieldset>
					</div>
				</form>
				<div id="top_right">

					<div
						class="xans-element- xans-layout xans-layout-orderbasketcount cart_img ">
						<a href="<%=cp%>/basket/basket.do"
							class="xans-element- xans-layout xans-layout-orderbasketcount "><img
							src="https://cafe24.poxo.com/ec01/tiggers/7SMRVOMaSPlAMM7/eouUaeSOm/OlVBwR8fuLkT+9dqHPq3MM5InQF5NRGPZtYypg0igGsmZg2+RDN2MY/DDcPg==/_/web/upload/secbe2/btn_basket.png"
							alt="장바구니"></a> <span class="cart_count">0</span>
					</div>

				</div>




				<div class="xans-element- xans-layout xans-layout-statelogoff ">
					<ul class="memberoff">
						<c:choose>
							<c:when test="${empty sessionScope.customInfo.id }">
								<li><a href="<%=cp%>/pj/login.do" class="log">로그인</a></li>
								<li class="menu_join"><a href="<%=cp%>/pj/join.do">회원가입
										<div class="joinpoint" style="top: 22.3129px; left: 25px;">
											+ 2,000
											<div class="tri"></li>
							</c:when>
							<c:otherwise>
								<li><a href="<%=cp%>/pj/logout.do" class="log">로그아웃</a></li>
								<li><a href="<%=cp%>/pj/updated.do">회원정보수정</a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="<%=cp%>/purchased/purchased.do">주문조회</a></li>
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
					<a href="<%=cp%>/view/main.do"><img
						src="<%=cp%>/project/image/logo.png"></a>
				</div>
				<div style="clear: both"></div>
			</div>
		</div>
	</div>

	<div align="center">

		<form action="" method="get" name="searchForm">
			<ul
				style="background-color: none; flex-direction: row; justify-content: space-around;">
				<span class="cate_allmenu"> <span class="mypage_open">
						<img src="<%=cp%>/project/image/menu.png" alt="">
				</span> <span class="mypage_close"> <img
						src="<%=cp%>/project/image/allmenu_close.png" alt="">
				</span>
				</span>


				<li class="number" id=searchValue
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #ffffff;">
					<a href="<%=cp%>/img/list.do">전체상품</a>
				</li>

				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #FFFFFF; text-align: center;">
					<a href="<%=cp%>/img/list.do?category=쿠로미">쿠로미</a>
				</li>

				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">
					<a href="<%=cp%>/img/list.do?category=폼폼푸린">폼폼푸린</a>
				</li>
				
				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #FFFFFF; text-align: center;">
					<a href="<%=cp%>/img/list.do?category=헬로키티">헬로키티</a>
				</li>
				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">
					<a href="<%=cp%>/img/list.do?category=마이멜로디">마이멜로디</a>
				</li>
				<li class="number"
					style="vertical-align: middle; display: inline-block; z-index: 10; font-family: Montserrat, Pretendard, 'Malgun Gothic', sans-serif; padding-right: 20px; line-height: 50px; height: 50px; background-color: #ffffff; text-align: center;">
					<a href="<%=cp%>/img/list.do?category=포차코">포차코</a>
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

	<div
		style="top: 50%; right: 5%; z-index: 1000; margin-right: 50px; height: 269px; width: 42.5px; position: fixed; margin-right: 20px;">
		<li><a href="#top"> <img
				src="<%=cp%>/project/image/gotop.png" alt=""
				style="height: 42px; width: 42px; margin-bottom: 60px; background-color: #e47195; border-radius: 10%;"></a></li>
		<li style="background-color: #e47195; text-align: center; border-radius: 50%;" ><a href="<%=cp%>/rev/list.do">리&nbsp;뷰</a></li>
		
		<li><a href="#scrollbottom"> <img
				src="<%=cp%>/project/image/godown.png" alt=""
				style="height: 42px; width: 42px; margin-top: 60px; background-color: #e47195; border-radius: 10%;"></a></li>
	</div>



</body>
</html>