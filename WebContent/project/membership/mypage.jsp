<%@page import="com.project.membership.MembershipDTO"%>
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
<title>마이페이지</title>

<link rel="stylesheet" href="<%=cp%>/project/css/project.css"
	type="text/css">

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
		<div id="contents_sub" style="width: 50%">

			<div class="path">
				<span>현재 위치</span>
				<ol>
					<li><a href="<%=cp%>/view/main.do">홈</a></li>
					<li title="현재 위치">마이 쇼핑</li>
				</ol>
			</div>

			<div class="titleArea">
				<h2>마이쇼핑</h2>
			</div>


			<div class="xans-element- xans-myshop xans-myshop-asyncbenefit">
				<div class="ec-base-box typeMember gMessage ">
					<div class="information">
						<p class="thumbnail">
							<img
								src="//img.echosting.cafe24.com/skin/base_ko_KR/member/img_member_default.gif"
								alt=""
								onerror="this.src='//img.echosting.cafe24.com/skin/base/member/img_member_default.gif';"
								class="myshop_benefit_group_image_tag">
						</p>
						<div class="description">
							<span>저희 쇼핑몰을 이용해 주셔서 감사합니다. <strong class="txtEm"><span><span
										class="xans-member-var-name">${sessionScope.customInfo.id }</span></span></strong>
								님은 <strong>[<span class="displaynone"><img
										src="" alt="" class="myshop_benefit_group_icon_tag"></span><span
									class="xans-member-var-group_name">오리엔탈</span><span
									class="myshop_benefit_ship_free_message"></span>]
							</strong> 회원이십니다.
							</span>
							<p class="displaynone myshop_benefit_display_no_benefit">
								<strong class="txtEm"><span
									class="myshop_benefit_dc_pay"></span> <span
									class="myshop_benefit_dc_min_price">0원 이상</span></strong> 구매시 <strong
									class="txtEm"><span class="myshop_benefit_dc_price">0원</span><span
									class="myshop_benefit_dc_type"></span></strong>을 <span
									class="myshop_benefit_use_dc">추가할인없음</span> 받으실 수 있습니다. <span
									class="myshop_benefit_dc_max_percent"></span>
							</p>
							<p class="displaynone myshop_benefit_display_with_all">
								<strong class="txtEm"><span
									class="myshop_benefit_dc_pay"></span> <span
									class="myshop_benefit_dc_min_price_mileage">0원 이상</span></strong> 구매시 <strong
									class="txtEm"><span
									class="myshop_benefit_dc_price_mileage">0원</span><span
									class="myshop_benefit_dc_type_mileage"></span></strong>을 <span
									class="myshop_benefit_use_dc_mileage"></span> 받으실 수 있습니다. <span
									class="myshop_benefit_dc_max_mileage_percent"></span>
							</p>
							<div class=" gBlank5" id="sGradeAutoDisplayArea">
								<p class=" sAutoGradeDisplay ">
									<strong>[<span class="sNextGroupIconArea displaynone"><img
											src="" alt="" class="myshop_benefit_next_group_icon_tag"></span><span
										class="xans-member-var-sNextGrade">발사믹</span>]
									</strong> 까지 남은 구매금액은 <strong><span
										class="xans-member-var-sGradeIncreasePrice">300,000원</span></strong>
									입니다. (최근 <span class="xans-member-var-sGradePeriod">6개월
										동안</span> 구매금액 : <span class="xans-member-var-sPeriodOrderPrice">0원</span>)
								</p>
								<p class="txtInfo txt11">승급 기준에 따른 예상 금액이므로 총주문 금액과 다를 수
									있습니다.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br />
			<br />




			<div
				class="xans-element- xans-myshop xans-myshop-summary ec-base-box gHalf:before"
				style="width: 30%">

				<ul
					style="display: block; list-style-type: disc; margin-block-start: 2em; margin-block-end: 2em; margin-inline-start: 10px; margin-inline-end: 0px; padding-inline-start: 1px; padding-inline-end: 1px; unicode-bidi: isolate;">

					<li class="shopMain order">
						<h3
							style="display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 15px; margin-inline-end: 0px; unicode-bidi: isolate;">
							<a href="<%=cp%>/purchased/purchased.do"><strong>Order</strong><br />
								<span>주문내역 조회</span></a>
						</h3>
						<p
							style="display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 15px; margin-inline-end: 0px; unicode-bidi: isolate;">
							<a href="<%=cp%>/purchased/purchased.do">고객님께서 주문하신 상품의<br>
								주문내역을 확인하실 수 있습니다.
							</a>
						</p>
					</li>
					<br />
					<hr color="#e8e8e8" />
					<br />

					<li class="shopMain wishlist">
						<h3
							style="display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 15px; margin-inline-end: 0px; unicode-bidi: isolate;">
							<a href="<%=cp%>/basket/basket.do"><strong>Cart</strong><br />
								<span>장바구니</span></a>
						</h3>
						<p
							style="display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 15px; margin-inline-end: 0px; unicode-bidi: isolate;">
							<a href="<%=cp%>/basket/basket.do">장바구니에 담아두신<br> 상품의
								목록을 보여드립니다.
							</a>
						</p>
					</li>
					<br />
					<hr color="#e8e8e8" />
					<br />

					<li class="shopMain profile">
						<h3
							style="display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 15px; margin-inline-end: 0px; unicode-bidi: isolate;">
							<a href="<%=cp%>/pj/updated.do"><strong>Profile </strong><br />
								<span>회원 정보</span></a>
						</h3>
						<p
							style="display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 15px; margin-inline-end: 0px; unicode-bidi: isolate;">
							<a href="<%=cp%>/pj/updated.do">회원이신 고객님의 개인정보를<br> 관리하는
								공간입니다.
							</a>
						</p>
					</li>




				</ul>

			</div>

			<div style="margin: 80px 0px 0px 0px;"></div>

		</div>
	</div>
	<br />
	<br />

	<jsp:include page="../footer.jsp" />





</body>
</html>