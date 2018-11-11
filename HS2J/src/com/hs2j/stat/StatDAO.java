package com.hs2j.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;

public class StatDAO {

	private final Logger LOG = Logger.getLogger(StatDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
	
	
	public List<String> getCodeList(){
		Connection connection = sConnMaker.makeConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> list = new ArrayList<String>();
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			sb.append("select CD_DTL_ID                   \n");
			sb.append("from hs_code                       \n");
			sb.append("where cd_mst_id = 'ITEM_CATEGORY' \n");
			LOG.debug("sql : " + sb.toString());
			ps = connection.prepareStatement(sb.toString());
			
			int isSuccess = ps.executeUpdate();
			LOG.debug("getCodeList 성공 여부 : " + isSuccess);
			
			rs = ps.executeQuery();
            
            while(rs.next()){
            	list.add(rs.getString("cd_dtl_id"));
            }
            LOG.debug("list:"+list.toString());
            
			
		}catch(SQLException se){
			LOG.debug("---StatDAO.getCodeList.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
	
		return list;
		
	}//getCodeList
	
	public String getItemCountsByList(String code){
		Connection connection = sConnMaker.makeConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String itemCount = null;
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) AS count      \n");
			sb.append("  FROM HS_ITEM           \n");
			sb.append(" WHERE ITEM_CATEGORY = ? \n");
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			LOG.debug("getItemCountsByList/code=" + code);
			ps.setString(1, code);
			
			
			int isSuccess = ps.executeUpdate();
			LOG.debug("getCodeList 성공 여부 : " + isSuccess);
			
			rs = ps.executeQuery();
			if(rs.next()){
				itemCount = rs.getString("count");
			}
            LOG.debug("itemCount:"+itemCount);
            
			
		}catch(SQLException se){
			LOG.debug("---StatDAO.getCodeList.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
	
		return itemCount;
		
	}//getItemCountsByList
	
	public String getCodeName(String code){
		Connection connection = sConnMaker.makeConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String codeName = null;
		
		try{
			//3. query 수행
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT CD_DTL_NM      \n");
			sb.append("  FROM HS_CODE           \n");
			sb.append(" WHERE  CD_DTL_ID = ? \n");
			LOG.debug("sql : " + sb.toString());
			
			ps = connection.prepareStatement(sb.toString());
			LOG.debug("getcode=" + code);
			ps.setString(1, code);
			
			rs = ps.executeQuery();
			if(rs.next()){
				codeName = rs.getString("CD_DTL_NM");
			}
            LOG.debug("itemCount:"+codeName);
            
			
		}catch(SQLException se){
			LOG.debug("---StatDAO.getCodeName.SQLException---");
			LOG.debug(se.getMessage());
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
	
		return codeName;
		
	}//getCodeName
	
}
