/**
* OrderHistoryMidAction  ���������Ɖ��ʃA�N�V�����N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/06/11�i�m�h�h�j���c �Ĕ�
* 			�E�����`�[���s�@�\ �d�l�ύX
* 			�i�`�F�b�N���ꂽ�s�݂̂ł͂Ȃ��A����̔������ԍ������S�Ă̍s���o�͂���j
*        2003/07/14�i�m�h�h�j���c �Ĕ�
* 			�E�����`�[���s���APrintOrdersQueryVO�ɃZ�b�g���鍀�ڂɁA������R�[�h�ǉ�
* 		 2003/07/18�i�m�h�h�j���c �Ĕ�
* 			�E�V�������敪���X�V�\���ڂ�
* 		 2003/08/18�i�m�h�h�j���c �Ĕ�
* 			�E�_�E�����[�h���A"�\���i��"���ڒǉ�
* 		 2003/09/05�i�m�h�h�j���c �Ĕ�
* 			�E�������ԍ��̃t�H�[�}�b�g���\�b�h�ǉ�
* 		 2004/01/16,20 (�m�h�h) �X
* 			�E�����`�[���s���ɔ��������ύX����Ă�����R�����g�����\����
* 		 2004/01/22�i�m�h�h�j�X
* 			�E���ɐ�����\�ɏC��
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		 2004/08/03 (NII) ����
* 			�E�[���ύX���A���ɓ��ɔ[�����Z�b�g
*		 2005/05/25�i�m�h�h�j�g�c
*			�E�[�i�於��'�i�`�q�d�c'�̏ꍇ�A�����敪"0"�̏C��
* 		 2005/09/01�i�m�h�h�j�g�c
* 			�E���ׂɒP���̍��ڒǉ�
* 			�E�������M�A�����`�[���s�̏ꍇ�A���������ԍ��������X�V����VO�ɃZ�b�g
* 		 2005/12/01�i�m�h�h�j�g�c
* 			�E���z���ݸ�Ή�
* 		 2006/01/25�i�m�h�h�j�c�[
* 			�E�_�E�����[�h���ڂɒ��c���ǉ�
*        2007/03/01�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɕ����ރR�[�h�ǉ�
*        2007/12/05�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɃ^�C�g�������ǉ�
* 		 2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
* 		 2008/03/07�i�m�h�h�j�c��
* 			�E�P���̒ǉ�
* 		 2008/06/02�i�m�h�h�j�c��
* 			�E�u�`�o�Ё@�P���E���z�̏C��
*
*/

