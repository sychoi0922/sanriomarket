package com.project.purchased;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PurchasedDAO {
	
	private Connection conn;
	
	public PurchasedDAO(Connection conn) {
		this.conn = conn;
	}
	
	//purchased�� �ʿ��� maxNum
	//���� DAO���� �Ἥ ���� util�� �̾Ƴ��� �޼���.
	//������ �׳� ��� DAO�� �־������ util ������ ��� �˴ϴ�.
	public int getMaxNum() {

		int maxNum = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(max(pid),0) from purchased";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				maxNum = rs.getInt(1);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return maxNum;

	}
	
	//pno���� pname ������
	//���� util�� ������ �޼���
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
	//���� util�� ������ �޼���.
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
	//���� util�� ������ �޼���.
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
	
	//�������� �� pinformation�� �ǸŰ����� �����ϴ� �޼���.
	//���� util�� ������ ��.

	public int salesUp(int count,int pnum) {

		int result = 0;

		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtUpdate = null;
		ResultSet rs = null;
		String sql;

		try {

			sql="select sales from pinformation where pnum = ?";
			pstmtSelect = conn.prepareStatement(sql);
			pstmtSelect.setInt(1, pnum);
			rs=pstmtSelect.executeQuery();

			sql="update pinformation set sales = ? where pnum = ?";
			pstmtUpdate = conn.prepareStatement(sql);

			while(rs.next()) {

				int sales = rs.getInt("sales");

				sales = sales + count;

				pstmtUpdate.setInt(1, sales);
				pstmtUpdate.setInt(2, pnum);

				result=pstmtUpdate.executeUpdate();

			}

			pstmtSelect.close();
			pstmtUpdate.close();
			rs.close();



		} catch (Exception e) {
			// TODO: handle exception
		}




		return result;
	}
	
	//���� ������� �� pinformation�� sales �� ����.
	
	public int salesDown(int count,int pnum) {

		int result = 0;

		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtUpdate = null;
		ResultSet rs = null;
		String sql;

		try {

			sql="select sales from pinformation where pnum = ?";
			pstmtSelect = conn.prepareStatement(sql);
			pstmtSelect.setInt(1, pnum);
			rs=pstmtSelect.executeQuery();

			sql="update pinformation set sales = ? where pnum = ?";
			pstmtUpdate = conn.prepareStatement(sql);

			while(rs.next()) {

				int sales = rs.getInt("sales");

				sales = sales - count;

				pstmtUpdate.setInt(1, sales);
				pstmtUpdate.setInt(2, pnum);

				result=pstmtUpdate.executeUpdate();

			}

			pstmtSelect.close();
			pstmtUpdate.close();
			rs.close();



		} catch (Exception e) {
			// TODO: handle exception
		}




		return result;
	}


	
	
	//purchased ���̺��� ����Ʈ�� ���
	public List<PurchasedDTO> getLists(String id, int start, int end) {
		
		List<PurchasedDTO> lists = new ArrayList<PurchasedDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			//�α����� ������ ������ ������ �����ϹǷ� id�� �������� ������.
			//id�� session���� �޾ƿ´�.
			
			
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from purchased where id = ? order by buyday desc) data)";
			sql+= "where rnum>=? and rnum<=?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				PurchasedDTO dto = new PurchasedDTO();
				
				String pname = searchPName(rs.getInt("pnum"));
				
				//dto�� ����.
				//���̺� �ִ� �� �Ӹ� �ƴ϶� �ʿ信 ���� pname�� dto�� �����ߴ�.
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
	
	//���� ���
	//���ų��� ���̺��� �����͸� �����ϰ� ��� ���� ���̺�� �����͸� �̵��Ѵ�.
	public int cancled(int pid) {
		
		int result = 0;
		
		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtDelete = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from purchased where pid = ?";
			
			pstmtSelect = conn.prepareStatement(sql);
			pstmtSelect.setInt(1, pid);
			rs=pstmtSelect.executeQuery();
			
			sql="insert into cancel (pid, id, pnum, savefilename,purchcount,price,buyday) ";
			sql+="values (?,?,?,?,?,?,?)";
			pstmtInsert = conn.prepareStatement(sql);
			
			sql="delete from purchased where pid = ?";
			
			pstmtDelete=conn.prepareStatement(sql);
			pstmtDelete.setInt(1, pid);
			
			while(rs.next()) {
				
				pid = rs.getInt("pid");
				String id = rs.getString("id");
				int pnum = rs.getInt("pnum");
				String saveFileName = rs.getString("savefilename");
				int purchCount = rs.getInt("purchcount");
				int price = rs.getInt("price");
				Timestamp buyDay = rs.getTimestamp("buyday");
				
				pstmtInsert.setInt(1, pid);
				pstmtInsert.setString(2, id);
				pstmtInsert.setInt(3, pnum);
				pstmtInsert.setString(4, saveFileName);
				pstmtInsert.setInt(5, purchCount);
				pstmtInsert.setInt(6, price);
				pstmtInsert.setTimestamp(7, buyDay);
				
				result = pstmtInsert.executeUpdate();
				
				salesDown(purchCount, pnum);
				
				pstmtDelete.setInt(1, pid);
				pstmtDelete.executeUpdate();
				
			}
			
			pstmtSelect.close();
			pstmtInsert.close();
			pstmtDelete.close();
			rs.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return result;
		
	}
	
	
	//����~6������ purchased ���̺��� ����Ʈ�� ���
	public List<PurchasedDTO> getListsDay(String dayMessage, String id, int start, int end) {

		List<PurchasedDTO> lists = new ArrayList<PurchasedDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			
			//daymessage�� ������ �Ϳ� ���� ������ �޶�����.
			//����, 1���� ��, 1������, 3������, 6������.
			//�� ���� �Ű������� �޾ƿ��� �ȴ�.
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from purchased where id = ? and " + dayMessage + " order by buyday desc) data)";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();

			while(rs.next()) {

				PurchasedDTO dto = new PurchasedDTO();

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
	
	//��¥�� ���� �����ؼ� purchased ���̺� ���
	public List<PurchasedDTO> getListsDay(String startDateStr, String endDateStr, String id, int start, int end){
		
		List<PurchasedDTO> lists = new ArrayList<PurchasedDTO>();
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
//		
//		LocalDate startDate = LocalDate.parse(startDateStr);
//		LocalDate endDate = LocalDate.parse(endDateStr);
//		
//		LocalDate startDateTime = startDate.minusDays(1);
//		LocalDate endDateTime = endDate.plusDays(1);
		
		
		try {
			
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+="select pid,id,pnum,purchcount,price,savefilename,buyday ";
			sql+="from purchased where buyday > ? and buyday < ? and id = ? order by buyday desc) data)";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(startDateTime));
			pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(endDateTime));
			pstmt.setString(3, id);
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rs=pstmt.executeQuery();
			
			

			while(rs.next()) {

				PurchasedDTO dto = new PurchasedDTO();

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
	
	
	//������ ���� ���ϴ�, �̹����Խ��ǿ��� �ٷ� ���Ÿ� ������ �� ����� �޼���.
	//�̹����Խ��ǿ��� �ٷ� ���Ÿ� ���� ��, pnum�� inum, count(���Ű���)�� �޾ƿ�
	//�̸� �������� purchased�� �־�� �� �����͸� �ϳ��ϳ� �������ؿ�
	//purchased ���̺�� insert �Ѵ�.
	public int buyProduct(int pnum, String saveFileName, int count, String id) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		
		//pinformation ���̺� ���� ������ pnum�� �������� pname�� pirce�� ���ؿ���
		//image ���̺� ���� ������ inum�� �������� �̹��� ������ �ʿ���
		//savefilename�� �����´�.
		
		int pid = getMaxNum()+1;
		int price = searchPrice(pnum);
		
		
		try {
			
			
			//�ʿ��� �����͸� Ȯ�������� ���� insert �ϸ� �ȴ�.
			sql ="insert into purchased (pid, id, pnum, savefilename, purchcount, price, buyday) ";
			sql+="values (?,?,?,?,?,?,sysdate)";
			
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setString(2, id);
			pstmt.setInt(3, pnum);
			pstmt.setString(4, saveFileName);
			pstmt.setInt(5, count);
			pstmt.setInt(6, price);
			
			result = pstmt.executeUpdate();
			
			salesUp(count, pnum);
			
			pstmt.close();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
		
	}
	
	
	public int getDataCount(String id) {
		
		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from purchased where id = ?";
			
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
			
			sql = "select nvl(count(*),0) from purchased ";
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
			
			sql = "select nvl(count(*),0) from purchased ";
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
