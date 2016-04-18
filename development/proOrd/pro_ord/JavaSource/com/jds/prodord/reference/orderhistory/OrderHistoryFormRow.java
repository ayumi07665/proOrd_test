/**
* OrderHistoryFormRow  ���������Ɖ��ʃt�H�[���s�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ڂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
* 		2004/01/22 �i�m�h�h�j�X
* 			�E�����敪�ǉ��ɂ��C��
* 		2005/05/25�i�m�h�h�j�g�c
* 			�E�ꏊ�R�[�h�ǉ�
* 		2005/09/01�i�m�h�h�j�g�c
* 			�E���z�̒ǉ�
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
*		2008/03/07�i�m�h�h�j�c��
* 			�E�P���̒ǉ�
*		2008/06/02�i�m�h�h�j�c��
* 			�E�u�`�o�Ё@�P���E���z�̏C��
* 
*/

package com.jds.prodord.reference.orderhistory;



public class OrderHistoryFormRow implements java.io.Serializable{
	
	public Object clone(){
		OrderHistoryFormRow row = new OrderHistoryFormRow();
		row.setDaiKaiSkbCod(new String(this.daiKaiSkbCod));
		row.setKaiSkbCod(new String(this.kaiSkbCod));
		row.setHacCod(new String(this.hacCod));
		row.setSinKyuKbn(new String(this.sinKyuKbn));
		row.setKigBng(new String(this.kigBng));
		row.setHbiDte(new String(this.hbiDte));
		row.setCheck_Mid(this.check_Mid);
		row.setHacSyoBng(new String(this.hacSyoBng));
		row.setHacSyoDte(new String(this.hacSyoDte));
		row.setSyrZmiSgn(new String(this.syrZmiSgn));
		row.setPrsFukSziCod(new String(this.prsFukSziCod));
		row.setFukSzisuu(new String(this.fukSzisuu));
		row.setHacSuu(new String(this.hacSuu));
		row.setHacSuuOld(new String(this.hacSuuOld));
		row.setNyoSuu(new String(this.nyoSuu));
		row.setNkiYear(new String(this.nkiYear));
		row.setNkiMonth(new String(this.nkiMonth));
		row.setNkiDay(new String(this.nkiDay));
		row.setGyoBng(new String(this.gyoBng));
		row.setNhnMeiKj(new String(this.nhnMeiKj));
		row.setBreakflg(this.breakflg);
		row.setHjiHnb(new String(this.hjiHnb));
		row.setBunCod(new String(this.bunCod));
		row.setCmt(new String(this.cmt));
		row.setNyoDte(new String(this.nyoDte));
		row.setCykkbn(new String(this.Cykkbn));	//2004.01.22 add
		row.setBshCod(new String(this.bshCod));	//2005.05.25 add
		row.setKin(new String(this.kin));          //2005.09.13 add
		row.setBiKou2(new String(this.biKou2));          //2007.12.25 add
		row.setTan2(new String(this.tan2));          //2008.03.07 add
		row.setTan2Old(new String(this.tan2Old));
		row.setKinOld(new String(this.kinOld));
				
		return row;
	}
	
	
    /**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod = "";
    
    /**
     * �ꏊ�R�[�h
     */
    private String bshCod = "";
	
    
    // --------------------------------------------------- Instance Variables    


    /**
     * ������
     */
    private String hacCod = "";
    
    /**
     * �敪
     */
    private String sinKyuKbn = "";
    

    /**
     * �L���ԍ�
     */
    private String kigBng = "";
    

    /**
     * ������
     */
    private String hbiDte = "";

    /**
     * �`�F�b�N�{�b�N�X
     */
    private boolean check_Mid = false;
    

    /**
     * �������ԍ�
     */
    private String hacSyoBng = "";
    

    /**
     * ������
     */
    private String hacSyoDte = "";
    

    /**
     * �o�͍�
     */
    private String syrZmiSgn = "";
    

    /**
     * ���ރR�[�h
     */
    private String prsFukSziCod = "";
    

    /**
     * �݌ɐ�
     */
    private String fukSzisuu = "";
   
