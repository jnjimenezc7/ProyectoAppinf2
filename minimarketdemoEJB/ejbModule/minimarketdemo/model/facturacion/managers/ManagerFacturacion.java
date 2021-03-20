package minimarketdemo.model.facturacion.managers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.core.entities.TblDetalle;
import minimarketdemo.model.core.entities.TblDetallePK;
import minimarketdemo.model.core.entities.TblFactura;
import minimarketdemo.model.productos.dto.DTOInvProductos;

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
    
    
   
    public void registrarFactura(TblCliente cliente,double valor, String tipoPago) throws Exception{
    	TblFactura factura=new TblFactura();
    	BigDecimal montofactura;
    	if(cliente==null)
    		throw new Exception("Registre un cliente");
    	
    	factura.setFechaFactura(new Date());
    	factura.setNumeroFactura("00001");
    	factura.setTblCliente(cliente);
    	montofactura=new BigDecimal(valor);
    	factura.setValorFactura(montofactura);
    	factura.setTipoPago(tipoPago);
    	if(cliente!=null)
    	em.persist(factura);
    }
    
    
    public void registrarDetalle(Integer idFactura,List<DTOInvProductos> productos) {
    	TblDetallePK dp=new TblDetallePK();
    	BigDecimal montofactura;
    	//dp.setCodigoproducto(nuemeroFactura);
    	dp.setIdFactura(idFactura);
    	TblDetalle detalle=new TblDetalle();
    	for (DTOInvProductos dtoInvProductos : productos) {
    		//dp.setCodigoproducto(dtoInvProductos.getCodproducto());
			detalle.setCantidad(dtoInvProductos.getCantidad());
			montofactura=new BigDecimal(dtoInvProductos.getPreciounitario());
			detalle.setPreciounitariocompra(montofactura);
			//detalle.set
		}
    	
    }
    
    

}
