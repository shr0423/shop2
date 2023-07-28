package org.sp.shop.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.shop.admin.domain.TopCategory;

//Topcategory에 대한CRUD만을 수행하는 DAO객체
public class TopCategoryDAO {
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="shop";
	String password="1234";
	
	//모든 레코드 가져오기
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<TopCategory> list=new ArrayList<TopCategory>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,password);
			if(con==null) {
				System.out.println("접속실패");
			}else {
				String sql="select * from topcategory";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				//rs는 곧 닫히므로, rs의 레코드들을 한건, 한건 DTO에 옮겨놓자
				while(rs.next()) {
					TopCategory dto=new TopCategory();
					//넣을때는 setter로..
					dto.setTopCategory_idx(rs.getInt("topcategory_idx"));
					dto.setTopname(rs.getString("topname"));
					
					list.add(dto);//리스트에 채워넣기
				}
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
}
