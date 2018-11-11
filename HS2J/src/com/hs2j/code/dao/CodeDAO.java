package com.hs2j.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.code.vo.CodeVO;
import com.hs2j.comm.DTO;
import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;
import com.hs2j.comm.WorkDiv;

public class CodeDAO implements WorkDiv {

		//Log 설정
		private final Logger LOG = Logger.getLogger(CodeDAO.class);
		
		//DB Connection 설정
		private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
	
		public List<DTO> do_selectList(DTO dto) {
		
		List<DTO> list = new ArrayList<DTO>();
		CodeVO vo = (CodeVO) dto;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		
		
		try{
			connection = sConnMaker.makeConnection();
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT cd_dtl_id,      \n");
			sb.append("       cd_dtl_nm       \n");
			sb.append("FROM hs_code       \n");
			sb.append("WHERE cd_mst_id = ?    \n");
			sb.append("  AND cd_use_yn = '1'  \n");
			sb.append("ORDER BY cd_seq        \n");
			
			LOG.debug("sql:\n" + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, vo.getMstId());

			rs = ps.executeQuery();
			while(rs.next()){
				CodeVO retVO = new CodeVO(); 
				
				retVO.setDtlId(rs.getString("cd_dtl_id"));
				retVO.setDtlNm(rs.getString("cd_dtl_nm"));
				
				LOG.debug("-----retVO : \n" + retVO.toString());
				list.add(retVO);
			}
			
			rs.close();
			ps.close();
			connection.close();
			
		} catch (SQLException e){
			LOG.debug("--selectList.SQLException--");
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
			
		}
		
		return list;
	}

		@Override
		public DTO do_selectOne(DTO vo) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int do_del(DTO vo) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int do_add(DTO vo) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int do_update(DTO vo) {
			// TODO Auto-generated method stub
			return 0;
		}
}
