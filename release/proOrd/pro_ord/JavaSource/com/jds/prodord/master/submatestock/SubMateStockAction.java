/**                                                                                                                                                                                                                                      /**
* SubMateStockAction  �����ލ݌Ƀ����e�i���X  �A�N�V�����N���X
*
*	�쐬��    2003/08/18
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*	�ύX��    2003/09/18
*	�ύX��    �i�m�h�h�j����  ���� 
*      �ύX���e  �������ڂɃT���v���敪��ǉ�
* 		 2004/07/02�i�m�h�h�j�g�c
* 			�E�R�s�[���y�[�X�g�@�\�ǉ�
*/

package com.jds.prodord.master.submatestock;

import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import java.io.*;

  public class SubMateStockAction extends Action {

	private static  SubMateStockDelegate bzDelegate = new SubMateStockDelegate();


//===================================================================

	public ActionForward perform(ActionMapping mapping,ActionForm form
											,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		SubMateStockForm fmForm = (SubMateStockForm)form;
		SubMateStockVO fmVO = new SubMateStockVO();
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

		//***  �}�X�^�[���j���[�̍݌ɓ��������e�i���X�{�^������������  ***
		if(fmForm.getCommand().equals("INIT")){
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

			ArrayList kaiList = logonForm.getKaiSkbCodList();

			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);

			fmForm.setHyoKbn(logonForm.getHyoKbn());
		}

		//***  �����ލ݌Ƀ����e�i���X�̎��s�{�^������������  ***
		if(fmForm.getCommand().equals(SubMateStockForm.COMMAND_JIKKOU)){


			//�����敪���Ɖ�
			if(fmForm.getPrcKbn().equals("1")){
					errors = this.kigoban_search(fmForm,req,res); //�L���ԍ����ݔ���
				if(errors == null)  //��������
					errors = this.search(fmForm,req,res); //����
				if(errors == null)	//��������
			 	 	req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));

			//�����敪���X�V
			}else{			  
					errors = this.update(fmForm,req,res);
				if(errors == null)  //��������
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
			}


		//***  �����ލ݌Ƀ����e�i���X�̃N���A�{�^������������  ***
		}else if(fmForm.getCommand().equals(SubMateStockForm.COMMAND_CLEAR)){

			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));


		// ��\��t���������  2004/06/29 add
		}else if(fmForm.getCommand().equals(SubMateStockForm.COMMAND_PASTE)){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
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

//=====  �L���ԍ����ݔ��菈��  ================================================================
	private ActionErrors kigoban_search (SubMateStockForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;

		SubMateStockVO fmVO = new SubMateStockVO();

		String daikaiskbcod = fmForm.getQueryDaiKaiSkbCod();
		ArrayList kaiskbcod = fmForm.getQueryKaiSkbCodList();
		ArrayList kigo_arr =  new ArrayList();
		ArrayList kigo_ArrErr = new ArrayList();
		ArrayList search_arr = new ArrayList();

		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(daikaiskbcod);
		fmVO.setQueryKaiSkbCodList(kaiskbcod);
		kigo_arr = DataFormatUtils.supplement_kigBng(fmForm.getKigo_arr());//�L�����ȗ����͑Ή� 2003/09/24
		fmVO.setKigoBan_arr(kigo_arr);

		try{
			//�������s
			/**
			 * �s��̂��̏ꂵ�̂��̑Ή��ł��B
			 * search_arr�ɂ́A 0�F�G���[�i�Ԃ̓��͔ԍ� 1�FOK�i�Ԃ̋L���ԍ����Z�b�g����Ă���
			 * **/
			search_arr = bzDelegate.kigoban_search(fmVO);
			kigo_ArrErr = (ArrayList)search_arr.get(0);

		if(kigo_ArrErr.size() != 0){
			errors = new ActionErrors();			

			for(int i=0;i<kigo_ArrErr.size();i++){
				errors.add("",new ActionError("errors.insert.notfound","�L���ԍ�("+(String)kigo_ArrErr.get(i)+"�Ԗ�)"));
			}
		}else{
			//kigo_arr�́A�ȗ��E�L���ԍ������藐��Ă��邪�A��������SQL�͋L���ԍ��ł̌����̂���OK�i�Ԃ̋L���ԍ����ăZ�b�g
			kigo_arr = (ArrayList)search_arr.get(1);	
			//VO�̕ێ����s���Ă��Ȃ��̂ŁAForm�ɉ��u��
			fmForm.setKigbng_arr(kigo_arr);
		}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));  //�G���[���������܂���
		}

		return errors;
	}



