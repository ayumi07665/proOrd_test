/**
* PrsOrderAction  �v���X�E�����ޔ�����ʃA�N�V�����N���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/05/12 �i�m�h�h�j�g�c �h�q
* 			�E�u�����w���v�u�`�[���s�v��Ɖ��ʂ֖߂� �̒ǉ��B
* 		 2003/05/16 �i�m�h�h�j���c �Ĕ�
* 			�E�u�����w���v�u�`�[���s�v���ɔ����於�̂̍ăZ�b�g�ǉ��B
* 		 2003/06/18 �i�m�h�h�j���c �Ĕ�
* 			�E�O100����100���Ή��ǉ��B
*			�E�����ޔ����@�\�ǉ��B
* 		 2003/09/19�i�m�h�h�j���c �Ĕ�
* 			�E�\�����ڂɃv���X�E������ �V�������敪�P�E�Q��ǉ�
* 			�E�v���X�E������ �����݌v �� ���ɗ݌v�ɕύX
* 		 2003/12/19�i�m�h�h�j�X
* 			�E�S�i�ԂɃf�t�H���g�Ń`�F�b�N��t����
* 		 2004/01/13�i�m�h�h�j�X
* 			�E�[����N���A�ɔ����C��
* 		 2004/03/03�i�m�h�h�j�X
* 			�E�[�i�於�̂ɂQ�O��������ǉ�
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		 2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
*		 2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�Ŕ[�i�於���擾����悤�ɏC��
*		 2005/05/26�i�m�h�h�j�g�c
*			�E�[�i�於��'�i�`�q�d�c'�̏ꍇ�A�����敪"0"�̏C��
*		 2005/09/14�i�m�h�h�j�g�c
*			�E�����ޔ�����ʁA�����ރR�[�h���v���_�E�����ڂɕύX�iVAP�БΉ��j
*		 2005/09/22�i�m�h�h�j�g�c
*			�E�[���ꊇ�ύX�@�\�ǉ�
*		 2007/09/26�i�m�h�h�j����
*			�E�u�}�j���A�������ꊇ�\�t�v�Ή��i�敪�̒l��āj
*		 2007/12/25�i�m�h�h�j�c��
*			�E�u�}�j���A�������ꊇ�\�t�v�\�t���ڒǉ��Ή��i���l�Q�j
*/

package com.jds.prodord.order.prsorder;
import com.jds.prodord.common.*;
import com.jds.prodord.indicate.manualorder.*;
import com.jds.prodord.indicate.manualorderpaste.ManualOrderPasteForm;
import com.jds.prodord.print.printorders.*;
import com.jds.prodord.reference.ikkatsurefer.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class PrsOrderAction extends Action {
	
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  PrsOrderDelegate bzDelegate = new PrsOrderDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		PrsOrderForm fmForm = (PrsOrderForm)form;			
		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��
		
		PrsOrderLeftFrame leftFrame = new PrsOrderLeftFrame();
		//�������f�[�^���������p�̃f�[�^���i�[����VO
		PrintOrdersQueryVO poQueryVO = new PrintOrdersQueryVO();
		
		//�Z�b�V��������J�ڌ���ʂŃZ�b�g����VO���擾
		PrsOrderVO[] resultVOs = (PrsOrderVO[])session.getAttribute(CommonConst.PRSORDER_VOS);
		
		fmForm.setNewWindow("0");//����p��ʂ��J�����ǂ����̃t���O��������

		//�T�[�r�X���Ԃ̃`�F�b�N
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
//System.out.println("command : "+fmForm.getCommand());
		//�ŏ��ɑJ�ڌ���ʂ��炫���Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			//�Z�b�V��������ManualOrderForm�����o���A�R�}���h���Z�b�g
			ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
			if(manualForm != null)
				manualForm.setCommand("INIT");
			
	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCod(logonForm.getKaiskbcod());
			fmForm.setQueryKaiSkbCodList(kaiList);
			fmForm.setBshCod(logonForm.getBshCod());//2005/02/03 add
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			//�����ރR�[�h�v���_�E�����X�g�̎擾 2005/09/14 add
			errors = this.setFukSziList(fmForm);
			
			session.removeAttribute(CommonConst.INFOMSG_KEY);
				
			//�ǂ̉�ʂ���J�ڂ��Ă�����Form�ɃZ�b�g
			for(int i = 0; i < resultVOs.length; i++){
				if(resultVOs[i] != null){
					fmForm.setPre_page(resultVOs[i].getPre_page());
					fmForm.setPrs_FukSgn(resultVOs[i].getPrs_FukSgn());
					break;
				}
			}
			//��ʂɃf�[�^���Z�b�g
      		errors = this.setData(fmForm,leftFrame,resultVOs,req,res);
			
			if(errors == null){	//��������
//System.out.println("******��������*****");
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));	
			}

			//�G���[����
			if(errors != null){
				super.saveErrors(req,errors);
	    		return mapping.findForward("failure");
			}
		}
		
		//�u�����w���v������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_HACHUSHIJI)){
//System.out.println("Action--> �����w��");
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.hacShiji(fmForm,leftFrame,req,res);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				fmForm.setCommand(PrsOrderForm.SUCCESS_HACHUSHIJI);
				return mapping.findForward("next");
			}
		}
		//�u�����w���v����
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_HACHUSHIJI)){
//System.out.println("Action--> �����w������");
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.indicate.end"));
			fmForm.setCommand("INIT");
		}
		
		//�u�`�[���s�v������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_DENPYOHAKKOU)){
