package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tbl_detalle database table.
 * 
 */
@Entity
@Table(name="tbl_detalle")
@NamedQuery(name="TblDetalle.findAll", query="SELECT t FROM TblDetalle t")
public class TblDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblDetallePK id;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false, precision=131089)
	private BigDecimal preciounitariocompra;

	//bi-directional many-to-one association to TblFactura
	@ManyToOne
	@JoinColumn(name="id_factura", nullable=false, insertable=false, updatable=false)
	private TblFactura tblFactura;

	public TblDetalle() {
	}

	public TblDetallePK getId() {
		return this.id;
	}

	public void setId(TblDetallePK id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPreciounitariocompra() {
		return this.preciounitariocompra;
	}

	public void setPreciounitariocompra(BigDecimal preciounitariocompra) {
		this.preciounitariocompra = preciounitariocompra;
	}

	public TblFactura getTblFactura() {
		return this.tblFactura;
	}

	public void setTblFactura(TblFactura tblFactura) {
		this.tblFactura = tblFactura;
	}

}