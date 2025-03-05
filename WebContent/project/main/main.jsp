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
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/project.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/project/css/optimiuser.css" />

<script type="text/javascript">


function sendIt() {
	var f = document.searchForm;
	
	f.action = "<%=cp%>/img/list.do";
	
	f.submit();
}
</script>

</head>

<body style="padding: 0px; margin: 0px;">
	
	
	<jsp:include page="../header.jsp" />
<br/><br/><br/><br/><br/>

	<!-- contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents -->
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

	<div id="contents">
		<div style="height: 650px;">
			<div
				style="width: 550px; height: 550px; margin: auto; background-color: #e47195;">

				<img alt="외않되" src="<%=cp%>/project/image/test.png"
					style="height: 80px; width: 100%; height: 100%; object-fit: cover;">

			</div>
		</div>

		<div
			style="margin: auto; font-size: 34px; line-height: 1.2em; font-weight: 670; letter-spacing: 0.1px; font-family: 'TTTtangsbudaejjigaeB'; color: #e47195; text-align: center; background-color: #FCECF0;">
			<h2>
				<span style="font-size: 27px;">따끈따끈 신상템
					<p>매일 업데이트 ! 귀욤뽀짝 신상 라인업</p>
				</span>
			</h2>
		</div><br/><br/>

		<div style="height: auto;">
			<div style="width: 1090px; height: auto; margin: auto;">
				<div>


					<table width="560" align="center" class="boxTA">
						<c:set var="n" value="0" />
						<c:forEach var="pInfoLists" items="${pInfoLists}">
									<c:if test="${n == 0}">
										<tr>
									</c:if>
									<c:if test="${n != 0 && n % 3 == 0}">
										</tr>
										<tr>
									</c:if>
									
									<td width="190" align="center"><a
										href="<%=cp %>/pinfo/detail.do?pnum=${pInfoLists.inum }"> <img
										
											src="${imagePath}/${sp.searchImg(pInfoLists.inum)}" width="180"
											height="180">
										</a> <br /> <b>${pInfoLists.pname} </b> <br /> <b>${pInfoLists.price} 원</b>
									</td>


									<c:set var="n" value="${n + 1}" />


						</c:forEach>

						<c:if test="${n % 3 == 1}">
							<td></td>
						</c:if>

						<c:if test="${n % 3 != 0}">
							<td></td>
						</c:if>

						<c:if test="${n != 0 && n % 3 == 0}">
							</tr>
						</c:if>

						<c:if test="${dataCount == 0}">
							<tr bgcolor="#FFFFFF">
								<td align="center" colspan="3" height="30">등록된 자료가 없습니다.</td>
							</tr>
						</c:if>
					</table>

				</div>
				<!--  -->

			</div>
		</div>

	</div>
	
	<div id="scrollbottom"></div>

	<!-- footerfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooterfooter -->
<br/><br/>


	<jsp:include page="../footer.jsp" />
	<!-- footer -->
	
	
</body>

</html>