//System.out.println("Action--> �`�[���s");
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.dnpHakkou(fmForm,leftFrame,poQueryVO,req,res);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				
				com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");
		  			
				//poQueryVO�ɑ�\��ЂƉ�Ў��ʃR�[�h���Z�b�g
				poQueryVO.setDaiKaiSkbCod(logonForm.getDaikaiskbcod());
				poQueryVO.setKaiSkbCod(logonForm.getKaiskbcod());
				poQueryVO.setKaiSkbCod_arr(logonForm.getKaiSkbCodList());

				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//�������f�[�^�����������Z�b�g���ꂽpoQueryVO���Z�b�V�����Ɋi�[
				
				fmForm.setCommand(PrsOrderForm.SUCCESS_DENPYOHAKKOU);
				return mapping.findForward("next");
			}
		}
		//�u�`�[���s�v����
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_DENPYOHAKKOU)){
//System.out.println("Action--> �`�[���s����");
			fmForm.setNewWindow("1");
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
			fmForm.setCommand("INIT");
		}
		//��100��
		else if(fmForm.getCommand().equals(PrsOrderForm.NEXT)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,1);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				fmForm.setCommand(PrsOrderForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//�O100��
		else if(fmForm.getCommand().equals(PrsOrderForm.PREV)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,-1);
			
			if(errors == null){	//��������
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//���̃t���[�����Z�b�g������
				fmForm.setCommand(PrsOrderForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//�O100����100������
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_PREVNEXT)){
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
		}
		
		//�u�Ɖ��ʂ֖߂�v������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_BACK)){
//System.out.println("Action--> �Ɖ��ʂ֖߂�");
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.back(fmForm,leftFrame,req,res);
			
			if(errors == null){	//��������
//				fmForm.setCommand(PrsOrderForm.COMMAND_BACK);
				session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
				session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
				session.removeAttribute(CommonConst.PRSORDER_VOS);
				return mapping.findForward("back");
			}
		}
		//�u�v���X�����v������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_PRSHACHU)){
			//�Z�b�V��������ManualOrderForm�����o���A�R�}���h���Z�b�g
			ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
			manualForm.setCommand(ManualOrderForm.COMMAND_PRSHACHU);
			return mapping.findForward("next_manual");
			
		}//�u�����ޔ����v������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_FKUSHIZAIHACHU)){
			//�Z�b�V��������ManualOrderForm�����o���A�R�}���h���Z�b�g
			ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
			manualForm.setCommand(ManualOrderForm.COMMAND_FUKUHACHU);
			return mapping.findForward("next_manual");
			
		}//�敪�ύX�{�^��������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_KBNHENKO)){
			this.kbnHenko(fmForm,leftFrame,req,res);
//			this.nynclear(fmForm,leftFrame,req,res); // 2004.01.13 add
			fmForm.setCommand(PrsOrderForm.SUCCESS_KBNHENKO);
			return mapping.findForward("next");
		}//�敪�ύX����
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_KBNHENKO)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			this.setKbn(fmForm,leftFrame);
			fmForm.setPrs_FukSgn(fmForm.getPrs_FukSgn()); // 2004.01.13 add st
			if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
				this.setNnMei(fmForm,leftFrame); 
			}				// 2004.01.13 add ed
			session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//���̃t���[�����Z�b�g������
			fmForm.setCommand("INIT");
			
			if(fmForm.getPre_page().equals(PrsOrderForm.ManualOrder)){
				//�Z�b�V��������ManualOrderForm�����o���A�敪���Z�b�g
				ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
				manualForm.setKbn(fmForm.getKbn());
			}else if(fmForm.getPre_page().equals(PrsOrderForm.ManualOrderPaste)){
				//�Z�b�V��������ManualOrderPasteForm�����o���A�敪���Z�b�g
				ManualOrderPasteForm mopForm = (ManualOrderPasteForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDERPASTE);
				mopForm.setKbn(fmForm.getKbn());
			}

//2004.01.13 add �[����N���A�ɔ����C�� st
		}//�[����N���A�{�^��������
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_NHNCLEAR)){ 
			this.nynclear(fmForm,leftFrame,req,res);
			fmForm.setCommand(PrsOrderForm.SUCCESS_NHNCLEAR);
			return mapping.findForward("next");
		}//�[����N���A����
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_NHNCLEAR)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			this.setNhnMei(fmForm,leftFrame);
			fmForm.setCommand("INIT");