    /**
     * ������
     */
    private String hacSuu = "";
    private String hacSuuOld = "";
    

    /**
     * ���ɐ�
     */
    private String nyoSuu = "";
    

    /**
     * �[��
     */
   
    private String nkiYear = "";
    private String nkiMonth = "";
    private String nkiDay = "";
    

    /**
     * �����ԍ�
     */
    private String gyoBng = "";
    

    /**
     * �[�i��
     */
    private String nhnMeiKj = "";
    
    
    /** ������E�敪�E�L���ԍ��E�������u���[�N�t���O
	 */
    private boolean breakflg = false;
    
    
    /**
     * �\���i��
     */
    private String hjiHnb = "";
    
    /**
     * ���ރR�[�h
     */
    private String bunCod = "";

    /**
     * �R�����g
     */
    private String cmt = "";
    
    /**
     * ���ɓ�
     */
    private String nyoDte = "";  
    
//2004.01.22 add
	/**
	 * �����敪
	 */
	private String Cykkbn = "";  
    
//2005.09.13 add
	/**
	 * ���z
	 */
	private String kin = "";
	private String kinOld = "";
	
//	2007.12.20 add
	  /**
	   * ���l�Q
	   */
	  private String biKou2 = "";

//	2008.03.07 add
	  /**
	   * �P��
	   */
	  private String tan2 = "";
	  private String tan2Old = "";
	
	//---------------------------------------------------------------------------

    /**
     * ��\��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param daiKaiSkbCod ��\��Ў��ʃR�[�h
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }
    /**
     * ��\��Ў��ʃR�[�h�̎擾
     * 
     * @return ��\��Ў��ʃR�[�h
     */
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    
    /**
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod 
     */
    public void setKaiSkbCod(String kaiSkbCod) {
        this.kaiSkbCod = kaiSkbCod;
    }
    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public String getKaiSkbCod() {
        return this.kaiSkbCod;
    }


    // ----------------------------------------------------------- Properties


    /**
     * ������̃Z�b�g
     * 
     * @param hacCod ������
     */
    public void setHacCod(String hacCod) {
        this.hacCod = hacCod;
    }
    /**
     * ������̎擾
     * 
     * @return ������
     */
    public String getHacCod() {
        return this.hacCod;
    }


    /**
     * �敪�̃Z�b�g
     * 
     * @param sinKyuKbn �敪
     */
    public void setSinKyuKbn(String sinKyuKbn) {
        this.sinKyuKbn = sinKyuKbn;
    }
    /**
     * �敪�̎擾
     * 
     * @return �敪
     */
    public String getSinKyuKbn() {
        return this.sinKyuKbn;
    }


    /**
     * �L���ԍ��̃Z�b�g
     * 
     * @param kigBng �L���ԍ�
     */
    public void setKigBng(String kigBng) {
        this.kigBng = kigBng;
    }
    /**
     * �L���ԍ��̎擾
     * 
     * @return �L���ԍ�
     */
    public String getKigBng() {
        return this.kigBng;
    }


    /**
     * �������̃Z�b�g
     * 
     * @param hbiDte ������
     */
    public void setHbiDte(String hbiDte) {
        this.hbiDte = hbiDte;
    }
    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getHbiDte() {
        return this.hbiDte;
    }


    /**
     * �`�F�b�N�{�b�N�X�̃Z�b�g
     * 
     * @param checkMid �`�F�b�N�{�b�N�X
     */
    public void setCheck_Mid(boolean check_Mid) {
        this.check_Mid = check_Mid;
    }
    /**
     * �`�F�b�N�{�b�N�X�̎擾
     * 
     * @return �`�F�b�N�{�b�N�X
     */
    public boolean getCheck_Mid() {
        return this.check_Mid;
    }


    /**
     * �������ԍ��̃Z�b�g
     * 
     * @param hacSyoBng �������ԍ�
     */
    public void setHacSyoBng(String hacSyoBng) {
        this.hacSyoBng = hacSyoBng;
    }
    /**
     * �������ԍ��̎擾
     * 
     * @return �������ԍ�
     */
    public String getHacSyoBng() {
        return this.hacSyoBng;
    }


