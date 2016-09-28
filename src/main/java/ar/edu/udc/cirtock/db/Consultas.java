package ar.edu.udc.cirtock.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import ar.edu.udc.cirtock.model.Herramienta;
import ar.edu.udc.cirtock.model.Insumo;
import ar.edu.udc.cirtock.model.Producto;
import ar.edu.udc.cirtock.model.Usuario;

public class Consultas {
	
	public static LinkedList<Herramienta> obtenerHerramientas(Connection conn, String patronDescripcion, String patronNombre, Integer patronCantidad){
		LinkedList<Herramienta> herramientas = new LinkedList<>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT");
		query.append("  h.id,");
		query.append("  h.nombre,");
		query.append("  h.descripcion,");
		query.append("  h.cantidad ");
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

    public static LinkedList<Insumo> obtenerInsumos(Connection conn, String patronDescripcion, String patronNombre, Integer patronCantidad) {
		LinkedList<Insumo> insumos = new LinkedList<>();
		Boolean existePatronBusqueda = 
				(patronDescripcion != null && patronDescripcion.trim().length()>0) 
				|| (patronNombre != null && patronNombre.trim().length()>0)
				|| (patronCantidad != null && patronCantidad>0) ;
		StringBuffer query = new StringBuffer();
		query.append("SELECT");
		query.append("  i.id,");
		query.append("  i.nombre,");
		query.append("  i.descripcion,");
		query.append("  i.cantidad ");
		query.append("FROM");
		query.append("  cirtock.insumo i ");
                if (existePatronBusqueda){
			query.append("WHERE ");
			query.append("true ");
			if (patronDescripcion != null && patronDescripcion.trim().length()>0){
				query.append(" AND upper(i.descripcion) LIKE ?::text ");
			}
	
			if (patronNombre != null && patronNombre.trim().length()>0){
				query.append(" AND upper(i.nombre) LIKE ?::text ");
			}
		}
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			int i = 0;
			
			if (patronDescripcion != null && patronDescripcion.trim().length()>0){
				i++;
				preparedStatement.setString(i, "%"+patronDescripcion.trim().toUpperCase()+"%");
			}
	
			if (patronNombre != null && patronNombre.trim().length()>0){
				i++;
				preparedStatement.setString(i, "%"+patronNombre.trim().toUpperCase()+"%");
			}
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Insumo nueva = new Insumo();
				nueva.setId(rs.getInt("id"));
				nueva.setNombre(rs.getString("nombre"));
				nueva.setDescripcion(rs.getString("descripcion"));
				nueva.setCantidad(rs.getInt("cantidad"));
				insumos.add(nueva);
			} 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return insumos;    
    }
    public static LinkedList<Producto> obtenerProductos(Connection conn, String patronDescripcion, String patronNombre, Integer patronCantidad){
		LinkedList<Producto> productos = new LinkedList<>();
		Boolean existePatronBusqueda = 
				(patronDescripcion != null && patronDescripcion.trim().length()>0) 
				|| (patronNombre != null && patronNombre.trim().length()>0)
				|| (patronCantidad != null && patronCantidad>0) ;
		StringBuffer query = new StringBuffer();
		query.append("SELECT");
		query.append("  p.id,");
		query.append("  p.nombre,");
		query.append("  p.descripcion ");
		query.append("FROM");
		query.append("  cirtock.producto p ");
		if (existePatronBusqueda){
			query.append("WHERE ");
			query.append("true ");
			if (patronDescripcion != null && patronDescripcion.trim().length()>0){
				query.append(" AND upper(p.descripcion) LIKE ?::text ");
			}
	
			if (patronNombre != null && patronNombre.trim().length()>0){
				query.append(" AND upper(p.nombre) LIKE ?::text ");
			}
		}
		
		try {
			
			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			int i = 0;
			
			if (patronDescripcion != null && patronDescripcion.trim().length()>0){
				i++;
				preparedStatement.setString(i, "%"+patronDescripcion.trim().toUpperCase()+"%");
			}
	
			if (patronNombre != null && patronNombre.trim().length()>0){
				i++;
				preparedStatement.setString(i, "%"+patronNombre.trim().toUpperCase()+"%");
			}
                        
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.println("Producto encontado " + rs.getString("nombre"));
				Producto nueva = new Producto();
				nueva.setId(rs.getInt("id"));
				nueva.setNombre(rs.getString("nombre"));
				nueva.setDescripcion(rs.getString("descripcion"));
				productos.add(nueva);
			} 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return productos;
    }
    public static LinkedList<Usuario> obtenerUsuario(Connection conn, String patronUsuario, String patronPassword){
 		LinkedList<Usuario> usuarios = new LinkedList<>();
 		StringBuffer query = new StringBuffer();
 		query.append("SELECT");
 		query.append("  u.usuario,");
 		query.append("  u.password");
 		query.append("FROM");
 		query.append("  cirtock.usuario u");
 		try {
 			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
 			
 			ResultSet rs = preparedStatement.executeQuery();
 			
 			while (rs.next()) {
 				Usuario nueva = new Usuario();
 				nueva.setUsuario(rs.getString("usuario"));
 				nueva.setPassword(rs.getString("descripcion"));
 				usuarios.add(nueva);
 			} 
 		} catch (SQLException e) {
 			
 			e.printStackTrace();
 		}
 		
 		return usuarios;
     }
}
