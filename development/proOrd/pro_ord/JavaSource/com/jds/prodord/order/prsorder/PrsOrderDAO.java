/**
* PrsOrderDAO  �v���X�E�����ޔ�����ʃf�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���������e�[�u��(FTBHAC02)�A�������f�[�^(FTBHAC01)�A������}�X�^�[(FTBMST03)�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*		2003/05/01 �i�m�h�h�j�g�c �h�q
* 			�E�u�����w���v�u�`�[���s�v�̒ǉ��B
*		2003/05/16 �i�m�h�h�j���c �Ĕ�
* 			�E�����摶�݃`�F�b�N�ɔ����於�̂̎擾�ǉ��B
* 		2004/04/02  (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		2005/05/19 �i�m�h�h�j�g�c
*			�E�������ꊇ�o�͑Ή��i���������e�[�u���E�������f�[�^��'USRID'�o�^�j
*		2005/08/26 �i�m�h�h�j�g�c
* 			�E���������ҏW���@�ύX�i���������e�[�u���E�������f�[�^��'RRKTBL,'TAN'�o�^�j
* 		2005/09/14�i�m�h�h�j�g�c
* 			�E�����ޔ�����ʁA�����ރR�[�h���v���_�E�����ڂɕύX�iVAP�БΉ��j
*/

package com.jds.prodord.order.prsorder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.LabelValueBean;
import com.jds.prodord.common.StringUtils;

public class PrsOrderDAO extends CommonDAO{

	
	public PrsOrderDAO() throws SQLException{
    	super();
	}
	
	//���������e�[�u���o�^*******************************************************************
	
	public void insertHac02(
		PrsOrderVO fmVO,
		int upddte,
		int updjkk,
		String syrsuu,
		String seqno,
		String syrzmisgn,
		String cykkbn,
		String syrkbn)
		throws SQLException {
		String sql = "INSERT INTO FTBHAC02 (DAIKAISKBCOD,KAISKBCOD,HACSYODTE"
					 + ",HACSYOBNG,SYRSUU,SEQNO,HACCOD,SYRZMISGN,SINKYUKBN,GYOBNG,KIGBNG,TITKJ"
					 + ",BUNCOD,PRSFUKSZICOD,SETSUU,HACSUU,NYOSUU,NKI,NHNMEIKJ,CYKKBN"
					 + ",UPDKBN,UPDDTE,UPDJKK,CMT,FUKZAISUU,USRID,RRKTBL,TAN,BIKOU2) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?)";
					 
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
				
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			
			ps.setInt(3,upddte);
			ps.setString(4,"");
			ps.setString(5,syrsuu);
			ps.setString(6,seqno);
			ps.setString(7,fmVO.getPrsMkrCod());
			ps.setString(8,syrzmisgn);
			ps.setString(9,fmVO.getKbn());
			ps.setString(10,"");
			ps.setString(11,fmVO.getKigBng());
			ps.setString(12,fmVO.getTitKj());
			if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
				ps.setString(13,"0");
				ps.setString(14,"");
			}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
				ps.setString(13,fmVO.getBunCod());  //�{���̕��ރR�[�h��o�^
				ps.setString(14,fmVO.getFukSziCod());
			}
			ps.setString(15,fmVO.getSetSuu());
			ps.setString(16,fmVO.getPrsHacSuu());
			ps.setString(17,"0");
			ps.setInt(18,fmVO.getPrsNki());
			ps.setString(19,fmVO.getNhnMei());
			ps.setString(20,cykkbn);
	
			ps.setString(21,"A");
			ps.setInt(22,upddte);
			ps.setInt(23,updjkk);
			ps.setString(24,fmVO.getComment());
			ps.setString(25,fmVO.getFukSziSuu());//2003/07/28 add
			ps.setString(26,fmVO.getUsrId());//2005/05/19 add
			ps.setString(27,"");	//2005/08/29 add
			ps.setString(28,"0");
			ps.setString(29,fmVO.getBiKou2());
			
			ps.executeUpdate();
			
		}finally{
			if(ps != null)
				ps.close();
		}			
	}
	

	
	public static String NOT_EXIST = "NOT_EXIST";
	//�����摶�݃`�F�b�N****************************************************************
	
	public void hasHacSaki(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT SIRHACCOD, SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;
		
		try{
			ps = conn.prepareStatement(sql);
//System.out.println("�����摶�݃`�F�b�N");
//System.out.println("��\��� : "+fmVO.getDaiKaiSkbCod());	
//System.out.println("������ : "+fmVO.getPrsMkrCod());		
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getPrsMkrCod());

			rs = ps.executeQuery();
			
			if(rs.next()){
				fmVO.setHacNm(StringUtils.removeSuffix(rs.getString("HACNM"),"�@"));
			}else{
				fmVO.setHacNm(NOT_EXIST);
			}			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}


	/**
	 * �����ރ}�X�^�[�����i�����ރR�[�h�v���_�E�����擾�j
	 * @param daiKaiSkbCod
	 * @param kaiSkbCod
	 */
	public ArrayList getFukSziList(String daiKaiSkbCod, String kaiSkbCod) 
		throws SQLException {
		
		String query = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList fukSziList = new ArrayList();

		try{
			query = "SELECT FUKSZICOD,FUKSZINMKJ FROM FTBFUK01 " +
					" WHERE DAIKAISKBCOD = ? "+
					" AND KAISKBCOD = ? " +
					" ORDER BY FUKSZICOD";
					
			ps = conn.prepareStatement(query);
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			rs = ps.executeQuery();

			while(rs.next()){
				//�����ވꗗ�i�����ރR�[�h�E�����ޖ��́j�̃Z�b�g
				String[] fukSzi = new String[2];
				fukSzi[0] = rs.getString("FUKSZICOD");
				fukSzi[1] = StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@");
				fukSziList.add(new LabelValueBean(fukSzi[0]+" "+fukSzi[1],fukSzi[0]));
			}
		}finally{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}

		return fukSziList;

	}
}

