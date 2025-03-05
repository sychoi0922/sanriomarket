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
<title>산리오 공지사항</title>


<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/optimiuser.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/project.css" />


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
			<div
				class="xans-element- xans-board xans-board-readpackage-4 xans-board-readpackage xans-board-4 ">
				<div
					class="xans-element- xans-board xans-board-title-4 xans-board-title xans-board-4 ">
					<div class="path">
						<span>현재 위치</span>
						<ol>
							<li><a href="<%=cp%>/view/main.do">홈</a></li>
							<li title="현재 위치"><strong>공지사항</strong></li>
						</ol>
					</div>
					<div class="title">
						<h2>
							<font color="#555555">공지사항</font>
						</h2>
						<p>고객님께 알려드리는 공지사항입니다 :D</p>
					</div>
				</div>
				<div class="DB_board_cate"></div>
				<div class="boardSort">
					<span
						class="xans-element- xans-board xans-board-replysort-1002 xans-board-replysort xans-board-1002 "></span>
				</div>





				<div class="ec-base-table typeWrite">
					<table border="1">
						<caption>상품 게시판 상세</caption>
						<colgroup>
							<col style="width: 130px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">제목</th>
								<td>${dto.title }</td>
							</tr>
							<tr>
								<th scope="row">작성자</th>
								<td>${dto.writer }</td>
							</tr>
							<tr>
								<th scope="row">작성일</th>
								<td>${dto.created }</td>

							</tr>
							<tr>
								<th scope="row">내용</th>
								<td style="padding: 20px 80px 20px 62px;" valign="top"
									height="200">${dto.content}</td>
							</tr>


						</tbody>
					</table>
					<div class="ec-base-button ">
						<span class="gLeft"> <a
							href="<%=cp %>/not/list.do?${params}" class="btnNormalFix sizeS">목록</a>
						</span>
					</div>
					<div>
						<ul>
							<c:choose>
								<c:when test="${dto.nnum==1 }">
									<li class="next "><strong>다음글</strong> <a
										href="${artNeviUrl }&nnum=${dto.nextNum}">${dto.nextTitle }</a></li>
								</c:when>

								<c:when test="${dto.nnum==dataCount }">
									<li class="next "><strong>이전글</strong> <a
										href="${artNeviUrl }&nnum=${dto.preNum}">${dto.preTitle }</a></li>
								</c:when>

								<c:otherwise>
									<li class="next "><strong>다음글</strong> <a
										href="${artNeviUrl }&nnum=${dto.nextNum}">${dto.nextTitle }</a></li>
									<li class="next "><strong>이전글</strong> <a
										href="${artNeviUrl }&nnum=${dto.preNum}">${dto.preTitle }</a></li>
								</c:otherwise>
							</c:choose>

						</ul>
					</div>
				</div>
			</div>
		</div>


	</div>
	<jsp:include page="../footer.jsp" />

</body>
</html>