//			2004.01.13 add �[����N���A�ɔ����C�� ed

		//�[���ꊇ�ύX�{�^�������� 2005/09/22 add
		}else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_NKICHANGE)){ 
			this.changeNki(fmForm,leftFrame,req,res);
			fmForm.setCommand(PrsOrderForm.SUCCESS_NKICHANGE);
			return mapping.findForward("next");
		//�[���ꊇ�ύX����
		}else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_NKICHANGE)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			this.setCheckbox_checked(fmForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
			this.setNki(fmForm,leftFrame);
			fmForm.setCommand("INIT");
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
	 * �����ރR�[�h�v���_�E�����X�g�̃Z�b�g
	 * @param fmForm
	 * @return errors
	 */
	private ActionErrors setFukSziList(PrsOrderForm fmForm){
		
		ActionErrors errors = null;
		ArrayList fukSziList = null;
		
		try{
			//�����ވꗗ�擾
			fukSziList = bzDelegate.doFukSziList(fmForm.getQueryDaiKaiSkbCod(),fmForm.getQueryKaiSkbCod());
			fmForm.setFukSziList(fukSziList);
			
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		
		return errors;
	}

	/************************************************************************************************
	 * 		�f�[�^�Z�b�g
	 * 
	 ***********************************************************************************************/
	private ActionErrors setData (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
								  PrsOrderVO[] resultFmVOs, HttpServletRequest req,HttpServletResponse res){
//System.out.println("Action setData START");					
		ActionErrors errors = null;
			
		//����������
		fmForm.removePrsOrderVO();
		fmForm.removeAllRow();
		leftFrame.removeAllRow();
		
		int rowCount = 0;//�s��
		int pageCount = 0;//�s��
		int max = PrsOrderForm.MAX_ROW_COUNT;//�ő�s��
			
		//���ʂ�1�y�[�W���������ꍇ
		if(resultFmVOs.length <= max){
			fmForm.setSize(resultFmVOs.length);
			leftFrame.setSize(resultFmVOs.length);
			fmForm.setCount(resultFmVOs.length);
			rowCount = resultFmVOs.length;
			pageCount++;
		}
		//���ʂ�1�y�[�W�𒴂����ꍇ
		else{
			fmForm.setSize(max);
			leftFrame.setSize(max);
			fmForm.setCount(max);
			rowCount = max;
			pageCount = resultFmVOs.length / max;
			//���[�ȍs���o����1�y�[�W����
			if(resultFmVOs.length % max != 0)
				pageCount++;
		}

		//�f�[�^���t�H�[���ɃZ�b�g
//System.out.println("resultFmVOs.length : "+resultFmVOs.length);
		for(int i = 0;i < rowCount;i++){
			
		    PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
		    PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);
		    
		    this.setData(fmForm,formRow_R,formRow_L,resultFmVOs[i]);
		   
		}
		fmForm.setPrsOrderVO(new ArrayList(Arrays.asList(resultFmVOs)));//�S�y�[�W����VO���Z�b�g
		fmForm.setPageCount(pageCount);//�y�[�W��
		fmForm.setCurrentPage(0);//���݂̃y�[�W
		fmForm.setReferredMaxPage(0);//�Q�Ƃ��ꂽ�ő�y�[�W
		fmForm.setAllRowCount(resultFmVOs.length);//�S�y�[�W�̌���
		fmForm.setKbn(resultFmVOs[0].getKbn());//2004.01.13 add �[����N���A�ɔ����C��
		
		if(resultFmVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist_prepage"));
		}
		return errors;			
			
	}
	
	
	/************************************************************************************************
	 * 		�����w��
	 * 
	 ***********************************************************************************************/
	private ActionErrors hacShiji (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
//System.out.println("Action hacShiji START");	
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    
	    int page = fmForm.getCurrentPage();
		int max = PrsOrderForm.MAX_ROW_COUNT;
	    
	    PrsOrderVO[] currentVOs = fmForm.getVosList(page);//���݂̃y�[�W��VO�����o��
		
		for(int i = 0; i < fmForm.getSize();i++){	

			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1()){
				lisFmVO.add(null);	
				continue;
			}

			
			PrsOrderVO fmVO = currentVOs[i];
			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());		
			fmVO.setComment(formRow_R.getComment().trim());
			fmVO.setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			fmVO.setNhnMei(formRow_R.getNhnMei().trim());
			fmVO.setCheck_prs1(formRow_R.getCheck_prs1());
			fmVO.setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
			fmVO.setFukSziNm(fmForm.getFukSziNmOptionsLabel(formRow_R.getFukSziCod()));

			lisFmVO.add(fmVO);	
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		ArrayList allVos = fmForm.getPrsOrderVO();
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
//if(lisFmVO.get(j) != null){
//PrsOrderVO vo = (PrsOrderVO)lisFmVO.get(j);
//System.out.println("��vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//}else
//System.out.println("��vo.get("+i+") : null!");
				}
			}
			if(i == allVos.size())
				continue;


			PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("��vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//else
//System.out.println("��vo.get("+i+") : null!");
			if(!vo.getCheck_prs1()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
//		for(int i = 0; i < allVos.size(); i++){
//PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj());
//else
//System.out.println("vo.get("+i+") : null!");
//		}
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}
		
		try{
			//�����w�����s
//			PrsOrderResult[] results = bzDelegate.doHacShiji((PrsOrderVO[])allVos.toArray(new PrsOrderVO[allVos.size()]),fmForm.getPrs_FukSgn());
			PrsOrderResult[] results = bzDelegate.doHacShiji(
					(PrsOrderVO[]) allVos.toArray(new PrsOrderVO[allVos.size()]),
					fmForm.getPrs_FukSgn(),fmForm.getBshCod());//bshCod 2005/05/26 add

			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//������R�[�h�����݂��Ȃ�
                    if(results[i].getDescription().equals(PrsOrderDelegate.HACSAKI_NOT_EXIST)){
						String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"�y�[�W��"+s[1],"������R�[�h"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				 //�o�����[�I�u�W�F�N�g���̂��i�[
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
						
					fmForm.setPrsOrderVO(i, results[i].getVO());
					if(page * max <= i && i < (page + 1) * max){
						//�����於��\�����Ȃ���
						fmForm.getFormRow_R(i % max).setHacNm(this.getSubstringStr(results[i].getVO().getHacNm(),10));
						if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
							//�v���X������������������
							this.setPrsHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}else if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
							//�����ޔ�����������������
							this.setFukHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}
						//�����敪������������
						fmForm.getFormRow_R(i % max).setChoksoKbn(results[i].getVO().getChoksoKbn());
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
	


	/*************************************************************************************************
	 * 		�`�[���s
	 * 
	 ************************************************************************************************/
	private ActionErrors dnpHakkou (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,PrintOrdersQueryVO poQueryVO,
									HttpServletRequest req,HttpServletResponse res){
//System.out.println("Action dnpHakkou START");
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    
	    int page = fmForm.getCurrentPage();
		int max = PrsOrderForm.MAX_ROW_COUNT;
		
		PrsOrderVO[] currentVOs = fmForm.getVosList(page);//���݂̃y�[�W��VO�����o��
		
		for(int i = 0; i < fmForm.getSize();i++){	
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1()){
				lisFmVO.add(null);	
				continue;
			}
				
			PrsOrderVO fmVO = currentVOs[i];

			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			fmVO.setComment(formRow_R.getComment().trim());
			fmVO.setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			fmVO.setNhnMei(formRow_R.getNhnMei().trim());
			
			fmVO.setCheck_prs1(formRow_R.getCheck_prs1());
			fmVO.setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
			fmVO.setFukSziNm(fmForm.getFukSziNmOptionsLabel(formRow_R.getFukSziCod()));
	
			lisFmVO.add(fmVO);	
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		ArrayList allVos = fmForm.getPrsOrderVO();
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
//if(lisFmVO.get(j) != null){
//PrsOrderVO vo = (PrsOrderVO)lisFmVO.get(j);
//System.out.println("��vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//}else
//System.out.println("��vo.get("+i+") : null!");
				}
			}
			if(i == allVos.size())
				continue;


			PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("��vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//else
//System.out.println("��vo.get("+i+") : null!");
			if(!vo.getCheck_prs1()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
//		for(int i = 0; i < allVos.size(); i++){
//PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj());
//else
//System.out.println("vo.get("+i+") : null!");
//		}
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}
		
		try{
			//�`�[���s���s
//			PrsOrderResult[] results = bzDelegate.doDnpHakkou((PrsOrderVO[])allVos.toArray(new PrsOrderVO[allVos.size()]),fmForm.getPrs_FukSgn());
			PrsOrderResult[] results = bzDelegate.doDnpHakkou(
					(PrsOrderVO[]) allVos.toArray(new PrsOrderVO[allVos.size()]),
					fmForm.getPrs_FukSgn(),fmForm.getBshCod());//bshCod 2005/05/26 add

			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//������R�[�h�����݂��Ȃ�
                    if(results[i].getDescription().equals(PrsOrderDelegate.HACSAKI_NOT_EXIST)){
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
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					PrsOrderVO fmVO = results[i].getVO();
					fmForm.setPrsOrderVO(i, fmVO);
					if(page * max <= i && i < (page + 1) * max){
						if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
							//�v���X������������������
							this.setPrsHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}else if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
							//�����ޔ�����������������
							this.setFukHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}
						//�����於��\�����Ȃ���
						fmForm.getFormRow_R(i % max).setHacNm(this.getSubstringStr(fmVO.getHacNm(),10));
						//�����敪������������
						fmForm.getFormRow_R(i % max).setChoksoKbn(results[i].getVO().getChoksoKbn());
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
	private ActionErrors dispPre_NxtData (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res, int addIndex){
//System.out.println("Action dispPrevNextData START");					
		ActionErrors errors = null;
		PrsOrderVO[] resultVOs = null;//�Oor���y�[�W��VO���i�[
		PrsOrderVO[] currentVOs = null;//���݂̃y�[�W��VO���i�[

		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);
				
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
			currentVOs[i].setFukSziNm(fmForm.getFukSziNmOptionsLabel(formRow_R.getFukSziCod()));
		}
		
		//�Ώۃy�[�W�̃f�[�^���擾
		try{
			ArrayList check_prs1_index = new ArrayList();
	    	ArrayList check_fuk1_index = new ArrayList();
			
			//�Oor���y�[�W��VO���擾
			resultVOs = fmForm.getVosList(fmForm.getCurrentPage() + addIndex);
			
			if(resultVOs == null)
				throw new IndexOutOfBoundsException();
			
			//����������
			fmForm.removeAllRow();
			leftFrame.removeAllRow();
					
			//���ʂ��t�H�[���ɃZ�b�g
			fmForm.setSize(resultVOs.length);
			leftFrame.setSize(resultVOs.length);
			fmForm.setCount(resultVOs.length);
			fmForm.clearTableField();
			leftFrame.clearTableField();
			
			for(int i = 0; i < resultVOs.length; i++){
				PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
				PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);

				this.setData(fmForm,formRow_R,formRow_L,resultVOs[i]);

				//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
				if(formRow_R.getCheck_prs1())
					check_prs1_index.add(i+"");
				if(formRow_R.getCheck_fuk1())
					check_fuk1_index.add(i+"");
			}
			fmForm.setCurrentPage(fmForm.getCurrentPage() + addIndex);//���݂̃y�[�W
			fmForm.setReferredMaxPage(fmForm.getCurrentPage());//�Q�Ƃ��ꂽ�ő�y�[�W
			fmForm.setCheck_index(check_prs1_index,check_fuk1_index);//�`�F�b�N�{�b�N�X�̃C���f�b�N�X���Z�b�g
			
		}catch(IndexOutOfBoundsException e){
			resultVOs = new PrsOrderVO[0];
		}		
		if(resultVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
		}
			
		return errors;			
			
	}

	/************************************************************************************************
	 * 		��Đ��Ɖ��ʂ֖߂�
	 * 
	 ***********************************************************************************************/
	private ActionErrors back (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
//System.out.println("PrsOrderAction back START ---------------------------------------------------");	

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		
		ArrayList check_prs1_index = new ArrayList();
	   	ArrayList check_prs2_index = new ArrayList();
	   	ArrayList check_fuk1_index = new ArrayList();
		ArrayList check_prsHacSuu_index = new ArrayList();
			
			
		//��Đ��Ɖ��ʂ̃f�[�^
		IkkatsuReferForm ikkatsuForm = new IkkatsuReferForm();
				
		IkkatsuReferLeftFrame ikkatsuLeftFrame = new IkkatsuReferLeftFrame();

		ikkatsuForm = (IkkatsuReferForm)session.getAttribute(CommonConst.FORMBEAN_NAME_IKKATSU);
		ikkatsuLeftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
		
		int page = fmForm.getCurrentPage();
		int page_teian = 0;
		ikkatsuForm.setCurrentPage(page_teian);
		ikkatsuForm.clearReferredMaxPage();
		
		int max = PrsOrderForm.MAX_ROW_COUNT;
		int max_teian = IkkatsuReferForm.MAX_ROW_COUNT;
		
		PrsOrderVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//���݂̃y�[�W��VO�����o��
		
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);
				
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			
		}
			
		ArrayList prsOrderVOs = fmForm.getPrsOrderVO();
		ArrayList ikkatsuVOs = ikkatsuForm.getAllVos();
		
		
		for(int i = 0, j = 0; i < prsOrderVOs.size(); i++){ //========================================
			
			PrsOrderVO prsOrderVO;
			
			if(page * max <= i && i < (page * max) + currentVOs.length){
				prsOrderVO = currentVOs[j];
				j++;
			}else{
				prsOrderVO = (PrsOrderVO)prsOrderVOs.get(i);			
			}
			if(!prsOrderVO.getBasedRowFlg().equals("1"))//��s�t���O=1�̂��̂�����Đ��Ɖ��ʂɕԂ�
				continue;
			
			IkkatsuReferVO ikkatsuVO = (IkkatsuReferVO)ikkatsuVOs.get(prsOrderVO.getIndex());
			
			if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
				ikkatsuVO.setPrsHacSuu(prsOrderVO.getPrsHacSuu());			
				ikkatsuVO.setPrsNki(prsOrderVO.getPrsNki());
				ikkatsuVO.setPrsMkrCod(prsOrderVO.getPrsMkrCod());
			}
			if(prsOrderVO.getSyrZmiSgn_prs() == IkkatsuReferDAO.HACZMI
			|| prsOrderVO.getSyrZmiSgn_prs() == IkkatsuReferDAO.SYRZMI){
				ikkatsuVO.setCheck_prs1(false);
				ikkatsuVO.setCheck_prs2(false);
				ikkatsuVO.setCheck_prsHacSuu(false);
			}
			
			//�v���X���
			ikkatsuVO.setPrsHacSuu1(prsOrderVO.getPrsHacSuu1());
			ikkatsuVO.setPrsHacSuu2(prsOrderVO.getPrsHacSuu2());
			ikkatsuVO.setPrsHacNyo1(prsOrderVO.getPrsHacNyo1());
			ikkatsuVO.setPrsHacNyo2(prsOrderVO.getPrsHacNyo2());
			ikkatsuVO.setPrsHacNki1(prsOrderVO.getPrsHacNki1());
			ikkatsuVO.setPrsHacNki2(prsOrderVO.getPrsHacNki2());
			ikkatsuVO.setPrsKbn1(prsOrderVO.getPrsKbn1());
			ikkatsuVO.setPrsKbn2(prsOrderVO.getPrsKbn2());			

			//�����ޏ��
			ikkatsuVO.setFukHacSuu(prsOrderVO.getFukHacSuu1());//�O�񔭒������Z�b�g
			ikkatsuVO.setFukHacSuu1(prsOrderVO.getFukHacSuu1());
			ikkatsuVO.setFukHacSuu2(prsOrderVO.getFukHacSuu2());
			ikkatsuVO.setFukHacNki1(prsOrderVO.getFukHacNki1());
			ikkatsuVO.setFukHacNki2(prsOrderVO.getFukHacNki2());
			ikkatsuVO.setFukKbn1(prsOrderVO.getFukKbn1());
			ikkatsuVO.setFukKbn2(prsOrderVO.getFukKbn2());
