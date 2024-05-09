package es.unican.is2;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {//WMC +1
		this.datos = datos;
		
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() {//WMC +1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {//WMC +1
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhade(Vendedor nuevo) throws DataAccessException {//WMC +1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {//WMC +1 //CCog + 1
			return false;
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException {//WMC +1
		Vendedor v = buscaVendedor(id);
		if (v == null) {//WMC +1 //CCog + 1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException {//WMC +1
		Vendedor v = buscaVendedor(id);
		if (v == null) {//WMC +1 //CCog + 1
			return false;
		}
		
		v.anhadeVenta(importe);
		
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 * @throws DataAccessException 
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {//WMC +1
		
		lista = vendedores();
	
		for (Vendedor v : lista) {//WMC +1 //CCog + 1
			if (v.getId().equals(id)) {//WMC +1 //CCog + 2
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException {//WMC +1
		lista = new LinkedList<Vendedor>();

		try (Scanner in = new Scanner(new FileReader(datos))){
			
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) {//WMC +2 //CCog + 2
				ven = creaVendedor(in, TipoVendedor.Senior);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Practicas")) {//WMC +2 //CCog + 2
				ven = creaVendedor(in, TipoVendedor.Junior);
				lista.add(ven);
			}
			while (in.hasNext()) {//WMC +1 //CCog + 1
				ven = creaVendedor(in, null);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {//WMC +1 //CCog + 1
			throw new DataAccessException();
		}// try

		return lista;

	}

	private Vendedor creaVendedor(Scanner in, TipoVendedor tipo) {//WMC +1
		Vendedor ven;
		if (tipo == null) {//WMC +1 //CCog + 1
			in.next();
		}
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni = in.next();
		in.next();
		double totalVentas = in.nextDouble();
		
		if (tipo != null) {//WMC +1 //CCog + 1
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorEnPlantilla(nombre, idIn, dni, tipo);
			ven.setC(totalComision);

		} else {
			ven = new VendedorEnPracticas(nombre, idIn, dni);
		}
		ven.setTotalVentas(totalVentas);
		return ven;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {//WMC +1
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) {//WMC +1 //CCog + 1
			if (v instanceof VendedorEnPracticas) {//WMC +1 //CCog + 2
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) {//WMC +1 //CCog + 2
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v; //CCog + 3
				if (vp.tipo().equals(TipoVendedor.Junior))
					junior.add(vp);
				else
					senior.add(vp);
			}
		}

		try (PrintWriter out = new PrintWriter(new FileWriter(datos))){

			

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			pintaDatos(out, senior);
			out.println();
			out.println("Junior");
			pintaDatos(out, junior);
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) {//WMC +1 //CCog + 1
				VendedorEnPracticas v3 = (VendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDni()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
		} catch (IOException e) {//WMC +1 //CCog + 1
			throw new DataAccessException();

		}
	}

	private void pintaDatos(PrintWriter out, List<Vendedor> lista) {//WMC +1
		for (Vendedor v : lista) {//WMC +1 //CCog + 1
			VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
			out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.dni()
					+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getC());
		}
	}

}
