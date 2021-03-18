package minimarketdemo.controller.client.rest.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.productos.dto.DTOInvProductos;

@Named
@SessionScoped
public class BeanProducto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DTOInvProductos> listProductos;
	private List<DTOInvProductos> listProductosEncontrados;
	private String codigoProd;
	private DTOInvProductos producto;

	public BeanProducto() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
	  listProductos=clienteListProductos();
	}

	public List<DTOInvProductos> clienteListProductos() {
	List<DTOInvProductos> lista=new ArrayList<DTOInvProductos>();
		lista.add(new DTOInvProductos("Pord0001", true, 3.0, "azucar", 3.10, 5, 2));
		lista.add(new DTOInvProductos("Pord0002", true, 3.0, "azucar", 3.10, 5, 2));
		lista.add(new DTOInvProductos("Pord0003", true, 3.0, "azucar", 3.10, 5, 2));
		lista.add(new DTOInvProductos("Pord0004", true, 3.0, "azucar", 3.10, 5, 2));
		lista.add(new DTOInvProductos("Pord0005", true, 3.0, "azucar", 3.10, 5, 2));
		return lista;
	}

	
	public void actionListenerfindProductosByCodigo() {
		boolean encontrado=false;
		for (DTOInvProductos dtoInvProductos : listProductos) {
			if(dtoInvProductos.getCodigoProducto().equals(codigoProd)) {
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
