package com.project.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ReviewDAO {

	private Connection conn;

	public ReviewDAO(Connection conn) {

		this.conn = conn;
	}
	
//maxNum
	public int getMaxNum() { //num �� �ߺ� ���ϱ� ���� ����

		int maxNum=0; 

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql= "select nvl(max(rvnum),0) from review"; //�����Ͱ� 100������ �÷��� 1��

			pstmt = conn.prepareStatement(sql);

			rs=pstmt.executeQuery();

			if(rs.next()) {
				maxNum = rs.getInt(1); //������ �÷��� 1��  �� 1 

			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return maxNum;

		//select nvl(max(num),0) from board   : 
		// 'num' ���� �ִ밪�� ��ȯ�ϵ�, ���� 'num' ���� NULL�̶�� 0�� ��ȯ�ϵ��� �ϴ� ����


	}
	
//insert
	public int insertData(ReviewDTO dto) { //�ۿø���

		int result = 0; 

		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into review(rvnum,title,id,pname,content,rpwd,created) ";
			sql+= "values(?,?,?,?,?,?,sysdate)"; 

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getRvnum());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getPname());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getRpwd());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}


//dataCount
	public int getDataCount(String searchKey, String searchValue) { 

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			searchValue = "%" +searchValue + "%"; //�˻��� ���� �ڵ� 

			sql = "select nvl(count(*),0) from review ";
			sql+="where "+ searchKey +" like ?";    
			
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

//Lists
	public List<ReviewDTO> getLists(int start, int end,
			String searchKey, String searchValue ){  

		List<ReviewDTO> lists = new ArrayList<ReviewDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql; 

		try {  

			searchValue = "%" +searchValue +"%";  


			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select rvnum,title,id,pname, ";
			sql+= "to_char(created,'YYYY-MM-DD') created "; 
			sql+= "from review where "+ searchKey + " like ? ";
			sql+= "order by rvnum desc) data) ";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue); //�˻�
			pstmt.setInt(2, start); 		//�˻�
			pstmt.setInt(3, end); 			//�˻�

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ReviewDTO dto = new ReviewDTO();

				dto.setRvnum(rs.getInt("rvnum"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setPname(rs.getString("pname"));
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


//ReadData
	public ReviewDTO getReadData(int rvnum){  

		ReviewDTO dto = null; 

		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		String sql; 

		try { 
			sql = "select rvnum,title,id,pname,content,rpwd, ";
			sql+= "to_char(created,'YYYY-MM-DD') created from review where rvnum=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, rvnum);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				dto = new ReviewDTO();

				dto.setRvnum(rs.getInt("rvnum"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setPname(rs.getString("pname"));
				dto.setContent(rs.getString("content"));
				dto.setRpwd(rs.getString("rpwd"));
				dto.setCreated(rs.getString("created"));

			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

//update
	public int updateData(ReviewDTO dto) {

		int result=0;

		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update review set title=?, pname=?, content=?, rpwd=? ";
			sql+=" where rvnum=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,dto.getTitle());
			pstmt.setString (2,dto.getPname());
			pstmt.setString(3,dto.getContent());
			pstmt.setString(4,dto.getRpwd());
			pstmt.setInt(5, dto.getRvnum());

			result = pstmt.executeUpdate();

			pstmt.close();


		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

//delete	
	public int deleteData(int rvnum) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql; 

		try {

			sql = "delete review where rvnum=?";   
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, rvnum);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;

	}








}
