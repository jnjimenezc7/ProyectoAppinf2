package minimarketdemo.model.facturacion.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.entities.TblCliente;
import minimarketdemo.model.core.entities.TblDetalle;
import minimarketdemo.model.core.entities.TblDetallePK;
import minimarketdemo.model.core.entities.TblFactura;
import minimarketdemo.model.core.entities.TblModoPago;
import minimarketdemo.model.facturas.dto.DTOTblFactura;
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
    
    public TblFactura obtenerUltimoRegistro() {
    	TblFactura f=null;
    	try {
    		Query q = em.createQuery("SELECT t FROM TblFactura t order by t.numeroFactura desc",TblFactura.class).setMaxResults(1);
    		f=(TblFactura) q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return f;

    }
    public int obtenerUltimoRegistroFactura() {
    	int nFactura=0;
    	try {
    		TblFactura f= obtenerUltimoRegistro();
    		if(f==null)
    			nFactura=1;
    		
        	nFactura= Integer.parseInt(f.getNumeroFactura());
        	nFactura++;
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return nFactura;
    	
    
    	
    		

    	}
    
    public List<TblModoPago> findAllModoPago(){
    	return em.createNamedQuery("TblModoPago.findAll", TblModoPago.class).getResultList();
    }
   
    public void registrarFactura(TblCliente cliente,double valor, TblModoPago modoPago,List<DTOInvProductos> detalle) throws Exception{
    	TblFactura factura=new TblFactura();
    	BigDecimal montofactura;
    		if(cliente.getIdentificacion()==null) 
    			throw new Exception("Debe seleccionar un usuario");
    		if(detalle==null) 
    			throw new Exception("Debe ingresar al menos un producto");
    		
    		factura.setFechaFactura(new Date());
    	    factura.setNumeroFactura(obtenerUltimoRegistroFactura()+"");
    	    montofactura=new BigDecimal(valor);
    	    factura.setValorFactura(montofactura);
    	    factura.setTblCliente(cliente);
    	    factura.setTblModoPago(modoPago);
    	    em.persist(factura);
    	    factura=obtenerUltimoRegistro();
    	    registrarDetalle(factura, detalle);
    }
    
    
    public void registrarDetalle(TblFactura factura,List<DTOInvProductos> productos)throws Exception {
    	BigDecimal pu;
    	TblDetalle detalle=new TblDetalle();
    	for (DTOInvProductos dtoInvProductos : productos) {;
    		detalle.setTblFactura(factura);
			detalle.setCantidad(dtoInvProductos.getCantidad());
			pu=new BigDecimal(dtoInvProductos.getPreciounitario());
			detalle.setPreciounitariocompra(pu);
			detalle.setId(guardarPKdetalle(factura.getIdFactura(),dtoInvProductos.getCodproducto()));
			em.merge(detalle);
		}
    	
    	
    }
    public TblDetallePK guardarPKdetalle(int idFactura,String codigoprod) {
    	TblDetallePK dtp=new TblDetallePK();
    	dtp.setIdFactura(idFactura);
    	dtp.setCodigoproducto(codigoprod);
    return dtp;
    }
    public void guardardetallefactura(TblDetalle detalle) {
    	
    	
    }
    
    ///Consulta de la identificacion por medio del idCliente de la tabla Facturacion
	public String  findClienteByIdCliente(int idCliente){
		TblCliente c;
		String sql = "Select b from TblCliente b where b.idCliente=:idCliente";
		Query q = em.createQuery(sql, TblCliente.class).setParameter("idCliente", idCliente);
		c = (TblCliente) q.getSingleResult();
		return c.getIdentificacion()+"";
	}
    //listado de faturas Api
    public List<DTOTblFactura> findAllDTOTblFactura(){
    	List<DTOTblFactura> listaDTO= new ArrayList<DTOTblFactura>();
    	for (TblFactura factura: findAllFacturas()) {
    		DTOTblFactura c= new DTOTblFactura(factura.getNumeroFactura(),findClienteByIdCliente(factura.getTblCliente().getIdCliente()),factura.getValorFactura().doubleValue(),factura.getFechaFactura());
    		listaDTO.add(c);
    	}//
    	return listaDTO;
    }
    

}
