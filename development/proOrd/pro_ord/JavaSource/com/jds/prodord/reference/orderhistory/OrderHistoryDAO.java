/**
* OrderHistoryDAO  ���������Ɖ��ʃf�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    ���������e�[�u��(FTBHAC02)�A�i�ԃ}�X�^�[(FTBHIN01)�A�����ލ݌Ƀe�[�u��(FTBHIN12)�A
* 			 �`�ԃ����N�ʏ����}�X�^�[(FTBMST05)�A�����ރ}�X�^�[(FTBFUK01)�A�����ރp�^�[���}�X�^�[(FTBFUK02)�A
* 			 �݌Ƀe�[�u��(FTBZAI01)�A�������f�[�^(FTBHAC01)�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*		2003/07/18�i�m�h�h�j���c �Ĕ�
* 			�E�V�������敪���X�V�\���ڂ�
* 			�E�i�ԃ}�X�^�[�������A�\���i�Ԃ����݂��Ȃ�������A���������̋L���ԍ���\���i�ԂɃZ�b�g
* 		2003/08/06 �i�m�h�h�j���c �Ĕ�
* 			�E���������������A�i�ԃ}�X�^�[�Ƃ̌������O��������
* 		2003/08/21 �i�m�h�h�j���c �Ĕ�
* 			�E�\�[�g���ύX(������,�V���敪,�L���ԍ�,�������ԍ�,�s�ԍ� �� ������,�L���ԍ�,�������ԍ�)
*		2003/08/25 �i�m�h�h�j���c �Ĕ�
* 			�E�V�i�ԃ}�X�^�[(HIN02)�Ή� �ǉ�
* 		2004/01/22 �i�m�h�h�j�X
* 			�E���ɐ�����\�ɏC��
* 		2004/02/25�@(�m�h�h�j�X
* 			�E�����ރR�[�h�E�d����̓W�J���p�^�[���}�X�^����t���\���}�X�^�ɕύX
* 		2004/02/27 �i�m�h�h�j�X
* 			�E���[�T�C�����X�V����悤�ɏC��
* 		2004/03/04 (�m�h�h�j�X
* 			�EVAP�̂Ƃ��͕K�������ރR�[�h���\�������悤�ɏC��
* 		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		2004/04/01 (NII) �X
* 			�E�w������ɓ��ɓ�(From-to)��ǉ�
* 		2004/08/03 (NII) ����
* 			�E���������e�[�u���X�V�ɓ��ɓ���ǉ�
* 		2005/05/25�i�m�h�h�j�g�c
* 			�E�������ꊇ�o�͑Ή��i�������f�[�^�o�^�Ƀ��[�U�[�h�c�̒ǉ��j
* 			�E���������e�[�u���������A�ꏊ�R�[�h��ǉ�
* 		2005/09/09�i�m�h�h�j�g�c
* 			�E���׍��ڂɋ��z�̒ǉ�
* 			�EVAP�̏ꍇ�A�����޺��ޕ����g�p�ɂ�蕛���ޖ��̎擾���A�����޺��ނ������ɒǉ�
* 			�E�������M�̏ꍇ�A�����X�V���𔭒������e�[�u���E�������f�[�^�ɍX�V
* 			�E�\�[�g�����i���������̏ꍇ�j�ǉ�
* 		2005/12/01�i�m�h�h�j�g�c
* 			�E���z���ݸ�Ή�
* 		2006/01/25�i�m�h�h�j�c�[
* 			�E�_�E�����[�h���ڂɒ��c���ǉ�
* 		2006/08/08 �i�m�h�h�j���c �Ĕ�
* 			�E���������f�[�^�擾���ɁA�e�[�u�����������Ĉ�x�Ŏ擾����悤�ɕύX
*           �E�����`�[���s�p�����p���\�b�h�ƁA���������f�[�^�擾�p���\�b�h�𕪂���
* 		2007/12/05�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɃ^�C�g�������ǉ�
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
*		2008/02/25�i�m�h�h�j�c��
* 			�E���[�ς݂�HAC01.�f�[�^����FLG��'1'���Z�b�g
*		2008/03/07�i�m�h�h�j�c��
* 			�E�P���̒ǉ�
*		2008/06/02�i�m�h�h�j�c��
* 			�E�u�`�o�Ё@�P���E���z�̏C��
*
**/

package com.jds.prodord.reference.orderhistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.QueryUtils;
import com.jds.prodord.common.StringUtils;

public class OrderHistoryDAO extends CommonDAO{
	
	public OrderHistoryDAO() throws SQLException{
    	super();
	}	

//--------------------------------------------------------------------------------------------------

	//���������e�[�u������(�����`�[���s�p����) *************************************************
	
