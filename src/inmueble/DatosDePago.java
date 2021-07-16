package inmueble;


import usuario.Usuario;

public class DatosDePago {

	/**
	 * Contiene la informacion pertinente para realizar un pago
	 */
	private FormaDePago formaDePago;
	private String nombreCompleto;
	private String direccion;
	private String email;
	
	public DatosDePago(FormaDePago formaDePago, String nombreCompleto, String direccion, String email) {
		this.formaDePago = formaDePago;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.email = email;
	}

	/**
	 * 
	 * @param inmueble
	 * @return retorna true si las formas de pago admitidas por el inmueble incluyen las de los datos de pago
	 */
	public Boolean sonDatosAdmitidosPara(Inmueble inmueble) {
		return inmueble.getFormasDePago().contains(this.formaDePago);
	}

}
