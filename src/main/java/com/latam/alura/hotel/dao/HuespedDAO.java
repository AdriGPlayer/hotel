package com.latam.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import com.latam.alura.hotel.factory.*;
import com.latam.alura.hotel.modelo.*;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection _con) {
		con = _con;

	}

	public void guardarHuesped(ModeloHuesped huesped) {
		try (con) {
			String query = "INSERT INTO huesped (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
					+ "VALUES(?,?,?,?,?,?)";

			final PreparedStatement statement;
			statement = con.prepareStatement(query);
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, new java.sql.Date(huesped.getFechaNacimiento().getTime()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReservacion());
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public List<ModeloHuesped> mostrar(String criterio) {

		List<ModeloHuesped> resultado = new ArrayList<>();
		String sql = "";
		if (criterio.equals("")) {
			sql = "SELECT * FROM huesped";
		} else if (esNumeroEnetero(criterio)) {
			Integer id = Integer.parseInt(criterio);
			sql = "SELECT * FROM huesped WHERE  id = " + id + " OR apellido = " + id;
		} else if (criterio != "") {
			sql = "SELECT * FROM huesped WHERE nombre =  '" + criterio + "' OR apellido = '" + criterio + "' OR "
					+ "nacionalidad = '" + criterio + "' OR telefono = '" + criterio + "'";
		}

		try {
			final Statement statement = con.createStatement();
			try (statement) {
				final ResultSet resultSet = statement.executeQuery(sql);
				try (resultSet){
				while (resultSet.next()) {
					ModeloHuesped fila = new ModeloHuesped(resultSet.getInt("id"), resultSet.getString("nombre"),
							resultSet.getString("apellido"), resultSet.getDate("fecha_nacimiento"),
							resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
							resultSet.getInt("id_reserva"));
					resultado.add(fila);
				}
				return resultado;
					
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean esNumeroEnetero(String criterio) {
		try {
			Integer.parseInt(criterio);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int modificar(ModeloHuesped huesped) {
		String sql = "UPDATE huesped SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?";
		try {
			final PreparedStatement st = con.prepareStatement(sql);
			try (st){
					st.setString(1, huesped.getNombre());
					st.setString(2, huesped.getApellido());
					st.setDate(3, new java.sql.Date(huesped.getFechaNacimiento().getTime()));
					st.setString(4, huesped.getNacionalidad());
					st.setString(5, huesped.getTelefono());
					st.setInt(6, huesped.getIdReservacion());
					st.setInt(7, huesped.getId());
					st.execute();
					int updateCount = st.getUpdateCount();
					return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		String sql = "DELETE FROM huesped WHERE id = ?";

		try {
			final PreparedStatement state = con.prepareStatement(sql);
			try(state){
				state.setInt(1, id);
				state.execute();
				int updateCount = state.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
