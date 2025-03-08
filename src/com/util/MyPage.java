package com.util;

public class MyPage {
	//전체 페이지 갯수
	public int getPageCount(int numPerPage, int dataCount) {//3, 34
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount ++;	//나머지가 0 이 아니면 페이지를 1개 추가하라
		}
		
		return pageCount;
	}
	
	//페이지 처리 메소드
								//(어떤 페이지뿌릴꺼야, 전체페이지수, 뿌릴 페이지)
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		int numPerBlock = 5;
		//웹에서 표시될 페이지 선택 갯수 
		//		 1 2 3 4 5  다음▶
		//◀이전 6 7 8 9 10 다음▶
		//◀이전  11 12 
		int currentPageSetup; //◀이전을 눌렀을때의 값
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0) {
			return "";
		}
		
	//검색을 안했다면
		//list.jsp
	//검색을 했다면
		//list.jsp?searchKey=name&searchValue=민영
		
		if(listUrl.indexOf("?")!= -1) {
			listUrl = listUrl + "&";
		}else {
			listUrl = listUrl + "?";
		}
	//위의 if문을 거치면 get방식으로전송시 ?가 없기때문에
	//검색을 안했다면
		//list.jsp?
	//검색을 했다면 get방식으로 전송시 ?로 값이 있기때문에
		//list.jsp?searchKey=name&searchValue=민영&
		
		//표시할 첫페이지에서 -1 한 값
		//currentPageSetup : ◀이전에 들어갈 값
		//currentPage: 현제 보고있는 페이지
		//numPerBlock: 페이지를 갯수
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		//나머지가 0일때는  numPerBlock을 빼준다.
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		//◀이전
		if(totalPage> numPerBlock && currentPageSetup>0) {
			sb.append("<a href=\""+ listUrl + "pageNum="
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			//<a href="list.jsp?pageNum=5"◀이전</a>&nbsp;
							//"를 문자화 하기 위해'\'사용 =>\"
		}
		
		// 6 7 8 9 10
		page = currentPageSetup+1;
				//현제 totalPage보다 크지않고
				//이전페이지+페이지표지 수 의 전까지
		while(page<=totalPage && page<=(currentPageSetup + numPerBlock)) {
			//현제 페이지를 구분해주는 작업
			if(page == currentPage) {
				sb.append("<font color=\"Fuchsia\">" +page+"</font>&nbsp;");
				//<font color="Fuchsia">9</font>&nbsp;
			}else {
				sb.append("<a href=\""+listUrl + "pageNum=" +page +"\">"
						+page +"</a>&nbsp;");
				//<a href="list.jsp?pageNum=10">10</a>&nbsp;
			}
			page++;
		}
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock) {
			//(전체페이지- >페이지표시갯수
			sb.append("<a href=\"" + listUrl+"pageNum="+
					page + "\"> 다음▶</a>&nbsp;");
			//<a href="list.jsp?pageNum=9">다음▶</ㅁ>&nbsp;
		}
		return sb.toString();
		
		
	}
	
}
