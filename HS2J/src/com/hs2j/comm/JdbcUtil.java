package com.hs2j.comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JdbcUtil {

	//Log 설정
	//private static Logger LOG = Logger.getLogger(JdbcUtil.class);
	/**
	 * ResultSet Close
	 * @param rs
	 */
	public static void close(ResultSet rs){
		if(null != rs){
			try {
		//		LOG.debug("-------" + rs);
				rs.close();
			//	LOG.debug("-------rs.isClosed()" + rs.isClosed());
			} catch (SQLException e) {
				//LOG.debug("-------rs close:Exception");
				//LOG.debug(e.getMessage());
			}
		}//--rs
	}//--close
	
	/**
	 * PreparedStatement Close
	 * @param ps
	 */
	public static void close(PreparedStatement ps){
		if(null != ps){
			try {
				//LOG.debug("-------" + ps);
				ps.close();
				//LOG.debug("-------ps.isClosed():" + ps.isClosed());
			} catch (SQLException e) {
				//LOG.debug("-------ps close:Exception");
				//LOG.debug(e.getMessage());
			}
		}//--ps
	}//--close
	
	/**
	 * Connection Close
	 * @param connection
	 */
	public static void close(Connection connection){
		if(null != connection){
			try {
				//LOG.debug("-------" + connection);
				connection.close();
				//LOG.debug("-------connection.isClosed()" + connection.isClosed());
			} catch (SQLException e) {
				//LOG.debug("-------connection close:Exception");
				//LOG.debug(e.getMessage());
			}
		}//--connection
	}//--close
}
