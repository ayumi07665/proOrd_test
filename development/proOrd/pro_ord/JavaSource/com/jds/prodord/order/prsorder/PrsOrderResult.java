/**
* PrsOrderResult  �v���X�E�����ޔ������ �������ʃN���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/


package com.jds.prodord.order.prsorder;


public class PrsOrderResult {


		private PrsOrderVO fmVO;
		private boolean success;
		private String desc;
		
		private String syrSuu = "";
		
		public PrsOrderResult(PrsOrderVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}


		public PrsOrderVO getVO(){
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
			
			this.syrSuu = s;
			
		}
		public String getSyrSuu(){
			
			return this.syrSuu;
			
		}

	
}


