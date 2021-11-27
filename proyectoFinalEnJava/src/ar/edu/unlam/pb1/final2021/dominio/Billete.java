package ar.edu.unlam.pb1.final2021.dominio;

public class Billete {
	
	private Moneda moneda;
	private int valor;
	private long numeroDeSerie;
	
	public Billete(Moneda moneda, int valor, long numeroDeSerie) {
		/*
		 * Contructor de la clase. Inicializar los atributos de los objetos de la misma.
		 */
		
		this.moneda= moneda;
		this.valor=valor;
		this.numeroDeSerie=numeroDeSerie;
	}
	
	public Moneda getMoneda() {
		return moneda;
	}

	public int getValor() {
		return valor;
	}

	public long getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public String toString() {
		/*
		 * Devuelve en forma de String información que denote el estado del objeto.
		 */	
		
		return "Billete [moneda= " + moneda + ", valor= " + valor + ", numeroDeSerie= " + numeroDeSerie + "]";
	}
}
