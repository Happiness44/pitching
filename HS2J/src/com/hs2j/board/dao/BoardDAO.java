package com.hs2j.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.User.vo.UserVO;
import com.hs2j.board.vo.BCSelectListVO;
import com.hs2j.board.vo.BCSelectOneVO;
import com.hs2j.board.vo.BoardVO;
import com.hs2j.comm.DTO;
import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.file.dao.FileDAO;
import com.hs2j.file.vo.FileVO;


public class BoardDAO {

	private final Logger LOG = Logger.getLogger(BoardDAO.class);
	private SimpleConnectionMaker eConnMaker = new SimpleConnectionMaker();

	public int do_add(DTO dto){
		int addFlag = -1;
		BoardVO bvo = (BoardVO)dto;
		Connection connection = null;
		PreparedStatement ps = null;
		
		UserVO member = null;
		String userId = null;
		String userAccount = null;

		try{
			connection = eConnMaker.makeConnection();
			LOG.debug("======do_add 디비 연결 성공");
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO HS_BOARD       \n");
			sb.append("	(                         \n");
			sb.append("		BD_SEQ,               \n");
			sb.append("		BD_TITLE,             \n");
			sb.append("		BD_CATEGORY,          \n");
			sb.append("		BD_POST_CATEGORY,     \n");
			sb.append("		BD_WITHIN_CATEGORY,   \n");
			sb.append("		BD_CONTENTS, 		  \n");
			sb.append("		BD_FILE,			  \n");
			sb.append("		BD_REG_PERSON,        \n");
			sb.append("		BD_REG_DATE           \n");
			sb.append("	)                         \n");
			sb.append("	VALUES                    \n");
			sb.append("	(                         \n");
			sb.append("		BD_SEQ.NEXTVAL,       \n");
			sb.append("		?,                    \n");
			sb.append("		?,                    \n");
			sb.append("		?,                    \n");
			sb.append("		?,                    \n");
			sb.append("		?,                    \n");
			sb.append("		?,                    \n");
			sb.append("		?,                    \n");
			sb.append("		SYSDATE               \n");
			sb.append("	)                         \n");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, bvo.getBdTitle());
			ps.setString(4, bvo.getBdWithinCategory());
			switch(bvo.getBdWithinCategory()) {
				case "1100": 
					ps.setString(2, "10");
					ps.setString(3, "100");
				break;
				
				case "2100": 
				case "2200": 
					ps.setString(2, "20");
					ps.setString(3, "200");
				break;
				
				case "3100": 
				case "3200": 
				case "3300": 
				case "3400": 
				case "3500": 
				case "3600": 
				case "3700": 
				case "3800": 
				case "3900": 
					ps.setString(2, "30");
					ps.setString(3, "300");
				break;
			}
			ps.setString(5, bvo.getBdContents());
			ps.setString(6, bvo.getBdFile());
			ps.setString(7, bvo.getBdRegPerson());		//여기서 로그인 한 사람의 아이디를 써야한다.
			
			addFlag = ps.executeUpdate();
			LOG.debug("addFlag: " + addFlag);
		}catch (SQLException sqle){
			LOG.debug("-SQLException----------------------------");
			sqle.printStackTrace();
			LOG.debug("-----------------------------");
		}finally{
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return addFlag;
	}
	
	public int do_del(DTO dto){
		int delFlag = -1;
		BoardVO bvo = (BoardVO)dto;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{
			connection = eConnMaker.makeConnection();
			LOG.debug("=====do_del 디비 연결 성공");
			
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM HS_BOARD \n");
			sb.append(" WHERE BD_SEQ = ?    \n");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, bvo.getBdSeq());
			
			delFlag = ps.executeUpdate();
			LOG.debug("delFlag: " + delFlag);

		}catch (SQLException sqle){
			LOG.debug("-SQLException----------------------------");
			sqle.printStackTrace();
			LOG.debug("-----------------------------");
		}finally{
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return delFlag;
	}
	
	public int do_udt(DTO dto){
		int updFlag = -1;
		BoardVO bvo = (BoardVO)dto;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{
			connection = eConnMaker.makeConnection();
			LOG.debug("do_udt 디비 연결 성공");
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE HS_BOARD               \n");
			sb.append("   SET BD_TITLE = ?           \n");
			sb.append("      ,BD_WITHIN_CATEGORY = ? \n");
			sb.append("      ,BD_CONTENTS = ?        \n");
			sb.append("      ,BD_FILE = ?            \n");
			sb.append("      ,BD_REG_DATE = sysdate  \n");
			sb.append(" WHERE BD_SEQ = ?             \n");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, bvo.getBdTitle());
			ps.setString(2, bvo.getBdWithinCategory());
			ps.setString(3, bvo.getBdContents());
			ps.setString(4, bvo.getBdFile());
			ps.setString(5, bvo.getBdSeq());
			
			updFlag = ps.executeUpdate();
			LOG.debug("updFlag: " + updFlag);

		}catch (SQLException sqle){
			LOG.debug("-SQLException----------------------------");
			sqle.printStackTrace();
			LOG.debug("-----------------------------");
		}finally{
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return updFlag;
	}
	
	public BCSelectOneVO do_selectOne(DTO dto){											//단건 조회
		BCSelectOneVO retVO = null;
		BoardVO vo = (BoardVO)dto;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			connection = eConnMaker.makeConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT b.BD_SEQ                             \n");
			sb.append("      ,b.BD_TITLE                           \n");
			sb.append("      ,c.CD_DTL_NM                  		   \n");
			sb.append("      ,b.BD_REG_PERSON                 	   \n");		
			sb.append("      ,b.BD_REG_DATE                        \n");	
			sb.append("      ,b.BD_CONTENTS                  	   \n");               	       
			sb.append("      ,f.F_URL                              \n");
			sb.append("  FROM HS_BOARD b left outer join HS_CODE c \n");
			sb.append("    ON b.BD_WITHIN_CATEGORY = c.CD_DTL_ID   \n");
			sb.append("       left outer join HS_FILE f            \n");
			sb.append("    ON b.BD_FILE = f.F_SEQ                  \n");
			sb.append("  WHERE b.BD_SEQ = ?                        \n");
			   
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getBdSeq());
			
