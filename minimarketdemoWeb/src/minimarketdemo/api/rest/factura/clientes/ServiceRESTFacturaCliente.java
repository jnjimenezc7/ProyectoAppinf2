package minimarketdemo.api.rest.factura.clientes;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.facturacion.managers.ManagerFacturacion;
import minimarketdemo.model.facturas.dto.DTOTblFactura;

@RequestScoped
@Path("factura")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRESTFacturaCliente {

	@EJB
	private ManagerFacturacion managerFactura;

	@GET
	@Path(value = "clientes")
	public List<DTOTblFactura> findAllFacturaClientes() {
		return managerFactura.findAllDTOTblFactura();
	}
	
	
}

