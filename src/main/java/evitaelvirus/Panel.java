package evitaelvirus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener {
	private final static int PANTALLA_INICIO = 0;
    private final static int PANTALLA_JUEGO_LEVEL1 = 1;
    private final static int PANTALLA_JUEGO_LEVEL2 = 2;
    private final static int PANTALLA_JUEGO_LEVEL3 = 3;
    private final static int PANTALLA_PERDEDOR = 4;
    private final static int PANTALLA_GANADOR = 5;
    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;
    private PantallaImagen fondo;
    private Persona personita;
    private List<Enemigo> enemigos;
    private int nuevoEnemigoTimer;
    private int contadorEnemigos;
    private final static int FRECUENCIA_ENEMIGOS_LEVEL1 = 400;
    private final static int FRECUENCIA_ENEMIGOS_LEVEL2 = 200;
    private final static int FRECUENCIA_ENEMIGOS_LEVEL3= 100;
    private Vacuna vacuna;
    private final static int TIEMPO_VACUNA = 500;
    private Vidas vidas;
    private int cantidadVidas;
    private Puntaje puntos;
    private int cantidadPuntos;
    private int pantallaActual;
    private int contadorLevels;
    private PantallaImagen pantallaInicio;
    private PantallaImagen pantallaPerdedor;
    private PantallaGanador pantallaGanador;
    private PantallaImagen pantallaFinal;
    private Sonidos sonidos;
    private int contadorMovimientoEnemigos = 0;
    
    public Panel(int anchoJuego, int largoJuego) {
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
        inicializarPantallas();
        this.pantallaActual = PANTALLA_INICIO;
        cargarSonidos();
        sonidos.repetirSonido("intro");
        inicializarVidas();
        inicializarPuntos();
    }
    
    public void inicializarJuego(){
    	inicializarEnemigos();
    	inicializarVacuna();
    	inicializarPersona();
    }
    
    public void inicializarPantallas() {
    	this.fondo = new PantallaImagen(anchoJuego, largoJuego, "imagenes/fondoCiudad.jpg");
    	this.pantallaInicio = new PantallaImagen(anchoJuego, largoJuego, "imagenes/pantallaInicio.png");
        this.pantallaPerdedor = new PantallaImagen(anchoJuego, largoJuego, "imagenes/pantallaPerdedor.png");
        this.pantallaGanador = new PantallaGanador(anchoJuego, largoJuego, "imagenes/pantallaGanador.png");
        this.pantallaFinal = new PantallaGanador(anchoJuego, largoJuego, "imagenes/GameOver.jpg");
    }
    
    public void inicializarPersona() {
    	personita = new Persona(100,100,0,0,30,30);
    }
    
    
    public void inicializarEnemigos() {
    	enemigos = new ArrayList<Enemigo>();
    	enemigos.add(new EnemigoVioleta(new Random().nextInt(anchoJuego),new Random().nextInt(largoJuego),0,0, 40, 40));
    	enemigos.add(new EnemigoVerde(new Random().nextInt(anchoJuego),new Random().nextInt(largoJuego),0,0, 40, 40));
    	
    }
    
    public void agregarEnemigos(int frecuenciaAparicionEnemigos) {
    	if (nuevoEnemigoTimer >= frecuenciaAparicionEnemigos) {
    		contadorEnemigos++;
    		if (contadorEnemigos%2 == 0) {
    			enemigos.add(new EnemigoVioleta(new Random().nextInt(anchoJuego),new Random().nextInt(largoJuego),0,0, 40, 40));
    		} else {
    			enemigos.add(new EnemigoVerde(new Random().nextInt(anchoJuego),new Random().nextInt(largoJuego),0,0, 40, 40));
    		}
    		nuevoEnemigoTimer = 0;
    	}
    }
    
    private void inicializarVacuna() {
    	this.vacuna = new Vacuna(new Random().nextInt(anchoJuego-100),new Random().nextInt(largoJuego-100),70,70,0);
    }
    
    private void inicializarVidas() {
    	cantidadVidas = 3;
    	vidas = new Vidas(380,50,new Font("Arial", 10,20),Color.black,cantidadVidas);
    }
    
    private void inicializarPuntos() {
    	cantidadPuntos = 0;
    	puntos = new Puntaje(320,72,new Font("Arial", 10,15),Color.black,cantidadPuntos);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }
    
    @Override
    public void run() {
        while (true) {
            actualizarAmbiente();
            dibujar();
            esperar(10);
        }
    }
    
    private void actualizarAmbiente() {
    	if (pantallaActual == PANTALLA_JUEGO_LEVEL1) {
    		verificarChoqueBordesPantalla();
        	moverPersona();
        	puntos.incrementarPuntaje();
        	nuevoEnemigoTimer++;
        	vacuna.incrementarTimer();
        	moverEnemigo();
        	verificarChoqueConEnemigo();
        	verificarChoqueConVacuna();
        	agregarEnemigos(FRECUENCIA_ENEMIGOS_LEVEL1);
    	}
    	if (pantallaActual == PANTALLA_JUEGO_LEVEL2) {
    		verificarChoqueBordesPantalla();
        	moverPersona();
        	puntos.incrementarPuntaje();
        	nuevoEnemigoTimer++;
        	vacuna.incrementarTimer();
        	moverEnemigo();
        	verificarChoqueConEnemigo();
        	verificarChoqueConVacuna();
        	agregarEnemigos(FRECUENCIA_ENEMIGOS_LEVEL2);
    	}
    	if (pantallaActual == PANTALLA_JUEGO_LEVEL3) {
    		verificarChoqueBordesPantalla();
        	moverPersona();
        	puntos.incrementarPuntaje();
        	nuevoEnemigoTimer++;
        	vacuna.incrementarTimer();
        	moverEnemigo();
        	verificarChoqueConEnemigo();
        	verificarChoqueConVacuna();
        	agregarEnemigos(FRECUENCIA_ENEMIGOS_LEVEL3);
    	}
    }
    
    private void moverPersona() {
    	 personita.setPosicionX(personita.getPosicionX()+personita.getVelocidadX());
    	 personita.setPosicionY(personita.getPosicionY()+personita.getVelocidadY());
    }
    
    private void moverEnemigo() {
    	
    	if (contadorMovimientoEnemigos >= 10 ) {
	    	for (Enemigo enemigo : enemigos) {
	    		if (personita.getPosicionX() > enemigo.getPosicionX()) {
	    			enemigo.setPosicionX(enemigo.getPosicionX()+1);
	    		} else if (personita.getPosicionX() < enemigo.getPosicionX()) {
	    			enemigo.setPosicionX(enemigo.getPosicionX()-1);
	    		}
	    		if (personita.getPosicionY() > enemigo.getPosicionY()) {
	    			enemigo.setPosicionY(enemigo.getPosicionY()+1);
	    		} else if (personita.getPosicionY() < enemigo.getPosicionY()) {
	    			enemigo.setPosicionY(enemigo.getPosicionY()-1);
	    		}
	    	}
	    	contadorMovimientoEnemigos = 0;
    	}
    	contadorMovimientoEnemigos++;
    }
    
    private void verificarChoqueConEnemigo() {
    	for (Enemigo enemigo : enemigos) {
    		if (hayColision(
    				personita.getPosicionX(), personita.getPosicionY(), personita.getAncho(), personita.getLargo(),
    				enemigo.getPosicionX(), enemigo.getPosicionY(), enemigo.getAncho(), enemigo.getLargo()
    			)) {
    			sonidos.tocarSonido("sad");
    			vidas.perderVida();
    			pantallaActual = PANTALLA_PERDEDOR;
    			esperar(6);
    		}
    	}
    }
   
    private void verificarChoqueConVacuna() {
    	if (vacuna.getTimer() >= TIEMPO_VACUNA && 
    		hayColision(
    				personita.getPosicionX(), personita.getPosicionY(), personita.getAncho(), personita.getLargo(),
        			vacuna.getPosicionX(), vacuna.getPosicionY(), vacuna.getAncho(), vacuna.getLargo()
        	)) {
        		contadorLevels = contadorLevels + 1;
        		vacuna.setTimer(0);
        		pantallaActual = PANTALLA_GANADOR;
        		sonidos.tocarSonido("cheers");
        	}
    }
    
    private void verificarChoqueBordesPantalla() {
    	if (personita.getPosicionX() <= 5 || personita.getPosicionX() >= anchoJuego-5) {
    		personita.setVelocidadX(personita.getVelocidadX()*(-2));
    	}
    	if (personita.getPosicionY() <= 5 || personita.getPosicionY() >= largoJuego-5) {
    		personita.setVelocidadY(personita.getVelocidadY()*(-2));
    	}
    }
    
    private void dibujar() {
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pantallaActual == PANTALLA_INICIO) {
        	dibujarInicioJuego(g);
        } else if (pantallaActual == PANTALLA_JUEGO_LEVEL1 || pantallaActual == PANTALLA_JUEGO_LEVEL2 || pantallaActual == PANTALLA_JUEGO_LEVEL3) {
        	dibujarJuego(g);
    	} else if (pantallaActual == PANTALLA_PERDEDOR) {
    		dibujarPantallaPerdedorOFinal(g);
    	} else if (pantallaActual == PANTALLA_GANADOR) {
    		dibujarPantallaGanador(g);
    	}
    }
    
    public void dibujarJuego(Graphics graphics) {
    	dibujarFondo(graphics);
    	dibujarVidas(graphics);
    	dibujarPuntos(graphics);
    	dibujarVacuna(graphics);
        dibujarEnemigo(graphics);
        dibujarPersona(graphics);
    }
    
    public void dibujarPersona(Graphics graphics) {
    	personita.dibujarse(graphics);
    }
    
    public void dibujarEnemigo(Graphics graphics) {
    	for (Enemigo enemigo : enemigos) {
        	enemigo.dibujarse(graphics);
        }
    }
    
    public void dibujarVacuna(Graphics graphics) {
    	if (vacuna.getTimer() >= TIEMPO_VACUNA) {
    		vacuna.dibujarse(graphics);
    	}
    }
    
    public void dibujarFondo(Graphics graphics) {
    	fondo.dibujarse(graphics);
    }
    
    public void dibujarInicioJuego(Graphics graphics) {
    	pantallaInicio.dibujarse(graphics);
    }
    
    public void dibujarPantallaPerdedorOFinal(Graphics graphics) {
    	if (vidas.getVidas() > 0) {
    		pantallaPerdedor.dibujarse(graphics);
    	} else {
    		pantallaFinal.dibujarse(graphics);
    	}
    }
    
    public void dibujarPantallaGanador(Graphics graphics) {
    	pantallaGanador.setNumeroDosis(contadorLevels);
    	pantallaGanador.dibujarse(graphics);
    }
    
    public void dibujarVidas(Graphics graphics) {
    	vidas.dibujarse(graphics);
    }
    
    public void dibujarPuntos(Graphics graphics) {
    	puntos.dibujarse(graphics);
    }
    
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    	
    	 if (pantallaActual == PANTALLA_INICIO && contadorLevels == 0) {
    		inicializarJuego();
            pantallaActual = PANTALLA_JUEGO_LEVEL1;
    	 }
    	 if (pantallaActual == PANTALLA_GANADOR && contadorLevels == 1) {
    		 esperar(3000);
    		 inicializarJuego();
    		 pantallaActual = PANTALLA_JUEGO_LEVEL2;
    	 }
    	 if (pantallaActual == PANTALLA_GANADOR && contadorLevels == 2) {
    		 esperar(3000);
    		 inicializarJuego();
    		 pantallaActual = PANTALLA_JUEGO_LEVEL3;
    	 }
    	 if (pantallaActual == PANTALLA_GANADOR && contadorLevels == 3 || pantallaActual == PANTALLA_PERDEDOR && vidas.getVidas() == 0 ) {
    		 esperar(3000);
    		 this.contadorLevels = 0;
    		 inicializarJuego();
    		 vidas.reiniciarVidas();
    		 puntos.setPuntosEnCero();
    		 pantallaActual = PANTALLA_INICIO;
    	 }
    	 if (pantallaActual == PANTALLA_PERDEDOR) {
    		 esperar(3000);
    		 if (contadorLevels == 0) {
    			 inicializarJuego();
    			 pantallaActual = PANTALLA_JUEGO_LEVEL1;
    		 } else if (contadorLevels == 1) {
    			 inicializarJuego();
    			 pantallaActual = PANTALLA_JUEGO_LEVEL2;
    		 } else if (contadorLevels == 2) {
    			 inicializarJuego();
    			 pantallaActual = PANTALLA_JUEGO_LEVEL3;
    		 }
    	 }
        if (arg0.getKeyCode() == 39) {
        	personita.setVelocidadX(5);
        }
        if (arg0.getKeyCode() == 37) {
        	personita.setVelocidadX(-5);
        }
        
        if (arg0.getKeyCode() == 38) {
        	personita.setVelocidadY(-5);
        }
        
        if (arg0.getKeyCode() == 40) {
        	personita.setVelocidadY(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	personita.setVelocidadX(0);
    	personita.setVelocidadY(0);
    }
    
    private void cargarSonidos() {
        try {
            sonidos = new Sonidos();
            sonidos.agregarSonido("intro", "sonidos/waduwadu.wav");
            sonidos.agregarSonido("cheers", "sonidos/cheers.wav");
            sonidos.agregarSonido("sad", "sonidos/sad.wav");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
    
    private boolean hayColision (
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        if (
            haySolapamientoDeRango(
                elemento1PosicionX,
                elemento1PosicionX+elemento1Ancho,
                elemento2PosicionX,
                elemento2PosicionX+elemento2Ancho)
            &&     
            haySolapamientoDeRango(
                elemento1PosicionY,
                elemento1PosicionY+elemento1Largo,
                elemento2PosicionY,
                elemento2PosicionY+elemento2Largo)) {
            return true;
        }
        return false;
    }
    
    private boolean haySolapamientoDeRango(int a, int b, int c, int d) {
        if (a < d && b > c  ) {
            return true;
        }
        return false;
    }

}
