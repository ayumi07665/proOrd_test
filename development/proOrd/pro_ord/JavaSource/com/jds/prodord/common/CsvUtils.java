package com.jds.prodord.common;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

public class CsvUtils {

	private final static String CSV_CONTENT_TYPE = "application/octet-stream";
	private final static String CSV_HEADER_1     = "Content-Disposition";
	private final static String CSV_HEADER_2     = "attachment;filename=";
	private final static String CSV_HEADER_TAIL  = ".csv";
	private final static String CSV_ENTER	     = "\n";
	private final static String CSV_COMMA	     = ",";
	private final static String CSV_ENCODING     = "Cp932";
	private final static String CSV_QUOTE        = "\"";

	private final static SimpleDateFormat CSV_YYYYMMDD = new SimpleDateFormat("yyMMdd");
	private final static SimpleDateFormat CSV_HHMMSS = new SimpleDateFormat("HHmmss");

	public CsvUtils(){
	}


	/**
	 * 
	 * @param s
	 * @return 
	 * @exception 
	 */

	public synchronized static ActionErrors returnCsvStream(HttpServletResponse res,String filename,String title,String header,ArrayList arr){
		ActionErrors errors = null;
		String csvFileName = getFileName(filename);

		StringBuffer tmpStream = new StringBuffer();
		tmpStream.append(title);
		tmpStream.append(CSV_ENTER);
		tmpStream.append(CSV_ENTER);
		tmpStream.append(header);
		tmpStream.append(CSV_ENTER);

	        StringBuffer tmp_sb = new StringBuffer();
		String tmp_str	    = "";

		for(int i=0; i<arr.size(); i++){
			CommonCsvVoInterface ccvi;
			if(arr.get(i) instanceof CommonCsvVoInterface){
				ccvi = (CommonCsvVoInterface)arr.get(i);
			}else{
		    		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
				return errors;
			}
			for(int j=0; j<ccvi.getColSize(); j++){
				if(ccvi.getColumnType(j) == CommonCsvVoInterface.TYPE_STRING){
					// •¶Žš—ñ‚É h‚ªŠÜ‚Ü‚ê‚Ä‚¢‚éê‡‚Ì‘Î‰ž
   			     	        for(int k=0; k<ccvi.getColumn(j).length(); k++){
  				            tmp_str = ccvi.getColumn(j).substring(k,k+1);
     					    if(tmp_str.equals("\"")){
     					           tmp_sb.append("\"");
        				    }
         				    tmp_sb.append(tmp_str);
      				        }
					tmpStream.append("="+CSV_QUOTE + tmp_sb.toString() + CSV_QUOTE);
					tmp_sb = tmp_sb.delete(0,tmp_sb.length());
				}else{
					tmpStream.append("" + ccvi.getColumn(j));
				}
				if(j<ccvi.getColSize()-1){
					tmpStream.append(CSV_COMMA);
				}
			}
			tmpStream.append(CSV_ENTER);
		}

		res.setContentType(CSV_CONTENT_TYPE);
		res.setHeader(CSV_HEADER_1,csvFileName);
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(res.getOutputStream(),CSV_ENCODING)));

			pw.write(tmpStream.toString());
			pw.flush();
			pw.close();
		}catch(java.io.IOException ioe){
			SystemException se = new SystemException(ioe);
			se.printStackTrace();
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s 
	 * @return 
	 * @exception 
	 */

	private synchronized static String getFileName(String filename){
		Date date = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append(CSV_HEADER_2);
		sb.append(filename);
		sb.append("-");
		sb.append(CSV_YYYYMMDD.format(date));
		sb.append("-");
		sb.append(CSV_HHMMSS.format(date));
		sb.append(CSV_HEADER_TAIL);
		
		return sb.toString();
	}
	
}

