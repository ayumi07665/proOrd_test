/**
* KetSziTblAction  �`�ԕʍ\�����ރ}�X�^�[�����e�i���X�A�N�V�����N���X
*
*	�쐬��    2004/01/30
*	�쐬��    �i�m�h�h�j�X
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*
*/

package com.jds.prodord.master.ketszitbl;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
public class KetSziTblAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  KetSziTblDelegate bzDelegate = new KetSziTblDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		KetSziTblForm fmForm = (KetSziTblForm)form;
		KetSziTblVO fmVO = new KetSziTblVO();
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
			
			fmForm.setKaiSkbCodOptions(kaiList);
			fmForm.setKetCodOptions(logonForm.getKetCodList(),logonForm.getKetNm2List());
			fmForm.setHyoKbn(logonForm.getHyoKbn());
		}
		//�u�N���A�v������
		else if(fmForm.getCommand().equals(KetSziTblForm.COMMAND_CLEAR)){
			fmForm.clearAll();
		fmForm.setUpdatable(false);
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
		}


		//�����敪���Ɖ�
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null)	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
		}
		//�����敪���X�V
		else if(fmForm.getPrcKbn().equals("3")){
			errors = this.update(fmForm,req,res);
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
	private ActionErrors select (KetSziTblForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		KetSziTblVO fmVO = new KetSziTblVO();		
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());

		String[] ket = fmForm.getKetCod();
	
		//�`�ԃR�[�h
		if(fmForm.getKetCod() != null)
		   fmVO.setKetCodList(fmForm.getKetCod());
		
		try{
			//�������s
			KetSziTblVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			
			fmForm.removeKetSziTblVO();
			
			if(resultFmVOs.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVOs.length),KetSziTblForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
				
				for(int i = 0;i < resultFmVOs.length;i++){
					KetSziTblFormRow formRow = fmForm.getFormRow(i);
					formRow.setKetCod(resultFmVOs[i].getKetCod());
					formRow.setKetNm(resultFmVOs[i].getKetNm());
					formRow.setKetNm2(resultFmVOs[i].getKetNm2());
					formRow.setFuksziCodArr(resultFmVOs[i].getFuksziCodArr());
					fmForm.addKetSziTblVO(resultFmVOs[i]);
				}
				fmForm.setCount(fmForm.getSize());
			}else{
				fmForm.setCount(0);
				fmForm.setSize(KetSziTblForm.DEFAULT_ROW_COUNT);
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
	 * �X�V
	 * 
	 */
	private ActionErrors update (KetSziTblForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
		
		for(int i = 0; i < fmForm.getSize();i++){
			KetSziTblVO fmVO = fmForm.getKetSziTblVO(i);
			KetSziTblFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				lisFmVO.add(null);	
				continue;
			}
			fmVO.setFuksziCodString(formRow.getFuksziCod());
				
			lisFmVO.add(fmVO);
		}

		
		try{
			//�X�V���s
			KetSziTblResult[] results = bzDelegate.doUpdate((KetSziTblVO[])lisFmVO.toArray(new KetSziTblVO[lisFmVO.size()]));

		
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(KetSziTblDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(KetSziTblDelegate.LOGICAL_DELETE)||
					   results[i].getDescription().equals(KetSziTblDelegate.NOT_EXIST)){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				   }else if(results[i].getDescription().equals(KetSziTblDelegate.FUKSZICOD_NOT_EXIST)){
				   		errors = new ActionErrors();

						ArrayList errs_fuk = results[i].getMap(KetSziTblResult.KEY_FUKSZICOD);
						String index = "";
				 		//�����ރR�[�h�����݂��Ȃ�
						if(errs_fuk != null){	
							for(int j = 0; j < errs_fuk.size(); j++){
								String err_fuk = (String)errs_fuk.get(j);
								index = err_fuk;//�G���[�̂�����Index
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",String.valueOf(i+1),"�����ރR�[�h("+ index +"�Ԗ�)"));
							}
				   		}	
					}
				}
			}
			if(errors == null){ 
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
					fmForm.getFormRow(i).setCheck_bx(false);
					fmForm.setKetSziTblVO(i,results[i].getVO());
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
