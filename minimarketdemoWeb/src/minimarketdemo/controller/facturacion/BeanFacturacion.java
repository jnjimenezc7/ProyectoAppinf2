package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.cliente.manager.ManagerCliente;
import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.core.entities.TblFactura;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;

@Named
@SessionScoped
public class BeanFacturacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerFacturacion managerFacturacion;
	
	@EJB
	private ManagerCliente managerCliente;
	
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
			JSFUtil.crearMensajeINFO("Usuario encontrdo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cliente=new TblCliente();
			JSFUtil.crearMensajeERROR("Usuaro no encontrado");
			e.printStackTrace();
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
