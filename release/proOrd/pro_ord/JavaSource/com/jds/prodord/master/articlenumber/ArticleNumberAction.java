/**
* ArticleNumberAction  �i�ԃ}�X�^�[�����e�i���X�A�N�V�����N���X
*
*	�쐬��    2003/08/25
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*	[�ύX����]
*        2003/09/11�i�m�h�h�j����  ����
* 			�E�i�ԃ}�X�^�[�ł��X�V�ł���悤�ɏ����ύX�B
* 		 2003/10/07�i�m�h�h�j���c  �Ĕ�
* 			�E�����E�o�^����Form��VO��ۑ��A�X�V�E�폜���͂���VO�𗘗p����
* 			  �������s�Ȃ��悤�ɕύX
*/



package com.jds.prodord.master.articlenumber;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;

public class ArticleNumberAction extends Action {

		private static final String INFOMSG_KEY = "INFOMSG_KEY";

		private static  ArticleNumberDelegate bzDelegate = new ArticleNumberDelegate();


	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		HttpSession session = req.getSession();		//�t�H�[���̃f�[�^
		ArticleNumberForm fmForm = (ArticleNumberForm)form;
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
	
//=====  �ȉ��A�t�H�[���̉������{�^�����珈��������s��  ==========================================

		//***  �}�X�^�[���j���[����i�ԏƉ�ւ̃{�^������������  ***
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


