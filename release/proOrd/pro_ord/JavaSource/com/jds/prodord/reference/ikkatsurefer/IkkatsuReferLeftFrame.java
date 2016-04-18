/**
* IkkatsuReferLeftFrame  �ꊇ�Ɖ���
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���t���[���̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
* 		2003/
* 			�E
* 
*/

package com.jds.prodord.reference.ikkatsurefer;

import java.util.*;

public class IkkatsuReferLeftFrame {

	public static final String SYRZMI = "�o�͍�";
	public static final String HACZMI = "������";
	public static final String MIHACHU = "������";

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

