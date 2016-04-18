/**
* PurchaseOrderAction  �d��������}�X�^�[�Ɖ�A�N�V�����N���X
*
*	�쐬��    2003/05/19
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*/



package com.jds.prodord.master.purchaseorder;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PurchaseOrderAction extends Action {
	
	private static final String INFOMSG_KEY = "INFOMSG_KEY";

	private static  PurchaseOrderDelegate bzDelegate = new PurchaseOrderDelegate();



	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		HttpSession session = req.getSession();		//�t�H�[���̃f�[�^
		PurchaseOrderForm fmForm = (PurchaseOrderForm)form;
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



	PurchaseOrderVO fmVO = new PurchaseOrderVO();
	
//	System.out.println("Command�̒��g:"+fmForm.getCommand());

		//--------�ȉ��A�t�H�[���̉������{�^�����珈��������s��

		//***  �}�X�^�[���j���[�̎d��������{�^������������  ***

		if(fmForm.getCommand().equals("INIT")){
//		System.out.println("�}�X�^�[���j���[���F�i�ԃ}�X�^�[�Ɖ�{�^����������܂���");
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();

			fmForm.setDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setKaiSkbCodList(kaiList);			

		}


		//***  �d��������}�X�^�[�Ɖ�̎��s�{�^������������  ***

		if(fmForm.getCommand().equals(PurchaseOrderForm.COMMAND_JITTKOU)){

//			System.out.println("�d��������}�X�^�[�Ɖ���F���s�{�^����������܂���");
				errors = this.find(fmForm,req,res);
				
			if(errors == null)
				req.setAttribute(INFOMSG_KEY,mr.getMessage("info.select.end"));


		//***  �d��������}�X�^�[�Ɖ�̃N���A�{�^������������  ***

		}else if(fmForm.getCommand().equals(PurchaseOrderForm.COMMAND_CLEAR)){

//			System.out.println("�d��������}�X�^�[�Ɖ���F�N���A�{�^����������܂���");

			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		}


		//�G���[����
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}

		//�G���[�Ȃ�
		return mapping.findForward("success");		
	}


//===============================================================================

	//*** ���� ***

	private ActionErrors find (PurchaseOrderForm fmForm,HttpServletRequest req,HttpServletResponse res){

//	System.out.println("�����������s���܂�");

		ActionErrors errors = null;
		PurchaseOrderVO queryFmVO = new PurchaseOrderVO();

		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		queryFmVO.setDaiKaiSkbCod(fmForm.getDaiKaiSkbCod());
		queryFmVO.setKaiSkbCodList(fmForm.getKaiSkbCodList());
		queryFmVO.setHattyuCod(fmForm.getHattyuCod());


//		System.out.println("�������������s���܂�");

		try{
			//�������s
			PurchaseOrderVO resultFmVO = bzDelegate.doFind(queryFmVO);

			fmForm.clearTableField1();

            if(resultFmVO.getFinded_flg()){	

				//�������ʂ��t�H�[���ɃZ�b�g

				fmForm.setHattyuCod(resultFmVO.getHattyuCod());
				fmForm.setOrderName1(resultFmVO.getOrderName1());
				fmForm.setOrderName2(resultFmVO.getOrderName2());
				fmForm.setOrderAdr1(resultFmVO.getOrderAdr1());
				fmForm.setOrderAdr2(resultFmVO.getOrderAdr2());
				fmForm.setTelNum(resultFmVO.getTelNum());
				fmForm.setPostNum(resultFmVO.getPostNum());

            }
            
	        else{
				//�f�[�^�����݂��܂���
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));

	        }

		}catch(SQLException sqle){
			sqle.printStackTrace();

			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;

	}

}