    /**
     * �������̃Z�b�g
     * 
     * @param hacSyoDte ������
     */
    public void setHacSyoDte(String hacSyoDte) {
        this.hacSyoDte = hacSyoDte;
    }
    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getHacSyoDte() {
        return this.hacSyoDte;
    }


    /**
     * �o�͍ς̃Z�b�g
     * 
     * @param syrZmiSgn �o�͍�
     */
    public void setSyrZmiSgn(String syrZmiSgn) {
        this.syrZmiSgn = syrZmiSgn;
    }
    /**
     * �o�͍ς̎擾
     * 
     * @return �o�͍�
     */
    public String getSyrZmiSgn() {
        return this.syrZmiSgn;
    }


    /**
     * ���ރR�[�h�̃Z�b�g
     * 
     * @param prsFukSziCod ���ރR�[�h
     */
    public void setPrsFukSziCod(String prsFukSziCod) {
        this.prsFukSziCod = prsFukSziCod;
    }
    /**
     * ���ރR�[�h�̎擾
     * 
     * @return ���ރR�[�h
     */
    public String getPrsFukSziCod() {
        return this.prsFukSziCod;
    }


    /**
     * �݌ɐ��̃Z�b�g
     * 
     * @param fukSzisuu �݌ɐ�
     */
    public void setFukSzisuu(String fukSzisuu) {
        this.fukSzisuu = fukSzisuu;
    }
     /**
     * �݌ɐ��̎擾
     * 
     * @return �݌ɐ�
     */
    public String getFukSzisuu() {
        return this.fukSzisuu;
    }

    /**
     * �������̃Z�b�g
     * 
     * @param hacSuu ������
     */
    public void setHacSuu(String hacSuu) {
        this.hacSuu = hacSuu;
    }
    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getHacSuu() {
        return this.hacSuu;
    }

	/**
     * ������Old�̃Z�b�g
     * 
     * @param hacSuuOld ������Old
     */
    public void setHacSuuOld(String hacSuuOld) {
        this.hacSuuOld = hacSuuOld;
    }
    /**
     * ������Old�̎擾
     * 
     * @return ������Old
     */
    public String getHacSuuOld() {
        return this.hacSuuOld;
    }

    /**
     * ���ɐ��̃Z�b�g
     * 
     * @param nyoSuu ���ɐ�
     */
    public void setNyoSuu(String nyoSuu) {
        this.nyoSuu = nyoSuu;
    }
    /**
     * ���ɐ��̎擾
     * 
     * @return ���ɐ�
     */
    public String getNyoSuu() {
        return this.nyoSuu;
    }


//** �[��  **//
    /**
     * �[��(�N)�̃Z�b�g
     * 
     * @param nkiYear �[��(�N)
     */
    public void setNkiYear(String nkiYear) {
        this.nkiYear = nkiYear;
    }
    /**
     * �[��(�N)�̎擾
     * 
     * @return �[��(�N)
     */
    public String getNkiYear() {
        return this.nkiYear;
    }
    /**
     * �[��(��)�̃Z�b�g
     * 
     * @param nkiMonth �[��(��)
     */
    public void setNkiMonth(String nkiMonth) {
        this.nkiMonth = nkiMonth;
    }
    /**
     * �[��(��)�̎擾
     * 
     * @return �[��(��)
     */
    public String getNkiMonth() {
        return this.nkiMonth;
    }
    /**
     * �[��(��)�̃Z�b�g
     * 
     * @param nkiDay �[��(��)
     */
    public void setNkiDay(String nkiDay) {
        this.nkiDay = nkiDay;
    }

    /**
     * �[��(��)�̎擾
     * 
     * @return �[��(��)
     */
    public String getNkiDay() {
        return this.nkiDay;
    }




    /**
     * �����ԍ��̃Z�b�g
     * 
     * @param hacBng �����ԍ�
     */
    public void setGyoBng(String gyoBng) {
        this.gyoBng = gyoBng;
    }
    /**
     * �����ԍ��̎擾
     * 
     * @return �����ԍ�
     */
    public String getGyoBng() {
        return this.gyoBng;
    }



