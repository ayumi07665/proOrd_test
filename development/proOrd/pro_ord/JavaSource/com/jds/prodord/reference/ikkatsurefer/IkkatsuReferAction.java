/**
* IkkatsuReferAction  �ꊇ�Ɖ��ʃA�N�V�����N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*       2003/05/06 �i�m�h�h�j�g�c �h�q
* 			�E��v���X�R�����g���ͣ �@�\�ǉ��B
* 		2003/06/16 �i�m�h�h�j���c �Ĕ�
* 			�E�O100����100���Ή��ǉ��B
* 		2003/06/18 �i�m�h�h�j���c �Ĕ�
* 			�E�����ޔ����@�\�ǉ��B
* 		2003/07/01 �i�m�h�h�j�g�c �h�q
* 			�E�����ޔ����̂Ƃ��A�[�i�於�̎擾�ǉ��B
* 		2003/07/31�i�m�h�h�j���c �Ĕ�
* 			�E�v���X�R�����g���́E�����ޔ������s���A�`�F�b�N���ꂽ�s�����łȂ��A
* 			  �S�Ă̍s�̓��͏���ێ�����悤�ɏC���B
* 		2003/09/19�i�m�h�h�j���c �Ĕ�
* 			�E�\�����ڂɃv���X�E������ �V�������敪�P�E�Q��ǉ�
* 			�E�v���X�E������ �����݌v �� ���ɗ݌v�ɕύX
*		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
*		2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
*		2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
*		2004/08/20 (�m�h�h) ����
*			�E�v���X�R�����g���͎��s���A�S�i�ԂɃ`�F�b�N������悤�ɏC��
*		2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�Ŕ[�i�於���擾����悤�ɏC��
*		2005/05/23�i�m�h�h�j�g�c
*			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
*/

