/**
* FzkHinKsiResult  �t���i�\���}�X�^�[�e�[�u�������e�i���X���ʃN���X
*
*	�쐬��    2004/02/13
*	�쐬��    �i�m�h�h�j�X
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.fzkhinksi;

import java.util.*;

public class FzkHinKsiResult {

		private FzkHinKsiVO fmVO;
		private boolean success;
		private String desc;
		
		public HashMap map = null;
		final static String KEY_FUKSZICOD = "FUKSZICOD";
		final static String KEY_SIRSKCOD = "SIRSKCOD";
			
		public FzkHinKsiResult(FzkHinKsiVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public FzkHinKsiVO getVO(){
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
		

	
}