    /**
     * �[�i���̃Z�b�g
     * 
     * @param nhnMeiKj �[�i��
     */
    public void setNhnMeiKj(String nhnMeiKj) {
        this.nhnMeiKj = nhnMeiKj;
    }
    /**
     * �[�i���̎擾
     * 
     * @return �[�i��
     */
    public String getNhnMeiKj() {
        return this.nhnMeiKj;
    }

	/** ������E�敪�E�L���ԍ��E�������u���[�N�t���O
	 */
    public boolean getBreakflg(){
		return breakflg;
	}	
	public void setBreakflg(boolean s){
		breakflg = s;
	}	
	/** �`�F�b�N�{�b�N�X���������\�b�h
	 */
	public void clear_check(){
		this.setCheck_Mid(false);
    }


    /**
     * �\���i��
     * 
     * @param hjiHnb �\���i��
     */
    public void setHjiHnb(String hjiHnb) {
        this.hjiHnb = hjiHnb;
    }
    /**
     * �\���i�Ԃ̎擾
     * 
     * @return �\���i��
     */
    public String getHjiHnb() {
        return this.hjiHnb;
    }
    
    /**
     * ���ރR�[�h
     * 
     * @param bunCod ���ރR�[�h
     */
    public void setBunCod(String bunCod) {
        this.bunCod = bunCod;
    }
    /**
     * ���ރR�[�h�̎擾
     * 
     * @return ���ރR�[�h
     */
    public String getBunCod() {
        return this.bunCod;
    }

    /**
     * ���ɓ�
     * 
     * @param nyoDte ���ɓ�
     */
    public void setNyoDte(String nyoDte) {
        this.nyoDte = nyoDte;
    }
    /**
     * ���ɓ��̎擾
     * 
     * @return ���ɓ�
     */
    public String getNyoDte() {
        return this.nyoDte;
    }
    
    /**
     * �R�����g
     * 
     * @param cmt �R�����g
     */
    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
    /**
     * �R�����g�̎擾
     * 
     * @return �R�����g
     */
    public String getCmt() {
        return this.cmt;
    }
    
	/**
	 * ���l�Q
	 * 
	 * @param biKou2 ���l�Q
	 */
	public void setBiKou2(String biKou2) {
		this.biKou2 = biKou2;
	}
	/**
	 * ���l�Q�̎擾
	 * 
	 * @return ���l�Q
	 */
	public String getBiKou2() {
		return this.biKou2;
	}
    
//2004.01.22 add
	/**
 	* @param Cykkbn �����敪
 	*/
	public void setCykkbn(String Cykkbn) {
		this.Cykkbn = Cykkbn;
	}

	/**
	* @return  �����敪
	*/
	public String getCykkbn() {
		return this.Cykkbn;
	}
	
//2005.05.25 add
	/**
	 * �ꏊ�R�[�h�̎擾
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * �ꏊ�R�[�h�̃Z�b�g
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}

//2005.09.13 add
	/**
	 * ���z�̎擾
	 * @return
	 */
	public String getKin() {
		return kin;
	}
	
	/**
	 * ���z�̐ݒ�
	 * @param string
	 */
	public void setKin(String string) {
		kin = string;
	}

//	2008.03.07 add
	  /**
	   * �P���̎擾
	   * @return
	   */
	  public String getTan2() {
		  return tan2;
	  }
	
	  /**
	   *�P���̐ݒ�
	   * @param string
	   */
	  public void setTan2(String string) {
		  tan2 = string;
	  }

	/**
	 * �P��Old�̎擾
	 * @return
	 */
	public String getTan2Old() {
		return tan2Old;
	}
	
	/**
	 *�P��Old�̐ݒ�
	 * @param string
	 */
	public void setTan2Old(String string) {
		tan2Old = string;
	}

	/**
	 * ���zOld�̎擾
	 * @return
	 */
	public String getKinOld() {
		return kinOld;
	}
	
	/**	
	 *���zOld�̐ݒ�
	 * @param string
	 */
	public void setKinOld(String string) {
		kinOld = string;
	}

}
