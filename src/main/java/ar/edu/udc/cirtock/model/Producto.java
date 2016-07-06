package ar.edu.udc.cirtock.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.exception.CirtockSQLException;


public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private LinkedList<Herramienta> herramientas;
	private LinkedList<Insumo> insumos;
	
	public Producto(){
	}
	
	public LinkedList<Herramienta> getHerramientas() {
		return herramientas;
	}


	public void setHerramientas(LinkedList<Herramienta> herramientas) {
		this.herramientas = herramientas;
	}


	public LinkedList<Insumo> getInsumos() {
		return insumos;
	}


	public void setInsumos(LinkedList<Insumo> insumos) {
		this.insumos = insumos;
	}


	public Producto(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * Precondicion: el metodo debe ser ejecutado en una transaccion
	 **/
	public void insert(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO cirtock.producto(");
			query.append("  nombre,");
			query.append("  descripcion");
			query.append(") VALUES (");
			query.append("  ?::text,");
			query.append("  ?::text");
			query.append(")");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.nombre);
			preparedStatement.setString(2, this.descripcion);
			preparedStatement.executeUpdate();

			query = new StringBuffer();
			query.append("SELECT currval('cirtock.seq_producto') AS id");
			preparedStatement = conn.prepareStatement(query.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.id = rs.getInt("id");
			} else {
				throw new CirtockException("No se pudo establecer el identificador del producto");
			}
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al agregar el producto a la base de datos");
		}
	}

	public void update(String usuario, Connection conn) throws CirtockException{

		try {

			StringBuffer query;
                        query = new StringBuffer();
			query.append("UPDATE cirtock.producto");
			query.append("SET");
			query.append("  nombre = ?::text,");
			query.append("  descripcion = ?::text,");
			query.append("WHERE id = ?::integer");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.nombre);
			preparedStatement.setString(2, this.descripcion);
                        preparedStatement.setInt(3, this.id);
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al actualizar el producto en la base de datos");
		}
		
	}

	public void delete(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query;
                        query = new StringBuffer();
			query.append("DELETE cirtock.producto");
			query.append("WHERE id = ?::integer");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al eliminar el producto de la base de datos");
		}
	}
	
}
