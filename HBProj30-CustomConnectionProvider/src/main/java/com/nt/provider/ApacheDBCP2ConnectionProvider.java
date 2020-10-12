package com.nt.provider;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;

public class ApacheDBCP2ConnectionProvider extends UserSuppliedConnectionProviderImpl{
	
	private BasicDataSource bds;
	
	public ApacheDBCP2ConnectionProvider() {
		
		bds = new BasicDataSource();
		bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("system");
		bds.setPassword("1234");
		bds.setMinIdle(10);
		bds.setMaxIdle(100);
		
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		System.out.println("ApacheDBCP2ConnectionProvider.getConnection()");
		return bds.getConnection();
	}
	
	@Override
	public void closeConnection(Connection con) throws SQLException {
		System.out.println("ApacheDBCP2ConnectionProvider.closeConnection()");
		con.close();
	}

}
