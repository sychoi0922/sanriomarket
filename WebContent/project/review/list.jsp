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
<title>산리오 후기(-)</title>
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/optimiuser.css" />

</head>
<body>
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
	<jsp:include page="../header.jsp" />

	<div id="container">
		<div id="contents_sub">
			<div
				class="xans-element- xans-board xans-board-readpackage-4 xans-board-readpackage xans-board-4 ">
				<div
					class="xans-element- xans-board xans-board-title-4 xans-board-title xans-board-4 ">
					<div class="path">
						<span>현재 위치</span>
						<ol>
							<li><a href="/">홈</a></li>
							<li title="현재 위치"><strong>구매후기</strong></li>
						</ol>
					</div>
					<div class="title">
						<h2>
							<font color="#555555">구매후기</font>
						</h2>
						<p>고객님의 소중한 후기가 많은 분들께 큰 도움이 됩니다 :D</p>
					</div>
				</div>

				<div class="ec-base-table typeWrite">
					<div align="right">
						<a href="<%=cp%>/rev/created.do" class="btnNormalFix sizeS">후기쓰기</a>
					</div>
					<table border="1">
						<caption>상품 게시판 목록</caption>


						<thead>
							<tr style="font-size: 15px">
								<td align="left">번호</td>
								<td rowspan="5">상품정보</td>
								<td rowspan="1">제목</td>
								<td rowspan="1">작성자</td>
								<td rowspan="1">작성일</td>
							</tr>
						</thead>
						<tbody>
							<tr style="background-color: #FFFFFF; color: #000000;"
								class="xans-record-">


								<c:forEach var="dto" items="${lists }">
<tr style="background-color: #FFFFFF; color: #000000;"
								class="xans-record-">

										<td>${dto.rvnum }</td>
										<td>${dto.pname }</td>
										<td><strong> <a
												href="${articleUrl}&rvnum=${dto.rvnum}">${dto.title }</a></strong></td>
										<td>${dto.id }</td>
										<td scope="col" class="">${dto.created }</td>


									</tr>
								</c:forEach>
						</tbody>
						<tr>

							<td colspan="5" align="center">
								<form action="" method="post" name="dataCountForm">
									<div>
										<p>
											<c:if test="${dataCount!=0 }">
					${pageIndexList }
				</c:if>
											<c:if test="${dataCount==0 }">
					등록된 게시물이 없습니다.
				</c:if>
										</p>

									</div>
								</form>
							</td>
						<tr>
						</tr>
					</table>
				</div>



				<div align="left" style="display: flex; align-items: center;">
					<span>검색어</span>
					<form action="" method="post" name="searchForm"
						style="margin-left: 10px;">
						<select id="search_key" name="searchKey">
							<option value="title">제목</option>
							<option value="pname">상품</option>
							<option value="id">작성자</option>
							<option value="content">내용</option>
						</select> <input type="text" name="searchValue" class="inputTypeText"
							style="margin-left: 5px;" /> <input type="button" value="검 색"
							class="btnEmFix" onclick="sendIt();" style="margin-left: 5px;" />
					</form>
				</div>
			</div>
		</div>
	</div>




	
	<br />
	<br />
	<br />
	<br />
	<br />
	<div id="scrollbottom"></div>
	<jsp:include page="../footer.jsp" />
</body>
</html>