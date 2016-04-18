package com.jds.prodord.indicate.manualorderpaste;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.jds.prodord.common.CheckCommon;
import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.SystemException;

/**
 * ManualOrderPasteAction  �}�j���A�������w���ꊇ�\�t�A�N�V�����N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�����Ăяo�����s���A��ʐ�����s���N���X�B</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>�E</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  ManualOrderPasteDelegate bzDelegate = new ManualOrderPasteDelegate();
	
	
	/* (�� Javadoc)
	 * @see org.apache.struts.action.Action#perform(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		ManualOrderPasteForm fmForm = (ManualOrderPasteForm)form;
		ManualOrderPasteVO fmVO = new ManualOrderPasteVO();
		

		//�u�v���X�^�����ށv�ύX
		if(fmForm.getPrsFksChangeFlg().equals("1")){
			fmForm.setFormRows(new LinkedList());
//			fmForm.setCpPaste("");
			fmForm.setCpPasteMode("1");
			fmForm.setPrsFksChangeFlg("0");

			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		

		}


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
				fmForm.setUsrId(logonForm.getUser());
				fmForm.setBshCod(logonForm.getBshCod());
				
				fmForm.setCpPasteMode("1");

				//�I�����ڂ̃I�v�V�������Z�b�g
				fmForm.initOptionLists();
				
			}
			fmForm.setFromMenuSgn(false);
		    //�������E�Ɖ�ʉ�ʂ�BEAN��remove
		    session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
			session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			session.removeAttribute(CommonConst.PRSORDER_VOS);


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
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());	
				return mapping.findForward("next");	
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
	public void dataPaste(ManualOrderPasteForm fmForm){
		
		ManualOrderPasteVO fmVO = new ManualOrderPasteVO();
		fmVO.setCpPaste(fmForm.getCpPaste());
		fmVO.setPrsFks(fmForm.getPrsFks());

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
	private ActionErrors execute(ManualOrderPasteForm fmForm,HttpServletRequest req,HttpServletResponse res,ManualOrderPasteVO fmVO){

		ActionErrors errors = null;
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setQueryKaiSkbCod(fmForm.getQueryKaiSkbCod());
		
		fmVO.setBshCod(fmForm.getBshCod());
		fmVO.setUsrId(fmForm.getUsrId());
	
		//�v���X�^������
		fmVO.setPrsFks(fmForm.getPrsFks());
		//�敪
		fmVO.setKbn(fmForm.getKbn());

		//���׍s
		fmVO.setFormRows(fmForm.getFormRows());

		try{

			ManualOrderPasteResult result = null;
			
			if(fmForm.getPrsFks().equals(ManualOrderPasteForm.PRS)){
				result = bzDelegate.doPrsHachu(fmVO,fmForm);
			}else{
				result = bzDelegate.doFukHachu(fmVO,fmForm);
			}

			//�G���[����������
			if(!result.isSuccess()){
				errors = new ActionErrors();
				LinkedList errList = result.getErrList();
				for (Iterator iter = errList.iterator(); iter.hasNext();) {
					String err[] =(String[])iter.next();
					errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(err[1])));
				}
				return errors;
			}	

			fmVO.setPrsVOs(result.getVO().getPrsVOs());


		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		

		return errors;			
	}
	

	
}
