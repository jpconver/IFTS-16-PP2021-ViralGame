package evitaelvirus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Persona extends Personaje {
	private BufferedImage img;
    
    public Persona (int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
		try {
			String path = Paths.get(Persona.class.getClassLoader().getResource("imagenes/carita.png").toURI()).toString();
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
}
