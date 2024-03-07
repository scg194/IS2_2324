package es.unican.is2.FranquiciaUCBusiness;
import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.Tienda;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;

public class GestionTiendas implements IGestionTiendas{
	
	private ITiendasDAO tiendasDAO;

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		
		if (tienda(t.getNombre()) != null ) {
			return null;
		}
		
		return tiendasDAO.crearTienda(t);
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda tienda = tienda(nombre);
		
		if (tienda == null) {
			return null;
		}
		
		long id = tienda.getId();
		return tiendasDAO.eliminarTienda(id);
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {
		return tiendasDAO.tiendaPorNombre(nombre);
	}
}