//=====  �Ɖ��  ================================================================

	private ActionErrors search (SubMateStockForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;

		SubMateStockVO fmVO = new SubMateStockVO();

		String daikaiskbcod = fmForm.getQueryDaiKaiSkbCod();
		ArrayList kaiskbcod = fmForm.getQueryKaiSkbCodList();
		ArrayList lisFmVO = new ArrayList();
		ArrayList kigo_arr =  new ArrayList();

		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(daikaiskbcod);
		fmVO.setQueryKaiSkbCodList(kaiskbcod);
		fmVO.setSmpKbn(fmForm.getSmpKbnSel()); //2003/09/18
		//���͕i�Ԃ̍ăZ�b�g�ˌ������Ɏ擾�����L���ԍ����Z�b�g����悤�ɕύX
		fmVO.setKigoBan_arr(fmForm.getKigbng_arr());

		try{
			//�������s
			SubMateStockVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			fmForm.removeSubMateStockVO();
			
			if(resultFmVOs.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVOs.length),SubMateStockForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();

				for(int i = 0;i < resultFmVOs.length;i++){

					if(i < resultFmVOs.length-1){
						if(i == 0)
							fmForm.setHidFlag(i,false);
						if(resultFmVOs[i].getKigoBan().equals(resultFmVOs[i+1].getKigoBan())){
							fmForm.setHidFlag(i+1,true);
						}else{
							fmForm.setHidFlag(i+1,false);
						}
					}
					fmForm.setHidDaiKaiSkbCod(i,resultFmVOs[i].getHidDaiKaiSkbCod());
					fmForm.setSmpKbn(i,resultFmVOs[i].getSmpKbn()); //2003/09/18
					fmForm.setKigoBan(i,resultFmVOs[i].getHjiHnb());
					fmForm.setHuksizaiCod(i,resultFmVOs[i].getHuksizaiCod());
					fmForm.setHuksizaiMei(i,resultFmVOs[i].getHuksizaiMei());
					fmForm.setHuksizaiZaiko(i,DataFormatUtils.setFormatString(resultFmVOs[i].getHuksizaiZaiko()));
					fmForm.setHidUpdDte(i,resultFmVOs[i].getHidUpdDte());					
					fmForm.setHidUpdJkk(i,resultFmVOs[i].getHidUpdJkk());					
					fmForm.addSubMateStockVO(resultFmVOs[i]);
				}

			fmForm.setAtai(Integer.toString(resultFmVOs.length));


			
			}else{
				fmForm.setSize(SubMateStockForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));   //�Ώۃf�[�^�����݂��܂���
			}


		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));  //�G���[���������܂���
		}		

		return errors;			
	}



//=====  �X�V���\�b�h  ================================================================

	private ActionErrors update(SubMateStockForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
			
		for(int i = 0; i < fmForm.getSize();i++){
			SubMateStockVO fmVO = fmForm.getSubMateStockVO(i);
			if(!fmForm.getTeiseiSuu(i).equals("") && !fmForm.getTeiseiSuu(i).equals("0")){
				if(fmForm.getSign(i) == false)
					fmVO.setHuksizaiZaiko(Integer.toString(Integer.parseInt(fmVO.getHuksizaiZaiko()) + Integer.parseInt(fmForm.getTeiseiSuu(i))));
				if(fmForm.getSign(i) == true)
					fmVO.setHuksizaiZaiko(Integer.toString(Integer.parseInt(fmVO.getHuksizaiZaiko()) - Integer.parseInt(fmForm.getTeiseiSuu(i))));
			}else{
				lisFmVO.add(null);
				continue;
			}



			lisFmVO.add(fmVO);	
		}
		
		try{
			//�X�V���s
			SubMateStockResult[] results = bzDelegate.doUpdate((SubMateStockVO[])lisFmVO.toArray(new SubMateStockVO[lisFmVO.size()]));

			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(SubMateStockDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(SubMateStockDelegate.LOGICAL_DELETE)||
   					   results[i].getDescription().equals(SubMateStockDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				}
			}


			if(errors == null){

				fmForm.setPrcKbn("1");
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					fmForm.setSubMateStockVO(i, results[i].getVO());
					fmForm.setHuksizaiZaiko(i,DataFormatUtils.setFormatString(results[i].getSubMateStock().getHuksizaiZaiko()));
					fmForm.setSign(i,true);
					fmForm.setTeiseiSuu(i,"");
					fmForm.pointClear();
				}
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;
	}
//=====  �z��֋�̕�����̗v�f��ǉ����郁�\�b�h  ================================================================

	public ArrayList addBlankElement(ArrayList arr){

	for(int z = arr.size(); z<10; z++){
		arr.add("");
	}
		return arr;
	}
	
	/** 
	 * �\��t��
	 * @param fmForm
	 */
	public void dataPaste(SubMateStockForm fmForm){
		
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
						
						if(fmForm.getKigoBan0().equals("")){
							fmForm.setKigoBan0(str);
							continue;
						}
						if(fmForm.getKigoBan1().equals("")){
							fmForm.setKigoBan1(str);					
							continue;
						}
						if(fmForm.getKigoBan2().equals("")){
							fmForm.setKigoBan2(str);
							continue;
						}
						if(fmForm.getKigoBan3().equals("")){
							fmForm.setKigoBan3(str);
							continue;
						}
						if(fmForm.getKigoBan4().equals("")){
							fmForm.setKigoBan4(str);
							continue;
						}
						if(fmForm.getKigoBan5().equals("")){
							fmForm.setKigoBan5(str);
							continue;
						}
						if(fmForm.getKigoBan6().equals("")){
							fmForm.setKigoBan6(str);
							continue;
						}
						if(fmForm.getKigoBan7().equals("")){
							fmForm.setKigoBan7(str);
							continue;
						}
						if(fmForm.getKigoBan8().equals("")){
							fmForm.setKigoBan8(str);
							continue;
						}
						if(fmForm.getKigoBan9().equals("")){
							fmForm.setKigoBan9(str);
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
