package ar.edu.unlam.pb1.final2021.dominio;

public class EntidadFinanciera {
	
	private double TIPO_DE_CAMBIO_DOLAR_COMPRA = 89.00;
	private double TIPO_DE_CAMBIO_DOLAR_VENTA = 95.00;
	private double TIPO_DE_CAMBIO_EURO_COMPRA = 106.50;
	private double TIPO_DE_CAMBIO_EURO_VENTA = 112.50;
	private double TIPO_DE_CAMBIO_REAL_COMPRA = 14.90;
	private double TIPO_DE_CAMBIO_REAL_VENTA = 16.90;
	private double MONTO_MAXIMO_PARA_VENTA = 100000000.0;
	private Cliente clientes[];
	private Billete billetes[];
	private String nombre;
	private final int CANTIDAD_MAXIMA_DE_CLIENTES=1000;
	private final int CANTIDAD_MAXIMA_DE_BILLETES=5000000;
	private int billetesIngresados;
	private int clientesIngresados;
	
	public EntidadFinanciera(String nombre) {
		/*
		 * Se deben realizar las operaciones para garantizar el correcto funcionamiento de los objetos
		 * de la clase
		 */	
		this.nombre=nombre;
		this.billetes=new Billete[CANTIDAD_MAXIMA_DE_BILLETES];
		this.clientes=new Cliente[CANTIDAD_MAXIMA_DE_CLIENTES];
		this.clientesIngresados=0;
		this.billetesIngresados=0;
	}
	
	public void ingresarNuevoCliente(Cliente nuevo) {
		/*
		 * Se incorpora el cliente recibido por parametro a la lista de clientes de la entidad.
		 */	
		for (int i = 0; i < clientes.length; i++) {
			if(clientes[i]==null && clientesIngresados<CANTIDAD_MAXIMA_DE_CLIENTES) {
				clientes[clientesIngresados++]=nuevo;
			}
		}
	}
	
	public void ingresarNuevoLoteDeBilletes(Moneda moneda, int cantidadDeBilletes) {
		/*
		 * Debe registrar la cantidad de billetes indicados por parámetro, para la moneda indicada.
		 * El número de serie de cada billete se debe calcular de formma aleatoria a través del método 
		 * obtenerNumeroDeSerie.
		 */
		for (int i = 0; i< CANTIDAD_MAXIMA_DE_BILLETES ; i++) {
			if(billetes[i]==null && (cantidadDeBilletes+billetesIngresados)<CANTIDAD_MAXIMA_DE_BILLETES) {
				billetes[billetesIngresados++]= new Billete(moneda, darValorDeBillete(moneda), obtenerNumeroDeSerie(moneda));
			}
		}
	}
	
	private int darValorDeBillete(Moneda moneda) {
		int valorDeBillete=0;
		valorDeBillete=((int)(Math.random()*1000)+1);
		return valorDeBillete;
	}

	private long obtenerNumeroDeSerie(Moneda moneda) { 
		/*
		 * Calcula un número aleatorio entre 1 y 999.999.999. 
		 */		
		long numeroDeSerie=0l;
		numeroDeSerie= ((long)(Math.random()*999999999)+1);
		return numeroDeSerie;
	}
	
	public double realizarTransaccion(long cuil, Moneda moneda, Operacion operacion, int importe) {
		/*
		 * Se intenta realizar la operación con los datos recibidos. Se devuelve 0.0 si la operación no se puede realizar, o el valor 
		 * en pesos que resulta de hacer la conversión.
		 * 1. Se busca el cliente en el listado de clientes de la entidad (Invocar al método buscarCliente).
		 * 2. Se valida que el cliente esté habilitado para realizar una transascción.
		 * 3. Si la operación es de compra, se verifica si la entidad cuenta con el importe ingresado (Invocar al método verificarImporte)
		 * 4. No es necesario marcar los billtes como entregados ni asignar los billetes utilizados en la transacción al cliente. Eso
		 * se desarrollará en la siguiente fase cuando se cuente con el hardware necesario.
		 */
		
		if(buscarCliente(cuil).isHabilitadoParaRealizarTransacciones()){
			switch(operacion) {
			case Compra :		
				if(verificarImporte(moneda, importe)) {
					return realizarConversionCompra(moneda, importe);
				}	
				break;
				
			case Venta :
				if(importe<MONTO_MAXIMO_PARA_VENTA) {
					return realizarConversionVenta(moneda, importe);
				}
				break;
			}
		}
		return 0.0;
	}
	
	private double realizarConversionCompra(Moneda moneda,int importe) {
		double valorConvertido=0.0;
		switch(moneda) {
		case Peso:
			valorConvertido=importe;
			break;
		case Dolar:
			valorConvertido= importe/TIPO_DE_CAMBIO_DOLAR_COMPRA;
			break;
		case Euro:
			valorConvertido=importe/TIPO_DE_CAMBIO_EURO_COMPRA;
			break;
		case Real:
			valorConvertido=importe/TIPO_DE_CAMBIO_REAL_COMPRA;
			break;
		}
		return valorConvertido;
	}

	private double realizarConversionVenta(Moneda moneda,int importe) {
		double valorConvertido=0.0;
		switch(moneda) {
		case Peso:
			valorConvertido=importe;
			break;
		case Dolar:
			valorConvertido= importe/TIPO_DE_CAMBIO_DOLAR_VENTA;
			break;
		case Euro:
			valorConvertido=importe/TIPO_DE_CAMBIO_EURO_VENTA;
			break;
		case Real:
			valorConvertido=importe/TIPO_DE_CAMBIO_REAL_VENTA;
			break;
			
		}
		return valorConvertido;
	}
	private Cliente buscarCliente(long cuil) {
		/*
		 * Busca un cliente por CUIL. Devuelve el cliente encontrado o null ni no existe.
		 */
		for (int i = 0; i < clientes.length; i++) {
			if(clientes[i]!=null && clientes[i].getCuil()==cuil) {
				return clientes[i];
			}
		}
		return null;
	}
	
	private boolean verificarImporte(Moneda moneda, int importe) {
		/*
		 * Verifica que la entidad cuente con el importe de la moneda recibida por parámetro.
		 * El objetivo es verificar si los billetes ingresados son suficientes para realizar la operación sin importar si se realizaron
		 * tranascciones previas (las transacciones no disminuye la cantidad de billetes). 
		 */
		
		int importeAcumulado=0;
		
		for (int i = 0; i < billetes.length; i++) {
			if(billetes[i]!=null && billetes[i].getMoneda().equals(moneda)) {
				importeAcumulado+=billetes[i].getValor();
			}
		}
		return (importeAcumulado>=importe);
	}
	
	public String listarTransaccionesDelCliente(long cuil) {
		/*
		 * Se invoca el método buscarCliente con el cuil recibido por parámetro. Si se encuentra se devuelve el resultado de invocar
		 * al método listarTransaccionesRealizadas de ese cliente. Caso contrario se devuelve ""
		 */
		if(buscarCliente(cuil)!=null) {
			return buscarCliente(cuil).listarTransaccionesRealizadas();
		}else {		return ""; }
	}

}



