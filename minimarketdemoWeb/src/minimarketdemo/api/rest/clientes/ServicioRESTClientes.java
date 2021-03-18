package minimarketdemo.api.rest.clientes;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.cliente.manager.ManagerCliente;
import minimarketdemo.model.core.entities.TblCliente;

@RequestScoped
@Path("servicio")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRESTClientes {

	@EJB
	private ManagerCliente managerCliente;

	@GET
	@Path(value = "clientes")
	public List<TblCliente> findAllClientes() {
		return managerCliente.findAllUsuario();
	}

}
