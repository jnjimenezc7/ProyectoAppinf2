package minimarketdemo.controller.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.cliente.manager.ManagerCliente;

import minimarketdemo.model.core.entities.TblCliente;

@Named
@SessionScoped
public class BeanCliente implements Serializable {

	private List<TblCliente> listaClientes;
	@EJB 
	private ManagerCliente mCliente;
	private TblCliente nuevoCliente;
	private TblCliente edicionCliente;
	
	public BeanCliente() {
		
	}
	
	@PostConstruct
	private void inicializar(){
		listaClientes=mCliente.findAllUsuario();
		nuevoCliente=new TblCliente();
		edicionCliente=new TblCliente();
	}

	public List<TblCliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<TblCliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public void actionListenerInsertarCliente() {
		try {
			mCliente.insertarCliente(nuevoCliente);
			System.out.println(nuevoCliente);
			listaClientes=mCliente.findAllUsuario();
			System.out.println(listaClientes);
			JSFUtil.crearMensajeINFO("Cliente creado.");
			nuevoCliente=new TblCliente();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarCliente(TblCliente cliente) {
		edicionCliente=cliente;
	}
	
	public void actionListenerGuardarEdicionCliente() {
		try {
			mCliente.actualizarCliente(edicionCliente);
			JSFUtil.crearMensajeINFO("Cliente editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
	public TblCliente getNuevoCliente() {
		return nuevoCliente;
	}

	public void setNuevoCliente(TblCliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}

	public TblCliente getEdicionCliente() {
		return edicionCliente;
	}

	public void setEdicionCliente(TblCliente edicionCliente) {
		this.edicionCliente = edicionCliente;
	}
	
	
	

}
