/**
* IkkatsuReferLeftFrame  ˆêŠ‡Æ‰ï‰æ–Ê
*
*	ì¬“ú    2003/03/31
*	ì¬ŽÒ    i‚m‚h‚hj‰ª“c ‰Ä”ü
* ˆ—ŠT—v    ¶ƒtƒŒ[ƒ€‚Ìˆ—Œ‹‰Ê‚ðŠi”[‚·‚éƒNƒ‰ƒXB
*
*	[•ÏX—š—ð]
* 		2003/
* 			E
* 
*/

package com.jds.prodord.reference.ikkatsurefer;

import java.util.*;

public class IkkatsuReferLeftFrame {

	public static final String SYRZMI = "o—ÍÏ";
	public static final String HACZMI = "”­’Ï";
	public static final String MIHACHU = "–¢”­’";

    private ArrayList details = new ArrayList();
	
	public IkkatsuReferLeftFrame(){
	}
	
	
	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
		
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();
			  for(int k=0; k<j; k++){
			    details.add(new IkkatsuReferLeftRow());			
			  }	
		}
	}

	
	public IkkatsuReferLeftRow getFormRow_L(int i){
		IkkatsuReferLeftRow fr = (IkkatsuReferLeftRow)details.get(i);
		if(fr == null){
			fr = new IkkatsuReferLeftRow();
			details.set(i,fr);
		}
		return fr;
	}

	
	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			IkkatsuReferLeftRow fr = (IkkatsuReferLeftRow)details.get(i);
			if(fr != null)
	            details.set(i,new IkkatsuReferLeftRow());			
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

