package ar.edu.unlam.pb1.final2021.dominio;

public class Transaccion {
	
	private Operacion operacion;
	private Moneda moneda;
	private int cantidad;
	
	public Transaccion(Operacion operacion, Moneda moneda, int cantidad) {
		this.operacion = operacion;
		this.moneda = moneda;
		this.cantidad = cantidad;
	}
	
	public String toString() {
		return "Operacion " + this.operacion + " - Moneda " + this.moneda + " - Cantidad " + this.cantidad;
	}

}
