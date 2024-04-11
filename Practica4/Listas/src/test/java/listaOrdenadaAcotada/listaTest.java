package listaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;

public class listaTest {
	
	private ListaOrdenadaAcotada<Integer> lista;
	
	@BeforeEach
	public void setUp() {
		lista = new ListaOrdenadaAcotada<Integer>(20);
	}
	
	
	@Test
	public void getAddTest() {
		
		//intento con la lista vacia
		/*TODO*/ //assertEquals(null, lista.get(0));
		
		//añado 1 elemento
		lista.add(0);
		
		//compruebo 1 unico elemento
		assertEquals(0, lista.get(0));
		
		//añado muchos elementos
		lista.add(1);
		lista.add(2);
		lista.add(3);
		
		//compruebo un elemnto intermedio
		assertEquals(2, lista.get(2));
		
		//compruebo elemento final
		assertEquals(3, lista.get(3));
		
		//casos no validos
	
		//indice menor a 0
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3));
		
		//indice mayor al size
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(4));

		
		
	}
	

	
	

}
