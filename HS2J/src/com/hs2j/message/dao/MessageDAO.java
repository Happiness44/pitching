package com.hs2j.message.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.comm.DTO;
import com.hs2j.message.vo.MessageVO;

public class MessageDAO {

	private final Logger LOG = Logger.getLogger(MessageDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();

	public int do_add(DTO dto) {
		int isSuccess = -1;
		MessageVO vo = (MessageVO) dto;

		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;

		try {
			// 3. query 수행
			StringBuilder sb = new StringBuilder();

			// --새 쪽지 쓰기
			sb.append("INSERT INTO hs_message                      \n");
			sb.append("(                                           \n");
			sb.append("	 msg_seq                                   \n");
			sb.append("	,msg_title                                 \n");
			sb.append("	,msg_contents                              \n");
			sb.append("	,msg_sender                                \n");
			sb.append("	,msg_receiver                              \n");
			sb.append("	,msg_date                                  \n");
			sb.append("	,msg_no_parent                             \n");
			sb.append(")                                           \n");
			sb.append("VALUES                                      \n");
			sb.append("(                                           \n");
			sb.append("	 msg_sequence.NEXTVAL                      \n");
			sb.append("	,?			                               \n");
			sb.append("	,?				                           \n");
			sb.append("	,?								           \n");
			sb.append("	,?								    	   \n");
			sb.append("	,SYSDATE                                   \n");
			sb.append("	,?		                                   \n");
			sb.append(")                                           \n");

			LOG.debug("sql : " + sb.toString());

			ps = connection.prepareStatement(sb.toString());

			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContents());
			ps.setString(3, vo.getSender());
			ps.setString(4, vo.getReceiver());
			ps.setString(5, vo.getNoParent());

			isSuccess = ps.executeUpdate();
			LOG.debug("add 성공 여부 : " + isSuccess);

			// 4. DB연결 종료
			ps.close();
			connection.close();

		} catch (SQLException se) {
			LOG.debug("---MessageDAO.do_add.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}// --do_add

	public int do_update_read(DTO dto) {
		int isSuccess = -1;
		MessageVO vo = (MessageVO) dto;

		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;

		try {
			// 3. query 수행
			StringBuilder sb = new StringBuilder();

			// --업데이트 (10:읽음, 20:안읽음)
			sb.append("UPDATE hs_message       \n");
			sb.append("   SET msg_read = '10'  \n");
			sb.append("  WHERE msg_seq = ?     \n");

			LOG.debug("sql : " + sb.toString());

			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getSeq());

			isSuccess = ps.executeUpdate();
			LOG.debug("update_read 성공 여부 : " + isSuccess);

		} catch (SQLException se) {
			LOG.debug("---MessageDAO.do_update_read.SQLException---");
			LOG.debug(se.getMessage());
		} finally {

			JdbcUtil.close(ps);
			JdbcUtil.close(connection);

		}
		return isSuccess;
	}// --do_update_read

	public MessageVO do_selectOne(DTO dto) {

		MessageVO vo = (MessageVO) dto;

		MessageVO retVO = null;
		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 3. query 수행
			StringBuilder sb = new StringBuilder();

			// 특정 쪽지 내용 조회(30일 지나지 않은 쪽지만)
			sb.append("	SELECT msg_seq         \n");
			sb.append("	,msg_title       \n");
			sb.append("	,msg_contents    \n");
			sb.append("	,msg_sender      \n");
			sb.append("	,msg_receiver    \n");
			sb.append("	,msg_date        \n");
			sb.append("	,msg_no_parent   \n");
			sb.append("	,msg_show_yn     \n");
			sb.append("	,msg_read        \n");
			sb.append("  FROM hs_message   		  \n");
			sb.append(" WHERE msg_seq = ? 		  \n");
			sb.append("  AND msg_show_yn = '10'	  \n");

			LOG.debug("sql:\n" + sb.toString());

			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getSeq());

			rs = ps.executeQuery();

			if (rs.next()) {
				retVO = new MessageVO();
				retVO.setSeq(rs.getString("msg_seq"));
				retVO.setTitle(rs.getString("msg_title"));
				retVO.setContents(rs.getString("msg_contents"));
				retVO.setSender(rs.getString("msg_sender"));
				retVO.setReceiver(rs.getString("msg_receiver"));
				retVO.setMsgDate(rs.getString("msg_date"));
				retVO.setNoParent(rs.getString("msg_no_parent"));
				retVO.setShowYn(rs.getString("msg_show_yn"));
				retVO.setRead(rs.getString("msg_read"));

				LOG.debug("------retVO : \n " + retVO.toString());

			}

		} catch (SQLException se) {
			LOG.debug("--MessageDAO.selectOne.SQLException--");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);

		}

