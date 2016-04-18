/**
* ManualOrderAction  �}�j���A�������w���A�N�V�����N���X
*
*	�쐬��    2003/04/25
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*       2003/05/29�i�m�h�h�j�g�c �h�q
*  			�E�������ځi��ЁE���[�J�[���ށE�`�ԃR�[�h�E�M�m�敪�j�̒ǉ��B
* 		2003/07/23 �i�m�h�h�j���c �Ĕ�
* 			�E�\�[�g�����ǉ��i�`�ԃR�[�h�A�\�[�g�L�[�j�B
* 		2003/09/10�i�m�h�h�j���c �Ĕ�
* 			�E�L���ԍ��̋L�����ȗ����͑Ή�
* 		2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		2004/07/02�i�m�h�h�j�g�c
* 			�E�R�s�[���y�[�X�g�@�\�ǉ�
*		2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�Ŕ[�i�於���擾����悤�ɏC��
*		2005/05/25�i�m�h�h�j�g�c
*			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 		2006/05/10�i�m�h�h�j�c�[ �N��
* 			�E�L���O�Ђ̃����N���b�܂�
* 
*/

package com.jds.prodord.indicate.manualorder;
import com.jds.prodord.common.*;
import com.jds.prodord.order.prsorder.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ManualOrderAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  ManualOrderDelegate bzDelegate = new ManualOrderDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		ManualOrderForm fmForm = (ManualOrderForm)form;
		ManualOrderVO fmVO = new ManualOrderVO();
		

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
		
		//�{�^�����\��t�� 2004/06/29 add
		if(fmForm.getPaste().equals("�\��t��")){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
			fmForm.setPaste("");//�\��t���{�^�����Z�b�g
			return (new ActionForward(mapping.getInput()));
		}

		//--------�ȉ��A�t�H�[���̉������{�^�����珈��������s��
		//�ŏ��Ƀ��j���[���炫���Ƃ��A�Ɖ�ʉ�ʂ���u�߂�v�{�^���Ŗ߂��Ă����Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			if(fmForm.getFromMenuSgn()){
				fmForm.clearAll();
				fmForm.setRb_select(ManualOrderForm.SELECT_HBI);
		   		com.jds.prodord.register.LogonForm logonForm 
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
				String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
				String KaiSkbCod = 	logonForm.getKaiskbcod();
				ArrayList kaiList = logonForm.getKaiSkbCodList();
				fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
				fmForm.setQueryKaiSkbCod(KaiSkbCod);
				fmForm.setQueryKaiSkbCodList(kaiList);
				fmForm.setBshCod(logonForm.getBshCod());//2005/02/03 add
				fmForm.setUsrId(logonForm.getUser());//2005/05/23 add
				//�I�����ڂ̃I�v�V�������Z�b�g
				fmForm.setKaiSkbCodOptions(kaiList);
				fmForm.setKetCodOptions(logonForm.getKetNmList());
				fmForm.setMkrBunOptions(logonForm.getMkrBunList());
				
			}
			fmForm.setFromMenuSgn(false);
			fmForm.setCheckBoxValue();
		    //�������E�Ɖ�ʉ�ʂ�BEAN��remove
		    session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
			session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			session.removeAttribute(CommonConst.PRSORDER_VOS);
		}
		//�{�^�����v���X����
		if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_PRSHACHU)){
			errors = this.prsHachu(fmForm,req,res,fmVO);

			if(errors == null){	//��������

				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());	
				return mapping.findForward("next");	
			}
		//�{�^���������ޔ���
		}else if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_FUKUHACHU)){
			errors = this.fukHachu(fmForm,req,res,fmVO);
			if(errors == null){	//��������
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());	
				return mapping.findForward("next");	
			}

		//�{�^�����N���A
		}else if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		
		//�{�^�����\��t���@2004/06/29 add
		}else if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_PASTE)){
			dataPaste(fmForm);
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

	
	/**
	 * �v���X����
	 * 
	 */
	private ActionErrors prsHachu(ManualOrderForm fmForm,HttpServletRequest req,HttpServletResponse res,ManualOrderVO fmVO){

		ActionErrors errors = null;
		
		ArrayList arr_kai = new ArrayList();
		ArrayList arr_kig = new ArrayList();
		ArrayList arr_hyo = new ArrayList();
		ArrayList arr_ket = new ArrayList();
		ArrayList arr_mkr = new ArrayList();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setBshCod(fmForm.getBshCod());//2005/02/03 add
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/23 add
	
		//�R�}���h
		fmVO.setCommand(fmForm.getCommand());
		
		//�敪
		fmVO.setKbn(fmForm.getKbn());


		/** ��ЁE���[�J�[�E�`�ԁE�M�m�E������  �w��̂Ƃ� **/
		//������
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HBI){
			fmVO.setHbiDte1(fmForm.getHbiDte1());
			fmVO.setHbiDte2(fmForm.getHbiDte2());
			fmVO.setHbiDte3(fmForm.getHbiDte3());
			fmVO.setHbiDte4(fmForm.getHbiDte4());
			fmVO.setHbiDte5(fmForm.getHbiDte5());


			//��Ў��ʃR�[�h	
			if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_ALL){
				fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			}else if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_SELECT){
				String[] kai = fmForm.getKaiSkbCod();
				for(int i = 0; i<kai.length; i++){
					arr_kai.add(kai[i]);
				}
				fmVO.setKaiSkbCod_arr(arr_kai);			
			}
		
			//�`�ԃR�[�h
			if(fmForm.getRb_ketCod() == ManualOrderForm.RB_SELECT){
				String[] ket = fmForm.getKetCod();
				for(int i = 0; i<ket.length; i++){
					arr_ket.add(ket[i]);
				}
				fmVO.setKetCod_arr(arr_ket);			
			}
			//���[�J�[����
			if(fmForm.getRb_mkrBun() == ManualOrderForm.RB_SELECT){
				String[] mkr = fmForm.getMkrBun();
				for(int i = 0; i<mkr.length; i++){
					arr_mkr.add(mkr[i]);
				}
				fmVO.setMkrBun_arr(arr_mkr);			
			}
			//�M�m�敪
			if(fmForm.getRb_hyoKbn() == ManualOrderForm.RB_SELECT){
				String[] hyoKbn = fmForm.getHyoKbn();
				for(int i = 0; i<hyoKbn.length; i++){
					arr_hyo.add(hyoKbn[i]);
				}
				fmVO.setHyoKbn_arr(arr_hyo);			
			}
			//�\�[�g����
			fmVO.setSort_ketCod(fmForm.getSort_ketCod());
			fmVO.setSort_sortKey(fmForm.getSort_sortKey());
		}
		
		/** �ǉ��i��  �w��̂Ƃ� **/
		//�ǉ��i��
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HIN){
			//��Ў��ʃR�[�h
			fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			//�L���ԍ�
			arr_kig = DataFormatUtils.supplement_kigBng(fmForm.getKigBngStr_List());//�L�����ȗ����͑Ή� 2003/09/10
			fmVO.setKigBng_arr(arr_kig);
		}
		fmForm.setCheckBoxValueToArray();
		
		if(queryDaiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
		//�����N�`�F�b�N
			try{
				ManualOrderResult result = bzDelegate.doRankCheck((ManualOrderVO)fmVO);
	
				//�G���[�������̂͂Ȃ������ׂ�
				if(result != null && !result.isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
	
					ArrayList errs_kig = result.getMap(ManualOrderResult.KEY_KIGBNG);
					String index = "";
					 //�i�Ԃ����݂��Ȃ�
					if(errs_kig != null){	
						for(int i = 0; i < errs_kig.size(); i++){
							String[] err_kig = (String[])errs_kig.get(i);
							index = err_kig[0];//�G���[�̂�����Index
							int type  = Integer.parseInt(err_kig[1]);//�G���[�̎��
							if(type == 1)//ManualOrderDAO.NOT_EXIST
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"�i��"));
							if(type == 2)//ManualOrderDAO.RANK_ERR
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.error_no",index,"�i��","�����N"));
						}
					}
				}
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}
		}
		
		if(errors == null || errors.empty()){
			//�u�����N�̗v�f����������l�߂ăZ�b�g������
			fmVO.setKigBng_arr(DataFormatUtils.removeBlankElement(fmVO.getKigBng_arr()));

			try{
				PrsOrderVO[] prsVOs = bzDelegate.doPrsHachu(fmVO,fmForm);
				fmVO.setPrsVOs(prsVOs);

				//�������ʂ��O����������
				if(prsVOs.length == 0){
					errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				}	


			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}		
		}
		return errors;			
	}
	

	/**
	 * �����ޔ���
	 * 
	 */
	private ActionErrors fukHachu 
			(ManualOrderForm fmForm,HttpServletRequest req,HttpServletResponse res,ManualOrderVO fmVO){

		HttpSession session = req.getSession();
		ActionErrors errors = null;
		
		ArrayList arr_kai = new ArrayList();
		ArrayList arr_kig = new ArrayList();
		ArrayList arr_ket = new ArrayList();
		ArrayList arr_mkr = new ArrayList();
		ArrayList arr_hyo = new ArrayList();
		
		com.jds.prodord.register.LogonForm logonForm 
				= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);

		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/25 add
	
		//�R�}���h
		fmVO.setCommand(fmForm.getCommand());
		
		//�敪
		fmVO.setKbn(fmForm.getKbn());
		

		/** ��ЁE���[�J�[�E�`�ԁE�M�m�E������  �w��̂Ƃ� **/
		//������
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HBI){
			fmVO.setHbiDte1(fmForm.getHbiDte1());
			fmVO.setHbiDte2(fmForm.getHbiDte2());
			fmVO.setHbiDte3(fmForm.getHbiDte3());
			fmVO.setHbiDte4(fmForm.getHbiDte4());
			fmVO.setHbiDte5(fmForm.getHbiDte5());
			
			//��Ў��ʃR�[�h	
			if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_ALL){
				fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			}else if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_SELECT){
				String[] kai = fmForm.getKaiSkbCod();
				for(int i = 0; i<kai.length; i++){
					arr_kai.add(kai[i]);
				}
				fmVO.setKaiSkbCod_arr(arr_kai);			
			}
			
			//�`�ԃR�[�h
			if(fmForm.getRb_ketCod() == ManualOrderForm.RB_SELECT){
				String[] ket = fmForm.getKetCod();
				for(int i = 0; i<ket.length; i++){
					arr_ket.add(ket[i]);
				}
				fmVO.setKetCod_arr(arr_ket);			
			}
			//���[�J�[����
			if(fmForm.getRb_mkrBun() == ManualOrderForm.RB_SELECT){
				String[] mkr = fmForm.getMkrBun();
				for(int i = 0; i<mkr.length; i++){
					arr_mkr.add(mkr[i]);
				}
				fmVO.setMkrBun_arr(arr_mkr);			
			}
			//�M�m�敪
			if(fmForm.getRb_hyoKbn() == ManualOrderForm.RB_SELECT){
				String[] hyoKbn = fmForm.getHyoKbn();
				for(int i = 0; i<hyoKbn.length; i++){
					arr_hyo.add(hyoKbn[i]);
				}
				fmVO.setHyoKbn_arr(arr_hyo);			
			}
			//�\�[�g����
			fmVO.setSort_ketCod(fmForm.getSort_ketCod());
			fmVO.setSort_sortKey(fmForm.getSort_sortKey());
		}
		

		/** �ǉ��i��  �w��̂Ƃ� **/
		//�ǉ��i��
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HIN){
			//��Ў��ʃR�[�h
			fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			//�L���ԍ�
			arr_kig = DataFormatUtils.supplement_kigBng(fmForm.getKigBngStr_List());//�L�����ȗ����͑Ή� 2003/09/10
			fmVO.setKigBng_arr(arr_kig);
		}
		fmForm.setCheckBoxValueToArray();
		
		if(queryDaiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
			//�����N�`�F�b�N
			try{
				ManualOrderResult result = bzDelegate.doRankCheck((ManualOrderVO)fmVO);

				//�G���[�������̂͂Ȃ������ׂ�
				if(result != null && !result.isSuccess()){
					if(errors == null)
						errors = new ActionErrors();

					ArrayList errs_kig = result.getMap(ManualOrderResult.KEY_KIGBNG);
					String index = "";
					 //�i�Ԃ����݂��Ȃ�
					if(errs_kig != null){	
						for(int i = 0; i < errs_kig.size(); i++){
							String[] err_kig = (String[])errs_kig.get(i);
							index = err_kig[0];//�G���[�̂�����Index
							int type  = Integer.parseInt(err_kig[1]);//�G���[�̎��
							if(type == 1)//ManualOrderDAO.NOT_EXIST
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"�i��"));
							if(type == 2)//ManualOrderDAO.RANK_ERR
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.error_no",index,"�i��","�����N"));
						}
					}
				}	
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}		
		}

		if(errors == null || errors.empty()){
			//�u�����N�̗v�f����������l�߂ăZ�b�g������
			fmVO.setKigBng_arr(DataFormatUtils.removeBlankElement(fmVO.getKigBng_arr()));
			try{
			
				PrsOrderVO[] prsVOs = bzDelegate.doFukHachu(fmVO,fmForm);
				fmVO.setPrsVOs(prsVOs);

				//�������ʂ��O����������
				if(prsVOs.length == 0){
					errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				}
		

			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}		
		}
		return errors;			
	}

	

	
//---------------------------------------------------------------------------------------------------	
	
	/** 
	 * �\��t��
	 * @param fmForm
	 */
	public void dataPaste(ManualOrderForm fmForm){
		
		final String tab = "\t";
		int formSize = 15;
		
		String data = fmForm.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));
		ArrayList kigBngList = new ArrayList();
		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);


		try {

			for (int i = 0; i < count; i++) {

				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					StringTokenizer line = new StringTokenizer(txt, tab);	

					if(line.hasMoreTokens())
						kigBngList.add(line.nextToken().trim());

				}

			}

			fmForm.setKigBng_List(kigBngList);

		}catch(IOException ex){
			SystemException se = new SystemException(ex);
			se.printStackTrace();
		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
		}
	}
	
}
