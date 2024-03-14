import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import es.unican.is2.FranquiciasUCCommon.Categoria;
import es.unican.is2.FranquiciasUCCommon.Empleado;

public class EmpleadoTest {
	private Empleado empleado;
	
	@BeforeEach
	public void setUp() throws Exception{
		empleado = new Empleado("72199353F", "samuel", Categoria.ENCARGADO, LocalDate.of(2024, 3, 14));
	}
	
	@Test
	public void testConstructor() {
		assertEquals("72199353F", empleado.getDNI());
		assertEquals("samuel", empleado.getNombre());
		assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
		assertEquals(LocalDate.of(2024, 3, 14), empleado.getFechaContratacion());
	}
	
	@Test
	public void testSueldo() {
		//casos validos
		
		//0-5 años
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2024, 3, 14));
		empleado.setBaja(false);
		assertEquals(2000, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.VENDEDOR);
		empleado.setFechaContratacion(LocalDate.of(2022, 3, 14));
		empleado.setBaja(true);
		assertEquals(1125, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.AUXILIAR);
		empleado.setFechaContratacion(LocalDate.of(2019, 3, 14));
		empleado.setBaja(false);
		assertEquals(1000, empleado.sueldoBruto());
		
		//6-10 años
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2018, 3, 14));
		empleado.setBaja(false);
		assertEquals(2050, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2016, 3, 14));
		empleado.setBaja(false);
		assertEquals(2050, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2014, 3, 14));
		empleado.setBaja(false);
		assertEquals(2050, empleado.sueldoBruto());
		
		//11-20 años
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2013, 3, 14));
		empleado.setBaja(false);
		assertEquals(2100, empleado.sueldoBruto());

		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2009, 3, 14));
		empleado.setBaja(false);
		assertEquals(2100, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2004, 3, 14));
		empleado.setBaja(false);
		assertEquals(2100, empleado.sueldoBruto());
		
		//21-infinto
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(2003, 3, 14));
		empleado.setBaja(false);
		assertEquals(2200, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.of(1994, 3, 14));
		empleado.setBaja(false);
		assertEquals(2200, empleado.sueldoBruto());
		
		//casos no validos
	}
	
	@Test
	public void testBaja() {
		empleado.darDeBaja();
		assertEquals(true, empleado.getBaja());
	}
	
	@Test
	public void testAlta() {
		empleado.darDeAlta();
		assertEquals(false, empleado.getBaja());
	}
	
	
	
	

}
