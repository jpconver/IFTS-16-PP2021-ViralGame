package evitaelvirus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Puntaje {

    private int posicionX;
    private int posicionY;
    private Font font;
    private Color color;
    private int puntos;
    private double puntosDouble = 0;

	public Puntaje(int posicionX, int posicionY, Font font, Color color, int puntos) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.font = font;
        this.color = color;
        this.puntos = puntos;
    }
	
	public void setPuntosEnCero() {
		this.puntosDouble = 0;
	}

    public void dibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Días sobreviviendo " + String.valueOf(puntos), posicionX, posicionY);
    }

    public void incrementarPuntaje() {
    	puntosDouble = puntosDouble + 0.1;
    	puntos = (int)(puntosDouble);
    }

    public int getPuntos() {
        return puntos;
    }

}
