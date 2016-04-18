/**
* StockDaysAction  �݌ɓ����}�X�^�[�����e�i���X�A�N�V�����N���X
*
*	�쐬��    2003/06/09
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/
* 			�E
* 
*/

  package com.jds.prodord.master.stockdays;
  import com.jds.prodord.common.*;

  import javax.servlet.http.*;
  import org.apache.struts.action.*;
  import org.apache.struts.util.*;
  import java.sql.*;
  import java.util.*;

  public class StockDaysAction extends Action {

	private static  StockDaysDelegate bzDelegate = new StockDaysDelegate();


//===================================================================

	public ActionForward perform(ActionMapping mapping,ActionForm form
											,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		StockDaysForm fmForm = (StockDaysForm)form;
		StockDaysVO fmVO = new StockDaysVO();
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
//		System.out.println("�}�X�^�[���j���[���F�݌ɓ��������e�i���X�{�^����������܂���");
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


		//***  �݌ɓ��������e�i���X�̎��s�{�^������������  ***

		if(fmForm.getCommand().equals(StockDaysForm.COMMAND_JIKKOU)){

//			System.out.println("�݌ɓ��������e�i���X���F���s�{�^����������܂���");

			//�����敪���Ɖ�
			if(fmForm.getPrcKbn().equals("1")){
				errors = this.select(fmForm,req,res);
			  if(errors == null){	//��������
			  	req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
			  }

			//�����敪���o�^
			}else if(fmForm.getPrcKbn().equals("2")){
				errors = this.insert(fmForm,req,res);
			  if(errors == null){	//��������
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
			  }

			//�����敪���ύX
			}else if(fmForm.getPrcKbn().equals("3")){
				errors = this.update(fmForm,req,res);
				if(errors == null){	//��������
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				}

			//�����敪���폜
			}else if(fmForm.getPrcKbn().equals("4")){
				errors = this.delete(fmForm,req,res);
				if(errors == null){	//��������
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.delete.end"));
				}
			}


		//***  �݌ɓ��������e�i���X�̃N���A�{�^������������  ***
			}else if(fmForm.getCommand().equals(StockDaysForm.COMMAND_CLEAR)){

//				System.out.println("�݌ɓ��������e�i���X���F�N���A�{�^����������܂���");
				fmForm.clearAll();
				fmForm.setCommand("INIT");
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
	
//=====  �Ɖ�\�b�h  ================================================================

	private ActionErrors select (StockDaysForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		StockDaysVO fmVO = new StockDaysVO();	
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());


		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();


		//��Ў��ʃR�[�h	
		fmVO.setKaiSkbCod(kai[0]);
		//�����N
		fmVO.setRank(fmForm.getRank());	
		//�`�ԃR�[�h
		fmVO.setKetCod(ket[0]);

		try{
			//�������s
			StockDaysVO[] resultFmVOs = bzDelegate.doFind(fmVO);

			fmForm.removeStockDaysVO();
				
			if(resultFmVOs.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVOs.length),StockDaysForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();

				for(int i = 0;i < resultFmVOs.length;i++){
					fmForm.setOutKaiSkbCod(i,resultFmVOs[i].getKaiSkbCod());
					fmForm.setOutRank(i,resultFmVOs[i].getRank());
					fmForm.setOutKetCod(i,resultFmVOs[i].getKetCod());
					fmForm.setOutStockDays(i,resultFmVOs[i].getOutStockDays());

					fmForm.addStockDaysVO(resultFmVOs[i]);
				}
			}else{
				fmForm.setSize(StockDaysForm.DEFAULT_ROW_COUNT);
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

	

//=====  �o�^���\�b�h  ================================================================

	private ActionErrors insert (StockDaysForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		StockDaysVO fmVO = new StockDaysVO();	
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������

		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(kai[0]);
		fmVO.setRank(fmForm.getRank());
		fmVO.setKetCod(ket[0]);

		for(int i = 0; i < StockDaysForm.DEFAULT_ROW_COUNT;i++){

			fmVO.setOutStockDays(fmForm.getOutStockDays(i));

		}

		lisFmVO.add(fmVO);

		try{
			//�o�^���s
			StockDaysResult result = bzDelegate.doInsert(fmVO);
		
			//�G���[�`�F�b�N
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //�o�^���悤�Ƃ����f�[�^�����łɑ���
				if(result.getDescription().equals(StockDaysDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				 //�o�^���悤�Ƃ����f�[�^���_���폜��

				else if(result.getDescription().equals(StockDaysDelegate.LOGICAL_DELETE))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));

			}

					
			if(errors == null){
				for(int i = 0;i < StockDaysForm.DEFAULT_ROW_COUNT;i++){

					fmForm.setOutKaiSkbCod(i,fmVO.getKaiSkbCod());
					fmForm.setOutRank(i,fmVO.getRank());
					fmForm.setOutKetCod(i,fmVO.getKetCod());
				
				}
				
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	
		
		return errors;			
	}



//=====  �X�V���\�b�h  ================================================================

	private ActionErrors update (StockDaysForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
			
		for(int i = 0; i < fmForm.getSize();i++){

			StockDaysVO fmVOs = fmForm.getStockDaysVO(i);

			if(!fmForm.getStockDays().trim().equals("")){
				fmVOs.setOutStockDays(fmForm.getStockDays());  //�ꊇ�ύX
			}else{
				if(!fmForm.getExecute(i)){  //falese�łȂ����
					lisFmVO.add(null);	
					continue;
				}
				fmVOs.setOutStockDays(fmForm.getOutStockDays(i));  //�ʏ�ύX
			}
			lisFmVO.add(fmVOs);	
		}
		
		try{
			//�X�V���s

			StockDaysResult[] results = bzDelegate.doUpdate((StockDaysVO[])lisFmVO.toArray(new StockDaysVO[lisFmVO.size()]));

			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(StockDaysDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(StockDaysDelegate.LOGICAL_DELETE)||
   					   results[i].getDescription().equals(StockDaysDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate",String.valueOf(i+1)));
				}
			}


			if(errors == null){
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					fmForm.setExecute(i,false);
					fmForm.setStockDaysVO(i, results[i].getVO());
	
					fmForm.setOutStockDays(i,results[i].getVO().getOutStockDays());
					fmForm.setStockDays("");
				}
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;
	}


//=====  �폜���\�b�h  ================================================================

	private ActionErrors delete (StockDaysForm fmForm,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������
				
		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//�`�F�b�N�Ȃ�
				lisFmVO.add(null);	
				continue;
			}
			//�`�F�b�N����
			StockDaysVO fmVO = fmForm.getStockDaysVO(i);
			lisFmVO.add(fmVO);
		}
		try{
			//�폜���s
			StockDaysResult[] result = bzDelegate.doDelete((StockDaysVO[])lisFmVO.toArray(new StockDaysVO[lisFmVO.size()]));

			//�G���[�`�F�b�N
		for(int i = 0; i < result.length;i++){
			if(result[i] != null && !result[i].isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //�ق��̃��[�U�[���ύX�ς�
				if(result[i].getDescription().equals(StockDaysDelegate.ANOTHER_USER_UPDATE)) 
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate",String.valueOf(1)));
				 //�폜���悤�Ƃ����f�[�^���_���폜�� ��  ���݂��Ȃ�
				else if(result[i].getDescription().equals(StockDaysDelegate.LOGICAL_DELETE)||
					    result[i].getDescription().equals(StockDaysDelegate.NOT_EXIST))	

					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));

			}
		}
			//�폜�������R�[�h����ʂ������
			if(errors == null){
				int index = 0;
				for(int i = 0; i < result.length;i++,index++){
					if(result[i] == null)
						continue;

					fmForm.removeTableField(index);
					fmForm.removeStockDaysVO(index);
					index--;
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


