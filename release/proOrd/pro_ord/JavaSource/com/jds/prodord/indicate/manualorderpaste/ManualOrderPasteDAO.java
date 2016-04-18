package com.jds.prodord.indicate.manualorderpaste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.order.prsorder.PrsOrderVO;
/**
 * ManualOrderPasteAction  �}�j���A�������w���ꊇ�\�t�f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�i�ԃ}�X�^�[�iFTBHIN01�j�A�����e�[�u���iVTBCNL09�j�A��Ѓ}�X�^�[�iFTBKAI01�j�A�ɃA�N�Z�X����N���X�B</dd>
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

public class ManualOrderPasteDAO extends CommonDAO{

	
	public ManualOrderPasteDAO() throws SQLException{
    	super();
	}

	
//**�i�ԑ��݃`�F�b�N******************************************************************

	public PrsOrderVO getHinData(String daiKaiSkbCod, String kigBng, String tblNm)throws SQLException{	

		PreparedStatement ps = null;
		ResultSet rs = null;
		PrsOrderVO fmVO = new PrsOrderVO();

		String query = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
					+ " ,H.ZEIKMITKA FROM "+tblNm+" H" 
					+ " LEFT OUTER JOIN FTBMST06 K ON"
					+ " H.KETCOD = K.KETCOD"
					+ " WHERE H.DAIKAISKBCOD = ? "
					+ " AND K.DAIKAISKBCOD = ? "
					+ " AND H.KIGBNG = ? "
					+ " AND H.UPDKBN <> 'D' ";

		try{
			ps = conn.prepareStatement(query);
			ps.setString(1, daiKaiSkbCod);
			ps.setString(2, daiKaiSkbCod);
			ps.setString(3, kigBng);
			rs = ps.executeQuery();

			if(rs.next()){
				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setHjiHnb(rs.getString("HJIHNB").trim());
				fmVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"�@"));
				fmVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				fmVO.setKetCod(rs.getString("KETCOD").trim());
				fmVO.setHbiDte(rs.getString("HBIDTE").trim());
				fmVO.setTomRak(rs.getString("TOMRAK").trim());
				fmVO.setZikTik(DataFormatUtils.setFormatLong(rs.getLong("ZEIKMITKA"))); 

				if(!rs.getString("JYTPRSMKR").trim().equals("")){//����v���X���[�J�[�������
					fmVO.setPrsMkrCod(rs.getString("JYTPRSMKR").trim());//����v���X���[�J�[���Z�b�g
				}else{//�Ȃ����
					fmVO.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());//�v���X���[�J�[�R�[�h���Z�b�g
				}
				fmVO.setNhnCod(fmVO.getPrsMkrCod());
				
				fmVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				fmVO.setSetSuu(rs.getString("SETSUU").trim());
				fmVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"�@"));
				
				if(tblNm.equals("FTBHIN01"))
					fmVO.setExsitHin01(true);
				
			}else{
				if(tblNm.equals("FTBHIN02"))
					return null;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		
		return fmVO;

	}

	//�����於�̎擾*******************************************************************
	public String getHacNm(String daiKaiSkbCod,String sirHacCod)throws SQLException{
		String hacNm ="";
		String sql = "SELECT SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,sirHacCod);

			rs = ps.executeQuery();
			
			if(rs.next()){
				hacNm = StringUtils.removeSuffix(rs.getString("HACNM"),"�@");
			}
				
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		return hacNm;
	}
	

	/**
	 * �����ރ}�X�^�[�����i�����ރR�[�h�v���_�E�����擾�j
	 * @param daiKaiSkbCod
	 * @param kaiSkbCod
	 */
	public void getFukSziData(PrsOrderVO fmVO)throws SQLException{
		String hacNm ="";


		String sql = "SELECT FUKSZIHACCOD,FUKSZINMKJ,BUNCOD FROM FTBFUK01 "
							+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? "
							+ " AND FUKSZICOD = ?";

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
					
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getFukSziCod());
					
			rs = ps.executeQuery();
					
			if(rs.next()){
				fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@"));
				fmVO.setBunCod(rs.getString("BUNCOD").trim());
				fmVO.setExsitFuk01(true);
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
				fmVO.setBasedRowFlg("1"); 
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

}