package com.jds.prodord.reference.ikkatsurefer;
import com.jds.prodord.common.*;
import com.jds.prodord.indicate.teiansuurefer.*;
import com.jds.prodord.order.prsorder.*;
import com.jds.prodord.print.printorders.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class IkkatsuReferAction extends Action {
	
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  IkkatsuReferDelegate bzDelegate = new IkkatsuReferDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		IkkatsuReferForm fmForm = (IkkatsuReferForm)form;			
		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��
		
		IkkatsuReferLeftFrame leftFrame = new IkkatsuReferLeftFrame();
		IkkatsuReferVO fmVO = new IkkatsuReferVO();
		//�������f�[�^���������p�̃f�[�^���i�[����VO
		PrintOrdersQueryVO poQueryVO = new PrintOrdersQueryVO();
		
		//�Z�b�V���������Đ��Ɖ�w����ʂŃZ�b�g����VO���擾
		TeiansuuReferVO queryVO = (TeiansuuReferVO)session.getAttribute(CommonConst.TEIANSUUREFER_VO);

		fmForm.setNewWindow("0");//����p��ʂ��J�����ǂ����̃t���O��������
		
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

		//�ŏ��ɒ�Đ��Ɖ�w����ʂ��炫���Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			
			//2003/05/12 �v���X������ʂ��炫���Ƃ�
			if(session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT) != null){
				this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
				return mapping.findForward("success");
			}

	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
			fmForm.setQueryDaiKaiSkbCod(logonForm.getDaikaiskbcod());
			fmForm.setQueryKaiSkbCod(logonForm.getKaiskbcod());
			queryVO.setBshCod(logonForm.getBshCod());//2005/02/03 add
			queryVO.setUsrId(logonForm.getUser());//2005/05/23 add
			fmForm.setHyoKbn(logonForm.getHyoKbn());//2011/05/26 add
			
			errors = this.find(fmForm,leftFrame,queryVO,req,res);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));	
			}
			//�G���[����
			if(errors != null){
				super.saveErrors(req,errors);
	    		return mapping.findForward("failure1");
			}
		}
		
		//�{�^���������w��
		else if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_HACHUSHIJI)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.hacShiji(fmForm,leftFrame,req,res);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_HACHUSHIJI);
				return mapping.findForward("next");
			}
		}
		//�����w������
		else if(fmForm.getCommand().equals(IkkatsuReferForm.SUCCESS_HACHUSHIJI)){
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.indicate.end"));
		}
		
		//�{�^�����`�[���s
		else if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_DENPYOHAKKOU)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.dnpHakkou(fmForm,leftFrame,poQueryVO,req,res);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				
				//poQueryVO�ɑ�\��ЂƉ�Ў��ʃR�[�h���Z�b�g
				poQueryVO.setDaiKaiSkbCod(queryVO.getDaiKaiSkbCod());
				poQueryVO.setKaiSkbCod(queryVO.getKaiSkbCod());
				poQueryVO.setKaiSkbCod_arr(queryVO.getKaiSkbCod_arr());
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//�������f�[�^�����������Z�b�g���ꂽpoQueryVO���Z�b�V�����Ɋi�[
				
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_DENPYOHAKKOU);
				return mapping.findForward("next");
			}
		}
		//�`�[���s����
		else if(fmForm.getCommand().equals(IkkatsuReferForm.SUCCESS_DENPYOHAKKOU)){
			fmForm.setNewWindow("1");
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
		}
		//��100��
		else if(fmForm.getCommand().equals(IkkatsuReferForm.NEXT)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,1);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//�O100��
		else if(fmForm.getCommand().equals(IkkatsuReferForm.PREV)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,-1);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//�O100����100������
		else if(fmForm.getCommand().equals(IkkatsuReferForm.SUCCESS_PREVNEXT)){
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
		}
		
		//�{�^�����v���X�R�����g����
		if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_PRSCOMMENT)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			errors = this.prsComment(fmForm,leftFrame,fmVO,req,res);

			if(errors == null){	//��������
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());
				return mapping.findForward("next_prs");	
			}
		}
		//�{�^���������ޔ���
		if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_FKUSHIZAIHACHU)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			errors = this.fukHachu(fmForm,leftFrame,fmVO,req,res);

			if(errors == null){	//��������
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());
				return mapping.findForward("next_prs");	
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
	 * ����
	 * 
	 */
	private ActionErrors find (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									TeiansuuReferVO queryVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;

		try{			
			//����������
			fmForm.removeVosList();
			fmForm.removeIkkatsuReferVO();
			fmForm.removeAllRow();
			leftFrame.removeAllRow();
			
			//�������s
			IkkatsuReferVO[] resultFmVOs = bzDelegate.doFind(queryVO);

			ArrayList vosList = new ArrayList();//�y�[�W�P�ʂ�VO�̃��X�g���i�[����
			
			int pageCount = 0;//�y�[�W��
			int max = IkkatsuReferForm.MAX_ROW_COUNT;//�ő�s��
			
			//���ʂ�1�y�[�W���������ꍇ
			if(resultFmVOs.length <= max){
				fmForm.setSize(resultFmVOs.length);
				leftFrame.setSize(resultFmVOs.length);
				fmForm.setCount(resultFmVOs.length);
				pageCount++;
			}
			//���ʂ�1�y�[�W�𒴂����ꍇ
			else{
				fmForm.setSize(max);
				leftFrame.setSize(max);
				fmForm.setCount(max);
				pageCount = resultFmVOs.length / max;
				//���[�ȍs���o����1�y�[�W����
				if(resultFmVOs.length % max != 0)
					pageCount++;
			}

			fmForm.clearTableField();
			leftFrame.clearTableField();
			
			for(int i = 0; i<pageCount; i++){//�y�[�W�� ArrayList��new���Ă���
				vosList.add(new java.util.ArrayList());
			}

			int cnt = 0;

			for(int i = 0; i < pageCount; i++){
				ArrayList vos = (ArrayList)vosList.get(i);
				
				for(int j = cnt; j < max + cnt && j < resultFmVOs.length; j++){
					if(i == 0){
						IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(j);
				   	 	IkkatsuReferLeftRow formRow_L = leftFrame.getFormRow_L(j);
				    	//�������ʂ��t�H�[���ɃZ�b�g
				   		this.setData(fmForm,formRow_R,formRow_L,resultFmVOs[j]);
						fmForm.addIkkatsuReferVO(resultFmVOs[j]);
					}
					resultFmVOs[j].setQueryKaiSkbCod(fmForm.getQueryKaiSkbCod());//VO�Ƀ��[�U�[�̉�Ў��ʃR�[�h���Z�b�g���Ă���
					vos.add(resultFmVOs[j]);
				}
				cnt += max;
			}
			fmForm.setVosList(vosList);//�S�y�[�W����VO�̃��X�g
			fmForm.setPageCount(pageCount);//�y�[�W��
			fmForm.setCurrentPage(0);//���݂̃y�[�W
			fmForm.setReferredMaxPage(0);//�Q�Ƃ��ꂽ�ő�y�[�W
			fmForm.setAllRowCount(resultFmVOs.length);//�S�y�[�W�̌���


			if(resultFmVOs.length == 0){
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist_prepage"));
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
	 * �����w��
	 * 
	 */
	private ActionErrors hacShiji (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;
		
		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//���݂̃y�[�W��VO�����o��
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1()){
				lisFmVO.add(null);
				if(formRow_R.getCheck_prs2())
					check_prs2_index.add(i+"");
				continue;
			}
				
			IkkatsuReferVO fmVO = currentVOs[i];

			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			
			lisFmVO.add(fmVO);	
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);

		ArrayList allVos = fmForm.getAllVos();
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_prs1()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}
		
		try{
			//�����w�����s
			IkkatsuReferResult[] results = bzDelegate.doHacShiji((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]));	
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//������R�[�h�����݂��Ȃ�
                    if(results[i].getDescription().equals(IkkatsuReferDelegate.HACSAKI_NOT_EXIST)){
                    	String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"�y�[�W��"+s[1],"������R�[�h"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				//�o�����[�I�u�W�F�N�g���̂��i�[			
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;
						
					fmForm.setIkkatsuReferVO_all(i, results[i].getVO());
					
					if(page * max <= i && i < (page + 1) * max){
						//�����σT�C�����Z�b�g������
						leftFrame.getFormRow_L(i % max).setHacZmiSgn(this.getHacZmiSgnString(results[i].getVO().getHacZmiSgn()));
						//�v���X������������������
						this.setPrsHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
					}
				}
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
	 * �`�[���s
	 * 
	 */
	private ActionErrors dnpHakkou (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									PrintOrdersQueryVO poQueryVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;
		
		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//���݂̃y�[�W��VO�����o��
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
		ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");

			
			if(!formRow_R.getCheck_prs2()){
				lisFmVO.add(null);	
				if(formRow_R.getCheck_prs1())
					check_prs1_index.add(i+"");
				continue;
			}
				
			IkkatsuReferVO fmVO = currentVOs[i];

			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());

			lisFmVO.add(fmVO);	
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);
		
		ArrayList allVos = fmForm.getAllVos();
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_prs2()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}

		try{
			//�`�[���s���s
			IkkatsuReferResult[] results = bzDelegate.doDnpHakkou((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]));	
			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//������R�[�h�����݂��Ȃ�
                    if(results[i].getDescription().equals(IkkatsuReferDelegate.HACSAKI_NOT_EXIST)){
						String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"�y�[�W��"+s[1],"������R�[�h"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				//�o�����[�I�u�W�F�N�g���̂��i�[				
				boolean b = false;
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;
						
					IkkatsuReferVO fmVO = results[i].getVO();
					fmForm.setIkkatsuReferVO_all(i, fmVO);
					
					if(page * max <= i && i < (page + 1) * max){
						//�����σT�C�����Z�b�g������
						leftFrame.getFormRow_L(i % max).setHacZmiSgn(this.getHacZmiSgnString(fmVO.getHacZmiSgn()));
						//�v���X������������������
						this.setPrsHacJoho(fmForm.getFormRow_R(i % max),fmVO);
					}
					//�inull�łȂ��ŏ��́jresults���珈���񐔂���o����poQueryVO�ɃZ�b�g
					if(results[i] != null && !b){
						poQueryVO.setSyrSuu(results[i].getSyrSuu());
						b = true;
					}
				}

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
	 * �O100����100��
	 * 
	 */
	private ActionErrors dispPre_NxtData (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res, int addIndex){

		ActionErrors errors = null;
		IkkatsuReferVO[] resultVOs = null;//�Oor���y�[�W��VO���i�[
		IkkatsuReferVO[] currentVOs = null;//���݂̃y�[�W��VO���i�[
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		this.setCurrentVOs(currentVOs, fmForm, leftFrame);//2003/07/31 add

		//�Ώۃy�[�W�̃f�[�^���擾
		try{
			ArrayList check_prs1_index = new ArrayList();
	   	 	ArrayList check_prs2_index = new ArrayList();
	    	ArrayList check_fuk1_index = new ArrayList();
			ArrayList check_prsHacSuu_index = new ArrayList();
			
			//�Oor���y�[�W��VO���擾
			resultVOs = fmForm.getVosList(fmForm.getCurrentPage() + addIndex);
			
			//����������
			fmForm.removeIkkatsuReferVO();
			fmForm.removeAllRow();
			leftFrame.removeAllRow();
					
			//���ʂ��t�H�[���ɃZ�b�g
			fmForm.setSize(resultVOs.length);
			leftFrame.setSize(resultVOs.length);
			fmForm.setCount(resultVOs.length);
			fmForm.clearTableField();
			leftFrame.clearTableField();
			
			for(int i = 0; i < resultVOs.length; i++){
				IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
				IkkatsuReferLeftRow formRow_L = leftFrame.getFormRow_L(i);
				
				this.setData(fmForm,formRow_R,formRow_L,resultVOs[i]);
				
				//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
				if(formRow_R.getCheck_prs1())
					check_prs1_index.add(i+"");
				if(formRow_R.getCheck_prs2())
					check_prs2_index.add(i+"");
				if(formRow_R.getCheck_fuk1())
					check_fuk1_index.add(i+"");
				if(formRow_R.getCheck_prsHacSuu())
					check_prsHacSuu_index.add(i+"");
			}
			fmForm.setCurrentPage(fmForm.getCurrentPage() + addIndex);//���݂̃y�[�W
			fmForm.setReferredMaxPage(fmForm.getCurrentPage());//�Q�Ƃ��ꂽ�ő�y�[�W
			fmForm.setIkkatsuReferVO(new ArrayList(Arrays.asList(resultVOs)));//���݂�VO
			fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);//�`�F�b�N�{�b�N�X�̃C���f�b�N�X���Z�b�g
			
		}catch(IndexOutOfBoundsException e){
			resultVOs = new IkkatsuReferVO[0];
		}		
		if(resultVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
		}
			
		return errors;			
			
	}

	/*** 2003/05/08 *************************************************************************
	 * 		�v���X�R�����g����	                    							        	*
	 * 												             							*
	 ****************************************************************************************/
	private ActionErrors prsComment (IkkatsuReferForm fmForm,IkkatsuReferLeftFrame leftFrame,
									IkkatsuReferVO fmVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;

		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//���݂̃y�[�W��VO�����o��
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		this.setCurrentVOs(currentVOs, fmForm, leftFrame);//2003/07/31 add

		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(formRow_R.getCheck_prs1())
				check_prs1_index.add(i+"");
			if(formRow_R.getCheck_prs2())
				check_prs2_index.add(i+"");
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			if(formRow_R.getCheck_prsHacSuu())
				check_prsHacSuu_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1() && !formRow_R.getCheck_prs2()){
				lisFmVO.add(null);	
				continue;
			}
				
			IkkatsuReferVO vo = currentVOs[i];

			vo.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			vo.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			vo.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());

			vo.setCheck_prs1(formRow_R.getCheck_prs1());
			vo.setCheck_prs2(formRow_R.getCheck_prs2());
			vo.setCheck_fuk1(formRow_R.getCheck_fuk1());
			vo.setCheck_prsHacSuu(formRow_R.getCheck_prsHacSuu());
			
			lisFmVO.add(vo);	
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);
		
		
		ArrayList allVos = fmForm.getAllVos();
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_prs1() && !vo.getCheck_prs2()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}
		
		try{
			//�G���[�`�F�b�N���s
			IkkatsuReferResult[] results = bzDelegate.doCheck((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]));	
			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//������R�[�h�����݂��Ȃ�
                    if(results[i].getDescription().equals(IkkatsuReferDelegate.HACSAKI_NOT_EXIST)){
						String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"�y�[�W��"+s[1],"������R�[�h"));
                    }
				}
			}
			if(errors == null){
				 //�o�����[�I�u�W�F�N�g���̂��i�[
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
					fmForm.setIkkatsuReferVO_all(i, results[i].getVO());
				}
				//�v���X������ʂɓn���f�[�^�Z�b�g				
				fmVO.setPrsVOs(this.setPrsOrderData(results));	
			}
			
			
			
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}
	
	/*** 2003/06/17 *************************************************************************
	 * 		�����ޔ���                    							       				 	*
	 * 												             							*
	 ****************************************************************************************/
	private ActionErrors fukHachu (IkkatsuReferForm fmForm,IkkatsuReferLeftFrame leftFrame,
									IkkatsuReferVO fmVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;

		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//���݂̃y�[�W��VO�����o��
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		this.setCurrentVOs(currentVOs, fmForm, leftFrame);//2003/07/31 add

		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(formRow_R.getCheck_prs1())
				check_prs1_index.add(i+"");
			if(formRow_R.getCheck_prs2())
				check_prs2_index.add(i+"");
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			if(formRow_R.getCheck_prsHacSuu())
				check_prsHacSuu_index.add(i+"");
			
			if(!formRow_R.getCheck_fuk1()){
				lisFmVO.add(null);	
				continue;
			}
				
			IkkatsuReferVO vo = currentVOs[i];

			vo.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			vo.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			vo.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());

			vo.setCheck_prs1(formRow_R.getCheck_prs1());
			vo.setCheck_prs2(formRow_R.getCheck_prs2());
			vo.setCheck_fuk1(formRow_R.getCheck_fuk1());
			vo.setCheck_prsHacSuu(formRow_R.getCheck_prsHacSuu());
			
			lisFmVO.add(vo);	
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);
		
		
		ArrayList allVos = fmForm.getAllVos();

		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_fuk1()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}
		
		try{
			if(errors == null){
				for(int i = 0; i < allVos.size(); i++){
					if(allVos.get(i) == null)
						continue;
					fmForm.setIkkatsuReferVO_all(i, (IkkatsuReferVO)allVos.get(i));
				}
				//�v���X�����ޔ�����ʂɓn���f�[�^�������E�Z�b�g				
				fmVO.setPrsVOs(this.setFukOrderData(bzDelegate.doFukHachu((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]))));
			}
			
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}

	
//---------------------------------------------------------------------------------------------------	
	
	/**
	 *  VO�̃f�[�^��Form�ɃZ�b�g���郁�\�b�h
	 * 
	 */
	private void setData(IkkatsuReferForm fmForm, IkkatsuReferFormRow formRow_R, IkkatsuReferLeftRow formRow_L, 
				IkkatsuReferVO resultVO){
		
		//�L�[����
	    formRow_R.setDaiKaiSkbCod(resultVO.getDaiKaiSkbCod());
	    formRow_L.setDaiKaiSkbCod(resultVO.getDaiKaiSkbCod());
	    formRow_R.setKaiSkbCod(resultVO.getKaiSkbCod());
	    formRow_L.setKaiSkbCod(resultVO.getKaiSkbCod());
	    formRow_R.setKigBng(resultVO.getKigBng());
	    formRow_L.setKigBng(resultVO.getKigBng());
	    
	    //�i�ԃ}�X�^�[
	    formRow_L.setKetCod(resultVO.getKetCod());
	    formRow_L.setKetNm(resultVO.getKetNm());
	    formRow_L.setHjiHnb(resultVO.getHjiHnb());//�\���i�Ԃ��Z�b�g
	    formRow_L.setArtKj(resultVO.getArtKj());
	    formRow_L.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultVO.getHbiDte()));
	    formRow_L.setTitKj(resultVO.getTitKj());
	    formRow_L.setTomRak(resultVO.getTomRak());
		formRow_L.setZikTik(resultVO.getZikTik()); //2004.04.21 add
	    formRow_R.setPrsMkrCod(resultVO.getPrsMkrCod());
	    
	    //���������e�[�u��
	    formRow_L.setHacZmiSgn(this.getHacZmiSgnString(resultVO.getHacZmiSgn()));
	    			    
	    //�݌Ƀe�[�u��
	    if(resultVO.getExsitZai01()){
		    formRow_L.setAziSuu(resultVO.getAziSuu());
			formRow_L.setTnaSekZan(resultVO.getTnaSekZan());
			formRow_L.setJucZan(resultVO.getJucZan());
			formRow_L.setMhikSuu(resultVO.getMhikSuu());
			formRow_L.setRsvSum(resultVO.getRsvSum());
			
			formRow_R.setTodUriSuu(resultVO.getTodUriSuu());
			formRow_R.setB1dUri(resultVO.getB1dUri());
			formRow_R.setB2dUri(resultVO.getB2dUri());
			formRow_R.setB3dUri(resultVO.getB3dUri());
			formRow_R.setB4dUri(resultVO.getB4dUri());
			formRow_R.setB5dUri(resultVO.getB5dUri());
			formRow_R.setTowUri(resultVO.getTowUri());
			formRow_R.setB1wUri(resultVO.getB1wUri());
			formRow_R.setB2wUri(resultVO.getB2wUri());
			formRow_R.setTomUri(resultVO.getTomUri());
			formRow_R.setB1mUri(resultVO.getB1mUri());
			formRow_R.setB2mUri(resultVO.getB2mUri());
			formRow_R.setTomHpn(resultVO.getTomHpn());
			formRow_R.setB1mHpn(resultVO.getB1mHpn());
			formRow_R.setB2mHpn(resultVO.getB2mHpn());
			formRow_R.setTomTna(resultVO.getTomTna());
			formRow_R.setB1mTna(resultVO.getB1mTna());
			formRow_R.setB2mTna(resultVO.getB2mTna());
			
			formRow_L.setAvgUri(resultVO.getAvgUri());
			
			formRow_R.setPrsMnyKei(resultVO.getPrsMnyKei());
			formRow_R.setPrsHacRui(resultVO.getPrsHacRui());
			formRow_R.setFukMnyKei(resultVO.getFukMnyKei());
			formRow_R.setFukHacRui(resultVO.getFukHacRui());
			formRow_R.setPrsNyoKei(resultVO.getPrsNyoKei());
			formRow_R.setFukNyoKei(resultVO.getFukNyoKei());
			formRow_R.setFukZaiSuu(resultVO.getFukZaiSuu());//���̓`�F�b�N�Ŏg������formRow_R�ɂ��Z�b�g
			
			formRow_L.setFukHacRui(resultVO.getFukHacRui());
			formRow_L.setFukNyoKei(resultVO.getFukNyoKei());
			formRow_L.setFukZaiSuu(resultVO.getFukZaiSuu());
			
	    }
	    
		//���������e�[�u���i�v���X�j
		if(resultVO.getExsitHac02Prs()){
			formRow_R.setPrsHacSuu1(resultVO.getPrsHacSuu1());
			formRow_R.setPrsHacNyo1(resultVO.getPrsHacNyo1());
			if(!resultVO.getPrsHacNki1().equals(""))
				formRow_R.setPrsHacNki1(DataFormatUtils.setFormatYYMMDD(resultVO.getPrsHacNki1()));
			formRow_R.setPrsKbn1(DataFormatUtils.getKbnString(resultVO.getPrsKbn1()));
			formRow_R.setPrsHacSuu2(resultVO.getPrsHacSuu2());
			formRow_R.setPrsHacNyo2(resultVO.getPrsHacNyo2());
			if(!resultVO.getPrsHacNki2().equals(""))
				formRow_R.setPrsHacNki2(DataFormatUtils.setFormatYYMMDD(resultVO.getPrsHacNki2()));
			formRow_R.setPrsKbn2(DataFormatUtils.getKbnString(resultVO.getPrsKbn2()));
		}
		//���������e�[�u���i�����ށj
		if(resultVO.getExsitHac02Fuk()){
			formRow_R.setFukHacSuu1(resultVO.getFukHacSuu1());
			formRow_L.setFukHacSuu1(resultVO.getFukHacSuu1());
			formRow_R.setFukHacNyo1(resultVO.getFukHacNyo1());
			if(!resultVO.getFukHacNki1().equals(""))
				formRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki1()));
			formRow_R.setFukKbn1(DataFormatUtils.getKbnString(resultVO.getFukKbn1()));
			formRow_R.setFukHacSuu2(resultVO.getFukHacSuu2());
			formRow_R.setFukHacNyo2(resultVO.getFukHacNyo2());
			if(!resultVO.getFukHacNki2().equals(""))
				formRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki2()));
			formRow_R.setFukKbn2(DataFormatUtils.getKbnString(resultVO.getFukKbn2()));
		}
		//�����ރ}�X�^�[
		if(resultVO.getExsitFuk01()){
			formRow_R.setFukMkrCod(resultVO.getFukSziHacSaki());
		}
		//���o����-------------------------------------------
		if(resultVO.getExsitZai01() && resultVO.getExsitMst05() && resultVO.getExsitMst04()){
			formRow_L.setTeianSuu(resultVO.getTeianSuu());
			formRow_L.setFskZaiSuu(resultVO.getFskZaiSuu());
			formRow_R.setPrsHacSuu(resultVO.getPrsHacSuu());
			formRow_R.setFukHacSuu(resultVO.getFukHacSuu());

			formRow_R.setCheck_prs1(resultVO.getCheck_prs1());
			formRow_R.setCheck_prs2(resultVO.getCheck_prs2());
			formRow_R.setCheck_fuk1(resultVO.getCheck_fuk1());
			formRow_R.setCheck_flg(resultVO.getCheck_flg());
			formRow_R.setCheck_prsHacSuu(resultVO.getCheck_prsHacSuu());
			formRow_R.setCheck_sgn(resultVO.getCheck_sgn());

		}
		if(resultVO.getPrsNki() != 0){
			formRow_R.setPrsNkiYear(resultVO.getPrsNkiYear());
			formRow_R.setPrsNkiMonth(resultVO.getPrsNkiMonth());
			formRow_R.setPrsNkiDay(resultVO.getPrsNkiDay());
		}
		if(resultVO.getFukNki() != 0){
			formRow_R.setFukNkiYear(resultVO.getFukNkiYear());
			formRow_R.setFukNkiMonth(resultVO.getFukNkiMonth());
			formRow_R.setFukNkiDay(resultVO.getFukNkiDay());
		}
		if(resultVO.getExsitZai01())
			formRow_L.setZaiNisuu(resultVO.getZaiNisuu());
		formRow_R.setChoksoKbn(resultVO.getChoksoKbn());
		
		resultVO.setExisted();
		
		/* �i�ԃ}�X�^�[		: FTBHIN01 | �݌Ƀe�[�u��			: FTBZAI01	| ���������e�[�u�� : FTBHAC02 
			�݌ɓ����}�X�^�[	: FTBMST04 | �`�ԃ����N�ʏ����}�X�^�[	: FTBMST05	| �����ރ}�X�^�[   : FTBFUK01  
			�����ރp�^�[���}�X�^�[  : FTBFUK02			*/    
				    
	}
	
	/**
	 *  �`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ������\�b�h
	 * 
	 */
	private void setCheckbox_checked(IkkatsuReferForm fmForm){
		
		ArrayList check_prs1_index = fmForm.getCheck_prs1_index();
	    ArrayList check_prs2_index = fmForm.getCheck_prs2_index();
	    ArrayList check_fuk1_index = fmForm.getCheck_fuk1_index();
	    ArrayList check_prsHacSuu_index = fmForm.getCheck_prsHacSuu_index();
	    int index_prs1 = 0;
	    int index_prs2 = 0;
	    int index_fuk1 = 0;
	    int index_prsHacSuu = 0;
	    
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			if(check_prs1_index.size() != index_prs1){
				if(i == Integer.parseInt(check_prs1_index.get(index_prs1)+"")){
					formRow_R.setCheck_prs1(true);
					index_prs1++;
				}
			}
			if(check_prs2_index.size() != index_prs2){
				if(i == Integer.parseInt(check_prs2_index.get(index_prs2)+"")){
					formRow_R.setCheck_prs2(true);
					index_prs2++;
				}
			}
			if(check_fuk1_index.size() != index_fuk1){
				if(i == Integer.parseInt(check_fuk1_index.get(index_fuk1)+"")){
					formRow_R.setCheck_fuk1(true);
					index_fuk1++;
				}
			}
			if(check_prsHacSuu_index.size() != index_prsHacSuu){
				if(i == Integer.parseInt(check_prsHacSuu_index.get(index_prsHacSuu)+"")){
					formRow_R.setCheck_prsHacSuu(true);
					index_prsHacSuu++;
				}
			}
		
		}
		
	}
	
	
	/** �����σT�C���̕\���������Ԃ� **/
	private String getHacZmiSgnString(int sgn){
		String haczmisgn = "";
		switch(sgn){
			case 0:
				haczmisgn = IkkatsuReferLeftFrame.MIHACHU;
				break;
			case 1:
				haczmisgn = IkkatsuReferLeftFrame.HACZMI;
				break;
			case 2:
				haczmisgn = IkkatsuReferLeftFrame.SYRZMI;
				break;
		}
		return haczmisgn;
	}
	
	/** �v���X���������Z�b�g���Ȃ��� **/
	public void setPrsHacJoho(IkkatsuReferFormRow formRow_R,IkkatsuReferVO fmVO){
		formRow_R.setPrsHacSuu1(fmVO.getPrsHacSuu1());
		formRow_R.setPrsHacNyo1(fmVO.getPrsHacNyo1());
		if(!fmVO.getPrsHacNki1().equals(""))
			formRow_R.setPrsHacNki1(DataFormatUtils.setFormatYYMMDD(fmVO.getPrsHacNki1()));
		else
			formRow_R.setPrsHacNki1("");
		formRow_R.setPrsKbn1(DataFormatUtils.getKbnString(fmVO.getPrsKbn1()));
		
		formRow_R.setPrsHacSuu2(fmVO.getPrsHacSuu2());
		formRow_R.setPrsHacNyo2(fmVO.getPrsHacNyo2());
		if(!fmVO.getPrsHacNki2().equals(""))
			formRow_R.setPrsHacNki2(DataFormatUtils.setFormatYYMMDD(fmVO.getPrsHacNki2()));
		else
			formRow_R.setPrsHacNki2("");
		formRow_R.setPrsKbn2(DataFormatUtils.getKbnString(fmVO.getPrsKbn2()));
	}
	
	/** �G���[�̂������y�[�W�E�s�Ԃ��v�Z���ĕԂ� **/
	public String[] getErrorIndex(int index, int max){
		int page = (index+1) / max;
		int _index = (index+1) % max;
		String[] str = {page+1+"",_index+""};
		return str;
	}