	public OrderHistoryVO[] findHac02(OrderHistoryVO queryVO)throws SQLException{
		String query = 
					"SELECT " +
						"HAC02.DAIKAISKBCOD," +
						"HAC02.KAISKBCOD," +
						"HAC02.HACCOD," +
						"HAC02.SINKYUKBN," +
						"HAC02.KIGBNG," +
						"HAC02.HACSYOBNG," +
						"HAC02.HACSYODTE," +
						"HAC02.SYRZMISGN," +
						"HAC02.PRSFUKSZICOD," +
						"HAC02.HACSUU," +
						"HAC02.NYOSUU," +
						"HAC02.NKI," +
						"HAC02.GYOBNG," +
						"HAC02.NHNMEIKJ," +
						"HAC02.BUNCOD," +
						"HAC02.UPDDTE," +
						"HAC02.UPDJKK," +
						"VALUE(HIN01.FUKSZIMKR,VALUE(HIN02.FUKSZIMKR,'')) AS FUKSZIMKR," +
						"HAC02.CMT," +
						"HAC02.NYODTE," +
						"HAC02.SYRSUU," +
						"HAC02.SEQNO," +
						"HAC02.KNUSGN," +
						"HAC02.RRKTBL," +
						"HAC02.TAN," +
						"HAC02.BIKOU2," +
						"HAC02.TAN2 ";
						
			  query += "FROM " +
							"FTBHAC02 HAC02 ";
	
			  query += "LEFT OUTER JOIN FTBHIN01 HIN01 ON " +
							 "HAC02.DAIKAISKBCOD = HIN01.DAIKAISKBCOD AND " +
							 "HAC02.KAISKBCOD = HIN01.KAISKBCOD AND " +
							 "HAC02.KIGBNG = HIN01.KIGBNG ";
									
			  query += "LEFT OUTER JOIN FTBHIN02 HIN02 ON " +
							 "HAC02.DAIKAISKBCOD = HIN02.DAIKAISKBCOD AND " +
							 "HAC02.KAISKBCOD = HIN02.KAISKBCOD AND " +
							 "HAC02.KIGBNG = HIN02.KIGBNG ";
			 
		//��\��Ў��ʃR�[�h
		query += "WHERE HAC02.DAIKAISKBCOD = '"+ queryVO.getDaiKaiSkbCod() + "'";

		//��Ў��ʃR�[�h
		if(queryVO.getKaiSkbCodList().size() > 0){
			query += " AND HAC02.KAISKBCOD "
				+ QueryUtils.editArrayToQuery(queryVO.getKaiSkbCodList());
		}
		//�������ԍ�
    	if(!queryVO.getHacSyoBngFrm_H().equals("")){
      		query += " AND HAC02.HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBngFrm_H()) + "'";
    	} 
		//������
    	if(!queryVO.getHacSyoDteFrm_H().equals("")){
      		query += " AND HAC02.HACSYODTE = " + queryVO.getHacSyoDteFrm_H() + "";
    	}
		query += " ORDER BY HAC02.HACCOD,HAC02.KIGBNG,HAC02.HACSYOBNG,HAC02.GYOBNG";


//String[] param = {};
//System.out.println("findHac02 sql : " + QueryUtils.fillInQuestion(query, param));

  		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList finded_Arr = new ArrayList();	
		
        try{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
	
			while(rs.next()){
				OrderHistoryVO fmVO = new OrderHistoryVO();
				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setHacCod(rs.getString("HACCOD").trim());
				fmVO.setSinKyuKbn(rs.getString("SINKYUKBN").trim());
				fmVO.setSinKyuKbnOld(rs.getString("SINKYUKBN").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setHacSyoBng(rs.getString("HACSYOBNG").trim());
				fmVO.setHacSyoDte(rs.getString("HACSYODTE").trim());
				fmVO.setSyrZmiSgn(rs.getString("SYRZMISGN").trim());
				fmVO.setPrsFukSziCod(rs.getString("PRSFUKSZICOD").trim());
				fmVO.setHacSuu(rs.getString("HACSUU").trim());
				fmVO.setHacSuuOld(rs.getString("HACSUU").trim());
				fmVO.setNyoSuu(rs.getString("NYOSUU").trim());
				fmVO.setNki(rs.getInt("NKI"));
				fmVO.setGyoBng(rs.getString("GYOBNG").trim());
				fmVO.setNhnMeiKj(StringUtils.removeSuffix(rs.getString("NHNMEIKJ"),"�@"));
				fmVO.setBunCod(rs.getString("BUNCOD").trim());
				fmVO.setUpdDte(rs.getInt("UPDDTE"));
				fmVO.setUpdJkk(rs.getInt("UPDJKK"));
				fmVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				fmVO.setCmt(StringUtils.removeSuffix(rs.getString("CMT"),"�@"));
				fmVO.setNyoDte(rs.getString("NYODTE").trim());
				fmVO.setSyrSuu(rs.getString("SYRSUU").trim());
				fmVO.setSeqNo(rs.getString("SEQNO").trim());
				fmVO.setKnuSgn(rs.getString("KNUSGN").trim()); //04.02.27 add
				fmVO.setBshCod(queryVO.getBshCod());//2005/05/25 add
				fmVO.setRrkTbl(rs.getString("RRKTBL").trim());	//2005/09/13 add
				if(queryVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_FX)){
					fmVO.setKin(rs.getString("TAN"));
					fmVO.setKinOld(rs.getString("TAN"));
				}else{
					fmVO.setKin(String.valueOf(rs.getInt("TAN")));
					fmVO.setKinOld(String.valueOf(rs.getInt("TAN")));
				}
				fmVO.setBiKou2(rs.getString("BIKOU2").trim());
				fmVO.setTan2(rs.getString("TAN2"));				
				fmVO.setTan2Old(rs.getString("TAN2"));

				finded_Arr.add(fmVO);
			}

		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

		return (OrderHistoryVO[])finded_Arr.toArray(new OrderHistoryVO[finded_Arr.size()]);
	}


	//���������f�[�^�����擾 ***************************************************************
	
	public int getHac02Count(OrderHistoryVO queryVO) throws SQLException {
		
		int count = 0;
		
		String query = "SELECT COUNT(*) FROM FTBHAC02 HAC02 ";
	
		query += "LEFT OUTER JOIN FTBHIN01 HIN01 ON " +
					 "HAC02.DAIKAISKBCOD = HIN01.DAIKAISKBCOD AND " +
					 "HAC02.KAISKBCOD = HIN01.KAISKBCOD AND " +
					 "HAC02.KIGBNG = HIN01.KIGBNG ";
					
		query += "LEFT OUTER JOIN FTBHIN02 HIN02 ON " +
					 "HAC02.DAIKAISKBCOD = HIN02.DAIKAISKBCOD AND " +
					 "HAC02.KAISKBCOD = HIN02.KAISKBCOD AND " +
					 "HAC02.KIGBNG = HIN02.KIGBNG ";
		
		//����������������擾
		query += makeQueryCondition(queryVO);
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
	
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		
		return count;
	}	
	
	//���������f�[�^���� *******************************************************************
	
	public OrderHistoryVO[] findHac02Data(OrderHistoryVO queryVO)throws SQLException{

		String query = 
					"SELECT " +
						"HAC02.DAIKAISKBCOD," +
						"HAC02.KAISKBCOD," +
						"HAC02.HACCOD," +
						"HAC02.SINKYUKBN," +
						"HAC02.KIGBNG," +
						"HAC02.HACSYOBNG," +
						"HAC02.HACSYODTE," +
						"HAC02.SYRZMISGN," +
						"HAC02.PRSFUKSZICOD," +
						"HAC02.HACSUU," +
						"HAC02.NYOSUU," +
						"HAC02.NKI," +
						"HAC02.GYOBNG," +
						"HAC02.NHNMEIKJ," +
						"HAC02.BUNCOD," +
						"HAC02.UPDDTE," +
						"HAC02.UPDJKK," +
						"VALUE(HIN01.TITKJ,VALUE(HIN02.TITKJ,G'')) AS TITKJ," +
						"VALUE(HIN01.HBIDTE,VALUE(HIN02.HBIDTE,0)) AS HBIDTE," +
						"VALUE(HIN01.HJIHNB,VALUE(HIN02.HJIHNB,'')) AS HJIHNB," +
						"VALUE(HIN01.FUKSZIMKR,VALUE(HIN02.FUKSZIMKR,'')) AS FUKSZIMKR," +
						"HAC02.CMT," +
						"HAC02.NYODTE," +
						"HAC02.SYRSUU," +
						"HAC02.SEQNO," +
						"HAC02.KNUSGN," +
						"HAC02.RRKTBL," +
						"HAC02.TAN," +
						"HAC02.BIKOU2," +
						"HAC02.TAN2," +
						"ZAI.AZISUU," +
						"ZAI.JUCZAN," +
						"VALUE((CASE WHEN HAC02.BUNCOD IN ('1','2') THEN HIN12.FUKSZISUU ELSE 0 END), 0) AS FUKSZISUU," +
						"FUK01.FUKSZINMKJ " +
					"FROM " +
						"FTBHAC02 HAC02 ";
	
		   query += "LEFT OUTER JOIN FTBHIN01 HIN01 ON " +
						"HAC02.DAIKAISKBCOD = HIN01.DAIKAISKBCOD AND " +
						"HAC02.KAISKBCOD = HIN01.KAISKBCOD AND " +
						"HAC02.KIGBNG = HIN01.KIGBNG ";
					
		   query += "LEFT OUTER JOIN FTBHIN02 HIN02 ON " +
						"HAC02.DAIKAISKBCOD = HIN02.DAIKAISKBCOD AND " +
						"HAC02.KAISKBCOD = HIN02.KAISKBCOD AND " +
						"HAC02.KIGBNG = HIN02.KIGBNG ";
					
		   query += "LEFT OUTER JOIN FTBZAI01 ZAI ON " +
						"HAC02.DAIKAISKBCOD = ZAI.KAISKBCOD AND " +
						"HAC02.KIGBNG = ZAI.KIGBNG ";
	
		   query += "LEFT OUTER JOIN " +
						"(SELECT " +
							"HAC02.DAIKAISKBCOD," +
							"HAC02.KAISKBCOD," +
							"HAC02.HACSYODTE," +
							"HAC02.HACSYOBNG," +
							"HAC02.SYRSUU," +
							"HAC02.SEQNO," +
							"HAC02.GYOBNG," +
							//�i�ԃf�[�^(������)���� ���� �敪���T���v�� ���� ������ �� ��������������'S' 
							"(CASE WHEN VALUE(HIN01.HBIDTE,VALUE(HIN02.HBIDTE,-1)) > 0 THEN " +
								"(CASE WHEN HAC02.SINKYUKBN = '2' THEN " +
									"(CASE WHEN " +
										"(CASE WHEN HACSYODTE >= 800000 " +
											"THEN 19000000 + HACSYODTE " +
											"ELSE 20000000 + HACSYODTE END)" +
										" < " +
										"(CASE WHEN VALUE(HIN01.HBIDTE,VALUE(HIN02.HBIDTE,0)) >= 800000 " +
											"THEN 19000000 + VALUE(HIN01.HBIDTE,VALUE(HIN02.HBIDTE,0)) " +
											"ELSE 20000000 + VALUE(HIN01.HBIDTE,VALUE(HIN02.HBIDTE,0)) END) " +
									"THEN 'S' ELSE '' END) " +
								"ELSE '' END) " +
							"ELSE '' END) AS SMPKBN " +
						"FROM FTBHAC02 HAC02 " +
						"LEFT OUTER JOIN FTBHIN01 HIN01 ON " +
							"HAC02.DAIKAISKBCOD = HIN01.DAIKAISKBCOD AND " +
							"HAC02.KAISKBCOD = HIN01.KAISKBCOD AND " +
							"HAC02.KIGBNG = HIN01.KIGBNG " +
						"LEFT OUTER JOIN FTBHIN02 HIN02 ON " +
							"HAC02.DAIKAISKBCOD = HIN02.DAIKAISKBCOD AND " +
							"HAC02.KAISKBCOD = HIN02.KAISKBCOD AND " +
							"HAC02.KIGBNG = HIN02.KIGBNG" +
						") SMPTBL ON " +
						"HAC02.DAIKAISKBCOD = SMPTBL.DAIKAISKBCOD AND " +
						"HAC02.KAISKBCOD = SMPTBL.KAISKBCOD AND " +
						"HAC02.HACSYODTE = SMPTBL.HACSYODTE AND " +
						"HAC02.HACSYOBNG = SMPTBL.HACSYOBNG AND " +
						"HAC02.SYRSUU = SMPTBL.SYRSUU AND " +
						"HAC02.SEQNO = SMPTBL.SEQNO AND " +
						"HAC02.GYOBNG = SMPTBL.GYOBNG ";

		   query += "LEFT OUTER JOIN FTBHIN12 HIN12 ON " +
						"HAC02.DAIKAISKBCOD = HIN12.DAIKAISKBCOD AND " +
						"HAC02.KAISKBCOD = HIN12.KAISKBCOD AND " +
						"HAC02.KIGBNG = HIN12.KIGBNG AND " +
						"HAC02.PRSFUKSZICOD = HIN12.FUKSZICOD AND " +
						"HIN12.SMPKBN = SMPTBL.SMPKBN ";
					
			query += "LEFT OUTER JOIN FTBFUK01 FUK01 ON " +
					 "HAC02.DAIKAISKBCOD = FUK01.DAIKAISKBCOD AND " +
				     "HAC02.KAISKBCOD = FUK01.KAISKBCOD AND " +
					 "HAC02.PRSFUKSZICOD = FUK01.FUKSZICOD ";
	
		//����������������擾
		query += makeQueryCondition(queryVO);

		//���������̏ꍇ
		if (queryVO.isChkHacJun())
			query += " ORDER BY HAC02.BUNCOD,HAC02.HACCOD,HAC02.HACSYOBNG,HAC02.GYOBNG";
		else
			query += " ORDER BY HAC02.HACCOD,HAC02.KIGBNG,HAC02.HACSYOBNG,HAC02.GYOBNG";

String[] param = {};
debug("findHac02Data sql : " + QueryUtils.fillInQuestion(query, param));

		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList finded_Arr = new ArrayList();	
		
		try{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
	
			while(rs.next()){
				OrderHistoryVO fmVO = new OrderHistoryVO();
				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setHacCod(rs.getString("HACCOD").trim());
				fmVO.setSinKyuKbn(rs.getString("SINKYUKBN").trim());
				fmVO.setSinKyuKbnOld(rs.getString("SINKYUKBN").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setHacSyoBng(rs.getString("HACSYOBNG").trim());
				fmVO.setHacSyoDte(rs.getString("HACSYODTE").trim());
				fmVO.setSyrZmiSgn(rs.getString("SYRZMISGN").trim());
				fmVO.setPrsFukSziCod(rs.getString("PRSFUKSZICOD").trim());
				fmVO.setHacSuu(rs.getString("HACSUU").trim());
				fmVO.setHacSuuOld(rs.getString("HACSUU").trim());
				fmVO.setNyoSuu(rs.getString("NYOSUU").trim());
				fmVO.setNki(rs.getInt("NKI"));
				fmVO.setGyoBng(rs.getString("GYOBNG").trim());
				fmVO.setNhnMeiKj(StringUtils.removeSuffix(rs.getString("NHNMEIKJ"),"�@"));
				fmVO.setBunCod(rs.getString("BUNCOD").trim());
				fmVO.setUpdDte(rs.getInt("UPDDTE"));
				fmVO.setUpdJkk(rs.getInt("UPDJKK"));
				fmVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				fmVO.setHbiDte(rs.getString("HBIDTE"));
				fmVO.setHjiHnb(rs.getString("HJIHNB").trim());
				if(fmVO.getHjiHnb().equals(""))
					fmVO.setHjiHnb(fmVO.getKigBng());
				fmVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				fmVO.setCmt(StringUtils.removeSuffix(rs.getString("CMT"),"�@"));
				fmVO.setNyoDte(rs.getString("NYODTE").trim());
				fmVO.setSyrSuu(rs.getString("SYRSUU").trim());
				fmVO.setSeqNo(rs.getString("SEQNO").trim());
				fmVO.setKnuSgn(rs.getString("KNUSGN").trim()); //04.02.27 add
				fmVO.setBshCod(queryVO.getBshCod());//2005/05/25 add
				fmVO.setRrkTbl(rs.getString("RRKTBL").trim());	//2005/09/13 add
				if(queryVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_FX)){
					fmVO.setKin(rs.getString("TAN"));
					fmVO.setKinOld(rs.getString("TAN"));
				}else{
					fmVO.setKin(String.valueOf(rs.getInt("TAN")));
					fmVO.setKinOld(String.valueOf(rs.getInt("TAN")));
				}
//				fmVO.setKin(String.valueOf(rs.getString("TAN")));
				fmVO.setBiKou2(rs.getString("BIKOU2").trim());	
				fmVO.setTan2(rs.getString("TAN2"));
				fmVO.setTan2Old(rs.getString("TAN2"));
				if(rs.getString("FUKSZINMKJ") != null)
					fmVO.setFukSziNmkj(rs.getString("FUKSZINMKJ").trim());  //�����ޖ���
				
				//��ڽ�̏ꍇ���݌Ƀe�[�u�����݌ɐ�
				if(fmVO.getBunCod().equals("0")){
					if(rs.getString("AZISUU") != null)
						fmVO.setFukSziSuu(rs.getString("AZISUU").trim());
				}
				//�����ނ̏ꍇ�������ލ݌Ƀe�[�u����蕛���ލ݌ɐ�
				else if (fmVO.getBunCod().equals("1")
						|| fmVO.getBunCod().equals("2")) {
					fmVO.setFukSziSuu(rs.getString("FUKSZISUU").trim());
				}
				else
					fmVO.setFukSziSuu("0");
				if(rs.getString("JUCZAN") != null)
					fmVO.setCyuzan(rs.getString("JUCZAN"));

				finded_Arr.add(fmVO);
			}

		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

		return (OrderHistoryVO[])finded_Arr.toArray(new OrderHistoryVO[finded_Arr.size()]);
	}

	/**
	 * ���������������̌�������������𐶐����ĕԂ��܂��B
	 * @param queryVO
	 * @return ��������������
	 */
	private String makeQueryCondition(OrderHistoryVO queryVO) {
		
		String query = "WHERE HAC02.DAIKAISKBCOD = '"+ queryVO.getDaiKaiSkbCod() + "'";		
		
		//��Ў��ʃR�[�h
		if(queryVO.getKaiSkbCodList().size() > 0){
			query += " AND HAC02.KAISKBCOD "
				+ QueryUtils.editArrayToQuery(queryVO.getKaiSkbCodList());
		}
		//�������I��
		if (queryVO.getSyrZmiSgn_H().equals("1")) { //���o��
			query += " AND HAC02.SYRZMISGN = '0'";
		} else if (queryVO.getSyrZmiSgn_H().equals("2")) { //�o�͍�
			query += " AND HAC02.SYRZMISGN = '1'";
		}
		//���ރR�[�h�I��
		if (queryVO.getBunCod_H().equals("1")) { //�v���X
			query += " AND HAC02.BUNCOD = '0'";
		} else if (queryVO.getBunCod_H().equals("2")) { //������
			query += " AND HAC02.BUNCOD IN ('1','2')";
		}
		//���ɏ�
		if (queryVO.getNyuko_H().equals("1")) { //������
			query += " AND HAC02.HACSUU > HAC02.NYOSUU AND KNUSGN = ''";
		}
		//������R�[�h
		if (!queryVO.getHacCod_H().equals("")) {
			query += " AND HAC02.HACCOD = '" + queryVO.getHacCod_H() + "'";
		}
		//�L���ԍ�
		if (!queryVO.getKigBng_H().equals("")) {
			query += " AND HAC02.KIGBNG = '" + queryVO.getKigBng_H() + "'";
		}
		//�������ԍ�From&To
		if (!queryVO.getHacSyoBngTo_H().equals("")) {
			query += " AND HAC02.HACSYOBNG >= '"
				+ DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBngFrm_H())
				+ "'"
				+ " AND HAC02.HACSYOBNG <= '"
				+ DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBngTo_H())
				+ "'";
		} else if (!queryVO.getHacSyoBngFrm_H().equals("")) {
			query += " AND HAC02.HACSYOBNG = '"
				+ DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBngFrm_H())
				+ "'";
		}
		//������From&To
		if (!queryVO.getHacSyoDteTo_H().equals("")) {
			query += " AND HAC02.HACSYODTE >= "
				+ queryVO.getHacSyoDteFrm_H()
				+ " AND HAC02.HACSYODTE <= "
				+ queryVO.getHacSyoDteTo_H();
		} else if (!queryVO.getHacSyoDteFrm_H().equals("")) {
			query += " AND HAC02.HACSYODTE = " + queryVO.getHacSyoDteFrm_H();
		}
		//�[��From&To
		if (!queryVO.getNkiTo_H().equals("")) {
			query += " AND HAC02.NKI >= "
				+ queryVO.getNkiFrm_H()
				+ " AND HAC02.NKI <= "
				+ queryVO.getNkiTo_H();
		} else {
			if (!queryVO.getNkiFrm_H().equals("")) {
				query += " AND HAC02.NKI = " + queryVO.getNkiFrm_H();
			}
		}
		//�敪
		if (!queryVO.getKbn_H().equals("")) {
			query += " AND HAC02.SINKYUKBN = '" + queryVO.getKbn_H() + "'";
		}
		//���ɓ�From&To
		if (!queryVO.getNyoDteTo_H().equals("")) {
			query += " AND HAC02.NYODTE >= "
				+ queryVO.getNyoDteFrm_H()
				+ " AND HAC02.NYODTE <= "
				+ queryVO.getNyoDteTo_H();
		} else {
			if (!queryVO.getNyoDteFrm_H().equals("")) {
				query += " AND HAC02.NYODTE = " + queryVO.getNyoDteFrm_H();
			}
		}
		//���[�J�[����
		if(queryVO.getMkrBun().length > 0){
			query += " AND (HIN01.MKRBUN "
				+ QueryUtils.editArrayToQuery(queryVO.getMkrBun());
		}	
		if(queryVO.getMkrBun().length > 0){
			query += " OR HIN02.MKRBUN "
				+ QueryUtils.editArrayToQuery(queryVO.getMkrBun())
				+ ")";
		}	
		//������
		if (queryVO.getHbiDte_H() != 0) {
			query += " AND (HIN01.HBIDTE = " + queryVO.getHbiDte_H() + "";
			query += " OR HIN02.HBIDTE = " + queryVO.getHbiDte_H() + ")";
		}
		return query;
	}


