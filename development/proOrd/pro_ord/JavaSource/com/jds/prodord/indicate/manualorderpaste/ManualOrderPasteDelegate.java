package com.jds.prodord.indicate.manualorderpaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;

import com.jds.prodord.common.*;
import com.jds.prodord.order.prsorder.PrsOrderForm;
import com.jds.prodord.order.prsorder.PrsOrderVO;
/**
 * ManualOrderPasteDelegate  �}�j���A�������w���ꊇ�\�t���s�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>���������s����N���X�B</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>�E</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteDelegate {
	
	/**
	 * �u�\�t�v�������s��
	 * @param fmVO
	 * @throws Exception
	 */
	public void doDataPaste(ManualOrderPasteVO fmVO)throws Exception{

		final String tab = "\t";
		LinkedList dataList = new LinkedList();
		LinkedList formRows = new LinkedList();

		String data = fmVO.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));

		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);

		try {
			for (int i = 0; i < count; i++) {

				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					if(!txt.trim().equals(""))
						dataList.add(StringUtils.split(txt, tab));

				}

			}
			for (Iterator iter = dataList.iterator(); iter.hasNext();) {

				ManualOrderPasteFormRow row = new ManualOrderPasteFormRow();
				String str[] = (String[])iter.next();
				
				for(int i =0;i<str.length;i++){
					if(i==0){
						row.setKigBng(formatStr(str[i]));
					}else{ 
						//�v���X
						if(fmVO.getPrsFks().equals(ManualOrderPasteForm.PRS)){
							switch(i){
								case 1:
								   row.setSuuRyo(formatStr(str[i]));
								   break;
								default:
								   row.setBiKou2(formatStr(str[i]));
								   break;
							}
						//������	
						}else{
							switch(i){
								case 1:
								   row.setFukSziCod(formatStr(str[i]));
								   break;
								case 2:
								   row.setSuuRyo(formatStr(str[i]));
								   break;
								default:
								   row.setBiKou2(formatStr(str[i]));
								   break;
							}
						}
					}	
				}

				formRows.add(row);
			}
			
			fmVO.setFormRows(formRows);
			
		} catch (IOException e) {
			System.out.println("�\��t���f�[�^�̓ǂݍ��݂ŃG���[���������܂����B");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * �l��null�Ȃ�u�����N�A����ȊO�Ȃ�trim()���ĕԂ��B
	 * @param value �`�F�b�N�Ώۂ̒l
	 * @return �l��null�Ȃ�u�����N�A����ȊO�Ȃ�trim()���ĕԂ��B
	 */
	public String formatStr(String value) {
		if(value == null)
			return "";
		else
			return value.toUpperCase().trim();
	}

	/**
	 * �v���X����
	 * @param fmVO
	 * @param fmForm
	 * @return
	 * @throws SQLException
	 */
	public ManualOrderPasteResult doPrsHachu(ManualOrderPasteVO fmVO,ManualOrderPasteForm fmForm)throws SQLException{
				 	
		ManualOrderPasteDAO fmDao = null;
		ManualOrderPasteResult result = new ManualOrderPasteResult(fmVO,true,"");

		try{
			fmDao = new ManualOrderPasteDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			boolean error = false;

			LinkedList formRows = fmVO.getFormRows();
			LinkedList voList = new LinkedList();
			LinkedList errList = new LinkedList();

			OrderUtils orderUtils = new OrderUtils(fmDao);
			String kigHead ="";
			
			int i=0;
			for (Iterator iter = formRows.iterator(); iter.hasNext(); i++) {
				ManualOrderPasteFormRow row = (ManualOrderPasteFormRow) iter.next();
				// �i�Ԃ����͂���Ă��Ȃ�������A���̍s��
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpKigBng = row.getKigBng();
				
				//�L�����̎擾
				kigHead = DataFormatUtils.getKigHeader(inpKigBng, kigHead);
				//�ȗ��i�Ԃ̕ҏW
				String kigBng = DataFormatUtils.editKigBng(inpKigBng, kigHead);
				
				PrsOrderVO poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN01");

				//�i�ԃ}�X�^�[�Ȃ�
				if(!poVO.getExsitHin01()){
					poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN02");

					//HIN02���Ȃ�������
					if(poVO==null){
						//�s���Ƃ̃G���[����
						errList.add(new String[]{String.valueOf(i),ManualOrderPasteForm.NOT_HIN_EXIST});
						error =true;
						continue;
					}
				}
				
				poVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				poVO.setUsrId(fmVO.getUsrId());


				poVO.setKbn(fmVO.getKbn());//�敪�i���͒l�j

				poVO.setPrsHacSuu(row.getSuuRyo());//�������i���͒l�j

				poVO.setBiKou2(row.getBiKou2());//���l�Q�i���͒l�j

				poVO.setPrsNki(this.calculatePrsNki(poVO.getHbiDte(),poVO.getDaiKaiSkbCod()));//�[��
				poVO.setComment("");//�R�����g
				poVO.setChoksoKbn("0");//�����敪
				poVO.setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//�[�i�於
				//������}�X�^�[����
				poVO.setHacNm(fmDao.getHacNm(poVO.getDaiKaiSkbCod(),poVO.getPrsMkrCod()));

				
				//�݌Ƀ}�X�^�[����
				fmDao.findZai01(poVO);
				//�����ޔ�����R�[�h�E�����ޖ��̎擾
				orderUtils.findFukSziHacSaki(poVO);	

				//���������e�[�u�������i�v���X�j
				fmDao.findHac02Prs(poVO);
				//���������e�[�u�������i�����ށj
				fmDao.findHac02Fuk(poVO);

				
				//��s�t���O
				poVO.setBasedRowFlg("1");
				//�S�i�ԂɃ`�F�b�N������@
				poVO.setCheck_prs1(true);
				if(i == 0){
					poVO.setPre_page(PrsOrderForm.ManualOrderPaste);//�J�ڌ��y�[�W
					poVO.setPrs_FukSgn(PrsOrderForm.PRSHACHU);
				}
			

				voList.add(poVO);				

				
			}
			if(error){
				//�Y���f�[�^�Ȃ�
				result =  new ManualOrderPasteResult(fmVO,false,"");
				result.setErrList(errList);
			}else{
				fmVO.setPrsVOs((PrsOrderVO[]) voList.toArray(new PrsOrderVO[voList.size()]));
				//�Y���f�[�^�Ȃ�
				result =  new ManualOrderPasteResult(fmVO,true,"");
			}


		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return result;
		
	}
	
	/**
	 * �����ޔ���
	 * @param fmVO
	 * @param fmForm
	 * @return
	 * @throws SQLException
	 */
	public ManualOrderPasteResult doFukHachu(ManualOrderPasteVO fmVO,ManualOrderPasteForm fmForm)throws SQLException{
				 	
		ManualOrderPasteDAO fmDao = null;
		ManualOrderPasteResult result = new ManualOrderPasteResult(fmVO,true,"");
		PrsOrderVO[] finded = null;

		try{
			fmDao = new ManualOrderPasteDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
		
			boolean error = false;

			LinkedList formRows = fmVO.getFormRows();
			LinkedList voList = new LinkedList();
			LinkedList errList = new LinkedList();

			OrderUtils orderUtils = new OrderUtils(fmDao);

			String kigHead = "";				

			int i=0;
			for (Iterator iter = formRows.iterator(); iter.hasNext(); i++) {
				ManualOrderPasteFormRow row = (ManualOrderPasteFormRow) iter.next();
				// �i�Ԃ����͂���Ă��Ȃ�������A���̍s��
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpKigBng = row.getKigBng();
				
				//�L�����̎擾
				kigHead = DataFormatUtils.getKigHeader(inpKigBng, kigHead);
				//�ȗ��i�Ԃ̕ҏW
				String kigBng = DataFormatUtils.editKigBng(inpKigBng, kigHead);


				PrsOrderVO poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN01");

				//�i�ԃ}�X�^�[�Ȃ�
				if(!poVO.getExsitHin01()){
					poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN02");

					//HIN02���Ȃ�������
					if(poVO==null){
						//�s���Ƃ̃G���[����///���b�Z�[�W�Ƃ��Ȃɂ�
						errList.add(new String[]{String.valueOf(i),ManualOrderPasteForm.NOT_HIN_EXIST});
						error =true;
						continue;
					}
				}
				//�t���i�\���}�X�^�[�����E�����ރR�[�h�̃Z�b�g���擾
				HashMap resultMap 
					= orderUtils.findFukSzi_SirSkCodArr(
						poVO.getDaiKaiSkbCod(),
						poVO.getKaiSkbCod(),
						poVO.getKigBng());
					
				ArrayList fukSziCod_arr = (ArrayList) resultMap.get("fukSziCod_arr");
				ArrayList fukSziHacSaki_arr = (ArrayList) resultMap.get("fukSziHacSaki_arr");
				
				if(fukSziCod_arr==null||!fukSziCod_arr.contains(row.getFukSziCod())){
					//�s���Ƃ̃G���[����
					errList.add(new String[]{String.valueOf(i),ManualOrderPasteForm.NOT_FKS_EXIST});
					error =true;

					continue;
				}
				

				poVO.setFukSziCod(row.getFukSziCod());//�����޺��ށi���͒l�j

				int idx = fukSziCod_arr.indexOf(row.getFukSziCod());
				poVO.setFukSziHacSaki((String)fukSziHacSaki_arr.get(idx));

				//�����ޔ�����𔭒���R�[�h�ɃZ�b�g
				poVO.setPrsMkrCod(poVO.getFukSziHacSaki());
				//�����ރ}�X�^�[����
				fmDao.getFukSziData(poVO);

				poVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				poVO.setUsrId(fmVO.getUsrId());

				poVO.setKbn(fmVO.getKbn());//�敪
				poVO.setPrsHacSuu(row.getSuuRyo());//�������ɂ͓��͒l
				poVO.setBiKou2(row.getBiKou2());//���l�Q�ɂ͓��͒l
				poVO.setPrsNki(this.calculateFukNki(poVO.getHbiDte(),poVO.getDaiKaiSkbCod()));//�[��
				poVO.setComment("");//�R�����g
				poVO.setChoksoKbn("0");//�����敪

				poVO.setNhnMei(fmDao.getHacNm(poVO.getDaiKaiSkbCod(),poVO.getNhnCod()));//�[�i�於�̎擾
				poVO.setHacNm(fmDao.getHacNm(poVO.getDaiKaiSkbCod(),poVO.getFukSziHacSaki()));//�����於�̎擾


				//�݌Ƀ}�X�^�[����
				fmDao.findZai01(poVO);
					
				//���������e�[�u�������i�v���X�j
				fmDao.findHac02Prs(poVO);
				//���������e�[�u�������i�����ށj
				fmDao.findHac02Fuk(poVO);
				
				//�S�i�ԂɃ`�F�b�N������@
				poVO.setCheck_prs1(true);
				//��s�t���O
				poVO.setBasedRowFlg("1");
				
				if(i == 0){
					poVO.setPre_page(PrsOrderForm.ManualOrderPaste);//�J�ڌ��y�[�W
					poVO.setPrs_FukSgn(PrsOrderForm.FUKHACHU);
				}

			voList.add(poVO);				

			}		
		
		if(error){
			result =  new ManualOrderPasteResult(fmVO,false,"");
			result.setErrList(errList);
		}else{
			fmVO.setPrsVOs((PrsOrderVO[]) voList.toArray(new PrsOrderVO[voList.size()]));
			result =  new ManualOrderPasteResult(fmVO,true,"");
		}

		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}

		return result;
	}
	
	//--------------------------------------------------------------------
	
	/**
	 * �v���X�[���̎Z�o
	 * @param hbiDte
	 * @param daiKaiSkbCod
	 * @return
	 */
	public int calculatePrsNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
		
		int prsnki = 0;
		//�����̓��t
		DateUtils duToday = new DateUtils();
		//2005/05/30 �j�Ђ̏ꍇ�A�[��(���� + �V��)
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_K)||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//�V����
			prsnki = duToday.getDate6Int();
		}else{

			//������
			DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);
			duHbiDte.addToDate(Calendar.DATE, -14);//�������|�P�S��

			//2006/12/19 �e�w�Ђ̏ꍇ
			if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
				//�������|�P�S�����������O�̓��t��������
				if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
					int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
					if(duToday.getDate8Int() > hbiDte8){
						duToday.addToDate(Calendar.DATE, 14);//�P�S����
						prsnki = duToday.getDate6Int();//�P�S����
					}else{
						prsnki = duToday.getDate6Int();//�������t
					}	
				}else{
					prsnki = duHbiDte.getDate6Int();//�������|�P�S��
				}
			}else{
				//�������|�P�S�����������O�̓��t��������
				if(duToday.getDate8Int() > duHbiDte.getDate8Int())
					prsnki = duToday.getDate6Int();//�������t
				else
					prsnki = duHbiDte.getDate6Int();//�������|�P�S��
			}
		}
		return prsnki;
	}

	/**
	 * �����ޔ[���̎Z�o
	 * @param hbiDte
	 * @param daiKaiSkbCod
	 * @return
	 */
	public int calculateFukNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
			
		int fuknki = 0;
		//�����̓��t
		DateUtils duToday = new DateUtils();
		//������
		DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);

		//2006/12/19 �e�w�Ђ̏ꍇ
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			duHbiDte.addToDate(Calendar.DATE, -42);//�������|�S�Q��
			//�������|�S�Q�����������O�̓��t��������
			if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
				int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
				if(duToday.getDate8Int() > hbiDte8){
					duToday.addToDate(Calendar.DATE, 7);//�V����
					fuknki = duToday.getDate6Int();//�V����
				}else{
					fuknki = duToday.getDate6Int();//�������t
				}	
			}else{
				fuknki = duHbiDte.getDate6Int();//�������|�S�Q��
			}
			//LAN�Ђ̏ꍇ	
		}else if(daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//�V����
			fuknki = duToday.getDate6Int();
		}else{
			duHbiDte.addToDate(Calendar.DATE, -21);//�������|�Q�P��
			//�������|�Q�P�����������O�̓��t��������
			if(duToday.getDate8Int() > duHbiDte.getDate8Int())
				fuknki = duToday.getDate6Int();//�������t
			else
				fuknki = duHbiDte.getDate6Int();//�������|�Q�P��
		}
		return fuknki;
	}

	
}

