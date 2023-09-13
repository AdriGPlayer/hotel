package com.latam.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.latam.alura.hotel.modelo.*;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public Integer guardar(ModeloReservacion reservacion) {
		Integer generarID = 0;
		String sql = "INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_pago)" + "values(?,?,?,?)";

		try {
			final PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setDate(1, new java.sql.Date(reservacion.getFechaEntrada().getTime()));
				statement.setDate(2, new java.sql.Date(reservacion.getFechaSalida().getTime()));
				statement.setFloat(3, reservacion.getValorReservacion());
				statement.setString(4, reservacion.getFormaPago());
				statement.execute();

				final ResultSet resulSet = statement.getGeneratedKeys();
				try (resulSet) {
					while (resulSet.next()) {
						reservacion.setId(resulSet.getInt(1));
						JOptionPane.showMessageDialog(null,
								"Se a creado una reservacion de numero " + reservacion.getId());
					}
					generarID = reservacion.getId();
					return generarID;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<ModeloReservacion> mostrar(String criterio) {
		String sql = "";
		if (criterio.equals("")) {
			sql = "SELECT * FROM reservas";
		}
		if (esNumero(criterio)) {
			Integer id = Integer.parseInt(criterio);
			sql = "SELECT *  FROM reservas WHERE id_reservas = " + id;
		}
		if (esDecimal(criterio)) {
			float valor = Float.parseFloat(criterio);
			sql = "SELECT *  FROM reservas WHERE valor = " + valor;
		}
		if (criterio.equalsIgnoreCase("tarjeta de credito") || criterio.equalsIgnoreCase("dinero en efectivo")
				|| criterio.equalsIgnoreCase("tarjeta de debito")) {
			sql = "SELECT *  FROM reservas WHERE forma_pago = '" + criterio + "' ";
		}

		List<ModeloReservacion> resultado = new ArrayList<>();
		try {
			final Statement statement = con.createStatement();
			try (statement) {
				final ResultSet resultset = statement.executeQuery(sql);
				try (resultset) {
					while (resultset.next()) {
						ModeloReservacion reservacion = new ModeloReservacion(resultset.getInt("id_reservas"),
								resultset.getDate("fecha_entrada"), resultset.getDate("fecha_salida"),
								resultset.getFloat("valor"), resultset.getString("forma_pago"));
						resultado.add(reservacion);

					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	private boolean esNumero(String crit) {
		try {
			Integer.parseInt(crit);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean esDecimal(String crit) {
		try {
			Float.parseFloat(crit);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int modificar(ModeloReservacion reservacion) {
		try {

			String sql = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ?  WHERE id_reservas = ?";
			final PreparedStatement statement = con.prepareStatement(sql);
			try(statement){
			statement.setDate(1, new java.sql.Date(reservacion.getFechaEntrada().getTime()));
			statement.setDate(2, new java.sql.Date(reservacion.getFechaSalida().getTime()));
			statement.setFloat(3, reservacion.getValorReservacion());
			statement.setString(4, reservacion.getFormaPago());
			statement.setInt(5, reservacion.getId());
			statement.execute();
			int updateCount = statement.getUpdateCount();
			return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public int eliminar(Integer id) {
		String sql = "DELETE FROM reservas WHERE id_reservas = ?";

		try {
			final PreparedStatement st = con.prepareStatement(sql);
			try (st){
			st.setInt(1, id);
			st.execute();

			int updateCount = st.getUpdateCount();
			return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
