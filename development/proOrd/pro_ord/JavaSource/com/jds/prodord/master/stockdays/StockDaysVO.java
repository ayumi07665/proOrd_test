/**
* StockDaysVO  �݌ɓ����}�X�^�[�����e�i���X�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/06/09
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.stockdays;

import java.util.*;
public class StockDaysVO {

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String rank = new String();
	private String ketCod = new String();
	private String outStockDays = new String();

	private int updDte;
	private int updJkk;
	

    //���[�U�[�̑�\��Ў��ʃR�[�h
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }


    //���[�U�[�̉�Ў��ʃR�[�h
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

    
//-----  ���̓G���A  -----------------------------------------

//***  ��Ў��ʃR�[�h��get/set  ***
    public String getKaiSkbCod() {
        return this.kaiSkbCod;
    }
    public void setKaiSkbCod(String kaiSkbCod) {
        this.kaiSkbCod = kaiSkbCod;
    }

//***  �����N��get/set  ***
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

//***  �`�ԃR�[�h��get/set  ***
	public String getKetCod() {
		return ketCod;
	}
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}


//***  �݌ɓ�����get/set  ***
	public String getOutStockDays() {
		return outStockDays;
	}
	public void setOutStockDays(String outStockDays) {
		this.outStockDays = outStockDays;
	}


//***  �X�V���t��get/set  ***
	public int getUpdDte() {
		return updDte;
	}
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}

//***  �X�V���Ԃ�get/set  ***
	public int getUpdJkk() {
		return updJkk;
	}
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}

}
