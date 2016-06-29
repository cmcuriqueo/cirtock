package ar.edu.udc.cirtock.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.exception.CirtockSQLException;

public class Herramienta {
	private Integer id;
	private String nombre;
	private Integer cantidad;
	private String descripcion;
	
	public Herramienta() {
	}
	
	/* para db*/
	private Herramienta(String usuario, Integer id, Connection conn) throws CirtockException {
		super();

		try {

			StringBuffer query = new StringBuffer();
			query.append("SELECT");
			query.append("  id AS id,");
			query.append("  nombre AS nombre,");
			query.append("  descripcion AS descripcion,");
			query.append("  cantidad AS cantidad ");
			query.append("FROM");
			query.append("  cirtock.herramienta h ");
			query.append("WHERE");
			query.append("  h.id = ?::integer");
			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.id = rs.getInt("id");
				this.nombre = rs.getString("nombre");
				this.descripcion = rs.getString("descripcion");
				this.cantidad = rs.getInt("cantidad");
			} else {
				throw new CirtockException("No existe una herramienta con el id: " + id);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(e.getStackTrace());
			throw new CirtockSQLException("Error al acceder a la base de datos");
		}
	}
	
	public void setID(Integer id){
		this.id = id;
	}
	
	public Integer getID(){
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * Precondicion: el metodo debe ser ejecutado en una transaccion
	 **/
	public void insert(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO cirtock.herramienta(");
			query.append("  nombre,");
			query.append("  descripcion,");
			query.append("  cantidad");
			query.append(") VALUES (");
			query.append("  ?::text,");
			query.append("  ?::text,");
			query.append("  ?::text");
			query.append(")");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.nombre);
			preparedStatement.setString(2, this.descripcion);
			preparedStatement.setInt(3, this.cantidad);
			preparedStatement.executeUpdate();

			query = new StringBuffer();
			query.append("SELECT currval('cirtock.seq_herramienta') AS id");
			preparedStatement = conn.prepareStatement(query.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.id = rs.getInt("id");
			} else {
				throw new CirtockException("No se pudo establecer el identificador de la herramienta");
			}
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(e.getStackTrace().toString());
			throw new CirtockSQLException("Error al agregar la herramienta a la base de datos");
		}
	}

	public void update(String usuario, Connection conn) {
		
	}

	public void delte(String usuario, Connection conn) {
		
	}
}
