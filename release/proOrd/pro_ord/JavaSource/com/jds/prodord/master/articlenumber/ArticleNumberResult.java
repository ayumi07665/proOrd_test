/**
* ArticleNumberResult  �i�ԃ}�X�^�[�����e�i���X���ʃN���X
*
*	�쐬��    2003/08/25
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.articlenumber;

import java.util.*;

public class ArticleNumberResult {

		private ArticleNumberVO fmVO;
		private boolean success;
		private String desc;
		
		public ArrayList err = new ArrayList();
		final static String NOTEXIST_PRS = "�v���X���[�J�[�R�[�h";
		final static String NOTEXIST_JYT = "����v���X���[�J�[�R�[�h";
		final static String NOTEXIST_FUK = "�����ރ��[�J�[�R�[�h";
		
//==========================================================================================

		public ArticleNumberResult(ArticleNumberVO fmVO,boolean success,String desc){

			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}

		public ArticleNumberVO getVO(){
			return fmVO;
		}

		public boolean isSuccess(){
			return success;
		}

		public String getDescription(){
			return desc;
		}

		//�G���[�̂��鍀�ڂ̊i�[���ꂽArrayList���擾���郁�\�b�h
		public ArrayList getError(){
			return err;
		}

		//ArrayList�ɃG���[�̂��鍀�ڂ̃��X�g���Z�b�g���郁�\�b�h
		public void setError(ArrayList arr){
			err = arr;
		}
		
	
}

