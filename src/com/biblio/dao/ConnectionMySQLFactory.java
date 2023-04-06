package com.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionMySQLFactory {

	private static String DRIVERCLASS = "org.mariadb.jdbc.Driver";
	private static String BD_TYPE     = "mariadb";
	private static String BD_DATABASE = "biblio";
	private static String BD_USER     = "root";
	private static String BD_PASSWD   = "root";

	
	private static ConnectionMySQLFactory instanceSingleton = null;
	
	private Connection con = null;
	 
	static public ConnectionMySQLFactory getInstance() {
		if (instanceSingleton==null) {
			
			try {
			      Class.forName(DRIVERCLASS);
			      ConnectionMySQLFactory.instanceSingleton = new ConnectionMySQLFactory(); 

			} catch(ClassNotFoundException e) {
			  // TODO : basculer le serveur en mode ERREUR !!!
			  System.out.println("============= ERREUR DRIVERS : BDD HS ==================");
		  }
			
			
			
			ConnectionMySQLFactory.instanceSingleton = new ConnectionMySQLFactory();
		}
		return ConnectionMySQLFactory.instanceSingleton;
	}
	
	public Connection getConnection() throws SQLException {
		if ( this.con == null ) {
		      this.con = DriverManager.getConnection(
		    		  				"jdbc:"+BD_TYPE+"://localhost/"+BD_DATABASE,
		    		  				BD_USER,BD_PASSWD);
			}
			return this.con;
	}
	
	public void releaseConnection( Connection con ) {
		if (con==null) {
			return;
		}
		try {
			if ( ! this.con.isValid(3) ) {
				this.con.close();
				this.con = null;
			}
		} catch (SQLException e) {
			con = null;
		}
	}
}
