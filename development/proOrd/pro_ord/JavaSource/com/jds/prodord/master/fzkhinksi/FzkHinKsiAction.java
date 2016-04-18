/**
* FzkHinKsiAction  付属品構成マスターメンテナンスアクションクラス
*
*	作成日    2004/02/12
*	作成者    （ＮＩＩ）森
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
* 		 2004/06/23（ＮＩＩ）蛭田
* 			・明細に｢発注先名称｣追加
*/

package com.jds.prodord.master.fzkhinksi;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class FzkHinKsiAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  FzkHinKsiDelegate bzDelegate = new FzkHinKsiDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		FzkHinKsiForm fmForm = (FzkHinKsiForm)form;
		FzkHinKsiVO fmVO = new FzkHinKsiVO();
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
		

		//サービス時間のチェック
		if(errors == null)
			errors = new ActionErrors();
		int check_result = CheckCommon.checkBatchDate(); //サービス時間のチェック
		switch(check_result){
			//内部エラーのとき
			case CheckCommon.errors_internal:
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
				break;
			//サービス時間外のとき	
			case CheckCommon.offservice:
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.offservice"));
				break;
			//エラーなしのとき	
			case CheckCommon.success:
				errors = null;
		}
		
		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}



		//--------以下、フォームの押したボタンから処理分岐を行う
		
		//最初にメニューからきたとき、照会結果画面から「戻る」ボタンで戻ってきたとき
		if(fmForm.getCommand().equals("INIT")){
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			String KaiSkbCod = 	logonForm.getKaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCod(KaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);
			
			fmForm.setKaiSkbCodOptions(kaiList);
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			
		}
		//「クリア」押下時
		else if(fmForm.getCommand().equals(FzkHinKsiForm.COMMAND_CLEAR)){
			fmForm.clearAll();
		fmForm.setUpdatable(false);
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
		}

		//処理区分が照会
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null)	//処理成功
			req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
		}

		//処理区分が登録
		else if(fmForm.getPrcKbn().equals("2")){
			errors = this.insert(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
				fmForm.setUpdatable(false);
			}		
		}

		//処理区分が更新
		else if(fmForm.getPrcKbn().equals("3")){
			errors = this.update(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				fmForm.setUpdatable(false);
			}		
		}

		//処理区分が削除
		else if(fmForm.getPrcKbn().equals("4")){
			errors = this.delete(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.delete.end"));
				fmForm.setUpdatable(false);
			}		
		}
				
		//--------処理分岐終わり
		
		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}
		//エラーなし
		return mapping.findForward("success");			
	}

	
	/**
	 * 検索
	 * 
	 */
	private ActionErrors select (FzkHinKsiForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		FzkHinKsiVO fmVO = new FzkHinKsiVO();		
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());

		String[] kai = fmForm.getKaiSkbCod();
		fmVO.setKaiSkbCod(kai[0]);	

		fmVO.setHinban(fmForm.getHinban());
		fmVO.setTitle(fmForm.getTitle());
		fmVO.setHbidte(fmForm.getHbidte());
		fmVO.setKetNm(fmForm.getKetNm());
		fmVO.setSetsuu(fmForm.getSetsuu());
	
		try{
			//検索実行
			FzkHinKsiVO[] resultFmVO = bzDelegate.doFind(fmVO);
			
			fmForm.clearFzkHinKsiVO();
			
			if(resultFmVO.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVO.length + FzkHinKsiForm.MIN_OF_BRANK_ROW_COUNT),FzkHinKsiForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
							
				for(int i = 0;i < resultFmVO.length;i++){
					FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
					if(i==0){
						fmForm.setTitle(resultFmVO[i].getTitle());
						fmForm.setHinban(resultFmVO[i].getHinban());
						fmForm.setHbidte(DataFormatUtils.setFormatYYMMDD(resultFmVO[i].getHbidte()));
						fmForm.setKetNm(resultFmVO[i].getKetNm());
						fmForm.setKetCod(resultFmVO[i].getKetCod());
						fmForm.setSetsuu(resultFmVO[i].getSetsuu());
						if(!resultFmVO[i].getExsitMst08()){
							fmForm.setPrcKbn("2");
							errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound","付属構成マスターに入力品番")); 
						}
					}
					formRow.setFuksziCod(resultFmVO[i].getFuksziCod());
					formRow.setFuksziNm(resultFmVO[i].getFuksziNm());
					formRow.setSirSk(resultFmVO[i].getSirSk());
					formRow.setCheck_bx(resultFmVO[i].getCheck_bx());
					formRow.setHacNm(resultFmVO[i].getHacNm()); //2004/06/23 add
				}
				fmForm.setFzkHinKsiVO(resultFmVO[0]);
				fmForm.setCount(resultFmVO[0].get08FuksziCodArr().size());
				if(resultFmVO[0].getFlag())
					fmForm.setSize(10);
					
			}else{
				fmForm.setCount(0);
				fmForm.setSize(FzkHinKsiForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				fmForm.setHbidte("");
				fmForm.setTitle("");
				fmForm.setSetsuu("");
				fmForm.setKetCod("");
				fmForm.setKetNm("");
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist")); 
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}


	/**
	 * 登録
	 * 
	 */

	private ActionErrors insert(FzkHinKsiForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String kaiSkbCod = fmForm.getKaiSkbCod()[0];

		FzkHinKsiVO fmVO = fmForm.getFzkHinKsiVO();	
		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		ArrayList line_Arr = new ArrayList();
		for(int i = 0; i < fmForm.getSize();i++){			
					
			FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				continue;
			}
			
			sb_fukSziCod.append(formRow.getFuksziCod());	
			sb_sirSkCod.append(StringUtils.rpad(formRow.getSirSk(),6," "));
			line_Arr.add((i+1)+"");
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());
		fmVO.setLineArr(line_Arr);
		fmVO.setHinban(fmForm.getHinban());
		
		try{
			//登録実行
			FzkHinKsiResult result = bzDelegate.doInsert(fmVO);
		
			//エラーだったのはないか調べる
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //ほかのユーザーが登録済み
				if(result.getDescription().equals(FzkHinKsiDelegate.EXIST)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				}else if(result.getDescription().equals(FzkHinKsiDelegate.COD_NOT_EXIST)){
					errors = new ActionErrors();

					ArrayList errs_fuk = result.getMap(FzkHinKsiResult.KEY_FUKSZICOD);
					ArrayList errs_sir = result.getMap(FzkHinKsiResult.KEY_SIRSKCOD);
					String index = "";
					//副資材コードが存在しない
					if((errs_fuk != null) || (errs_sir != null)){
						for(int j = 0; j < errs_fuk.size(); j++){
							String err_fuk = (String)errs_fuk.get(j);
							index = err_fuk;//エラーのあったIndex
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"副資材コード"));

							String err_sir = (String)errs_sir.get(j);
							index = err_sir;//エラーのあったIndex
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"仕入先コード"));
						}
					}
				}
			}
			if(errors == null){ 
				fmForm.setFzkHinKsiVO(result.getVO());
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	
	/**
	 * 更新
	 * 
	 */

	private ActionErrors update (FzkHinKsiForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String kaiSkbCod = fmForm.getKaiSkbCod()[0];

		FzkHinKsiVO fmVO = fmForm.getFzkHinKsiVO();	
		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		ArrayList line_Arr = new ArrayList();
		for(int i = 0; i < fmForm.getSize();i++){			
					
			FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				continue;
			}
			
			sb_fukSziCod.append(formRow.getFuksziCod());	
			sb_sirSkCod.append(StringUtils.rpad(formRow.getSirSk(),6," "));
			line_Arr.add((i+1)+"");
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());
		fmVO.setLineArr(line_Arr);
		fmVO.setHinban(fmForm.getHinban());
		
		try{
			//更新実行
			FzkHinKsiResult result = bzDelegate.doUpdate(fmVO);
		
			//エラーだったのはないか調べる
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //ほかのユーザーが変更済み
				if(result.getDescription().equals(FzkHinKsiDelegate.ANOTHER_USER_UPDATE)||
				   result.getDescription().equals(FzkHinKsiDelegate.LOGICAL_DELETE)||
				   result.getDescription().equals(FzkHinKsiDelegate.NOT_EXIST)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
			   }else if(result.getDescription().equals(FzkHinKsiDelegate.COD_NOT_EXIST)){
			   		errors = new ActionErrors();

					ArrayList errs_fuk = result.getMap(FzkHinKsiResult.KEY_FUKSZICOD);
					ArrayList errs_sir = result.getMap(FzkHinKsiResult.KEY_SIRSKCOD);
					String index = "";
					//副資材コードが存在しない
					if((errs_fuk != null) || (errs_sir != null)){
						for(int j = 0; j < errs_fuk.size(); j++){
							String err_fuk = (String)errs_fuk.get(j);
							index = err_fuk;//エラーのあったIndex
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"副資材コード"));

							String err_sir = (String)errs_sir.get(j);
							index = err_sir;//エラーのあったIndex
							if(!index.equals("-1"))
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",index,"仕入先コード"));
						}
					}
				}
			}
			if(errors == null){ 
				fmForm.setFzkHinKsiVO(result.getVO());
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	
	/**
	 * 削除
	 * 
	 */

	private ActionErrors delete(FzkHinKsiForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String kaiSkbCod = fmForm.getKaiSkbCod()[0];

		FzkHinKsiVO fmVO = fmForm.getFzkHinKsiVO();	
		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		ArrayList line_Arr = new ArrayList();
		for(int i = 0; i < fmForm.getSize();i++){			
					
			FzkHinKsiFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				continue;
			}
			
			sb_fukSziCod.append(formRow.getFuksziCod());	
			sb_sirSkCod.append(formRow.getSirSk());
			line_Arr.add((i+1)+"");
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());
		fmVO.setLineArr(line_Arr);

		try{
			//削除実行
			FzkHinKsiResult result = bzDelegate.doDelete(fmVO);
		
			//エラーだったのはないか調べる
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //ほかのユーザーが変更済み
				if(result.getDescription().equals(FzkHinKsiDelegate.ANOTHER_USER_UPDATE)||
				   result.getDescription().equals(FzkHinKsiDelegate.LOGICAL_DELETE)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				}else if(result.getDescription().equals(FzkHinKsiDelegate.NOT_EXIST)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound","削除するデータ"));
				}
			}
			if(errors == null){ 
				fmForm.clearFzkHinKsiVO();
				fmForm.setSize(FzkHinKsiForm.DEFAULT_ROW_COUNT);
				for(int i=0;i<fmForm.getSize();i++){
					fmForm.clearTableField(i);
				}
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	
}