//			ikkatsuVO.setFukZaiSuu(prsOrderVO.getFukZaiSuu());
			
			if(prsOrderVO.getSyrZmiSgn_fuk() == IkkatsuReferDAO.HACZMI
			|| prsOrderVO.getSyrZmiSgn_fuk() == IkkatsuReferDAO.SYRZMI){
				ikkatsuVO.setCheck_fuk1(false);
				ikkatsuVO.setCheck_flg(false);
				ikkatsuVO.setCheck_sgn("");
			}
			
//			//�o�͍σT�C��
			ikkatsuVO.setHacZmiSgn(prsOrderVO.getSyrZmiSgn_prs());
//			ikkatsuVO.setHacFlg(true);
			
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
//			if(ikkatsuVO.getCheck_prs1())
//				check_prs1_index.add(prsOrderVO.getIndex()+"");
//			if(ikkatsuVO.getCheck_prs2())
//				check_prs2_index.add(prsOrderVO.getIndex()+"");
//			if(ikkatsuVO.getCheck_fuk1())
//				check_fuk1_index.add(prsOrderVO.getIndex()+"");
//			if(ikkatsuVO.getCheck_prsHacSuu())
//				check_prsHacSuu_index.add(prsOrderVO.getIndex()+"");
							
			ikkatsuForm.setIkkatsuReferVO_all(prsOrderVO.getIndex(), ikkatsuVO);	

					
		}//=======================================================================================
		
		ikkatsuForm.setSize(Math.min(max_teian, ikkatsuVOs.size()));
		ikkatsuLeftFrame.setSize(Math.min(max_teian, ikkatsuVOs.size()));
		
		IkkatsuReferVO ikkatsuVO[] = ikkatsuForm.getVosList(ikkatsuForm.getCurrentPage());
		
		//Form�̓��e���Z�b�g���Ȃ���
		for(int i = 0; i < ikkatsuForm.getSize(); i++){//----------------------------
			
			IkkatsuReferFormRow ikkatsuRow_R = ikkatsuForm.getFormRow_R(i);
			IkkatsuReferLeftRow ikkatsuRow_L = ikkatsuLeftFrame.getFormRow_L(i);
			
			//�L�[����
		    ikkatsuRow_R.setDaiKaiSkbCod(ikkatsuVO[i].getDaiKaiSkbCod());
		    ikkatsuRow_L.setDaiKaiSkbCod(ikkatsuVO[i].getDaiKaiSkbCod());
		    ikkatsuRow_R.setKaiSkbCod(ikkatsuVO[i].getKaiSkbCod());
		    ikkatsuRow_L.setKaiSkbCod(ikkatsuVO[i].getKaiSkbCod());
		    ikkatsuRow_R.setKigBng(ikkatsuVO[i].getKigBng());
		    ikkatsuRow_L.setKigBng(ikkatsuVO[i].getKigBng());
		    //�i�ԃ}�X�^�[
		    ikkatsuRow_L.setKetCod(ikkatsuVO[i].getKetCod());
		    ikkatsuRow_L.setKetNm(ikkatsuVO[i].getKetNm());
		    ikkatsuRow_L.setHjiHnb(ikkatsuVO[i].getHjiHnb());//�\���i�Ԃ��Z�b�g
		    ikkatsuRow_L.setArtKj(ikkatsuVO[i].getArtKj());
		    ikkatsuRow_L.setHbiDte(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getHbiDte()));
		    ikkatsuRow_L.setTitKj(ikkatsuVO[i].getTitKj());
		    ikkatsuRow_L.setTomRak(ikkatsuVO[i].getTomRak());
		    ikkatsuRow_L.setZikTik(ikkatsuVO[i].getZikTik()); //2004.04.21 add 
	    	ikkatsuRow_R.setPrsMkrCod(ikkatsuVO[i].getPrsMkrCod());
	    
	    	//���������e�[�u��
	    	ikkatsuRow_L.setHacZmiSgn(this.getHacZmiSgnString(ikkatsuVO[i].getHacZmiSgn()));
	    	//�݌Ƀe�[�u��
		    ikkatsuRow_L.setAziSuu(ikkatsuVO[i].getAziSuu());
			ikkatsuRow_L.setTnaSekZan(ikkatsuVO[i].getTnaSekZan());
			ikkatsuRow_L.setJucZan(ikkatsuVO[i].getJucZan());
			ikkatsuRow_L.setMhikSuu(ikkatsuVO[i].getMhikSuu());
			ikkatsuRow_L.setRsvSum(ikkatsuVO[i].getRsvSum());
			
			ikkatsuRow_R.setTodUriSuu(ikkatsuVO[i].getTodUriSuu());
			ikkatsuRow_R.setB1dUri(ikkatsuVO[i].getB1dUri());
			ikkatsuRow_R.setB2dUri(ikkatsuVO[i].getB2dUri());
			ikkatsuRow_R.setB3dUri(ikkatsuVO[i].getB3dUri());
			ikkatsuRow_R.setB4dUri(ikkatsuVO[i].getB4dUri());
			ikkatsuRow_R.setB5dUri(ikkatsuVO[i].getB5dUri());
			ikkatsuRow_R.setTowUri(ikkatsuVO[i].getTowUri());
			ikkatsuRow_R.setB1wUri(ikkatsuVO[i].getB1wUri());
			ikkatsuRow_R.setB2wUri(ikkatsuVO[i].getB2wUri());
			ikkatsuRow_R.setTomUri(ikkatsuVO[i].getTomUri());
			ikkatsuRow_R.setB1mUri(ikkatsuVO[i].getB1mUri());
			ikkatsuRow_R.setB2mUri(ikkatsuVO[i].getB2mUri());
			ikkatsuRow_R.setTomHpn(ikkatsuVO[i].getTomHpn());
			ikkatsuRow_R.setB1mHpn(ikkatsuVO[i].getB1mHpn());
			ikkatsuRow_R.setB2mHpn(ikkatsuVO[i].getB2mHpn());
			ikkatsuRow_R.setTomTna(ikkatsuVO[i].getTomTna());
			ikkatsuRow_R.setB1mTna(ikkatsuVO[i].getB1mTna());
			ikkatsuRow_R.setB2mTna(ikkatsuVO[i].getB2mTna());
			
			ikkatsuRow_L.setAvgUri(ikkatsuVO[i].getAvgUri());
			
			ikkatsuRow_R.setPrsMnyKei(ikkatsuVO[i].getPrsMnyKei());
			ikkatsuRow_R.setPrsHacRui(ikkatsuVO[i].getPrsHacRui());
			ikkatsuRow_R.setFukMnyKei(ikkatsuVO[i].getFukMnyKei());
			ikkatsuRow_R.setFukHacRui(ikkatsuVO[i].getFukHacRui());
			ikkatsuRow_R.setPrsNyoKei(ikkatsuVO[i].getPrsNyoKei());
			ikkatsuRow_R.setFukNyoKei(ikkatsuVO[i].getFukNyoKei());
			ikkatsuRow_L.setFukHacRui(ikkatsuVO[i].getFukHacRui());
			ikkatsuRow_L.setFukZaiSuu(ikkatsuVO[i].getFukZaiSuu());
			ikkatsuRow_L.setFukNyoKei(ikkatsuVO[i].getFukNyoKei());
	    
			//���������e�[�u���i�v���X�j
			ikkatsuRow_R.setPrsHacSuu1(ikkatsuVO[i].getPrsHacSuu1());
			ikkatsuRow_R.setPrsHacNyo1(ikkatsuVO[i].getPrsHacNyo1());
			if(!ikkatsuVO[i].getPrsHacNki1().equals(""))
				ikkatsuRow_R.setPrsHacNki1(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getPrsHacNki1()));
			ikkatsuRow_R.setPrsKbn1(DataFormatUtils.getKbnString(ikkatsuVO[i].getPrsKbn1()));
			ikkatsuRow_R.setPrsHacSuu2(ikkatsuVO[i].getPrsHacSuu2());
			ikkatsuRow_R.setPrsHacNyo2(ikkatsuVO[i].getPrsHacNyo2());
			if(!ikkatsuVO[i].getPrsHacNki2().equals(""))
				ikkatsuRow_R.setPrsHacNki2(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getPrsHacNki2()));
			ikkatsuRow_R.setPrsKbn2(DataFormatUtils.getKbnString(ikkatsuVO[i].getPrsKbn2()));
			
			//���������e�[�u���i�����ށj
			ikkatsuRow_R.setFukHacSuu1(ikkatsuVO[i].getFukHacSuu1());
			ikkatsuRow_L.setFukHacSuu1(ikkatsuVO[i].getFukHacSuu1());
			ikkatsuRow_R.setFukHacNyo1(ikkatsuVO[i].getFukHacNyo1());
			if(!ikkatsuVO[i].getFukHacNki1().equals(""))
				ikkatsuRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getFukHacNki1()));
			ikkatsuRow_R.setFukKbn1(DataFormatUtils.getKbnString(ikkatsuVO[i].getFukKbn1()));
			ikkatsuRow_R.setFukHacSuu2(ikkatsuVO[i].getFukHacSuu2());
			ikkatsuRow_R.setFukHacNyo2(ikkatsuVO[i].getFukHacNyo2());
			if(!ikkatsuVO[i].getFukHacNki2().equals(""))
				ikkatsuRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getFukHacNki2()));
			ikkatsuRow_R.setFukKbn2(DataFormatUtils.getKbnString(ikkatsuVO[i].getFukKbn2()));
			
			//�����ރ}�X�^�[
			ikkatsuRow_R.setFukMkrCod(ikkatsuVO[i].getFukSziHacSaki());

			//���o����-------------------------------------------

			ikkatsuRow_L.setTeianSuu(ikkatsuVO[i].getTeianSuu());
			ikkatsuRow_L.setFskZaiSuu(ikkatsuVO[i].getFskZaiSuu());
			ikkatsuRow_R.setPrsHacSuu(ikkatsuVO[i].getPrsHacSuu());
			ikkatsuRow_R.setFukHacSuu(ikkatsuVO[i].getFukHacSuu());

			ikkatsuRow_R.setCheck_prs1(ikkatsuVO[i].getCheck_prs1());
			ikkatsuRow_R.setCheck_prs2(ikkatsuVO[i].getCheck_prs2());
			ikkatsuRow_R.setCheck_fuk1(ikkatsuVO[i].getCheck_fuk1());
			ikkatsuRow_R.setCheck_flg(ikkatsuVO[i].getCheck_flg());
			ikkatsuRow_R.setCheck_prsHacSuu(ikkatsuVO[i].getCheck_prsHacSuu());
			ikkatsuRow_R.setCheck_sgn(ikkatsuVO[i].getCheck_sgn());

			if(ikkatsuVO[i].getPrsNki() != 0){
				ikkatsuRow_R.setPrsNkiYear(ikkatsuVO[i].getPrsNkiYear());
				ikkatsuRow_R.setPrsNkiMonth(ikkatsuVO[i].getPrsNkiMonth());
				ikkatsuRow_R.setPrsNkiDay(ikkatsuVO[i].getPrsNkiDay());
			}
			if(ikkatsuVO[i].getFukNki() != 0){
				ikkatsuRow_R.setFukNkiYear(ikkatsuVO[i].getFukNkiYear());
				ikkatsuRow_R.setFukNkiMonth(ikkatsuVO[i].getFukNkiMonth());
				ikkatsuRow_R.setFukNkiDay(ikkatsuVO[i].getFukNkiDay());
			}

			ikkatsuRow_L.setZaiNisuu(ikkatsuVO[i].getZaiNisuu());
			ikkatsuRow_R.setChoksoKbn(ikkatsuVO[i].getChoksoKbn());
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			if(ikkatsuVO[i].getCheck_prs1())
				check_prs1_index.add(i+"");
			if(ikkatsuVO[i].getCheck_prs2())
				check_prs2_index.add(i+"");
			if(ikkatsuVO[i].getCheck_fuk1())
				check_fuk1_index.add(i+"");
			if(ikkatsuVO[i].getCheck_prsHacSuu())
				check_prsHacSuu_index.add(i+"");
			
		}//-----------------------------------------------------------------------------
		
		ikkatsuForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);//�`�F�b�N�{�b�N�X�̃C���f�b�N�X���Z�b�g
		
		session.setAttribute(CommonConst.FORMBEAN_NAME_IKKATSU,ikkatsuForm);
		session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,ikkatsuLeftFrame);


		return errors;			
			
	}
	
	/************************************************************************************************
	 * 		�敪�ύX				2003/09/11 add
	 * 
	 ***********************************************************************************************/
	private void kbnHenko (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
		
		PrsOrderVO[] currentVOs = null;//���݂̃y�[�W��VO���i�[
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			// 2003.12.19 upd �S�i�ԂɃ`�F�b�N��t����
//			if(formRow_R.getCheck_prs1())
				check_prs1_index.add(i+"");
//			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//200712/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		//�敪�ύX
		String kbn = fmForm.getKbn();							
		for(int i = 0;i < fmForm.getAllRowCount(); i++){

		    fmForm.getPrsOrderVO(i).setKbn(kbn);
			if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
				fmForm.getPrsOrderVO(i).setNhnMei(DataFormatUtils.getSkoNm(fmForm.getBshCod()));//2005.02.03 add
			}				// 2004.01.13 add ed
			fmForm.getPrsOrderVO(i).setCheck_fuk1(true); //2004.01.15 add
			fmForm.getPrsOrderVO(i).setCheck_prs1(true); //2004.01.15 add

		}
		
							
	}
	private void setKbn (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//�敪�ύX
		String kbn = fmForm.getKbn();							
		for(int i = 0;i < leftFrame.getSize(); i++){

		    leftFrame.getFormRow_L(i).setKbn(DataFormatUtils.getKbnString(kbn));
		   
		}
	}
	//2004.01.14 add
	private void setNnMei (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//�[����N���A							
		for(int i = 0;i < leftFrame.getSize(); i++){
			//2005.02.03 add
			fmForm.getFormRow_R(i).setNhnMei(DataFormatUtils.getSkoNm(fmForm.getBshCod()));		   
		}
	}

	
	/************************************************************************************************
	 * 		�[����N���A				2004/01/13 add
	 * 
	 ***********************************************************************************************/
	private void nynclear (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
		
		PrsOrderVO[] currentVOs = null;//���݂̃y�[�W��VO���i�[
		ArrayList check_prs1_index = new ArrayList();
		ArrayList check_fuk1_index = new ArrayList();
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
				check_prs1_index.add(i+"");
				check_fuk1_index.add(i+"");
			
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		//�[����N���A
		for(int i = 0;i < fmForm.getAllRowCount(); i++){

			fmForm.getPrsOrderVO(i).setNhnMei("��");
			fmForm.getPrsOrderVO(i).setCheck_fuk1(true); //2004.01.15 add
			fmForm.getPrsOrderVO(i).setCheck_prs1(true); //2004.01.15 add

		}
		
							
	}
	private void setNhnMei (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//�[����N���A							
		for(int i = 0;i < leftFrame.getSize(); i++){
				fmForm.getFormRow_R(i).setNhnMei("��");
		}
	}

	/************************************************************************************************
	 * 		�[���ꊇ�ύX				2005/09/21 add
	 * 
	 ***********************************************************************************************/
	private void changeNki (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
		
		PrsOrderVO[] currentVOs = null;//���݂̃y�[�W��VO���i�[
		ArrayList check_prs1_index = new ArrayList();
		ArrayList check_fuk1_index = new ArrayList();
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
			check_prs1_index.add(i+"");
			check_fuk1_index.add(i+"");
			
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());
		}
		//Form�ɃZ�b�g
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		//�[���ꊇ�ύX
		for(int i = 0;i < fmForm.getAllRowCount(); i++){
			fmForm.getPrsOrderVO(i).setCheck_fuk1(true);
			fmForm.getPrsOrderVO(i).setCheck_prs1(true);
			fmForm.getPrsOrderVO(i).setPrsNki(fmForm.getNki_Y(),fmForm.getNki_M(),fmForm.getNki_D());
		}
	}

	private void setNki (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//�[���ꊇ�ύX
		for(int i = 0;i < leftFrame.getSize(); i++){
			fmForm.getFormRow_R(i).setPrsNkiYear(fmForm.getNki_Y());
			fmForm.getFormRow_R(i).setPrsNkiMonth(fmForm.getNki_M());
			fmForm.getFormRow_R(i).setPrsNkiDay(fmForm.getNki_D());
		}
	}


