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
			// 画面左１～３
			this.put("1001",new Menu("1","1001","提案数照会指示","teiansuuRefer"));
			this.put("1002",new Menu("1","1002","マニュアル発注指示","manualOrder")); 
			this.put("1003",new Menu("1","1003","発注書出力","indicatePrint")); 
			// 画面右４～６
			this.put("2001",new Menu("2","2001","発注履歴照会","orderHistoryTop"));
			this.put("2002",new Menu("2","2002","マスターメンテナンス","mastermenu")); 
			this.put("2004",new Menu("2","2004","終了","Logoff")); 
		}else if(hyoKbn.equals("1")){
			// 画面左
			this.put("1001",new Menu("1","1001","発注履歴照会","orderHistoryTop"));
			// 画面右 
			this.put("2003",new Menu("2","2001","終了","Logoff")); 
		}
		if(hyoKbn.equals("2")||hyoKbn.equals("4")){
		// 画面左４
		this.put("1004",new Menu("1","1004","マニュアル発注指示一括貼付","manualOrderPaste"));
		// 画面右３
		this.put("2003",new Menu("2","2003","副資材構成マスター一括貼付","fzkHinKsiPaste"));
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

