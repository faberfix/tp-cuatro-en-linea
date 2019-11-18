package juego;

/**
 * 
 * Juego Cuatro en Línea
 * 
 * 
 * @AXIOMAS:
 * 
 * @REGLAS:
 * 
 * 		...
 *
 */
public class CuatroEnLinea {
	
	
	private String jugadorRojo, jugadorAmarillo, jugadorActual;
	private Casillero [][] matrizTablero;
	
	
	/**
	 * @PRE : 'filas' y 'columnas' son mayores o iguales a 4.
	 * @POST : empieza el juego entre el jugador que tiene fichas rojas, identificado como 
	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 		 'jugadorAmarillo'. 
	 * 		 Todo el tablero está vacío.
	 * 
	 * @param filas : cantidad de filas que tiene el tablero.
	 * @param columnas : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo : nombre del jugador con fichas amarillas.
	 */		
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {
		
			if(!validadoresEnSerie(filas,columnas,jugadorRojo,jugadorAmarillo)){
				
				this.matrizTablero = new Casillero[columnas][filas];
				this.jugadorRojo = jugadorRojo;
				this.jugadorAmarillo = jugadorAmarillo;
				this.jugadorActual = jugadorRojo;
				tableroVacio();
				
			}
			
	}
	
	
	
	/**
	 * @POST: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {
		
		return matrizTablero[0].length;
	}

	/**
	 * @POST: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		return matrizTablero.length;
	}

	/**
	 * @PRE : fila está en el intervalo [1, contarFilas()],
	 * 		 columnas está en el intervalo [1, contarColumnas()].
	 * @POST: indica qué ocupa el casillero en la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
		
		return matrizTablero[contarColumnas()-1][contarFilas()-1];
		
	}
	
	/**
	 * @PRE : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
	 * @POST: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {
		
		if(!termino()){
			for(int i = 0; i < this.matrizTablero.length; i++){
				for(int j = 0; j < this.matrizTablero[i].length; j++){
					
				}
			}
		}
	}
	
	/**
	 * @POST: indica si el juego terminó porque uno de los jugadores
	 * 		 ganó o no existen casilleros vacíos.
	 */
	public boolean termino() {
		
		return false;
	}

	/**
	 * @POST: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		
		return false;
	}

	/**
	 * @PRE : el juego terminó.
	 * @POST: devuelve el nombre del jugador que ganó el juego.
	 */
	public String obtenerGanador() {
		
		return null;
	}
	
	/**
	 * @POST: crea la matriz tablero vacia
	 * 
	 */
	private void tableroVacio(){
		for(int i = 0; i < this.matrizTablero.length; i++){
			for(int j = 0; j < this.matrizTablero[i].length; j++){
				this.matrizTablero[i][j] = Casillero.VACIO;
			}
		}
	}
	/**
	 * @POST: valida los datos impuesto por el juego
	 * 
	 * @param filas
	 * @param columnas
	 * @param jugadorRojo
	 * @param jugadorAmarillo
	 * @return
	 */
	private boolean validadoresEnSerie(int filas, int columnas, String jugadorRojo, String jugadorAmarillo){
		
		if (filas < 4 || columnas < 4){
			throw new Error("Las filas y columnas deben ser mayores o iguales a 4.");
		}
		
		if (filas > 15 || columnas > 15){
			throw new Error("Las filas y columnas deben ser menores o iguales a 15.");
		}
		
		if (jugadorRojo.isEmpty()){
			throw new Error("Falta el nombre del jugador Rojo.");
		}
		
		if (jugadorAmarillo.isEmpty()){
			throw new Error("Falta el nombre del jugador Amarillo.");
		}
		
		if (jugadorRojo.equalsIgnoreCase(jugadorAmarillo)){
			throw new Error("No pueden tener el mismo los jugadores.");
		}
		
		return false;
	}
}