		return retVO;

	}// --do_selectOne

	public List<DTO> do_select_hierList(DTO dto) {

		MessageVO vo = (MessageVO) dto;

		List<DTO> list = new ArrayList<DTO>();
		Connection connection = sConnMaker.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBuilder sb = new StringBuilder();

		//	sb.append("SELECT *                                     \n");
		//	sb.append("     FROM (                                  \n");
			sb.append("	SELECT ROWNUM  as rnum					     \n");
			sb.append("		, msg_seq    						     \n");
			sb.append("		,msg_title  						     \n");
			sb.append("		,msg_contents 							   \n");
			sb.append("		,msg_sender   							   \n");
			sb.append("		,msg_receiver  							  \n");
			sb.append("		,msg_date     							   \n");
			sb.append("		,msg_no_parent 							  \n");
			sb.append("		,msg_show_yn   							  \n");
			sb.append("		,msg_read    						    \n");
			sb.append("		  ,PRIOR msg_title                      \n");
			sb.append("		  ,PRIOR msg_contents                   \n");
			sb.append("	  FROM hs_message                           \n");
			sb.append("		WHERE msg_show_yn = '10'				\n");
			sb.append("	  START WITH msg_seq = ?                    \n");
			sb.append("	 CONNECT BY PRIOR msg_no_parent = msg_seq   \n");
	//		sb.append("	 ) TT1                                      \n");
	//		sb.append("WHERE TT1.rnum <= 5 	                        \n");
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getSeq());

			rs = ps.executeQuery();

			while (rs.next()) {
				MessageVO retVO = new MessageVO();

				retVO.setSeq(rs.getString("msg_seq"));
				retVO.setTitle(rs.getString("msg_title"));
				retVO.setContents(rs.getString("msg_contents"));
				retVO.setSender(rs.getString("msg_sender"));
				retVO.setReceiver(rs.getString("msg_receiver"));
				retVO.setMsgDate(rs.getString("msg_date"));
				retVO.setNoParent(rs.getString("msg_no_parent"));
				retVO.setShowYn(rs.getString("msg_show_yn"));
				retVO.setRead(rs.getString("msg_read"));

				LOG.debug("------retVO : \n " + retVO.toString());
				list.add(retVO);
			}


		} catch (SQLException se) {
			LOG.debug("--MessageDAO.select_hierList.SQLException--");
			LOG.debug(se.getMessage());
		} finally {

			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);

		}

		return list;

	}// --select_hierList

	public List<DTO> do_select_list(DTO dto) {
		MessageVO vo = (MessageVO) dto;

		List<DTO> list = new ArrayList<DTO>();
		Connection connection = sConnMaker.makeConnection();
		

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sbParam = new StringBuilder();
			if (null != vo.getSearchDiv()) {
				if (vo.getSearchDiv().equals("10")) {
					sbParam.append(" WHERE msg_sender = \'" + vo.getSearchWord() + "\'");
				} else if (vo.getSearchDiv().equals("20")) {
					sbParam.append(" WHERE msg_receiver = \'" + vo.getSearchWord() + "\'");
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT ta.num                                        \n");
			sb.append("  ,ta.page_num                                       \n");
			sb.append("  ,tb.total_cnt                                      \n");
			sb.append("  ,ta.msg_seq                                        \n");
			sb.append("  ,ta.msg_title                                      \n");
			sb.append("  ,ta.msg_contents                                   \n");
			sb.append("  ,ta.msg_sender                                     \n");
			sb.append("  ,ta.msg_receiver                                   \n");
			sb.append("  ,ta.msg_show_yn                                    \n");
			sb.append("  ,ta.msg_date                                       \n");
			sb.append("  ,ta.msg_read                                       \n");
			sb.append("  ,ta.msg_no_parent                                  \n");
			sb.append("FROM(                                                \n");
			sb.append("SELECT ROWNUM as num, TT1.*                          \n");
			sb.append("FROM(                                                \n");
			sb.append("SELECT CEIL(T1.rnum/?) AS page_num, T1.* --page_size \n");
			sb.append("FROM(                                                \n");
			sb.append("SELECT ROWNUM as rnum                                \n");
			sb.append("  ,msg_seq                                           \n");
			sb.append("  ,msg_title                                         \n");
			sb.append("  ,msg_contents                                      \n");
			sb.append("  ,msg_sender                                        \n");
			sb.append("  ,msg_receiver                                      \n");
			sb.append("  ,msg_show_yn                                       \n");
			sb.append("  ,msg_date                                          \n");
			sb.append("  ,msg_read                                          \n");
			sb.append("  ,msg_no_parent                                     \n");
			sb.append("FROM hs_message                                      \n");
			sb.append("--검색조건												\n");
			// -------------검색조건--------------------------------
			if (null != vo.getSearchDiv()) {
				if (sbParam.toString().length() > 0) {
					sb.append(sbParam.toString());
				}
			}
			// --------------------------------------------------
			sb.append("AND msg_show_yn = '10'                               \n");
			sb.append("ORDER BY msg_date DESC                               \n");
			sb.append(")T1                                                  \n");
			sb.append(")TT1                                                 \n");
			sb.append("WHERE TT1.page_num = ? --page_num                    \n");
			sb.append(")ta                                                  \n");
			sb.append("NATURAL JOIN                                         \n");
			sb.append("(                                                    \n");
			sb.append("SELECT COUNT(*) total_cnt                            \n");
			sb.append(" FROM hs_message                                     \n");
			sb.append("--검색조건												\n");

			// -------------검색조건--------------------------------
			if (null != vo.getSearchDiv()) {
				if (sbParam.toString().length() > 0) {
					sb.append(sbParam.toString());
				}
			}
			// --------------------------------------------------
			sb.append("AND msg_show_yn = '10'                               \n");
			sb.append(") tb                                                 \n");

			// -------? 값 세팅하기------------
			 ps = connection.prepareStatement(sb.toString());
			ps.setInt(1, vo.getPageSize()); // page_size
			ps.setInt(2, vo.getPageNum()); // page_num

			rs = ps.executeQuery();

			while (rs.next()) {
				MessageVO retVO = new MessageVO();

				retVO.setNum(rs.getInt("num"));
				retVO.setTotalCnt(rs.getInt("total_cnt"));
				retVO.setPageNum(rs.getInt("page_num"));

				retVO.setSeq(rs.getString("msg_seq"));
				retVO.setTitle(rs.getString("msg_title"));
				retVO.setContents(rs.getString("msg_contents"));
				retVO.setSender(rs.getString("msg_sender"));
				retVO.setReceiver(rs.getString("msg_receiver"));
				retVO.setMsgDate(rs.getString("msg_date"));
				retVO.setNoParent(rs.getString("msg_no_parent"));
				retVO.setShowYn(rs.getString("msg_show_yn"));
				retVO.setRead(rs.getString("msg_read"));

				//
				retVO.setPageSize(5);
				LOG.debug("------retVO : \n " + retVO.toString());
				list.add(retVO);
			}

		} catch (SQLException se) {
			LOG.debug("--MessageDAO.select_list.SQLException--");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}

		return list;

	}// --select_list
}