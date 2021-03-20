package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_factura database table.
 * 
 */
@Entity
@Table(name="tbl_factura")
@NamedQuery(name="TblFactura.findAll", query="SELECT t FROM TblFactura t")
public class TblFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura", unique=true, nullable=false)
	private Integer idFactura;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_factura", nullable=false)
	private Date fechaFactura;

	@Column(name="numero_factura", nullable=false, length=50)
	private String numeroFactura;

	@Column(name="tipo_pago", nullable=false, length=50)
	private String tipoPago;

	@Column(name="valor_factura", nullable=false, precision=131089)
	private BigDecimal valorFactura;

	//bi-directional many-to-one association to TblDetalle
	@OneToMany(mappedBy="tblFactura")
	private List<TblDetalle> tblDetalles;

	//bi-directional many-to-one association to TblCliente
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	private TblCliente tblCliente;

	public TblFactura() {
	}

	public Integer getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFechaFactura() {
		return this.fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public BigDecimal getValorFactura() {
		return this.valorFactura;
	}

	public void setValorFactura(BigDecimal valorFactura) {
		this.valorFactura = valorFactura;
	}

	public List<TblDetalle> getTblDetalles() {
		return this.tblDetalles;
	}

	public void setTblDetalles(List<TblDetalle> tblDetalles) {
		this.tblDetalles = tblDetalles;
	}

	public TblDetalle addTblDetalle(TblDetalle tblDetalle) {
		getTblDetalles().add(tblDetalle);
		tblDetalle.setTblFactura(this);

		return tblDetalle;
	}

	public TblDetalle removeTblDetalle(TblDetalle tblDetalle) {
		getTblDetalles().remove(tblDetalle);
		tblDetalle.setTblFactura(null);

		return tblDetalle;
	}

	public TblCliente getTblCliente() {
		return this.tblCliente;
	}

	public void setTblCliente(TblCliente tblCliente) {
		this.tblCliente = tblCliente;
	}

}