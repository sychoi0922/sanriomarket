<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400&family=Pretendard:wght@400&display=swap" rel="stylesheet">
<style>
h4 {
    font: 1.5em 'Montserrat', 'Pretendard', 'Malgun Gothic', sans-serif;
    display: block;
    margin-block-start: 1.33em;
    margin-block-end: 1.33em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
    unicode-bidi: isolate;
}
a {
    display: inline-block; /* 링크의 기본 스타일을 블록처럼 보이도록 설정 */
    text-decoration: none; /* 링크의 밑줄 제거 */
}

input[type="button"] {
    box-sizing: border-box; /* 버튼의 패딩과 테두리가 전체 크기에 포함되도록 설정 */
}
body, code {
	font: 0.75em 'Montserrat', 'Pretendard', 'Malgun Gothic', sans-serif;
    color: #636363;
    background: #fff;
    font-size: 13px;
    font-weight: bold;
}

.container {
	width: 90%;
	max-width: 1200px;
	margin: 20px auto;
       background-color: #fff;
       border-radius: 8px;
       box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
       padding: 20px;
   }
   .product-content {
       display: flex;
       flex-wrap: wrap;
       gap: 20px;
       margin-bottom: 0; /* 제품 정보와 이미지의 여백을 제거 */
   }
   .product-image {
       flex: 1;
       text-align: center;
       margin-right: 20px; /* 이미지와 테이블 사이에 간격 추가 */
   }
   .product-image img {
       max-width: 100%; /* 이미지 가로 크기를 줄임 */
       height: auto;
       border-radius: 8px;
   }
   .product-details {
       flex: 1;
       background-color: #ffffff;
       border-radius: 8px;
       padding: 20px;
       box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
   }
   .tg {
       border-collapse: collapse;
       width: 100%;
   }
   .tg td, .tg th {
       border: 1px solid #ffffff; /* 테이블 선 색상 */
       padding: 8px; /* 셀 내부 패딩 설정 */
       text-align: left;
       border-radius: 8px; /* 셀의 모서리를 둥글게 */
   }
   .tg th {
       background-color: #ffffff;
       text-align: center;
   }
   .tg .tg-03ax {
       font-weight: bold;
       font-size: 0.9em;
   }
   .tg td input[type="button"] {
   
       height: 100%; /* 버튼을 셀의 높이에 맞게 확장 */
       padding: 10px; /* 버튼의 위아래와 좌우 여백 설정 */
       font-size: 16px; /* 버튼의 폰트 크기 */
       border: none; /* 버튼 테두리 제거 */
       border-radius: 4px; /* 버튼의 모서리를 둥글게 */
       cursor: pointer; /* 마우스 커서를 포인터로 변경 */
       box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 버튼에 그림자 추가 */
       display: block; /* 버튼을 블록 요소로 변경 */
   }
   .button1 {
       font: 0.75em 'Montserrat', 'Pretendard', 'Malgun Gothic', sans-serif;
       background-color: #DBBAD7; /* 바로구입 버튼 배경색 */
       color: #blue; /* 버튼 텍스트 색상 */
       width: 100%;
       font-size: 13px;
       font-weight: bold;
   }
           .button1:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
        }
   .button2 {
       background-color: #ffffff; /* 장바구니 버튼 배경색 */
       color: #blue; /* 버튼 텍스트 색상 */
       flex: 1; /* 버튼의 너비를 동일하게 분배 */
   }
   .button-container {
       display: flex;
       gap: 10px; /* 버튼 사이 간격 추가 */
   }
   .button2 img {
       width: 20px; /* 아이콘 크기 조정 */
       height: 20px; /* 아이콘 크기 조정 */
       vertical-align: middle; /* 아이콘과 텍스트 수직 중앙 정렬 */
       margin-right: 8px; /* 아이콘과 텍스트 사이 여백 */
   }
   
.button-container {
    display: flex;
    gap: 10px; /* 버튼 사이 간격 추가 */
}
.btn-s, .btn-l {
    width: 100%; /* 버튼의 너비를 100%로 설정하여 버튼 컨테이너 내에서 고정된 크기 유지 */
    height: 40px; /* 버튼의 높이 설정 */
    border: none; /* 버튼 테두리 제거 */
    border-radius: 4px; /* 버튼의 모서리를 둥글게 */
    cursor: pointer; /* 클릭 커서 설정 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 버튼에 그림자 추가 */
}

.btn-s {
    width: 15%; /* 버튼 크기를 절반으로 줄임 */
}

.btn-l {
    width: 65%; /* 장바구니 버튼 크기 증가 */
}
   .recommendations {
       margin-top: 30px;
   }
   .recommendations h2 {
       font-size: 1.5em;
       text-align: center;
       margin-bottom: 20px;
       color: #333;
   }
   .recommendations .products {
       display: flex;
       flex-wrap: wrap;
       gap: 20px;
       justify-content: space-between;
   }
   .recommendations .product {
       flex: 1 1 calc(20% - 20px);
       background-color: #fff;
       border-radius: 8px;
       box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
       text-align: center;
       padding: 10px;
       box-sizing: border-box;
   }
   .recommendations .product img {
       max-width: 100%;
       height: auto;
       border-radius: 8px;
   }
   .product-details {
       text-align: center; /* 가운데 정렬 */
       margin-top: 30px; /* 제품 상세정보 위쪽 여백 */
   }
   .product-details img {
       display: block;
       margin: 0 auto; /* 이미지 가운데 정렬 */
       max-width: 100%; /* 이미지 최대 폭 설정 */
       height: auto;
       border-radius: 8px;
   }
