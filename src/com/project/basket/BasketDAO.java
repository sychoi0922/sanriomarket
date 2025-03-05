package com.project.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BasketDAO {

	private Connection conn;

	public BasketDAO(Connection conn) {
		this.conn = conn;
	}

	//purchased에 필요한 maxNum
	//util로 따로 빼놨던 메서드.
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
	//util로 따로 빼놨던 메서드.
	public String searchPName(int pnum) {

		String pname = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql ="select pname from pinformation where pnum = ?";
			
			pstmt = conn.prepareStatement(sql);
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
	//util로 따로 빼놨던 메서드.
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
	//util로 따로 빼놨던 메서드.
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
	

	//장바구니 테이블 리스트로 출력해서 화면에 띄움.
	public List<BasketDTO> getLists(){

		List<BasketDTO> lists = new ArrayList<BasketDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			
			//평범한 데이터 출력이다.
			sql = "select pnum,savefilename,basketcount,price,ship from basket";

			pstmt=conn.prepareStatement(sql);

			rs=pstmt.executeQuery();

			while(rs.next()) {

				BasketDTO dto = new BasketDTO();
				
				String pname = searchPName(rs.getInt("pnum"));
				
				
				dto.setPname(pname);
				dto.setPnum(rs.getInt("pnum"));
				dto.setSaveFileName(rs.getString("savefilename"));
				dto.setBasketCount(rs.getInt("basketcount"));
				dto.setPrice(rs.getInt("price"));
				dto.setShip(rs.getString("ship"));
				

				lists.add(dto);

			}

			pstmt.close();
			rs.close();



		} catch (Exception e) {
			// TODO: handle exception
		}

		return lists;
	}

	//체크박스 선택한 데이터 삭제
	public int deleteBasketCheck(String[] selectedPnum) {

		int result = 0;
		
		//dao 자체는 별로 어려울 것 없다.
		//그냥 특정 pnum 값 지우는 것.
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql="delete from basket where pnum=?";

			for(String pnum : selectedPnum) {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(pnum));
				result = pstmt.executeUpdate();
			}

			pstmt.close();
			rs.close();



		} catch (Exception e) {
			// TODO: handle exception
		}


		return result;


	}

	//모든 데이터 삭제
	public int deleteBasketAll() {

		int result = 0;
		
		//조건문이 사라졌다.
		//고로 그냥 싹 지워진다.

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql="delete from basket";

			pstmt=conn.prepareStatement(sql);
			result = pstmt.executeUpdate();

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	//낱개 데이터 삭제
	public int deleteBasketOne(int pnum) {

		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			//똑같이 pnum을 받아서 그 데이터만 지운다.

			sql="delete from basket where pnum = ?";

			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			result = pstmt.executeUpdate();

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}



		return result;
	}

	//장바구니 전부 구매는 장바구니 안에 있는 데이터를 가공해서 그대로
	//구매내역 테이블로 옮기면 된다.
	public int buyBasketAll(String id) {	//로그인한 아이디 정보는 세션에서 받아와야하나.
											//지금은 일단 임시로 지정해 받아왔다.

		int result = 0;

		PreparedStatement pstmtSelect = null;	//장바구니 select하기 위한 pstmt
		PreparedStatement pstmtInsert = null;	//구매내역 insert하기 위한 pstmt
		PreparedStatement pstmtDelete = null;	//장바구니 delete하기 위한 pstmt
		ResultSet rs = null;
		String sql;
		


		int maxNum=getMaxNum();

		try {


			//우선 장바구니 테이블 안의 데이터를 전부 받아오고
			sql="select pnum,savefilename,basketcount,price from basket ";


			pstmtSelect = conn.prepareStatement(sql);
			rs=pstmtSelect.executeQuery();

			//그 값들을 purchased 테이블에 맞게 가공하여 purchased 테이블로 이동한다.
			sql="insert into purchased (pid, id, pnum, savefilename, purchcount, price, buyday) ";
			sql+="values (?,?,?,?,?,?,sysdate)";
			pstmtInsert = conn.prepareStatement(sql);

			while(rs.next()) {
				//장바구니 테이블에서 데이터 받고 각각 변수 지정해서 저장.
				int pnum = rs.getInt("pnum");
				String saveFileName = rs.getString("savefilename");
				int purchCount = rs.getInt("basketCount");
				int price = rs.getInt("price");


				maxNum = maxNum+1;	//구매내역 번호


				pstmtInsert.setInt(1, maxNum);
												//세션으로 가져올 아이디.
				pstmtInsert.setString(2,id);	//지금은 우선 임시로 쓴다.
				pstmtInsert.setInt(3, pnum);
				pstmtInsert.setString(4, saveFileName);
				pstmtInsert.setInt(5, purchCount);
				pstmtInsert.setInt(6, price);

				result = pstmtInsert.executeUpdate();
				
				salesUp(purchCount, pnum);


			}


			//이동이 완료되었으면 장바구니를 깔끔하게 리셋
			sql="delete from basket";
			pstmtDelete = conn.prepareStatement(sql);

			pstmtDelete.executeUpdate();


			pstmtSelect.close();
			pstmtInsert.close();
			pstmtDelete.close();
			rs.close();



		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}


	//장바구니 낱개 구매.
	//선택한 물건의 pnum만 받아서 구매내역으로 보낸다.

	public int buyBasketOne(int pnum, String id) {		//셀렉팅할 때 필요한 pnum과 아이디를 매개변수로 받아온다.

		int result = 0;

		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtDelete = null;
		ResultSet rs = null;
		String sql;
		

		int maxNum=getMaxNum();

		try {
			
			//위에서 조건문 추가된 게 다다.

			sql="select pnum,savefilename,basketcount,price from basket where pnum = ?";
			pstmtSelect = conn.prepareStatement(sql);
			pstmtSelect.setInt(1, pnum);
			rs=pstmtSelect.executeQuery();

			sql="insert into purchased (pid, id, pnum, savefilename, purchcount, price, buyday) ";
			sql+="values (?,?,?,?,?,?,sysdate)";
			pstmtInsert = conn.prepareStatement(sql);

			sql="delete from basket where pnum = ?";
			pstmtDelete = conn.prepareStatement(sql);

			while(rs.next()) {

				pnum = rs.getInt("pnum");
				String saveFileName = rs.getString("savefilename");
				int purchCount = rs.getInt("basketCount");
				int price = rs.getInt("price");

				maxNum = maxNum+1;

				pstmtInsert.setInt(1, maxNum);
				pstmtInsert.setString(2, id);
				pstmtInsert.setInt(3, pnum);
				pstmtInsert.setString(4, saveFileName);
				pstmtInsert.setInt(5, purchCount);
				pstmtInsert.setInt(6, price);

				result = pstmtInsert.executeUpdate();
				

				pstmtDelete.setInt(1, pnum);

				pstmtDelete.executeUpdate();
				
				salesUp(purchCount, pnum);

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

	public int buyBasketCheck(String[] selectedPnum,String id) {

		int result = 0;

		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtDelete = null;
		ResultSet rs = null;
		String sql;

		List<BasketDTO> lists = new ArrayList<BasketDTO>();

		int maxNum=getMaxNum();

		try {
			
			//역시 조건문 추가된 게 다다.

			sql="select pnum,savefilename,basketcount,price from basket ";
			sql+="where pnum = ?";

			pstmtSelect = conn.prepareStatement(sql);

			sql="insert into purchased (pid, id, pnum, savefilename, purchcount, price, buyday) ";
			sql+="values (?,?,?,?,?,?,sysdate)";

			pstmtInsert = conn.prepareStatement(sql);

			sql="delete from basket where pnum = ?";
			pstmtDelete = conn.prepareStatement(sql);

			for(String pnumStr : selectedPnum) {


				pstmtSelect.setInt(1, Integer.parseInt(pnumStr));
				rs=pstmtSelect.executeQuery();



				while(rs.next()) {

					int pnum = rs.getInt("pnum");
					String saveFileName = rs.getString("savefilename");
					int purchCount = rs.getInt("basketCount");
					int price = rs.getInt("price");

					maxNum = maxNum+1;

					pstmtInsert.setInt(1, maxNum);
					pstmtInsert.setString(2, id);	
					pstmtInsert.setInt(3, pnum);
					pstmtInsert.setString(4, saveFileName);
					pstmtInsert.setInt(5, purchCount);
					pstmtInsert.setInt(6, price);

					result = pstmtInsert.executeUpdate();

					pstmtDelete.setInt(1, pnum);

					pstmtDelete.executeUpdate();
					
					salesUp(purchCount, pnum);

				}

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
	
	public int countChange(int count, int pnum) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			//장바구니에서 구매 개수를 바꿀 때마다 실시간으로 수정된 데이터를 저장한다.
			//이를 바탕으로 합계 가격도 변동된다.
			
			sql = "update basket set basketcount = ? where pnum = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, pnum);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
	}
	
	public int moveBasket(int pnum, String saveFileName, int count, String id) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		int price = searchPrice(pnum);
		
		
		try {
			
			//아직은 쓰지 않는 메서드.
			//이미지게시판에서 장바구니 넣기를 실행했을 때,
			//pnum, inum, count, id를 받아서 데이터를 가공해 장바구니에 집어넣는다.
			
			sql ="insert into basket (pnum, basketcount, price, savefilename) ";
			sql+="values (?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, pnum);
			pstmt.setInt(2, count);
			pstmt.setInt(3, price);
			pstmt.setString(4, saveFileName);
			
			System.out.println(pnum);
			System.out.println(count);
			System.out.println(price);
			System.out.println(saveFileName);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
		
		
	}

}
