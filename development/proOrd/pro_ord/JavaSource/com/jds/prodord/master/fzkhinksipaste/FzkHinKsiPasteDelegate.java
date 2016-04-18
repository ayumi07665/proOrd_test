package com.jds.prodord.master.fzkhinksipaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;

import com.jds.prodord.common.*;
/**
*FzkHinKsiPasteDelegate  �t���i�\���}�X�^�[�ꊇ�\��t�����s�N���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    �t���i�\���}�X�^�[�����e�i���X������N���X�B
*
*	[�ύX����]
*/

public class FzkHinKsiPasteDelegate {
	
	/**
	 * �u�\�t�v�������s��
	 * @param fmVO
	 * @throws Exception
	 */
	public void doDataPaste(FzkHinKsiPasteVO fmVO)throws Exception{

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

				FzkHinKsiPasteFormRow row = new FzkHinKsiPasteFormRow();
				String str[] = (String[])iter.next();
				
				for(int i =0;i<str.length;i++){
					if(i==0){
							row.setKigBng(formatStr(str[i]));
					}else	if(i==1){
							row.setFukSziCod(formatStr(str[i]));
					}else{
							row.setSirSk(formatStr(str[i]));
							break;
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
	 * �o�^����
	 * @param fmVO
	 * @param fmForm
	 * @return
	 * @throws SQLException
	 */
	public FzkHinKsiPasteResult doInsert(FzkHinKsiPasteVO fmVO,FzkHinKsiPasteForm fmForm)throws SQLException{
				 	
		FzkHinKsiPasteDAO fmDao = null;
		FzkHinKsiPasteResult result = new FzkHinKsiPasteResult(fmVO,true,"");
		FzkHinKsiPasteVO[] finded = null;

		try{
			fmDao = new FzkHinKsiPasteDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
		
			boolean error = false;

			//�o�^����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			LinkedList formRows = fmVO.getFormRows();
			LinkedList voList = new LinkedList();
			LinkedList errList = new LinkedList();

			OrderUtils orderUtils = new OrderUtils(fmDao);

			String kigHead = "";				
			String oldKigHead = "";				
			String newKigHead = "";				
			String oldKigBng = "";				

						
			StringBuffer sb_fukSziCod = new StringBuffer();
			StringBuffer sb_sirSkCod = new StringBuffer();
			ArrayList line_Arr = new ArrayList();

			int i=0;
			for (Iterator iter = formRows.iterator(); iter.hasNext(); i++) {
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter.next();
				// �i�Ԃ����͂���Ă��Ȃ�������A���̍s��
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpKigBng = row.getKigBng();
				
				//�L�����̎擾
				kigHead = DataFormatUtils.getKigHeader(inpKigBng, kigHead);
				//�ȗ��i�Ԃ̕ҏW
				String kigBng = DataFormatUtils.editKigBng(inpKigBng, kigHead);

				//�i�ԃ}�X�^�[�Ȃ�
				if(!fmDao.hasKigBng(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN01")){
					if(!fmDao.hasKigBng(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN02")){
						//�s���Ƃ̃G���[����///���b�Z�[�W�Ƃ��Ȃɂ�
						errList.add(new int[]{i,FzkHinKsiPasteForm.NOT_HIN_EXIST});
						error =true;
						continue;
					}
				}
				//�����ރR�[�h�Ȃ�
				if(!fmDao.hasFukSziCod(fmVO,row.getFukSziCod()+"")){
					//�s���Ƃ̃G���[����///���b�Z�[�W�Ƃ��Ȃɂ�
					errList.add(new int[]{i,FzkHinKsiPasteForm.NOT_FKS_EXIST});
					error =true;
					continue;
				}
 				//�d����R�[�h�Ȃ�
				if(!fmDao.hassirSkCod(fmVO,row.getSirSk()+"")){
					//�s���Ƃ̃G���[����///���b�Z�[�W�Ƃ��Ȃɂ�
					errList.add(new int[]{i,FzkHinKsiPasteForm.NOT_SIR_EXIST});
					error =true;
					continue;
				}
				//�t���i�\���}�X�^�[
				if(!fmDao.selectForUpdate(fmVO,kigBng)){
					//�s���Ƃ̃G���[����///���b�Z�[�W�Ƃ��Ȃɂ�
					errList.add(new int[]{i,FzkHinKsiPasteForm.MST08_EXIST});
					error =true;
					continue;
				}
			}		

			//�}�X�^�[�`�F�b�N�ŃG���[��������
			if(error){
				result =  new FzkHinKsiPasteResult(fmVO,false,"");
				result.setErrList(errList);
				return result;
			}		

			
			//�i�ԃu���C�N����

			Iterator iter2 = formRows.iterator();

			if (iter2.hasNext()){
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter2.next();

				String inpoldKigBng = row.getKigBng();
				//�L�����̎擾
				oldKigHead = DataFormatUtils.getKigHeader(inpoldKigBng, kigHead);
				//�ȗ��i�Ԃ̕ҏW
				oldKigBng = DataFormatUtils.editKigBng(inpoldKigBng, kigHead);


				sb_fukSziCod.append(row.getFukSziCod());	
				sb_sirSkCod.append(StringUtils.rpad(row.getSirSk(),6," "));

				fmVO.setFuksziCod08String(sb_fukSziCod.toString());
				fmVO.setSirSk08String(sb_sirSkCod.toString());
				fmVO.setKigBng(oldKigBng);
			}
			while(iter2.hasNext()){
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter2.next();
				// �i�Ԃ����͂���Ă��Ȃ�������A���̍s��
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpnewKigBng = row.getKigBng();
				//�L�����̎擾
				newKigHead = DataFormatUtils.getKigHeader(inpnewKigBng, kigHead);
				//�ȗ��i�Ԃ̕ҏW
				String newKigBng = DataFormatUtils.editKigBng(inpnewKigBng, kigHead);
				if(oldKigBng.equals(newKigBng)){
					sb_fukSziCod.append(row.getFukSziCod());	
					sb_sirSkCod.append(StringUtils.rpad(row.getSirSk(),6," "));

					fmVO.setFuksziCod08String(sb_fukSziCod.toString());
					fmVO.setSirSk08String(sb_sirSkCod.toString());
					fmVO.setKigBng(oldKigBng);
				}else{
					//�\�[�g
					doSort(fmVO);
					//�t���i�\���}�X�^�[����
					if(fmDao.selectForUpdate(fmVO,oldKigBng)){
						//�o�^���s
						fmDao.insert(fmVO,upddte,updjkk);
					}

					oldKigBng = newKigBng;					
					fmVO.setFuksziCod08String("");
					fmVO.setSirSk08String("");

					sb_fukSziCod.delete(0,200);
					sb_sirSkCod.delete(0,200);

					sb_fukSziCod.append(row.getFukSziCod());	
					sb_sirSkCod.append(StringUtils.rpad(row.getSirSk(),6," "));

					fmVO.setFuksziCod08String(sb_fukSziCod.toString());
					fmVO.setSirSk08String(sb_sirSkCod.toString());
					fmVO.setKigBng(oldKigBng);
				}
			}

		doSort(fmVO);
		//�t���i�\���}�X�^�[����
		if(fmDao.selectForUpdate(fmVO,oldKigBng)){
			//�o�^���s
			fmDao.insert(fmVO,upddte,updjkk);
		}


		result =  new FzkHinKsiPasteResult(fmVO,true,"");


	}catch(SQLException sqle){
		if(fmDao != null){
			try{
				fmDao.rollback();
			}catch(SQLException sqle3){
				sqle3.printStackTrace();
			}
		}
		throw sqle;
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
	 * @param fmVO
	 */

	 private void doSort(FzkHinKsiPasteVO fmVO) {
		 ArrayList fuk_arr = fmVO.get08FuksziCodArr();
		 ArrayList sir_arr = fmVO.get08SirSkCodArr();
		 TreeMap tm = new TreeMap();
		
		 for(int i = 0; i < fuk_arr.size();i++){
			 HashMap map = new HashMap();
			 map.put("fuk_cod",fuk_arr.get(i));
			 map.put("sir_cod",sir_arr.get(i));
			
			 tm.put(fuk_arr.get(i),map);
		 }
		
		 ArrayList map_arr = new ArrayList();
		 HashMap map_bun1 = new HashMap();
		 Iterator iter = tm.values().iterator(); //����
		
		 while (iter.hasNext()) {
			 HashMap map = (HashMap)iter.next();
			 map_arr.add(map);			
		 }

		 StringBuffer sb_fukSziCod = new StringBuffer();
		 StringBuffer sb_sirSkCod = new StringBuffer();
		 for(int i = 0; i < map_arr.size();i++){	
			 HashMap map = (HashMap)map_arr.get(i);		
			 sb_fukSziCod.append(map.get("fuk_cod"));	
			 sb_sirSkCod.append(StringUtils.rpad((String) map.get("sir_cod"),6," "));
		 }
		 fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		 fmVO.setSirSk08String(sb_sirSkCod.toString());

	 }
		
}

