/**
* KetRakAction  �`�ԃ����N�ʃ}�X�^�[�����e�i���X�A�N�V�����N���X
*
*	�쐬��    2003/05/02
*	�쐬��    �i�m�h�h�j���� ���G
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/05/15�i�m�h�h�j�g�c �h�q
* 			�E��Ў��ʃR�[�h�A�`�ԃ����N �h���b�v�_�E���Ή��B
* 		 2003/05/22�i�m�h�h�j���c �Ĕ�
* 			�E�X�V���̔r������Ή��B
* 		 2003/06/02�i�m�h�h�j�g�c �h�q
* 			�E��폜��̕����w��B��o�^��̓��͓��e���f�B��N���A��{�^���ǉ��B
* 		 2003/08/22�i�m�h�h�j���c �Ĕ�
* 			�E�X�V���̈ꊇ�ύX�@�\�ǉ��B
*/

package com.jds.prodord.master.ketrak;
import com.jds.prodord.common.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class KetRakAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  KetRakDelegate bzDelegate = new KetRakDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		KetRakForm fmForm = (KetRakForm)form;
		KetRakVO fmVO = new KetRakVO();
		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��
		

		//�T�[�r�X���Ԃ̃`�F�b�N
		if(errors == null)
			errors = new ActionErrors();
		int check_result = CheckCommon.checkBatchDate(); //�T�[�r�X���Ԃ̃`�F�b�N
		switch(check_result){
			//�����G���[�̂Ƃ�
			case CheckCommon.errors_internal:
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
				break;
			//�T�[�r�X���ԊO�̂Ƃ�	
			case CheckCommon.offservice:
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.offservice"));
				break;
			//�G���[�Ȃ��̂Ƃ�	
			case CheckCommon.success:
				errors = null;
		}
		
		//�G���[����
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}



		//--------�ȉ��A�t�H�[���̉������{�^�����珈��������s��
		
		//�ŏ��Ƀ��j���[���炫���Ƃ��A�Ɖ�ʉ�ʂ���u�߂�v�{�^���Ŗ߂��Ă����Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			String KaiSkbCod = 	logonForm.getKaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCod(KaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);
			//2003/05/15
			fmForm.setKaiSkbCodOptions(kaiList);
			fmForm.setKetCodOptions(logonForm.getKetCodList(),logonForm.getKetNm2List());//2003/05/27 2003/08/01 �C��
			fmForm.setHyoKbn(logonForm.getHyoKbn()); // add 2011/05/30
			
		}
		//�u�N���A�v������
		else if(fmForm.getCommand().equals(KetRakForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
		}


		//�����敪���Ɖ�
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
			}
			
		//�����敪���o�^
		}else if(fmForm.getPrcKbn().equals("2")){
			errors = this.insert(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
			}
							
		//�����敪���ύX
		}else if(fmForm.getPrcKbn().equals("3")){
			errors = this.update(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
			}		

		//�����敪���폜
		}else if(fmForm.getPrcKbn().equals("4")){
			errors = this.delete(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.delete.end"));
			}		
		}
		
		//--------��������I���
		
		//�G���[����
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}

		//�G���[�Ȃ�
		return mapping.findForward("success");			
	}

	
	/**
	 * �Ɖ�
	 * 
	 */
	private ActionErrors select (KetRakForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		KetRakVO fmVO = new KetRakVO();		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		
		//2003/05/15
		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();
	
		//��Ў��ʃR�[�h	
		fmVO.setKaiSkbCod(kai[0]);	
		//�����N
		fmVO.setRank(fmForm.getRank());			
		//�`�ԃR�[�h
		fmVO.setKetCod(ket[0]);			

		try{
			//�������s
			KetRakVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			
			fmForm.removeKetRakVO();
			
			if(resultFmVOs.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVOs.length),KetRakForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
				
				for(int i = 0;i < resultFmVOs.length;i++){
					fmForm.setOutKaiSkbCod(i,resultFmVOs[i].getKaiSkbCod());
					fmForm.setOutRank(i,resultFmVOs[i].getRank());
					fmForm.setOutKetCod(i,resultFmVOs[i].getKetCod());
					fmForm.setSsnRedTim(i,resultFmVOs[i].getSsnRedTim());
					fmForm.setMinZaiSuu(i,resultFmVOs[i].getMinZaiSuu());
					fmForm.setMinRotSuu(i,resultFmVOs[i].getMinRotSuu());
					fmForm.setMrmSuu(i,resultFmVOs[i].getMrmSuu());
					fmForm.addKetRakVO(resultFmVOs[i]);//2003/05/22 add okada
				}
			
			}else{
				fmForm.setSize(KetRakForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist")); 
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}
	
	/**
	 * �o�^
	 * 
	 */
	private ActionErrors insert (KetRakForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		KetRakVO fmVO = new KetRakVO();		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		//2003/05/15
		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
//		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setKaiSkbCod(kai[0]);
//		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setRank(fmForm.getRank());
		fmVO.setKetCod(ket[0]);
		
			
		for(int i = 0; i < KetRakForm.DEFAULT_ROW_COUNT;i++){

			fmVO.setSsnRedTim(fmForm.getSsnRedTim(i));
			fmVO.setMinZaiSuu(fmForm.getMinZaiSuu(i));
			fmVO.setMinRotSuu(fmForm.getMinRotSuu(i));
			fmVO.setMrmSuu(fmForm.getMrmSuu(i));		

		}
		
		lisFmVO.add(fmVO);
		
		try{
			//�o�^���s
			KetRakResult result = bzDelegate.doInsert(fmVO);
		
			//�G���[�`�F�b�N
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //�o�^���悤�Ƃ����f�[�^�����łɑ���
				if(result.getDescription().equals(KetRakDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				 //�o�^���悤�Ƃ����f�[�^���_���폜��
//				else if(result.getDescription().equals(KetRakDelegate.LOGICAL_DELETE))	
//					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));

			}

			//�o�^���e�Z�b�g
			if(errors == null){
				for(int i = 0; i < KetRakForm.DEFAULT_ROW_COUNT;i++){

					fmForm.setOutKaiSkbCod(i,result.getVO().getKaiSkbCod());
					fmForm.setOutRank(i,result.getVO().getRank());
					fmForm.setOutKetCod(i,result.getVO().getKetCod());
					fmForm.addKetRakVO(result.getVO());//2003/07/14 add okada
				}
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	
		
		return errors;			
	}

	
	/**
	 * �X�V
	 * 
	 */
	private ActionErrors update (KetRakForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
		boolean ikkatsuHenko = false;
		//�ꊇ�ύX�G���A�Ɉ�ł����͂���������
		if(!fmForm.getIkkatsu_ssnRedTim().equals("") || !fmForm.getIkkatsu_minZaiSuu().equals("") || 
		   !fmForm.getIkkatsu_minRotSuu().equals("") || !fmForm.getIkkatsu_mrmSuu().equals(""))
		   ikkatsuHenko = true;
		
		for(int i = 0; i < fmForm.getSize();i++){
			KetRakVO fmVO = fmForm.getKetRakVO(i);//2003/05/22 add okada
			
			if(ikkatsuHenko){//�ꊇ�ύX�̎��c�\������Ă���S�Ă̍s���X�V�ΏۂƂ���
				
				if(!fmForm.getIkkatsu_ssnRedTim().trim().equals(""))
					fmVO.setSsnRedTim(fmForm.getIkkatsu_ssnRedTim());
				else
					fmVO.setSsnRedTim(fmForm.getSsnRedTim(i));
					
				if(!fmForm.getIkkatsu_minZaiSuu().trim().equals(""))
					fmVO.setMinZaiSuu(fmForm.getIkkatsu_minZaiSuu());
				else
					fmVO.setMinZaiSuu(fmForm.getMinZaiSuu(i));
					
				if(!fmForm.getIkkatsu_minRotSuu().trim().equals(""))
					fmVO.setMinRotSuu(fmForm.getIkkatsu_minRotSuu());
				else
					fmVO.setMinRotSuu(fmForm.getMinRotSuu(i));
					
				if(!fmForm.getIkkatsu_mrmSuu().trim().equals(""))
					fmVO.setMrmSuu(fmForm.getIkkatsu_mrmSuu());
				else
					fmVO.setMrmSuu(fmForm.getMrmSuu(i));
				
			}else{//�`�F�b�N�̕t���Ă���s���X�V�Ώ�
				
				if(!fmForm.getExecute(i)){
					lisFmVO.add(null);	
					continue;
				}
				fmVO.setSsnRedTim(fmForm.getSsnRedTim(i));
				fmVO.setMinZaiSuu(fmForm.getMinZaiSuu(i));
				fmVO.setMinRotSuu(fmForm.getMinRotSuu(i));
				fmVO.setMrmSuu(fmForm.getMrmSuu(i));
				
			}
			lisFmVO.add(fmVO);
		}
		
		try{
			//�X�V���s
			KetRakResult[] results = bzDelegate.doUpdate((KetRakVO[])lisFmVO.toArray(new KetRakVO[lisFmVO.size()]));
		
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(KetRakDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(KetRakDelegate.LOGICAL_DELETE)||
					   results[i].getDescription().equals(KetRakDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				}
			}
			if(errors == null){ 
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
					fmForm.setExecute(i,false);
					KetRakVO vo = results[i].getVO();
					fmForm.setSsnRedTim(i,vo.getSsnRedTim());
					fmForm.setMinZaiSuu(i,vo.getMinZaiSuu());
					fmForm.setMinRotSuu(i,vo.getMinRotSuu());
					fmForm.setMrmSuu(i,vo.getMrmSuu());
					fmForm.setKetRakVO(i,vo);
				}
				fmForm.setIkkatsu_ssnRedTim("");
				fmForm.setIkkatsu_minZaiSuu("");
				fmForm.setIkkatsu_minRotSuu("");
				fmForm.setIkkatsu_mrmSuu("");
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}




	/**
	 * �폜
	 * 
	 */
	private ActionErrors delete (KetRakForm fmForm,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
		
		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//�`�F�b�N�Ȃ�
				lisFmVO.add(null);	
				continue;
			}
			//�`�F�b�N����
			KetRakVO fmVO = fmForm.getKetRakVO(i);
			lisFmVO.add(fmVO);
		}
		
		try{
			//�폜���s
			KetRakResult[] results = bzDelegate.doDelete((KetRakVO[])lisFmVO.toArray(new KetRakVO[lisFmVO.size()]));
		
			//�G���[�`�F�b�N
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(KetRakDelegate.ANOTHER_USER_UPDATE))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
					 //�폜���悤�Ƃ����f�[�^���_���폜�� ��  ���݂��Ȃ�
					else if(results[i].getDescription().equals(KetRakDelegate.LOGICAL_DELETE)||
						    results[i].getDescription().equals(KetRakDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				}
			}
			
			//�폜�������R�[�h��ʂ������
			if(errors == null){
				int index = 0;
				for(int i = 0; i < results.length;i++,index++){
					if(results[i] == null)
						continue;

					fmForm.removeTableField(index);
					fmForm.removeKetRakVO(index);
					index--;
				}
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}
		
		return errors;			
	}
	
	
}
