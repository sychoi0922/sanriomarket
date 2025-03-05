package com.project.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.image.ImageDTO;
import com.project.pInformation.PinformationDTO;
import com.util.DBConn;

public class MainDAO {
	Connection conn = DBConn.getConnection();
	
	public MainDAO(Connection conn) {
		this.conn=conn;
	}
	
	
	public List<ImageDTO> getRanImageData(){	//ImageDTO9       
		 List<ImageDTO> lists = new ArrayList<ImageDTO>();
		 ImageDTO dto = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql;
		 
		 
		 try {
			
			 sql = "select * from ( ";
			 sql+= "select * from image order by DBMS_RANDOM.RANDOM ";
			 sql+= ") where rownum <= 9";
			 
			 
			 pstmt=conn.prepareStatement(sql);
			 
			 
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 dto = new ImageDTO();
				 
				 dto.setInum(rs.getInt("inum"));
				 dto.setSubject(rs.getString("subject"));
				 dto.setSaveFileName(rs.getString("SAVEFILENAME"));
				 dto.setOriginalFileName(rs.getString("ORIGINALFILENAME"));
				 
				 lists.add(dto);
			 }
			 
			 pstmt.close();
			 rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("getRanImageData");
		}
		 
		 return lists;
	}
	
	public List<PinformationDTO> getRanPinfoData(){	//ImageDTO        9       
		List<PinformationDTO> lists = new ArrayList<PinformationDTO>();
		PinformationDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select * from ( ";
			sql+= "select * from pinformation order by DBMS_RANDOM.RANDOM ";
			sql+= ") where rownum <= 12";
			
			
			pstmt= conn.prepareStatement(sql);
			
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new PinformationDTO();
				
				dto.setPname(rs.getString("pname"));
				dto.setCategory(rs.getString("CATEGORY"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setDay(rs.getString("DAY"));
				dto.setPcount(rs.getInt("pcount"));
				dto.setSales(rs.getInt("SALES"));
				dto.setInum(rs.getInt("inum"));
				dto.setSaveFileName(rs.getString("SAVEFILENAME"));
				 dto.setOriginalFileName(rs.getString("ORIGINALFILENAME"));
				 
				 
				lists.add(dto);
			}
			
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("getRanPinfoData");
		}
		
		return lists;
	}

	
	
	public List<PinformationDTO> getSearchSubjectPinfo(String pname){
		List<PinformationDTO> lists = new ArrayList<PinformationDTO>();
		PinformationDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql= "select * from pinformation where pname like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new PinformationDTO();
				
				dto.setPname(rs.getString("pname"));
				dto.setCategory(rs.getString("CATEGORY"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setDay(rs.getString("DAY"));
				dto.setPcount(rs.getInt("pcount"));
				dto.setSales(rs.getInt("SALES"));
				dto.setInum(rs.getInt("inum"));
				dto.setSaveFileName(rs.getString("SAVEFILENAME"));
				dto.setOriginalFileName(rs.getString("ORIGINALFILENAME"));
				
				
				lists.add(dto);
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			 System.out.println(e.toString());
			 System.out.println("DAO: getSearchSubjectPinfo      ");
		}
		
		
		
		
		return lists;
	}
	
	public List<ImageDTO> getSearchSubjectImage(String subject){
		
		List<ImageDTO> lists = new ArrayList<ImageDTO>();
		ImageDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		sql= "select * from Image where subject = ?";
		
		
		
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, subject);
			
			rs=pstmt.executeQuery();
			
			 while(rs.next()) {
				 dto = new ImageDTO();
				 
				 dto.setInum(rs.getInt("inum"));
				 dto.setSubject(rs.getString("subject"));
				 dto.setSaveFileName(rs.getString("SAVEFILENAME"));
				 dto.setOriginalFileName(rs.getString("ORIGINALFILENAME"));
				 
				 lists.add(dto);
			 }
			
		} catch (Exception e) {
			 System.out.println(e.toString());
			 System.out.println("DAO: getSearchSubjectImage      ");
		}
		
		return lists;
	}
	
	public int getPInfoDataCount(String searchKey, String searchValue) {
		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			searchValue="%"+ searchValue +"%";
			
			sql="select nvl(count(*), 0) from pinformation "; //전체 데이터를 구하는 쿼리
			
			sql+="where "+searchKey + " like ?" ;
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				totalDataCount=rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			 System.out.println(e.toString());
		}
		
		return totalDataCount;
	}

	public int getImageDataCount(String searchKey, String searchValue) {
		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			searchValue="%"+ searchValue +"%";
			
			sql="select nvl(count(*), 0) from image "; //전체 데이터를 구하는 쿼리
			
			sql+="where "+searchKey + " like ?" ;
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				totalDataCount=rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			 System.out.println(e.toString());
		}
		
		return totalDataCount;
	}
	
}
