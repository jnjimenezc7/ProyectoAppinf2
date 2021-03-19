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

	public List<DTOInvProductos> ObtenerDatos() {
		Client c = ClientBuilder.newClient();
		List<DTOInvProductos> lista = new ArrayList<DTOInvProductos>();
		Response res = c.target(
				"http://moduloinventario.j.layershift.co.uk/minimarketdemoWeb/apirest/inventario/listainventario")
				.request(MediaType.APPLICATION_JSON).get();
		DTOInvProductos[] productos = res.readEntity(DTOInvProductos[].class);
		for (DTOInvProductos producto : productos) {

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
	public List<DTOInvProductos> agregarCarrito(String codProducto, int cantidad, List<DTOInvProductos> carrito) {
		// busco el codigo
		List<DTOInvProductos> listaProductos = ObtenerDatos();
		DTOInvProductos producto = new DTOInvProductos();
		for (DTOInvProductos p : listaProductos) {
			if (p.getCodproducto() == codProducto) {
				producto.setCodproducto(p.getCodproducto());
				producto.setNombreproducto(p.getNombreproducto());
				producto.setCantidad(cantidad);
				producto.setPreciopvp(p.getPreciopvp());
				producto.setPreciopvp(p.getPreciopvp() * cantidad);
				break;
			}
		}
		// si es necesario, se inicializa el carrito de compras:
		if (carrito == null) {
			carrito = new ArrayList<DTOInvProductos>();
		}
		// agregar el producto al carro:
		carrito.add(producto);
		System.out.print("DEVUELVO DE MANAGER---" + carrito);
		return carrito;
		
	}

}
