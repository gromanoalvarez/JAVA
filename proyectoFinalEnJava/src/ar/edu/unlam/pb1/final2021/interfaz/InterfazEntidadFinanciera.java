package ar.edu.unlam.pb1.final2021.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.final2021.dominio.Cliente;
import ar.edu.unlam.pb1.final2021.dominio.EntidadFinanciera;
import ar.edu.unlam.pb1.final2021.dominio.Moneda;
import ar.edu.unlam.pb1.final2021.dominio.Operacion;

public class InterfazEntidadFinanciera {

	private static Moneda monedaSeleccionada;
	private static Operacion operacionSeleccionada;

	public static void main(String[] args) {
		
		EntidadFinanciera actual = new EntidadFinanciera("Casa Sazon");
		int opcion = 0;

		System.out.println("Bienvenido ");
		do {
			opcion = seleccionarOpcion();
			switch (opcion) {
			case 1: 
				darDeAltaUnCliente(actual);
				break;
			case 2: 
				ingresarBilletes(actual);
				break;
			case 3:
				realizarUnaTransaccion(actual);
				break;
			case 4:
				listarLasOperacionesDeUnCliente(actual);
				break;
			case 9:
				break;
			default:
				System.out.println("Opción Invalida");
				break;
			}

		} while (opcion != 9);
	}
	
	private static int seleccionarOpcion() {
		Scanner teclado = new Scanner(System.in);
		int opcionSeleccionada=0;
		
		System.out.println("************************");
		System.out.println("Menu de opciones\n");
		System.out.println("1 - Dar de alta un cliente");
		System.out.println("2 - Ingresar un lote de billetes");
		System.out.println("3 - Realizara una transaccion");
		System.out.println("4 - Listar las operaciones de un cliente");
		System.out.println("9 - Salir");
		System.out.println("************************");
		System.out.println("Ingrese una opcion");
		
		opcionSeleccionada = teclado.nextInt();
		
		return opcionSeleccionada;
	}
	
	private static void darDeAltaUnCliente(EntidadFinanciera actual) {
		/*
		 * Interfaz de usuario encargada de dar de alta un cliente agregandolo a la entidad financiera. 
		 * Para esto se debe invocar al metodo ingresarNuevoCliente del objeto actual
		 */
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese el nombre");
		String nombre=teclado.next();
		System.out.println("Ingrese el apellido");
		String apellido =teclado.next();
		System.out.println("Ingrese el numero de cuil");
		long cuil=teclado.nextLong();
		boolean habilitado=true;
		Cliente nuevo= new Cliente(nombre, apellido, cuil, habilitado);
		actual.ingresarNuevoCliente(nuevo);
	}

	private static void ingresarBilletes(EntidadFinanciera actual) {
		/*
		 * Interfaz encargada de registrar nuevos billetes. Se debe ingresar la moneda y la cantidad de billetes.
		 * Luego invocar el método ingresarNuevoLoteDeBilletes para que se registren en la entidad.
		 */
		Scanner teclado = new Scanner(System.in);
		
		Moneda moneda = ingresarMoneda(teclado);
		System.out.println("ingrese cantidad de billetes");
		int cantidadDeBilletes=teclado.nextInt();

		actual.ingresarNuevoLoteDeBilletes(moneda, cantidadDeBilletes);
	}
	

	private static Moneda ingresarMoneda(Scanner teclado) {
		
		System.out.println("ingrese el tipo de moneda: \n "
				+ "1.peso"
				+ "2.dolar"
				+ "3.euro"
				+ "4.real");
		int tipoMoneda = teclado.nextInt();
		switch(tipoMoneda) {
		case 1: 
			return monedaSeleccionada.Peso;
		case 2: 
			return monedaSeleccionada.Dolar;
		case 3:  
			return monedaSeleccionada.Euro;
		case 4: 
			return monedaSeleccionada.Real;
		default :System.out.println("opcion incorrecta, intente nuevamente");
		}
		return monedaSeleccionada;
	}
	
	private static void realizarUnaTransaccion(EntidadFinanciera actual) {
		
		/*
		 * Interfaz encargada de realizar una transacción.
		 * 1. Se ingresa el CUIL del cliente
		 * 2. Se ingresa la moneda, la cantidad y el tipo de operación (compra o venta)
		 * 3. Se invoca al método realizarTransaccion
		 * 4. Se informa el resultado de la operación (valor en pesos de la conversión)
		 */
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("ingrese cuil del cliente");
		long cuil=teclado.nextLong();
		
		Moneda moneda=ingresarMoneda(teclado);
		
		Operacion operacion = ingresarTipoDeOperacion(teclado);
		
		System.out.println("ingrese importe");
		int importe=teclado.nextInt();
		
		System.out.println(actual.realizarTransaccion(cuil, moneda, operacion, importe));
	}

	private static Operacion ingresarTipoDeOperacion(Scanner teclado) {
		System.out.println("ingrese tipo de operacion \n"
				+ "1.compra"
				+ "2.venta");
		int tipo=teclado.nextInt();
		switch(tipo) {
		case 1: 
			return operacionSeleccionada.Compra;
		case 2: 
			return operacionSeleccionada.Venta;
		default: System.out.println("opcion incorrecta, intente nuevamente");
		}
		return operacionSeleccionada;
	}
	
	private static void listarLasOperacionesDeUnCliente(EntidadFinanciera actual) {
		/*
		 * Se debe listar las transacciones de un cliente.
		 * 1. Se ingresa el número de CUIL
		 * 2. Se invoca el método listarTransaccionesDelCliente de la entididad actual
		 * 3. Se muestra por pantalla el resultado que devuelve dicho método.
		 */
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("ingrese cuil del cliente");
		long cuil=teclado.nextLong();
		
		System.out.println(actual.listarTransaccionesDelCliente(cuil));
	}
}
