package com.project.image;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.pInformation.PinformationDTO;
import com.util.SearchProd;


public class ImageDAO {
	private Connection conn = null;
	public ImageDAO(Connection conn) {
		this.conn=conn;
	}
	
	public int getMaxInum() {
		int maxInum=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		try {
			sql ="select nvl(max(inum),0) from image";
			
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				maxInum=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println();
		}
		return maxInum;
	}
	
	public int insertData(ImageDTO dto) {
		
		int result=0;
		PreparedStatement pstmt =null;
		String sql;
		try {
			sql ="insert into image (inum,subject,saveFileName, ";
			sql+="originalFileName) values (?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getInum());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getSaveFileName());
			pstmt.setString(4, dto.getOriginalFileName());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	public List<ImageDTO> getlList(int start,int end,String searchKey,String searchValue) {
															//윗줄 검색
		List<ImageDTO> lists = new ArrayList<ImageDTO>();
		
		SearchProd sep = new SearchProd(conn);
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql;
		try {
			searchValue = "%" + searchValue + "%"; //검색
			sql ="select * from ( ";
			sql+="select rownum rnum, data.* from (";
			sql+="select inum,subject,saveFileName,originalFileName ";
			sql+="from image ";
			sql+="where " + searchKey + " like ? ";//검색
			sql+="order by inum desc) data) ";
			sql+="where rnum>=? and rnum<=?";
			
//			searchValue = "%" + searchValue + "%"; //검색
//			sql ="select * from ( ";
//			sql+="select rownum rnum, data.* from (";
//			sql+="select a.inum,a.subject,a.saveFileName,b.price,b.category,b.day,b.sales ";
//			sql+="from image a join pinformation b on a.inum=b.inum ";
//			sql+="where " + searchKey + " like ? ";//검색
//			sql+="order by inum desc) data) ";
//			sql+="where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);//검색
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ImageDTO dto = new ImageDTO();
				
				int price = sep.searchPrice(rs.getInt("inum"));
				
				int pnum = sep.searchPnum(rs.getInt("inum"));
				
				String category  = sep.searchCateg(rs.getInt("inum"));
				
				dto.setInum(rs.getInt("inum"));
				dto.setSubject(rs.getString("subject"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
				
				dto.setPrice(price);
				dto.setPnum(pnum);
				dto.setCategory(category);
				
				
				lists.add(dto);
			
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	public List<ImageDTO> getlList(int start,int end, String category, String searchKey,String searchValue) {
		//윗줄 검색
		List<ImageDTO> lists = new ArrayList<ImageDTO>();

		SearchProd sep = new SearchProd(conn);

		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql;
		try {
			searchValue = "%" + searchValue + "%"; //검색
			sql ="select * from ( ";
			sql+="select rownum rnum, data.* from (";
			sql+="select inum,subject,saveFileName,originalFileName ";
			sql+="from image ";
			sql+="where category = ? and " + searchKey + " like ? ";//검색
			sql+="order by inum desc) data) ";
			sql+="where rnum>=? and rnum<=?";

//			searchValue = "%" + searchValue + "%"; //검색
//			sql ="select * from ( ";
//			sql+="select rownum rnum, data.* from (";
//			sql+="select a.inum,a.subject,a.saveFileName,b.price,b.category,b.day,b.sales ";
//			sql+="from image a join pinformation b on a.inum=b.inum ";
//			sql+="where " + searchKey + " like ? ";//검색
//			sql+="order by inum desc) data) ";
//			sql+="where rnum>=? and rnum<=?"; 

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setString(2, searchValue);//검색
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				ImageDTO dto = new ImageDTO();

				int price = sep.searchPrice(rs.getInt("inum"));

				int pnum = sep.searchPnum(rs.getInt("inum"));


				dto.setInum(rs.getInt("inum"));
				dto.setSubject(rs.getString("subject"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));

				dto.setPrice(price);
				dto.setPnum(pnum);
				dto.setCategory(category);
				
				System.out.println(dto.getCategory());
				System.out.println(dto.getPnum());


				lists.add(dto);

			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	public int getDataCount(String searchKey,String searchValue) {//검색
		
		int totalDataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		
		try {
			
			searchValue = "%" + searchValue + "%";	//검색
			sql ="select nvl(count(*),0) from image ";
			sql+="where " + searchKey + " like ?";//검색
						
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);//검색
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalDataCount = rs.getInt(1);
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return totalDataCount;
		
	}
	
	
	public int getDataCount(String category, String searchKey,String searchValue) {//검색
		
		int totalDataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		
		try {
			
			searchValue = "%" + searchValue + "%";	//검색
			sql ="select nvl(count(*),0) from image ";
			sql+="where category = ? and " + searchKey + " like ?";//검색
						
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setString(2, searchValue);//검색
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalDataCount = rs.getInt(1);
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return totalDataCount;
		
	}
	
	public ImageDTO getReadData(int inum) {
		
		ImageDTO dto = null;
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql;
		try {
			sql ="select inum,subject,saveFileName,originalFileName ";
			sql+="from image where inum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ImageDTO();
				
				dto.setInum(rs.getInt("inum"));
				dto.setSubject(rs.getString("subject"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
			
			
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	
	
	public int deleteData(int inum) {//DB
		
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		try {
			sql ="delete image where inum=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	public int searchPnum(int inum) {
		
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
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		return pnum;
	}
	

	
}



