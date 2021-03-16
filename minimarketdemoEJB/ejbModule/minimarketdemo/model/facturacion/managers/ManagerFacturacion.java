package minimarketdemo.model.facturacion.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.core.entities.TblFactura;

/**
 * Session Bean implementation class ManagerFacturacion
 */
@Stateless
@LocalBean
public class ManagerFacturacion {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
	
    public ManagerFacturacion() {
        // TODO Auto-generated constructor stub
    }
    
    public List<TblFactura> findAllFacturas(){
    	return em.createNamedQuery("TblFactura.findAll", TblFactura.class).getResultList();
    }

}
