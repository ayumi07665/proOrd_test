/**
* NyukoSuuAdjustAction  ���ɐ������A�N�V�����N���X
*
*	�쐬��    2003/09/30
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/10/24�i�m�h�h�j���c �Ĕ�
* 			�E���������ɔ��������tFrm-To�A�[��Frm-To�A�L���ԍ��~10��ǉ�
* 		 2004/06/28�i�m�h�h�j�g�c
* 			�E�J�b�g���y�[�X�g�@�\�ǉ�
*/

package com.jds.prodord.master.nyukosuuadjust;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import java.io.*;


public class NyukoSuuAdjustAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  NyukoSuuAdjustDelegate bzDelegate = new NyukoSuuAdjustDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		NyukoSuuAdjustForm fmForm = (NyukoSuuAdjustForm)form;
		NyukoSuuAdjustVO fmVO = new NyukoSuuAdjustVO();
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
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			
		}
		//�u�N���A�v������
		else if(fmForm.getCommand().equals(NyukoSuuAdjustForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setUpdatable(false);
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));

		//��\��t���������  2004/06/28 add
		}else if(fmForm.getCommand().equals(NyukoSuuAdjustForm.COMMAND_PASTE)){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
			return (new ActionForward(mapping.getInput()));
		}


		//�����敪���Ɖ�
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
				fmForm.setUpdatable(true);
			}else
				fmForm.setUpdatable(false);		
		//�����敪���X�V
		}else if(fmForm.getPrcKbn().equals("2")){
			errors = this.nyoSuuAdjust(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				fmForm.setUpdatable(false);
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
	private ActionErrors select (NyukoSuuAdjustForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		NyukoSuuAdjustVO fmVO = new NyukoSuuAdjustVO();		
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setQueryDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		
		fmVO.setHacCod_H(fmForm.getHacCod_H().trim());
		fmVO.setHacDteFrm_H(fmForm.getHacDteFrm_H());
		fmVO.setHacDteTo_H(fmForm.getHacDteTo_H());
		fmVO.setNkiFrm_H(fmForm.getNkiFrm_H());
		fmVO.setNkiTo_H(fmForm.getNkiTo_H());
		//�L���ԍ�
		ArrayList arrKig = new ArrayList();
		ArrayList arr = DataFormatUtils.supplement_kigBng(fmForm.getKigBng_List());
		for(int i = 0; i < arr.size(); i++){
			arrKig.add(DataFormatUtils.mk_srykig(arr.get(i).toString()));
		}
		fmVO.setkigBng_arr_H(DataFormatUtils.removeBlankElement(arrKig));
		
		try{
			//�������s
			NyukoSuuAdjustVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			
			fmForm.removeNyukoSuuAdjustVO();
			
			if(resultFmVOs.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVOs.length),NyukoSuuAdjustForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
				
				for(int i = 0;i < resultFmVOs.length;i++){
					NyukoSuuAdjustFormRow formRow = fmForm.getFormRow(i);
					formRow.setHacCod(resultFmVOs[i].getHacCod());
					formRow.setKbn(DataFormatUtils.getKbnString(resultFmVOs[i].getKbn()));
					formRow.setKigBng((!resultFmVOs[i].getHjiHnb().equals(""))? resultFmVOs[i].getHjiHnb() : resultFmVOs[i].getKigBng());
					formRow.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[i].getHacSyoDte()+""));
					formRow.setHacSyoBng(resultFmVOs[i].getHacSyoBng());
					formRow.setGyoBng(resultFmVOs[i].getGyoBng());
					formRow.setHacSuu(DataFormatUtils.setFormatString(resultFmVOs[i].getHacSuu()));
					formRow.setNyoSuu(DataFormatUtils.setFormatString(resultFmVOs[i].getNyoSuu()));
					formRow.setKnuSgn(resultFmVOs[i].getKnuSgn());
					formRow.setNki(DataFormatUtils.setFormatYYMMDD(resultFmVOs[i].getNki()));
					fmForm.addNyukoSuuAdjustVO(resultFmVOs[i]);
				}
				fmForm.setCount(fmForm.getSize());
			}else{
				fmForm.setCount(0);
				fmForm.setSize(NyukoSuuAdjustForm.DEFAULT_ROW_COUNT);
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
	 * ���ɐ�����
	 * 
	 */
	private ActionErrors nyoSuuAdjust (NyukoSuuAdjustForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
		
		for(int i = 0; i < fmForm.getSize();i++){
			NyukoSuuAdjustFormRow formRow = fmForm.getFormRow(i);
			if(!formRow.getExecute()){
				lisFmVO.add(null);	
				continue;
			}
			NyukoSuuAdjustVO fmVO = fmForm.getNyukoSuuAdjustVO(i);
			fmVO.setKnuSgn(formRow.getKnuSgn());
				
			lisFmVO.add(fmVO);
		}
		
		try{
			//�X�V���s
			NyukoSuuAdjustResult[] results = bzDelegate.doNyukoSuuAdjust((NyukoSuuAdjustVO[])lisFmVO.toArray(new NyukoSuuAdjustVO[lisFmVO.size()]));
		
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(NyukoSuuAdjustDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(NyukoSuuAdjustDelegate.LOGICAL_DELETE)||
					   results[i].getDescription().equals(NyukoSuuAdjustDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
					else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){ 
				//���[�T�C���𗧂Ă���A����ȊO�̍s����ʂ������
				for(int i = 0,index = 0; i < results.length;i++,index++){
					if(results[i] != null)
						continue;
					fmForm.removeTableField(index);
					fmForm.removeNyukoSuuAdjustVO(index);
					index--;
				}
				for(int i = 0; i < fmForm.getSize(); i++){
					fmForm.getFormRow(i).setNyoSuu(DataFormatUtils.setFormatString(fmForm.getNyukoSuuAdjustVO(i).getNyoSuu()));
				}
				fmForm.setCount(fmForm.getSize());
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	/** 
	 * �\��t��
	 * @param fmForm
	 */
	public void dataPaste(NyukoSuuAdjustForm fmForm){
		
		final String tab = "\t";
		String data = fmForm.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));
		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);

		
		try {

			for (int i = 0; i < count; i++) {
			
				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					StringTokenizer line = new StringTokenizer(txt, tab);	
		
					if(line.hasMoreTokens()){
									
						String str = line.nextToken().trim();
						
						if(fmForm.getKigBng1_H().equals("")){
							fmForm.setKigBng1_H(str);
							continue;
						}
						if(fmForm.getKigBng2_H().equals("")){
							fmForm.setKigBng2_H(str);					
							continue;
						}
						if(fmForm.getKigBng3_H().equals("")){
							fmForm.setKigBng3_H(str);
							continue;
						}
						if(fmForm.getKigBng4_H().equals("")){
							fmForm.setKigBng4_H(str);
							continue;
						}
						if(fmForm.getKigBng5_H().equals("")){
							fmForm.setKigBng5_H(str);
							continue;
						}
						if(fmForm.getKigBng6_H().equals("")){
							fmForm.setKigBng6_H(str);
							continue;
						}
						if(fmForm.getKigBng7_H().equals("")){
							fmForm.setKigBng7_H(str);
							continue;
						}
						if(fmForm.getKigBng8_H().equals("")){
							fmForm.setKigBng8_H(str);
							continue;
						}
						if(fmForm.getKigBng9_H().equals("")){
							fmForm.setKigBng9_H(str);
							continue;
						}
						if(fmForm.getKigBng10_H().equals("")){
							fmForm.setKigBng10_H(str);
							continue;
						}
					}
				}
			}

		}catch(IOException ex){
			ex.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
