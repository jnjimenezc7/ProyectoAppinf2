package minimarketdemo.controller.cliente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.cliente.manager.ManagerCliente;
import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.productos.dto.DTOInvProductos;

@Named
@SessionScoped
public class BeanCliente implements Serializable {

	private List<TblCliente> listaClientes;
	@EJB
	private ManagerCliente mCliente;
	private TblCliente nuevoCliente;
	private TblCliente edicionCliente;
	private String cedula;

	public BeanCliente() {

	}

	@PostConstruct
	private void inicializar() {
			
		listaClientes = mCliente.findAllUsuario();
		
		nuevoCliente = new TblCliente();
		edicionCliente = new TblCliente();

	}

	public void Limpiar() {
		nuevoCliente = new TblCliente();
	}

	public void actionListenerfindByIdCliente() {
		try {
			 nuevoCliente = mCliente.findClienteByCedula(cedula);
			 if(nuevoCliente.getEstado())
				 cedula=nuevoCliente.getIdentificacion();
			JSFUtil.crearMensajeINFO("Usuario encontrdo");
			
			if(nuevoCliente.getEstado()==false) {
				JSFUtil.crearMensajeWARN("Usuario no esta activo");

				nuevoCliente=new TblCliente();
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			nuevoCliente=new TblCliente();
			JSFUtil.crearMensajeERROR("Usuaro no encontrado");
			e.printStackTrace();
		}
		
		
	}

	

	public void actionListenerFindClienteByCedula(String cedula) {

		try {
			 nuevoCliente= mCliente.findClienteByCedula(cedula);
			 cedula=nuevoCliente.getIdentificacion();
			JSFUtil.crearMensajeINFO("Usuario encontrado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			listaClientes = mCliente.findAllUsuario();
			System.out.println(listaClientes);
			JSFUtil.crearMensajeINFO("Cliente creado.");
			nuevoCliente = new TblCliente();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarCliente(TblCliente cliente) {
		edicionCliente = cliente;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
