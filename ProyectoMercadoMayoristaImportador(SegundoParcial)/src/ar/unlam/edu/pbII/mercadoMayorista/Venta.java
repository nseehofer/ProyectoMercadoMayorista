package ar.unlam.edu.pbII.mercadoMayorista;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Venta implements Comparable<Venta> {
	private Map<Producto, Integer> productosVendidos = new TreeMap<Producto,Integer>();
	private static Integer contadorID = 0;
	private Integer idVenta;

	public Venta() {
		idVenta = contadorID++;
	}

	public void agregarProductoYCantidad(Producto producto, Integer cantidad) {
		this.productosVendidos.put(producto, cantidad);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
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
		Venta other = (Venta) obj;
		if (idVenta == null) {
			if (other.idVenta != null)
				return false;
		} else if (!idVenta.equals(other.idVenta))
			return false;
		return true;
	}

	@Override
	public int compareTo(Venta otraVenta) {
		return this.idVenta.compareTo(otraVenta.idVenta);
	}

	@Override
	public String toString() {
		return "Venta [productosVendidos=" + productosVendidos + "]";
	}

	public Double precioDelProducto(Producto producto) {
		Integer cantidadDelProdcutoVendido = this.productosVendidos.get(producto);
		Double precioDelProductoVendido = 0.0;
		if(cantidadDelProdcutoVendido!= null) {
			precioDelProductoVendido = (producto.verPrecio() * cantidadDelProdcutoVendido);
		} else {
			// ESE PRODUCTO NO EXISTE EN ESA VENTA 
		}
		return precioDelProductoVendido;
	}
	

	public Double precioTotal() {
		Double precioTotal = 0.0;
		Iterator <Producto> iteradorProductos = this.productosVendidos.keySet().iterator();
		while(iteradorProductos.hasNext()) {
			Producto producto = iteradorProductos.next();
			Integer cantidadDeProducto = this.productosVendidos.get(producto);
			precioTotal += (producto.verPrecio() * cantidadDeProducto);
		}
		
		return precioTotal;
	}
	/*

	@Override
	public String reportar(Factura factura) {
		factura.setProductosVendidos(this.productosVendidos);
		return factura.getProductosVendidos().toString();
	}*/

	public Map<Producto, Integer> verProductosVendidos() {
		return productosVendidos;
	}

	
	
	
	

}
