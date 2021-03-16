package minimarketdemo.model.cliente.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerCliente {

	/**
	 * Default constructor.
	 */
	@EJB
	private ManagerDAO mDAO;

	@PersistenceContext
	private EntityManager em;

	public ManagerCliente() {
		// TODO Auto-generated constructor stub
	}

	public List<TblCliente> findAllUsuario() {
		return em.createNamedQuery("TblCliente.findAll", TblCliente.class).getResultList();
	}

	public TblCliente findClienteByCedula(String cedula){
		String sql = "Select b from TblCliente b where b.identificacion=:cedula";
		Query q = em.createQuery(sql, TblCliente.class).setParameter("cedula", cedula);
	return (TblCliente) q.getSingleResult();
	}

	public void insertarCliente(TblCliente nuevoCliente) throws Exception {
		TblCliente cliente = new TblCliente();
		cliente.setIdentificacion(nuevoCliente.getIdentificacion());
		cliente.setCiudadNacimiento(nuevoCliente.getCiudadNacimiento());
		cliente.setCorreo(nuevoCliente.getCorreo());
		cliente.setCredito(nuevoCliente.getCredito());
		cliente.setDireccion(nuevoCliente.getDireccion());
		cliente.setEstado(nuevoCliente.getEstado());
		cliente.setFechaNacimiento(nuevoCliente.getFechaNacimiento());
		cliente.setNombres(nuevoCliente.getNombres());
		cliente.setTelefono(nuevoCliente.getTelefono());
		cliente.setValorLimiteCredito(nuevoCliente.getValorLimiteCredito());
		em.persist(cliente);
	}

	public void actualizarCliente(TblCliente editarCliente) throws Exception {
		TblCliente cliente = (TblCliente) mDAO.findById(TblCliente.class, editarCliente.getIdCliente());
		// modulo.setNombreModulo(editarCliente.getNombreModulo());
		// modulo.setRutaAcceso(edicionModulo.getRutaAcceso());

		cliente.setIdentificacion(editarCliente.getIdentificacion());
		cliente.setCiudadNacimiento(editarCliente.getCiudadNacimiento());
		cliente.setCorreo(editarCliente.getCorreo());
		cliente.setCredito(editarCliente.getCredito());
		cliente.setDireccion(editarCliente.getDireccion());
		cliente.setEstado(editarCliente.getEstado());
		cliente.setFechaNacimiento(editarCliente.getFechaNacimiento());
		cliente.setNombres(editarCliente.getNombres());
		cliente.setTelefono(editarCliente.getTelefono());
		cliente.setValorLimiteCredito(editarCliente.getValorLimiteCredito());

		mDAO.actualizar(cliente);
	}

}
