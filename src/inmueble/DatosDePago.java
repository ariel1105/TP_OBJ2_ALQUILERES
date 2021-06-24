package inmueble;


import usuario.Usuario;

public class DatosDePago {
	
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

	public void abonar(Usuario dueño, double monto) {		
	}

	public Boolean sonDatosAdmitidosPara(Inmueble inmueble) {
		return inmueble.getFormasDePago().contains(this.formaDePago);
	}

}
