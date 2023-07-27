package org.sp.shop.admin.view;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	
	public Page() {
		//모든 페이지는 너비,높이가 공통적임
		setPreferredSize(new Dimension(1100,570));
		setVisible(false);
		
	}
}