//---------------------------------------------------------------------------------------------
	
	//���������e�[�u���X�V*******************************************************************

	public void updateHac02(OrderHistoryVO fmVO,int upddte,int updjkk,String syrzmisgn,String cykkbn)
		throws SQLException{
		String sql = "UPDATE FTBHAC02 SET NKI = ?,NHNMEIKJ = ?,SYRZMISGN = ?,HACSUU = ?,UPDKBN = ?,"
		+ "UPDDTE = ?,UPDJKK = ?,CMT = ?,CYKKBN = ?,SINKYUKBN = ?,NYOSUU= ?,KNUSGN= ?,NYODTE= ?," //2004.01.22 add ���ɐ��̒ǉ� 02.27 add ���[�T�C��
//		+ "TAN = ? ,RRKTBL = ? " //2005.09.15 ���z�A�����X�V�� add
		+ "TAN = ?,BIKOU2 = ?,TAN2 = ? "
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? AND HACSYODTE = ?"
					 + " AND HACSYOBNG = ? AND GYOBNG = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			int idx = 1;
			ps.setInt(idx++,fmVO.getNki());
			ps.setString(idx++,fmVO.getNhnMeiKj());
			ps.setString(idx++,syrzmisgn);
			ps.setString(idx++,fmVO.getHacSuu());
			ps.setString(idx++,"U");
			ps.setInt(idx++,upddte);
			ps.setInt(idx++,updjkk);
			ps.setString(idx++,fmVO.getCmt());
			ps.setString(idx++,cykkbn);
			ps.setString(idx++,fmVO.getSinKyuKbn()); //2003/07/18 add
			ps.setString(idx++,fmVO.getNyoSuu()); //2003/01/22 add
			ps.setString(idx++,fmVO.getKnuSgn()); //2004/02/27 add
			ps.setInt(idx++,Integer.parseInt(fmVO.getNyoDte())); //2004/08/03 add
			if(!fmVO.getKin().trim().equals(""))
				ps.setString(idx++,fmVO.getKin());  //2005/09/13 add
			else
				ps.setString(idx++,"0");
//			ps.setString(idx++,fmVO.getRrkTbl());   //2005/09/15 add
			ps.setString(idx++,fmVO.getBiKou2());
			if(!fmVO.getTan2().trim().equals(""))
				ps.setString(idx++,fmVO.getTan2());  //2008/03/07 add
			else
				ps.setString(idx++,"0");
			ps.setString(idx++,fmVO.getDaiKaiSkbCod());
			ps.setString(idx++,fmVO.getKaiSkbCod());
			ps.setString(idx++,fmVO.getKigBng());
			ps.setString(idx++,fmVO.getHacSyoDte());
			ps.setString(idx++,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(idx++,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(idx++,fmVO.getSyrSuu());
			ps.setString(idx++,fmVO.getSeqNo());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}

	}

	final static String HAC01 = "FTBHAC01";
	final static String HAC02 = "FTBHAC02";
	/**
	 * ���������e�[�u���E�������f�[�^�X�V�i�������M�p�A�����X�V���̍X�V�j
	 * @param partOfQuery
	 * @param rrkTal
	 * @param upddte
	 * @param updjkk
	 * @param HAC_TABLE
	 * @throws SQLException
	 */
	public void updateHac(String partOfQuery,String rrkTal,int upddte,int updjkk,String HAC_TABLE) throws SQLException{
		
		String sql = "UPDATE "+ HAC_TABLE +" SET UPDKBN = ?,UPDDTE = ?,UPDJKK = ?,RRKTBL = ? "
				   + " WHERE "+ partOfQuery;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			int idx = 1;
			ps.setString(idx++,"U");
			ps.setInt(idx++,upddte);
			ps.setInt(idx++,updjkk);
			ps.setString(idx++,rrkTal);

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}
	}


	//�������f�[�^�X�V*******************************************************************

	public void updateHac01(OrderHistoryVO fmVO,int upddte,int updjkk,String cykkbn)
		throws SQLException{
		String sql = "UPDATE FTBHAC01 SET NKI = ?,HACSUU = ?,NHNMEIKJ = ?,UPDKBN = ?,UPDDTE = ?,"
//					 + "UPDJKK = ?,DTAEXCFLG = ?,CYKKBN = ?,CMT = ?,SINKYUKBN = ?,TAN = ?,RRKTBL = ? "
					 + "UPDJKK = ?,DTAEXCFLG = ?,CYKKBN = ?,CMT = ?,SINKYUKBN = ?,TAN = ?,BIKOU2 = ?,TAN2 = ? "
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? "
					 + " AND KIGBNG = ? AND HACSYODTE = ? AND HACSYOBNG = ? AND GYOBNG = ?"
					 + " AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			int idx = 1;
			ps.setInt(idx++,fmVO.getNki());
			ps.setString(idx++,fmVO.getHacSuu());
			ps.setString(idx++,fmVO.getNhnMeiKj());
			ps.setString(idx++,"U");
			ps.setInt(idx++,upddte);
			ps.setInt(idx++,updjkk);
			if(!fmVO.getKnuSgn().trim().equals("1"))
				ps.setString(idx++,"0");
			else
				ps.setString(idx++,"1");
			ps.setString(idx++,cykkbn);
			ps.setString(idx++,fmVO.getCmt());
			ps.setString(idx++,fmVO.getSinKyuKbn());//2003/07/18 add
			if(!fmVO.getKin().trim().equals(""))
				ps.setString(idx++,fmVO.getKin());  //2005/09/13 add
			else
				ps.setString(idx++,"0");
//			ps.setString(idx++,fmVO.getRrkTbl());   //2005/09/15 add
			ps.setString(idx++,fmVO.getBiKou2());
			if(!fmVO.getTan2().trim().equals(""))
				ps.setString(idx++,fmVO.getTan2());  //2008/03/07 add
			else
				ps.setString(idx++,"0");
			ps.setString(idx++,fmVO.getDaiKaiSkbCod());
			ps.setString(idx++,fmVO.getKaiSkbCod());
			ps.setString(idx++,fmVO.getKigBng());
			ps.setString(idx++,fmVO.getHacSyoDte());
			ps.setString(idx++,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(idx++,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(idx++,fmVO.getSyrSuu());
			ps.setString(idx++,fmVO.getSeqNo());
			
			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}

//---------------------------------------------------------------------------------------------------
	final static int HIN01 = 1;
	final static int HIN02 = 2;
//**�i�ԑ��݃`�F�b�N******************************************************************

	public boolean hasKigBng(OrderHistoryVO fmVO,int sgn)throws SQLException{		
		if(fmVO.getKigBng_H().equals("")){
			return true;
		}
			
		String query = "";
		
		String sql = "SELECT KIGBNG ";
				if(sgn == HIN01)
					sql += " FROM FTBHIN01 ";
				else if(sgn == HIN02)
					sql += " FROM FTBHIN02 ";
					sql += " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					;
					
		//��Ў��ʃR�[�h
        for(int i=0; i<fmVO.getKaiSkbCodList().size(); i++){
          if(i == 0)
          	sql +=  " AND KAISKBCOD IN ('";
          sql += fmVO.getKaiSkbCodList().get(i).toString();
          if(i == fmVO.getKaiSkbCodList().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }

		sql		   += " AND UPDKBN <> 'D' ";
		//�܂��͏ȗ��L���Ō���
		query	= sql + " AND SRYKIG = '" + fmVO.getKigBng_H() + "'";

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				fmVO.setKigBng_H(rs.getString("KIGBNG").trim());//�������ꂽ�L���ԍ����Z�b�g���Ȃ���
				return true;
			}else{
				//�ȗ��i�ԂŌ������Č��ʂȂ���������A�L���ԍ��Ō������Ȃ���
				query	= sql + " AND KIGBNG = '" + fmVO.getKigBng_H() + "'";
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					return true;
				}else{
					return false;
				}
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			

	}
	


//**** �X�V�`�F�b�N *************************************************************************************
	
	public static final int NOT_MODIFIED = 1;
	public static final int MODIFIED = 2;
	public static final int NOT_EXIST = 3;
	public static final int LOGICAL_DELETE = 4;
	
	
	public int selectForUpdate(OrderHistoryVO fmVO)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBHAC02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND HACSYODTE = ? AND HACSYOBNG = ? "
					+ " AND GYOBNG = ? AND SYRSUU = ? AND SEQNO = ?";

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getHacSyoDte());
			ps.setString(4,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(5,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(6,fmVO.getSyrSuu());
			ps.setString(7,fmVO.getSeqNo());

			rs = ps.executeQuery();

			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");
						
				while(rs.next()){
				}
				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}

				if(updDte != fmVO.getUpdDte()|| updJkk != fmVO.getUpdJkk()){
					return MODIFIED;
				}	
				return NOT_MODIFIED;			
			}else{

				return NOT_EXIST;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}			
	}
	
	
	//�������f�[�^���݃`�F�b�N****************************************************************
	
	public boolean hasHac01(OrderHistoryVO fmVO)throws SQLException{
		
		String sql = "SELECT KIGBNG FROM FTBHAC01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND HACSYODTE = ?"
					+ " AND HACSYOBNG = ? AND SYRSUU = ? AND SEQNO = ? AND GYOBNG = ?";


		ResultSet rs = null;
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getHacSyoDte());
			ps.setString(4,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(5,fmVO.getSyrSuu());
			ps.setString(6,fmVO.getSeqNo());
			ps.setString(7,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));

			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		
	}
	
	/**
	 * �����ލ݌Ƀe�[�u��(FTBHIN12)�������\�b�h
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,bunCod,fukSziCod,smpKbn
	 * @throws java.sql.SQLException 
	 */
	public ArrayList findHin12(OrderHistoryVO fmVO,String smpKbn)throws SQLException{

		String sql = "SELECT BUNCOD,FUKSZICOD FROM FTBHIN12"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?"
			     	+ " AND SMPKBN = ?";
			     	
		ArrayList ret = new ArrayList();
		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getKigBng());
			ps.setString(4,smpKbn);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				String[] str = {rs.getString("BUNCOD").trim(),rs.getString("FUKSZICOD").trim()};
				ret.add(str);
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}		
		return ret;
	}
	
}
