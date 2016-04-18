/**
* OrderHistoryTopAction  ���������Ɖ��ʌ��������A�N�V�����N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/09/05�i�m�h�h�j���c �Ĕ�
* 			�E�������ԍ��̃t�H�[�}�b�g���\�b�h�ǉ�
* 		 2004/04/01�i�m�h�h�j�X
* 			�E�w������ɓ��ɓ�(From�`To)�ǉ�
* 		 2005/05/25�i�m�h�h�j�g�c
* 			�E�ꏊ�R�[�h�̒ǉ�
* 
*/

package com.jds.prodord.reference.orderhistory;
import com.jds.prodord.common.*;
import com.jds.prodord.register.LogonForm;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class OrderHistoryTopAction extends Action {

	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  OrderHistoryDelegate bzDelegate = new OrderHistoryDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		
		//�t�H�[���̃f�[�^
		OrderHistoryTopForm fmForm = (OrderHistoryTopForm)form;			
		OrderHistoryVO fmVO = new OrderHistoryVO();

		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��
	

		//--------�ȉ��A�t�H�[���̉������{�^�����珈��������s��
		//�ŏ��Ƀ��j���[���炫���Ƃ�
		if(fmForm.getCommand().equals("INIT")){
	   		LogonForm logonForm = (LogonForm)session.getAttribute("user");

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			fmForm.setBshCod(logonForm.getBshCod());//2005/05/25 add

			//�I�����ڂ̃I�v�V�������Z�b�g
			fmForm.setMkrBunOptions(logonForm.getMkrBunList());
			
			session.removeAttribute(CommonConst.ORDERHISTORY_VO);
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			fmForm.clear_text();
			if(errors == null){	//��������

			}
		}
		
		//�u���s�v������
		else if(fmForm.getCommand().equals(OrderHistoryTopForm.COMMAND_JIKKOU)){
			errors = this.jikkou(fmForm,req,res,fmVO);
			if(errors == null){	//��������
				session.setAttribute(CommonConst.ORDERHISTORY_VO,fmVO);
				return (new ActionForward(mapping.getInput()));
			}else{
				session.removeAttribute(CommonConst.ORDERHISTORY_VO);
			}

		//�u�N���A�v������
		}else if(fmForm.getCommand().equals(OrderHistoryTopForm.COMMAND_CLEAR)){
			fmForm.clear_text();
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.ORDERHISTORY_VO);
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
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
	
	
//**************************************************************************************************

	/**
	 * ���s
	 * 
	 */
	private ActionErrors jikkou (OrderHistoryTopForm fmForm,HttpServletRequest req,HttpServletResponse res,OrderHistoryVO fmVO){

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		String hacSyoPtn = ((LogonForm)session.getAttribute(CommonConst.user)).getHacSyoPtn();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setKaiSkbCod(fmForm.getQueryKaiSkbCod());
		fmVO.setKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setBshCod(fmForm.getBshCod());//2005/05/25 add

		//�R�}���h
		fmVO.setCommand(fmForm.getCommand());
		//�����敪
		fmVO.setPrcKbn(fmForm.getPrcKbn());
		
		fmVO.setSyrZmiSgn_H(fmForm.getRb_HacSyo());//������
		fmVO.setBunCod_H(fmForm.getRb_BunCod());  //���ރR�[�h
		fmVO.setNyuko_H(fmForm.getRb_Nyuko());  //���ɏ�
		fmVO.setHacCod_H(fmForm.getQueryHacCod());//������R�[�h
		fmVO.setKigBng_H(DataFormatUtils.mk_srykig(fmForm.getQueryKigBng()));//�L���ԍ�
		//������
		fmVO.setHbiDte_H(fmForm.getQueryHbiDte_Y(),fmForm.getQueryHbiDte_M(),fmForm.getQueryHbiDte_D());
		//�������ԍ�
		if(fmForm.getCheckHacBng()){
			if(!fmForm.getQueryHacBngTo().equals("")){
				fmVO.setHacSyoBngFrm_H(DataFormatUtils.makeHacSyoBng(fmForm.getQueryHacBngFrm(),hacSyoPtn));
				fmVO.setHacSyoBngTo_H(DataFormatUtils.makeHacSyoBng(fmForm.getQueryHacBngTo(),hacSyoPtn));
				fmForm.setQueryHacBngFrm(fmVO.getHacSyoBngFrm_H());
				fmForm.setQueryHacBngTo(fmVO.getHacSyoBngTo_H());
			}else{
				if(!fmForm.getQueryHacBngFrm().equals("")){
					fmVO.setHacSyoBngFrm_H(DataFormatUtils.makeHacSyoBng(fmForm.getQueryHacBngFrm(),hacSyoPtn));
					fmForm.setQueryHacBngFrm(fmVO.getHacSyoBngFrm_H());
				}
			}
		}
		//������
		if(fmForm.getCheckHacDte()){
			if(!fmForm.getQueryHacDteFrm_Y().equals("") && !fmForm.getQueryHacDteFrm_M().equals("")  
			   && !fmForm.getQueryHacDteFrm_D().equals("")){
					fmVO.setHacSyoDteFrm_H(fmForm.getQueryHacDteFrm_Y(),fmForm.getQueryHacDteFrm_M(),fmForm.getQueryHacDteFrm_D());
					fmVO.setHacSyoDteTo_H(fmForm.getQueryHacDteTo_Y(),fmForm.getQueryHacDteTo_M(),fmForm.getQueryHacDteTo_D());
			}else{
				if(!fmForm.getQueryHacDteTo_Y().equals("") && !fmForm.getQueryHacDteTo_M().equals("") && 
			       !fmForm.getQueryHacDteTo_D().equals("")){
			       		fmVO.setHacSyoDteFrm_H(fmForm.getQueryHacDteFrm_Y(),fmForm.getQueryHacDteFrm_M(),fmForm.getQueryHacDteFrm_D());
			    }
			}
		}
		//�[��
		if(fmForm.getCheckNki()){
			if(!fmForm.getQueryNkiFrm_Y().equals("") && !fmForm.getQueryNkiFrm_M().equals("") 
			  && !fmForm.getQueryNkiFrm_D().equals("")){
				fmVO.setNkiFrm_H(fmForm.getQueryNkiFrm_Y(),fmForm.getQueryNkiFrm_M(),fmForm.getQueryNkiFrm_D());
				fmVO.setNkiTo_H(fmForm.getQueryNkiTo_Y(),fmForm.getQueryNkiTo_M(),fmForm.getQueryNkiTo_D());
			}else{
				if(!fmForm.getQueryNkiTo_Y().equals("") && !fmForm.getQueryNkiTo_M().equals("") 
			  	  && !fmForm.getQueryNkiTo_D().equals("")){
					fmVO.setNkiFrm_H(fmForm.getQueryNkiFrm_Y(),fmForm.getQueryNkiFrm_M(),fmForm.getQueryNkiFrm_D());
			  	}
			}
		}
		//���[�J�[����
		if(fmForm.getCheckMkrBun()){
			if(fmForm.getMkrBun() != null)
				fmVO.setMkrBun(fmForm.getMkrBun());
		}
		//�敪
		if(fmForm.getCheckKbn()){
			fmVO.setKbn_H(fmForm.getKbn());
		}
		//���ɓ�  2004/04/01 add
		if((fmForm.getCheckNyoDte())){
			if(!fmForm.getQueryNyoDteFrm_Y().equals("") && !fmForm.getQueryNyoDteFrm_M().equals("") 
			  && !fmForm.getQueryNyoDteFrm_D().equals("")){
				fmVO.setNyoDteFrm_H(fmForm.getQueryNyoDteFrm_Y(),fmForm.getQueryNyoDteFrm_M(),fmForm.getQueryNyoDteFrm_D());
				fmVO.setNyoDteTo_H(fmForm.getQueryNyoDteTo_Y(),fmForm.getQueryNyoDteTo_M(),fmForm.getQueryNyoDteTo_D());
			}else{
				if(!fmForm.getQueryNyoDteTo_Y().equals("") && !fmForm.getQueryNyoDteTo_M().equals("") 
				  && !fmForm.getQueryNyoDteTo_D().equals("")){
					fmVO.setNyoDteFrm_H(fmForm.getQueryNyoDteFrm_Y(),fmForm.getQueryNyoDteFrm_M(),fmForm.getQueryNyoDteFrm_D());
				}
			}
		}
		//�������� 2005/09/16 add
		fmVO.setChkHacJun(fmForm.isChkHacJun());
		
		try{
			//�G���[�`�F�b�N���s
			OrderHistoryResult result = bzDelegate.doJikkou((OrderHistoryVO)fmVO);
		
			//�G���[�������̂͂Ȃ������ׂ�
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
				if(result.getDescription().equals(OrderHistoryDelegate.KIGBNG_NOT_EXIST))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect","�L���ԍ�"));
                if(result.getDescription().equals(OrderHistoryDelegate.HACSAKI_NOT_EXIST))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect","������R�[�h"));
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

