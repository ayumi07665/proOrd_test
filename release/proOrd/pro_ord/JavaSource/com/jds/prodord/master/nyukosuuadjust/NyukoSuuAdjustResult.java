/**
* NyukoSuuAdjustResult  ���ɐ��������ʃN���X
*
*	�쐬��    2003/09/30
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.nyukosuuadjust;


public class NyukoSuuAdjustResult {

		private NyukoSuuAdjustVO fmVO;
		private boolean success;
		private String desc;
		
		public NyukoSuuAdjustResult(NyukoSuuAdjustVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		
		public NyukoSuuAdjustVO getVO(){
			return fmVO;
		}
		public boolean isSuccess(){
			return success;
		}
		public String getDescription(){
			return desc;
		}
		
}