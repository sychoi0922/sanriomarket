<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
//String uri = request.getRequestURI();  ==/study/boardTest/created.jsp
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>산리오 후기쓰기</title>


<link rel="stylesheet" type="text/css"href="<%=cp%>/project/css/optimiuser.css" />

<script type="text/javascript">

    function sendIt() {
    	
        var f = document.myForm;

        var titleError = document.getElementById('titleError');
        var contentError = document.getElementById('contentError');
        var rpwdError = document.getElementById('rpwdError');

        titleError.textContent = '';
        contentError.textContent = '';
        rpwdError.textContent = '';

        var str = f.title.value;
        str = str.trim();
        if (!str) {
            titleError.textContent = '제목을 입력하세요.';
            titleError.style.display = 'block';
            f.title.focus();
            return;
        }
        f.title.value = str;

        
        str = f.content.value;
        str = str.trim();
        if (!str) {
            contentError.textContent = '내용을 입력하세요.';
            contentError.style.display = 'block';
            f.content.focus();
            return;
        }
        
        f.content.value = str;
	
        
        str = f.rpwd.value;
        str = str.trim();
        
        if (str!="${dto.rpwd}") {
        	
            rpwdError.textContent = '비밀번호가 틀렸습니다.';
            rpwdError.style.display = 'block';
            f.rpwd.focus();
            return;
        }
        f.rpwd.value = str;

        alert("후기가 수정되었습니다.");
       
        f.action = "<%=cp%>/rev/updated_ok.do";
        f.submit();
    }
</script>
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
                                        <input type="text" name="title" size="60" maxlength="100" value="${dto.title }" />
                                        <span id="titleError" class="error-message"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">제품이름</th>
                                    <td>
                                        <input type="text" name="pname" size="60" maxlength="100" value="${dto.pname }"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">작성자</th>
                                    <td>
                                        <input type="text" name="id" size="60" maxlength="100"
                                            value="${dto.id}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">내용</th>
                                    <td>
                                    
                                        <textarea rows="12" cols="63" name="content"
                                            style="resize: none; background-color: #ffffff;">${dto.content }</textarea>
                                        
                                        <span id="contentError" class="error-message"></span>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <th scope="row">비밀번호</th>
                                    <td>
                                        <input type="password" id="rpwd" name="rpwd" size="35" maxlength="10" value="${dto.rpwd }"/>
                                        <span id="rpwdError" class="error-message"></span>
                                    </td>
                                
                            </tbody>
                        </table>
                        </div>
                        <br/>
                        <div>
                        <input type="hidden" name="rvnum" value="${dto.rvnum }">
						<input type="hidden" name="pageNum" value="${pageNum }">
						
						<input type="hidden" name="searchkey" value="${searchKey }">
						<input type="hidden" name="searchValue" value="${searchValue }">
                            
                            <input type="button" class="btnNormalFix sizeS " value="수정하기" onclick="sendIt();"> 
                            <a href="<%=cp%>/rev/list.do?${params}" class="btnNormalFix sizeS">수정취소</a>
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
		<jsp:include page="../footer.jsp" />
</body>
</html>