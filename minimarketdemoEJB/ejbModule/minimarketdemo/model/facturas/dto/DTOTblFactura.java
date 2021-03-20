package minimarketdemo.model.facturas.dto;

import java.util.Date;

public class DTOTblFactura {

	
	private  String numeroFactura;
	private  String identificacion;
	private double valorFactura;
	private Date fechaFactura;
	
	

	public DTOTblFactura(String numeroFactura, String identificacion, double valorFactura, Date fechaFactura) {
		
		this.numeroFactura = numeroFactura;
		this.identificacion = identificacion;
		this.valorFactura = valorFactura;
		this.fechaFactura = fechaFactura;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}	

	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public double getValorFactura() {
		return valorFactura;
	}
	public void setValorFactura(double valorFactura) {
		this.valorFactura = valorFactura;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	
	
}