//---------------------------------------------------------------------------------------------------	
	
	/**
	 *  VO�̃f�[�^��Form�ɃZ�b�g���郁�\�b�h
	 * 
	 */
	private void setData(PrsOrderForm fmForm, PrsOrderFormRow formRow_R, PrsOrderLeftRow formRow_L, 
				PrsOrderVO resultVO){

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
	    if(!resultVO.getHbiDte().equals(""))
	    	formRow_L.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultVO.getHbiDte()));
	    formRow_L.setTitKj(resultVO.getTitKj());
	    formRow_L.setTomRak(resultVO.getTomRak());
	    formRow_L.setZikTik(resultVO.getZikTik()); //2004.04.21 add 
	    formRow_R.setPrsMkrCod(resultVO.getPrsMkrCod());
	    
	    //�����ރp�^�[���R�[�h
		formRow_L.setFukSziPtn(resultVO.getFukSziPtn());

	    formRow_L.setKbn(DataFormatUtils.getKbnString(resultVO.getKbn()));
	    formRow_R.setPrsHacSuu(resultVO.getPrsHacSuu());
			
		if(resultVO.getPrsNki() != 0){
			formRow_R.setPrsNkiYear(resultVO.getPrsNkiYear());
			formRow_R.setPrsNkiMonth(resultVO.getPrsNkiMonth());
			formRow_R.setPrsNkiDay(resultVO.getPrsNkiDay());
		}
		formRow_R.setComment(resultVO.getComment());
		formRow_R.setBiKou2(resultVO.getBiKou2());//2007/12/25 add
		formRow_R.setHacNm(this.getSubstringStr(resultVO.getHacNm(),10));
