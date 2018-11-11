package com.hs2j.User.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.DTO;
import com.hs2j.User.vo.UserVO;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.comm.JdbcUtil;

public class UserDAO {
	private final Logger LOG=Logger.getLogger(UserDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
	
	/**
	 * 단건조회
	 */
	public UserVO do_selectOne(DTO dto) {
		UserVO retVO = null;
		UserVO vo=(UserVO) dto;
		Connection connection = null;
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {
			connection =sConnMaker.makeConnection();
			StringBuilder sb=new StringBuilder();
			sb.append(" SELECT user_id  ,   \n");   
			sb.append(" 	   user_pw  ,   \n");
			sb.append(" 	   user_email      , \n");
			sb.append(" 	   user_name       , \n");
			sb.append(" 	   user_account    , \n");
			sb.append(" 	   user_specialism , \n");
			sb.append(" 	   user_work       , \n");
			//sb.append(" 	   user_img_file   , \n");
			sb.append(" 	   user_p_number     \n");
			sb.append("  FROM hs_user 		\n");
			sb.append("  WHERE user_id = ? 	\n");
			LOG.debug("sql:\n" + sb.toString());
		
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getUserId());
			
			rs = ps.executeQuery();
			if (rs.next()) {
				retVO= new UserVO();
				retVO.setUserId		(rs.getString("user_id"));
				retVO.setUserPw		(rs.getString("user_pw"));
				retVO.setUserEmail 	(rs.getString("user_email"));
				retVO.setUserName		(rs.getString("user_name"));
				retVO.setUserAccount	(rs.getString("user_account"));
				retVO.setUserSpecialism(rs.getString("user_specialism"));
				retVO.setUserWork		(rs.getString("user_work"));
				//retVO.setUserImgFile	(rs.getString("user_img_file"));
				retVO.setUserPNumber	(rs.getString("user_p_number"));
				
				LOG.debug("TTT"+retVO.toString());
			}

		} catch (SQLException e) {
			LOG.debug("--SQLexception----------------");
			e.printStackTrace();
			LOG.debug("------------------");
		}finally {
		
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return retVO;
		
	}
	
	/**
	 * 추가
	 */
	public int do_add(DTO dto) {
		UserVO vo=(UserVO) dto;
		int isSuccess = -1;
		
		Connection connection = null;
		PreparedStatement ps= null;
		
		try {
			connection = sConnMaker.makeConnection();
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			
			//회원가입
			sb.append(" INSERT                 \n");
			sb.append(" INTO hs_user           \n");
			sb.append(" (                      \n");
			sb.append("     user_id,           \n");
			sb.append("     user_pw,           \n");
			sb.append("     user_email,        \n");
			sb.append("     user_name,         \n");
			sb.append("     user_account,      \n");
			sb.append("     user_specialism,   \n");
			sb.append("     user_work,         \n");
			//sb.append("     user_img_file,     \n");
			sb.append("     user_p_number      \n");
			sb.append(" )                      \n");
			sb.append(" VALUES                 \n");
			sb.append(" (                      \n");
			sb.append(" 	?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append(" 	?,                 \n");
			//sb.append(" 	?,                 \n");
			sb.append(" 	TO_CHAR(?,'09999999999')                 \n");
			sb.append(" )                      \n");
			
			LOG.debug("sql:\n" + sb.toString());
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, vo.getUserId());
			ps.setString(2, vo.getUserPw());
			ps.setString(3, vo.getUserEmail());
			ps.setString(4, vo.getUserName());
			ps.setString(5, vo.getUserAccount());
			ps.setString(6, vo.getUserSpecialism());
			ps.setString(7, vo.getUserWork());
			//ps.setString(8, vo.getUserImgFile());
			ps.setString(8, vo.getUserPNumber());
			
			isSuccess = ps.executeUpdate();
			LOG.debug("add 성공 여부 : " + isSuccess);
			
			//4. DB연결 종료
			ps.close();
			connection.close();

			LOG.debug(connection);
			
		} catch (SQLException e) {
			LOG.debug("--UserDAO.do_add.SQLexception----------------");
			LOG.debug("------------------");
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}//--do_add
	
	/**
	 * 수정
	 * @param dto
	 * @return
	 */
	public int do_update(DTO dto) {
		UserVO vo=(UserVO) dto;
		int isSuccess=-1;
		Connection connection = null;
		PreparedStatement ps= null;
		
		try {
			connection =sConnMaker.makeConnection();
			StringBuilder sb= new StringBuilder();
			
			//3.query 수행
			sb.append(" UPDATE hs_user         \n");
			sb.append(" SET user_pw = ?,       \n");
			sb.append("     user_email = ?,    \n");
			sb.append("     user_name = ?,     \n");
			sb.append("     user_specialism =?,   \n");
			sb.append("     user_work=? ,        \n");
			sb.append("     user_p_number = ?  \n");
			sb.append(" WHERE user_id = ?      \n");
			
			LOG.debug("sql:\n"+sb.toString());
	        ps = connection.prepareStatement(sb.toString());
	        
	        ps.setString(1, vo.getUserPw());
	        ps.setString(2, vo.getUserEmail());
	        ps.setString(3, vo.getUserName());
	        ps.setString(4, vo.getUserSpecialism());
	        ps.setString(5, vo.getUserWork());
	        ps.setString(6, vo.getUserPNumber());
	        ps.setString(7, vo.getUserId());
	        
	        
	        isSuccess = ps.executeUpdate();
			LOG.debug("update 성공 여부 : " + isSuccess);
			
			//4. DB연결 종료
			ps.close();
			connection.close();
			
		}catch(SQLException se){
			LOG.debug("---UserDAO.do_update.SQLException---");
			LOG.debug(se.getMessage());
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}//--do_update

	public List<DTO> do_selectList(DTO dto) {
		
		List<DTO>  list=new ArrayList<DTO>();
		UserVO vo   = (UserVO) dto;
		Connection  connection = null;
		PreparedStatement ps   = null;
		ResultSet rs =  null;
		
		try{	
			connection = sConnMaker.makeConnection();
			
			StringBuilder sbParam=new StringBuilder();//검색어
			//id   = 10
			//name = 20
			if(null != vo.getSearchDiv()){
				if( vo.getSearchDiv().equals("10")){//id
					sbParam.append(" WHERE userAccount like ?||'%' \n");
				}else if( vo.getSearchDiv().equals("20")){//name
					sbParam.append(" WHERE user_id like ?||'%' \n");
				}
			}

			
			StringBuilder sb=new StringBuilder();
			sb.append(" SELECT p1.*                                                      \n");                       
		    sb.append("    ,(SELECT cd_dtl_nm                                            \n");                       
		    sb.append("       FROM hs_code tc                                            \n");                   
		    sb.append("      WHERE cd_mst_id ='USER_ACCOUNT'                             \n");                             
		    sb.append("        AND cd_dtl_id = p1.user_account) as userAccount          \n");                                 
			sb.append("   FROM (                                                         \n");                        
		    sb.append("    SELECT t1.*                                                   \n");                    
		    sb.append("          ,ROW_NUMBER() OVER(ORDER BY t1.USER_NAME asc) AS rnum   \n");                         
		    sb.append("          ,COUNT(*) OVER() AS  total_cnt                          \n");                    
		    sb.append("      FROM hs_user t1                                             \n");                                               		
			sb.append("              --검색조건                                                                                                    \n");
			//-----------------------------------------------------------------------
			//검색조건
			if(null != vo.getSearchDiv()){
				if( sbParam.toString().length()>0){
					sb.append(sbParam.toString());
				}
			}
			//-----------------------------------------------------------------------
			sb.append("   )p1                                                                                                  \n");
			sb.append(" WHERE p1.rnum BETWEEN (? *(? -1 ) +1)   AND  (? * (?-1)+?)                                             \n");

			//sb.append(" WHERE p1.rnum BETWEEN (:page_size *(:page_num -1 ) +1)   AND  (:page_size * (:page_num-1)+:page_size) 
			LOG.debug("sql:\n"+sb.toString());
			
			
			ps = connection.prepareStatement(sb.toString());
			
			
			if(null != vo.getSearchDiv() && sbParam.toString().length()>0){
				ps.setString(1, vo.getSearchWord());
				ps.setInt(2, vo.getPageSize());
				ps.setInt(3, vo.getPageNum());	
				ps.setInt(4, vo.getPageSize());
				ps.setInt(5, vo.getPageNum());	
				ps.setInt(6, vo.getPageSize());
				
			}else{
				ps.setInt(1, vo.getPageSize());
				ps.setInt(2, vo.getPageNum());	
				ps.setInt(3, vo.getPageSize());
				ps.setInt(4, vo.getPageNum());	
				ps.setInt(5, vo.getPageSize());
			}
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				UserVO retVO = new UserVO();
				
				retVO.setNum(rs.getInt("rnum"));
				retVO.setUserId(rs.getString("user_id"));
				retVO.setUserName(rs.getString("user_name"));
				retVO.setUserPw(rs.getString("user_pw"));
				retVO.setUserSpecialism(rs.getString("user_specialism"));
				retVO.setUserWork(rs.getString("user_work"));
				retVO.setUserAccount(rs.getString("userAccount"));
				retVO.setUserEmail(rs.getString("user_email"));
				retVO.setUserPNumber(rs.getString("user_p_number"));
				retVO.setTotalCnt(rs.getInt("total_cnt"));
				LOG.debug("retVO:\n"+retVO.toString());
				list.add(retVO);
			}

			
		}catch(SQLException e) {
			LOG.debug("-SQLException------------");
			e.printStackTrace();
			LOG.debug("-------------");
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		
		return list;
	}
	/**
	 *로그인 처리 
	 */
	public UserVO do_login(UserVO t) {
		UserVO userVO = t;
		PreparedStatement ps=null;
		ResultSet rs = null;
				
		UserVO member = this.checkPasswd(userVO.getUserId(), userVO.getUserPw());
		if(member == null) {
			//userVO.setMessage("비밀번호를 확인 하세요.");
			LOG.debug("ID&비밀번호를 확인 하세요.");
			userVO.setMessageDiv("01");
			return null;
		}
		UserVO outUser= (UserVO) do_selectOne(userVO);
		outUser.setMessageDiv("11");
		outUser.setMessage("["+outUser.getUserName()+"]님 \n 환영 합니다.");
		return member;
	}
	/**
	 * id 체크
	 */
	public int checkUserId(String userId) {
		int isSuccess = 0;
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection connection =null;
		
		try {
			connection =sConnMaker.makeConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT COUNT(*) as cnt   \n");
			sb.append("   FROM hs_user     \n");
			sb.append("  WHERE user_id = ? \n");
			
			ps=connection.prepareStatement(sb.toString());
			System.out.println("checkUserId : \n" + sb.toString());
			System.out.println("userId : " + userId);
			System.out.println("=====================");
			
			ps.setString(1, userId);
			
			rs= ps.executeQuery();
			
			if(rs.next()) {
				isSuccess = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			LOG.debug("--checkPasswdSQLexception----------------");
			e.printStackTrace();
			LOG.debug("------------------");
		}finally {
		
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return isSuccess;
	}
	
	/**
	 * id/비번 체크
	 */
	public UserVO checkPasswd(String userId,String userPw) {
		UserVO member= null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int isSuccess = 0;
		
		try {
			connection =sConnMaker.makeConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT user_id  ,   \n");   
			sb.append(" 	   user_pw  ,   \n");
			sb.append(" 	   user_email      , \n");
			sb.append(" 	   user_name       , \n");
			sb.append(" 	   user_account    , \n");
			sb.append(" 	   user_specialism , \n");
			sb.append(" 	   user_work       , \n");
			//sb.append(" 	   user_img_file   , \n");
			sb.append(" 	   user_p_number     \n");
			sb.append("   FROM hs_user           \n");
			sb.append("  WHERE user_id = ?       \n");
			sb.append("    AND user_pw  = ?       \n");
			
			ps= connection.prepareStatement(sb.toString());
			ps.setString(1, userId);
			ps.setString(2, userPw);
			
			System.out.println("=====================");
			System.out.println("checkPasswd : \n" + sb.toString());
			System.out.println("userId : " + userId);
			System.out.println("userPw : " + userPw);
			System.out.println("=====================");
			
			rs = ps.executeQuery();
			if(rs.next()) {
				member= new UserVO();
				member.setUserId		(rs.getString("user_id"));
				member.setUserPw		(rs.getString("user_pw"));
				member.setUserEmail 	(rs.getString("user_email"));
				member.setUserName		(rs.getString("user_name"));
				member.setUserAccount	(rs.getString("user_account"));
				member.setUserSpecialism(rs.getString("user_specialism"));
				member.setUserWork		(rs.getString("user_work"));
				//member.setUserImgFile	(rs.getString("user_img_file"));
				member.setUserPNumber	(rs.getString("user_p_number"));
				
				LOG.debug("check"+member.toString());
			}
		} catch (SQLException e) {
			LOG.debug("--checkPasswdSQLexception----------------");
			e.printStackTrace();
			LOG.debug("------------------");
		}finally {
		
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return member;
		//User_ID 체크
	}

	
}
