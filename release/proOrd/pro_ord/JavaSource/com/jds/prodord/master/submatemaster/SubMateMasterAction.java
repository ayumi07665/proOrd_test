/**
* SubMateMasterAction  �����ރ}�X�^�[�����e�i���X  �A�N�V�����N���X
*
*	�쐬��    2003/07/16
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/
* 			�E
* 
*/

  package com.jds.prodord.master.submatemaster;
  import com.jds.prodord.common.*;

  import javax.servlet.http.*;
  import org.apache.struts.action.*;
  import org.apache.struts.util.*;
  import java.sql.*;
  import java.util.*;

  public class SubMateMasterAction extends Action {

	private static  SubMateMasterDelegate bzDelegate = new SubMateMasterDelegate();


//===================================================================

	public ActionForward perform(ActionMapping mapping,ActionForm form
											,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		SubMateMasterForm fmForm = (SubMateMasterForm)form;
		SubMateMasterVO fmVO = new SubMateMasterVO();
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
//		System.out.println("�}�X�^�[���j���[���F�{�^����������܂���");
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
			
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			
//			System.out.println("��\��ЁF"+ daiKaiSkbCod);
//			System.out.println("��Ў��ʁF"+ KaiSkbCod);

		}


		//*** �����ރ}�X�^�[�����e�̎��s�{�^������������  ***

		if(fmForm.getCommand().equals(SubMateMasterForm.COMMAND_JIKKOU)){

//			System.out.println("�����ރ}�X�^�[�����e�����e���F���s�{�^����������܂���");

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
			}else if(fmForm.getCommand().equals(SubMateMasterForm.COMMAND_CLEAR)){

//				System.out.println("�N���A�{�^����������܂���");
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

	private ActionErrors select (SubMateMasterForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		SubMateMasterVO fmVO = new SubMateMasterVO();
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());

		String[] kai = fmForm.getKaiSkbCod();



		//��Ў��ʃR�[�h	
		fmVO.setKaiSkbCod(kai[0]);
//			System.out.println("��Ў��ʁF"+ fmVO.getKaiSkbCod());		
		
		//�����޺���
		fmVO.setHukSziCod(fmForm.getHukSziCod());	

		try{
			//�������s
			SubMateMasterVO[] resultFmVOs = bzDelegate.doFind(fmVO);

			fmForm.removeSubMateMasterVO();
				
			if(resultFmVOs.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVOs.length),SubMateMasterForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();

				for(int i = 0;i < resultFmVOs.length;i++){
					fmForm.setHidDaiKaiSkbCod(i,resultFmVOs[i].getHidDaiKaiSkbCod());
					fmForm.setHidKaiSkbCod(i,resultFmVOs[i].getHidKaiSkbCod());
					fmForm.setOutHukSziCod(i,resultFmVOs[i].getOutHukSziCod());
					fmForm.setOutBunruiCod(i,resultFmVOs[i].getOutBunruiCod());
					fmForm.setOutHatcCod(i,resultFmVOs[i].getOutHatcCod());
					fmForm.setOutHukSziMei(i,resultFmVOs[i].getOutHukSziMei());
					fmForm.setHidUpdDte(i,resultFmVOs[i].getHidUpdDte());
					fmForm.setHidUpdJkk(i,resultFmVOs[i].getHidUpdJkk());
					
					fmForm.addSubMateMasterVO(resultFmVOs[i]);
				}
			}else{
				fmForm.setSize(SubMateMasterForm.DEFAULT_ROW_COUNT);
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

	private ActionErrors insert (SubMateMasterForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		SubMateMasterVO fmVO = new SubMateMasterVO();	
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������


		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setHukSziCod(fmForm.getHukSziCod());
		
		String[] kai = fmForm.getKaiSkbCod();

		//��Ў��ʃR�[�h	
		fmVO.setKaiSkbCod(kai[0]);

		lisFmVO.add(fmVO);

		for(int i = 0; i < SubMateMasterForm.DEFAULT_ROW_COUNT;i++){
			fmVO.setOutBunruiCod(fmForm.getOutBunruiCod(i).trim());
			fmVO.setOutHatcCod(fmForm.getOutHatcCod(i).trim());
			fmVO.setOutHukSziMei(fmForm.getOutHukSziMei(i).trim());
		}

		try{
			//�o�^���s
			SubMateMasterResult result = bzDelegate.doInsert(fmVO);

			//�G���[�`�F�b�N
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //�o�^���悤�Ƃ��������ރR�[�h�ł��łɓo�^�ς�
				if(result.getDescription().equals(SubMateMasterDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));

				 //�o�^���悤�Ƃ����f�[�^���_���폜��
				else if(result.getDescription().equals(SubMateMasterDelegate.LOGICAL_DELETE))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));

				//�����悪�Ȃ�
				if(result.getDescription().equals(SubMateMasterDelegate.NG)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound","������R�[�h"));
				}
			}
					
			if(errors == null){
				for(int i = 0;i < SubMateMasterForm.DEFAULT_ROW_COUNT;i++){

					fmForm.setOutHukSziCod(i,fmVO.getHukSziCod());
					fmForm.setOutBunruiCod(i,fmVO.getOutBunruiCod());
					fmForm.setOutHatcCod(i,fmVO.getOutHatcCod());
					fmForm.setOutHukSziMei(i,fmVO.getOutHukSziMei());
				
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

	private ActionErrors update (SubMateMasterForm fmForm,HttpServletRequest req,HttpServletResponse res){


		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  //�o�����[�I�u�W�F�N�g������

		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//�`�F�b�N�Ȃ�
				lisFmVO.add(null);	
				continue;
			}

/**			//�`�F�b�N����
			SubMateMasterVO fmVO = fmForm.getSubMateMasterVO(i);
			lisFmVO.add(fmVO);

				for(int x = 0; x < SubMateMasterForm.DEFAULT_ROW_COUNT;x++){
					fmVO.setHidDaiKaiSkbCod(fmForm.getHidDaiKaiSkbCod(x).trim());
					fmVO.setHidKaiSkbCod(fmForm.getHidKaiSkbCod(x).trim());
					fmVO.setOutHukSziCod(fmForm.getOutHukSziCod(x).trim());	
					fmVO.setOutBunruiCod(fmForm.getOutBunruiCod(x).trim());
					fmVO.setOutHatcCod(fmForm.getOutHatcCod(x).trim());
					fmVO.setOutHukSziMei(fmForm.getOutHukSziMei(x).trim());
					fmVO.setHidUpdDte(fmForm.getHidUpdDte(x));
					fmVO.setHidUpdJkk(fmForm.getHidUpdJkk(x));
				}
**/
			//�`�F�b�N����
			SubMateMasterVO fmVO = fmForm.getSubMateMasterVO(i);

			fmVO.setOutBunruiCod(fmForm.getOutBunruiCod(i).trim());
			fmVO.setOutHatcCod(fmForm.getOutHatcCod(i).trim());
			fmVO.setOutHukSziMei(fmForm.getOutHukSziMei(i).trim());
			
			lisFmVO.add(fmVO);
		}

		try{
			//�X�V���s
			SubMateMasterResult[] results = bzDelegate.doUpdate((SubMateMasterVO[])lisFmVO.toArray(new SubMateMasterVO[lisFmVO.size()]));

			
			//�G���[�������̂͂Ȃ������ׂ�
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //�ق��̃��[�U�[���ύX�ς�
					if(results[i].getDescription().equals(SubMateMasterDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(SubMateMasterDelegate.LOGICAL_DELETE)||
   					   results[i].getDescription().equals(SubMateMasterDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));

					if(results[i].getDescription().equals(SubMateMasterDelegate.NG))	
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",String.valueOf(i+1),"������R�[�h"));
				}
			}


			if(errors == null){
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					fmForm.setExecute(i,false);
					fmForm.setSubMateMasterVO(i, results[i].getVO());
	
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

	private ActionErrors delete (SubMateMasterForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  
		

		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//�`�F�b�N�Ȃ�
				lisFmVO.add(null);	
				continue;
			}
			//�`�F�b�N����
			SubMateMasterVO fmVO = fmForm.getSubMateMasterVO(i);
			lisFmVO.add(fmVO);
	
		}

		try{
			//�폜���s
			SubMateMasterResult[] result = bzDelegate.doDelete((SubMateMasterVO[])lisFmVO.toArray(new SubMateMasterVO[lisFmVO.size()]));

			//�G���[�`�F�b�N
		for(int i = 0; i < result.length;i++){
			if(result[i] != null && !result[i].isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //�ق��̃��[�U�[���ύX�ς�
				if(result[i].getDescription().equals(SubMateMasterDelegate.ANOTHER_USER_UPDATE)) 
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				 //�폜���悤�Ƃ����f�[�^���_���폜�� ��  ���݂��Ȃ�
				else if(result[i].getDescription().equals(SubMateMasterDelegate.LOGICAL_DELETE)||
					    result[i].getDescription().equals(SubMateMasterDelegate.NOT_EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
//					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));

			}
		}
			//�폜�������R�[�h����ʂ������
			if(errors == null){
				int index = 0;
				for(int i = 0; i < result.length;i++,index++){
					if(result[i] == null)
						continue;

					fmForm.removeTableField(index);
					fmForm.removeSubMateMasterVO(index);
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


