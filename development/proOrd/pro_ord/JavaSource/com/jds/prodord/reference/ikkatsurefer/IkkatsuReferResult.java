/**
* IkkatsuReferResult  �ꊇ�Ɖ��� �������ʃN���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.reference.ikkatsurefer;

public class IkkatsuReferResult {


		private IkkatsuReferVO fmVO;
		private boolean success;
		private String desc;
		
		private String syrsuu = "";
		
		public IkkatsuReferResult(IkkatsuReferVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public IkkatsuReferVO getVO(){
			return fmVO;
		}
		public boolean isSuccess(){
			return success;
		}
		public String getDescription(){
			return desc;
		}

		//������
		public void setSyrSuu(String s){
			
			this.syrsuu = s;
			
		}
		public String getSyrSuu(){
			
			return this.syrsuu;
			
		}

	
}


