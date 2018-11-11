package com.hs2j.file.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.file.comm.FileDTO;
import com.hs2j.file.vo.FileVO;


public class FileDAO {
	private final Logger LOG = Logger.getLogger(FileDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
	
	
	/**
	 * 추가
	 */
	public int do_add(FileDTO dto) {
		int isSuccess=-1;
		FileVO vo=(FileVO) dto;
		Connection connection = null;
		PreparedStatement ps= null;
		try {
			connection = sConnMaker.makeConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" INSERT INTO HS_FILE  \n");
			sb.append("   (                  \n");
			sb.append("     f_seq,           \n");
			sb.append("     f_dtl_seq,       \n");
			sb.append("     f_org_nm,        \n");
			sb.append("     f_re_nm,         \n");
			sb.append("     f_url,           \n");
			sb.append("     f_ext            \n");
			sb.append("   )                  \n");
			sb.append("   VALUES             \n");
			sb.append("   (                  \n");
			sb.append("     file_seq.nextval,\n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?                \n");
			sb.append("   )                  \n");
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, vo.getF_DTL_SEQ());
			ps.setString(2, vo.getF_ORG_NM());
			ps.setString(3, vo.getF_RE_NM());
			ps.setString(4, vo.getF_URL());
			ps.setString(5, vo.getF_EXT());
			
			isSuccess = ps.executeUpdate();
			LOG.debug("add 성공 여부 : " + isSuccess);
			
			
			//4. DB연결 종료
			ps.close();
			connection.close();
			
		}catch(SQLException se){
			LOG.debug("---FileDAO.do_add.SQLException---");
			LOG.debug(se.getMessage());
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}
	
	/**
	 * 삭제
	 */
	
	public int do_delete(FileDTO dto) {
		int isSuccess=-1;
		FileVO vo=(FileVO)dto;
		Connection connection = null;
		PreparedStatement ps= null;
		try {
			connection =sConnMaker.makeConnection();
			StringBuilder sb= new StringBuilder();
			//query 수행
			sb.append("UPDATE HS_FILE		\n");
			sb.append("	  SET f_use ='20' \n");
			sb.append(" WHERE f_seq= ? \n");
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1,vo.getF_SEQ());
			
			isSuccess = ps.executeUpdate();
			LOG.debug("delete 성공 여부 : " + isSuccess);
			
			//4. DB연결 종료
			ps.close();
			connection.close();
			
		}catch(SQLException se){
			LOG.debug("---FileDAO.do_delete.SQLException---");
			LOG.debug(se.getMessage());
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}
	/**
	 * do_selectOne
	 * @param dto
	 * @return
	 */
	
	public FileVO do_selectOne(FileDTO dto) {
		FileVO vo=(FileVO) dto;
		FileVO retVO = null;
		
		Connection connection = null;
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {
			connection = sConnMaker.makeConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT                        \n");
			sb.append("      t1.f_seq,                 \n");
			sb.append("      t1.f_dtl_seq,             \n");
			sb.append("      t1.f_org_nm,              \n");
			sb.append("      t1.f_re_nm,               \n");
			sb.append("      t1.f_url,                 \n");
			sb.append("      t1.f_ext,                 \n");
			sb.append("      t1.f_use             		\n");
			sb.append(" FROM hs_file t1   				\n");
			sb.append(" WHERE  t1.f_seq = ?				\n");

			
			LOG.debug("sql:\n" + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getF_SEQ());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVO= new FileVO();
				retVO.setF_SEQ(rs.getString("f_seq"));
				retVO.setF_DTL_SEQ(rs.getString("f_dtl_seq"));
				retVO.setF_ORG_NM(rs.getString("f_org_nm"));
				retVO.setF_RE_NM(rs.getString("f_re_nm"));
				retVO.setF_URL(rs.getString("f_url"));
				retVO.setF_EXT(rs.getString("f_ext"));
				retVO.setF_USE(rs.getString("f_use"));
			
			LOG.debug("------retVO : \n " + retVO.toString());
		}
		
		//4. DB연결 종료
		rs.close();
		ps.close();
		connection.close();
		
	}catch(SQLException se){
		LOG.debug("--FileDAO.selectOne.SQLException--");
		LOG.debug(se.getMessage());
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(ps);
		JdbcUtil.close(connection);
	}
		
