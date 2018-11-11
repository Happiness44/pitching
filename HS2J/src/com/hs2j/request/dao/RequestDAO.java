package com.hs2j.request.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.DTO;
import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.request.dao.RequestDAO;
import com.hs2j.request.vo.ReqListViewVO;
import com.hs2j.request.vo.ReqOneViewVO;
import com.hs2j.request.vo.RequestVO;


public class RequestDAO {
	
	private final Logger LOG = Logger.getLogger(RequestDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
	
	public int do_add(RequestVO vo){
		
		LOG.debug("do_add input VO:" + vo.toString());
		int isSuccess = -1;
		
		Connection connection = sConnMaker.makeConnection();
		
		PreparedStatement ps = null;
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			
			//--새 요청
			sb.append("INSERT INTO hs_requests   \n");
			sb.append("  (                       \n");
			sb.append("	 req_seq                 \n");
			sb.append("	,req_item                \n");
			sb.append("	,req_category            \n");
			sb.append("	,req_message             \n");
			sb.append("	,req_date                \n");
			sb.append("	,req_sender              \n");
			sb.append("	,req_money               \n");
			sb.append("  )                       \n");
			sb.append("  VALUES                  \n");
			sb.append("  (                       \n");
			sb.append("    req_sequence.NEXTVAL  \n");
			sb.append("    ,?                   \n");
			sb.append("    ,?                    \n");
			sb.append("    ,?                    \n");
			sb.append("    ,SYSDATE              \n");
			sb.append("    ,?                    \n");
			sb.append("	,?                       \n");
			sb.append("  )                      \n");
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, vo.getItem());
			ps.setString(2, vo.getCategory());
			ps.setString(3, vo.getReqMessage());
			ps.setString(4, vo.getSender());
			ps.setString(5, vo.getMoney());	
			
			isSuccess = ps.executeUpdate();
			LOG.debug("add 성공 여부 : " + isSuccess);
						
		}catch(SQLException se){
			LOG.debug("---RequestDAO.do_add.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}//--do_add
	
	
	public int do_update_acceptance(RequestVO vo){
		int isSuccess = -1;
		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			
			//--수락/거절 업데이트
			sb.append("--수락:10,거절:20                 \n");
			sb.append("UPDATE hs_requests            \n");
			sb.append("   SET req_acceptance = ?  \n");
			sb.append("  WHERE req_seq = ?    \n");
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, vo.getAcceptance());
			ps.setString(2, vo.getSeq());
			
			isSuccess = ps.executeUpdate();
			LOG.debug("update_acceptance 성공 여부 : " + isSuccess);
			
			//4. DB연결 종료
			ps.close();
			connection.close();
			
		}catch(SQLException se){
			LOG.debug("---RequestDAO.do_update_acceptance.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}//--do_update_acceptance
	
	public DTO do_selectOne(DTO dto){
		int isSuccess = -1;
		RequestVO vo = (RequestVO) dto;
		
		ReqOneViewVO retVO = null;
		
		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT t2.item_title                            \n");
			sb.append("  , t2.item_reg_person                           \n");
			sb.append("	 , t1.req_sender                               \n");
			sb.append("	 , t3.user_specialism                          \n");
			sb.append("	 , t3.user_img_file                            \n");
			sb.append("	 , t3.user_work                                \n");
			sb.append(",   t1.req_category                             \n");
			sb.append("	 , t1.req_message                              \n");
			sb.append("	 , t1.req_money                                \n");
			sb.append("	 , t1.req_seq                                  \n");
			sb.append("  FROM hs_requests t1, hs_item t2, hs_user t3   \n");
			sb.append("WHERE t1.req_item = t2.item_seq                 \n");
			sb.append("  AND t1.req_sender = t3.user_id                \n");
			sb.append("  AND t1.req_seq = ?                            \n");
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, vo.getSeq());
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				retVO = new ReqOneViewVO();
				retVO.setItemTitle(rs.getString("item_title"));
				retVO.setItemRegPerson(rs.getString("item_reg_person"));
				retVO.setReqSender(rs.getString("req_sender"));
				retVO.setUserSpecialism(rs.getString("user_specialism"));
				retVO.setUserImgFile(rs.getString("user_img_file"));
				retVO.setUserWork(rs.getString("user_work"));
				retVO.setReqMessage(rs.getString("req_message"));
				retVO.setReqSeq(rs.getString("req_seq"));
				retVO.setReqMoney(rs.getString("req_money"));
				retVO.setReqCategory(rs.getString("req_category"));
				
				LOG.debug("------retVO : \n " + retVO.toString());

			}
			
			
		}catch(SQLException se){
			LOG.debug("--RequestDAO.do_selectOne.SQLException--");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		} 
		
		return retVO;
				
	}//--do_selectOne
	
