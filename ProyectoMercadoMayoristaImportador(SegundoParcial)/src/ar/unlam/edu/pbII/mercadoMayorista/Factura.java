package ar.unlam.edu.pbII.mercadoMayorista;

import java.util.Map;
import java.util.TreeMap;

public class Factura {
	private Map<Producto,Integer> productosVendidos = new TreeMap<Producto,Integer>();
	private Integer id;
	private Venta venta;
	private static Integer contadorID = 0;

	public Factura(Venta venta) {
		this.id=contadorID ++;
		this.venta = venta;
	}
	

	@Override
	public String toString() {
		return "Factura [productosVendidos=" + productosVendidos + "]";
	}

	public Map<Producto, Integer> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(Map<Producto, Integer> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}


	public Venta getVenta() {
		return venta;
	}


	
	
	
}