//--------------------------------------------------------------------------------------------------


	/** �v���X������ʂɓn���f�[�^���Z�b�g�B 2003/05/08  2003/06/16 �C���i�O100����100���Ή��j**/
	public PrsOrderVO[] setPrsOrderData(IkkatsuReferResult[] results){

		ArrayList prsVO_arr = new ArrayList();
		boolean flg = false;
		for(int i = 0; i < results.length;i++){
			if(results[i] == null)
				continue;
			IkkatsuReferVO fmVO = results[i].getVO();
			PrsOrderVO prsVO = new PrsOrderVO();
			
			//�i�ԏ��
			prsVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			prsVO.setKaiSkbCod(fmVO.getKaiSkbCod());
			prsVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
			prsVO.setUsrId(fmVO.getUsrId());//2005.05.23 add
			prsVO.setKigBng(fmVO.getKigBng());
			prsVO.setHjiHnb(fmVO.getHjiHnb());
			prsVO.setArtKj(fmVO.getArtKj());
			prsVO.setHbiDte(fmVO.getHbiDte());
			prsVO.setKetCod(fmVO.getKetCod());
			prsVO.setKetNm(fmVO.getKetNm());
			prsVO.setTitKj(fmVO.getTitKj());
			prsVO.setTomRak(fmVO.getTomRak());
			prsVO.setZikTik(fmVO.getZikTik()); //2004.04.21 add
			prsVO.setKbn("0");
			prsVO.setSetSuu(fmVO.getSetSuu());
			prsVO.setSyrZmiSgn_prs(fmVO.getHacZmiSgn());
			prsVO.setPrsHacSuu(fmVO.getPrsHacSuu());
			prsVO.setPrsNki(fmVO.getPrsNki());
			prsVO.setPrsMkrCod(fmVO.getPrsMkrCod());
			prsVO.setChoksoKbn("0");
			prsVO.setComment("");
			prsVO.setHacNm(fmVO.getHacNm());
			prsVO.setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//2005/02/03 add
			// 2004/08/20 upd �S�i�ԂɃ`�F�b�N������
			prsVO.setCheck_prs1(true);

			
			//�v���X�������
			prsVO.setPrsMnyKei(fmVO.getPrsMnyKei());
			prsVO.setPrsHacRui(fmVO.getPrsHacRui());
			prsVO.setPrsNyoKei(fmVO.getPrsNyoKei());
			prsVO.setPrsHacSuu1(fmVO.getPrsHacSuu1());
			prsVO.setPrsHacSuu2(fmVO.getPrsHacSuu2());
			prsVO.setPrsHacNyo1(fmVO.getPrsHacNyo1());
			prsVO.setPrsHacNyo2(fmVO.getPrsHacNyo2());
			prsVO.setPrsHacNki1(fmVO.getPrsHacNki1());
			prsVO.setPrsHacNki2(fmVO.getPrsHacNki2());
			prsVO.setPrsKbn1(fmVO.getPrsKbn1());
			prsVO.setPrsKbn2(fmVO.getPrsKbn2());
			
			//������	�������
			prsVO.setFukMnyKei(fmVO.getFukMnyKei());
			prsVO.setFukHacRui(fmVO.getFukHacRui());
			prsVO.setFukNyoKei(fmVO.getFukNyoKei());
			prsVO.setFukHacSuu1(fmVO.getFukHacSuu1());
			prsVO.setFukHacSuu2(fmVO.getFukHacSuu2());
			prsVO.setFukHacNki1(fmVO.getFukHacNki1());
			prsVO.setFukHacNki2(fmVO.getFukHacNki2());
			prsVO.setFukZaiSuu(fmVO.getFukZaiSuu());
			prsVO.setFukKbn1(fmVO.getFukKbn1());
			prsVO.setFukKbn2(fmVO.getFukKbn2());
			
			//�����ޖ���
			prsVO.setFukSziNm(fmVO.getFukSziNm());
			//�����ރp�^�[���R�[�h
			prsVO.setFukSziPtn(fmVO.getFukSziPtn());
			//�����ރR�[�h
			prsVO.setFukSziCod(fmVO.getFukSziCod());
			//��s�t���O
			prsVO.setBasedRowFlg(fmVO.getBasedRowFlg());
			//���ރR�[�h
			prsVO.setBunCod(fmVO.getBunCod());
			
			//�����ރR�[�h
			prsVO.setFukSziCod_arr(fmVO.getFukSziCod_arr());
			//���ރR�[�h
			prsVO.setBunCod_arr(fmVO.getBunCod_arr());
			
			//�t���O
			prsVO.setExsitHin01(true);
			prsVO.setExsitHac02Prs(true);
			prsVO.setExsitHac02Fuk(true);
			prsVO.setExsitZai01(true);
			
			//Index
			prsVO.setIndex(i);

			if(!flg){
				//�J�ڌ��y�[�W
				prsVO.setPre_page(PrsOrderForm.IkkatsuRefer);
				prsVO.setPrs_FukSgn(PrsOrderForm.PRSHACHU);
				flg = true;
			}
						
			prsVO_arr.add(prsVO);
		}
		return (PrsOrderVO[])prsVO_arr.toArray(new PrsOrderVO[prsVO_arr.size()]);
	}
	
	/** �����ޔ�����ʂɓn���f�[�^���Z�b�g 2003/06/16  **/
	public PrsOrderVO[] setFukOrderData(IkkatsuReferVO[] fmVOs){
		ArrayList prsVO_arr = new ArrayList();
		IkkatsuReferVO fmVO_base = new IkkatsuReferVO();
		boolean flg = false;
		int index = 0;
		for(int i = 0; i < fmVOs.length;i++){
			if(fmVOs[i] == null)
				continue;
			IkkatsuReferVO fmVO = fmVOs[i];
			
			PrsOrderVO prsVO = new PrsOrderVO();
			
			if(fmVO.getBasedRowFlg().equals("1")){//��s�t���O="1"�̂��̂��i�[
				fmVO_base = fmVOs[i];
				prsVO.setIndex(index);//��Đ��Ɖ��ʂł�Index���Z�b�g
				index++;
			}
			
			//�i�ԏ��
			prsVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			prsVO.setKaiSkbCod(fmVO.getKaiSkbCod());
			prsVO.setQueryKaiSkbCod(fmVO_base.getQueryKaiSkbCod());
			prsVO.setUsrId(fmVO.getUsrId());//2005/05/25 add
			prsVO.setKigBng(fmVO_base.getKigBng());
			prsVO.setHjiHnb(fmVO_base.getHjiHnb());
			prsVO.setArtKj(fmVO_base.getArtKj());
			prsVO.setHbiDte(fmVO_base.getHbiDte());
			prsVO.setKetCod(fmVO_base.getKetCod());
			prsVO.setKetNm(fmVO_base.getKetNm());
			prsVO.setTitKj(fmVO_base.getTitKj());
			prsVO.setTomRak(fmVO_base.getTomRak());
			prsVO.setZikTik(fmVO_base.getZikTik()); //2004.04.21 add
			prsVO.setKbn("0");
			prsVO.setSetSuu(fmVO_base.getSetSuu());
			prsVO.setSyrZmiSgn_prs(fmVO_base.getHacZmiSgn());
			prsVO.setPrsHacSuu(fmVO.getFukHacSuu());
			prsVO.setPrsNki(fmVO_base.getFukNki());
			prsVO.setPrsMkrCod(fmVO.getFukSziHacSaki());
			prsVO.setChoksoKbn("0");
			prsVO.setComment("");
			prsVO.setHacNm(fmVO.getHacNm());
			prsVO.setNhnMei(fmVO_base.getNhnMei());//2003/07/01 �[�i�於�擾
			
			//�v���X�������
			prsVO.setPrsMnyKei(fmVO_base.getPrsMnyKei());
			prsVO.setPrsHacRui(fmVO_base.getPrsHacRui());
			prsVO.setPrsNyoKei(fmVO_base.getPrsNyoKei());
			prsVO.setPrsHacSuu1(fmVO_base.getPrsHacSuu1());
			prsVO.setPrsHacSuu2(fmVO_base.getPrsHacSuu2());
			prsVO.setPrsHacNyo1(fmVO_base.getPrsHacNyo1());
			prsVO.setPrsHacNyo2(fmVO_base.getPrsHacNyo2());
			prsVO.setPrsHacNki1(fmVO_base.getPrsHacNki1());
			prsVO.setPrsHacNki2(fmVO_base.getPrsHacNki2());
			prsVO.setPrsKbn1(fmVO_base.getPrsKbn1());
			prsVO.setPrsKbn2(fmVO_base.getPrsKbn2());
			
			//������	�������
			prsVO.setFukMnyKei(fmVO_base.getFukMnyKei());
			prsVO.setFukHacRui(fmVO_base.getFukHacRui());
			prsVO.setFukNyoKei(fmVO_base.getFukNyoKei());
			prsVO.setFukHacSuu1(fmVO.getFukHacSuu1());
			prsVO.setFukHacSuu2(fmVO.getFukHacSuu2());
			prsVO.setFukHacNki1(fmVO.getFukHacNki1());
			prsVO.setFukHacNki2(fmVO.getFukHacNki2());
			prsVO.setFukKbn1(fmVO.getFukKbn1());
			prsVO.setFukKbn2(fmVO.getFukKbn2());
			prsVO.setFukZaiSuu(fmVO_base.getFukZaiSuu());
			
			//�����ޔ����恕�����ޖ���
			prsVO.setFukSziNm(fmVO.getFukSziNm());
			//�����ރp�^�[���R�[�h
			prsVO.setFukSziPtn(fmVO_base.getFukSziPtn());
			//�����ރR�[�h
			prsVO.setFukSziCod(fmVO.getFukSziCod());
			//��s�t���O
			prsVO.setBasedRowFlg(fmVO.getBasedRowFlg());
			//���ރR�[�h
			prsVO.setBunCod(fmVO.getBunCod());
			
			//�t���O
			prsVO.setExsitHin01(true);
			prsVO.setExsitHac02Prs(true);
			prsVO.setExsitHac02Fuk(true);
			prsVO.setExsitZai01(true);
			
			if(!flg){
				//�J�ڌ��y�[�W
				prsVO.setPre_page(PrsOrderForm.IkkatsuRefer);
				prsVO.setPrs_FukSgn(PrsOrderForm.FUKHACHU);
				flg = true;
			}
						
			prsVO_arr.add(prsVO);
		}
		return (PrsOrderVO[])prsVO_arr.toArray(new PrsOrderVO[prsVO_arr.size()]);
	}
	
	/** �ύX����Ă���\���̂��鍀�ڂ�VO�ɃZ�b�g���Ȃ��� *///2003/07/31 add
	private void setCurrentVOs(IkkatsuReferVO[] currentVOs, IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame){
		for(int i = 0; i < currentVOs.length; i++){
			
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			IkkatsuReferLeftRow formRow_L = leftFrame.getFormRow_L(i);
					
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear(),formRow_R.getPrsNkiMonth(),formRow_R.getPrsNkiDay());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_prs2(formRow_R.getCheck_prs2());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setCheck_prsHacSuu(formRow_R.getCheck_prsHacSuu());
		}
	}
	
}

