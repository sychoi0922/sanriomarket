package com.project.notice; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {  //기존에 입력한 noticedata.txt로 list 와 article 만들 것임 

	private Connection conn;

	public NoticeDAO(Connection conn) {

		this.conn = conn;
	}

//getDataCount		
	public int getDataCount(String searchKey, String searchValue) {  	

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			searchValue = "%" +searchValue + "%"; //검색어 위한 코딩 

			sql = "select nvl(count(*),0) from notice ";
			sql+="where "+ searchKey +" like?";  
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchValue);
			
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

	
//getlists
	public List<NoticeDTO> getLists( int start, int end,
			String searchKey, String searchValue ){ 

		List<NoticeDTO> lists = new ArrayList<NoticeDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql; 

		try {  
			searchValue = "%" +searchValue +"%";
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select nnum,title,writer, ";
			sql+= "to_char(created,'YYYY-MM-DD') created "; 
			sql+= "from notice where "+ searchKey + " like ? " ;
			sql+= "order by nNum desc) data) ";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue); //검색
			pstmt.setInt(2, start); 		//검색
			pstmt.setInt(3, end); 			//검색

			rs = pstmt.executeQuery();

			while(rs.next()) {

				NoticeDTO dto = new NoticeDTO();


				dto.setNnum(rs.getInt("nnum"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setCreated(rs.getString("created"));

				lists.add(dto);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}


//getReadData	
	public NoticeDTO getReadData(int nnum){  //이전글,다음글 추가  

		NoticeDTO dto = null;  

		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		String sql; 

		try { 
			
			sql = "SELECT nnum, title, writer, content, TO_CHAR(created, 'YYYY-MM-DD') AS created, ";
            sql += "preNum, preTitle, nextNum, nextTitle ";
            sql += "FROM (SELECT nnum, title, writer, content, created, ";
            sql += "LAG(nnum, 1) OVER (ORDER BY nnum) AS preNum, ";
            sql += "LAG(title, 1) OVER (ORDER BY nnum) AS preTitle, ";
            sql += "LEAD(nnum, 1) OVER (ORDER BY nnum) AS nextNum, ";
            sql += "LEAD(title, 1) OVER (ORDER BY nnum) AS nextTitle ";
            sql += "FROM notice) ";
            sql += "WHERE nnum = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, nnum);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				dto = new NoticeDTO();

				dto.setNnum(rs.getInt("nnum"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setPreNum(rs.getInt("preNum"));
                dto.setPreTitle(rs.getString("preTitle"));
                dto.setNextNum(rs.getInt("nextNum"));
                dto.setNextTitle(rs.getString("nextTitle"));
				
				
				
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}






}	
