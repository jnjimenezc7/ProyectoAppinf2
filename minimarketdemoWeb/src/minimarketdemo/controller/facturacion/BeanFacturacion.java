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
import minimarketdemo.controller.cliente.BeanCliente;
import minimarketdemo.model.cliente.manager.ManagerCliente;
import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.core.entities.TblFactura;
import minimarketdemo.model.core.entities.TblModoPago;
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
	private ManagerProducto managerProducto;
	
	@Inject
	private BeanProducto producto;
	
	@Inject
	private BeanCliente cliente;
	private List<TblFactura> listFactura;
	private List<TblModoPago> listModoPago;
	private int tipoPago;

	
	

	@PostConstruct
	public void inicializar() {
		//listFactura = managerFacturacion.findAllFacturas();
		listModoPago=managerFacturacion.findAllModoPago();
	managerFacturacion.obtenerUltimoRegistro();
	actionListenerNumeroFacturacion();
		
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
	
	public int actionListenerNumeroFacturacion() {
        try {
             int numeros = managerFacturacion.obtenerUltimoRegistroFactura();
            return numeros;     
        } catch (Exception e) {
            JSFUtil.crearMensajeINFO("Error");  
        }
        return 0;   
    }
	
	public void actionListenerRegistrarFactura() {
		
		try {
				TblModoPago mp=listModoPago.get(tipoPago);
				managerFacturacion.registrarFactura(cliente.getNuevoCliente(),totalFactura(),mp,producto.getCarrito());
				JSFUtil.crearMensajeINFO("Factura registrada");	
				actionListenerLimpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	
 public void actionListenerLimpiar() {
	 cliente.Limpiar();
	 producto.limpiar();
 }
	public List<TblFactura> getListFactura() {
		return listFactura;
	}

	public void setListFactura(List<TblFactura> listFactura) {
		this.listFactura = listFactura;
	}


	public int getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}


	public List<TblModoPago> getListModoPago() {
		return listModoPago;
	}


	public void setListModoPago(List<TblModoPago> listModoPago) {
		this.listModoPago = listModoPago;
	}

	

}
