<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자주묻는질문</title>


<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/project.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/optimiuser.css" />
	
	
<link rel="stylesheet" href="<%=cp%>/project/css/main.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/list.css" />

	

</head>
<body>


	<jsp:include page="../header.jsp" />


	<div id="container">
		<div id="contents_sub">

			<div
				class="xans-element- xans-board xans-board-listpackage-3 xans-board-listpackage xans-board-3 ">
				<div
					class="xans-element- xans-board xans-board-title-3 xans-board-title xans-board-3 ">
					<div class="path">
						<span>현재 위치</span>
						<ol>
							<li><a href="<%=cp%>/view/main.do">홈</a></li>
							<li title="현재 위치"><strong>FAQ</strong></li>
						</ol>
					</div>
					<p class="imgArea"
						style="padding: 0px 0px 0px 0px; text-align: center;"></p>
					<div class="title">
						<h2>
							<font color="#555555">자주묻는질문</font>
						</h2>
						<p>자주 묻는 질문에 대한 답변입니다 :D</p>
					</div>
				</div>
				<div class="DB_board_faq">



					<ol>
						<li class="false"><a href="${listUrl}">전체</a></li>
						<li class="false"><a href="${listUrl}?category=주문/결제">주문/결제</a></li>
						<li class="false"><a href="${listUrl}?category=배송">배송</a></li>
						<li class="false"><a href="${listUrl}?category=반품/교환/환불">반품/교환/환불</a></li>
						<li class="false"><a href="${listUrl}?category=기타">기타</a></li>
					</ol>
				</div>


				<div class="ec-base-table typeList gBorder">

					<table border="1">
						<caption>게시판 목록</caption>
						<colgroup
							class="xans-element- xans-board xans-board-listheader-3 xans-board-listheader xans-board-3 ">
							<col style="width: 50px;">
							<col style="width: 130px;" class="">
							<col style="width: auto;">
						</colgroup>
						<tbody
							class="xans-element- xans-board xans-board-notice-3 xans-board-notice xans-board-3 center">

							<table border="1" summary="">
								<caption>게시판 목록</caption>
								<colgroup>
									<col style="width: 50px;">
									<col style="width: 130px;">
									<col style="width: auto;">
								</colgroup>
								<tbody>
									<c:forEach var="dto" items="${lists}">
										<!-- 질문이 있는 행 -->
										<tr>
											<td style="background-color: #fcecf0">Q.</td>
											<td style="background-color: #fcecf0">${dto.category}</td>
											<td style="background-color: #FFFFFF"><details>
												<summary>${dto.question}</summary>
												<div>
													<table border="1" style="background-color: #fcecf0">
														<tr style="background-color: #FFFFFF">
															<td>${dto.answer}</td>
														</tr>
													</table>
												</div>
												</details></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />


	<jsp:include page="../footer.jsp" />


</body>
</html>







