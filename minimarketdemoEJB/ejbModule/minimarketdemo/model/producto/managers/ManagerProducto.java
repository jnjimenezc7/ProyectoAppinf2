package minimarketdemo.model.producto.managers;

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
    
    public void ObtenerDatos() {
    	Client c = ClientBuilder.newClient();
		Response res = c
				.target("http://env-4208164.jelastic.saveincloud.net:8080/minimarketdemoWeb/apirest/auditoria/bitacora")
				.request(MediaType.APPLICATION_JSON).get();
		DTOInvProductos[] productos = res.readEntity(DTOInvProductos[].class);

		for (DTOInvProductos producto : productos) {

			System.out.println(producto.getnombreProducto());

		}
    }

}
