package com.util;

public class MyPage {
	//��ü ������ ����
	public int getPageCount(int numPerPage, int dataCount) {//3, 34
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount ++;	//�������� 0 �� �ƴϸ� �������� 1�� �߰��϶�
		}
		
		return pageCount;
	}
	
	//������ ó�� �޼ҵ�
								//(� �������Ѹ�����, ��ü��������, �Ѹ� ������)
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		int numPerBlock = 5;
		//������ ǥ�õ� ������ ���� ���� 
		//		 1 2 3 4 5  ������
		//������ 6 7 8 9 10 ������
		//������  11 12 
		int currentPageSetup; //�������� ���������� ��
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0) {
			return "";
		}
		
	//�˻��� ���ߴٸ�
		//list.jsp
	//�˻��� �ߴٸ�
		//list.jsp?searchKey=name&searchValue=�ο�
		
		if(listUrl.indexOf("?")!= -1) {
			listUrl = listUrl + "&";
		}else {
			listUrl = listUrl + "?";
		}
	//���� if���� ��ġ�� get����������۽� ?�� ���⶧����
	//�˻��� ���ߴٸ�
		//list.jsp?
	//�˻��� �ߴٸ� get������� ���۽� ?�� ���� �ֱ⶧����
		//list.jsp?searchKey=name&searchValue=�ο�&
		
		//ǥ���� ù���������� -1 �� ��
		//currentPageSetup : �������� �� ��
		//currentPage: ���� �����ִ� ������
		//numPerBlock: �������� ����
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		//�������� 0�϶���  numPerBlock�� ���ش�.
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		//������
		if(totalPage> numPerBlock && currentPageSetup>0) {
			sb.append("<a href=\""+ listUrl + "pageNum="
					+ currentPageSetup + "\">������</a>&nbsp;");
			//<a href="list.jsp?pageNum=5"������</a>&nbsp;
							//"�� ����ȭ �ϱ� ����'\'��� =>\"
		}
		
		// 6 7 8 9 10
		page = currentPageSetup+1;
				//���� totalPage���� ũ���ʰ�
				//����������+������ǥ�� �� �� ������
		while(page<=totalPage && page<=(currentPageSetup + numPerBlock)) {
			//���� �������� �������ִ� �۾�
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
		
		//������
		if(totalPage - currentPageSetup > numPerBlock) {
			//(��ü������- >������ǥ�ð���
			sb.append("<a href=\"" + listUrl+"pageNum="+
					page + "\"> ������</a>&nbsp;");
			//<a href="list.jsp?pageNum=9">������</��>&nbsp;
		}
		return sb.toString();
		
		
	}
	
}
