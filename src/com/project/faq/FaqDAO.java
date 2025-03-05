package com.project.faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FaqDAO {

	private Connection conn ; 

	public FaqDAO(Connection conn) {

		this.conn = conn;
	}


	//dataCount  
	public int getDataCount() { 																	//전체데이터 갯수- 삭제한것 제외 //(String searchkey, String searchValue) (검색종류, 검색어)

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {


			sql = "select nvl(count(*),0) from FAQ ";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				totalDataCount = rs.getInt(1);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		

		return totalDataCount;

	}
	
	

//Lists
	public List<FaqDTO> getLists( String category){  

		List<FaqDTO> lists = new ArrayList<FaqDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql; 

		try {  
			
			if(category==null){
				
				sql = "select category, question, answer from FAQ ";
				sql+= " order by fnum desc ";

				pstmt = conn.prepareStatement(sql);

				
			}else{
				
				sql = "select category, question, answer from FAQ";
				sql+=" where category like ? ";
				sql+= "order by fnum desc ";
				
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, category); 
			
			}

			rs = pstmt.executeQuery();

			while(rs.next()) {

				FaqDTO dto = new FaqDTO();

				dto.setCategory(rs.getString("category"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));

				lists.add(dto);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}			




	

	
	
	
	
	
	
	



}
