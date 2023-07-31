package org.sp.shop.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sp.shop.admin.domain.Admin;

import util.DBManager;

//오직 admin 테이블에 대한 CRUD만을 담당하기 위한 객체
public class AdminDAO {
	DBManager dbManager;
	
	public AdminDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	
	//아래의 로그인 메서드를 호출한 사람에게 그 결과를 알려줘야 한다
	//어떤 식의 결과? 로그인 성공한 사람에게는 그사람을 시스템이 기억해줘야 한다
	public Admin login(Admin admin) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Admin dto=null;//로그인 후 해당 관리자1사람 정보를 담기위한 객체
		
		try {
			con=dbManager.connect();
			
			if(con==null) {
				System.out.println("접속실패");
			}else {
				//jdbc프로그래밍시 컬럼의 값은 바인드 변수로 처리가 가능하다
				//이때 바인드 변수를 jdbc로 표현할때는?를 이용한다
				//바인드 변수란 데이터 베이스 성능을 향상시키기 위한 기법
				String sql="select * from admin where id=? and pass=?";
				pstmt=con.prepareStatement(sql);//쿼리수행 객체 생성
				pstmt.setString(1, admin.getId());//문장에서 첫번째로 발견된 물음표
				pstmt.setString(2, admin.getPass());//문장에서 두번째로 발견된 물음표
				
				//쿼리실행
				rs=pstmt.executeQuery();//쿼리 수행후 표를 반환받고 그 표를 ResutlSet
				//객체로 받음
				if(rs.next()) {//커서를 한칸 이동시 true가 반환된다면 레코드가 존재한다는
					//것이므로 로그인에 성공했따고 판단된
					dto=new Admin();//비어있는dto인스턴스 생성
					//채워넣기..
					dto.setAdmin_idx(rs.getInt("admin_idx"));
					dto.setId(rs.getString("id"));
					dto.setPass(rs.getString("pass"));
					dto.setName(rs.getString("name"));
					
				}
				
				
			}
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return dto;
	}
	
}
