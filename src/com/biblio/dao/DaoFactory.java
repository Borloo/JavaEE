package com.biblio.dao;

public class DaoFactory {

 private static DaoFactory instanceSingleton = null;
 
	 // Constructeur privé (usage limité à la classe elle même : Cf. "getInstance()")
	 private DaoFactory() {
	}
	
	public static DaoFactory getInstance() {
		if ( DaoFactory.instanceSingleton == null ) {
	      DaoFactory.instanceSingleton = new DaoFactory(); 
		}
		return DaoFactory.instanceSingleton;
	 
	}
	public AuteurDao getAuteurDao() {
		return new AuteurDaoImpl( );
	}

}