#quantity {
    border: none; /* 수량 select의 외곽선을 투명하게 설정 */
}

#totalPrice {
    border: none; /* 총상품금액 input의 외곽선을 투명하게 설정 */
}
</style>

<script>
    function calculateTotal() {
        var price = parseFloat(document.getElementById('price').innerText.replace(' 원', ''));
        var quantity = parseInt(document.getElementById('quantity').value);
        var total = price * quantity;
        document.getElementById('totalPrice').value = total + ' 원('+ quantity + '개)';
    }
    // 페이지 로드 시 총상품금액을 초기화
    window.onload = function() {
        calculateTotal();
    };
    
 	// 클릭 시 이미지 변경
    document.addEventListener('DOMContentLoaded', function() {
        var wishButton = document.getElementById('wishButton');
        if (wishButton) {
            wishButton.addEventListener('click', function() {
                wishButton.style.backgroundImage = "url('<%=cp %>/project/pInformation/icon/wished.png')";
            });
        }
    });
 	
 	

	function sendDataPur(){
		
		
		var f = document.myForm;
		
		var quantity = document.getElementById("quantity").value;
		var pnum = document.getElementById("pnum").value;
		var imgName = document.getElementById("imgName").value;
		
		var result = confirm("구매하시겠습니까?");
		
		if(result){

			f.action = "<%=cp%>/purchased/buyProduct.do";
			f.submit();
				
		}else{
			alert("취소되었습니다.");
		}
		
	}
	
	function sendDataBas(){
		
		var quantity = document.getElementById("quantity").value;
		var pnum = document.getElementById("pnum").value;
		var imgName = document.getElementById("imgName").value;
		
		
		var f = document.myForm;
		
		var result = confirm("장바구니로 옮기시겠습니까?");
		
		if(result){

			f.action = "<%=cp%>/basket/moveBasket.do";
			f.submit();
				
		}else{
			alert("취소되었습니다.");
		}
		
	}
	
</script>


</head>
<body>

<jsp:include page="../header.jsp" />
<div class="container">
    <form action="" method="get" name="myForm" enctype="multipart/form-data">
        <!-- 상품 이미지와 상세정보 -->
        <div class="product-content">
            <div class="product-image">
                <img src="<%=cp %>/pds/imgFile/${imgName}" alt="상품 이미지"/>
            </div>
            <div class="product-details">
                <table class="tg">
                    <tbody>
                        <tr>
                            <h4><td colspan="2"><b>${dto.pname}</b></td></h4>
                            
                        </tr>
                        <tr>
                            <td>판매가</td>
                            <td id="price"><span style="color: #E47195;">${dto.price} 원</span></td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>무료배송</td>
                        </tr>
                        <tr>
                            <td>수량</td>
                            <td>
                                <select id="quantity" name="quantity" onchange="calculateTotal()">
                                    <c:forEach var="i" begin="1" end="10">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tg-02ax">총상품금액</td>
                            <td >
                                <input type="text" class="tg-03ax" id="totalPrice" readonly value="${dto.price} 원()"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            	<input type="hidden" id="pnum" name="pnum" value="${pnum }"/>
                            	<input type="hidden" id="imgName" name="imgName" value="${imgName }"/>
                                <input class="button1" type="button" value="바로 구입" onclick="sendDataPur()"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="button-container">
                                    <input class="btn-l" type="button" onclick="sendDataBas()" style="background:url('<%=cp %>/project/pInformation/icon/btn_basket.png') no-repeat center; background-size: 35px 35px;">
                                    <input class="btn-s" type="button" id="wishButton" style="background:url('<%=cp %>/project/pInformation/icon/wish.png') no-repeat center; background-size: 20px 20px;" onclick="">
                                    <a href="<%=cp %>/img/list.do" class="btn-s" style="background: url('<%=cp %>/project/pInformation/icon/list.png')no-repeat center; background-size: 20px 20px;"></a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 추천 상품 테이블 -->
        <div class="recommendations">
            <h2>추천 상품</h2>
            <div class="products">
                <c:forEach var="dto" items="${lists}">
                    <div class="product">
                        <a href="<%=cp %>/pinfo/detail.do?pnum=${dto.pnum}">
                            <img src="<%=cp %>/pds/imgFile/${dto.saveFileName}" alt="추천 상품 이미지"/>
                        </a>
                        <div>${dto.price} 원</div>
                        <div>${dto.pname}</div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- 상품 상세 정보 -->
        <div class="product-details">
            <h2>상품상세정보</h2>
            <img src="${imagePath}/${dto.originalFileName}" alt="상품 상세 이미지"/>
        </div>
    </form>
</div>

<jsp:include page="../footer.jsp" />


</body>
</html>
