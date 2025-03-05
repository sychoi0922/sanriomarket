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
	
	//purchased에 필요한 maxNum
	//여러 DAO에서 써서 따로 util로 뽑아놨던 메서드.
	//지금은 그냥 모든 DAO에 넣어놨으니 util 파일은 없어도 됩니다.
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
	
	//pno으로 pname 셀렉팅
	//역시 util에 빼놨던 메서드
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
	
	//pnum으로 price 셀렉트
	//역시 util로 빼놨던 메서드.
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
	
	
	
	
	
	//inum으로 savefileimage 셀렉트
	//역시 util로 빼놨던 메서드.
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
	
	//구매했을 때 pinformation의 판매개수가 증가하는 메서드.
	//역시 util에 빼놨던 것.

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
	
	//구매 취소했을 때 pinformation의 sales 값 감소.
	
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


	
	
	//purchased 테이블을 리스트로 출력
	public List<PurchasedDTO> getLists(String id, int start, int end) {
		
		List<PurchasedDTO> lists = new ArrayList<PurchasedDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			//로그인한 유저가 구매한 내역을 봐야하므로 id를 조건절로 셀렉팅.
			//id는 session에서 받아온다.
			
			
			
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
				
				//dto에 저장.
				//테이블에 있는 값 뿐만 아니라 필요에 따라 pname도 dto에 저장했다.
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
	
	//구매 취소
	//구매내역 테이블에서 데이터를 삭제하고 취소 내역 테이블로 데이터를 이동한다.
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
	
	
	//오늘~6개월로 purchased 테이블을 리스트로 출력
	public List<PurchasedDTO> getListsDay(String dayMessage, String id, int start, int end) {

		List<PurchasedDTO> lists = new ArrayList<PurchasedDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			
			//daymessage는 선택한 것에 따라 내용이 달라진다.
			//오늘, 1주일 전, 1개월전, 3개월전, 6개월전.
			//그 값은 매개변수로 받아오면 된다.
			
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
	
	//날짜를 직접 지정해서 purchased 테이블 출력
	public List<PurchasedDTO> getListsDay(String startDateStr, String endDateStr, String id, int start, int end){
		
		List<PurchasedDTO> lists = new ArrayList<PurchasedDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		//input type Date로 가져온 날짜 값은 시간 부분이 빠져있기에 sysdate로 입력한
		//DB에서 같은 날짜의 값은 가져올 수가 없다.
		//따라서 input type date로 가져온 값에 startDate의 00:00:00 시작시간과
		//endDate의 23:59:59 의 하루의 끝 시간 데이터까지 입력해줘야만 제대로 
		//select할 수 있다.
		//아래는 시간데이터 추가하는 과정 by chatGPT
		
		//-----------------------------------
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // 시작 날짜와 끝 날짜를 LocalDateTime으로 변환
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
	
	
	//지금은 쓰지 못하는, 이미지게시판에서 바로 구매를 눌렀을 때 진행될 메서드.
	//이미지게시판에서 바로 구매를 누를 때, pnum과 inum, count(구매갯수)를 받아와
	//이를 바탕으로 purchased에 넣어야 할 데이터를 하나하나 셀렉팅해와
	//purchased 테이블로 insert 한다.
	public int buyProduct(int pnum, String saveFileName, int count, String id) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		
		//pinformation 테이블에 접속 가능한 pnum을 바탕으로 pname과 pirce를 구해오고
		//image 테이블에 접속 가능한 inum을 바탕으로 이미지 구현에 필요한
		//savefilename을 가져온다.
		
		int pid = getMaxNum()+1;
		int price = searchPrice(pnum);
		
		
		try {
			
			
			//필요한 데이터를 확보했으면 이제 insert 하면 된다.
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

        // 시작 날짜와 끝 날짜를 LocalDateTime으로 변환
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
