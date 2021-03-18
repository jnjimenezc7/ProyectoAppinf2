package minimarketdemo.controller.client.rest.producto;

import java.io.Serializable;
import java.util.ArrayList;
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

	public BeanProducto() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
	  listProductos=managerProducto.ObtenerDatos();
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


}