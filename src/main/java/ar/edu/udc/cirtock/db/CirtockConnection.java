package ar.edu.udc.cirtock.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.udc.cirtock.exception.CirtockException;


public class CirtockConnection {

	static {
		try{
			Class.forName( "org.postgresql.Driver" );
		} catch( ClassNotFoundException e ) {
			System.out.println( e.getMessage() );
			System.out.println( e.getStackTrace() );
		}
	}
	
	public static Connection getConection( String database, String user, String password ) throws CirtockException {
		try {
			return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+ database, user, password );
		} catch ( SQLException e ) {
			System.out.println("PASOOOOOOOOOOOOOOO");
			System.out.println( e.getMessage() );
			System.out.println( e.getStackTrace() );
			throw new CirtockException("No se pudo establecer una coneccion con la base de datos");
		}
	}
}
