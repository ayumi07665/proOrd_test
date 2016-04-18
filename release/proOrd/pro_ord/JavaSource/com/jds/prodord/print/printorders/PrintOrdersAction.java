/**
* PrintOrdersAction  ����������A�N�V�����N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����Ăяo�����s���A��ʐ�����s���N���X�B
*
*	[�ύX����]
*        2003/09/05�i�m�h�h�j���c �Ĕ�
* 			�E�������p�^�[���ɂ���Ĕ������ԍ��E�s�ԍ��̍̔ԕ��@�𕪊򂳂���
* 			  �������p�^�[���ɂ����mapping.findForward���ύX����
* 		 2004/04/21�i�m�h�h�j�X
* 			�E�ō��艿�\���ɔ����C��
* 		 2005/05/19�i�m�h�h�j�g�c
* 			�E�w�b�_�[�̃Z�b�g�ŉ�Ж������Q�A��\��Ђ��Z�b�g
* 			�E���������C�A�E�g�p�^�[���C��
* 		 2005/06/27�i�m�h�h�j�g�c
* 			�E�������f�[�^�������\�b�hPTN03�iK�Зp�j�ǉ�
* 		 2005/09/05�i�m�h�h�j�g�c
* 			�E�`�[���s�E�����`�[���s�̏ꍇ�A���������Ɣ������f�[�^�ɗ����X�V���������ށiVAP�БΉ��j
* 			�E�����X�V��(1�`5��)�E�����񐔁E���z�̕\���Ή�
* 		 2005/09/27�i�m�h�h�j�g�c
* 			�E������MAX�X��ɕύX
* 		 2007/07/18�i�m�h�h�j�c��
* 			�E�[�i�於�́@�u�`�o��10���� ���̑����[�J�[15�����ɕύX
* 		 2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
* 		 2008/03/10�i�m�h�h�j�c��
* 			�E�P���ǉ��Ή�
*/

package com.jds.prodord.print.printorders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.SystemException;
import com.jds.prodord.register.LogonForm;

public class PrintOrdersAction extends Action {
	
	
	//TODO ��Вǉ��Ή�(�޼�Ƚ���W�b�N�N���X�ǉ�)
	/** �������p�^�[���F01 (VAP��) �r�W�l�X���W�b�N */
	private static  PrintOrdersDelegate01 bzDelegate01 = new PrintOrdersDelegate01();
	/** �������p�^�[���F02 (CR ��) �r�W�l�X���W�b�N */
	private static  PrintOrdersDelegate02 bzDelegate02 = new PrintOrdersDelegate02();
	/** �������p�^�[���F03 (K  ��) �r�W�l�X���W�b�N */
	private static  PrintOrdersDelegate03 bzDelegate03 = new PrintOrdersDelegate03();
	/** �������p�^�[���F04 (FX ��) �r�W�l�X���W�b�N */
	private static  PrintOrdersDelegate04 bzDelegate04 = new PrintOrdersDelegate04();
	/** �������p�^�[���F05 (LAN(BAN) ��) �r�W�l�X���W�b�N */
	private static  PrintOrdersDelegate05 bzDelegate05 = new PrintOrdersDelegate05();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		PrintOrdersPageList ordersPages = new PrintOrdersPageList();
		
		PrintOrdersVO fmVO = new PrintOrdersVO();
					
		try {
			//���b�Z�[�W���Z�b�V���������菜��
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			
			//�Z�b�V��������PrintOrdersQueryVO���擾
			PrintOrdersQueryVO poQueryVO = (PrintOrdersQueryVO)session.getAttribute(CommonConst.PO_QUERY_VO);
			
			//�Z�b�V��������LogonForm���擾
			LogonForm logonForm =
				(LogonForm) session.getAttribute(CommonConst.user);
			
			//�������p�^�[���̎擾
			String hacSyoPtn = logonForm.getHacSyoPtn();
			
			//�������o�͏���
			PrintOrdersDelegate bzDelegate = bzDelegate01;//�f�t�H���g
			
			//TODO ��Вǉ��Ή�(�p�^�[���̕���ǉ�)
			//VAP
			if(hacSyoPtn.equals(CommonConst.PTN01))
				bzDelegate = bzDelegate01;
			//CR
			else if(hacSyoPtn.equals(CommonConst.PTN02))
				bzDelegate = bzDelegate02;
			//K
			else if(hacSyoPtn.equals(CommonConst.PTN03))
				bzDelegate = bzDelegate03;
			//FX
			else if(hacSyoPtn.equals(CommonConst.PTN04))
				bzDelegate = bzDelegate04;
			//LAN�iBAN)
			else if(hacSyoPtn.equals(CommonConst.PTN05))
				bzDelegate = bzDelegate05;
			
			errors = this.printOrders(bzDelegate,ordersPages,req,res,poQueryVO,logonForm);
			
			if(errors == null){	//��������
				req.setAttribute(CommonConst.BEAN_NAME_PRINT,ordersPages);
				session.removeAttribute(CommonConst.PO_QUERY_VO);
			}
					
			//�G���[����
			if(errors != null){
				super.saveErrors(req,errors);
				return mapping.findForward("failure");
			}
			
			//�G���[�Ȃ�
			//2005/05/30 �������̃��C�A�E�g�p�^�[���ύX(��Ў��ʃR�[�h)
			//TODO ��Вǉ��Ή�(struts-config��forward��ǉ��AJSP�ǉ�)
			ActionForward forward = mapping.findForward(logonForm.getKaiskbcod());
			if(forward != null && forward.getName() != null)
				return forward;
				
		} catch (Exception e) {
			return mapping.findForward("failure");
		}

