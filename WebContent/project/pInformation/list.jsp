<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body, code {
	font: 0.75em 'Montserrat', 'Pretendard', 'Malgun Gothic', sans-serif;
	color: #636363;
	background: #fff;
	font-size: 13px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function sendIt() {
		var f = document.searchForm;
		
		f.action = "<%=cp%>/pinfo/list.do";
		f.submit();
	}
</script>
</head>

<body>
	<br />
	<br />
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
	
	<br />
	<br />
	<table style="margin: auto">
		<tr>
			<td align="center"><font size="5px" color="#636363"></font></td>
		</tr>
	</table>
	<br />
	<table width="700" border="0" cellpadding="0" cellspacing="0"
		style="margin: auto;">

		<tr height="30">

			<td align="left" width="50%">total ${dataCount} items</td>
			<td align="right" width="50%">
				<form action="" method="post" name="searchForm">
					<select name="searchKey">
						<option value="subject">상품명</option>
					</select><input type="text" name="searchValue"><input type="button"
						value=" 검 색 " onclick="sendIt();"></input>
				</form>
			</td>
		</tr>
	</table>





	<table width="600" border="0" cellpadding="0" cellspacing="0"
		style="margin: auto;">
		<tr>
			<td height="3" bgcolor="#ffffff" align="center"></td>
		</tr>
	</table>

	<table width="600" border="0" cellspacing="1" cellpadding="3"
		bgColor="#FFFFFF" style="margin: auto;">

		<c:set var="n" value="0" />
		<!-- 행추적 n변수 0초기화 -->
		<c:forEach var="dto" items="${lists}">
			<!-- lists를 dto변수로 반복 -->
			<c:if test="${n==0}">
				<!-- n값이 0이면 새 행<tr>시작 -->
				<tr bgcolor="#FFFFFF">
			</c:if>
			<c:if test="${n!=0&&n%3==0 }">
				<!-- n을3으로나누었을때 나머지가 0이면 종료 -->
				</tr>
				<tr bgcolor="#FFFFFF">
			</c:if>
			<b><td width="200" align="center"><a
					href="<%=cp %>/pinfo/detail.do?pnum=${dto.pnum}"> <img
						src="${imagePath}/${dto.saveFileName}" width="180" height="180" /></a>
					<br /> <font color="#555555"> ${dto.pname}&nbsp;</font><br /> <span
					style="color: #E47195;">${dto.price} 원</span> <!--   <a href="<%=cp%>/img/delete.do?inum=${dto.inum}&pageNum=${pageNum}">삭제</a> -->
			</td></b>

			<c:set var="n" value="${n+1}" />
		</c:forEach>
		<!--
	<c:if test="${n>0||n%3!=0 }">
		<c:forEach var="i" begin="${n%3+1}" end="3" step="1">
			<td>&nbsp;</td>
		</c:forEach>
	</c:if>
-->
		<!-- 사진을 1개 넣었을때 2번째 TD가 만들기-->
		<c:if test="${n%3==1 }">
			<td></td>
		</c:if>

		<!-- 사진을 1개 넣었을때 3번째 TD가 만들기-->
		<c:if test="${n%3!=0 }">
			<td></td>
		</c:if>


		<c:if test="${n!=0 }">
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

	<table width="600" border="0" cellpadding="0" cellspacing="0"
		style="margin: auto;">
		<tr>
			<td height="3" bgcolor="#ffffff" align="center"></td>
		</tr>
	</table>
<div id="scrollbottom"></div>
	<br />
	<br />
	<br />
	<br />
</body>
</html>
</body>
</html>