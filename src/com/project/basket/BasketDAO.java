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

	//purchased�� �ʿ��� maxNum
	//util�� ���� ������ �޼���.
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
	//util�� ���� ������ �޼���.
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
	
	//pnum���� price ����Ʈ
	//util�� ���� ������ �޼���.
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
	//util�� ���� ������ �޼���.
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
	

	//��ٱ��� ���̺� ����Ʈ�� ����ؼ� ȭ�鿡 ���.
	public List<BasketDTO> getLists(){

		List<BasketDTO> lists = new ArrayList<BasketDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			
			//����� ������ ����̴�.
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

	//üũ�ڽ� ������ ������ ����
	public int deleteBasketCheck(String[] selectedPnum) {

		int result = 0;
		
		//dao ��ü�� ���� ����� �� ����.
		//�׳� Ư�� pnum �� ����� ��.
		
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

	//��� ������ ����
	public int deleteBasketAll() {

		int result = 0;
		
		//���ǹ��� �������.
		//��� �׳� �� ��������.

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

	//���� ������ ����
	public int deleteBasketOne(int pnum) {

		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			//�Ȱ��� pnum�� �޾Ƽ� �� �����͸� �����.

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

	//��ٱ��� ���� ���Ŵ� ��ٱ��� �ȿ� �ִ� �����͸� �����ؼ� �״��
	//���ų��� ���̺�� �ű�� �ȴ�.
	public int buyBasketAll(String id) {	//�α����� ���̵� ������ ���ǿ��� �޾ƿ;��ϳ�.
											//������ �ϴ� �ӽ÷� ������ �޾ƿԴ�.

		int result = 0;

		PreparedStatement pstmtSelect = null;	//��ٱ��� select�ϱ� ���� pstmt
		PreparedStatement pstmtInsert = null;	//���ų��� insert�ϱ� ���� pstmt
		PreparedStatement pstmtDelete = null;	//��ٱ��� delete�ϱ� ���� pstmt
		ResultSet rs = null;
		String sql;
		


		int maxNum=getMaxNum();

		try {


			//�켱 ��ٱ��� ���̺� ���� �����͸� ���� �޾ƿ���
			sql="select pnum,savefilename,basketcount,price from basket ";


			pstmtSelect = conn.prepareStatement(sql);
			rs=pstmtSelect.executeQuery();

			//�� ������ purchased ���̺� �°� �����Ͽ� purchased ���̺�� �̵��Ѵ�.
			sql="insert into purchased (pid, id, pnum, savefilename, purchcount, price, buyday) ";
			sql+="values (?,?,?,?,?,?,sysdate)";
			pstmtInsert = conn.prepareStatement(sql);

			while(rs.next()) {
				//��ٱ��� ���̺��� ������ �ް� ���� ���� �����ؼ� ����.
				int pnum = rs.getInt("pnum");
				String saveFileName = rs.getString("savefilename");
				int purchCount = rs.getInt("basketCount");
				int price = rs.getInt("price");


				maxNum = maxNum+1;	//���ų��� ��ȣ


				pstmtInsert.setInt(1, maxNum);
												//�������� ������ ���̵�.
				pstmtInsert.setString(2,id);	//������ �켱 �ӽ÷� ����.
				pstmtInsert.setInt(3, pnum);
				pstmtInsert.setString(4, saveFileName);
				pstmtInsert.setInt(5, purchCount);
				pstmtInsert.setInt(6, price);

				result = pstmtInsert.executeUpdate();
				
				salesUp(purchCount, pnum);


			}


			//�̵��� �Ϸ�Ǿ����� ��ٱ��ϸ� ����ϰ� ����
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


	//��ٱ��� ���� ����.
	//������ ������ pnum�� �޾Ƽ� ���ų������� ������.

	public int buyBasketOne(int pnum, String id) {		//�������� �� �ʿ��� pnum�� ���̵� �Ű������� �޾ƿ´�.

		int result = 0;

		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtDelete = null;
		ResultSet rs = null;
		String sql;
		

		int maxNum=getMaxNum();

		try {
			
			//������ ���ǹ� �߰��� �� �ٴ�.

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
			
			//���� ���ǹ� �߰��� �� �ٴ�.

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
			
			//��ٱ��Ͽ��� ���� ������ �ٲ� ������ �ǽð����� ������ �����͸� �����Ѵ�.
			//�̸� �������� �հ� ���ݵ� �����ȴ�.
			
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
			
			//������ ���� �ʴ� �޼���.
			//�̹����Խ��ǿ��� ��ٱ��� �ֱ⸦ �������� ��,
			//pnum, inum, count, id�� �޾Ƽ� �����͸� ������ ��ٱ��Ͽ� ����ִ´�.
			
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
