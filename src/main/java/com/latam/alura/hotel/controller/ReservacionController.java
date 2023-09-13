package com.latam.alura.hotel.controller;


import java.util.List;

import com.latam.alura.hotel.dao.*;
import com.latam.alura.hotel.factory.*;
import com.latam.alura.hotel.modelo.*;


public class ReservacionController {
	private ReservaDAO reservaDAO;
	
	public ReservacionController() {
			this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}

	public Integer guardaReservacion(ModeloReservacion reservacion) {
		return reservaDAO.guardar(reservacion);
		
	}

	public List<ModeloReservacion> mostrar(String criterio) {		
		return reservaDAO.mostrar(criterio);
	}

	public int modificar(ModeloReservacion reservacion) {
		
		return reservaDAO.modificar(reservacion);
	}

	public int eliminar(Integer id) {
		
		return reservaDAO.eliminar(id);
	}
}
