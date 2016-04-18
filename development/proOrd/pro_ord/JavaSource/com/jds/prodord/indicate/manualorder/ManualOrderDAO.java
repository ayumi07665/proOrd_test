/**
* ManualOrderDAO  �}�j���A�������w���f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/25
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �i�ԃ}�X�^�[(FTBHIN01)�A�݌Ƀe�[�u��(FTBZAI01)�A���������e�[�u��(FTBHAC02)�A
* 			 �����ރ}�X�^�[(FTBFUK01)�A�����ރp�^�[���}�X�^�[(FTBFUK02)�A�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*		2003/06/16�i�m�h�h�j�g�c �h�q
*  			�E�������ځi��ЁE���[�J�[���ށE�`�ԃR�[�h�E�M�m�敪�j�̒ǉ��B
*		2003/06/23
* 			�E������ޔ���� �ǉ��B
* 		2003/06/30
* 			�E�����ޔ����̂Ƃ��A�[�i�於�擾 �ǉ��B
* 		2003/07/23 �i�m�h�h�j���c �Ĕ�
* 			�E�\�[�g�����ǉ��i�`�ԃR�[�h�A�\�[�g�L�[�j�B
* 		2003/08/26 �i�m�h�h�j���c �Ĕ�
* 			�E�V�i�ԃ}�X�^�[(HIN02)�Ή� �ǉ�
* 		2004/02/25�@(�m�h�h�j�X
* 			�E�����ރR�[�h�E�d����̓W�J���p�^�[���}�X�^����t���\���}�X�^�ɕύX 
* 		2004/03/04 (�m�h�h�j�X
* 			�EVAP�̂Ƃ��͕K�������ރR�[�h���\�������悤�ɏC��
* 		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
* 		2005/05/25�i�m�h�h�j�g�c
* 			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 		2005/09/09�i�m�h�h�j�g�c
* 			�EVAP�̏ꍇ�A�����ޖ��̎擾���A��ď����ǉ��i�����޺��ށj
* 		2006/05/10�i�m�h�h�j�c�[ �N��
* 			�E�L���O�Ђ̃����N���b�܂�
* 		2007/02/13 �i�m�h�h�j���c �Ĕ�
* 			�E�i�ԃ}�X�^�[����(�P������)�̂Ƃ��A�`�Ԗ��̃}�X�^�[�Ƃ̌������O�������ɏC��
* 
***/

package com.jds.prodord.indicate.manualorder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.order.prsorder.PrsOrderVO;

public class ManualOrderDAO extends CommonDAO{
	
	public ManualOrderDAO() throws SQLException{
    	super();
	}
	
