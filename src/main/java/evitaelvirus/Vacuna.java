package evitaelvirus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Vacuna {
	private BufferedImage img;
	private int posicionX;
	private int posicionY;
	private int ancho;
	private int largo;
	private int timer;
	
	public Vacuna(int posicionX, int posicionY, int ancho, int largo, int timer) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.ancho = ancho;
		this.largo = largo;
		this.timer = timer;
		try {
			String path = Paths.get(Vacuna.class.getClassLoader().getResource("imagenes/vacuna.png").toURI()).toString();
		    this.img = ImageIO.read(new File(path));
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
    }
	
	 public void dibujarse(Graphics graphics) {
		 try {
	            graphics.drawImage(img, this.getPosicionX(), this.getPosicionY(), this.getAncho(), this.getLargo(), null);
	        } catch (Exception e1) {
	            throw new RuntimeException(e1);
	        }
	 }

	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	
	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	public void incrementarTimer() {
		this.timer++;
		//System.out.println(this.timer);
	}
	
}
		
