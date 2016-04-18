package com.jds.prodord.menu.mastermenu;

import java.util.TreeMap;

import com.jds.prodord.menu.Menu;
import com.jds.prodord.menu.MenuControl;

public class MasterMenuControl extends MenuControl{
		
	public MasterMenuControl(){
		// 画面左１～４
		this.put("1001",new Menu("1","1001","在庫日数マスターメンテナンス","StockDays"));
		this.put("1002",new Menu("1","1002","形態ランク別条件マスターメンテナンス","ketRak"));
		this.put("1003",new Menu("1","1003","副資材マスターメンテナンス","SubMateMaster")); 
		this.put("1004",new Menu("1","1004","付属品構成マスターメンテナンス","fzkHinKsi"));
		this.put("1005",new Menu("1","1005","形態別構成資材マスターメンテナンス","ketSziTbl"));
		
		// 画面右５～８
		this.put("2001",new Menu("2","2001","副資材在庫メンテナンス","SubMateStock")); 
		this.put("2002",new Menu("2","2002","品番マスターメンテナンス","ArticleNumber")); 
		this.put("2003",new Menu("2","2003","仕入発注先マスター照会","PurchaseOrder"));
		this.put("2004",new Menu("2","2004","入庫数調整","nyukoSuuAdjust"));
	
		menuMap1 = new TreeMap(this.subMap("1001","2000"));
		menuMap2 = new TreeMap(this.subMap("2001","3000"));
	}
}

