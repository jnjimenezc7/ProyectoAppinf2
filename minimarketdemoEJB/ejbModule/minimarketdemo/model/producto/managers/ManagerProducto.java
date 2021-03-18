package minimarketdemo.model.producto.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import minimarketdemo.model.core.entities.AudBitacora;
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
		Response res = c
				.target("http://moduloinventario.j.layershift.co.uk/minimarketdemoWeb/apirest/inventario/listainventario")
				.request(MediaType.APPLICATION_JSON).get();
		DTOInvProductos[] productos = res.readEntity(DTOInvProductos[].class);
		for (DTOInvProductos producto : productos) {

			lista.add(producto);

		}
		return lista;
	}

}
