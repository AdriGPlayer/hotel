package com.latam.alura.hotel.modelo;

import java.util.Date;

public class ModeloReservacion {
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float valorReservacion;
	private String formaPago;
	
	
	public ModeloReservacion(Date fecha_inicio, Date fecha_salida, float valor, String forma_pago) {
		fechaEntrada = fecha_inicio;
		fechaSalida = fecha_salida;
		valorReservacion =  valor;
		formaPago = forma_pago;
	}
	
	public ModeloReservacion(Integer _id, Date fecha_inicio, Date fecha_salida, float valor_reservacion, String forma_pago) {
		fechaEntrada = fecha_inicio;
		fechaSalida = fecha_salida;
		valorReservacion = valor_reservacion;
		formaPago = forma_pago;
		id = _id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public float getValorReservacion() {
		return valorReservacion;
	}

	public void setValorReservacion(float valorReservacion) {
		this.valorReservacion = valorReservacion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	
	
	
	
}
