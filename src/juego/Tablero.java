package juego;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * Representación gráfica del Tablero del Juego Cuatro en Linea.
 * 
 */
public class Tablero {

	private static final int ALTO_FILA = 80;
	private static final int ANCHO_COLUMNA = 80;
	private static final int ALTURA_BOTON = 40;
	private static final double RADIO = Math.min(ALTO_FILA - 1, ANCHO_COLUMNA - 1) / 2;
	
	private CuatroEnLinea juego;
	private GridPane grilla;
	private Stage escenario;

	/**
	 * @POST: asocia el Tablero a 'nuevoJuego' y lo inicializa a partir de su estado. 
	 * 
	 * @param nuevoJuego
	 */
	public Tablero(CuatroEnLinea nuevoJuego) {
		
		juego = nuevoJuego;
		escenario = new Stage();
		grilla = new GridPane();
		
	}
	
	/**
	 * @POST: muestra el Tablero en pantalla.
	 */
	public void mostrar() {
		
		dibujarBotones();
		
		double ancho = juego.contarColumnas() * ANCHO_COLUMNA - 11;
		double alto = (juego.contarFilas() * ALTO_FILA) + ALTURA_BOTON - 15;
		
		Scene escena = new Scene(grilla, ancho, alto);

		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.setTitle(Aplicacion.TITULO);
		
		dibujar();

		escenario.show();
	}
	
	/**
	 * @POST: agrega los botones para soltar una ficha en cada columna del Tablero.
	 */
	private void dibujarBotones() {
		
		for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

			Button botonSoltarFicha = new Button("Lanzar");
			botonSoltarFicha.setMinHeight(ALTURA_BOTON);

			botonSoltarFicha.setOnAction(new SoltarFicha(this, juego, columna));
			botonSoltarFicha.setMinWidth(ANCHO_COLUMNA);
			grilla.add(botonSoltarFicha, columna - 1, 0);
		}
	}
	
	/**
	 * @POST: actualiza el Tablero a partir del estado del juego asociado.
	 */
	public void dibujar() {

		for (int fila = 1; fila <= juego.contarFilas(); fila++) {

			for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

				Casillero casillero = juego.obtenerCasillero(fila, columna);
				
				Circle dibujoCasillero = dibujarCasillero(casillero);
				
				grilla.add(dibujoCasillero, columna - 1, fila);
				
			}
		}
	}

	/**
	 * @POST: dibuja y devuelve el casillero dado.
	 * 
	 * @param casillero
	 * @return representaciÃ³n grÃ¡fica del Casillero.
	 */
	private Circle dibujarCasillero(Casillero casillero) {
		
		Circle dibujoCasillero = new Circle(RADIO, obtenerPintura(casillero));
		
		dibujoCasillero.setStroke(new Color(0.5, 0.5, 0.5, 1.0));
		dibujoCasillero.setScaleX(.90);
		dibujoCasillero.setScaleZ(.90);
		dibujoCasillero.setScaleY(.90);
		return dibujoCasillero;
	}

	/**
	 * @POST: determina la pintura a utilizar para 'casillero'.

	 * @param casillero
	 * @return pintura a utilizar para identificar el Casillero.
	 */
	private Paint obtenerPintura(Casillero casillero) {

		Paint pintura;

		switch (casillero) {
		
			case AMARILLO:
				pintura = Color.YELLOW;
				break;
				
			case ROJO:
				pintura = Color.RED;
				break;
				
			default:
				pintura = Color.WHITE;
		}

		return pintura;
	}

	/**
	 * @PRE : el juego asociado termina.
	 * @POST: muestra un mensaje indicando el resultado del juego.
	 */
	public void mostrarResultado() {

		Stage dialogo = new Stage();
		
		BorderPane panelGanador = new BorderPane();
		panelGanador.setPadding(new Insets(10.0));
		Text textoResultado;
		Font fuente = new Font(40.0);
		
		if (juego.hayGanador()) {
		
			textoResultado = new Text("Gana el jugador " + juego.obtenerGanador());
			
		} else {
			
			textoResultado = new Text("Empataron");
		}
		
		textoResultado.setFont(fuente);
		panelGanador.setCenter(textoResultado);
		
		Scene escenaGanador = new Scene(panelGanador);
		
		dialogo.setScene(escenaGanador);
		dialogo.initOwner(escenario);
		dialogo.initModality(Modality.WINDOW_MODAL);
		dialogo.setResizable(false);
		
		dialogo.showAndWait();
	}
}
