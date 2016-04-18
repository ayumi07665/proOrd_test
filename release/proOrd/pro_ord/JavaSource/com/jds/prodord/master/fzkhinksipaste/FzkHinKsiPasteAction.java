package com.jds.prodord.master.fzkhinksipaste;
import com.jds.prodord.common.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

/**
* FzkHinKsiPasteAction  �t���i�\���}�X�^�[�ꊇ�\��t���A�N�V�����N���X
*
*	�쐬��    2007/09/21
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*/
public class FzkHinKsiPasteAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  FzkHinKsiPasteDelegate bzDelegate = new FzkHinKsiPasteDelegate();
	
	
	/* (�� Javadoc)
	 * @see org.apache.struts.action.Action#perform(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		FzkHinKsiPasteForm fmForm = (FzkHinKsiPasteForm)form;
		FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();
		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��
		
		if(!fmForm.getCommand().equals("INIT")){
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
		}
		
		//--------�ȉ��A�t�H�[���̉������{�^�����珈��������s��
		//�ŏ��Ƀ��j���[���炫���Ƃ��A�Ɖ�ʉ�ʂ���u�߂�v�{�^���Ŗ߂��Ă����Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			if(fmForm.getFromMenuSgn()){
				fmForm.clearAll();
				com.jds.prodord.register.LogonForm logonForm 
						= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
				String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
				String KaiSkbCod = 	logonForm.getKaiskbcod();
				ArrayList kaiList = logonForm.getKaiSkbCodList();

				fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
				fmForm.setQueryKaiSkbCod(KaiSkbCod);
				fmForm.setQueryKaiSkbCodList(kaiList);
				fmForm.setKaiSkbCod(KaiSkbCod);
				fmForm.setHyoKbn(logonForm.getHyoKbn());
				
				fmForm.setCpPasteMode("1");
				
			}
			fmForm.setFromMenuSgn(false);


		//�{�^�����\��t��
		}else if(fmForm.getCommand().equals(fmForm.getMessage("button.paste"))){

				dataPaste(fmForm);
			
				fmForm.setCpPaste("");
				fmForm.setCpPasteMode("0");
				fmForm.setSize(fmForm.ROW_MAX);


		//�{�^�������s
		}else if(fmForm.getCommand().equals(fmForm.getMessage("button.execute"))){
			errors = execute(fmForm,req,res,fmVO);
			if(errors == null){	//��������
//				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getFzkVOs());	
				if(errors == null){	//��������
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
				}
//				return mapping.findForward("next");	
			}


		//�{�^�����N���A
		}else if(fmForm.getCommand().equals(fmForm.getMessage("button.clear"))){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
		
		}

		
		//--------��������I���
		
		//�G���[����
		if(errors != null){
			super.saveErrors(req,errors);
		}

		return new ActionForward(mapping.getInput());
	}

//	---------------------------------------------------------------------------------------------------	
	/** 
	 * �\�t
	 * @param fmForm
	 */
	public void dataPaste(FzkHinKsiPasteForm fmForm){
		
		FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();
		fmVO.setCpPaste(fmForm.getCpPaste());

		try{
			bzDelegate.doDataPaste(fmVO);

			fmForm.setFormRows(fmVO.getFormRows());
			
		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
		}
	}


	/**
	 * ���s
	 * @param fmForm
	 * @param req
	 * @param res
	 * @param fmVO
	 * @return
	 */

	private ActionErrors execute(FzkHinKsiPasteForm fmForm,HttpServletRequest req,HttpServletResponse res,FzkHinKsiPasteVO fmVO){

		ActionErrors errors = null;
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setQueryKaiSkbCod(fmForm.getQueryKaiSkbCod());
		fmVO.setKaiSkbCod(fmForm.getKaiSkbCod());
		
		//���׍s
		fmVO.setFormRows(fmForm.getFormRows());

		try{

			FzkHinKsiPasteResult result = null;
			
			result = bzDelegate.doInsert(fmVO,fmForm);

			//�G���[����������
			if(!result.isSuccess()){
				errors = new ActionErrors();
				LinkedList errList = result.getErrList();
				for (Iterator iter = errList.iterator(); iter.hasNext();) {
					int err[] =(int[])iter.next();

					String key = null;

					switch(err[1]){
						case FzkHinKsiPasteForm.NOT_HIN_EXIST:
							key = "label.kigbng";
							errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(key)));
							break;					
						case FzkHinKsiPasteForm.NOT_FKS_EXIST:
							key = "label.fukshizai";
							errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(key)));
							break;					
						case FzkHinKsiPasteForm.NOT_SIR_EXIST:
							key = "label.sirhaccod";
							errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(key)));
							break;					
						case FzkHinKsiPasteForm.MST08_EXIST:
							errors.add("ROW_"+err[0],new ActionError("errors.insert.duplicate"));
							break;					
					}
				}
				return errors;
			}	



		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		

		return errors;			
	}

}
