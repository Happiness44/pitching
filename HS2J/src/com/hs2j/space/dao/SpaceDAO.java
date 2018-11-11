package com.hs2j.space.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.comm.DTO;
import com.hs2j.space.vo.SpaceVO;

public class SpaceDAO {
	
	private final Logger LOG = Logger.getLogger(SpaceDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
	
	
	public int do_add(SpaceVO vo){
		int isSuccess = -1;
		
		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			
			//--새 공간 정보 입력하기
			sb.append("INSERT INTO hs_space        \n");
			sb.append("(                           \n");
			sb.append("  sp_seq                    \n");
			sb.append("  ,sp_title                 \n");
			sb.append("  ,sp_reg_date              \n");
			sb.append("  ,sp_price                 \n");
			sb.append("  ,sp_location              \n");
			sb.append("  ,sp_category              \n");
			sb.append("  ,sp_url                   \n");
			sb.append("  ,sp_photo_url             \n");
			sb.append("  )                         \n");
			sb.append("VALUES                      \n");
			sb.append("(                           \n");
			sb.append(" space_sequence.NEXTVAL     \n");
			sb.append(" ,?                         \n");
			sb.append(" ,SYSDATE                   \n");
			sb.append(" ,?                         \n");
			sb.append(" ,?                         \n");
			sb.append(" ,?                         \n");
			sb.append(" ,?                         \n");
			sb.append(" ,?                         \n");
			sb.append(")                           \n");
			
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPrice());
			ps.setString(3, vo.getLocation());
			ps.setString(4, vo.getCategory());
			ps.setString(5, vo.getUrl());		
			ps.setString(6, vo.getPhotoUrl());
			
			isSuccess = ps.executeUpdate();
			LOG.debug("add 성공 여부 : " + isSuccess);
			
			
		}catch(SQLException se){
			LOG.debug("---SpaceDAO.do_add.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
			}
		return isSuccess;
	}//--do_add
	
	
	public List<DTO> do_selectList(DTO dto){

		SpaceVO vo = (SpaceVO) dto;
		List<DTO> list = new ArrayList<DTO>();
		
		Connection connection = sConnMaker.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			
			//--공간 정보 리스트로 가져오기
			sb.append("SELECT ta.num                                         \n");
			sb.append("	 , ta.page_num                                       \n");
			sb.append("	 , tb.total_cnt                                      \n");
			sb.append(",   ta.sp_seq                                          \n");
			sb.append("	 , ta.sp_title                                       \n");
			sb.append("	 , ta.sp_price                                       \n");
			sb.append("	 , ta.sp_url                                         \n");
			sb.append("	 , ta.sp_photo_url                                   \n");
			sb.append("	 FROM (                                              \n");
			sb.append("SELECT ROWNUM as num, TT1.*                           \n");
			sb.append("  FROM (                                              \n");
			sb.append("SELECT CEIL(T1.rnum/?) AS page_num, T1.* --page_size  \n");
			sb.append("  FROM (                                              \n");
			sb.append("SELECT ROWNUM as rnum                                 \n");
			sb.append("    , sp_seq                                          \n");
			sb.append("	,sp_title                                            \n");
			sb.append("	,sp_price                                            \n");
			sb.append("	,sp_url                                              \n");
			sb.append("	,sp_photo_url                                        \n");
			sb.append("FROM hs_space                                         \n");
			sb.append("----------------------                                \n");
			sb.append("--검색조건                                               \n");
			sb.append("-----------------------                               \n");
			sb.append(" ) T1                                                 \n");
			sb.append(" ) TT1                                                \n");
			sb.append(" WHERE TT1.page_num = ? --page_num                    \n");
			sb.append(" ) ta                                                 \n");
			sb.append(" NATURAL JOIN                                         \n");
			sb.append(" (                                                    \n");
			sb.append("SELECT COUNT(*) total_cnt                             \n");
			sb.append("  FROM hs_space                                       \n");
			sb.append("--------------------------------------------          \n");
			sb.append("--검색조건            		                                     \n");
			sb.append("------------------------------------------------      \n");
			sb.append(" ) tb                                                 \n");
			
			
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setInt(1, vo.getPageSize());
			ps.setInt(2, vo.getPageNum());

			LOG.debug("vo.getPageNum()" + vo.getPageNum());
			LOG.debug("vo.getPageSize()" + vo.getPageSize());
			
			rs = ps.executeQuery();
            
            while(rs.next()){
            	SpaceVO retVO = new SpaceVO();
            	
            	retVO.setNum(rs.getInt("num"));
            	retVO.setTotalCnt(rs.getInt("total_cnt"));
            	retVO.setPageNum(rs.getInt("page_num"));
            	
            	retVO.setTitle(rs.getString("sp_title"));
            	retVO.setPrice(rs.getString("sp_price"));
				retVO.setUrl(rs.getString("sp_url"));
				retVO.setPhotoUrl(rs.getString("sp_photo_url"));
				
				retVO.setSeq(rs.getString("sp_seq"));
				
				LOG.debug("------retVO : \n " + retVO.toString());
            	list.add(retVO);
            }
			
			
		}catch(SQLException se){
			LOG.debug("---SpaceDAO.do_selectList.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
			}
		
		return list;
	}
	
}
