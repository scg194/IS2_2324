package listaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;



public class listaTest {
	
	@Test
	public void constructorTest() {
		
		//creo con maximo 1
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(1);
				
		//creo con maximo cualquier numero
		ListaOrdenadaAcotada<Integer> lista2 = new ListaOrdenadaAcotada<Integer>(5);
		
		//creo con maximo 0
		ListaOrdenadaAcotada<Integer> lista3 = new ListaOrdenadaAcotada<Integer>(0);
		
		//creo con maximo cualquier numero negativo
		assertThrows(NegativeArraySizeException.class, () -> new ListaOrdenadaAcotada<Integer>(-3));
	}
	
	
	@Test
	public void getAddTest() {
		
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(4);
		
		//intento con la lista vacia
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
		
		//añado 1 elemento
		lista.add(-1);
		
		//compruebo 1 unico elemento
		assertEquals(-1, lista.get(0));
		
		//añado muchos elementos en diferente orden
		lista.add(2);
		lista.add(3);
		lista.add(0);
		
		//compruebo un elemnto intermedio
		assertEquals(2, lista.get(2));
		
		//compruebo elemento final
		assertEquals(3, lista.get(3));
		
		//casos no validos
	
		//buscar indice menor a 0
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3));
		
		//buscar indice mayor al size
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(4));
		
		//insertar en lista llena
		assertThrows(IllegalStateException.class, () -> lista.add(4));
		
		//insertar nulo
		assertThrows(NullPointerException.class, () -> lista.add(null));
	}
	
	@Test
	public void removeTest() {
		
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(4);
		
		//intento eliminar de lista vacia
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
		
		//lleno la lista
		lista.add(5);
		lista.add(10);
		lista.add(15);		
		lista.add(20);
		
		//elimino elemento final
		assertEquals(20, lista.remove(3));
		
		//elimino elemento intermedio
		assertEquals(10, lista.remove(1));
		
		//elimino primer elemento de la lista
		assertEquals(5, lista.remove(0));
		
		//elimino unico elemento
		assertEquals(15, lista.remove(0));
		
		//casos no validos
		
		//indice menor a 0
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-3));
		
		//inidce mayor al tamaño
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(5));
	}
	
	@Test
	public void sizeTest() {
		
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(4);
		
		//ningun elemento
		assertEquals(0, lista.size());
		
		lista.add(0);
		
		//1 elemento
		assertEquals(1, lista.size());
		
		lista.add(1);
		
		//varios elementos
		assertEquals(2, lista.size());
		
		lista.add(3);
		lista.add(4);
		
		//lista llena
		assertEquals(4, lista.size());
	}
	
	@Test
	public void clearTest() {
		
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(3);

		//lista ya vacia
		lista.clear();
		assertEquals(0, lista.size());
		
		//1 elemento
		lista.add(0);
		lista.clear();
		assertEquals(0, lista.size());
		
		//varios elementos
		lista.add(0);
		lista.add(1);
		lista.clear();
		assertEquals(0, lista.size());
		
		//lista llena
		lista.add(0);
		lista.add(1);
		lista.add(2);
		lista.clear();
		assertEquals(0, lista.size());
	}
	

	
	

}
