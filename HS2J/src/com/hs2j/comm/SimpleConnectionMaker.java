package com.hs2j.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class SimpleConnectionMaker {

	//private final Logger LOG = Logger.getLogger(SimpleConnectionMaker.class);
	
	public SimpleConnectionMaker(){}

	/**
	 * @return Connection
	 */
	
	public Connection makeConnection(){
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hs2j","hs2j");
			//LOG.debug("Connection:" + connection);
		} catch (ClassNotFoundException e) {
			//LOG.debug("----ConnectDB.ClassNotFoundException----");
			e.printStackTrace();
		} catch (SQLException se){
			//LOG.debug("----ConnectDB.SQLException----");
		}
		return connection;
	}
}