		//***  �i�ԃ}�X�^�[�Ɖ�̎��s�{�^�����������ꂽ��  ***
		if(fmForm.getCommand().equals(ArticleNumberForm.COMMAND_JITTKOU)){

				if(fmForm.getPrcKbn().equals("1")){
						errors = this.find(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.select.end"));
				
				}else if(fmForm.getPrcKbn().equals("2")){
						errors = this.insert(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.insert.end"));

				}else if(fmForm.getPrcKbn().equals("3")){
						errors = this.update(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.update.end"));

				}else if(fmForm.getPrcKbn().equals("4")){
						errors = this.delete(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.delete.end"));
				}

		//***  �i�ԃ}�X�^�[�Ɖ�̃N���A�{�^���������ꂽ��  ***
		}else if(fmForm.getCommand().equals(ArticleNumberForm.COMMAND_CLEAR)){

			fmForm.clearAll();
			fmForm.removeArticleNumberVO();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		}

		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}

		//�G���[�Ȃ�
		return mapping.findForward("success");		
	}

//*****  ����  **************************************************************************

	private ActionErrors find (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();
		String[] ket = new String[1];


		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		ArrayList queryKaiSkbCodList = fmForm.getQueryKaiSkbCodList();

		//�����L�[������o�����[�I�u�W�F�N�g
		fmVO.setQueryDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		String[] kai = fmForm.getKaiSkbCod();

		//��Ў��ʃR�[�h	
		fmVO.setKaiSkbCod(kai[0]);
		//�ȗ��i�ԑΉ���u�n�ɃZ�b�g
		fmVO.setKigoBan(DataFormatUtils.mk_srykig(fmForm.getKigoBan().trim()));

	    	HttpSession session = req.getSession(); 
    		com.jds.prodord.register.LogonForm logonForm
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");

		try{
			//�������s
			ArticleNumberVO resultFmVO = bzDelegate.doFind(fmVO);

			fmForm.clearTableField1();

            if(resultFmVO.getFind_flag() == true){	

				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setArtist(resultFmVO.getArtist());
				fmForm.setTitle(resultFmVO.getTitle());

				ket[0] = resultFmVO.getKetCod();
				fmForm.setKetCod(ket);

				String hbidte = hbiDateFormat(resultFmVO.getHbiDte());
	
				if(!hbidte.equals("")){
					fmForm.setYear(hbidte.substring(0,2));
					fmForm.setMonth(hbidte.substring(2,4));
					fmForm.setDay(hbidte.substring(4,6));
				}

				fmForm.setPrsMkrCod(resultFmVO.getPrsMkrCod());
				fmForm.setJytPrsMkr(resultFmVO.getJytPrsMkr());
				fmForm.setHukSizCod(resultFmVO.getHukSizCod());
				fmForm.setDbName(resultFmVO.getDbName());
				fmForm.setFindedFlag(true);
				fmForm.setArticleNumberVO(resultFmVO);//2003/10/07 add
            }else{
				//�f�[�^�����݂��܂���
				fmForm.removeArticleNumberVO();//2003/10/07 add
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();

			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;

	}  //���������I��



//*****  �o�^  **************************************************************************

	private ActionErrors insert (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();	

		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String[] kai = fmForm.getKaiSkbCod();
		String hbiDte = "";

		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(kai[0]);
		fmVO.setKigoBan(DataFormatUtils.mk_srykig(fmForm.getKigoBan().trim()));
		fmVO.setArtist(fmForm.getArtist());
		fmVO.setTitle(fmForm.getTitle());

		String[] ket = fmForm.getKetCod();
		fmVO.setKetCod(ket[0]);

		if(fmForm.getYear().equals(""))
			fmVO.setHbiDte("0");
		else
			fmVO.setHbiDte(Integer.toString((Integer.parseInt(fmForm.getYear(),10)*10000 +
			Integer.parseInt(fmForm.getMonth(),10)*100 + Integer.parseInt(fmForm.getDay(),10)),10));

		fmVO.setPrsMkrCod(fmForm.getPrsMkrCod());
		fmVO.setJytPrsMkr(fmForm.getJytPrsMkr());
		fmVO.setHukSizCod(fmForm.getHukSizCod());

		try{
			//�o�^���s
			ArticleNumberResult result = bzDelegate.doInsert(fmVO);

			//�G���[�`�F�b�N
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //�o�^���悤�Ƃ����f�[�^�����łɑ���
				if(result.getDescription().equals(ArticleNumberDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				 //�o�^���悤�Ƃ����f�[�^���_���폜��
				else if(result.getDescription().equals(ArticleNumberDelegate.LOGICAL_DELETE))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));
				if(result.getDescription().equals(ArticleNumberDelegate.NOTEXIST_HACCOD)){
					ArrayList err = result.getError();
					for(int i = 0; i < err.size(); i++){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect",err.get(i)+""));
					}
				}
			}else{//2003/10/07 add okada	
				fmForm.setArticleNumberVO(fmVO);
				fmForm.setDbName(fmVO.getDbName());			
			}

								
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	
		return errors;
	}



	
//*****  �X�V  **************************************************************************

	private ActionErrors update (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){


		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();	

		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO = fmForm.getArticleNumberVO();//2003/10/07 add
		fmVO.setPrcKbn(fmForm.getPrcKbn());  //03/09/11 �ǉ�
		
		fmVO.setArtist(fmForm.getArtist());
		fmVO.setTitle(fmForm.getTitle());

		String[] ket = fmForm.getKetCod();
		fmVO.setKetCod(ket[0]);
		
		if(fmForm.getYear().equals(""))
			fmVO.setHbiDte("0");
		else
			fmVO.setHbiDte(Integer.toString((Integer.parseInt(fmForm.getYear(),10)*10000 +
		 	Integer.parseInt(fmForm.getMonth(),10)*100 + Integer.parseInt(fmForm.getDay(),10)),10));


		fmVO.setPrsMkrCod(fmForm.getPrsMkrCod());
		fmVO.setJytPrsMkr(fmForm.getJytPrsMkr());
		fmVO.setHukSizCod(fmForm.getHukSizCod());

		try{
			//�X�V���s
			ArticleNumberResult result = bzDelegate.doUpdate(fmVO);
			
			//�G���[�������̂͂Ȃ������ׂ�

			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
						
				 //�ق��̃��[�U�[���ύX�ς�
				if(result.getDescription().equals(ArticleNumberDelegate.ANOTHER_USER_UPDATE)||
				   result.getDescription().equals(ArticleNumberDelegate.LOGICAL_DELETE)||
				   result.getDescription().equals(ArticleNumberDelegate.NOT_EXIST))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));

				if(result.getDescription().equals(ArticleNumberDelegate.NOTEXIST_HACCOD)){
					ArrayList err = result.getError();
					for(int i = 0; i < err.size(); i++){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect",err.get(i)+""));
					}
				}
			}else{//2003/10/07 add okada	
				fmForm.setArticleNumberVO(fmVO);
				fmForm.setDbName(fmVO.getDbName());			
			}


		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;
	}


//*****  �폜  **************************************************************************

	private ActionErrors delete (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();	
			
		fmVO = fmForm.getArticleNumberVO();//2003/10/07 add
		
	try{
			//�폜���s
			ArticleNumberResult result = bzDelegate.doDelete(fmVO);

			//�G���[�`�F�b�N
				if(result != null && !result.isSuccess()){
					if(errors == null)
						errors = new ActionErrors();

					 //�ق��̃��[�U�[���ύX�ς�
					if(result.getDescription().equals(ArticleNumberDelegate.ANOTHER_USER_UPDATE)) 
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate",String.valueOf(1)));
					 //�폜���悤�Ƃ����f�[�^���_���폜�� ��  ���݂��Ȃ�
					else if(result.getDescription().equals(ArticleNumberDelegate.LOGICAL_DELETE)||
						    result.getDescription().equals(ArticleNumberDelegate.NOT_EXIST))	
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
					else if(result.getDescription().equals(ArticleNumberDelegate.CANNOT_DELETE))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.cannot","�i�ԃ}�X�^�[�ł̑Ώەi��","�폜"));
					else if(result.getDescription().equals(ArticleNumberDelegate.EXIST_HAC02))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.cannot","�Ώەi��","���������ɑ��݂���ׁA�폜"));

				}else{//2003/10/07 add
					fmForm.clearTableField1();
					fmForm.setDbName(fmVO.getDbName());
					fmForm.removeArticleNumberVO();
				}

			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;			
	}


//-----�c�a����擾�����������̃T�C�Y���U���ɂȂ��Ă��邩����-------------------

	private String hbiDateFormat(String str){

		String hbiDte;

		if(str.equals("0") || str.equals("")){
			hbiDte = "";
			return hbiDte;
		}

		hbiDte = StringUtils.lpad(str,6,"0");

//		System.out.println("Action ������̔������ϊ��F"+hbiDte);

	return hbiDte;

	}


}  //�N���X�I��
