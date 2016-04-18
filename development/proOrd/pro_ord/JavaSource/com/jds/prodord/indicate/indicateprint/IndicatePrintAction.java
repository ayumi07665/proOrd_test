/**
* IndicatePrintAction  �������o�͎w���A�N�V�����N���X
*
*	�쐬��    2003/04/23
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*       2003/06/05�i�m�h�h�j�g�c �h�q
* 			�E�u�ďo�́v�̏������ځi���������t�A������R�[�h�j�ǉ��B
* 		2003/07/16�i�m�h�h�j���c �Ĕ�
* 			�E���������tFrom�Ƀf�t�H���g�œ������t�Z�b�g �ǉ��B
* 		2003/09/05�i�m�h�h�j���c �Ĕ�
* 			�E������������������pVO�ւ̌��������Z�b�g���@��ύX�B
* 			�iPrintOrdersQueryRow �� StringTokenizer�j
* 			�E�������ԍ��̃t�H�[�}�b�g���\�b�h�ǉ�
* 		2005/05/23�i�m�h�h�j�g�c
* 			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 
*/

package com.jds.prodord.indicate.indicateprint;
import com.jds.prodord.common.*;
import com.jds.prodord.print.printorders.*;
import com.jds.prodord.register.LogonForm;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class IndicatePrintAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  IndicatePrintDelegate bzDelegate = new IndicatePrintDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		IndicatePrintForm fmForm = (IndicatePrintForm)form;
		IndicatePrintVO fmVO = new IndicatePrintVO();
		
		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��

		//�������f�[�^���������p�̃f�[�^���i�[����VO
		PrintOrdersQueryVO poQueryVO = new PrintOrdersQueryVO();


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
		//�{�^�����`�[���s
		if(fmForm.getCommand().equals(IndicatePrintForm.COMMAND_DENPYOHAKKOU)){
			errors = this.dnpHakkou(fmForm,req,res,fmVO,poQueryVO);
			if(errors == null){	//��������
				fmForm.setCommand(IndicatePrintForm.SUCCESS_DENPYOHAKKOU);
				fmForm.setSyoriFlg(IndicatePrintForm.DENPYOHAKKOU);
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//�������f�[�^�����������Z�b�g���ꂽpoQueryVO���Z�b�V�����Ɋi�[
				return mapping.findForward("issue");
			}
		}
		//�`�[���s����
		else if(fmForm.getCommand().equals(IndicatePrintForm.SUCCESS_DENPYOHAKKOU)){
			if(session.getAttribute(CommonConst.PO_QUERY_VO) != null){
				fmForm.setSyoriFlg("");
				fmForm.setNewWindow("1");
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
			}else{
				fmForm.setCommand("INIT");
			}
		//�{�^�����ďo��
		}else if(fmForm.getCommand().equals(IndicatePrintForm.COMMAND_SAISYRYK)){
			errors = this.saiSyryk(fmForm,req,res,fmVO,poQueryVO);
			if(errors == null){	//��������
				fmForm.setCommand(IndicatePrintForm.SUCCESS_SAISYRYK);
				fmForm.setSyoriFlg(IndicatePrintForm.SAISYRYK);
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//�������f�[�^�����������Z�b�g���ꂽpoQueryVO���Z�b�V�����Ɋi�[
				return mapping.findForward("reissue");		
			}
		}
		//�ďo�͊���
		else if(fmForm.getCommand().equals(IndicatePrintForm.SUCCESS_SAISYRYK)){
			if(session.getAttribute(CommonConst.PO_QUERY_VO) != null){
				fmForm.setSyoriFlg("");
				fmForm.setNewWindow("1");
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.reissue.end"));
			}else{
				fmForm.setCommand("INIT");
			}
		//�N���A
		}else if(fmForm.getCommand().equals(IndicatePrintForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		}
		//�ŏ��Ƀ��j���[���炫���Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			this.init(fmForm,session);
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

//------------------------------------------------------------------------------------------------

	private void init(IndicatePrintForm fmForm, HttpSession session){
		fmForm.clearAll();
		fmForm.setSyoriFlg("");
		fmForm.setNewWindow("0");
		fmForm.setRb_select(IndicatePrintForm.SELECT_ALL);
   		LogonForm logonForm = (LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
		String KaiSkbCod = 	logonForm.getKaiskbcod();
		ArrayList kaiList = logonForm.getKaiSkbCodList();
		fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		fmForm.setQueryKaiSkbCod(KaiSkbCod);
		fmForm.setQueryKaiSkbCodList(kaiList);
		fmForm.setHacSyoDte_Frm_today();//���������tFrom�ɓ������t���Z�b�g 2003/07/16 add
		fmForm.setUsrId(logonForm.getUser());//2005/05/23 add
		fmForm.setHyoKbn(logonForm.getHyoKbn());
	}


//------------------------------------------------------------------------------------------------
	
	/**
	 * �`�[���s
	 * 
	 */
	private ActionErrors dnpHakkou (IndicatePrintForm fmForm,HttpServletRequest req,HttpServletResponse res,IndicatePrintVO fmVO,
									PrintOrdersQueryVO poQueryVO){

		ActionErrors errors = null;
		
		ArrayList arr_hac = new ArrayList();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/23 add
	
		//�R�}���h
		fmVO.setCommand(fmForm.getCommand());
		
		if(fmForm.getRb_select() == IndicatePrintForm.SELECT_SEL){
			arr_hac.add(fmForm.getHacCod1().trim());
			arr_hac.add(fmForm.getHacCod2().trim());
			arr_hac.add(fmForm.getHacCod3().trim());
			fmVO.setHacCod_arr(arr_hac);
		}
		
		try{
			//�`�[���s���s
			IndicatePrintResult[] results = bzDelegate.doDnpHakkou((IndicatePrintVO)fmVO);
			
			//�G���[�������̂͂Ȃ������ׂ�
			boolean err = false;
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
	
					ArrayList errs_hac = results[i].getMap(IndicatePrintResult.KEY_HACCOD);
					String index = "";
					 
					if(errs_hac != null){	
						for(int j = 0; j < errs_hac.size(); j++){
							index = (String)errs_hac.get(j);//�G���[�̂�����Index
							//������R�[�hNOT_EXIST
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"������R�[�h"));
							err = true;
						}
					}
					//�Ώۃf�[�^�����݂��Ȃ�
                    if(results[i].getDescription().equals(IndicatePrintDelegate.NOT_EXIST)){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
						err = true;
                    }else if(results[i].getDescription().equals("")){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception"));
						err = true;
						break;
                    }
				}

			}
			if(!err){//�G���[�Ȃ��̂Ƃ�
				//����������pVO�ɒl���Z�b�g				
				ArrayList tmpArr = new ArrayList();
				
				for(int i = 0; i < results.length;i++){
					IndicatePrintVO vo = results[i].getVO();
					
					//��Ў��ʃR�[�h,������,�������ԍ�,������R�[�h�̃Z�b�g��ArrayList�Ɋi�[  2003/09/02 �C�� okada
					String str = vo.getKaiSkbCod() + "%" +
									 StringUtils.lpad(vo.getHacSyoDte(),6,"0") + "%" +
						 			 StringUtils.lpad(vo.getHacSyoBng(),6," ") + "%" +
						   			 vo.getHacCod();
					if(tmpArr.indexOf(str) == -1){
						tmpArr.add(str);
					}
					
				}
				poQueryVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				poQueryVO.setKaiSkbCod(fmVO.getKaiSkbCod());
				poQueryVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
				poQueryVO.setPartOfQuery_arr(tmpArr);
				poQueryVO.setHasHacSyoBng(false);//���łɔ������ԍ��������Ă����false
				
			}	
		
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}
	
	
	
	
	/**
	 * �ďo��
	 * 
	 */
	private ActionErrors saiSyryk (IndicatePrintForm fmForm,HttpServletRequest req,HttpServletResponse res,IndicatePrintVO fmVO,
								   PrintOrdersQueryVO poQueryVO){

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		String hacSyoPtn = ((LogonForm)session.getAttribute(CommonConst.user)).getHacSyoPtn();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/23 add
	
		//�R�}���h
		fmVO.setCommand(fmForm.getCommand());
		
		if(!fmForm.getHacSyoBng_frm().trim().equals("")){
			
			fmVO.setHacSyoBng_frm(DataFormatUtils.makeHacSyoBng(fmForm.getHacSyoBng_frm().trim(),hacSyoPtn));
			//�������ԍ����O���߂��čĕ\��
			fmForm.setHacSyoBng_frm(fmVO.getHacSyoBng_frm());
		}
		if(!fmForm.getHacSyoBng_to().trim().equals("")){

			fmVO.setHacSyoBng_to(DataFormatUtils.makeHacSyoBng(fmForm.getHacSyoBng_to().trim(),hacSyoPtn));
			//�������ԍ����O���߂��čĕ\��
			fmForm.setHacSyoBng_to(fmVO.getHacSyoBng_to());
		}
		//2003/06/05
			//������R�[�h
		if(!fmForm.getHacCod().trim().equals("")){
			fmVO.setHacCod(fmForm.getHacCod().trim());
		}
			//���������t
		if(!fmForm.getHacSyoDte_Frm_Y().trim().equals("") && !fmForm.getHacSyoDte_Frm_M().trim().equals("") &&
		   !fmForm.getHacSyoDte_Frm_D().trim().equals("")){
		   	fmVO.setHacSyoDte_frm(fmForm.getHacSyoDte_Frm_Y(),fmForm.getHacSyoDte_Frm_M(),fmForm.getHacSyoDte_Frm_D());
		   	fmVO.setHacSyoDte_to(fmForm.getHacSyoDte_to_Y(),fmForm.getHacSyoDte_to_M(),fmForm.getHacSyoDte_to_D());
		}

		try{
			//�ďo��
			IndicatePrintResult[] results = bzDelegate.doSaiSyryk((IndicatePrintVO)fmVO);
		
			//�G���[�������̂͂Ȃ������ׂ�
			boolean err = false;
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//�Ώۃf�[�^�����݂��Ȃ�
	                if(results[i].getDescription().equals(IndicatePrintDelegate.NOT_EXIST)){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
						err = true;
	                }else if(results[i].getDescription().equals("")){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception"));
						err = true;
						break;
					}
	                if(results[i].getDescription().equals(IndicatePrintDelegate.NOT_HACCOD)){
	                	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect","������R�[�h"));
	                	err = true;
	                }
				}
			}
			if(!err){//�G���[�Ȃ��̂Ƃ�
				//����������pVO�ɒl���Z�b�g
				ArrayList finded = results[0].getFinded_arr();
				ArrayList tmpArr = new ArrayList();
				
				for(int i = 0; i<finded.size(); i++){
					IndicatePrintVO vo = (IndicatePrintVO)finded.get(i);
					
					//��Ў��ʃR�[�h,������,�������ԍ�,������R�[�h�̃Z�b�g��ArrayList�Ɋi�[  2003/09/02 �C�� okada
					String str = vo.getKaiSkbCod() + "%" +
								 vo.getHacSyoDte() + "%" +
						 		 vo.getHacSyoBng() + "%" +
						   		 vo.getHacCod();
						   		 
					if(tmpArr.indexOf(str) == -1){
						tmpArr.add(str);
					}
					
				}
				poQueryVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				poQueryVO.setKaiSkbCod(fmVO.getKaiSkbCod());
				poQueryVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
				poQueryVO.setPartOfQuery_arr(tmpArr);
				poQueryVO.setHasHacSyoBng(true);//���łɔ������ԍ��������Ă����true
				
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
