package com.latam.alura.hotel.controller;

import com.latam.alura.hotel.factory.*;
import com.latam.alura.hotel.modelo.*;

import java.util.List;

import com.latam.alura.hotel.dao.*;

public class HuespedController {
	private HuespedDAO  huespedDao;
	
	public HuespedController() {
		huespedDao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
		
	}

	public void guardar(ModeloHuesped huesped) {
		huespedDao.guardarHuesped(huesped);
		
	}

	public List<ModeloHuesped> mostrar(String criterio) {
		
		return huespedDao.mostrar(criterio);
	}

	public int modificar(ModeloHuesped huesped) {
	
		return huespedDao.modificar(huesped);
	}

	public  int eliminar(Integer id) {
		return huespedDao.eliminar(id);
	}
}