			rs = ps.executeQuery();
			if(rs.next()){
				retVO = new BCSelectOneVO();
				retVO.setBdSeq(rs.getString("bd_seq"));
				retVO.setBdTitle(rs.getString("bd_title"));
				retVO.setCdDtlNm(rs.getString("cd_dtl_nm"));
				retVO.setBdRegPerson(rs.getString("bd_reg_person"));
				retVO.setBdRegDate(rs.getString("bd_reg_date"));
				retVO.setBdContents(rs.getString("bd_contents"));
				retVO.setfUrl(rs.getString("f_url"));
				
				LOG.debug("retVO: \n"+retVO.toString());
			}
		}catch (SQLException e) {
			LOG.debug("-SQLException-------------------------");
			e.printStackTrace();
			LOG.debug("--------------------------");
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return retVO;
	}
	
	public List<BCSelectListVO> do_selectList(DTO dto){									//게시판 별 전체 글 리스트 조회
		List<BCSelectListVO> list = new ArrayList<BCSelectListVO>();
		BoardVO vo = (BoardVO)dto;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			connection = eConnMaker.makeConnection();
			
			StringBuilder sbParam = new StringBuilder();										//검색
			
			if(null != vo.getSearchDiv()){
				if(vo.getSearchDiv().equals("10")){												//제목으로 검색
					sbParam.append("AND b.BD_TITLE LIKE '%'||?||'%'\n");
				} else if(vo.getSearchDiv().equals("20")){										//작성자로 검색
					sbParam.append("AND b.BD_REG_PERSON LIKE '%'||?||'%'\n");
				} else if(vo.getSearchDiv().equals("30")){
					sbParam.append("AND c.CD_DTL_NM LIKE '%'||?||'%'\n");							//분류로 검색
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ta.*                                                                                 \n");
			sb.append("     FROM                                                                                   \n");
			sb.append("        (                                                                                   \n");
			sb.append("        SELECT b.BD_SEQ                                                                     \n");
			sb.append("              ,c.CD_DTL_NM                                                                  \n");
			sb.append("              ,b.BD_TITLE                                                                   \n");
			sb.append("              ,b.BD_REG_PERSON                                                              \n");
			sb.append("              ,CASE WHEN TO_CHAR(b.BD_REG_DATE,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')\n");                    
			sb.append("                    THEN TO_CHAR(b.BD_REG_DATE,'HH24:MI')                                   \n");                     
			sb.append("                    ELSE TO_CHAR(b.BD_REG_DATE,'YYYY-MM-DD') END AS reg_dt                  \n");
			sb.append("              ,ROW_NUMBER() OVER(ORDER BY b.BD_REG_DATE DESC) AS rnum                       \n");
			sb.append("              ,COUNT(*) OVER() AS  total_cnt                                                \n");
			sb.append("         FROM HS_BOARD b, HS_CODE c                                                         \n");
			sb.append("        WHERE b.BD_CATEGORY = ?                                                             \n");
			sb.append("          AND b.BD_WITHIN_CATEGORY = c.CD_DTL_ID                                            \n");
			//검색조건----------------------------------------------------------------------------------------------------
			if(null != vo.getSearchDiv()) {
				if (sbParam.toString().length() > 0) {
					sb.append(sbParam.toString());
				}
			}
		    //---------------------------------------------------------------------------------------------------------
			sb.append("        ORDER BY b.BD_REG_DATE DESC                                                         \n");
			sb.append("    )ta                                                                                     \n");
			sb.append("WHERE ta.rnum BETWEEN (?*(?-1)+1)   AND  (?*(?-1)+?)                                        \n");

			ps = connection.prepareStatement(sb.toString());
			
			if(null != vo.getSearchDiv() && sbParam.toString().length()>0){
				ps.setString(1, vo.getBdCategory());
				ps.setString(2, vo.getSearchWord());
				ps.setInt(3, vo.getPageSize());
				ps.setInt(4, vo.getPageNum());	
				ps.setInt(5, vo.getPageSize());
				ps.setInt(6, vo.getPageNum());	
				ps.setInt(7, vo.getPageSize());
			}else{
				ps.setString(1, vo.getBdCategory());
				ps.setInt(2, vo.getPageSize());
				ps.setInt(3, vo.getPageNum());	
				ps.setInt(4, vo.getPageSize());
				ps.setInt(5, vo.getPageNum());	
				ps.setInt(6, vo.getPageSize());
			}
		
			rs = ps.executeQuery();
			
			while(rs.next()){
				BCSelectListVO bcvo = new BCSelectListVO();
				
				bcvo.setBdRnum(rs.getInt("rnum"));
				bcvo.setBdSeq(rs.getString("bd_seq"));
				bcvo.setCdDtlNm(rs.getString("cd_dtl_nm"));
				bcvo.setBdTitle(rs.getString("bd_title"));
				bcvo.setBdRegPerson(rs.getString("bd_reg_person"));
				bcvo.setBdRegDate(rs.getString("reg_dt"));
				bcvo.setBdTotalCnt(rs.getInt("total_cnt"));
				
				list.add(bcvo);
			}
			
		} catch (SQLException e) {
			LOG.debug("-SQLException-------------------------");
			e.printStackTrace();
			LOG.debug("--------------------------");
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return list;
	}
}