//		formRow_R.setNhnMei(resultVO.getNhnMei());  upd 04/03/03 
		formRow_R.setNhnMei(this.getSubstringStr(resultVO.getNhnMei(),20));
		formRow_R.setChoksoKbn(resultVO.getChoksoKbn());

		formRow_R.setCheck_prs1(resultVO.getCheck_prs1());
		formRow_R.setCheck_fuk1(resultVO.getCheck_fuk1());
	    			    
	    //�݌Ƀe�[�u��
	    if(resultVO.getExsitZai01()){
			formRow_R.setPrsMnyKei(resultVO.getPrsMnyKei());
			formRow_R.setPrsHacRui(resultVO.getPrsHacRui());
			formRow_R.setFukMnyKei(resultVO.getFukMnyKei());
			formRow_R.setFukHacRui(resultVO.getFukHacRui());
			formRow_R.setFukZaiSuu(resultVO.getFukZaiSuu());
			formRow_L.setFukHacRui(resultVO.getFukHacRui());
			formRow_R.setPrsNyoKei(resultVO.getPrsNyoKei());
			formRow_R.setFukNyoKei(resultVO.getFukNyoKei());
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
			if(!resultVO.getFukHacNki1().equals(""))
				formRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki1()));
			formRow_R.setFukKbn1(DataFormatUtils.getKbnString(resultVO.getFukKbn1()));
			formRow_R.setFukHacSuu2(resultVO.getFukHacSuu2());
			if(!resultVO.getFukHacNki2().equals(""))
				formRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki2()));
			formRow_R.setFukKbn2(DataFormatUtils.getKbnString(resultVO.getFukKbn2()));
		}
		//�����ރR�[�h
		formRow_R.setFukSziCod(resultVO.getFukSziCod());
		//��s�t���O(�s�̐F�ύX�̂��߂Ɏg�p)
		formRow_L.setBasedRowFlg(resultVO.getBasedRowFlg());
		/* �i�ԃ}�X�^�[		: FTBHIN01 | �݌Ƀe�[�u��			: FTBZAI01	| ���������e�[�u�� : FTBHAC02 
			�݌ɓ����}�X�^�[	: FTBMST04 | �`�ԃ����N�ʏ����}�X�^�[	: FTBMST05	| �����ރ}�X�^�[   : FTBFUK01  
			�����ރp�^�[���}�X�^�[  : FTBFUK02			*/    
				    
	}
	
