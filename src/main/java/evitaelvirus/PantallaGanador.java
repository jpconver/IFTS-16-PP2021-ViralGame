package evitaelvirus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaGanador extends PantallaImagen {
	
	private int numeroDosis;

	 public PantallaGanador(int ancho, int largo, String resource) {
		 super(ancho, largo, resource);
	 }

	public void dibujarse(Graphics graphics) {
		 super.dibujarse(graphics);
		 if (this.numeroDosis == 1) {
			 String mensajePuntos = "ra";
			 mostrarMensaje(graphics, "Conseguiste la " + (this.numeroDosis) + mensajePuntos + " dosis :)");
			 presionarTeclaMensaje(graphics);
		 } else if (this.numeroDosis == 2) {
			 String mensajePuntos = "da";
			 mostrarMensaje(graphics, "Conseguiste la " + (this.numeroDosis) + mensajePuntos + " dosis :)");
			 presionarTeclaMensaje(graphics);
		 } else {
			 mostrarMensaje(graphics, "CONSEGUISTE INMUNIDAD TOTAL");
		 }
	 }

	 private void mostrarMensaje(Graphics g, String mensaje) {
		 g.setColor(Color.black);
		 g.setFont(new Font("Arial black", 30, 16));
		 g.drawString(mensaje, 10, 70);
	 }
	 
	 private void presionarTeclaMensaje(Graphics g) {
		 g.setColor(Color.black);
		 g.setFont(new Font("Arial black", 30, 16));
		 g.drawString("Presione una tecla para continuar", 10, 550);
	 }
	 
	 public int getNumeroDosis() {
		return numeroDosis;
	 }

	public void setNumeroDosis(int numeroDosis) {
		this.numeroDosis = numeroDosis;
	}
}
