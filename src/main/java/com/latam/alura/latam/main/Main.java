package com.latam.alura.latam.main;

import javax.swing.JFrame;

import com.latam.alura.hotel.views.MenuPrincipal;

public class Main {
	public static void main(String[] args) {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
	}
	
}
