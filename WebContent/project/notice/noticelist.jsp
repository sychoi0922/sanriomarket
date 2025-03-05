

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
	href="<%=cp%>/project/css/project.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/optimiuser.css" />


<script type="text/javascript">

	function sendIt(){ //자기가 자기 자신에게 보냄
		
	
		var f =document.searchForm;
		
		f.action = "<%=cp%>
	/not/list.do";

		f.submit();
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
						<caption>게시판 목록</caption>

						<thead>
							<tr style="font-size: 15px">
								<td align="left">번호</td>
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

										<td>${dto.nnum }</td>

										<td><strong> <a
												href="${articleUrl}&nnum=${dto.nnum}">${dto.title }</a></strong></td>
										<td>${dto.writer }</td>
										<td class=""><span class="txtNum">${dto.created }</span></td>


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
							<option value="writer">작성자</option>
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
	<jsp:include page="../footer.jsp" />

</body>
</html>
