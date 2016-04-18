package com.jds.prodord.menu;

import java.util.Collection;
import java.util.TreeMap;

public class MenuControl extends TreeMap{
	
	protected TreeMap menuMap1;
	protected TreeMap menuMap2;
	
	public MenuControl() {
	}
	
	public MenuControl(String hyoKbn){
		
		if(hyoKbn.equals("0")||hyoKbn.equals("2")||hyoKbn.equals("3")||hyoKbn.equals("4")){
			// ��ʍ��P�`�R
			this.put("1001",new Menu("1","1001","��Đ��Ɖ�w��","teiansuuRefer"));
			this.put("1002",new Menu("1","1002","�}�j���A�������w��","manualOrder")); 
			this.put("1003",new Menu("1","1003","�������o��","indicatePrint")); 
			// ��ʉE�S�`�U
			this.put("2001",new Menu("2","2001","���������Ɖ�","orderHistoryTop"));
			this.put("2002",new Menu("2","2002","�}�X�^�[�����e�i���X","mastermenu")); 
			this.put("2004",new Menu("2","2004","�I��","Logoff")); 
		}else if(hyoKbn.equals("1")){
			// ��ʍ�
			this.put("1001",new Menu("1","1001","���������Ɖ�","orderHistoryTop"));
			// ��ʉE 
			this.put("2003",new Menu("2","2001","�I��","Logoff")); 
		}
		if(hyoKbn.equals("2")||hyoKbn.equals("4")){
		// ��ʍ��S
		this.put("1004",new Menu("1","1004","�}�j���A�������w���ꊇ�\�t","manualOrderPaste"));
		// ��ʉE�R
		this.put("2003",new Menu("2","2003","�����ލ\���}�X�^�[�ꊇ�\�t","fzkHinKsiPaste"));
		}			
		
		menuMap1 = new TreeMap(this.subMap("1001","2000"));
		menuMap2 = new TreeMap(this.subMap("2001","3000"));
	}
	
	public Collection getMenu1(){
		return menuMap1.values();
	} 

	public Collection getMenu2(){
		return menuMap2.values();
	} 
}

