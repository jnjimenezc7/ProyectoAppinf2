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

	@Column(name="id_factura", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idFactura;

	@Column(name="id_producto", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idProducto;

	public TblDetallePK() {
	}
	public Integer getIdFactura() {
		return this.idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public Integer getIdProducto() {
		return this.idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
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
			this.idFactura.equals(castOther.idFactura)
			&& this.idProducto.equals(castOther.idProducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFactura.hashCode();
		hash = hash * prime + this.idProducto.hashCode();
		
		return hash;
	}
}