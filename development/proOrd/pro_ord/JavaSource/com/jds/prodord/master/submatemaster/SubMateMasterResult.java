/**
* SubMateMasterResult  �����ރ}�X�^�[�����e�i���X  ���ʃN���X
*
*	�쐬��    2003/06/24
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.submatemaster;

import java.util.*;

public class SubMateMasterResult {

		private SubMateMasterVO fmVO;
		private boolean success;
		private String desc;
		
		public HashMap map = null;
		final static String KEY_KAISKBCOD = "kai";
		
//==========================================================================================

		public SubMateMasterResult(SubMateMasterVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public SubMateMasterVO getVO(){
			
			return fmVO;
		}
		public SubMateMasterVO getOutSubMateMaster(){
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


