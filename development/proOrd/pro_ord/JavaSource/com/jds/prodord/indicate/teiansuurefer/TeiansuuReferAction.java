/**
* TeiansuuReferAction  ��Đ��Ɖ�w���A�N�V�����N���X
*
*	�쐬��    2003/02/20
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/07/16�i�m�h�h�j���c �Ĕ�
* 			�E�\�[�g�����Ƀ\�[�g�L�[�ǉ��B
* 			�E�i�Ԏw��̏ꍇ�A���̑��̏����͉����Ȃ��B�L�����ȗ����͑Ή� 
* 		 2003/09/10�i�m�h�h�j���c �Ĕ�
* 			�E�L���ԍ��̋L�����ȗ����͑Ή�
* 		 2003/09/19�i�m�h�h�j���c �Ĕ�
* 			�E�Ώۃf�[�^�̂ݕ\���E��Ώۃf�[�^�\�� �I���@�\�ǉ�
* 		 2004/07/02�i�m�h�h�j�g�c
* 			�E�R�s�[���y�[�X�g�@�\�ǉ�
*		  2006/05/10�i�m�h�h�j�c�[ �N��
*			�E�j�Ђ̃����N�����ύX�Ή�
* 
*/

package com.jds.prodord.indicate.teiansuurefer;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class TeiansuuReferAction extends Action {
		
	/**
	 * �r�W�l�X���W�b�N
	 */
	private static  TeiansuuReferDelegate bzDelegate = new TeiansuuReferDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//�t�H�[���̃f�[�^
		TeiansuuReferForm fmForm = (TeiansuuReferForm)form;
		TeiansuuReferVO fmVO = new TeiansuuReferVO();


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

		//�{�^�����\��t�� 2004/06/29 add
		if(fmForm.getPaste().equals("�\��t��")){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
			fmForm.setPaste("");//�\��t���{�^�����Z�b�g
			return (new ActionForward(mapping.getInput()));
		}


		//--------�ȉ��A�t�H�[���̉������{�^�����珈��������s��	
		//�ŏ��Ƀ��j���[���炫���Ƃ��A�Ɖ�ʉ�ʂ���u�߂�v�{�^���Ŗ߂��Ă����Ƃ�
		if(fmForm.getCommand().equals("INIT")){
			//���j���[���炫���Ƃ�
			if(fmForm.getFromMenuSgn()){
				fmForm.clearAll();
		   		com.jds.prodord.register.LogonForm logonForm 
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
				String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
				String KaiSkbCod = 	logonForm.getKaiskbcod();
				ArrayList kaiList = logonForm.getKaiSkbCodList();
				fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
				fmForm.setQueryKaiSkbCod(KaiSkbCod);
				fmForm.setQueryKaiSkbCodList(kaiList);
				//�I�����ڂ̃I�v�V�������Z�b�g
				fmForm.setKaiSkbCodOptions(kaiList);
				fmForm.setKetCodOptions(logonForm.getKetNmList());
				fmForm.setMkrBunOptions(logonForm.getMkrBunList());
				fmForm.setRankOptions(daiKaiSkbCod);
			}
			fmForm.setFromMenuSgn(false);
			fmForm.setCheckBoxValue();
			//�Ɖ�ʉ�ʂ�BEAN��remove
			session.removeAttribute(CommonConst.FORMBEAN_NAME_IKKATSU);
			session.removeAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			session.removeAttribute(CommonConst.TEIANSUUREFER_VO);
		}
		//�{�^����������ďƉ�
		if(fmForm.getCommand().equals(TeiansuuReferForm.COMMAND_TEIANSYOKAI)){
			errors = this.teianSyokaiShiji(fmForm,req,res,fmVO);
			if(errors == null){	//��������
				session.setAttribute(CommonConst.TEIANSUUREFER_VO,fmVO);	
				return mapping.findForward("next");	
			}
		//�{�^������Đ���������
		}else if(fmForm.getCommand().equals(TeiansuuReferForm.COMMAND_JIDOUHACHU)){
			errors = this.teianSyokaiShiji(fmForm,req,res,fmVO);
			if(errors == null){	//��������
				session.setAttribute(CommonConst.TEIANSUUREFER_VO,fmVO);
				return mapping.findForward("next");		
			}		
		//�{�^�����N���A
		}else if(fmForm.getCommand().equals(TeiansuuReferForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setRb_default();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));

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
	 * ��Đ��Ɖ�w���E��Đ���������
	 * 
	 */
	private ActionErrors teianSyokaiShiji (TeiansuuReferForm fmForm,HttpServletRequest req,HttpServletResponse res,TeiansuuReferVO fmVO){

		HttpSession session = req.getSession();
		
		ActionErrors errors = null;
		
		ArrayList arr_kai = new ArrayList();
		ArrayList arr_rnk = new ArrayList();
		ArrayList arr_ket = new ArrayList();
		ArrayList arr_mkr = new ArrayList();
		ArrayList arr_hyo = new ArrayList();
		ArrayList arr_kig = new ArrayList();
		ArrayList arr_srt = new ArrayList();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//���ł����t�H�[���̒l���o�����[�I�u�W�F�N�g�ɃZ�b�g
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
	
		//�R�}���h
		fmVO.setCommand(fmForm.getCommand());
		
		//�i�Ԃ��`�k�k�w��̂Ƃ�
		if(fmForm.getRb_kigBng() == TeiansuuReferForm.RB_ALL){
			
			//��Ў��ʃR�[�h	
			if(fmForm.getRb_kaiSkbCod() == TeiansuuReferForm.RB_ALL){
				fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			}else if(fmForm.getRb_kaiSkbCod() == TeiansuuReferForm.RB_SELECT){
				String[] kai = fmForm.getKaiSkbCod();
				for(int i = 0; i<kai.length; i++){
					arr_kai.add(kai[i]);
				}
				fmVO.setKaiSkbCod_arr(arr_kai);			
			}
			//�����N
			if(fmForm.getRb_rank() == TeiansuuReferForm.RB_ALL){
				String[] RANK = TeiansuuReferForm.makeRank(queryDaiKaiSkbCod);
				for(int i = 0; i<RANK.length; i++){
					arr_rnk.add(RANK[i]);
				}
				fmVO.setRank_arr(arr_rnk);
			}else if(fmForm.getRb_rank() == TeiansuuReferForm.RB_SELECT){
				String[] rank = fmForm.getRank();
				for(int i = 0; i<rank.length; i++){
					arr_rnk.add(rank[i]);
				}
				fmVO.setRank_arr(arr_rnk);			
			}
			//�`�ԃR�[�h
			if(fmForm.getRb_ketCod() == TeiansuuReferForm.RB_SELECT){
				String[] ket = fmForm.getKetCod();
				for(int i = 0; i<ket.length; i++){
					arr_ket.add(ket[i]);
				}
				fmVO.setKetCod_arr(arr_ket);			
			}
			//���[�J�[����
			if(fmForm.getRb_mkrBun() == TeiansuuReferForm.RB_SELECT){
				String[] mkr = fmForm.getMkrBun();
				for(int i = 0; i<mkr.length; i++){
					arr_mkr.add(mkr[i]);
				}
				fmVO.setMkrBun_arr(arr_mkr);			
			}
			//�M�m�敪
			if(fmForm.getRb_hyoKbn() == TeiansuuReferForm.RB_SELECT){
				String[] hyoKbn = fmForm.getHyoKbn();
				for(int i = 0; i<hyoKbn.length; i++){
					arr_hyo.add(hyoKbn[i]);
				}
				fmVO.setHyoKbn_arr(arr_hyo);			
			}
		}
		//�i�Ԏw��̂Ƃ�
		if(fmForm.getRb_kigBng() == TeiansuuReferForm.RB_SELECT){
			//��Ў��ʃR�[�h
			fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			//�L���ԍ�
			ArrayList arr = DataFormatUtils.supplement_kigBng(fmForm.getKigBng_List());//�L�����ȗ����͑Ή� 2003/09/10
			for(int i = 0; i < arr.size(); i++){
				arr_kig.add(DataFormatUtils.mk_srykig(arr.get(i).toString().trim()));
			}
			fmVO.setKigBng_arr(arr_kig);		
		}
		//�\�[�g����
		fmVO.setSort_rank(fmForm.getSort_rank());
		fmVO.setSort_ketCod(fmForm.getSort_ketCod());
		fmVO.setSort_sortKey(fmForm.getSort_sortKey());
		
		fmVO.setHitaisyo(fmForm.getHitaisyo());
		if(fmForm.getHitaisyo())
			fmVO.setTaisyoRange(fmForm.getTaisyoRange());
			
		fmForm.setCheckBoxValueToArray();
		
		try{
			//��Đ��Ɖ�w�� �G���[�`�F�b�N���s
			TeiansuuReferResult result = bzDelegate.doTeianSyokaiShiji((TeiansuuReferVO)fmVO);
		
			//�G���[�������̂͂Ȃ������ׂ�
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				ArrayList errs_kig = result.getMap(TeiansuuReferResult.KEY_KIGBNG);
				String index = "";
				 //�i�Ԃ����݂��Ȃ�
				if(errs_kig != null){	
					for(int i = 0; i < errs_kig.size(); i++){
						String[] err_kig = (String[])errs_kig.get(i);
						index = err_kig[0];//�G���[�̂�����Index
						int type  = Integer.parseInt(err_kig[1]);//�G���[�̎��
						if(type == 1)//TeiansuuReferDAO.NOT_EXIST
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"�i��"));
						if(type == 2)//TeiansuuReferDAO.RANK_ERR
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.error_no",index,"�i��","�����N"));
					}
				}
			}else{
				//�u�����N�̗v�f����������l�߂ăZ�b�g������
				fmVO.setKigBng_arr(DataFormatUtils.removeBlankElement(fmVO.getKigBng_arr()));
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
	 * �\��t��
	 * @param fmForm
	 */
	public void dataPaste(TeiansuuReferForm fmForm){
		
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
						
						if(fmForm.getKigBng1().equals("")){
							fmForm.setKigBng1(str);
							continue;
						}
						if(fmForm.getKigBng2().equals("")){
							fmForm.setKigBng2(str);					
							continue;
						}
						if(fmForm.getKigBng3().equals("")){
							fmForm.setKigBng3(str);
							continue;
						}
						if(fmForm.getKigBng4().equals("")){
							fmForm.setKigBng4(str);
							continue;
						}
						if(fmForm.getKigBng5().equals("")){
							fmForm.setKigBng5(str);
							continue;
						}
						if(fmForm.getKigBng6().equals("")){
							fmForm.setKigBng6(str);
							continue;
						}
						if(fmForm.getKigBng7().equals("")){
							fmForm.setKigBng7(str);
							continue;
						}
						if(fmForm.getKigBng8().equals("")){
							fmForm.setKigBng8(str);
							continue;
						}
						if(fmForm.getKigBng9().equals("")){
							fmForm.setKigBng9(str);
							continue;
						}
						if(fmForm.getKigBng10().equals("")){
							fmForm.setKigBng10(str);
							continue;
						}
						if(fmForm.getKigBng11().equals("")){
							fmForm.setKigBng11(str);
							continue;
						}
						if(fmForm.getKigBng12().equals("")){
							fmForm.setKigBng12(str);					
							continue;
						}
						if(fmForm.getKigBng13().equals("")){
							fmForm.setKigBng13(str);
							continue;
						}
						if(fmForm.getKigBng14().equals("")){
							fmForm.setKigBng14(str);
							continue;
						}
						if(fmForm.getKigBng15().equals("")){
							fmForm.setKigBng15(str);
							continue;
						}
					}
				}
			}

		}catch(IOException ex){
			SystemException se = new SystemException(ex);
			se.printStackTrace();
		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
		}
	}
	

}
