<%@page import="com.project.basket.BasketDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.project.basket.BasketDTO"%>
<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	request.setCharacterEncoding("UTF-8"); 
	String cp=request.getContextPath();
	
	
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 장바구니 </title>
<script type="text/javascript">

	//체크박스 전체 선택 기능.
	function toggleCheckboxes(source) {
		var checkboxes = document.getElementsByName('check');
		for (var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
	
	
	function deleteCheck() {
		
		
		var f = document.myForm;
		var result = confirm("장바구니에서 제거하시겠습니까?");
		
		if(result){

			f.action = "<%=cp%>/basket/deleteBasketCheck.do";
			f.submit();
				
		}else{
			alert("취소되었습니다.");
		}
		
		
		
	}
	
	function deleteAll() {
		
		
		var f = document.myForm;
		var result = confirm("장바구니에서 제거하시겠습니까?");
		
		if(result){
			

			f.action = "<%=cp%>/basket/deleteBasketAll.do";
			f.submit();
			
		}else{
			alert("취소되었습니다.");
		}
		
		
	}
	
	
	function buyAll() {
		
		var f = document.myForm;
		var result = confirm("구매하시겠습니까?");
		
		if(result){

			f.action = "<%=cp%>/basket/buyBasketAll.do";
			f.submit();
			
		}else{
			alert("취소되었습니다.");
		}
		
		
	}
	
	function buyCheck() {
		
		var f = document.myForm;
		var result = confirm("구매하시겠습니까?");
		
		if(result){
			
			f.action = "<%=cp%>/basket/buyBasketCheck.do";
			f.submit();
			
		}else{
			alert("취소되었습니다.");
		}
		
	}
	
	function countChange(){
		var f = document.myForm;
		
		f.action = "<%=cp%>/basket/countChange.do";
		f.submit();
		
	}
	
	function buyOne(url){
		
		var result = confirm("구매하시겠습니까?");
		
		if(result){
			window.location.href = url;
			
		}else{
			alert("취소되었습니다.");
		}
	}
	
	function deleteOne(url){
		
		var result = confirm("장바구니에서 제거하시겠습니까?");
		
		if(result){
			
			window.location.href = url;
		}else{
			
			alert("취소되었습니다.");
		}
	}
	
</script>



<link rel="stylesheet" href="<%=cp%>/project/css/main.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/list.css" />

</head>
<body>

	<jsp:include page="header.jsp" />


	<form action="" method="post" name="myForm">
		<div id="container">
			<div id="contents_sub">

				<div class="path">
					<span>현재 위치</span>
					<ol>
						<li><a href="<%=cp%>/view/main.do">홈</a></li>
						<li title="현재 위치"><strong>장바구니</strong></li>
					</ol>
				</div>

				<div class="titleArea">
					<h2>장바구니</h2>
				</div>

				<div class="xans-element- xans-order xans-order-basketpackage ">
					<div class="orderListArea ec-base-table typeList gBorder">
						<div class="xans-element- xans-order xans-order-normtitle title ">
							<h3>일반상품 (${size })</h3>
						</div>
						<table border="1" summary=""
							class="xans-element- xans-order xans-order-normnormal xans-record-">
							<caption>기본배송</caption>
							<colgroup>
								<col style="width: 27px">
								<col style="width: 92px">
								<col style="width: auto">
								<col style="width: 88px">
								<col style="width: 110px">
								<col style="width: 88px">
								<col style="width: 88px">
								<col style="width: 85px">
								<col style="width: 98px">
								<col style="width: 110px">
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><input type="checkbox"
										onclick="toggleCheckboxes(this)"></th>
									<th scope="col">이미지</th>
									<th scope="col">상품정보</th>
									<th scope="col">수량</th>
									<th scope="col">상품구매금액</th>
									<th scope="col">할인금액</th>
									<th scope="col" class="mileage">적립금</th>
									<th scope="col">배송구분</th>
									<th scope="col">배송비</th>
									<th scope="col">선택</th>
								</tr>
							</thead>
							<tfoot class="right">
								<tr>
									<td colspan="10">합계 : <strong class="txtEm gIndent10"><span
											id="normal_normal_ship_fee_sum" class="txt18">
												${totalPrice } </span>원</strong> <span class="displaynone"> </span></td>
								</tr>
							</tfoot>

							<tbody class="xans-element- xans-order xans-order-list center">
								<c:forEach var="dto" items="${lists }">
									<tr class="xans-record-">

										<td><input type="checkbox" name="check"
											value="${dto.pnum }"></td>
										<td><a href="<%=cp %>/pinfo/detail.do?pnum=${dto.pnum}">
												<img src="${imagePath }/${dto.saveFileName}" width="180" />
										</a></td>
										<td>${dto.pname }</td>
										<td class="right"><span class=""><span
												class="ec-base-qty"> <input id="quantity"
													name="quantity" size="2" value="${dto.basketCount }"
													type="text"> <a
													href="<%=cp %>/basket/countChange.do?pnum=${dto.pnum}&count=${dto.basketCount + 1}">
														<img src="${imagePath }/up.gif">
												</a> <a
													href="<%=cp %>/basket/countChange.do?pnum=${dto.pnum}&count=${dto.basketCount - 1}">
														<img src="${imagePath }/down.gif">
												</a>
											</span></span></td>
										<td>${dto.price * dto.basketCount }</td>
										<td>0원</td>
										<td>0원</td>
										<td>${dto.ship }</td>
										<td>무료</td>
										<td><a href="javascript:void(0);"
											onclick="buyOne('<%=cp %>/basket/buyBasketOne.do?pnum=${dto.pnum}');">
												<img src="${imagePath }/주문하기.png" width="80" />
										</a> <br /> <br /> <a href="javascript:void(0);"
											onclick="deleteOne('<%=cp %>/basket/deleteBasketOne.do?pnum=${dto.pnum}');">
												<img src="${imagePath }/x 삭제.png" width="80" />
										</a></td>

									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>

					<div
						class="xans-element- xans-order xans-order-selectorder ec-base-button ">
						<span class="gLeft"> <strong class="text">선택상품을</strong> <a
							href="#none" class="btnEm" onclick="deleteCheck()"><i
								class="icoDelete"></i> 삭제하기</a></span> <span class="gRight"> <a
							href="#none" class="btnNormal" onclick="deleteAll()">장바구니비우기</a></span>

					</div>
					<div
						class="xans-element- xans-order xans-order-totalorder ec-base-button justify">
						<a href="#none" onclick="buyAll()" class="btnSubmitFix sizeM">전체상품주문</a>
						<a href="#none" onclick="buyCheck()" class="btnEmFix sizeM">선택상품주문</a>
						<span class="gRight"><a
							href="<%=cp%>project/main/main.jsp" class="btnNormalFix sizeM">쇼핑계속하기</a></span>
					</div>
				</div>
				<div
					class="xans-element- xans-order xans-order-basketguide ec-base-help ">
					<h3>이용안내</h3>
					<div class="inner">
						<h4>장바구니 이용안내</h4>
						<ol>
							<li class="item1">해외배송 상품과 국내배송 상품은 함께 결제하실 수 없으니 장바구니 별로 따로
								결제해 주시기 바랍니다.</li>
							<li class="item2">해외배송 가능 상품의 경우 국내배송 장바구니에 담았다가 해외배송 장바구니로
								이동하여 결제하실 수 있습니다.</li>
							<li class="item3">선택하신 상품의 수량을 변경하시려면 수량변경 후 [변경] 버튼을 누르시면
								됩니다.</li>
							<li class="item4">[쇼핑계속하기] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</li>
							<li class="item5">장바구니와 관심상품을 이용하여 원하시는 상품만 주문하거나 관심상품으로
								등록하실 수 있습니다.</li>
							<li class="item6">파일첨부 옵션은 동일상품을 장바구니에 추가할 경우 마지막에 업로드 한 파일로
								교체됩니다.</li>
						</ol>
						<h4>무이자할부 이용안내</h4>
						<ol>
							<li class="item1">상품별 무이자할부 혜택을 받으시려면 무이자할부 상품만 선택하여 [주문하기]
								버튼을 눌러 주문/결제 하시면 됩니다.</li>
							<li class="item2">[전체 상품 주문] 버튼을 누르시면 장바구니의 구분없이 선택된 모든 상품에
								대한 주문/결제가 이루어집니다.</li>
							<li class="item3">단, 전체 상품을 주문/결제하실 경우, 상품별 무이자할부 혜택을 받으실 수
								없습니다.</li>
							<li class="item4">무이자할부 상품은 장바구니에서 별도 무이자할부 상품 영역에 표시되어,
								무이자할부 상품 기준으로 배송비가 표시됩니다.<br>실제 배송비는 함께 주문하는 상품에 따라 적용되오니
								주문서 하단의 배송비 정보를 참고해주시기 바랍니다.
							</li>
						</ol>
					</div>
				</div>

			</div>
		</div>
	</form>

	<jsp:include page="footer.jsp" />

	
</body>
</html>