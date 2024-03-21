import org.junit.jupiter.api.*;

import es.unican.is2.FranquiciaUCBusiness.GestionEmpleados;
import es.unican.is2.FranquiciaUCBusiness.GestionTiendas;
import es.unican.is2.FranquiciasUCCommon.IGestionTiendas;
import es.unican.is2.FranquiciasUCDAO.EmpleadosDAO;
import es.unican.is2.FranquiciasUCDAO.TiendasDAO;
import es.unican.is2.FranquiciasUCGUI.VistaGerente;

public class VistaGerenteITest {
	
	private VistaGerente vista;
	
	@BeforeEach
	public void setUp() throws Exception {
		// Crear componentes capa DAO
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		
		// Crear componentes capa negocio
		GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
		GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO, empleadosDAO);
		
		// Crear componentes capa presentacion
		vista = new VistaGerente(gTiendas, gEmpleados);
	}
	
	@Test
	public void Vistatest() {
		
	}
		
}
