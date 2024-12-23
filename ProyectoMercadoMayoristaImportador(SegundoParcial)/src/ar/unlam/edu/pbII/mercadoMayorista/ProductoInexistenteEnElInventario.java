package ar.unlam.edu.pbII.mercadoMayorista;

public class ProductoInexistenteEnElInventario extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductoInexistenteEnElInventario(String mensaje) {
		super(mensaje);
	}

}