	//�i�ԃ}�X�^�[����***********************************************************************
	public PrsOrderVO[] findHin01(ManualOrderVO queryVO)throws SQLException{

		String[] RANK = ManualOrderForm.makeRank(queryVO.getDaiKaiSkbCod());

		String sql = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,K.KETNMKJ"
					+ " ,H.ZEIKMITKA FROM FTBHIN01 H" //add 04.04.21 �ō��艿�ǉ�
					+ " LEFT OUTER JOIN FTBMST06 K ON"
					+ " H.KETCOD = K.KETCOD"
					+ " WHERE H.DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					+ " AND K.DAIKAISKBCOD = '" + queryVO.getQueryKaiSkbCod() + "'"
					;
					
		String sql_kai = "";   
		//��Ў��ʃR�[�h
        for(int i=0; i<queryVO.getKaiSkbCod_arr().size(); i++){
          if(i == 0){
          	sql +=  " AND H.KAISKBCOD IN ('";
          	sql_kai +=  " AND H.KAISKBCOD IN ('";
          }
          sql += queryVO.getKaiSkbCod_arr().get(i).toString();
          sql_kai += queryVO.getKaiSkbCod_arr().get(i).toString();
          
          if(i == queryVO.getKaiSkbCod_arr().size() - 1){
            sql  += "')";
            sql_kai += "')";
          }else{
            sql  += "','";
            sql_kai  += "','";
          }
        }
        
        boolean hbi_flg = false;
        boolean ketCod_flg = false;
        boolean mkrBun_flg = false;
        boolean hyoKbn_flg = false;
        boolean kigBng_flg = false;
               	
        //������
        for(int i=0; i<queryVO.getHbiDte_arr().size(); i++){
          hbi_flg = true;
          if(i == 0){
          	sql +=  " AND H.HBIDTE IN (";
          }
          sql += queryVO.getHbiDte_arr().get(i).toString();
          if(i == queryVO.getHbiDte_arr().size() - 1){
            sql  += ")";
          }else{
            sql  += ",";
          }
        }
        
        //�L���ԍ�
        for(int i=0; i<queryVO.getKigBng_arr().size(); i++){
          if(i == 0){
          	sql +=  " AND H.KIGBNG IN ('";
          }
          sql += queryVO.getKigBng_arr().get(i).toString();
          if(i == queryVO.getKigBng_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }

		//�`�ԃR�[�h
        for(int i=0; i<queryVO.getKetCod_arr().size(); i++){
          if(i == 0){
          		sql +=  " AND ";
          	sql +=  "K.KETNMKJ IN (G'";
          }
          sql += queryVO.getKetCod_arr().get(i).toString();
          if(i == queryVO.getKetCod_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "',G'";
          }
        }
        //���[�J�[����
        for(int i=0; i<queryVO.getMkrBun_arr().size(); i++){
          if(i == 0){
          		sql +=  " AND ";
          	sql +=  "H.MKRBUN IN ('";
          }
          sql += queryVO.getMkrBun_arr().get(i).toString();
          if(i == queryVO.getMkrBun_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        //�M�m�敪
        for(int i=0; i<queryVO.getHyoKbn_arr().size(); i++){
          if(i == 0){
          		sql +=  " AND ";
          	sql +=  "H.KREHYOKBN IN ('";
          }
          sql += queryVO.getHyoKbn_arr().get(i).toString();
          if(i == queryVO.getHyoKbn_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        
        if(queryVO.getDaiKaiSkbCod().equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
			sql += " AND H.TOMRAK IN ('";
        
			for(int i=0; i<RANK.length; i++){
				sql += RANK[i];
				if(i == RANK.length -1){
					sql  += "')";
				}else{
					sql  += "','";
				}
			}        	
        }
        
        //�\�[�g��
        sql += " ORDER BY";
        if(hbi_flg)
        	sql += " H.HBIDTE,";
        if(queryVO.getSort_ketCod())
			sql += " H.KETCOD,";
		if(queryVO.getSort_sortKey())
			sql += " H.SRTKEY,";
		sql += " H.KIGBNG";
					
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				PrsOrderVO fmVO = new PrsOrderVO();
				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setHjiHnb(rs.getString("HJIHNB").trim());
				fmVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"�@"));
				fmVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				fmVO.setKetCod(rs.getString("KETCOD").trim());
				fmVO.setHbiDte(rs.getString("HBIDTE").trim());
				fmVO.setTomRak(rs.getString("TOMRAK").trim());
				String wk_ziktik = DataFormatUtils.setFormatString(rs.getString("ZEIKMITKA").trim().substring(0,rs.getString("ZEIKMITKA").trim().indexOf(".")));
				fmVO.setZikTik(wk_ziktik); //2004.04.21 add

				if(!rs.getString("JYTPRSMKR").trim().equals("")){//����v���X���[�J�[�������
					fmVO.setPrsMkrCod(rs.getString("JYTPRSMKR").trim());//����v���X���[�J�[���Z�b�g
				}else{//�Ȃ����
					fmVO.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());//�v���X���[�J�[�R�[�h���Z�b�g
				}
				fmVO.setNhnCod(fmVO.getPrsMkrCod());
				
				if(!rs.getString("FUKSZIMKR").trim().equals(""))
					fmVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				fmVO.setSetSuu(rs.getString("SETSUU").trim());
				fmVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"�@"));
				
				fmVO.setExsitHin01(true);
				finded_Arr.add(fmVO);
				
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);
	}
	
	final static String HIN01 = "FTBHIN01";
	final static String HIN02 = "FTBHIN02";
	//�i�ԃ}�X�^�[����(�P������)****************************************************************
	
	public boolean	findHin01_row(ManualOrderVO queryVO,String kigBng, PrsOrderVO prsVO, String HIN_TABLE)throws SQLException{
		boolean ret;

		String sql = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
					+ " ,H.ZEIKMITKA FROM " + HIN_TABLE + " H"
					+ " LEFT OUTER JOIN FTBMST06 K ON"
					+ " H.KETCOD = K.KETCOD"
					+ " AND K.DAIKAISKBCOD = '" + queryVO.getQueryKaiSkbCod() + "'"
					+ " WHERE H.DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					;
					
		//��Ў��ʃR�[�h
        for(int i=0; i<queryVO.getQueryKaiSkbCodList().size(); i++){
          if(i == 0){
          	sql +=  " AND H.KAISKBCOD IN ('";
          }
          sql += queryVO.getQueryKaiSkbCodList().get(i).toString();
          
          if(i == queryVO.getQueryKaiSkbCodList().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }

        //�L���ԍ�
        sql +=  " AND H.KIGBNG ='"+ DataFormatUtils.mk_srykig(kigBng) +"'";
        
        //�\�[�g��
        sql += " ORDER BY H.KIGBNG";
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				prsVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				prsVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				prsVO.setKigBng(rs.getString("KIGBNG").trim());
				prsVO.setHjiHnb(rs.getString("HJIHNB").trim());
				if(prsVO.getHjiHnb().equals(""))
					prsVO.setHjiHnb(kigBng);
				prsVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"�@"));
				prsVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				prsVO.setKetCod(rs.getString("KETCOD").trim());
				prsVO.setHbiDte(rs.getString("HBIDTE").trim());
				prsVO.setTomRak(rs.getString("TOMRAK").trim());
				String wk_ziktik = DataFormatUtils.setFormatString(rs.getString("ZEIKMITKA").trim().substring(0,rs.getString("ZEIKMITKA").trim().indexOf(".")));
				prsVO.setZikTik(wk_ziktik); //2004.04.21 add
				if(!rs.getString("JYTPRSMKR").trim().equals("")){//����v���X���[�J�[�������
					prsVO.setPrsMkrCod(rs.getString("JYTPRSMKR").trim());//����v���X���[�J�[���Z�b�g
				}else{//�Ȃ����
					prsVO.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());//�v���X���[�J�[�R�[�h���Z�b�g
				}
				prsVO.setNhnCod(prsVO.getPrsMkrCod());
				
				prsVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				prsVO.setSetSuu(rs.getString("SETSUU").trim());
				prsVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"�@"));
				prsVO.setExsitHin01(true);
				ret = true;
				
			}else{
				if(HIN_TABLE.equals(HIN02)){
					prsVO.setDaiKaiSkbCod(queryVO.getDaiKaiSkbCod());
					prsVO.setKaiSkbCod(queryVO.getQueryKaiSkbCod());//���[�U�[�̉�Ђ��Z�b�g
					prsVO.setSetSuu("1");//�f�t�H���g��"1"
					prsVO.setKigBng(kigBng);
					prsVO.setHjiHnb(kigBng);
					prsVO.setExsitHin01(false);
				}
				ret = false;
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return ret;	
	}			

	//�����於�̎擾*******************************************************************
	
	public void findHacNm(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getPrsMkrCod());

			rs = ps.executeQuery();
			
			if(rs.next()){
				fmVO.setHacNm(StringUtils.removeSuffix(rs.getString("HACNM"),"�@"));
			}
				
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}
	
	//�݌Ƀe�[�u������***********************************************************************
	public void findZai01(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT PRSMNYKEI,PRSHACRUI,FUKMNYKEI,PRSNYOKEI,FUKNYOKEI,FUKHACRUI,FUKZAISUU"
					+ " FROM FTBZAI01 "
					+ " WHERE KAISKBCOD = ? AND KIGBNG = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKigBng());

			rs = ps.executeQuery();
		
			if(rs.next()){
				fmVO.setPrsMnyKei(rs.getString("PRSMNYKEI").trim());
				fmVO.setPrsHacRui(rs.getString("PRSHACRUI").trim());
				fmVO.setFukMnyKei(rs.getString("FUKMNYKEI").trim());
				fmVO.setFukHacRui(rs.getString("FUKHACRUI").trim());
				fmVO.setFukZaiSuu(rs.getString("FUKZAISUU").trim());
				fmVO.setPrsNyoKei(rs.getString("PRSNYOKEI").trim());
				fmVO.setFukNyoKei(rs.getString("FUKNYOKEI").trim());
				fmVO.setExsitZai01(true);
			}else{
				fmVO.setExsitZai01(false);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}

//-----------------------------------------------------------------------------------
//���������e�[�u�������i�v���X�j*******************************************************************
	public void findHac02Prs(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT HACSUU,NYOSUU,NKI,SINKYUKBN FROM FTBHAC02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? AND BUNCOD = ?"
					+ " ORDER BY HACSYODTE DESC"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getKigBng());
			ps.setString(4,"0");//�v���X

			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next() && count<2){
				
				switch(count){
					case 0:
						fmVO.setPrsHacSuu1(rs.getString("HACSUU").trim());
						fmVO.setPrsHacNyo1(rs.getString("NYOSUU").trim());
						fmVO.setPrsHacNki1(rs.getString("NKI").trim());
						fmVO.setPrsKbn1(rs.getString("SINKYUKBN").trim());
						break;
					case 1:
						fmVO.setPrsHacSuu2(rs.getString("HACSUU").trim());
						fmVO.setPrsHacNyo2(rs.getString("NYOSUU").trim());
						fmVO.setPrsHacNki2(rs.getString("NKI").trim());
						fmVO.setPrsKbn2(rs.getString("SINKYUKBN").trim());
						break;
				}
				count++;
				fmVO.setExsitHac02Prs(true);
			}						
		
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}
	
	//���������e�[�u�������i�����ށj*******************************************************************
	public void findHac02Fuk(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT HACSUU,NYOSUU,NKI,SINKYUKBN FROM FTBHAC02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? AND BUNCOD = ? AND PRSFUKSZICOD = ?"
					+ " ORDER BY HACSYODTE DESC"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	

		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getKigBng());
			ps.setString(4,fmVO.getBunCod());
			ps.setString(5,fmVO.getFukSziCod());

			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next() && count<2){
				
				switch(count){
					case 0:
						fmVO.setFukHacSuu1(rs.getString("HACSUU").trim());
						fmVO.setFukHacNki1(rs.getString("NKI").trim());
						fmVO.setFukKbn1(rs.getString("SINKYUKBN").trim());
						break;
					case 1:
						fmVO.setFukHacSuu2(rs.getString("HACSUU").trim());
						fmVO.setFukHacNki2(rs.getString("NKI").trim());
						fmVO.setFukKbn2(rs.getString("SINKYUKBN").trim());
						break;
				}
				count++;
				fmVO.setExsitHac02Fuk(true);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}
		
