package es.unican.is2;
public class VendedorEnPracticas extends Vendedor {
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param dni
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) {//WMC +1
		super(nombre, id);
		this.dni= dni;
	}
	
	public String getDni() {//WMC +1
		return dni;
	}

	@Override
	public boolean equals(Object obj) {//WMC +1
		if (!(obj instanceof VendedorEnPracticas)) //WMC +1 //CCog +1
			return false;
		VendedorEnPracticas v = (VendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