package com.jds.prodord.reference.orderhistory;
import com.jds.prodord.common.*;
import com.jds.prodord.print.printorders.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class OrderHistoryMidAction extends Action {
	
	
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  OrderHistoryDelegate bzDelegate = new OrderHistoryDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		OrderHistoryMidForm midForm = (OrderHistoryMidForm)form;		
		OrderHistoryVO queryVO = new OrderHistoryVO();
		MessageResources mr = super.getResources();//�ʏ탁�b�Z�[�W�\���p�Ƀ��\�[�X�t�@�C�����烁�b�Z�[�W���Ƃ��Ă���̂Ɏg��

		midForm.setNewWindow("0");//����p��ʂ��J�����ǂ����̃t���O��������
		midForm.setTeiDenflg("0");//�����`�[���s���s���邩�̃t���O��������

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

		//�ŏ��ɔ�������������ʂ��炫���Ƃ�
		if(midForm.getCommand().equals("INIT")){
	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");
			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			midForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			midForm.setQueryKaiSkbCodList(kaiList);
			midForm.setHyoKbn(logonForm.getHyoKbn()); //add 2011/05/30
			
			session.removeAttribute(CommonConst.INFOMSG_KEY);

			/** ����s� **/
			queryVO = (OrderHistoryVO)session.getAttribute(CommonConst.ORDERHISTORY_VO);
			if(queryVO != null){
				if(queryVO.getPrcKbn().equals(OrderHistoryTopForm.PRCKBN_SEARCH))
					errors = this.find(midForm,queryVO,req,res);
				else if(queryVO.getPrcKbn().equals(OrderHistoryTopForm.PRCKBN_DOWNLOAD)){
					errors = this.download(midForm,queryVO,req,res);
					if(errors == null) //��������
						return null;
				}
			/** ��N���A� **/
			}else{
				midForm.removeAllRow();
				midForm.clear_page();
				midForm.removeOrderHistoryVO();
				midForm.removeVosList();
			}
			session.removeAttribute(CommonConst.ORDERHISTORY_VO);
	    }

		/**�u�����`�[���s�v������	**/ 
		else if(midForm.getCommand().equals(OrderHistoryMidForm.COMMAND_TEIDENHAKKOU)){
			errors = this.dnpHakkou(midForm,poQueryVO,req,res);

			if(errors == null){	//��������	
		   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");

				//poQueryVO�ɑ�\��ЂƉ�Ў��ʃR�[�h���X�g���Z�b�g
				poQueryVO.setDaiKaiSkbCod(logonForm.getDaikaiskbcod());
				poQueryVO.setKaiSkbCod(logonForm.getKaiskbcod());
				poQueryVO.setKaiSkbCod_arr(logonForm.getKaiSkbCodList());
				//�������f�[�^�����������Z�b�g���ꂽpoQueryVO���Z�b�V�����Ɋi�[
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);
				midForm.setCommand(OrderHistoryMidForm.SUCCESS_TEIDENHAKKOU);
				midForm.setTeiDenflg("1");
				midForm.setNewWindow("1");				
				ArrayList rows = (ArrayList)midForm.getFormRowCloneList(); //2004.01.19 add 
				session.setAttribute("saveRows",rows); 							//2004.01.19 add 
				return mapping.findForward("print");
			}
			//�G���[���������܂����B
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("errors.exception"));			
		}

		//::::::::::: �`�[���s���� :::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		else if(midForm.getCommand().equals(OrderHistoryMidForm.SUCCESS_TEIDENHAKKOU)){
			if(session.getAttribute(CommonConst.PO_QUERY_VO) != null){
				ArrayList rows = (ArrayList)session.getAttribute("saveRows"); //2004.01.19 add
				session.removeAttribute("saveRows"); 
				midForm.setFormRowList(rows); //2004.01.19 add 
				midForm.setNewWindow("1");
				midForm.setCheck_false();
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
			}

		}
		
		/**�u�������M�v������ **/
		else if(midForm.getCommand().equals(OrderHistoryMidForm.COMMAND_TEISOU)){
			
			if(queryVO != null)	
				errors = this.teiSei(midForm,req,res);
				
			if(errors == null){	//��������
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.correct.end"));
			}
		}
		
		/** ���100��� ������ **/
		else if(midForm.getCommand().equals(OrderHistoryMidForm.NEXT)){
			errors = this.dispPre_NxtData(midForm,req,res,1);
			
			if(errors == null){	//��������
				return mapping.findForward("success");
			}
		}
		/** ��O100��� ������ **/
		else if(midForm.getCommand().equals(OrderHistoryMidForm.PREV)){
			errors = this.dispPre_NxtData(midForm,req,res,-1);
			
			if(errors == null){	//��������
				this.setCheckbox_checked(midForm);//�`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ���
				return mapping.findForward("success");
			}
		}

		//�G���[����
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}
		
		
		//�G���[�Ȃ�
		return mapping.findForward("success");		
	}

	/******************************************************************************************
	 * 		����																		      * 
	 * 																				          *
	 ******************************************************************************************/
	private ActionErrors find (OrderHistoryMidForm midForm, OrderHistoryVO queryVO,
									HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		MessageResources mr = super.getResources();
		
		StringBuffer str_before = new StringBuffer();
		
		try{
		
			//����������
			midForm.removeAllRow();
			midForm.clear_page();
			midForm.removeOrderHistoryVO();
			
			//�������s
			OrderHistoryResult result = bzDelegate.doFindForRefer(queryVO);
			OrderHistoryVO[] resultFmVOs = result.getResultList();
			
			//�������ʂ��t�H�[���ɃZ�b�g
			midForm.setSize(resultFmVOs.length);
			midForm.clearTableField();	
			
			String breakKey_before = "";

			ArrayList vosList = new ArrayList();//�y�[�W�P�ʂ�VO�̃��X�g���i�[����
			
			int pageCount = 0;//�y�[�W��
			int max = OrderHistoryMidForm.MAX_ROW_COUNT;//�ő�s��
			
			//���ʂ�1�y�[�W���������ꍇ
			if(resultFmVOs.length <= max){
				midForm.setSize(resultFmVOs.length);
				midForm.setCount(resultFmVOs.length);
				pageCount++;
			}
			//���ʂ�1�y�[�W�𒴂����ꍇ
			else{
				midForm.setSize(max);
				midForm.setCount(max);
				pageCount = resultFmVOs.length / max;
				//���[�ȍs���o����1�y�[�W����
				if(resultFmVOs.length % max != 0)
					pageCount++;
			}
				
			for(int i = 0; i<pageCount; i++){//�y�[�W�� ArrayList��new���Ă���
				vosList.add(new ArrayList());
			}

			int cnt = 0;

			for(int i = 0; i < pageCount; i++){
				ArrayList vos = (ArrayList)vosList.get(i);
				
				for(int j = cnt; j < max + cnt && j < resultFmVOs.length; j++){
					if(i == 0){
					    OrderHistoryFormRow formRow = midForm.getFormRow(j);
						
						StringBuffer str = new StringBuffer();
									    
					    //������E�L���ԍ�
				  		str.append(resultFmVOs[j].getHacCod()).append(resultFmVOs[j].getHjiHnb());

						String breakKey = str.toString();

						if(!breakKey.equals(breakKey_before)){
							formRow.setBreakflg(true);
						}
						breakKey_before = str.toString();
					    //�L�[����
					    formRow.setDaiKaiSkbCod(resultFmVOs[j].getDaiKaiSkbCod());
					    formRow.setKaiSkbCod(resultFmVOs[j].getKaiSkbCod());
					    formRow.setKigBng(resultFmVOs[j].getKigBng());
					    formRow.setBshCod(queryVO.getBshCod());//2005/05/25 add
					    //�������ʃZ�b�g
					    formRow.setHacCod(resultFmVOs[j].getHacCod());//������
					    if(!resultFmVOs[j].getSinKyuKbn().equals(""))
					    	formRow.setSinKyuKbn(DataFormatUtils.getKbnString(resultFmVOs[j].getSinKyuKbn()));//�敪
					    formRow.setHjiHnb(resultFmVOs[j].getHjiHnb());//�\���i��
					    formRow.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[j].getHbiDte()));//������
					    formRow.setHacSyoBng(resultFmVOs[j].getHacSyoBng());//�������ԍ�
					    formRow.setGyoBng(resultFmVOs[j].getGyoBng());//�s�ԍ�
					    //������
					    formRow.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[j].getHacSyoDte()));
						//�o�͍σT�C��
					    if(!resultFmVOs[j].getSyrZmiSgn().equals(""))
					    	formRow.setSyrZmiSgn(getHacZmiSgnString(Integer.parseInt(resultFmVOs[j].getSyrZmiSgn())));
						//���ރR�[�h
						if(!resultFmVOs[j].getBunCod().equals(""))	
							formRow.setBunCod(this.getBunCodString(Integer.parseInt(resultFmVOs[j].getBunCod())));//���޺���
						if(!resultFmVOs[j].getKin().equals("0") && !resultFmVOs[j].getKin().equals("0.00"))
							formRow.setKin(resultFmVOs[j].getKin());//���z 2005/09/13 add
						if(!resultFmVOs[j].getKinOld().equals("0") && !resultFmVOs[j].getKinOld().equals("0.00"))
							formRow.setKinOld(resultFmVOs[j].getKinOld());//���zOld 2008/06/02 add
					    formRow.setFukSzisuu(DataFormatUtils.setFormatString(resultFmVOs[j].getFukSziSuu()));//��ڽ�����ގc
					    formRow.setHacSuu(resultFmVOs[j].getHacSuu());//������
						formRow.setHacSuuOld(resultFmVOs[j].getHacSuuOld());//������Old 2005/06/02 add
					    formRow.setNyoSuu(DataFormatUtils.setFormatString(resultFmVOs[j].getNyoSuu()));//���ɐ�
						//�[��
					    formRow.setNkiYear(resultFmVOs[j].getNkiYear());
					    formRow.setNkiMonth(resultFmVOs[j].getNkiMonth());
					    formRow.setNkiDay(resultFmVOs[j].getNkiDay());
					    formRow.setNhnMeiKj(resultFmVOs[j].getNhnMeiKj());//�[�i��
					    formRow.setCheck_Mid(resultFmVOs[j].getCheck_Mid());//�`�F�b�N�ޯ��
					    formRow.setCmt(resultFmVOs[j].getCmt());//�R�����g
					    formRow.setCykkbn(resultFmVOs[j].getCykKbn()); //�����敪�@2004.01.22 add 
					    //���ɓ�
					    if(!resultFmVOs[j].getNyoDte().equals("") && !resultFmVOs[j].getNyoDte().equals("0"))	
					    	formRow.setNyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[j].getNyoDte()));
						formRow.setBiKou2(resultFmVOs[j].getBiKou2());//���l�Q
						if(!resultFmVOs[j].getTan2().equals("0") && !resultFmVOs[j].getTan2().equals("0.00"))
							formRow.setTan2(resultFmVOs[j].getTan2());//�P�� 2008/03/07 add
						if(!resultFmVOs[j].getTan2Old().equals("0") && !resultFmVOs[j].getTan2Old().equals("0.00"))
							formRow.setTan2Old(resultFmVOs[j].getTan2Old());//�P��Old 2008/06/02 add
						midForm.addOrderHistoryVO(resultFmVOs[j]);
					}
					vos.add(resultFmVOs[j]);
				}
				cnt += max;
			}
			if(resultFmVOs.length !=0){
				midForm.setVosList(vosList);//�S�y�[�W����VO�̃��X�g
				midForm.setPageCount(pageCount);//�y�[�W��
				midForm.setCurrentPage(0);//���݂̃y�[�W
				midForm.setAllRowCount(resultFmVOs.length);//�S�y�[�W�̌���
			}
		

			if(resultFmVOs.length ==0){
				errors = new ActionErrors();
				if(result.getDescription().equals(OrderHistoryDelegate.EXCEED_REINPUT))
					//�����������z���܂����B
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError(
							"errors.exceed.reinput",
							DataFormatUtils.setFormatString(String.valueOf(OrderHistoryMidForm.MAX_REFER_COUNT)),
							DataFormatUtils.setFormatString(String.valueOf(result.getCount()))));
				else
					//�f�[�^�����݂��܂���
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
			}else{
				//��������܂����B
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
			}
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			//�G���[���������܂����B
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}
	
	
	/*****************************************************************************************
	 * 		�������M																	     *
	 * 																						 *
	 *****************************************************************************************/
	
	private ActionErrors teiSei (OrderHistoryMidForm midForm,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = midForm.getCurrentPage();//���݂̃y�[�W
		int max = OrderHistoryMidForm.MAX_ROW_COUNT;
		OrderHistoryVO[] currentVOs = midForm.getVosList(page);//���݂̃y�[�W��VO�����o��		

		for(int i = 0; i < midForm.getSize();i++){	
			OrderHistoryFormRow formRow = midForm.getFormRow(i);

			if(!formRow.getCheck_Mid()){
				lisFmVO.add(null);	
				continue;			}
				
			OrderHistoryVO fmVO = currentVOs[i];

			
			//�ύX���Z�b�g
			fmVO.setCheck_Mid(formRow.getCheck_Mid());
			fmVO.setSinKyuKbn(DataFormatUtils.getKbnCod(formRow.getSinKyuKbn()));//2003/07/18 add
			fmVO.setHacSuu(formRow.getHacSuu());
			fmVO.setNki(formRow.getNkiYear().trim(),formRow.getNkiMonth().trim(),formRow.getNkiDay().trim());			
			fmVO.setNhnMeiKj(formRow.getNhnMeiKj().trim());
			fmVO.setCmt(formRow.getCmt().trim());//�R�����g
			
			if(formRow.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
				fmVO.setTan2(formRow.getTan2());
				fmVO.setKin(formRow.getKin());
				//�P�����͂Ȃ��E���z���͂���@�P�������z�^�������i���񂾂��Z�o�j
				if(formRow.getTan2().equals("") && formRow.getKinOld().equals("") && !formRow.getKin().equals("")){
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal kin = new BigDecimal(formRow.getKin().trim());
					BigDecimal tan2 = kin.divide(hacsuu, 2, BigDecimal.ROUND_HALF_UP);//������3�ʂ��l�̌ܓ�
	
					fmVO.setTan2(String.valueOf(tan2).trim());	
					fmVO.setKin(formRow.getKin().trim());
				}
				//�P�����͂���E���z���͂Ȃ��@���z���P�����������i���񂾂��Z�o�j
				if(formRow.getKin().equals("") && formRow.getTan2Old().equals("") && !formRow.getTan2().equals("")){
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal tan2 = new BigDecimal(formRow.getTan2().trim());
					BigDecimal kin = hacsuu.multiply(tan2);

					fmVO.setTan2(formRow.getTan2().trim());
					fmVO.setKin(String.valueOf(kin).trim());


				}
				//�P�����͂���E���z���͂���
				if(!formRow.getTan2().equals("") && !formRow.getKin().equals("")){
					fmVO.setTan2(formRow.getTan2().trim());	
					fmVO.setKin(formRow.getKin().trim());
				}
			}else{
				fmVO.setTan2(formRow.getTan2().trim());
				fmVO.setKin(formRow.getKin().trim());
			}

			fmVO.setBiKou2(formRow.getBiKou2());
			
			lisFmVO.add(fmVO);	
		}

		ArrayList allVos = midForm.getAllVos();//�S�y�[�W��VO�̃��X�g�i���ʑS�́j
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		
		for(int i = 0; i<allVos.size(); i++){

			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					
					if(lisFmVO.get(j) != null){
				
						executeChecked = true;
					}
				}
			}

			if(i == allVos.size())
				continue;

			OrderHistoryVO vo = (OrderHistoryVO)allVos.get(i);

			if(!vo.getCheck_Mid()){
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
			//�u�������M�v���s
			OrderHistoryResult[] results = bzDelegate.doTeiseiSousin((OrderHistoryVO[])allVos.toArray(new OrderHistoryVO[allVos.size()]));
			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();

					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(OrderHistoryDelegate.ANOTHER_USER_UPDATE)){
					    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
					}else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}

			if(errors == null){
				 //�o�����[�I�u�W�F�N�g���̂��i�[
				HashMap keyMap = null;
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;
					if(keyMap == null)
						keyMap = results[i].getKeyMap();//�����X�V���L�[�̎擾 2005/09/15 add

					midForm.setOrderHistoryVO_all(i, results[i].getVO());
						
					if(page * max <= i && i < (page + 1) * max){
						midForm.getFormRow(i % max).setCheck_Mid(false);//�`�F�b�N���O��
						midForm.getFormRow(i % max).setCmt(results[i].getVO().getCmt());  //2004.01.21 add
						midForm.getFormRow(i % max).setNyoSuu(results[i].getVO().getNyoSuu());  //2004.01.22 add
						if(!results[i].getVO().getNyoDte().equals("") && !results[i].getVO().getNyoDte().equals("0")){
							midForm.getFormRow(i % max).setNyoDte(DataFormatUtils.setFormatYYMMDD(results[i].getVO().getNyoDte()));//2004.08.03 add
						}
						midForm.getFormRow(i % max).setHacSuuOld(results[i].getVO().getHacSuuOld());//������Old 2005/06/02 add
						midForm.getFormRow(i % max).setKin(results[i].getVO().getKin()); //���z 2005/09/13 add
						midForm.getFormRow(i % max).setKinOld(results[i].getVO().getKinOld()); //���zOld 2008/06/02 add
						midForm.getFormRow(i % max).setBiKou2(results[i].getVO().getBiKou2());  
						midForm.getFormRow(i % max).setTan2(results[i].getVO().getTan2()); //�P�� 2008/03/07 add
						midForm.getFormRow(i % max).setTan2Old(results[i].getVO().getTan2Old());//�P��Old 2008/06/02 add
					}
				}
				//�����������ڂƓ��������ԍ����A�����X�V����VO�ɂ��Z�b�g 2005/09/15 add
				ArrayList fmVOs = midForm.getAllVos();
				Iterator keySet = keyMap.keySet().iterator();
				while (keySet.hasNext()) {
					String key = (String) keySet.next();
					OrderHistoryVO value = (OrderHistoryVO) keyMap.get(key);
					//VO�ɂ��Z�b�g
					for(int i = 0;i < fmVOs.size();i++){
						OrderHistoryVO vo = (OrderHistoryVO)fmVOs.get(i);
						if(vo.getUpdateKey().equals(key)){
							vo.setRrkTbl(value.getRrkTbl());
							vo.setUpdDte(value.getUpdDte());
							vo.setUpdJkk(value.getUpdJkk());
							midForm.setOrderHistoryVO_all(i, vo);
						}
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
	

	/******************************************************************************************
	 * 		�_�E�����[�h																	  *
	 * 																				          *
	 ******************************************************************************************/
	private ActionErrors download (OrderHistoryMidForm midForm, OrderHistoryVO queryVO,
									HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		ArrayList lisCsvVO = new ArrayList();
		
		try{
		
			//�������s
			OrderHistoryResult result = bzDelegate.doFindForDownload(queryVO);
			OrderHistoryVO[] resultFmVOs = result.getResultList();

			if(resultFmVOs.length == 0){
				errors = new ActionErrors();
				//�f�[�^�����݂��܂���
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				
			}else{
			
				String colNmStr = "������,�敪,�L���ԍ�,�\���i��,�^�C�g������,������,�������ԍ�,�s��,������,�o�͍�,���޺���,"
								+ "�P��,���z,�݌ɐ�,������,���ɐ�,���ɓ�,�[��,�[�i��,�R�����g��,���c��,�����ޖ���,���l�Q";
	
				for(int i = 0; i < resultFmVOs.length;i++){
					OrderHistoryCsvVO csvVO = new OrderHistoryCsvVO();
					OrderHistoryVO fmVO = resultFmVOs[i];
				
					//�f�[�^�Z�b�g
					csvVO.setHacCod(fmVO.getHacCod());//������
					if(!fmVO.getSinKyuKbn().trim().equals(""))
						csvVO.setSinKyuKbn(DataFormatUtils.getKbnString(fmVO.getSinKyuKbn().trim()));//�敪
					csvVO.setKigBng(fmVO.getKigBng().trim());//�L���ԍ�
					csvVO.setHjiHnb(fmVO.getHjiHnb().trim());//�\���i�� 2003/08/18 add
					csvVO.setTitKj(fmVO.getTitKj().trim());//�^�C�g������
					if(!fmVO.getHbiDte().trim().equals("") && !fmVO.getHbiDte().equals("0"))
						csvVO.setHbiDte(fmVO.getHbiDte().trim());//������
					csvVO.setHacSyoBng(fmVO.getHacSyoBng().trim());//�������ԍ�
					csvVO.setGyoBng(fmVO.getGyoBng().trim());//�s��
					if(!fmVO.getHacSyoDte().trim().equals("") && !fmVO.getHacSyoDte().equals("0"))
						csvVO.setHacSyoDte(fmVO.getHacSyoDte().trim());//������			
					if(!fmVO.getSyrZmiSgn().trim().equals(""))
						csvVO.setSyrZmiSgn(getHacZmiSgnString(Integer.parseInt(fmVO.getSyrZmiSgn().trim())));//�o�͍�
					if(!fmVO.getBunCod().trim().equals(""))
						csvVO.setBunCod(this.getBunCodString(Integer.parseInt(fmVO.getBunCod().trim())));//���޺���
					csvVO.setTan2(fmVO.getTan2().trim());//�P�� 2008/03/07 add
					csvVO.setKin(fmVO.getKin().trim());//���z 2005/09/15 add
					csvVO.setFukSziSuu(fmVO.getFukSziSuu().trim());//�݌ɐ�
					csvVO.setHacSuu(fmVO.getHacSuu().trim());//������
					csvVO.setNyoSuu(fmVO.getNyoSuu().trim());//���ɐ�
					if(!fmVO.getNyoDte().trim().equals("") && !fmVO.getNyoDte().equals("0"))
						csvVO.setNyoDte(fmVO.getNyoDte().trim());//���ɓ�
					if(fmVO.getNki() != 0)
						csvVO.setNki(fmVO.getNki()+"");//�[��
					csvVO.setNhnMeiKj(fmVO.getNhnMeiKj().trim());//�[�i��
					csvVO.setCmt(fmVO.getCmt().trim());//�R�����g
					csvVO.setCyuzan(fmVO.getCyuzan().trim());//���c��
					csvVO.setPrsFukSziCod(fmVO.getPrsFukSziCod().trim());//�����ރR�[�h
					csvVO.setFukSziNmkj(fmVO.getFukSziNmkj().trim());//�����ޖ���
					csvVO.setBiKou2(fmVO.getBiKou2().trim());//���l�Q
				
					lisCsvVO.add(csvVO);
				}
			
				errors = CsvUtils.returnCsvStream(res,"LightCsv",midForm.getQueryDaiKaiSkbCod(),colNmStr,lisCsvVO);
			}

		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			//�G���[���������܂����B
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}

	/********************************************************************************************
	 * 		�����`�[���s																		*
	 * 																							*
	 ********************************************************************************************/
	private ActionErrors dnpHakkou (OrderHistoryMidForm midForm,PrintOrdersQueryVO poQueryVO,
									HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		ArrayList tmpArr = new ArrayList();
		int page = midForm.getCurrentPage();//���݂̃y�[�W
		int max = OrderHistoryMidForm.MAX_ROW_COUNT;
		OrderHistoryVO[] currentVOs = midForm.getVosList(page);//���݂̃y�[�W��VO�����o��

        for(int i = 0; i < midForm.getSize();i++){	
			OrderHistoryFormRow formRow = midForm.getFormRow(i);
			
			//�`�F�b�N�Ȃ��͂ʂ���
			if(!formRow.getCheck_Mid()){
				lisFmVO.add(null);
				continue;
			}
				
			OrderHistoryVO fmVO = currentVOs[i];

			//�ύX���Z�b�g
			fmVO.setCheck_Mid(formRow.getCheck_Mid());
			fmVO.setSinKyuKbn(DataFormatUtils.getKbnCod(formRow.getSinKyuKbn()));//2003/07/18 add
			fmVO.setNki(formRow.getNkiYear().trim(),formRow.getNkiMonth().trim(),formRow.getNkiDay().trim());			
			fmVO.setHacSuu(formRow.getHacSuu().trim());
			fmVO.setNhnMeiKj(formRow.getNhnMeiKj().trim());
			fmVO.setCmt(formRow.getCmt().trim());//�R�����g

			if(formRow.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
				fmVO.setTan2(formRow.getTan2());
				fmVO.setKin(formRow.getKin());
				//�P�����͂Ȃ��E���z���͂���@�P�������z�^�������i���񂾂��Z�o�j
				if(formRow.getTan2().equals("") && formRow.getKinOld().equals("") && !formRow.getKin().equals("")){
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal kin = new BigDecimal(formRow.getKin().trim());
					BigDecimal tan2 = kin.divide(hacsuu, 2, BigDecimal.ROUND_HALF_UP);//������3�ʂ��l�̌ܓ�
	
					fmVO.setTan2(String.valueOf(tan2));	
					fmVO.setKin(formRow.getKin());
				}
				//�P�����͂���E���z���͂Ȃ��@���z���P�����������i���񂾂��Z�o�j
				if(formRow.getKin().equals("") && formRow.getTan2Old().equals("") && !formRow.getTan2().equals("")){
//					fmVO.setTan2(formRow.getTan2());
//					fmVO.setKin(String.valueOf(Double.parseDouble(formRow.getTan2()) * Integer.parseInt(formRow.getHacSuu())));
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal tan2 = new BigDecimal(formRow.getTan2().trim());
					BigDecimal kin = hacsuu.multiply(tan2);

					fmVO.setTan2(formRow.getTan2().trim());
					fmVO.setKin(String.valueOf(kin).trim());
				}
				//�P�����͂���E���z���͂���
				if(!formRow.getTan2().equals("") && !formRow.getKin().equals("")){
					fmVO.setTan2(formRow.getTan2().trim());	
					fmVO.setKin(formRow.getKin().trim());
				}
			}else{
				fmVO.setTan2(formRow.getTan2().trim());
				fmVO.setKin(formRow.getKin().trim());
			}

			fmVO.setBiKou2(formRow.getBiKou2());

			lisFmVO.add(fmVO);

		}

		ArrayList allVos = midForm.getAllVos();//�S�y�[�W��VO�̃��X�g�i���ʑS�́j
		
		boolean flg = false;
		//�����Ώێw��Ȃ�
		boolean executeChecked = false;
		
		for(int i = 0; i<allVos.size(); i++){

			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					
					if(lisFmVO.get(j) != null){
						executeChecked = true;
						//2004.01.20 add

						String str = ((OrderHistoryVO)allVos.get(i)).getKaiSkbCod() + "%" +
									 ((OrderHistoryVO)allVos.get(i)).getHacSyoDte() + "%" +
									 ((OrderHistoryVO)allVos.get(i)).getHacSyoBng() + "%" +
									 ((OrderHistoryVO)allVos.get(i)).getHacCod();
						if(tmpArr.indexOf(str) == -1){
						   tmpArr.add(str);
						}
					}
						
				}
			}

			if(i == allVos.size())
				continue;


			OrderHistoryVO vo = (OrderHistoryVO)allVos.get(i);

			if(!vo.getCheck_Mid()){
				allVos.set(i, null);
				continue;
			}else{  //2004.01.20 add

				String str = vo.getKaiSkbCod() + "%" +
							 vo.getHacSyoDte() + "%" +
							 vo.getHacSyoBng() + "%" +
							 vo.getHacCod();
				if(tmpArr.indexOf(str) == -1){
					tmpArr.add(str);
				}
			}

			executeChecked = true;

		}		

		poQueryVO.setPartOfQuery_arr(tmpArr);  //2004.01.20 add
		poQueryVO.setHasHacSyoBng(true);//���łɔ������ԍ��������Ă����true   //2004.01.20 add
		poQueryVO.setTeiDnpHakkou(true);//�����`�[���s�����̏ꍇ��true  //2005.09.14 add


		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","�����Ώ�"));
			return errors;	
		}
 		
		try{
			//�u�����`�[���s�v������
			OrderHistoryResult[] results = bzDelegate.doDnpHakkou((OrderHistoryVO[])allVos.toArray(new OrderHistoryVO[allVos.size()]));

			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
					 
					
					//�ق��̃��[�U�[���ύX�ς�
                    if(results[i].getDescription().equals(OrderHistoryDelegate.ANOTHER_USER_UPDATE)){
					    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				HashMap keyMap = null;
			
				ArrayList queryRows = new ArrayList();
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;

					if(keyMap == null)
						keyMap = results[i].getKeyMap();//�����X�V���L�[�̎擾 2005/09/15 add

					//�o�����[�I�u�W�F�N�g���̂��i�[
					midForm.setOrderHistoryVO_all(i, results[i].getVO());
					
					if(page * max <= i && i < (page + 1) * max){

						//�����σT�C�����Z�b�g������
						midForm.getFormRow(i % max).setSyrZmiSgn(this.getHacZmiSgnString(Integer.parseInt((results[i].getVO().getSyrZmiSgn()))));
						midForm.getFormRow(i % max).setCheck_Mid(false);//�`�F�b�N���O��
						midForm.getFormRow(i % max).setCmt(results[i].getVO().getCmt());  //2004.01.16 add
						midForm.getFormRow(i % max).setNyoSuu(results[i].getVO().getNyoSuu());  //2004.01.22 add
						if(!results[i].getVO().getNyoDte().equals("") && !results[i].getVO().getNyoDte().equals("0")){
							midForm.getFormRow(i % max).setNyoDte(DataFormatUtils.setFormatYYMMDD(results[i].getVO().getNyoDte()));//2004.08.03 add
						}
						midForm.getFormRow(i % max).setHacSuuOld(results[i].getVO().getHacSuuOld());//������Old 2005/06/02 add
						midForm.getFormRow(i % max).setKin(results[i].getVO().getKin());//���z 2005/09/13 add
						midForm.getFormRow(i % max).setKinOld(results[i].getVO().getKinOld());//���zOld 2008/06/02 add
						midForm.getFormRow(i % max).setBiKou2(results[i].getVO().getBiKou2());
						midForm.getFormRow(i % max).setTan2(results[i].getVO().getTan2());//�P�� 2008/03/07 add
						midForm.getFormRow(i % max).setTan2Old(results[i].getVO().getTan2Old());//�P��Old 2008/06/02 add
					}
				}

				//�����������ڂƓ��������ԍ����A�����X�V����VO�ɂ��Z�b�g 2005/09/15 add
				ArrayList fmVOs = midForm.getAllVos();
				Iterator keySet = keyMap.keySet().iterator();
				int updDte = 0;
				int updJkk = 0;
				while (keySet.hasNext()) {
					String key = (String) keySet.next();
					OrderHistoryVO value = (OrderHistoryVO) keyMap.get(key);
					//VO�ɂ��Z�b�g
					for(int i = 0;i < fmVOs.size();i++){
						OrderHistoryVO vo = (OrderHistoryVO)fmVOs.get(i);
						updDte = value.getUpdDte();
						updJkk = value.getUpdJkk();
						if(vo.getUpdateKey().equals(key)){
							vo.setRrkTbl(value.getRrkTbl());
							vo.setUpdDte(updDte);
							vo.setUpdJkk(updJkk);
							midForm.setOrderHistoryVO_all(i, vo);
						}
					}
				}
				//�`�[���s��̍X�V�����ł������X�V�������Z�b�g���邽�߂ɓn��
				poQueryVO.setUpdDte(updDte);
				poQueryVO.setUpdJkk(updJkk);

			}

   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}

		return errors;		
			
	}

	/*********************************************************************************************
	 * 		�O100����100��																		 *
	 * 																							 *
	 *********************************************************************************************/
	private ActionErrors dispPre_NxtData (OrderHistoryMidForm midForm, 
									HttpServletRequest req,HttpServletResponse res, int addIndex){
		ActionErrors errors = null;
		OrderHistoryVO[] resultVOs = null;//�Oor���y�[�W��VO���i�[
		OrderHistoryVO[] currentVOs = null;//���݂̃y�[�W��VO���i�[
		
		try{
			currentVOs = midForm.getVosList(midForm.getCurrentPage());
		
		}catch(IndexOutOfBoundsException e){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
			return errors;
		}
		
		//�ύX����Ă���\���̂��鍀�ڂ��Z�b�g���Ȃ���
		for(int i = 0; i < currentVOs.length; i++){
			
			OrderHistoryFormRow formRow = midForm.getFormRow(i);
					
			currentVOs[i].setCheck_Mid(formRow.getCheck_Mid());
			currentVOs[i].setSinKyuKbn(DataFormatUtils.getKbnCod(formRow.getSinKyuKbn()));//2003/07/18 add
			currentVOs[i].setNki(formRow.getNkiYear().trim(),formRow.getNkiMonth().trim(),formRow.getNkiDay().trim());
			currentVOs[i].setHacSuu(formRow.getHacSuu().trim());
			currentVOs[i].setHacSuuOld(formRow.getHacSuuOld().trim());
			currentVOs[i].setNhnMeiKj(formRow.getNhnMeiKj().trim());
			currentVOs[i].setCmt(formRow.getCmt().trim());
			currentVOs[i].setKin(formRow.getKin()); //2005/09/13 add
			currentVOs[i].setKinOld(formRow.getKinOld()); //2008/06/02 add
			currentVOs[i].setBiKou2(formRow.getBiKou2());
			currentVOs[i].setTan2(formRow.getTan2()); //2008/03/07 add
			currentVOs[i].setTan2Old(formRow.getTan2Old().trim()); //2008/06/02 add

		}

		//�Ώۃy�[�W�̃f�[�^���擾
		try{
			ArrayList check_mid_index = new ArrayList();
			
			//�Oor���y�[�W��VO���擾
			resultVOs = midForm.getVosList(midForm.getCurrentPage() + addIndex);
			
			//����������
			midForm.removeOrderHistoryVO();
			midForm.removeAllRow();
			
			String breakKey_before = "";
					
			//���ʂ��t�H�[���ɃZ�b�g
			midForm.setSize(resultVOs.length);
			midForm.setCount(resultVOs.length);
			midForm.clearTableField();
			
			for(int j = 0; j < resultVOs.length; j++){
				OrderHistoryFormRow formRow = midForm.getFormRow(j);
				
				StringBuffer str = new StringBuffer();
							    
			    //������E�L���ԍ�
				str.append(resultVOs[j].getHacCod()).append(resultVOs[j].getHjiHnb());
			       
				String breakKey = str.toString();
				if(!breakKey.equals(breakKey_before)){
					formRow.setBreakflg(true);
				}
				breakKey_before = str.toString();
			    //�L�[����
			    formRow.setDaiKaiSkbCod(resultVOs[j].getDaiKaiSkbCod());
			    formRow.setKaiSkbCod(resultVOs[j].getKaiSkbCod());
			    formRow.setKigBng(resultVOs[j].getKigBng());
			    
			    //�������ʃZ�b�g
			    formRow.setHacCod(resultVOs[j].getHacCod());//������
			    if(!resultVOs[j].getSinKyuKbn().equals(""))
			    	formRow.setSinKyuKbn(DataFormatUtils.getKbnString(resultVOs[j].getSinKyuKbn()));//�敪
			    formRow.setHjiHnb(resultVOs[j].getHjiHnb());//�\���i��
			    formRow.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultVOs[j].getHbiDte()));//������
			    formRow.setHacSyoBng(resultVOs[j].getHacSyoBng());//�������ԍ�
			    formRow.setGyoBng(resultVOs[j].getGyoBng());//�s�ԍ�
			    //������
			    formRow.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultVOs[j].getHacSyoDte()));
				//�o�͍σT�C��
			    if(!resultVOs[j].getSyrZmiSgn().equals(""))
			    	formRow.setSyrZmiSgn(getHacZmiSgnString(Integer.parseInt(resultVOs[j].getSyrZmiSgn())));
				//���ރR�[�h
			    if(!resultVOs[j].getBunCod().equals(""))	
			    	formRow.setBunCod(this.getBunCodString(Integer.parseInt(resultVOs[j].getBunCod())));//��ڽ�����޺���
				if(!resultVOs[j].getKin().equals("0") && !resultVOs[j].getKin().equals("0.00"))
				    formRow.setKin(resultVOs[j].getKin());
				if(!resultVOs[j].getKinOld().equals("0") && !resultVOs[j].getKinOld().equals("0.00"))
					formRow.setKinOld(resultVOs[j].getKinOld());
			    formRow.setFukSzisuu(DataFormatUtils.setFormatString(resultVOs[j].getFukSziSuu()));//��ڽ�����ގc
			    formRow.setHacSuu(resultVOs[j].getHacSuu());//������
				formRow.setHacSuuOld(resultVOs[j].getHacSuuOld());//������Old
			    formRow.setNyoSuu(DataFormatUtils.setFormatString(resultVOs[j].getNyoSuu()));//���ɐ�
				//�[��
			    formRow.setNkiYear(resultVOs[j].getNkiYear());
			    formRow.setNkiMonth(resultVOs[j].getNkiMonth());
			    formRow.setNkiDay(resultVOs[j].getNkiDay());
			    formRow.setNhnMeiKj(resultVOs[j].getNhnMeiKj());//�[�i��
			    formRow.setCheck_Mid(resultVOs[j].getCheck_Mid());//�`�F�b�N�ޯ��
			    formRow.setCmt(resultVOs[j].getCmt().trim());//�R�����g
				formRow.setBiKou2(resultVOs[j].getBiKou2().trim());//���l�Q
				if(!resultVOs[j].getTan2().equals("0") && !resultVOs[j].getTan2().equals("0.00")) //�P�� 2008/03/07 add
					formRow.setTan2(resultVOs[j].getTan2());
				if(!resultVOs[j].getTan2Old().equals("0") && !resultVOs[j].getTan2Old().equals("0.00")) //�P��2Old 2008/06/02 add
					formRow.setTan2Old(resultVOs[j].getTan2Old());
			    //���ɓ�
				if(!resultVOs[j].getNyoDte().equals("") && !resultVOs[j].getNyoDte().equals("0"))	
			    	formRow.setNyoDte(DataFormatUtils.setFormatYYMMDD(resultVOs[j].getNyoDte()));//���ɓ�

				//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X���i�[����
				if(formRow.getCheck_Mid()){
					check_mid_index.add(j+"");
				}

			}
			midForm.setCurrentPage(midForm.getCurrentPage() + addIndex);//���݂̃y�[�W

			midForm.setOrderHistoryVO(new ArrayList(Arrays.asList(resultVOs)));//���݂�VO
			midForm.setCheck_index(check_mid_index);//�`�F�b�N�{�b�N�X�̃C���f�b�N�X���Z�b�g
			
		}catch(IndexOutOfBoundsException e){
			resultVOs = new OrderHistoryVO[0];
		}		
		if(resultVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
		}
			
		return errors;			
			
	}
	
	
