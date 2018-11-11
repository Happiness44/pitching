package com.hs2j.bic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.bic.vo.BicVO;
import com.hs2j.board.dao.BoardDAO;
import com.hs2j.board.vo.BCSelectListVO;
import com.hs2j.comm.DTO;
import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;

public class BicDAO{
	
	private final Logger LOG = Logger.getLogger(BoardDAO.class);
	private SimpleConnectionMaker eConnMaker = new SimpleConnectionMaker();
	
	public int do_add(List<BicVO> list) {			//공공 API 데이터를 xml로 파싱한 후 데이터를 디비에 넣을 때
		int addFlag = -1;
		List<BicVO> blist = list;
		Connection connection = null; 
		PreparedStatement ps = null;
		
		try {
			connection = eConnMaker.makeConnection();
			
			for(int i=0; i<list.size(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append("INSERT INTO HS_BIC   \n");
				sb.append("(                    \n");
				sb.append("    BIC_SEQ,         \n");
				sb.append("    BIC_NAME,        \n");
				sb.append("    BIC_PHONE,       \n");
				sb.append("    BIC_ADDRESS,     \n");
				sb.append("    BIC_ROADNM,      \n");
				sb.append("    BIC_LATitude,    \n");
				sb.append("    BIC_LONGITUDE    \n");
				sb.append(")                    \n");
				sb.append("VALUES               \n");
				sb.append("(                    \n");
				sb.append("    BIC_SEQ.NEXTVAL, \n");
				sb.append("    ?,               \n");
				sb.append("    ?,               \n");
				sb.append("    ?,               \n");
				sb.append("    ?,               \n");
				sb.append("    ?,               \n");
				sb.append("    ?                \n");
				sb.append(")                    \n");
				
				ps = connection.prepareStatement(sb.toString());
				ps.setString(1, blist.get(i).getBicName());
				ps.setString(2, blist.get(i).getBicPhone());
				ps.setString(3, blist.get(i).getBicAddress());
				ps.setString(4, blist.get(i).getBicRoadNm());
				ps.setString(5, blist.get(i).getBicLatitude());
				ps.setString(6, blist.get(i).getBicLongitude());

				ps.executeUpdate();
			}
		}catch (SQLException sqle){
			sqle.printStackTrace();
		}finally{
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return addFlag;
	}
	
	public List<BicVO> do_selectList(DTO dto){			//필터링 or 전체 창업 보육 센터 리스트
		List<BicVO> list = new ArrayList<BicVO>();
		BicVO vo = (BicVO)dto;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = eConnMaker.makeConnection();
			String searchDiv = "";
			String searchWord = "";
			
			if(null != vo.getSearchDiv()) {
				searchDiv = vo.getSearchDiv();
			}
			
			if(null != vo.getSearchWord()) {
				searchWord = vo.getSearchWord();
			}
		
			StringBuilder sb = new StringBuilder();
		
			sb.append("SELECT BIC_SEQ,         \n");
			sb.append("       BIC_NAME,        \n");
			sb.append("       BIC_PHONE,       \n");
			sb.append("       BIC_ADDRESS,     \n");
			sb.append("       BIC_ROADNM,      \n");
			sb.append("       BIC_LATITUDE,    \n");
			sb.append("       BIC_LONGITUDE    \n");
			sb.append("  FROM HS_BIC           \n");
			//검색조건---------------------------------------------------
			sb.append("WHERE BIC_ADDRESS LIKE '%'||?||'%' AND BIC_NAME LIKE '%'||?||'%'\n");
			//--------------------------------------------------------
			sb.append(" ORDER BY BIC_ADDRESS   \n");
			
			ps = connection.prepareStatement(sb.toString());
			
			ps.setString(1, searchDiv);
			ps.setString(2, searchWord);

			rs = ps.executeQuery();

			while(rs.next()){
				BicVO bcvo = new BicVO();

				bcvo.setBicSeq(rs.getString("bic_seq"));
				bcvo.setBicName(rs.getString("bic_name"));
				bcvo.setBicPhone(rs.getString("bic_phone"));
				bcvo.setBicAddress(rs.getString("bic_address"));
				bcvo.setBicRoadNm(rs.getString("bic_roadnm"));
				bcvo.setBicLatitude(rs.getString("bic_latitude"));
				bcvo.setBicLongitude(rs.getString("bic_longitude"));
				
				list.add(bcvo);
			}
		} catch(SQLException e) {
			LOG.debug("-SQLException-------------------------");
			e.printStackTrace();
			LOG.debug("--------------------------------------");
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return list;
	}
}