package org.sp.shop.admin.domain;
//Topcategory 테이블의 레코드 한건을 담게될  DTO
public class TopCategory {
	private int topCategory_idx;
	private String topname;
	
	
	public int getTopCategory_idx() {
		return topCategory_idx;
	}
	public void setTopCategory_idx(int topCategory_idx) {
		this.topCategory_idx = topCategory_idx;
	}
	public String getTopname() {
		return topname;
	}
	public void setTopname(String topname) {
		this.topname = topname;
	}
	
	
}
