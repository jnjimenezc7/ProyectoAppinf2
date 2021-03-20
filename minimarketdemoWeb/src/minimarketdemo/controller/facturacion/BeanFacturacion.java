package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.client.rest.producto.BeanProducto;
import minimarketdemo.model.cliente.manager.ManagerCliente;
import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.core.entities.TblFactura;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;
import minimarketdemo.model.producto.managers.ManagerProducto;
import minimarketdemo.model.productos.dto.DTOInvProductos;

@Named
@SessionScoped
public class BeanFacturacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerFacturacion managerFacturacion;
	
	@EJB
	private ManagerCliente managerCliente;
	
	@EJB 
	private ManagerProducto managerProducto;
	
	@Inject
	private BeanProducto producto;
	private List<TblFactura> listFactura;
	private TblCliente cliente;
	private String cedula;
	
	

	@PostConstruct
	public void inicializar() {
		//listFactura = managerFacturacion.findAllFacturas();
		cedula="";
	}

	public void actionListenerfindByIdCliente() {
		try {
			 cliente = managerCliente.findClienteByCedula(cedula);
			 if(cliente.getEstado())
			JSFUtil.crearMensajeINFO("Usuario encontrdo");
			
			if(cliente.getEstado()==false) {
				JSFUtil.crearMensajeWARN("Usuario no esta activo");
				cliente=new TblCliente();
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cliente=new TblCliente();
			JSFUtil.crearMensajeERROR("Usuaro no encontrado");
			e.printStackTrace();
		}
		
		
	}
	

	public double totalFactura() {
		double valor=0.0;
		if(producto.getCarrito()!=null) {
			for (DTOInvProductos dtoInvProductos : producto.getCarrito()) {		
				valor+=dtoInvProductos.getValortotal();
			}
			
		}
		return valor+ivatotalFactura();
	}
	public double subtotalFactura() {
		double valor=0.0;
		if(producto.getCarrito()!=null) {
			for (DTOInvProductos dtoInvProductos : producto.getCarrito()) {
				valor+=dtoInvProductos.getValortotal();
			}
			
		}
		return valor;
	}
	public double ivatotalFactura() {
		double valor=0.0;
		if(producto.getCarrito()!=null) {
			for (DTOInvProductos dtoInvProductos : producto.getCarrito()) {
				if(dtoInvProductos.getIva()!=0.0) {
				double valormasIva=dtoInvProductos.getIva()*dtoInvProductos.getValortotal();
				valor+=valormasIva;
			}
				
			}
			
		}
		return valor;
	}
	
	
	public void actionListenerRegistrarFactura() {
		try {
			managerFacturacion.registrarFactura(cliente,totalFactura(),"efectivo");
			JSFUtil.crearMensajeINFO("Factura registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	public TblCliente getCliente() {
		return cliente;
	}

	public void setCliente(TblCliente cliente) {
		this.cliente = cliente;
	}

	public List<TblFactura> getListFactura() {
		return listFactura;
	}

	public void setListFactura(List<TblFactura> listFactura) {
		this.listFactura = listFactura;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	

}