//----------------------------------------------------------------------------------------------------
	
	/**
	 *  �`�F�b�N�{�b�N�X�Ƀ`�F�b�N�����Ȃ������\�b�h
	 */
	private void setCheckbox_checked(OrderHistoryMidForm midForm){
		
		ArrayList check_mid_index = midForm.getCheck_Mid_index();
	    int index_mid = 0;

		for(int i = 0; i < midForm.getSize();i++){	
			OrderHistoryFormRow formRow = midForm.getFormRow(i);
			if(check_mid_index.size() != index_mid){
				if(i == Integer.parseInt(check_mid_index.get(index_mid)+"")){
					formRow.setCheck_Mid(true);
					index_mid++;
				}
			}
		}
	}

//---------------------------------------------------------------------------------------------------	
	
	
   /** �����σT�C���̕\���������Ԃ� **/ 
	private String getHacZmiSgnString(int sgn){
		String haczmisgn = "";
		switch(sgn){
			case 0:
				haczmisgn = OrderHistoryMidForm.MISYRYK;
				break;
			case 1:
				haczmisgn = OrderHistoryMidForm.SYRZMI;
				break;
		}
		return haczmisgn;
	}

   /** ���ރR�[�h�̕\���������Ԃ� **/ 
	public String getBunCodString(int sgn){
		String buncod = "";
		switch(sgn){
			case 0:
				buncod = OrderHistoryMidForm.PRS;
				break;
			case 1:
			case 2:
				buncod = OrderHistoryMidForm.SZI;
				break;
		}
		return buncod;
	}
	
	
	
}

