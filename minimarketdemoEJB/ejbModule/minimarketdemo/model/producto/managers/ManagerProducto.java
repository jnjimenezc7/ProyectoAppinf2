package minimarketdemo.model.producto.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import minimarketdemo.model.productos.dto.DTOInvProductos;

/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto {

	/**
	 * Default constructor.
	 */
	public ManagerProducto() {
		// TODO Auto-generated constructor stub
	}

	public List<DTOInvProductos> ObtenerProductos() {
		Client c = ClientBuilder.newClient();
		List<DTOInvProductos> lista = new ArrayList<DTOInvProductos>();
		Response res = c.target(
				"http://moduloinventario.j.layershift.co.uk/minimarketdemoWeb/apirest/inventario/listainventario")
				.request(MediaType.APPLICATION_JSON).get();
		DTOInvProductos[] productos = res.readEntity(DTOInvProductos[].class);
		for (DTOInvProductos producto : productos) {
			producto.setCantidadProducto("");
			lista.add(producto);

		}
		return lista;
	}

	/**
	 * Método para agregar un producto al carrito de compras.
	 * 
	 * @param codigo   EL codigo del producto
	 * @param cantidad La cantidad que se compra
	 * @param carrito  la lista que representa al carrito de compra
	 * @return el carrito de compra con los nuevos productos.
	 */
	public List<DTOInvProductos> agregarCarrito(String codProducto, String cantidad, List<DTOInvProductos> carrito) {
		// busco el codigo
		List<DTOInvProductos> listaProductos = ObtenerProductos();
		DTOInvProductos producto = new DTOInvProductos();
		for (DTOInvProductos p : listaProductos) {
			if (p.getCodproducto().equals(codProducto)) {
				producto.setCodproducto(p.getCodproducto());
				producto.setPreciounitario(p.getPreciounitario());
				producto.setNombreproducto(p.getNombreproducto());
				producto.setCantidad(Integer.parseInt(cantidad));
				producto.setIva(p.getIva());
				;
				producto.setPreciopvp(p.getPreciopvp());
				/*
				 * if(p.getIva()!=0.0) { double valormasIva=p.getIva()*p.getPreciopvp();
				 * producto.setValortotal((p.getPreciopvp()+valormasIva) * cantidad); }else {
				 * producto.setValortotal(p.getPreciopvp() * cantidad); }
				 */
				producto.setValortotal(p.getPreciopvp() * Integer.parseInt(cantidad));

				break;
			}
		}
		// si es necesario, se inicializa el carrito de compras:
		if (carrito == null)
			carrito = new ArrayList<DTOInvProductos>();
		// agregar el producto al carro:
		carrito.add(producto);
		System.out.print("DEVUELVO DE MANAGER---" + carrito);
		return carrito;

	}

	public boolean validaCantidad(String cantidad) {
		if (!(cantidad.matches("[0-9]*"))||cantidad.equals("0")||cantidad.equals("")) {
			return false;
		}
		return true;
	}

}
