package com.latam.alura.hotel.modelo;

import java.util.Date;

public class ModeloHuesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer IdReservacion;
	
	public ModeloHuesped(Integer _id, String _nombre, String _apellido, Date _fechaNacimiento,
			String _nacionalidad, String _telefono, Integer _IdReservacion) {
		id = _id;
		nombre = _nombre;
		apellido = _apellido;
		fechaNacimiento = _fechaNacimiento;
		nacionalidad = _nacionalidad;
		telefono = _telefono;
		IdReservacion = _IdReservacion;
	} 
	public ModeloHuesped(String _nombre, String _apellido, Date _fechaNacimiento,
			String _nacionalidad, String _telefono, Integer _IdReservacion) {

		nombre = _nombre;
		apellido = _apellido;
		fechaNacimiento = _fechaNacimiento;
		nacionalidad = _nacionalidad;
		telefono = _telefono;
		IdReservacion = _IdReservacion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getIdReservacion() {
		return IdReservacion;
	}
	public void setIdReservacion(Integer idReservacion) {
		IdReservacion = idReservacion;
	} 
	
	
	
	
	
	
}
