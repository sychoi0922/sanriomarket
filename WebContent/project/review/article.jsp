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
<title>산리오 후기</title>
<link rel="stylesheet" type="text/css"href="<%=cp%>/project/css/optimiuser.css" />
<link rel="stylesheet" type="text/css"href="<%=cp%>/project/project.css" />

</head>
<body>
    
      <jsp:include page="../header.jsp" />
   
    

    <div id="container">
        <div id="contents_sub">
            <div class="xans-element- xans-board xans-board-readpackage-4 xans-board-readpackage xans-board-4 ">
                <div class="xans-element- xans-board xans-board-title-4 xans-board-title xans-board-4 ">
                    <div class="path">
                        <span>현재 위치</span>
                        <ol>
                            <li><a href="/">홈</a></li>
                            <li title="현재 위치"><strong>구매후기</strong></li>
                        </ol>
                    </div>
                    <div class="title">
                        <h2><font color="#555555">구매후기</font></h2>
                        <p>고객님의 소중한 후기가 많은 분들께 큰 도움이 됩니다 :D</p>
                    </div>
                </div>

                <form name="myForm" action="" method="get" enctype="multipart/form-data">
                    <div class="ec-base-table typeWrite">
                        <table border="1" >
                            <caption>상품 게시판 상세</caption>
                            <colgroup>
                                <col style="width: 130px;">
                                <col style="width: auto;">
                            </colgroup>
                            <tbody>
                                   <tr>
                                    <th scope="row">제목</th>
                                    <td>
                                        ${dto.title } 
                                    </td>
                                </tr>
                                <tr>
                                <th scope="row">제품이름</th>
                                <td>
										${dto.pname }
                                </td>
                                </tr>
                                <tr>
                                    <th scope="row">작성자</th>
                                    <td>
                                       ${dto.id }
                                    </td>
                                  
                                </tr>
                                <tr>
                                    <th scope="row">내용</th>
                                   <td style="padding: 20px 80px 20px 62px;"
				valign="top" height="200">
                                      
									${dto.content}
                                    </td>
                                </tr>
                                
                                
                            </tbody>
                        </table>
                        </div>
                        <br/>
                        <div>
                            <a href="<%=cp%>/rev/list.do" class="btnNormalFix sizeS">목록</a>
                            <a href="<%=cp%>/rev/updated.do?rvnum=${dto.rvnum}&${params }" class="btnNormalFix sizeS">수정하기</a>
                        
                        </div>
				</form>
			</div>
		</div>
	</div>


	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
		<jsp:include page="../footer.jsp" />
</body>
</html>