//-------------------------------------------------------------------------------------------
	public static final int EXIST = 0;//���݂���
	public static final int NOT_EXIST = 1;//���݂��Ȃ�
	public static final int RANK_ERR = 2;//���݂��邪�����N�G���[
//**�i�ԑ��݃`�F�b�N******************************************************************
	public int hasKigBng(ManualOrderVO fmVO, String kigBng)throws SQLException{		

		String[] RANK = ManualOrderForm.makeRank(fmVO.getDaiKaiSkbCod());
		if(kigBng.equals(""))
			return EXIST;
		String query = "";
		
		String sql = "SELECT KIGBNG"
					+ " FROM FTBHIN01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					;
					
		//��Ў��ʃR�[�h
        for(int i=0; i<fmVO.getQueryKaiSkbCodList().size(); i++){
          if(i == 0)
          	sql +=  " AND KAISKBCOD IN ('";
          sql += fmVO.getQueryKaiSkbCodList().get(i).toString();
          if(i == fmVO.getQueryKaiSkbCodList().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        
		sql		   += " AND UPDKBN <> 'D' ";
		//�܂��͏ȗ��L���Ō���
		query	= sql + " AND SRYKIG = '" + kigBng + "'";

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				boolean err_flg = true;
				String rank = rs.getString("TOMRAK").trim();
//				for(int i=0; i<ManualOrderForm.RANK.length; i++){
//					if(rank.equals(ManualOrderForm.RANK[i]))
				for(int i=0; i<RANK.length; i++){
					if(rank.equals(RANK[i]))
						err_flg = false;
				}
				if(err_flg){
					return RANK_ERR;
				}else{
					kigBng = rs.getString("KIGBNG").trim();//�������ꂽ�L���ԍ����Z�b�g���Ȃ���
					return EXIST;
				}	
			}else{
				//�ȗ��i�ԂŌ������Č��ʂȂ���������A�L���ԍ��Ō������Ȃ���
				query	= sql + " AND KIGBNG = '" + kigBng + "'";
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					boolean err_flg = true;
					String rank = rs.getString("TOMRAK").trim();
//					for(int i=0; i<ManualOrderForm.RANK.length; i++){
//						if(rank.equals(ManualOrderForm.RANK[i]))
					for(int i=0; i<RANK.length; i++){
						if(rank.equals(RANK[i]))
							err_flg = false;
					}
					if(err_flg){
						return RANK_ERR;
					}else{
						return EXIST;
					}	
				}
				return NOT_EXIST;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			
	}


	public static final int nhnMei = 0;//�[�i�於
	public static final int fukHacMei = 1;//�����ޔ����於
	//������}�X�^�[����(�[�i�於�̎擾)*******************************************************************
	
	public void findMst03(PrsOrderVO fmVO,int flg)throws SQLException{
		String sql = "SELECT SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?";

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			if(flg==nhnMei)
				ps.setString(2,fmVO.getNhnCod());
			if(flg==fukHacMei)
				ps.setString(2,fmVO.getFukSziHacSaki());

			rs = ps.executeQuery();
			
			if(rs.next()){
				if(flg==nhnMei)
					fmVO.setNhnMei(StringUtils.removeSuffix(rs.getString("HACNM"),"�@"));
				if(flg==fukHacMei)
					fmVO.setHacNm(StringUtils.removeSuffix(rs.getString("HACNM"),"�@"));				
			}else{
				if(flg==nhnMei)
					fmVO.setNhnMei("");
				if(flg==fukHacMei)
					fmVO.setHacNm("");				
			}
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		
	}
	
	//�����ރ}�X�^�[����(�i�ԃ}�X�^�[�Ȃ��̏ꍇ)*******************************************************************
	public void findFuk01_NotExistHin(PrsOrderVO fmVO)throws SQLException{

		// K�̏ꍇ
		// �����ޔ����͍s�Ȃ�Ȃ�
		if (fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_K))
			return;
			
		String sql = "SELECT FUKSZINMKJ,FUKSZICOD,BUNCOD FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND BUNCOD = '1'"
					+ " ORDER BY FUKSZICOD"; //2005/09/14 add

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
				
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			 
			rs = ps.executeQuery();
			
			ArrayList fukSziCod_arr = new ArrayList();
			ArrayList bunCod_arr = new ArrayList();
			if(rs.next()){
				fmVO.setFukSziCod(rs.getString("FUKSZICOD").trim());
				fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@"));
				fmVO.setBasedRowFlg("1"); //2004/08/17 add
				fmVO.setBunCod(rs.getString("BUNCOD").trim());

				bunCod_arr.add(rs.getString("BUNCOD").trim());
				fukSziCod_arr.add(rs.getString("FUKSZICOD").trim());
			}		
			fmVO.setBunCod_arr(bunCod_arr);    //���ރR�[�h
			fmVO.setFukSziCod_arr(fukSziCod_arr); //�����ރR�[�h
							
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}	
//	**�����N�`�F�b�N******************************************************************
	  public int rankCheck(ManualOrderVO fmVO, String kigBng)throws SQLException{		

	  	  String[] RANK = ManualOrderForm.makeRank(fmVO.getDaiKaiSkbCod());
		  if(kigBng.equals(""))
			  return EXIST;
		  String query = "";
		
		  String sql = "SELECT KIGBNG,TOMRAK"
					  + " FROM FTBHIN01 "
					  + " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					  ;
					
		  //��Ў��ʃR�[�h
		  for(int i=0; i<fmVO.getQueryKaiSkbCodList().size(); i++){
			if(i == 0)
			  sql +=  " AND KAISKBCOD IN ('";
			sql += fmVO.getQueryKaiSkbCodList().get(i).toString();
			if(i == fmVO.getQueryKaiSkbCodList().size() - 1){
			  sql  += "')";
			}else{
			  sql  += "','";
			}
		  }
        
		  sql		   += " AND UPDKBN <> 'D' ";
		  query	= sql + " AND KIGBNG = '"+ DataFormatUtils.mk_srykig(kigBng) +"'";

		  ResultSet rs = null;
		  Statement stmt = null;
		  try{
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery(query);
			
			  if(rs.next()){
				  boolean err_flg = true;
				  String rank = rs.getString("TOMRAK").trim();
				  for(int i=0; i<RANK.length; i++){
					  if(rank.equals(RANK[i]))
						  err_flg = false;
				  }
				  if(err_flg){
					  return RANK_ERR;
				  }else{
					  kigBng = rs.getString("KIGBNG").trim();//�������ꂽ�L���ԍ����Z�b�g���Ȃ���
					  return EXIST;
				  }	
//			  }else{
//				  //�ȗ��i�ԂŌ������Č��ʂȂ���������A�L���ԍ��Ō������Ȃ���
//				  query	= sql + 
//				  rs = stmt.executeQuery(query);
//				
//				  if(rs.next()){
//					  boolean err_flg = true;
//					  String rank = rs.getString("TOMRAK").trim();
//					  for(int i=0; i<RANK.length; i++){
//						  if(rank.equals(RANK[i]))
//							  err_flg = false;
//					  }
//					  if(err_flg){
//						  return RANK_ERR;
//					  }else{
//						return EXIST;
//					  }
//				  }
			  }
			return EXIST;
			
		  }finally{
			  if(rs != null)
				  rs.close();
			  if(stmt != null)
				  stmt.close();
		  }			
	  }
}