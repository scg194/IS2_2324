package es.unican.is2.FranquiciaUCBusiness;
import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.Empleado;
import es.unican.is2.FranquiciasUCCommon.IEmpleadosDAO;
import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

public class GestionEmpleados implements IGestionEmpleados{
	
	private IEmpleadosDAO empleadosDAO;
	private ITiendasDAO tiendasDAO;

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda t = tiendasDAO.tiendaPorNombre(nombre);
		
		if (t == null || e == null) {
			return null;
		}
		
		t.getEmpleados().add(e);
		tiendasDAO.modificarTienda(t);
		
		return e;
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda t = tiendasDAO.tiendaPorNombre(nombre);
		Empleado e = empleadosDAO.empleado(dni);
		
		if (t == null || e == null) {
			return null;
		}
		
		t.getEmpleados().remove(e);
		tiendasDAO.modificarTienda(t);
		
		return e;
		
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		Tienda tA = tiendasDAO.tiendaPorNombre(actual);
		Tienda tD = tiendasDAO.tiendaPorNombre(destino);
		Empleado e = empleadosDAO.empleado(dni);
		
		if (tA == null || tD == null || e == null || tA.buscaEmpleado(e.getDNI()) != null) {
			return false;
		}
		
		tA.getEmpleados().remove(e);
		tD.getEmpleados().add(e);
		tiendasDAO.modificarTienda(tA);
		tiendasDAO.modificarTienda(tD);
		
		return true;
		
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		return empleadosDAO.empleado(dni);	
	}
}