		return mapping.findForward("failure");
	}
	
		
	/**
	 * ����
	 * 
	 */
	private ActionErrors printOrders(
		PrintOrdersDelegate bzDelegate,
		ArrayList ordersPages,
		HttpServletRequest req,
		HttpServletResponse res,
		PrintOrdersQueryVO poQueryVO,
		LogonForm logonForm) {
		
		//�������p�^�[���̎擾
		String hacSyoPtn = logonForm.getHacSyoPtn();

		ActionErrors errors = null;

		try{			
			//�������s
			PrintOrdersVO[] resultFmVOs = bzDelegate.doFindPrintData(poQueryVO,logonForm);
			//�y�[�W���C�A�E�g�̎擾
			ArrayList pageLayout = bzDelegate.doGetPageLayout(poQueryVO);
			//1�y�[�W�̍ő�s��
			int defaultRowCount = PrintOrdersPage.DEFAULT_ROW_COUNT;
			//��Ɨp
			ArrayList tmpArr = new ArrayList();
			//�e�u���C�N�L�[���Ƃɉ��y�[�W���̃f�[�^�����邩���i�[����(�����ԍ��̍̔ԂŎg�p)
			ArrayList numbers = new ArrayList();

			//�y�[�W���C�A�E�g�̐ݒ�(1�y�[�W�̍ő�s�����l�����ăy�[�W���A�s�����Z�o����)
			for(int i=0, pageCount=0; i < pageLayout.size(); i++, pageCount=0){
				//�����u���C�N�L�[�����s�������o��
				PrintOrdersBreakKey bk = (PrintOrdersBreakKey)pageLayout.get(i);
				int rowCount = bk.getCount();
				//�����u���C�N�L�[�̍s�����AdefaultRowCount�����傫��������
				if(rowCount > defaultRowCount){
					int addPage = rowCount / defaultRowCount;//���₷�y�[�W��
					int oddRow = rowCount % defaultRowCount; //���[�ȍs��
					for(int j = 0; j < addPage; j++){
						tmpArr.add(new PrintOrdersBreakKey(bk.getHacCod(), defaultRowCount));
						pageCount++;
					}
					if(oddRow != 0){
						tmpArr.add(new PrintOrdersBreakKey(bk.getHacCod(), oddRow));
						pageCount++;
					}
				}//�����u���C�N�L�[�̍s�����AdefaultRowCount�ȓ�
				else{
					tmpArr.add(new PrintOrdersBreakKey(bk.getHacCod(), rowCount));
					pageCount++;
				}
				bk.setPageCount(pageCount);//�����u���C�N�L�[�����y�[�W�����Z�b�g
				numbers.add(bk);
			}
			//pageLayout�ɃZ�b�g������(�e�y�[�W�̍s���E�����悪�i�[����Ă���)
			pageLayout.clear();
			pageLayout.addAll((Collection)tmpArr.clone());
													   
			//�Z�o�����y�[�W���C�A�E�g�̒�����ordersPages�𐶐�����
			for(int i = 0; i < pageLayout.size(); i++){
				PrintOrdersPage page = new PrintOrdersPage();
				page.setSize(defaultRowCount);//�����ڏ�1�y�[�W�̍s���͏��defaultRowCount
				ordersPages.add(page);
			}
			
			String[] hacSyoBng_arr = null;
			String[] gyoBng_arr = null;
			//�̔ԑO��������
			if(!poQueryVO.getHasHacSyoBng()){
				//�������ԍ��擾
				hacSyoBng_arr = bzDelegate.doCreateHacSyoBng(logonForm.getKaiskbcod(),numbers);
				//�s�ԍ��擾
				gyoBng_arr = bzDelegate.doCreateGyoBng(logonForm.getKaiskbcod(),pageLayout);
			}
			
			//�������f�[�^��ordersPages�ɃZ�b�g
			for(int i=0, pageCount=0, rowCount=0; i < resultFmVOs.length; pageCount++){
				//�s�����擾
				rowCount = ((PrintOrdersBreakKey)pageLayout.get(pageCount)).getCount();
				for(int j = 0; j < rowCount; j++,i++){
					//�̔Ԃ��s�����ꍇ�A�������ԍ��E�s�ԍ���VO�ɃZ�b�g
					if(!poQueryVO.getHasHacSyoBng()){
						resultFmVOs[i].setHacSyoBng(hacSyoBng_arr[pageCount]);
						resultFmVOs[i].setGyoBng(gyoBng_arr[i]);
					}
					PrintOrdersPage ordersPage = (PrintOrdersPage)ordersPages.get(pageCount);
					if(j == 0)//�e�y�[�W�̍ŏ��ɂ̓w�b�_�[���Z�b�g
						this.setHeader(ordersPage, resultFmVOs, i, logonForm, poQueryVO);
					//���ׂ��Z�b�g
					this.setDetail(ordersPage.getOrdersRow(j), resultFmVOs[i], ordersPage.getRrkUpDte());
				}
			}

			//�ŏI�y�[�W�͉��y�[�W���Ȃ�
			((PrintOrdersPage)ordersPages.get(ordersPages.size()-1)).setPageBreak(false);
			
			if(!poQueryVO.getHasHacSyoBng()){
				//���������Ɣ������f�[�^�ɔ������ԍ��E�s�ԍ�����������(�X�V�����̎擾)
				bzDelegate.doUpDateHac01Hac02(resultFmVOs,true,poQueryVO.getUpdDte(),poQueryVO.getUpdJkk());
			}else if(poQueryVO.isTeiDnpHakkou()){
				//�����`�[���s�̏ꍇ�A���������Ɣ������f�[�^�ɗ����X�V������������(�X�V�����̎擾)
				bzDelegate.doUpDateHac01Hac02(resultFmVOs,false,poQueryVO.getUpdDte(),poQueryVO.getUpdJkk());
			}
			
   		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",e.toString()));
			
		}
		return errors;			
			
	}

