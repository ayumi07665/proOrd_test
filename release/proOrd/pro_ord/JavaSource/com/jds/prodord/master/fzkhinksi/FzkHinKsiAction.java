/**
* FzkHinKsiAction  �t���i�\���}�X�^�[�����e�i���X�A�N�V�����N���X
*
*	�쐬��    2004/02/12
*	�쐬��    �i�m�h�h�j�X
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
* 		 2004/06/23�i�m�h�h�j�g�c
* 			�E���ׂɢ�����於�̣�ǉ�
*/

package com.jds.prodord.master.fzkhinksi;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class FzkHinKsiAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  FzkHinKsiDelegate bzDelegate = new FzkHinKsiDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		FzkHinKsiForm fmForm = (FzkHinKsiForm)form;
		FzkHinKsiVO fmVO = new FzkHinKsiVO();
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
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			
		}
		//�u�N���A�v������
		else if(fmForm.getCommand().equals(FzkHinKsiForm.COMMAND_CLEAR)){
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

		//�����敪���o�^
		else if(fmForm.getPrcKbn().equals("2")){
			errors = this.insert(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
				fmForm.setUpdatable(false);
			}		
		}

		//�����敪���X�V
		else if(fmForm.getPrcKbn().equals("3")){
			errors = this.update(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				fmForm.setUpdatable(false);
			}		
		}

		//�����敪���폜
		else if(fmForm.getPrcKbn().equals("4")){
			errors = this.delete(fmForm,req,res);
			if(errors == null){	//��������
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.delete.end"));
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
	 * ����
	 * 
	 */
	private ActionErrors select (FzkHinKsiForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		FzkHinKsiVO fmVO = new FzkHinKsiVO();		
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());

		String[] kai = fmForm.getKaiSkbCod();
		fmVO.setKaiSkbCod(kai[0]);	

		fmVO.setHinban(fmForm.getHinban());
		fmVO.setTitle(fmForm.getTitle());
		fmVO.setHbidte(fmForm.getHbidte());
		fmVO.setKetNm(fmForm.getKetNm());
		fmVO.setSetsuu(fmForm.getSetsuu());
	
		try{
			//�������s
			FzkHinKsiVO[] resultFmVO = bzDelegate.doFind(fmVO);
			
			fmForm.clearFzkHinKsiVO();
			
			if(resultFmVO.length != 0){
				//�������ʂ��t�H�[���ɃZ�b�g
				fmForm.setSize(Math.max((resultFmVO.length + FzkHinKsiForm.MIN_OF_BRANK_ROW_COUNT),FzkHinKsiForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
							
				for(int i = 0;i < resultFmVO.length;i++){
					FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
					if(i==0){
						fmForm.setTitle(resultFmVO[i].getTitle());
						fmForm.setHinban(resultFmVO[i].getHinban());
						fmForm.setHbidte(DataFormatUtils.setFormatYYMMDD(resultFmVO[i].getHbidte()));
						fmForm.setKetNm(resultFmVO[i].getKetNm());
						fmForm.setKetCod(resultFmVO[i].getKetCod());
						fmForm.setSetsuu(resultFmVO[i].getSetsuu());
						if(!resultFmVO[i].getExsitMst08()){
							fmForm.setPrcKbn("2");
							errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound","�t���\���}�X�^�[�ɓ��͕i��")); 
						}
					}
					formRow.setFuksziCod(resultFmVO[i].getFuksziCod());
					formRow.setFuksziNm(resultFmVO[i].getFuksziNm());
					formRow.setSirSk(resultFmVO[i].getSirSk());
					formRow.setCheck_bx(resultFmVO[i].getCheck_bx());
					formRow.setHacNm(resultFmVO[i].getHacNm()); //2004/06/23 add
				}
				fmForm.setFzkHinKsiVO(resultFmVO[0]);
				fmForm.setCount(resultFmVO[0].get08FuksziCodArr().size());
				if(resultFmVO[0].getFlag())
					fmForm.setSize(10);
					
			}else{
				fmForm.setCount(0);
				fmForm.setSize(FzkHinKsiForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				fmForm.setHbidte("");
				fmForm.setTitle("");
				fmForm.setSetsuu("");
				fmForm.setKetCod("");
				fmForm.setKetNm("");
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
	 * �o�^
	 * 
	 */

	private ActionErrors insert(FzkHinKsiForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String kaiSkbCod = fmForm.getKaiSkbCod()[0];

		FzkHinKsiVO fmVO = fmForm.getFzkHinKsiVO();	
		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		ArrayList line_Arr = new ArrayList();
		for(int i = 0; i < fmForm.getSize();i++){			
					
			FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				continue;
			}
			
			sb_fukSziCod.append(formRow.getFuksziCod());	
			sb_sirSkCod.append(StringUtils.rpad(formRow.getSirSk(),6," "));
			line_Arr.add((i+1)+"");
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());
		fmVO.setLineArr(line_Arr);
		fmVO.setHinban(fmForm.getHinban());
		
		try{
			//�o�^���s
			FzkHinKsiResult result = bzDelegate.doInsert(fmVO);
		
			//�G���[�������̂͂Ȃ������ׂ�
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //�ق��̃��[�U�[���o�^�ς�
				if(result.getDescription().equals(FzkHinKsiDelegate.EXIST)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				}else if(result.getDescription().equals(FzkHinKsiDelegate.COD_NOT_EXIST)){
					errors = new ActionErrors();

					ArrayList errs_fuk = result.getMap(FzkHinKsiResult.KEY_FUKSZICOD);
					ArrayList errs_sir = result.getMap(FzkHinKsiResult.KEY_SIRSKCOD);
					String index = "";
					//�����ރR�[�h�����݂��Ȃ�
					if((errs_fuk != null) || (errs_sir != null)){
						for(int j = 0; j < errs_fuk.size(); j++){
							String err_fuk = (String)errs_fuk.get(j);
							index = err_fuk;//�G���[�̂�����Index
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"�����ރR�[�h"));

							String err_sir = (String)errs_sir.get(j);
							index = err_sir;//�G���[�̂�����Index
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"�d����R�[�h"));
						}
					}
				}
			}
			if(errors == null){ 
				fmForm.setFzkHinKsiVO(result.getVO());
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	
	/**
	 * �X�V
	 * 
	 */

	private ActionErrors update (FzkHinKsiForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String kaiSkbCod = fmForm.getKaiSkbCod()[0];

		FzkHinKsiVO fmVO = fmForm.getFzkHinKsiVO();	
		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		ArrayList line_Arr = new ArrayList();
		for(int i = 0; i < fmForm.getSize();i++){			
					
			FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				continue;
			}
			
			sb_fukSziCod.append(formRow.getFuksziCod());	
			sb_sirSkCod.append(StringUtils.rpad(formRow.getSirSk(),6," "));
			line_Arr.add((i+1)+"");
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());
		fmVO.setLineArr(line_Arr);
		fmVO.setHinban(fmForm.getHinban());
		
		try{
			//�X�V���s
			FzkHinKsiResult result = bzDelegate.doUpdate(fmVO);
		
			//�G���[�������̂͂Ȃ������ׂ�
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //�ق��̃��[�U�[���ύX�ς�
				if(result.getDescription().equals(FzkHinKsiDelegate.ANOTHER_USER_UPDATE)||
				   result.getDescription().equals(FzkHinKsiDelegate.LOGICAL_DELETE)||
				   result.getDescription().equals(FzkHinKsiDelegate.NOT_EXIST)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
			   }else if(result.getDescription().equals(FzkHinKsiDelegate.COD_NOT_EXIST)){
			   		errors = new ActionErrors();

					ArrayList errs_fuk = result.getMap(FzkHinKsiResult.KEY_FUKSZICOD);
					ArrayList errs_sir = result.getMap(FzkHinKsiResult.KEY_SIRSKCOD);
					String index = "";
					//�����ރR�[�h�����݂��Ȃ�
					if((errs_fuk != null) || (errs_sir != null)){
						for(int j = 0; j < errs_fuk.size(); j++){
							String err_fuk = (String)errs_fuk.get(j);
							index = err_fuk;//�G���[�̂�����Index
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"�����ރR�[�h"));

							String err_sir = (String)errs_sir.get(j);
							index = err_sir;//�G���[�̂�����Index
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"�d����R�[�h"));
						}
					}
				}
			}
			if(errors == null){ 
				fmForm.setFzkHinKsiVO(result.getVO());
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	
	/**
	 * �폜
	 * 
	 */

	private ActionErrors delete(FzkHinKsiForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String kaiSkbCod = fmForm.getKaiSkbCod()[0];

		FzkHinKsiVO fmVO = fmForm.getFzkHinKsiVO();	
		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		ArrayList line_Arr = new ArrayList();
		for(int i = 0; i < fmForm.getSize();i++){			
					
			FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				continue;
			}
			
			sb_fukSziCod.append(formRow.getFuksziCod());	
			sb_sirSkCod.append(formRow.getSirSk());
			line_Arr.add((i+1)+"");
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());
		fmVO.setLineArr(line_Arr);

		try{
			//�폜���s
			FzkHinKsiResult result = bzDelegate.doDelete(fmVO);
		
			//�G���[�������̂͂Ȃ������ׂ�
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //�ق��̃��[�U�[���ύX�ς�
				if(result.getDescription().equals(FzkHinKsiDelegate.ANOTHER_USER_UPDATE)||
				   result.getDescription().equals(FzkHinKsiDelegate.LOGICAL_DELETE)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				}else if(result.getDescription().equals(FzkHinKsiDelegate.NOT_EXIST)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound","�폜����f�[�^"));
				}
			}
			if(errors == null){ 
				fmForm.clearFzkHinKsiVO();
				fmForm.setSize(FzkHinKsiForm.DEFAULT_ROW_COUNT);
				for(int i=0;i<fmForm.getSize();i++){
					fmForm.clearTableField(i);
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
