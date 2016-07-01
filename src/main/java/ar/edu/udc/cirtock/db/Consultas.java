package ar.edu.udc.cirtock.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import ar.edu.udc.cirtock.model.Herramienta;

public class Consultas {
	
	public static LinkedList<Herramienta> obtenerHerramientas(Connection conn, String patronDescripcion, String patronNombre, Integer patronCantidad){
		LinkedList<Herramienta> herramientas = new LinkedList<>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("  h.id");
		query.append("  h.nombre");
		query.append("  h.descripcion");
		query.append("  h.cantidad");
		query.append("FROM");
		query.append("  cirtock.herramienta h");
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Herramienta nueva = new Herramienta();
				nueva.setID(rs.getInt("id"));
				nueva.setNombre(rs.getString("nombre"));
				nueva.setDescripcion(rs.getString("descripcion"));
				nueva.setCantidad(rs.getInt("cantidad"));
				herramientas.add(nueva);
			} 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return herramientas;
	}
}
