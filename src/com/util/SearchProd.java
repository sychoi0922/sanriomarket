package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchProd {//pinformation의 DTO를 사용하기 위한 클래스
	
	private Connection conn;
	
	public SearchProd(Connection conn) {
		this.conn=conn;
	}
	
	public int searchPrice(int inum) {//pinformation의 price DTO 생성
									  
		int price = 0;
		
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql ="select price from pinformation where inum = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				price=rs.getInt("price");
				
			}
			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return price;
	}
	
	public String searchImg(int inum) {//image의 saveFileName DTO 생성 
									   
		String imageFileName = null;
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql ="select saveFileName AS imageFileName from image where inum=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				imageFileName = rs.getString("imageFileName");
			}
			
			
			rs.close();
			pstmt.close();
			
			//System.out.println(imageFileName);
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return imageFileName;
	}
	
	
	public int searchPnum(int inum) {//pinformation의 pnum  DTO 생성
		
		int pnum = 0;
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql="select pnum from pinformation where inum=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				pnum=rs.getInt("pnum");
				
			}
			
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pnum;
	}
	public String searchCateg(int inum) {//pinformation의 category DTO 생성
		  
		String category = null;
		
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql ="select category from pinformation where inum = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				category=rs.getString("category");
				
			}
			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return category;
	}
	
	
	
}
