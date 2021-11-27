package ar.edu.unlam.pb1.final2021.dominio;

import java.util.Arrays;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private long cuil;
	private boolean habilitadoParaRealizarTransacciones;
	private final int CANTIDAD_DE_TRANSACCIONES_POSIBLES=30;
	
	private Transaccion transaccionesRealizadas[];
	private int cantidadDeTransaccionesHechas;
	
	
	public Cliente (String nombre, String apellido, long cuil, boolean habilitadoParaRealizarTransacciones) {
		/*
		 * Constructor de la clase. Se deben generar las acciones para garantizar el correcto funcionamiento de los objetos de la misma.
		 */
		
		this.nombre=nombre;
		this.apellido=apellido;
		this.cuil=cuil;
		this.habilitadoParaRealizarTransacciones=habilitadoParaRealizarTransacciones;
		this.transaccionesRealizadas= new Transaccion[CANTIDAD_DE_TRANSACCIONES_POSIBLES];
		this.cantidadDeTransaccionesHechas=0;
	}
	
	public void registrarNuevaTransaccion(Operacion operacion, Moneda moneda, int cantidad) {
		/*
		 * Se crea un objeto de tipo Transaccion con los parámetros recibidos, y se almacena en la lista de transacciones del cliente.
		 */
		
		for (int i = 0; i < transaccionesRealizadas.length; i++) {
			if(transaccionesRealizadas[i]==null && cantidadDeTransaccionesHechas<CANTIDAD_DE_TRANSACCIONES_POSIBLES) {
				transaccionesRealizadas[cantidadDeTransaccionesHechas++]= new Transaccion(operacion, moneda, cantidad);
			}	
		}
	}
	
	public String listarTransaccionesRealizadas() {
		/*
		 * Se genera una variable de tipo String en donde se concatenan las transacciones realiazadas por el cliente.
		 * Se devuelve el resultado de dicha variable.
		 */
		
		String resultado = "TRANSACCIONES REALIZADAS:\n";
		
		for (int i = 0; i < transaccionesRealizadas.length; i++) {
			if(transaccionesRealizadas[i]!=null) {
				resultado += transaccionesRealizadas[i].toString();
				resultado += "\n";
			}
		}
		return resultado;
	}
	
	//Getters and setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getCuil() {
		return cuil;
	}

	public void setCuil(long cuil) {
		this.cuil = cuil;
	}

	public boolean isHabilitadoParaRealizarTransacciones() {
		return habilitadoParaRealizarTransacciones;
	}

	public void setHabilitadoParaRealizarTransacciones(boolean habilitadoParaRealizarTransacciones) {
		this.habilitadoParaRealizarTransacciones = habilitadoParaRealizarTransacciones;
	}

	public Transaccion[] getTransaccionesRealizadas() {
		return transaccionesRealizadas;
	}

	public void setTransaccionesRealizadas(Transaccion[] transaccionesRealizadas) {
		this.transaccionesRealizadas = transaccionesRealizadas;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", cuil=" + cuil
				+ ", habilitadoParaRealizarTransacciones=" + habilitadoParaRealizarTransacciones
				+ ", CANTIDAD_DE_TRANSACCIONES_POSIBLES=" + CANTIDAD_DE_TRANSACCIONES_POSIBLES
				+ ", transaccionesRealizadas=" + Arrays.toString(transaccionesRealizadas) + "]";
	}

}
