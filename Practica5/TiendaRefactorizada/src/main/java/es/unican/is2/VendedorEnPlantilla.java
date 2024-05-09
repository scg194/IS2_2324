package es.unican.is2;
public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	private String dni;
	
	static final double COMISION_JUNIOR= 0.005;
	static final double COMISION_SENIOR= 0.01;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {//WMC +1
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	
	public TipoVendedor tipo() {//WMC +1
		return tipo;
	}
	
	public String dni() {//WMC +1
		return dni;
	}

	/**
	 * Anhade una venta al vendedor
	 * @param importe Importe de la venta
	 */
	public void anhadeVenta(double importe) {//WMC +1
		
		double comision = 0;
		switch (tipo) {//CCog +1
			case Junior://WMC +1
				comision = importe * COMISION_JUNIOR;
				break;
			case Senior://WMC +1
				comision = importe * COMISION_SENIOR;
				break;
			}
		
		anhade(importe);
		setC(getC()+comision);
	}
	
	@Override
	public boolean equals(Object obj) {//WMC +1
		if (!(obj instanceof VendedorEnPlantilla))//WMC +1 //CCog +1 
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.dni().equals(dni()));
	}
}
