package ar.edu.udc.cirtock.model;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.exception.CirtockSQLException;

public class Usuario {
	private String usuario;
	private String password;
	
	public Usuario(){
	}
	private Usuario(String usuario, String contraseña, Connection conn) throws CirtockException {
		super();

		try {

			StringBuffer query = new StringBuffer();
			query.append("SELECT");
			query.append(" 	password AS password,");
			query.append("  usuario AS usuario,");
			query.append("FROM");
			query.append("  cirtock.usuario u ");
			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.setUsuario(rs.getString("usuario"));
				this.setPassword(rs.getString("password"));
				
			} else {
				throw new CirtockException("No existe ese usuario: " + usuario);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(e.getStackTrace());
			throw new CirtockSQLException("Error al acceder a la base de datos");
		}
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * Precondicion: el metodo debe ser ejecutado en una transaccion
	 **/
	public void insert(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO cirtock.usuario(");
			query.append("  usuario,");
			query.append("  password");
			query.append(") VALUES (");
			query.append("  ?::text,");
			query.append("  ?::varchar[20]");
			query.append(")");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.usuario);
			preparedStatement.setString(2, this.password);
			preparedStatement.executeUpdate();

			query = new StringBuffer();
			preparedStatement = conn.prepareStatement(query.toString());
			
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al usuario en la base de datos");
		}
	}

	public void update(String usuario, Connection conn) throws CirtockException{

		try {

			StringBuffer query;
                        query = new StringBuffer();
			query.append("UPDATE cirtock.usuario");
			query.append("SET");
			query.append("  usuario = ?::text,");
			query.append("  password = ?::text");
			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.usuario);
			preparedStatement.setString(2, this.password);
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al actualizar el usuario en la base de datos");
		}
		
	}

	public void delete(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query;
                        query = new StringBuffer();
			query.append("DELETE cirtock.usuario");
			query.append("WHERE usuario = ?::text");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.usuario);
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al eliminar el usuario de la base de datos");
		}
	}
}