	public List<DTO> do_selectList(DTO dto){
		
		RequestVO vo = (RequestVO) dto;
		List<DTO> list = new ArrayList<DTO>();
		int isSuccess = -1;
		
		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{

			StringBuilder sbParam = new StringBuilder();
		
			if(null != vo.getSearchDiv()){
				if(vo.getSearchDiv().equals("10")){ //보낸 투자
					sbParam.append(" WHERE tr.req_sender = \'" + vo.getSearchWord() + "\'");
					sbParam.append("   AND tr.req_category = \'10\'");
				}else if (vo.getSearchDiv().equals("15")){ //받은 투자
					sbParam.append(" WHERE ti.item_reg_person = \'" + vo.getSearchWord() + "\'");
					sbParam.append("   AND tr.req_category = \'10\'");				
				}else if (vo.getSearchDiv().equals("20")){ //보낸 창업참가요청
					sbParam.append(" WHERE tr.req_sender = \'" + vo.getSearchWord()+ "\'");
					sbParam.append("   AND tr.req_category = \'20\'");	
				}else if (vo.getSearchDiv().equals("25")){ //받은 창업참가요청
					sbParam.append(" WHERE ti.item_reg_person = \'" + vo.getSearchWord() + "\'");
					sbParam.append("   AND tr.req_category = \'20\'");	
				}else if (vo.getSearchDiv().equals("40")){// 받은 투자(15) + 받은 요청(25)
					sbParam.append(" WHERE ti.item_reg_person = \'" + vo.getSearchWord() + "\'");
					sbParam.append("   AND tr.req_category IN ('10','20')");	
				}
			}

			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ta.num                                          \n");
			sb.append("	 , ta.page_num                                        \n");
			sb.append("	 , tb.total_cnt                                       \n");
			sb.append("	 , ta.item_title                                      \n");
			sb.append("   ,ta.item_reg_person                                  \n");
			sb.append(",   ta.seq                                              \n");
			sb.append("	 , ta.req_sender                                      \n");
			sb.append("	 , ta.req_date                                        \n");
			sb.append("   , ta.category                                       \n");
			sb.append("	 FROM (                                               \n");
			sb.append("SELECT ROWNUM as num, TT1.*                            \n");
			sb.append("  FROM (                                               \n");
			sb.append("SELECT CEIL(T1.rnum/?) AS page_num, T1.* --page_size   \n");
			sb.append("  FROM (                                               \n");
			sb.append("SELECT ROWNUM as rnum                                  \n");
			sb.append(",   ti.item_reg_person AS item_reg_person                \n");
			sb.append(",   tr.req_seq   AS  seq                                   \n");
			sb.append(",   tr.req_item 	AS item									  \n");
			sb.append(",   tr.req_category AS category                         \n");
			sb.append("	,  ti.item_title AS item_title                        \n");
			sb.append("	,  tr.req_sender AS req_sender                        \n");
			sb.append("	,  tr.req_date 	 AS req_date                          \n");
			sb.append("FROM hs_requests tr, hs_item ti                        \n");
			sb.append("---------------------------------------------          \n");
			sb.append("--검색조건---                                             \n");

			//-------------검색조건--------------------------------
			if(null != vo.getSearchDiv()){
				if(sbParam.toString().length() > 0){
						sb.append(sbParam.toString());
			}}
			//--------------------------------------------------
			sb.append("-------------------------------------------------      \n");
			sb.append("   AND tr.req_item = ti.item_seq                       \n");
			sb.append("  ) T1                                                 \n");
			sb.append("  ) TT1                                                \n");
			sb.append("  WHERE TT1.page_num = ? --page_num                    \n");
			sb.append("  ) ta                                                 \n");
			sb.append("  NATURAL JOIN                                         \n");
			sb.append("  (                                                    \n");
			sb.append("	SELECT COUNT(*) total_cnt                             \n");
			sb.append("	  FROM hs_requests tr, hs_item ti                     \n");
			sb.append("---------------------------------------------          \n");
			sb.append("--검색조건---                                             \n");
			

			//-------------검색조건--------------------------------
			if(null != vo.getSearchDiv()){
				if(sbParam.toString().length() > 0){
						sb.append(sbParam.toString());
			}}
			
			sb.append("-------------------------------------------------      \n");   
			sb.append("  AND tr.req_item = ti.item_seq                        \n");
			sb.append("  ) tb                                                 \n");
			
			//-------? 값 세팅하기------------
			ps = connection.prepareStatement(sb.toString());
	        ps.setInt(1, vo.getPageSize()); //page_size
	        ps.setInt(2, vo.getPageNum());	//page_num
	        
			rs = ps.executeQuery();
	            
	            while(rs.next()){
	            	ReqListViewVO retVO = new ReqListViewVO();
	            	
	            	retVO.setNum(rs.getInt("num"));
	            	retVO.setTotalCnt(rs.getInt("total_cnt"));
	            	retVO.setPageNum(rs.getInt("page_num"));
	            	
	            	retVO.setItemTitle(rs.getString("item_title"));
	            	retVO.setItemRegPerson(rs.getString("item_reg_person"));
	            	retVO.setReqSeq(rs.getString("seq"));
					retVO.setReqSender(rs.getString("req_sender"));
					retVO.setReqDate(rs.getString("req_date"));

					retVO.setReqCategory(rs.getString("category"));
					retVO.setPageSize(5);
					LOG.debug("------retVO : \n " + retVO.toString());
	            	list.add(retVO);
	            }
			
		}catch(SQLException se){
			LOG.debug("--RequestDAO.select_list.SQLException--");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
			}
		
		return list;
	}//--do_selectList	
}