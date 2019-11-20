package tests;

import juego.Casillero;
import juego.CuatroEnLinea;

import org.junit.Test;
import org.junit.Assert;

public class CuatroEnLineaTest {
	
	@Test(expected = Error.class)
	public void tableroFilasNegativas(){
		
		new CuatroEnLinea (-3, 5, "javitox","faberfix");
	}
	
	@Test(expected = Error.class)
	public void tableroFilasNegativas(){
		
		new CuatroEnLinea (7, -5, "javitox","faberfix");
	}

	@Test(expected = Error.class)
	public void tableroFilasYColumnasEnTres() {
		
		new CuatroEnLinea(3, 3, "javitox","faberfix");
	}
	
	@Test(expected = Error.class)
	public void tableroFilasYColumnasEnTrece() {
		
		new CuatroEnLinea(13, 13, "javitox","faberfix");
	}
	
	@Test
	public void seCreaUnJuegoCuatroPorCuatro() {
		
		CuatroEnLinea tablero = new CuatroEnLinea(4, 4, "javitox","faberfix");
		
		Assert.assertEquals(4, tablero.contarFilas());
		Assert.assertEquals(4, tablero.contarColumnas());
	}
	
	@Test
	public void seCreaUnJuegoDocePorDoce() {
		
		CuatroEnLinea tablero = new CuatroEnLinea(12, 12, "javitox","faberfix");
		
		Assert.assertEquals(12, tablero.contarFilas());
		Assert.assertEquals(12, tablero.contarColumnas());
	}
	
	@Test
	public void seCreaUnJuegoSinTerminar() {
		
		CuatroEnLinea tablero = new CuatroEnLinea(4, 4, "javitox","faberfix");
		
		Assert.assertFalse(tablero.termino());
	}
	
	@Test
	public void tableroVacio() {
		
		CuatroEnLinea tablero = new CuatroEnLinea(4, 4, "javitox","faberfix");
				
    		for(int i=0;i<casilleros.length;i++){
			for(int j=0; j<casilleros[i].length;j++){
      
				Assert.assertEquals(Casillero.VACIO, tablero.obtenerCasillero(i, j));
			}
		}
	}
	
	@Test
	public void jugadorGanaDiagonalAscendente() {
		CuatroEnLinea tablero = new CuatroEnLinea(4, 4, "javitox","faberfix");
		
		tablero.soltarFicha(1); // R
		tablero.soltarFicha(2); // A
		tablero.soltarFicha(2); // R
		tablero.soltarFicha(3); // A
		tablero.soltarFicha(3); // R
		tablero.soltarFicha(4); // A
		tablero.soltarFicha(3); // R
		tablero.soltarFicha(4); // A
		tablero.soltarFicha(4); // R
		tablero.soltarFicha(3); // A
		tablero.soltarFicha(4); // R
		
		// [    A R]
		// [    R R]
		// [  R R A]
		// [R A A A]
		
		assertTrue(tablero.hayGanador());
	}
	
	@Test
	public void jugadorGanaDiagonalDescendente() {
		CuatroEnLinea tablero = new CuatroEnLinea(4, 4, "javitox","faberfix");
		
		tablero.soltarFicha(1); // A
		tablero.soltarFicha(1); // R
		tablero.soltarFicha(1); // A
		tablero.soltarFicha(1); // R
		tablero.soltarFicha(2); // A
		tablero.soltarFicha(3); // R
		tablero.soltarFicha(2); // A
		tablero.soltarFicha(2); // R
		tablero.soltarFicha(2); // A
		tablero.soltarFicha(3); // R
		tablero.soltarFicha(3); // A
		tablero.soltarFicha(4); // R
		
		// [R A    ]
		// [A R A  ]
		// [R A R  ]
		// [A A R R]
		
		assertTrue(tablero.hayGanador());
	}
  }
		    
