/**
* IndicatePrintResult  �������o�͎w�����ʃN���X
*
*	�쐬��    2003/04/23
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.indicate.indicateprint;

import java.util.*;

public class IndicatePrintResult {

		private IndicatePrintVO fmVO;
		private boolean success;
		private String desc;
		private ArrayList finded_arr;
		
		public HashMap map = null;
		public static final String KEY_HACCOD = "haccod";
		
		public IndicatePrintResult(IndicatePrintVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public IndicatePrintVO getVO(){
			return fmVO;
		}
		public boolean isSuccess(){
			return success;
		}
		public String getDescription(){
			return desc;
		}
		//HashMap����G���[�̂��鍀�ڂ�index���擾���郁�\�b�h
		public ArrayList getMap(String key){
			if(map == null || map.get(key) == null)
				return null;
			return (ArrayList)map.get(key);			
		}
		//HashMap�ɃG���[�̂��鍀�ڂ�index���Z�b�g���郁�\�b�h
		public void setMap(String key, ArrayList errIndex){
			if(map == null)
				map = new HashMap();
				
			map.put(key,errIndex);			
		}
		
		//�������ʂ̔z����擾����
		public ArrayList getFinded_arr(){
			return finded_arr;		
		}
		//�������ʂ̔z����Z�b�g����
		public void setFinded_arr(ArrayList finded){
			finded_arr = finded;		
		}
		


	
}


