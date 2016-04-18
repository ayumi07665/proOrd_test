package com.jds.prodord.common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DataFormatUtils {

	private DataFormatUtils(){
	}



/**
 *  �R�����ɃJ���}�����郁�\�b�h�ł�
 *  @param   _str  �t�H�[�}�b�g�O�̃f�[�^
 *  @return        �R����؂�t�H�[�}�b�g�ς݂̃f�[�^
 */
	public static String setFormatString(String _str)
	{
	    String s;
	    long l;
  	  DecimalFormat _format;

   	 int idx = 1;
         if(_str == null){
     		 return " ";
    	 }else if(_str.equals("")){
   		   return " ";
   	 }else{
  		l = 0L;
  		s = "";
	    	if((idx = _str.indexOf(".")) == -1){
       			 l = Long.parseLong(_str);
        		_format = new DecimalFormat("###,###,###,###,##0");
    			s = _format.format(l);
      		}else{
        		String head = _str.substring(0,idx);
        		String tail = _str.substring(idx);
     		   	l = Long.parseLong(head);
      			_format = new DecimalFormat("###,###,###,###,##0");
     			s = _format.format(l) + tail;
  	        }
 		return s;
          }
	}

/**
 *  �R�����ɃJ���}�����郁�\�b�h�ł�
 *  @param   _l    �t�H�[�}�b�g�O�̃f�[�^
 *  @return        �R����؂�t�H�[�}�b�g�ς݂̃f�[�^
 */
	public static String setFormatLong(long _l)
	{
	   	String s;
    		long l;
    		DecimalFormat _format;

		l = 0L;
		s = "";

		_format = new DecimalFormat("###,###,###,###,##0");
		s = _format.format(_l);
		return s;
	}

/**
 *  �����_�̃t�H�[�}�b�g�����郁�\�b�h�ł�
 *  @param   _str  �t�H�[�}�b�g�O�̃f�[�^
 *  @return        �����_��P�ʂ܂Ńt�H�[�}�b�g�ς݂̃f�[�^
 */
	public static String setFormatfloat(float _f)
	{
		return String.valueOf(_f).substring(0,String.valueOf(_f).indexOf(".")+2);
	}

/**
 *  ���t�̃t�H�[�}�b�g�����郁�\�b�h�ł�
 *  @param   _str  �t�H�[�}�b�g�O�̃f�[�^
 *  @return        ���t�t�H�[�}�b�g�ς݂̃f�[�^
 */

	public static String setFormatYYMMDD(String s){
		
		if(s == null){
     		 return "";
    	}else if(s.equals("")){
   		   return "";
		}
		s = StringUtils.lpad(s,6,"0");
		return s.substring(0,2) + "/" + s.substring(2,4) + "/" + s.substring(4,6);
	}
	
/**
 *  "+"�܂���" "�����[�ɂ��Ă����ꍇ�A�������菜�����\�b�h�ł�
 *  @param   str  �t�H�[�}�b�g�O�̃f�[�^
 *  @return        ���[����"+"�܂���" "����菜�����f�[�^
 */
	public static long parseLongUnmark(String str){
  	  if(str.substring(0,1).equals(" ") ||
   	    str.substring(0,1).equals("+")){
  	    str = str.substring(1);
  	  }
  	  return Long.parseLong(str);
 	}

/**
 *  �ȗ��L������L���ԍ����쐬���郁�\�b�h�ł�
 *  @param   str  �t�H�[�}�b�g�O�̃f�[�^
 *  @return  �L���ԍ�
 */
	public static String mk_srykig(String kigBng){
		if(kigBng.indexOf(".") != -1){
			String syoryakuKigo = "";
			String put_zero = "";
			String head = "";
			String tail = "";
			int i = 0;
			for(i=0; i<=13-kigBng.length(); i++){
				put_zero = put_zero + "0";
			}
			head = kigBng.substring(0,kigBng.indexOf("."));
			tail = kigBng.substring(kigBng.indexOf(".")+1);
			head = head.concat(put_zero);
			syoryakuKigo = head.concat(tail);
			return syoryakuKigo;
		}else{
			return kigBng;
		}
	}
	
/**
 *  �L�����̏ȗ������ĕԂ�
 *  @param   ArrayList arr  �t�H�[�}�b�g�O�̃f�[�^
 *  @return  �L���ԍ�arr
 */
	public static ArrayList supplement_kigBng(ArrayList arr){
		ArrayList returnArr = new ArrayList();
		String kigBng = "";
		String head = "";
		String tail = "";
		for(int i = 0; i < arr.size(); i++){
			kigBng = arr.get(i)+"";
			if(kigBng.indexOf(".") != -1){
				if(kigBng.indexOf(".") == 0){
					tail = kigBng.substring(kigBng.indexOf(".")+1);
					kigBng = head.concat(".").concat(tail);
				}else
					head = kigBng.substring(0,kigBng.indexOf("."));
			}
			returnArr.add(kigBng);
		}
		return returnArr;
	}

	/**
	 * �ȗ��i�Ԃ̕ҏW�i�P�i�Ԃ��j<br>
	 * getKigHeader(String kigBng, String header)�ƕ��p���Ďg�p���܂��B<br>
	 * @param kigBng �L���ԍ�
	 * @param header �L����
	 * @return hinBan_r  �ϊ���̋L���ԍ�
	 * @throws FinderException
	 */
	public static String editKigBng(String kigBng, String header)  {

		String hinBan_r = "";		

		//�����ް����ȗ��i�Ԃ̎�
		if(kigBng.startsWith(".")){
				
			//�L���ԍ�+�����ް�
			hinBan_r = header + kigBng;
		
		}else{
			//�����ް����L���ԍ��t�i�Ԃ̎�
			hinBan_r = kigBng;
			
		}
		
		return mk_srykig(hinBan_r);	
		
	}


	/**
	 * �ȗ��i�Ԃ̕ҏW���\�b�h(editHinBan(String,String))�ɓn���L�����̕ҏW���s���ĕԂ��܂��B
	 * @param kigBng �L���ԍ�
	 * @param header �ۑ����Ă������L����
	 * @return �L����
	 */
	public static String getKigHeader(String kigBng, String header) {

		//"."���܂܂Ȃ����͂��̂܂ܕԂ�
		if(kigBng.indexOf(".") < 0)
			return header;
		//"."����n�܂鎞�͂��̂܂ܕԂ�
		if(kigBng.startsWith("."))
			return header;
		else{
		//�����ް����L�����t�̎��͋L�������擾����
			int i = kigBng.indexOf(".",0);
			header = kigBng.substring(0,i);
			return header;
		}
	}
	

	public static final String KYUHU = "����";
	public static final String SINPU = "�V��";
	public static final String SAMPLE = "�T���v��";
	public static final String DEMOBAN = "�f����";
	public static final String TOKHAN = "����";	
	public static final String SONOTA = "���̑�";
	public static final String HICATALOG	= "���۸�";
/**
 *  �V�������敪�̕\���������Ԃ� 
 *  @param   sgn  �敪(0�`4)
 *  @return  �敪�̕\��������
 * */
	public static String getKbnString(String sgn){
		String kbn = "";
		try{
			int _sgn = Integer.parseInt(sgn);
			switch(_sgn){
				case 0:
					kbn = KYUHU;
					break;
				case 1:
					kbn = SINPU;
					break;
				case 2:
					kbn = SAMPLE;
					break;
				case 3:
					kbn = DEMOBAN;
					break;
				case 4:
					kbn = TOKHAN;
					break;
				case 5:  //2004.03.08 add
					kbn = SONOTA;
					break;
				case 6:  //2005.06.24 add
					kbn = HICATALOG;
					break;
				default:
					kbn = sgn;
			}
		}catch(NumberFormatException e){
			return sgn;
		}
		return kbn;
	}
	
/**
 *  �V�������敪�̃R�[�h��Ԃ� 
 *  @param   sgn  �敪(�����E�V���E�T���v���E�f���ՁE����)
 *  @return  �敪�̃R�[�h
 * */
	public static String getKbnCod(String kbn){
		
		if(kbn.equals(""))
			return "";
		if(kbn.equals(KYUHU))
			return "0";
		else if(kbn.equals(SINPU))
			return "1";
		else if(kbn.equals(SAMPLE))
			return "2";
		else if(kbn.equals(DEMOBAN))
			return "3";
		else if(kbn.equals(TOKHAN))
			return "4";
		else if(kbn.equals(SONOTA)) //2004.03.08 add
			return "5";
		else if(kbn.equals(HICATALOG)) //2005.06.24 add
			return "6";
			
		return "";	
	}

	public static final String TAKASE = "�^�J�Z";
	public static final String JARED = "�i�`�q�d�c";
	//2005/02/03 add
	/**
	 * �ꏊ�R�[�h�ɂ���đq�ɖ����擾���郁�\�b�h
	 * @param bshCod
	 * @return �q�ɖ�
	 */
	public static String getSkoNm(String bshCod){

		String skoNm = "";
		//�ꏊ�R�[�h��"C320"�̏ꍇ
		if(bshCod.equals("C320"))
			skoNm = TAKASE;
		//�ꏊ�R�[�h��"C400"�̏ꍇ
		else if(bshCod.equals("C400"))
			skoNm = JARED;

		return skoNm;
	}

/**
 *  �������ԍ��̃t�H�[�}�b�g�p���\�b�h
 *  @param   String hacSyoBng
 *  @return  hacSyoBng���W���ɂȂ�܂ō������X�y�[�X�Ŗ��߂ĕԂ�
 * */
	public static String formatHacSyoBng(String hacSyoBng){
		if(hacSyoBng == null || hacSyoBng.trim().equals(""))
			return "";
		
		return StringUtils.lpad(hacSyoBng.trim(),8," ");
	}
	
/**
 *  �s�ԍ��̃t�H�[�}�b�g�p���\�b�h
 *  @param   String gyoBng
 *  @return  gyoBng���W���ɂȂ�܂ō������X�y�[�X�Ŗ��߂ĕԂ�
 * */
	public static String formatGyoBng(String gyoBng){
		if(gyoBng == null || gyoBng.trim().equals(""))
			return "";
		
		return StringUtils.lpad(gyoBng.trim(),8," ");
	}
	
	/**
	 * �O�񗚗��e�[�u���ɍ�����t�������ĕԂ��܂��B
	 * MAX9��ɒB���Ă����ꍇ�́A9�ԖڂɃZ�b�g���ĕԂ��܂��B
	 * @param rrkTbl_old
	 * @param today
	 * @return �����e�[�u��
	 */
	public static String makeRrkTblStr(String rrkTbl_old, int today) {		
		//�ő�J��Ԃ���
		final int MAX = 9;	
		//1�񕪂̃f�[�^��
		final int LENGTH = 4;
		//���ݓ��t(16�i)
		String today_hex = Integer.toHexString(today); 
		
		//����̌J��Ԃ��񐔎擾
		int newSize = (rrkTbl_old.length() + today_hex.length()) / LENGTH;
		//MAX��܂ŗ����X�V���𑝂₷
		String rrkTbl_new = "";
		if(newSize >= MAX)
			//�X�ԖڂɃZ�b�g
			rrkTbl_new = rrkTbl_old.substring(0, LENGTH * (MAX - 1)) + today_hex;		
		else
			rrkTbl_new = rrkTbl_old + today_hex;//��ԍŌ�ɃZ�b�g
	
		return rrkTbl_new;
	}
	
	/**
	 * �n���ꂽ���X�g�����̕�����̗v�f����菜���ĕԂ��܂��B
	 * @param arr
	 * @return ��̕��������菜�������X�g
	 */
	public static ArrayList removeBlankElement(ArrayList arr){

		while(arr.contains("")){
			arr.remove(arr.indexOf(""));
		}
		return arr;
	}
	
	/**
	 * separator���A�������ꍇ�̂��߂ɁAseparator�̒��O�ɃX�y�[�X��}������
	 * @param txt String
	 * @param separator String
	 * @return separator�ŋ�؂�ꂽToken�ԂɃX�y�[�X��}������������
	 */
	public static String insertSpace(String txt, String separator) {
		StringTokenizer line = new StringTokenizer(txt, separator, true);
		StringBuffer sb = new StringBuffer();
		while(line.hasMoreTokens()){
			String token = line.nextToken();
			if(token.equals(separator))
				sb.append(" ");
			sb.append(token);
		}
		return sb.toString();
	}
	
	/**
	 * �n���ꂽ�f�[�^�̍s�����擾����
	 * @param txt String
	 * @return �n���ꂽ�f�[�^�̍s��
	 */
	public static int getDataRowCount(String txt) {
		StringTokenizer line = new StringTokenizer(txt, "\n");
		int count;
		for(count = 0; line.hasMoreTokens(); count++){
			line.nextToken();
		}
		return count;
	}
	
	//TODO ��Вǉ��Ή�(�p�^�[���̕���ǉ�)
	/**
	 * �������ԍ��̃t�H�[�}�b�g
	 * @param bng
	 * @param hacSyoPtn
	 * @return �t�H�[�}�b�g�ςݔ������ԍ�
	 */
	public static String makeHacSyoBng(String bng, String hacSyoPtn){
		String hacSyoBng = "";
		if (hacSyoPtn.equals(CommonConst.PTN01)
			|| hacSyoPtn.equals(CommonConst.PTN03)
			|| hacSyoPtn.equals(CommonConst.PTN04)
			|| hacSyoPtn.equals(CommonConst.PTN05))
			hacSyoBng = StringUtils.lpad(bng.trim(),6,"0");
			
		if(hacSyoPtn.equals(CommonConst.PTN02))
			hacSyoBng = StringUtils.lpad(bng.trim(),8,"0");
	
		return hacSyoBng;	
	}

	/**
	 * �����敪��Ԃ��܂��B<br>
	 * nhnMeiStr�� %�^�J�Z% �܂��� %�i�`�q�d�c% �� �����敪="0"<br>
	 * ����ȊO �� �����敪="1"
	 * @param nhnMeiStr
	 * @param bshCod
	 * @return �����敪
	 */
	public static String getCykKbn(String nhnMeiStr, String bshCod) {
		String nhnMei = getSkoNm(bshCod);
		String cykKbn = "";
		
		for(int i = 0; i < nhnMeiStr.length()-(nhnMei.length()-1); i++){
			if(nhnMeiStr.startsWith(nhnMei,i)){
				cykKbn = "0";
				return  cykKbn;
			}
		}
		cykKbn = "1";
		return  cykKbn;
	}

	/**
	 * �T���v���敪�̎擾<br>
	 * �V���敪���T���v�� ���� ���������n���ꂽ���t�ȍ~��������"S"<br>
	 * �����łȂ�������u�����N
	 * @param sinKyuKbn
	 * @param hbiDteStr yyMMdd
	 * @param dteStr yyMMdd
	 * @return �T���v���敪
	 */
	public static String getSmpKbn(String sinKyuKbn, String hbiDteStr, String dteStr){
		if(sinKyuKbn.equals(getKbnCod(SAMPLE))){
			if(hbiDteStr.equals(""))
				return "";
			
			DateUtils hbiDte = new DateUtils(hbiDteStr, DateUtils.PTN_DATE_6);
			DateUtils dte = new DateUtils(dteStr, DateUtils.PTN_DATE_6);
			
			//���������n���ꂽ���t�ȍ~��������
			if(dte.getDate8Int() < hbiDte.getDate8Int())
				return "S";
		}
		return "";
	}
	
}