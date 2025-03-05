package com.project.pInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.SearchProd;



public class PinformationDAO {
	
	private Connection conn = null;
	public PinformationDAO(Connection conn) {
		this.conn=conn;
	}
	
	public int getMaxPnum() {
		int maxPnum=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		try {
			sql ="select nvl(max(pnum),0) from pinformation";
			
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				maxPnum=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println();
		}
		return maxPnum;
	}
	
	public int insertData(PinformationDTO dto) {
		
		int result=0;
		PreparedStatement pstmt =null;
		String sql;
		try {
			sql ="insert into pinformation (pnum,pname,category, ";
			sql+="price,day,pcount,sales,inum, ";
			sql+="saveFileName,originalFileName) values (?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getPnum());
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getCategory());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setString(5, dto.getDay());
			pstmt.setInt(6, dto.getPcount());
			pstmt.setInt(7, dto.getSales());
			pstmt.setInt(8, dto.getInum());
			pstmt.setString(9, dto.getSaveFileName());
			pstmt.setString(10, dto.getOriginalFileName());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	public List<PinformationDTO> getlList(int start,int end) {
		
		List<PinformationDTO> lists = new ArrayList<PinformationDTO>();
		
		SearchProd sep = new SearchProd(conn);
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql;
		try {
			sql ="select * from ( ";
			sql+="select rownum rnum, data.* from ( ";
			sql+="select pnum,pname,category,price, ";
			sql+="to_char(day,'YYYY-MM-DD') day,pcount,sales,inum, ";
			sql+="saveFileName,originalFileName ";
			sql+="from pinformation ";
			sql+="order by DBMS_RANDOM.RANDOM desc) data) ";
			sql+="where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PinformationDTO dto = new PinformationDTO();
				
				
				sep.searchImg(rs.getInt("inum"));
				
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(rs.getString("pname"));
				dto.setCategory(rs.getString("category"));
				dto.setPrice(rs.getInt("price"));
				dto.setDay(rs.getString("day"));
				dto.setPcount(rs.getInt("pcount"));
				dto.setSales(rs.getInt("sales"));
				dto.setInum(rs.getInt("inum"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
				
				
				
				lists.add(dto);
				
				
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	public int getDataCount() {
		
		int totalDataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		
		try {
	
			sql ="select nvl(count(*),0) from pinformation ";
						
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
	
	public PinformationDTO getReadData(int pnum) {

		PinformationDTO dto = null;
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql;
		SearchProd sep = new SearchProd(conn);
		try {
			sql ="select pnum,pname,category,price,day,pcount,sales,inum, ";
			sql+="saveFileName,originalFileName ";
			sql+="from pinformation where pnum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {
				
				dto = new PinformationDTO();
				sep.searchImg(rs.getInt("inum"));
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(rs.getString("pname"));
				dto.setCategory(rs.getString("category"));
				dto.setPrice(rs.getInt("price"));
				dto.setDay(rs.getString("day"));
				dto.setPcount(rs.getInt("pcount"));
				dto.setSales(rs.getInt("sales"));
				dto.setInum(rs.getInt("inum"));
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
	
	public int deleteData(int pnum) {//DB
		
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		try {
			sql ="delete pinformation where pnum=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	

}
