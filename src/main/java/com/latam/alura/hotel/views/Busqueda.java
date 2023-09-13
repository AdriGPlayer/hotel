package com.latam.alura.hotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.latam.alura.hotel.controller.*;
import com.latam.alura.hotel.modelo.*;


import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Busqueda<panel> extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloRerservas;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private boolean reservacion = true, huesped = false;
	private HuespedController huespedController;
	private ReservacionController reservacionController;
	private JButton btnEditar;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservacionController = new ReservacionController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane seleccion = (JTabbedPane) e.getSource();
				if (seleccion.getSelectedIndex() == 0) {
					reservacion = true;
					huesped = false;
					limpiarTablaReservacion();
					mostrarTablaReservacion(txtBuscar.getText());

				} else if (seleccion.getSelectedIndex() == 1) {
					reservacion = false;
					huesped = true;
					limpiarTablaHuesped();
					mostrarTablaHuesped(txtBuscar.getText());
					
				}

			}

			

		});

		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return colIndex != 0 && colIndex != 1 && colIndex != 3;
			}
		};
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		
		
		modeloRerservas = (DefaultTableModel) tbReservas.getModel();
		modeloRerservas.addColumn("Numero de Reserva");
		modeloRerservas.addColumn("Fecha Check In");
		modeloRerservas.addColumn("Fecha Check Out");
		modeloRerservas.addColumn("Valor");
		modeloRerservas.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		//mostrarTablaReservacion(txtBuscar.getText());
		
		
		tbHuespedes = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return colIndex != 0 && colIndex != 6;
			}
		};
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		//mostrarTablaHuesped(txtBuscar.getText());
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(reservacion == true) {
					modeloRerservas.setRowCount(0);
					mostrarTablaReservacion(txtBuscar.getText());
					
				}
				if(huesped == true) {
					modeloHuesped.setRowCount(0);
					mostrarTablaHuesped(txtBuscar.getText());
					
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		btnEditar = new JButton();
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reservacion == true) {
					modifificarReservacion();
					limpiarTablaReservacion();
					mostrarTablaReservacion(txtBuscar.getText());
				}
				if (huesped == true) {
					 	modificarHuesped();
				}
			}

			
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reservacion) {
					 eliminarReservacion();
					 limpiarTablaReservacion();
					 mostrarTablaReservacion(txtBuscar.getText());
				}else if (huesped) {
					eliminarHuesped();
					limpiarTablaHuesped();
					mostrarTablaHuesped(txtBuscar.getText());
				}
			}

			

			
		});

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void mostrarTablaReservacion(String criterio) {
		
		List<ModeloReservacion> tablaReservacion = reservacionController.mostrar(criterio);
		tablaReservacion.forEach(res -> modeloRerservas.addRow(new Object[] {
				res.getId(),
				res.getFechaEntrada(),
				res.getFechaSalida(),
				res.getValorReservacion(),
				res.getFormaPago()		
		}));
		
	}
	private void mostrarTablaHuesped(String criterio) {
		List<ModeloHuesped> tablaHuesped = huespedController.mostrar(criterio);
		tablaHuesped.forEach(hues -> modeloHuesped.addRow(new Object[] {
				hues.getId(),
				hues.getNombre(),
				hues.getApellido(),
				hues.getFechaNacimiento(),
				hues.getNacionalidad(),
				hues.getTelefono(),
				hues.getIdReservacion()
		}));
	}
	private void limpiarTablaHuesped() {
		
		modeloHuesped.getDataVector().clear();
	}

	private void limpiarTablaReservacion() {
		modeloRerservas.getDataVector().clear();
	}
	private void modifificarReservacion() {
		if(tieneFilaElegida()) {
			JOptionPane.showMessageDialog(null, "Por favor elige una fila");
			return;
		}
		Optional.ofNullable(modeloRerservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			try {
			Integer id = Integer.valueOf(modeloRerservas.getValueAt(tbReservas.getSelectedRow(),0).toString());
			Date fechaEntrada;
			Date fechaSalida;
			fechaEntrada = new SimpleDateFormat("yyyy-MM-dd").parse( modeloRerservas.getValueAt(tbReservas.getSelectedRow(), 1).toString());
			
			fechaSalida = new SimpleDateFormat("yyyy-MM-dd").parse( modeloRerservas.getValueAt(tbReservas.getSelectedRow(), 2).toString());
		
			
			String formaPago = (String) modeloRerservas.getValueAt(tbReservas.getSelectedRow(), 4);
			Date fechaActual = new Date();
			if (fechaSalida.getTime() < fechaActual.getTime()) {
				JOptionPane.showMessageDialog(null, "No se puede modificar a una fecha anterior");
				return;
			}
			
			float valor = Float.valueOf(modeloRerservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());
			long digerenciaDias = fechaSalida.getTime() - fechaEntrada.getTime();
			TimeUnit time = TimeUnit.DAYS;
			Long dif = time.convert(digerenciaDias, TimeUnit.MILLISECONDS);
			float valorCalculado = (float)((dif +1 )*15.0);
			if (Float.compare(valorCalculado, valor) == 0) {
				valor = Float.valueOf(modeloRerservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());
			}else if (Float.compare(valorCalculado, valor) < 0) {
				valor = valorCalculado;
			} else if (Float.compare(valorCalculado, valor) > 0) {
				valor = valorCalculado;
			}
			
			ModeloReservacion reservacion = new ModeloReservacion(id, fechaEntrada, fechaSalida, valor, formaPago);
			int filaModificada;
			filaModificada = reservacionController.modificar(reservacion);
			JOptionPane.showMessageDialog(null, "Item " + filaModificada + " modificado con exito");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
		},() -> JOptionPane.showMessageDialog(null,"Elige un dato a modificar"));
		
	}

	private boolean tieneFilaElegida() {
		
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}
	private boolean filaElegidaHuesped() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}
	
	
	private void modificarHuesped() {
		if (filaElegidaHuesped()) {
			JOptionPane.showMessageDialog(null, "Porfavor elige un valor a modificar");
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			try {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
			String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
			Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),
					3).toString());
			String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
			String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
			Integer idReservas = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			int filaModificada;
			ModeloHuesped huesped = new ModeloHuesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReservas);
			filaModificada = huespedController.modificar(huesped);
			JOptionPane.showMessageDialog(null, "Item " + filaModificada + " modificado con exito");
			}catch (ParseException e) {
				e.printStackTrace();
			}
		}, () -> JOptionPane.showMessageDialog(null, "Porfavor elige una fila a modificar"));
		
	}
	
	private void eliminarReservacion() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(null, "Elije una fila a eliminar");
		}
		Optional.ofNullable(modeloRerservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloRerservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			int cantidadEliminadas = reservacionController.eliminar(id);
			modeloRerservas.removeRow(tbReservas.getSelectedRow());
			JOptionPane.showMessageDialog(null, "item " + cantidadEliminadas + " eliminada con exito");
			
		}, () -> 	JOptionPane.showMessageDialog(null, "Por favor elije una fila"));
	
	}
	private void eliminarHuesped() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(null, "Elije una fila a eliminar");
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			int cantidadEliminadas = huespedController.eliminar(id);
			modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
			JOptionPane.showMessageDialog(null, "item " + cantidadEliminadas + " eliminada con exito");
			
		}, () -> 	JOptionPane.showMessageDialog(null, "Por favor elije una fila"));
		
	}
}
