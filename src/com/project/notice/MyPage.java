package com.project.notice;

public class MyPage { //����¡�� ���� 
	
	// ��ü �������� ����
	public int getPageCount(int numPerPage, int dataCount) {//ex (3, 34)
		
		int pageCount = 0 ; 
		
		pageCount = dataCount / numPerPage; // 34/3 = 11  ������ 1���� 3���ø��� 
		
		if (dataCount% numPerPage !=0) { //34%3 != 0 �̹Ƿ� 
			pageCount++;
			
		}
		return pageCount;  //12
	}
	
	
	// ������ ó�� �޼ҵ�   //�Խ��� �ؿ� ������ �ѱ�� ��ȣ��  
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {//String listUrl :  ������ ��ȣ Ŭ���� �ش� �������� �������� 
		
		// ������ ��ȣ 1 �տ��� ������ ����
		// 1 2 3 4 5 ������
		//������ 6 7 8 9 10 ������
		//������ 11 12 
		
		int numPerBlock =5;   // ������ ~ ������ ���̿� 5�� ���� ���̰� �� 
		int currentPageSetup; // ������ �� ��� �ִ� ��  //������ 6 7 8 9 10 ��������� 5�� �� ���� 
		int page; 
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage ==0) { //�̺κ��� Ȯ����, �Ƚᵵ �׸� 
			
			return "";
			
		}
		
		
		//�˻����� [�ۼ���: �ο�]�� �˻��� ���, ���������� ���� ������ŭ�� �Խù��� ������ �ؾ���
		//list.jsp ���� 
		//[select name=searchKey"... �� �˻��׸�, input type="text" name="searchValue"..�� �ο�] 
		
		//list.jsp
		//list.jsp?searchKey=name&searchValue=�ο�         //�̰��� �Ѿ���� 
		if(listUrl.indexOf("?")!=-1) {
			listUrl = listUrl +"&";    
			
		}else {
			
			listUrl = listUrl +"?";     //listUrl���� list.jsp�� ��� �� ���� 
			
		}
		 //if�� ����ϸ鼭 
		//list.jsp?
		//list.jsp?searchKey=name&searchValue=�ο�&
		//�̰����� �ٲ�
		
		
		// ǥ���� ù���������� -1�� �� 
		
		//�������� ���� ���� ���ϴ� ���� 
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if (currentPage % numPerBlock ==0) {
			currentPageSetup = currentPageSetup - numPerBlock;
			
		}
		
		// ������ ����� 
		if(totalPage>numPerBlock && currentPageSetup>0) { //ex) 12> 5 && 5>0 �׷��� ������ ������ 
														  // ���� ���Ŀ� currentPage = 5 �̸� �ȸ������ 	
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + 
					currentPageSetup +"\">������</a>&nbsp;");  
			 //<a href="list.jsp?pageNum=5">������</a>&nbsp;
			
		}
		
		
		// 6 7 8 9 10 ����� 
		page = currentPageSetup +1;
		
		while (page<=totalPage && page<=(currentPageSetup +numPerBlock)) {// ex ) 6< 12 (�Խù��� �� 34�� �ϱ� ������ �������� 12) && 6<=(5+5)
			
			
			
			if(page==currentPage) {  //���� �������� ���� ��������ȣ�� ������, ������ �ٸ��� 
				sb.append("<font color=\"Fuchsia\">" +page +"<font>&nbsp;");
				//<font color="Fuchsia">9</font>&nbsp;
			
			
			}else { //�׷��� ������ 
				
				sb.append("<a href=\"" + listUrl +"pageNum=" + page +"\">"+
						 page + "</a>&nbsp;");
				//<a href = "list.jsp?pageNum=10">10</a>&nbsp;
				
			}
			page++;
			
		}
		
		
		// ������ �����
		if (totalPage - currentPageSetup > numPerBlock) { //ex) 12-5 > 5 �����ϴϱ� ������ ����� 
			
			sb.append("<a href=\"" + listUrl + "pageNum=" +
					page + "\">������</a>&nbsp;");
			//<a href="list.jsp?pageNum=9">������</a>&nbsp;
			
		}
		
		return sb.toString();
	}
	
	
}
