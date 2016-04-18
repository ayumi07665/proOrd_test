package com.jds.prodord.menu.mastermenu;

import java.util.TreeMap;

import com.jds.prodord.menu.Menu;
import com.jds.prodord.menu.MenuControl;

public class MasterMenuControl extends MenuControl{
		
	public MasterMenuControl(){
		// ��ʍ��P�`�S
		this.put("1001",new Menu("1","1001","�݌ɓ����}�X�^�[�����e�i���X","StockDays"));
		this.put("1002",new Menu("1","1002","�`�ԃ����N�ʏ����}�X�^�[�����e�i���X","ketRak"));
		this.put("1003",new Menu("1","1003","�����ރ}�X�^�[�����e�i���X","SubMateMaster")); 
		this.put("1004",new Menu("1","1004","�t���i�\���}�X�^�[�����e�i���X","fzkHinKsi"));
		this.put("1005",new Menu("1","1005","�`�ԕʍ\�����ރ}�X�^�[�����e�i���X","ketSziTbl"));
		
		// ��ʉE�T�`�W
		this.put("2001",new Menu("2","2001","�����ލ݌Ƀ����e�i���X","SubMateStock")); 
		this.put("2002",new Menu("2","2002","�i�ԃ}�X�^�[�����e�i���X","ArticleNumber")); 
		this.put("2003",new Menu("2","2003","�d��������}�X�^�[�Ɖ�","PurchaseOrder"));
		this.put("2004",new Menu("2","2004","���ɐ�����","nyukoSuuAdjust"));
	
		menuMap1 = new TreeMap(this.subMap("1001","2000"));
		menuMap2 = new TreeMap(this.subMap("2001","3000"));
	}
}

