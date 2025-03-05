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
<title>구매 내역</title>

<script type="text/javascript">

	function cancleOk(url) {
		
		var cancle = confirm("구매를 취소하시겠습니까?");
		
		if(cancle){
			
			window.location.href = url;
			
		}
		
		
	}
	
	window.onload = function() {
        var today = new Date();
        var yyyy = today.getFullYear();
        var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
        var dd = String(today.getDate()).padStart(2, '0');
        
        var todayDate = yyyy + '-' + mm + '-' + dd;
        
        var startDate = document.getElementById('startDate');
        var endDate = document.getElementById('endDate');
        
        
        
        if(!startDate.value){
        	startDate.value = todayDate;
        }
        
        if(!endDate.value){
        	endDate.value = todayDate;
        }
        
    }
	
	function browseDate() {
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/purchased/browseDate.do";
		f.submit();
		
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
						<li><a href="/myshop/index.html">마이쇼핑</a></li>
						<li title="현재 위치"><strong>주문조회</strong></li>
					</ol>
				</div>

				<div class="titleArea">
					<h2>주문조회</h2>
				</div>
				<div
					class="xans-element- xans-myshop xans-myshop-orderhistorytab ec-base-tab ">
					<ul class="menu">
						<li class="tab_class selected"><a
							href="<%=cp%>/purchased/purchased.do">주문내역조회
						</a></li>
						<li class="tab_class_cs"><a href="<%=cp%>/cancel/cancel.do">취소내역
						</a></li>
					</ul>
				</div>
				<div class="xans-element- xans-myshop xans-myshop-orderhistoryhead">
					<fieldset class="ec-base-box">
						<a href="<%=cp%>/purchased/purchased.do?daySelect=0"> 전체 </a>
						&nbsp; <a href="<%=cp%>/purchased/purchased.do?daySelect=1">
							오늘 </a> &nbsp; <a href="<%=cp%>/purchased/purchased.do?daySelect=2">
							1주일 </a> &nbsp; <a href="<%=cp%>/purchased/purchased.do?daySelect=3">
							1개월 </a> &nbsp; <a href="<%=cp%>/purchased/purchased.do?daySelect=4">
							3개월 </a> &nbsp; <a href="<%=cp%>/purchased/purchased.do?daySelect=5">
							6개월 </a> &nbsp; <label for="startDate">Start Date:</label> <input
							type="date" id="startDate" name="startDate"
							value="${startDateStr}" /> &nbsp; ~ &nbsp; <label for="endDate">End
							Date:</label> <input type="date" id="endDate" name="endDate"
							value="${endDateStr}" /> &nbsp;&nbsp; <input type="button"
							onclick="browseDate();" value="조회" />

					</fieldset>
				</div>

				<br />
				<div
					class="xans-element- xans-myshop xans-myshop-orderhistorylistitem ec-base-table typeList">
					<div class="title">
						<h3>주문 상품 정보</h3>
					</div>
					<table border="1" summary="">
						<colgroup>
							<col style="width: 135px;">
							<col style="width: 93px;">
							<col style="width: auto;">
							<col style="width: 61px;">
							<col style="width: 111px;">
							<col style="width: 111px;">
							<col style="width: 111px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">주문일자<br>[주문번호]
								</th>
								<th scope="col">이미지</th>
								<th scope="col">상품정보</th>
								<th scope="col">수량</th>
								<th scope="col">상품구매금액</th>
								<th scope="col">주문처리상태</th>
								<th scope="col">취소/교환/반품</th>
							</tr>
						</thead>

						<c:forEach var="dto" items="${lists }">
							<tr height="30">
								<td align="center">${dto.buyDay }<br /> <br />${dto.pid }</td>
								<td align="center"><img
									src="${imagePath }/${dto.saveFileName}" width="180" /></td>
								<td align="center">${dto.pname }</td>
								<td align="center">${dto.purchCount }</td>
								<td align="center">${dto.purchCount * dto.price }</td>
								<td align="center">구매 완료</td>
								<td align="center"><a href="javascript:void(0);"
									onclick="cancleOk('<%=cp %>/purchased/buyCancel.do?pid=${dto.pid}');">
										취소 </a></td>
							</tr>

						</c:forEach>

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
	</form>

	<jsp:include page="footer.jsp" />

	.
	
</body>
</html>