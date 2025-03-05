package com.project.notice;

public class MyPage { //페이징을 위함 
	
	// 전체 페이지의 갯수
	public int getPageCount(int numPerPage, int dataCount) {//ex (3, 34)
		
		int pageCount = 0 ; 
		
		pageCount = dataCount / numPerPage; // 34/3 = 11  페이지 1개당 3개올릴것 
		
		if (dataCount% numPerPage !=0) { //34%3 != 0 이므로 
			pageCount++;
			
		}
		return pageCount;  //12
	}
	
	
	// 페이지 처리 메소드   //게시판 밑에 페이지 넘기는 번호들  
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {//String listUrl :  페이지 번호 클릭시 해당 페이지만 나오도록 
		
		// 페이지 번호 1 앞에는 ◀이전 없음
		// 1 2 3 4 5 다음▶
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12 
		
		int numPerBlock =5;   // ◀이전 ~ 다음▶ 사이에 5개 숫자 보이게 함 
		int currentPageSetup; // ◀이전 에 들어 있는 값  //◀이전 6 7 8 9 10 다음▶경우 5가 들어가 있음 
		int page; 
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage ==0) { //이부분은 확인차, 안써도 그만 
			
			return "";
			
		}
		
		
		//검색에서 [작성자: 민영]을 검색할 경우, 한페이지에 정한 갯수만큼의 게시물이 나오게 해야함
		//list.jsp 보면 
		//[select name=searchKey"... 은 검색항목, input type="text" name="searchValue"..은 민영] 
		
		//list.jsp
		//list.jsp?searchKey=name&searchValue=민영         //이값이 넘어오면 
		if(listUrl.indexOf("?")!=-1) {
			listUrl = listUrl +"&";    
			
		}else {
			
			listUrl = listUrl +"?";     //listUrl에는 list.jsp가 들어 올 것임 
			
		}
		 //if문 통과하면서 
		//list.jsp?
		//list.jsp?searchKey=name&searchValue=민영&
		//이것으로 바뀜
		
		
		// 표시할 첫페이지에서 -1한 값 
		
		//◀이전에 들어가는 값을 구하는 공식 
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if (currentPage % numPerBlock ==0) {
			currentPageSetup = currentPageSetup - numPerBlock;
			
		}
		
		// ◀이전 만들기 
		if(totalPage>numPerBlock && currentPageSetup>0) { //ex) 12> 5 && 5>0 그러면 ◀이전 만들어라 
														  // 위의 공식에 currentPage = 5 이면 안만들게함 	
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + 
					currentPageSetup +"\">◀이전</a>&nbsp;");  
			 //<a href="list.jsp?pageNum=5">◀이전</a>&nbsp;
			
		}
		
		
		// 6 7 8 9 10 만들기 
		page = currentPageSetup +1;
		
		while (page<=totalPage && page<=(currentPageSetup +numPerBlock)) {// ex ) 6< 12 (게시물이 총 34개 니까 마지막 페이지가 12) && 6<=(5+5)
			
			
			
			if(page==currentPage) {  //현재 페이지가 찍은 페이지번호와 같으면, 색깔이 다르게 
				sb.append("<font color=\"Fuchsia\">" +page +"<font>&nbsp;");
				//<font color="Fuchsia">9</font>&nbsp;
			
			
			}else { //그렇지 않으면 
				
				sb.append("<a href=\"" + listUrl +"pageNum=" + page +"\">"+
						 page + "</a>&nbsp;");
				//<a href = "list.jsp?pageNum=10">10</a>&nbsp;
				
			}
			page++;
			
		}
		
		
		// 다음▶ 만들기
		if (totalPage - currentPageSetup > numPerBlock) { //ex) 12-5 > 5 충족하니까 다음▶ 만들기 
			
			sb.append("<a href=\"" + listUrl + "pageNum=" +
					page + "\">다음▶</a>&nbsp;");
			//<a href="list.jsp?pageNum=9">다음▶</a>&nbsp;
			
		}
		
		return sb.toString();
	}
	
	
}
