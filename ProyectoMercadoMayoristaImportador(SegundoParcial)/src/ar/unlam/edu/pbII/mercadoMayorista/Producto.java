package ar.unlam.edu.pbII.mercadoMayorista;

public class Producto implements Comparable<Producto> {

	private static Integer contadorID = 0;
	protected Integer id;
	protected Double precio;

	public Producto() {
		this.id = contadorID++;
	}

	public Double verPrecio() {
		return this.precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Producto producto) {
		return this.id.compareTo(producto.id);
	}

	@Override
	public String toString() {
		return "Producto: " + this.getClass().getSimpleName() + " Precio Unitario:  " + this.verPrecio().toString() + " Cantidad: " ;
	}

}
