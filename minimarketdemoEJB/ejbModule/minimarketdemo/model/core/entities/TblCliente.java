package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_clientes database table.
 * 
 */
@Entity
@Table(name="tbl_clientes")
@NamedQuery(name="TblCliente.findAll", query="SELECT t FROM TblCliente t")
public class TblCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente", unique=true, nullable=false)
	private Integer idCliente;

	@Column(name="ciudad_nacimiento", nullable=false, length=50)
	private String ciudadNacimiento;

	@Column(nullable=false, length=50)
	private String correo;

	@Column(nullable=false)
	private Boolean credito;

	@Column(nullable=false, length=50)
	private String direccion;

	@Column(nullable=false)
	private Boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento", nullable=false)
	private Date fechaNacimiento;

	@Column(nullable=false, length=50)
	private String identificacion;

	@Column(nullable=false, length=50)
	private String nombres;

	@Column(nullable=false, length=50)
	private String telefono;

	@Column(name="valor_limite_credito", nullable=false, precision=131089)
	private BigDecimal valorLimiteCredito;

	//bi-directional many-to-one association to TblFactura
	@OneToMany(mappedBy="tblCliente")
	private List<TblFactura> tblFacturas;

	public TblCliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCiudadNacimiento() {
		return this.ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Boolean getCredito() {
		return this.credito;
	}

	public void setCredito(Boolean credito) {
		this.credito = credito;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public BigDecimal getValorLimiteCredito() {
		return this.valorLimiteCredito;
	}

	public void setValorLimiteCredito(BigDecimal valorLimiteCredito) {
		this.valorLimiteCredito = valorLimiteCredito;
	}

	public List<TblFactura> getTblFacturas() {
		return this.tblFacturas;
	}

	public void setTblFacturas(List<TblFactura> tblFacturas) {
		this.tblFacturas = tblFacturas;
	}

	public TblFactura addTblFactura(TblFactura tblFactura) {
		getTblFacturas().add(tblFactura);
		tblFactura.setTblCliente(this);

		return tblFactura;
	}

	public TblFactura removeTblFactura(TblFactura tblFactura) {
		getTblFacturas().remove(tblFactura);
		tblFactura.setTblCliente(null);

		return tblFactura;
	}

}