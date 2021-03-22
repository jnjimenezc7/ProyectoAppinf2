package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_detalle database table.
 * 
 */
@Embeddable
public class TblDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=20)
	private String codigoproducto;

	@Column(name="id_factura", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idFactura;

	public TblDetallePK() {
	}
	public String getCodigoproducto() {
		return this.codigoproducto;
	}
	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}
	public Integer getIdFactura() {
		return this.idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblDetallePK)) {
			return false;
		}
		TblDetallePK castOther = (TblDetallePK)other;
		return 
			this.codigoproducto.equals(castOther.codigoproducto)
			&& this.idFactura.equals(castOther.idFactura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoproducto.hashCode();
		hash = hash * prime + this.idFactura.hashCode();
		
		return hash;
	}
}