package com.hs2j.comm;

import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.code.dao.CodeDAO;
import com.hs2j.code.vo.CodeVO;

public class StringUtil {
	
	//Log 설정
	private static Logger LOG = Logger.getLogger(StringUtil.class);
	
	/**
	 * 페이징
	 * @param totalCnt(총글수)
	 * @param pageNum(현재 페이지)
	 * @param pageSize(현재 페이지에 보여질 행수)
	 * @param bottomCnt(바닥에 보여줄 페이지수)
	 * @param url_i(호출 url)
	 * @param scriptNm(호출 자바 스크립트 이름)
	 * @return html
	 */
	public static String renderPaging(int totalCnt, int pageNum, int pageSize, int bottomCnt
			,String url_i,String scriptNm) {
		
		int maxNum = 0;
		int currPageNo = 1;
		int rowPerPage= 10;
		int bottomCount= 10;
		
		String url		  ="";
		String scriptName ="";
		
		//param value set
		maxNum 		= totalCnt;
		currPageNo  = pageNum;
		rowPerPage	= bottomCnt;
		bottomCount	= bottomCnt;
		url 		= url_i;
		scriptName	= scriptNm;
		
		/**
		 * <<
			<
			1 2 3 4 5 6 7 8 9 10
			>
		   >>
		 */
		//12
		int maxPageNo	= ((maxNum-1)/rowPerPage) +1;
		int startPageNo = ((currPageNo-1)/bottomCount)*bottomCount +1;
		int endPageNo 	= ((currPageNo-1)/bottomCount+1)*bottomCount;
		int nowBlockNo 	= ((currPageNo-1)/bottomCount)+1;
		int maxBlockNo 	= ((maxNum-1)/bottomCount)+1;
		
		int inx = 0;
		if(currPageNo > maxPageNo) return "";
		
		StringBuilder html = new StringBuilder();
		
		html.append(" <table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> \n");
		html.append(" 	<tr>                                                                         	  \n");
		html.append(" 		<td align=\"center\">                                                      	  \n");
		
		//<<
	      if(nowBlockNo > 1 && nowBlockNo < maxBlockNo){
	         html.append("<a href = \"javascript:" + scriptName + "( '" + url + "',1);\"> \n");
	         html.append("&laquo; \n");
	         html.append("</a> \n");
	      }
	      
		//<
		if(startPageNo > bottomCount) {
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+(startPageNo-1)+"); \"><</a> \n");
		}
		//1 2 3 4 5 6 7 8 9 10
		for(inx=startPageNo; inx <= maxBlockNo && inx <=endPageNo;inx++) {
			if(inx == currPageNo) {
				html.append("<b>"+inx+"</b> &nbsp;&nbsp; \n");
			}else {
					html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+inx+"); \"> \n");
					html.append(inx);
					html.append("</a> &nbsp;&nbsp; \n");
				  }
		}
		//> nowBlockNo bottomCount
		if(maxPageNo>=inx) {
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+((nowBlockNo*bottomCount)+1)+"); \"> \n");
			html.append(">  \n");
			html.append("</a> &nbsp;&nbsp; \n");
		}
		
		//>>
		if(maxPageNo >= inx) {
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+maxPageNo+"); \">&raquo;</a> \n");
		}
		
		html.append(" 		</td>                                                                    	  \n");
		html.append(" 	</tr>                                                                        	  \n");
		html.append(" </table>                                                                       	  \n");
		return html.toString();
	}
	
	/**
	 * null값 처리 (obj가 null이면 defVal을 return)
	 * @param obj
	 * @param defVal
	 * @return String
	 */
	public static String nvl(Object obj, String defVal){
		String retStr = "";

		if(null == obj) {
			retStr = defVal;
		} else {
			retStr = obj.toString().trim();
		}
		
		return retStr;
	}
	
	
	
	/**
	 * 
	 * @param list
	 * @param selectBox
	 * @param select_nm
	 * @param allYn
	 * @return
	 */
	public static String makeSelectBox(List<DTO> list
			,String select_box
			,String select_nm
			,boolean allYn){
		
		/**
		 * 			<select name = "levels" id = "levels">
						<option value = "1">일반</option>
						<option value = "9">관리자</option>
			</select>
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("	<select name = '" + select_nm + "' id = '" + select_nm + "'> \n");
		//option
		
			if(allYn == true){
				sb.append("<option value = ''>=전체=</option>\n");
			}
			
			for(DTO dto : list){
				CodeVO vo = (CodeVO) dto;
				
				sb.append("<option value = '" + vo.getDtlId() + "' ");
				if(select_box.equals(vo.getDtlId())){
					sb.append("selected = 'selected'");
				}
				sb.append(">\n");
				sb.append(vo.getDtlNm());
				sb.append("</option> \n");
			}
		//--option		
		sb.append("</select>");
		LOG.debug("-----StringUtil:");
		LOG.debug(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 
	 * @param list
	 * @param selectBox
	 * @param select_nm
	 * @param allYn
	 * @return
	 */
	public static String makeSelectBox2(List<DTO> list
			,String select_box
			,String select_nm
			,boolean allYn){
		
		/**
		 * 			<select name = "levels" id = "levels">
						<option value = "1">일반</option>
						<option value = "9">관리자</option>
			</select>
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("	<select name = '" + select_nm + "' id = '" + select_nm + "' style=\"width: 156px; height: 50px; margin: 5px;\"> \n");
		//option
		
			if(allYn == true){
				sb.append("<option value = ''>=전체=</option>\n");
			}
			
			for(DTO dto : list){
				CodeVO vo = (CodeVO) dto;
				
				sb.append("<option value = '" + vo.getDtlNm() + "' ");
				if(select_box != null) {
					if(select_box.equals(vo.getDtlNm())){
						sb.append("selected = 'selected'");
					}
				}
				sb.append(">\n");
				sb.append(vo.getDtlNm());
				sb.append("</option> \n");
			}
		//--option		
		sb.append("</select>");
		LOG.debug("-----StringUtil:");
		LOG.debug(sb.toString());
		return sb.toString();
	}

}
