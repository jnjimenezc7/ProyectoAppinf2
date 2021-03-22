package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_modo_pago database table.
 * 
 */
@Entity
@Table(name="tbl_modo_pago")
@NamedQuery(name="TblModoPago.findAll", query="SELECT t FROM TblModoPago t")
public class TblModoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mpago", unique=true, nullable=false)
	private Integer idMpago;

	@Column(length=40)
	private String nombre;

	@Column(name="otro_detalle", length=60)
	private String otroDetalle;

	//bi-directional many-to-one association to TblFactura
	@OneToMany(mappedBy="tblModoPago")
	private List<TblFactura> tblFacturas;

	public TblModoPago() {
	}

	public Integer getIdMpago() {
		return this.idMpago;
	}

	public void setIdMpago(Integer idMpago) {
		this.idMpago = idMpago;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOtroDetalle() {
		return this.otroDetalle;
	}

	public void setOtroDetalle(String otroDetalle) {
		this.otroDetalle = otroDetalle;
	}

	public List<TblFactura> getTblFacturas() {
		return this.tblFacturas;
	}

	public void setTblFacturas(List<TblFactura> tblFacturas) {
		this.tblFacturas = tblFacturas;
	}

	public TblFactura addTblFactura(TblFactura tblFactura) {
		getTblFacturas().add(tblFactura);
		tblFactura.setTblModoPago(this);

		return tblFactura;
	}

	public TblFactura removeTblFactura(TblFactura tblFactura) {
		getTblFacturas().remove(tblFactura);
		tblFactura.setTblModoPago(null);

		return tblFactura;
	}

}