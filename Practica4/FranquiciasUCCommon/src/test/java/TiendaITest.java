import es.unican.is2.FranquiciasUCCommon.Categoria;
import es.unican.is2.FranquiciasUCCommon.Empleado;
import es.unican.is2.FranquiciasUCCommon.Tienda;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;



public class TiendaITest {
	
	private Tienda tienda;
	
	@BeforeEach
	public void setUp() throws Exception{
		tienda = new Tienda("ferreteria", "torrelavega");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("ferreteria", tienda.getNombre());
		assertEquals("torrelavega", tienda.getDireccion());
	}
	
	@Test
	public void testSueldos() {
		Empleado empleado1 = new Empleado("72199353F", "samuel", Categoria.ENCARGADO, LocalDate.of(2024, 3, 14));
		Empleado empleado2 = new Empleado("72199353F", "paco", Categoria.VENDEDOR, LocalDate.of(2024, 3, 14));
		Empleado empleado3 = new Empleado("72199353F", "luis", Categoria.AUXILIAR, LocalDate.of(2024, 3, 14));
		
		//casos no validos
		//sin empleados
		assertEquals(0, tienda.gastoMensualSueldos());
		
		//1 solo empleado
		tienda.getEmpleados().add(empleado1);
		assertEquals(2000, tienda.gastoMensualSueldos());
		
		//varios empleados
		tienda.getEmpleados().add(empleado2);
		tienda.getEmpleados().add(empleado3);
		assertEquals(4500, tienda.gastoMensualSueldos());
	}
}
