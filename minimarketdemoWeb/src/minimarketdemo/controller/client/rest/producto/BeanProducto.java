package minimarketdemo.controller.client.rest.producto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.producto.managers.ManagerProducto;
import minimarketdemo.model.productos.dto.DTOInvProductos;

@Named
@SessionScoped
public class BeanProducto implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerProducto managerProducto;
	private List<DTOInvProductos> listProductos;
	private List<DTOInvProductos> listProductosEncontrados;
	private String codigoProd;
	private DTOInvProductos producto;
	private List<DTOInvProductos> carrito;
	private int cantidad;

	public BeanProducto() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
	  listProductos=managerProducto.ObtenerProductos();
	}

		
	public void actionListenerfindProductosByCodigo() {
		boolean encontrado=false;
		for (DTOInvProductos dtoInvProductos : listProductos) {
			if(dtoInvProductos.getCodproducto().equals(codigoProd)) {
				encontrado=true;
				//listProductosEncontrados.add(dtoInvProductos);
			}		
		}
		if(encontrado) {
			JSFUtil.crearMensajeINFO("Producto: "+codigoProd+" encontrado");
		}else {
			JSFUtil.crearMensajeERROR("Producto no encontrado");
		}
	}
	
	
	public String actionAgregarCarrito(String codigoProd,DTOInvProductos p) {
		//arreglar la cantidad en método carrito estaba como codigo OJO
		carrito = managerProducto.agregarCarrito(codigoProd, p.getCantidadProducto(), carrito);
		//totalFactura();
		return "";
		
	}
	
	
	public List<DTOInvProductos> getListProductosEncontrados() {
		return listProductosEncontrados;
	}

	public void setListProductosEncontrados(List<DTOInvProductos> listProductosEncontrados) {
		this.listProductosEncontrados = listProductosEncontrados;
	}

	public List<DTOInvProductos> getListProductos() {
		return listProductos;
	}

	public void setListProductos(List<DTOInvProductos> listProductos) {
		this.listProductos = listProductos;
	}
	
	public String getCodigoProd() {
		return codigoProd;
	}

	public void setCodigoProd(String codigoProd) {
		this.codigoProd = codigoProd;
	}

	public DTOInvProductos getProducto() {
		return producto;
	}

	public void setProducto(DTOInvProductos producto) {
		this.producto = producto;
	}

	public List<DTOInvProductos> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<DTOInvProductos> carrito) {
		this.carrito = carrito;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