//=========================================================================================================

	/** �w�b�_�[�̃Z�b�g */
	private void setHeader(PrintOrdersPage ordersPage, PrintOrdersVO[] resultFmVOs, int index, LogonForm logonForm, PrintOrdersQueryVO poQueryVO){
		
		ordersPage.setHacCod(resultFmVOs[index].getHacCod());
		ordersPage.setSirHacNm(resultFmVOs[index].getSirHacNm());
		ordersPage.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[index].getHacSyoDte()));
		ordersPage.setHacSyoBng(resultFmVOs[index].getHacSyoBng().trim());
		
		//[0]�ɃZ�b�g����Ă��郆�[�U�[�̉�Џ��𖈃y�[�W�Z�b�g����
		ordersPage.setKaiInfo(resultFmVOs[0]);
		//2005/06/14 usrId add
		ordersPage.setUsrId(logonForm.getUser());
		
		//�����ނ̔������̂Ƃ��̓t���O�𗧂Ă�
		if(!resultFmVOs[index].getBunCod().equals("0"))
			ordersPage.setFukSziFlg(true);

		//���ݓ��t
		Date sysdate = new Date();
		ordersPage.setCurrentDate((new SimpleDateFormat("yy/MM/dd HH:mm")).format(sysdate));

		/** �����X�V���E�����񐔂̃Z�b�g 2005/09/05 add **/
		String rrkTbl = resultFmVOs[index].getRrkDte();
		//���� �܂��� �`�[���s �܂��� �����`�[���s�̏ꍇ
		if(rrkTbl.length() == 0 || !poQueryVO.getHasHacSyoBng() || poQueryVO.isTeiDnpHakkou()){
			String today = new SimpleDateFormat("yyMMdd").format(sysdate);
			rrkTbl = DataFormatUtils.makeRrkTblStr(
					resultFmVOs[index].getRrkDte(), Integer.parseInt(today));
		}
		
		//�e�L�X�g�̕���
		String[] str = new String[9]; //MAX9��
		int size = makeRrkArr(rrkTbl, str);

		ordersPage.setRrkTbl(str[0],str[1],str[2],str[3],checkRrkTbl(str, size));
		ordersPage.setTeiNum(size);

		//�����X�V���̃Z�b�g(DB�o�^�p)
		resultFmVOs[index].setRrkDte(rrkTbl);
		ordersPage.setRrkUpDte(rrkTbl);

	}


	/**
	 * �����X�V���̃e�L�X�g�𕪉����ēn���ꂽ�z��ɋl�߁A�T�C�Y��Ԃ��܂��B
	 * @param rrkTbl
	 * @param str
	 * @return
	 */
	private int makeRrkArr(String rrkTbl, String[] str) {
		final int length = 4;
		int size = rrkTbl.length() / length;
		for(int i = 0; i < size; i++){
			try{
				str[i] = (rrkTbl.substring(i*length,(i+1)*length));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return size;
	}

	/**
	 * �������ɏo�͂��闚���X�V���t�T��ڂ��擾���܂��B
	 * @param rrkTbl
	 * @param size
	 * @return
	 */
	private String checkRrkTbl(String[] rrkTbl,int size){
		String rrkDte ="";
		if(size >=5)
			rrkDte = rrkTbl[size-1];
		return rrkDte;
	}

	
	/** ���׍s�̃Z�b�g */
	private void setDetail(PrintOrdersRow ordersRow, PrintOrdersVO resultFmVO,String rrkUpDte){
		
		ordersRow.setGyoBng(resultFmVO.getGyoBng().trim());
		ordersRow.setSinKyuKbn(DataFormatUtils.getKbnString(resultFmVO.getSinKyuKbn()));
		ordersRow.setKigBng(this.formatHjiHnb(resultFmVO.getHjiHnb()));
		if(ordersRow.getKigBng().equals(""))//�\���i�Ԃ����݂��Ȃ����́A�������f�[�^�̋L���ԍ������̂܂ܕ\������
			ordersRow.setKigBng(resultFmVO.getKigBng());
		ordersRow.setKetCod(resultFmVO.getKetCod());
		ordersRow.setKetNm(resultFmVO.getKetNm());
		ordersRow.setPrsFukSziCod(resultFmVO.getPrsFukSziCod());
		ordersRow.setSetSuu(DataFormatUtils.setFormatString(resultFmVO.getSetSuu()));
		ordersRow.setHacSuu(DataFormatUtils.setFormatString(resultFmVO.getHacSuu()));
		ordersRow.setNki(DataFormatUtils.setFormatYYMMDD(resultFmVO.getNki()));
		if(resultFmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP) 
				|| resultFmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_BAN))
			ordersRow.setNhnMeiKj(this.getSubstringStr(resultFmVO.getNhnMeiKj(),10));
		else
			ordersRow.setNhnMeiKj(this.getSubstringStr(resultFmVO.getNhnMeiKj(),15));
		ordersRow.setComment(resultFmVO.getComment());
		ordersRow.setTitKj(this.getSubstringStr(resultFmVO.getTitKj(),15));
		ordersRow.setArtKj(this.getSubstringStr(resultFmVO.getArtKj(),15));
		ordersRow.setZikTik(DataFormatUtils.setFormatString(resultFmVO.getZikTik()).trim()); //2004.04.21 add 08/17add
		ordersRow.setTka(DataFormatUtils.setFormatString(resultFmVO.getTka()).trim()); //2014.02.19
		ordersRow.setFukMeiKj(this.getSubstringStr(resultFmVO.getFukMeiKj(),15));	
		ordersRow.setFukSziSuu(DataFormatUtils.setFormatString(resultFmVO.getFukSziSuu()));
		ordersRow.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultFmVO.getHbiDte())); //2005.06.14 add
		ordersRow.setKin(DataFormatUtils.setFormatString(String.valueOf(resultFmVO.getKin())));//2005/09/13 add
		resultFmVO.setRrkDte(rrkUpDte); //�����X�V���t(DB�o�^�p) 2005/09/06 add
		ordersRow.setBiKou2(resultFmVO.getBiKou2());//2007/12/25 add
		ordersRow.setTan2(DataFormatUtils.setFormatString(String.valueOf(resultFmVO.getTan2())));//2008/03/10 add
	}
	
	/**
	 * �w�肵�������𒴂����ꍇ��substring���ĕԂ��܂��B
	 * @param str
	 * @param length
	 * @return
	 */
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
	
	/**
	 * �\���i�Ԃ̃t�H�[�}�b�g�i-����������X�y�[�X�Ɋ�����j
	 * @param kigBng
	 * @return
	 */
	private String formatHjiHnb(String kigBng){
		if(kigBng.indexOf("-") != -1){
			String returnKigBng = "";
			String put_space = "";
			String head = "";
			String tail = "";
			
			head = kigBng.substring(0, kigBng.indexOf("-"));
			tail = kigBng.substring(kigBng.indexOf("-") + 1);
			head = head.concat(put_space);
			returnKigBng = head.concat(tail);
			return returnKigBng;
		}else{
			return kigBng;
		}	
	}

}

