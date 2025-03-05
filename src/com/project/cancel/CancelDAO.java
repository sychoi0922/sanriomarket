package com.project.cancel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.purchased.PurchasedDTO;


//��ҳ��� ���̺��� ���ų��� ���̺�� ���� 80~90�� ��ġ�Ѵ�.
//�׷��� �뷫���� ������ ���ų��� table, purchased�� dao�� ������ ����.

public class CancelDAO {
	
	private Connection conn;
	
	public CancelDAO(Connection conn) {
		this.conn = conn;
		
	}
	
	//pno���� pname ������
	public String searchPName(int pnum) {

		String pname = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql ="select pname from pinformation where pnum = ?";
			
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);

			rs=pstmt.executeQuery();

			while(rs.next()) {

				pname = rs.getString("pname");
			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}



		return pname;


	}
	
	//pnum���� price ����Ʈ
	public int searchPrice(int pnum) {

		int price = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql ="select price from pinformation where pnum = ?";


			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);

			rs=pstmt.executeQuery();

			while(rs.next()) {

				price = rs.getInt("price");
			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}



		return price;


	}
	
	//inum���� savefileimage ����Ʈ
	public String searchImage(int inum) {
		
		String saveFileimage = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select savefilename from image where inum = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				saveFileimage = rs.getString("savefilename");
			}
			
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return saveFileimage;
	}
	

	//cancel ���̺� ����Ʈ�� ���
	public List<CancelDTO> getLists(String id, int start, int end) {
		
		List<CancelDTO> lists = new ArrayList<CancelDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from cancel where id = ? order by buyday desc) data)";
			sql+= "where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				CancelDTO dto = new CancelDTO();
				
				String pname = searchPName(rs.getInt("pnum"));
				
				dto.setPid(rs.getInt("pid"));
				dto.setId(rs.getString("id"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(pname);
				dto.setPurchCount(rs.getInt("purchcount"));
				dto.setPrice(rs.getInt("price"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setBuyDay(rs.getString("buyday"));
				
				lists.add(dto);
				
				
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return lists;
		
	}
	
	
	//��� ���� ����
	public int cancelDelete(int pid) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			
			sql="delete from cancel where pid = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return result;
		
	}
	
	
	//����~6������ cancel ���̺��� ����Ʈ�� ���
	public List<CancelDTO> getListsDay(String dayMessage, String id, int start, int end) {

		List<CancelDTO> lists = new ArrayList<CancelDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from cancel where id = ? and " + dayMessage + " order by buyday desc) data)";
			sql+= "where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();

			while(rs.next()) {

				CancelDTO dto = new CancelDTO();

				String pname = searchPName(rs.getInt("pnum"));
				
				


				dto.setPid(rs.getInt("pid"));
				dto.setId(rs.getString("id"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(pname);
				dto.setPurchCount(rs.getInt("purchcount"));
				dto.setPrice(rs.getInt("price"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setBuyDay(rs.getString("buyday"));

				lists.add(dto);


			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return lists;

	}
	
	
	//��¥�� ���� �����ؼ� cancel ���̺� ���
	public List<CancelDTO> getListsDay(String startDateStr, String endDateStr, String id, int start, int end){

		List<CancelDTO> lists = new ArrayList<CancelDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		//input type Date�� ������ ��¥ ���� �ð� �κ��� �����ֱ⿡ sysdate�� �Է���
		//DB���� ���� ��¥�� ���� ������ ���� ����.
		//���� input type date�� ������ ���� startDate�� 00:00:00 ���۽ð���
		//endDate�� 23:59:59 �� �Ϸ��� �� �ð� �����ͱ��� �Է�����߸� ����� 
		//select�� �� �ִ�.
		//�Ʒ��� �ð������� �߰��ϴ� ���� by chatGPT

		//-----------------------------------
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(startDateStr, formatter);
		LocalDate endDate = LocalDate.parse(endDateStr, formatter);

		// ���� ��¥�� �� ��¥�� LocalDateTime���� ��ȯ
		LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

		//-----------------------------

		try {
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql ="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from cancel where buyday >= ? and buyday <= ? and id = ? order by buyday desc) data)";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(startDateTime));
			pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(endDateTime));
			pstmt.setString(3, id);
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rs=pstmt.executeQuery();

			while(rs.next()) {

				CancelDTO dto = new CancelDTO();

				String pname = searchPName(rs.getInt("pnum"));


				dto.setPid(rs.getInt("pid"));
				dto.setId(rs.getString("id"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(pname);
				dto.setPurchCount(rs.getInt("purchcount"));
				dto.setPrice(rs.getInt("price"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setBuyDay(rs.getString("buyday"));

				lists.add(dto);


			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}



		return lists;
	}
	
	public int getDataCount(String id) {
		
		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from cancel where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalDataCount = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
		return totalDataCount;
	}
	
	public int getDataCount(String id, String dayMessage) {
		
		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from cancel ";
			sql+="where id = ? and " + dayMessage;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalDataCount = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
		return totalDataCount;
	}
	
	public int getDataCount(String id, String startDateStr, String endDateStr) {
		
		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String sql;
		
		//-----------------------------------
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // ���� ��¥�� �� ��¥�� LocalDateTime���� ��ȯ
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
		
        //-----------------------------
		
		try {
			
			sql = "select nvl(count(*),0) from cancel ";
			sql+="where id = ? and buyday > ? and buyday < ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(startDateTime));
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(endDateTime));
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalDataCount = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
		return totalDataCount;
	}



}

	





