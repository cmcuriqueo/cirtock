/*
 * Copyright 2016 Cesar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.edu.udc.cirtock.model;

import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.exception.CirtockSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Cesar
 */
public class Insumo {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    public Insumo() {
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
	/*
	 * Precondicion: el metodo debe ser ejecutado en una transaccion
	 **/
	public void insert(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO cirtock.insumo(");
			query.append("  nombre,");
			query.append("  descripcion,");
			query.append("  cantidad");
			query.append(") VALUES (");
			query.append("  ?::text,");
			query.append("  ?::text,");
			query.append("  ?::integer");
			query.append(")");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.nombre);
			preparedStatement.setString(2, this.descripcion);
			preparedStatement.setInt(3, this.cantidad);
			preparedStatement.executeUpdate();

			query = new StringBuffer();
			query.append("SELECT currval('cirtock.seq_insumo') AS id");
			preparedStatement = conn.prepareStatement(query.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.id = rs.getInt("id");
			} else {
				throw new CirtockException("No se pudo establecer el identificador del insumo");
			}
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al agregar el insumo a la base de datos");
		}
	}

	public void update(String usuario, Connection conn) throws CirtockException{

		try {

			StringBuffer query;
                        query = new StringBuffer();
			query.append("UPDATE cirtock.insumo");
			query.append("SET");
			query.append("  nombre = ?::text,");
			query.append("  descripcion = ?::text,");
			query.append("  cantidad = ?::integer ");
			query.append("WHERE id = ?::integer");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setString(1, this.nombre);
			preparedStatement.setString(2, this.descripcion);
			preparedStatement.setInt(3, this.cantidad);
                        preparedStatement.setInt(4, this.id);
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al actualizar el insumo en la base de datos");
		}
		
	}

	public void delete(String usuario, Connection conn) throws CirtockException {

		try {

			StringBuffer query;
                        query = new StringBuffer();
			query.append("DELETE cirtock.insumo");
			query.append("WHERE id = ?::integer");

			PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - " + usuario);
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new CirtockSQLException("Error al eliminar el insumo de la base de datos");
		}
	}
}