		return retVO;
		
	}
	/**
	 * selectList
	 */
	public List<FileDTO> do_select_list(FileDTO dto){
		FileVO vo=(FileVO)dto;
		
		List<FileDTO> list = new ArrayList<FileDTO>();
		Connection connection = null;
		PreparedStatement ps= null;
		ResultSet rs=null;

		try {
			connection = sConnMaker.makeConnection();
			StringBuilder sbParam = new StringBuilder();
			
			if(null != vo.getSearchDiv()){
				if(vo.getSearchDiv().equals("10")){
					sbParam.append(" WHERE f_use = \'" + vo.getSearchWord() + "\'");
				}else if (vo.getSearchDiv().equals("20")){
					sbParam.append(" WHERE f_use = \'"+ vo.getSearchWord() + "\'");
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("	SELECT                                                         \n");
			sb.append("	   ta.num                                                      \n");
			sb.append("	  ,ta.page_num                                                 \n");
			sb.append("	  ,tb.total_cnt                                                \n");
			sb.append("	  ,ta.f_seq                                                    \n");
			sb.append("	  ,ta.f_dtl_seq                                                \n");
			sb.append("	  ,ta.f_org_nm                                                 \n");
			sb.append("	  ,ta.f_re_nm                                                  \n");
			sb.append("	  ,ta.f_url                                                    \n");
			sb.append("	  ,ta.f_ext                                                    \n");
			sb.append("	  ,ta.f_use                                                    \n");              
			sb.append("	FROM(                                                          \n");
			sb.append("		SELECT rownum as num, ttl.*                                \n");
			sb.append("		FROM(                                                      \n");
			sb.append("			SELECT CEIL(t1.rnum/?) AS page_num, t1.* --page_size   \n");
			sb.append("			FROM(                                                  \n");
			sb.append("			SELECT rownum as rnum                                  \n");
			sb.append("			  	  ,f_seq                                               \n");
			sb.append("			  	  ,f_dtl_seq                                           \n");
			sb.append("			  	  ,f_org_nm                                            \n");
			sb.append("			  	  ,f_re_nm                                             \n");
			sb.append("			  	  ,f_url                                               \n");
			sb.append("			  	  ,f_ext                                               \n");
			sb.append("			  	  ,f_use                                               \n");                          
			sb.append("			 FROM hs_file                                     	   \n");	
			//-------------검색조건--------------------------------
			if(null != vo.getSearchDiv()){
				if(sbParam.toString().length() > 0){
						sb.append(sbParam.toString());
				}
			}
			//--------------------------------------------------
			sb.append("			WHERE f_use = '10'                                     \n");
			sb.append("			ORDER BY f_seq ASC                                    \n");
			sb.append("			)t1                                                    \n");
			sb.append("		)tt1                                                       \n");
			sb.append("		WHERE tt1.page_num = ? --page_num                          \n");
			sb.append("		)ta                                                        \n");
			sb.append("		NATURAL JOIN                                               \n");
			sb.append("		(                                                          \n");
			sb.append("		SELECT COUNT(*) total_cnt                                  \n");
			sb.append("		 FROM hs_file                                    		   \n");
			//-------------검색조건--------------------------------
			if(null != vo.getSearchDiv()){
				if(sbParam.toString().length() > 0){
						sb.append(sbParam.toString());
				}
			}
			//--------------------------------------------------			
			sb.append("		WHERE f_use = '10'                                         \n");
			sb.append("		) tb                                                   \n");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setInt(1, vo.getPageSize());
			ps.setInt(2, vo.getPageNum());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				FileVO retVO= new FileVO();
				retVO.setTotalCnt(rs.getInt("total_cnt"));
				
				retVO.setF_SEQ(rs.getString("f_seq"));
				retVO.setF_DTL_SEQ(rs.getString("f_dtl_seq"));
				retVO.setF_ORG_NM(rs.getString("f_org_nm"));
				retVO.setF_RE_NM(rs.getString("f_re_nm"));
				retVO.setF_URL(rs.getString("f_url"));
				retVO.setF_EXT(rs.getString("f_ext"));
				retVO.setF_USE(rs.getString("f_use"));
			
				list.add(retVO);
				LOG.debug("------retVO : \n " + retVO.toString());
		}
		
		    //--4.DB 연결 종료
            rs.close();
            ps.close();
            connection.close();
		}catch(SQLException se){
			LOG.debug("--MessageDAO.select_list.SQLException--");
			LOG.debug(se.getMessage());
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		
		return list;
		
	}
}