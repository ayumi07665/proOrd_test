/**
* PrsOrderLeftFrame  プレス  ・副資材発注画面 LeftFrame
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    左フレームの処理結果を格納するクラス。
*
*	[変更履歴]
* 		2003/
* 			・
* 
*/

package com.jds.prodord.order.prsorder;

import java.util.*;

public class PrsOrderLeftFrame {

	public static final String SYRZMI = "出力済";
	public static final String HACZMI = "発注済";
	public static final String MIHACHU = "未発注";
	
    private ArrayList details = new ArrayList();
	
	public PrsOrderLeftFrame(){
	}
	
	
	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
		
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();
			  for(int k=0; k<j; k++){
			    details.add(new PrsOrderLeftRow());			
			  }	
		}
	}

	
	public PrsOrderLeftRow getFormRow_L(int i){
		PrsOrderLeftRow fr = (PrsOrderLeftRow)details.get(i);
		if(fr == null){
			fr = new PrsOrderLeftRow();
			details.set(i,fr);
		}
		return fr;
	}

	
	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			PrsOrderLeftRow fr = (PrsOrderLeftRow)details.get(i);
			if(fr != null)
	            details.set(i,new PrsOrderLeftRow());			
		}	
	}
	

	public void clearAll(){
		clearTableField();
	}


	public void removeAllRow(){
		details.clear();
	}

		
	public ArrayList getFormRowList_L(){
		return details;
	}
	
	public void setFormRowList_L(ArrayList arr){
		details = arr;
	}	
	
		
	

}

