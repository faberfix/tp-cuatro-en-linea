package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * 		...
 *
 */
public class CuatroEnLinea {
	private String jugador1, jugador2, jugadorActual;
	private Casillero[][] casilleros;
	private int filas,columnas;
	
	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
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
		if(datosCorrectos(filas,columnas,jugadorRojo,jugadorAmarillo)){
			this.casilleros = new Casillero[filas][columnas];
			this.jugador1=jugadorRojo;
			this.jugador2=jugadorAmarillo;
			this.jugadorActual=jugadorRojo;
			tableroVacio();
			this.filas=filas;
			this.columnas=columnas;
		}
	}
	private boolean datosCorrectos(int filas, int columnas, String jugadorRojo, String jugadorAmarillo){
		if((filas>=4)&&(columnas>=4)&&(filas<=12)&&(filas<=12)&&(!jugadorRojo.isEmpty())&&(!jugadorAmarillo.isEmpty())){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void tableroVacio(){
		for(int i=0;i<casilleros.length;i++){
			for(int j=0; j<casilleros[i].length;j++){
				casilleros[i][j]=Casillero.VACIO;
			}
		}
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {
		
		return this.filas;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		
		return  this.columnas;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()],
	 * 		 columnas está en el intervalo [1, contarColumnas()].
	 * post: indica qué ocupa el casillero en la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
		return casilleros[fila-1][columna-1];
	}
	
	/**
	 * pre : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {
		int i= contarFilas()-1;
		
		if(!termino()){
			while(i>=0){
				if(this.casilleros[i][columna-1]==Casillero.VACIO){
					this.casilleros[i][columna-1]=turnoJugador();
					break;
				}
				i--;
			}
		}
		
		
	}
	
	private Casillero turnoJugador(){
		if(jugadorActual.equals(jugador1)){
			jugadorActual=jugador2;
			return Casillero.ROJO;
		}
		else{
			jugadorActual=jugador1;
			return Casillero.AMARILLO;
		}
	}
	
	/**
	 * post: indica si el juego terminó porque uno de los jugadores
	 * 		 ganó o no existen casilleros vacíos.
	 */
	public boolean termino() {
		if(casillerosLlenos()|| hayGanador()){
			return true;
		}
		
		return false;
	}
	
	private boolean casillerosLlenos(){
		for(int i=0;i<casilleros.length;i++){
			for(int j=0;j<casilleros[i].length;j++){
				if(casilleros[i][j]==Casillero.VACIO){
					return false;
				}
			}
		}
		return true; 
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		if(lineaHorizontalJ1() || lineaHorizontalJ2() || lineaVerticalJ1() || lineaVerticalJ2()||diagonalDecrecienteJ1()||diagonalCrecienteJ2() || diagonalCrecienteJ1() || diagonalDecrecienteJ2()){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * pre : el juego terminó.
	 * post: devuelve el nombre del jugador que ganó el juego.
	 */
	public String obtenerGanador() {
		if(lineaHorizontalJ1() || lineaVerticalJ1() || diagonalDecrecienteJ1() || diagonalCrecienteJ1()){
			return jugador1;
		}
		else if(lineaHorizontalJ2() || lineaVerticalJ2() || diagonalCrecienteJ2() || diagonalDecrecienteJ2()){
			return jugador2;
		}
		
		return null;
	}
	/*
	 * @POST: Devuelve si el jugador1 ha completado una linea horizontal.
	 */
	private boolean lineaHorizontalJ1(){
		boolean termino=false;
		//Recorre todas las columnas, excepto tres, para comparar con las siguientes.
		for(int i=0;i<(this.filas);i++){
			for(int j=0;j<this.columnas-3;j++){
				if((casilleros[i][j]==casilleros[i][j+1])&&(casilleros[i][j+1]==casilleros[i][j+2])&&(casilleros[i][j+2]==casilleros[i][j+3])&&(casilleros[i][j]==Casillero.ROJO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean lineaHorizontalJ2(){
		boolean termino=false;
		//Recorre todas las columnas, excepto tres, para comparar con las siguientes.
		for(int i=0;i<(this.filas);i++){
			for(int j=0;j<this.columnas-3;j++){
				if((casilleros[i][j]==casilleros[i][j+1])&&(casilleros[i][j+1]==casilleros[i][j+2])&&(casilleros[i][j+2]==casilleros[i][j+3])&&(casilleros[i][j]==Casillero.AMARILLO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean lineaVerticalJ1(){
		boolean termino=false;
		for(int i=0;i<this.filas-3;i++){
			for(int j=0; j<this.columnas;j++){
				if((casilleros[i][j]==casilleros[i+1][j])&&(casilleros[i+1][j]==casilleros[i+2][j])&&(casilleros[i+2][j]==casilleros[i+3][j])&&(casilleros[i][j]==Casillero.ROJO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean lineaVerticalJ2(){
		boolean termino=false;
		for(int i=0;i<this.filas-3;i++){
			for(int j=0; j<this.columnas;j++){
				if((casilleros[i][j]==casilleros[i+1][j])&&(casilleros[i+1][j]==casilleros[i+2][j])&&(casilleros[i+2][j]==casilleros[i+3][j])&&(casilleros[i][j]==Casillero.AMARILLO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean diagonalDecrecienteJ1(){
		boolean termino=false;
		for(int i=0;i<this.filas-3;i++){
			for(int j=0;j<this.columnas-3;j++){
				if((casilleros[i][j]==casilleros[i+1][j+1])&&(casilleros[i+1][j+1]==casilleros[i+2][j+2])&&(casilleros[i+2][j+2]==casilleros[i+3][j+3])&&(casilleros[i][j]==Casillero.ROJO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean diagonalCrecienteJ2(){
		boolean termino=false;
		for(int i=this.filas-1;i>=3;i--){
			for(int j=0;j<this.columnas-3;j++){
				if((casilleros[i][j]==casilleros[i-1][j+1])&&(casilleros[i-1][j+1]==casilleros[i-2][j+2])&&(casilleros[i-2][j+2]==casilleros[i-3][j+3])&&(casilleros[i][j]==Casillero.AMARILLO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean diagonalDecrecienteJ2(){
		boolean termino=false;
		for(int i=0;i<this.filas-3;i++){
			for(int j=0;j<this.columnas-3;j++){
				if((casilleros[i][j]==casilleros[i+1][j+1])&&(casilleros[i+1][j+1]==casilleros[i+2][j+2])&&(casilleros[i+2][j+2]==casilleros[i+3][j+3])&&(casilleros[i][j]==Casillero.AMARILLO)){
					termino=true;
				}
			}
		}
		return termino;
	}
	
	private boolean diagonalCrecienteJ1(){
		boolean termino=false;
		for(int i=this.filas-1;i>=3;i--){
			for(int j=0;j<this.columnas-3;j++){
				if((casilleros[i][j]==casilleros[i-1][j+1])&&(casilleros[i-1][j+1]==casilleros[i-2][j+2])&&(casilleros[i-2][j+2]==casilleros[i-3][j+3])&&(casilleros[i][j]==Casillero.ROJO)){
					termino=true;
				}
			}
		}
		return termino;
	}
}

