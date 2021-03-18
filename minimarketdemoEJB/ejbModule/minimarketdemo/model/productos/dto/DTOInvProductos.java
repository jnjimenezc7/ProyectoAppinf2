package minimarketdemo.model.productos.dto;

public class DTOInvProductos {

	private String codigoProducto;
	private Boolean iva;
	private double costo;
	private String nombreProducto;
	private double pvp;
	private Integer cantidad;
	private Integer idCategoriaProducto;

	// codigoProducto
	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	// iva
	public Boolean getiva() {
		return iva;
	}

	public void setiva(Boolean iva) {
		this.iva = iva;
	}

	// costo
	public double getcosto() {
		return costo;
	}

	public void setcosto(double costo) {
		this.costo = costo;
	}

	// nombreProducto
	public String getnombreProducto() {
		return nombreProducto;
	}

	public void setnombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	// pvp
	public double getpvp() {
		return pvp;
	}

	public void setpvp(double pvp) {
		this.pvp = pvp;
	}

	// cantidad
	public Integer getcantidad(){
        return cantidad;
    }

	public void setcantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	// idCategoriaProducto
	public Integer getidCategoriaProducto() {
		return idCategoriaProducto;
	}

	public void setidCategoriaProducto(Integer idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public DTOInvProductos(String codigoProducto, Boolean iva, double costo, String nombreProducto, double pvp,
			Integer cantidad, Integer idCategoriaProducto) {
		super();
		this.codigoProducto = codigoProducto;
		this.iva = iva;
		this.costo = costo;
		this.nombreProducto = nombreProducto;
		this.pvp = pvp;
		this.cantidad = cantidad;
		this.idCategoriaProducto = idCategoriaProducto;
	}
	
	
}
