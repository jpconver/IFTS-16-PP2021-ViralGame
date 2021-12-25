package evitaelvirus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class EnemigoVerde extends Enemigo {
	private BufferedImage img;
	
	public EnemigoVerde (int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
		String resource = "imagenes/virusaverde.png";
		try {
			String path = Paths.get(EnemigoVerde.class.getClassLoader().getResource(resource).toURI()).toString();
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