//---------------------------------------------------------------------------------------------------	
	
	/** �`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ������\�b�h**/
	private void setCheckbox_checked(PrsOrderForm fmForm){
		
		ArrayList check_prs1_index = fmForm.getCheck_prs1_index();
	    ArrayList check_fuk1_index = fmForm.getCheck_fuk1_index();
	    int index_prs1 = 0;
	    int index_fuk1 = 0;

//System.out.println("check_prs1_index.size() : "+check_prs1_index.size());
//System.out.println("check_fuk1_index.size() : "+check_fuk1_index.size());
	    
		for(int i = 0; i < fmForm.getSize();i++){	
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			if(check_prs1_index.size() != index_prs1){
				if(i == Integer.parseInt(check_prs1_index.get(index_prs1)+"")){
//System.out.println("setCheck_prs1(true) : "+i);
					formRow_R.setCheck_prs1(true);
					index_prs1++;
				}
			}
			if(check_fuk1_index.size() != index_fuk1){
				if(i == Integer.parseInt(check_fuk1_index.get(index_fuk1)+"")){
//System.out.println("setCheck_fuk1(true) : "+i);
					formRow_R.setCheck_fuk1(true);
					index_fuk1++;
				}
			}
		
		}
		
	}

	
	/** �v���X���������Z�b�g���Ȃ��� **/
	public void setPrsHacJoho(PrsOrderFormRow formRow_R,PrsOrderVO fmVO){
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
	/** �����ޔ��������Z�b�g���Ȃ��� **/
	public void setFukHacJoho(PrsOrderFormRow formRow_R,PrsOrderVO fmVO){
		formRow_R.setFukHacSuu1(fmVO.getFukHacSuu1());
		if(!fmVO.getFukHacNki1().equals(""))
			formRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(fmVO.getFukHacNki1()));
		else
			formRow_R.setFukHacNki1("");
		formRow_R.setFukKbn1(DataFormatUtils.getKbnString(fmVO.getFukKbn1()));
		
		formRow_R.setFukHacSuu2(fmVO.getFukHacSuu2());
		if(!fmVO.getFukHacNki2().equals(""))
			formRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(fmVO.getFukHacNki2()));
		else
			formRow_R.setFukHacNki2("");
		formRow_R.setFukKbn2(DataFormatUtils.getKbnString(fmVO.getFukKbn2()));
		formRow_R.setFukSziCod(fmVO.getFukSziCod().trim()); //2005/09/07 add
	}
	
	private String getSubstringStr(String str, int length){
		if(str == null)
			return "";
		if(str.length() < length)
			return str;
		if(length < 0)
			return "";
		str = str.substring(0,length);
		return str;
	}
	
	/** �G���[�̂������y�[�W�E�s�Ԃ��v�Z���ĕԂ� **/
	public String[] getErrorIndex(int index, int max){
		int page = (index+1) / max;
		int _index = (index+1) % max;
		String[] str = {page+1+"",_index+""};
		return str;
	}
	
	/** �����σT�C���̕\���������Ԃ� **/
	private String getHacZmiSgnString(int sgn){
		String haczmisgn = "";
		switch(sgn){
			case 0:
				haczmisgn = PrsOrderLeftFrame.MIHACHU;
				break;
			case 1:
				haczmisgn = PrsOrderLeftFrame.HACZMI;
				break;
			case 2:
				haczmisgn = PrsOrderLeftFrame.SYRZMI;
				break;
		}
		return haczmisgn;
	}

}