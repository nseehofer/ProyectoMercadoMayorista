package ar.unlam.edu.pbII.mercadoMayorista;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MercadoMayorista implements Reportador{

	private static Double impuestoPorProducto = 0.21;
	private static Double impuestoPorProductoEspecial = 0.15;
	private Map<Producto, Integer> inventarioDeProductos = new TreeMap<Producto, Integer>();
	private Set<Venta> ventasRealizadas = new HashSet<Venta>();;

	public void añadirProductoAlInventario(Producto producto, Integer cantidad) {
		this.inventarioDeProductos.put(producto, cantidad);
	}

	public Double pedirImpuesto(Producto producto) {
		Double impuestoARetornar = 0.0;

		if (producto.getClass().getSimpleName().equals("PerfumeDeLujo")
				|| producto.getClass().getSimpleName().equals("Bebida")) {

			impuestoARetornar = calcularYSumarImpuestosAProductoEspecial(producto);
		} else {
			impuestoARetornar = calcularYSumarImpuestoAProducto(producto);
		}

		return impuestoARetornar;
	}

	private Double calcularYSumarImpuestoAProducto(Producto producto) {
		Double impuestoARetornar;
		Double precioActualProducto = producto.verPrecio();
		impuestoARetornar = precioActualProducto * impuestoPorProducto;
		return impuestoARetornar;
	}

	private Double calcularYSumarImpuestosAProductoEspecial(Producto producto) {
		Double impuestoARetornar;
		Double precioActualProducto = producto.verPrecio();
		impuestoARetornar = (precioActualProducto * impuestoPorProducto)
				+ (precioActualProducto * impuestoPorProductoEspecial);
		return impuestoARetornar;
	}

	public void venderProducto(Venta venta, Producto producto, Integer cantidad) {
		Integer cantidadDeProducto = this.inventarioDeProductos.get(producto);
		if (cantidadDeProducto != null && venta != null) {
			actualizarInventarioDeProductos(producto, cantidad, cantidadDeProducto);
			venta.agregarProductoYCantidad(producto, cantidad);
		} else if (cantidadDeProducto != null && venta == null) {
			actualizarInventarioDeProductos(producto, cantidad, cantidadDeProducto);
			generarNuevaVenta().agregarProductoYCantidad(producto, cantidad);
		}

	}

	private void actualizarInventarioDeProductos(Producto producto, Integer cantidad, Integer valueDeKey) {
		Integer cantidadActualizada = valueDeKey - cantidad;
		this.inventarioDeProductos.put(producto, cantidadActualizada);
	}

	public Venta generarNuevaVenta() {
		Venta nuevaVenta = new Venta();
		this.ventasRealizadas.add(nuevaVenta);
		return nuevaVenta;
	}

	public Double totalPorDetalle(Venta venta, Producto mermelada) {
		Double precioDelProducto = 0.0;

		if (this.ventasRealizadas.contains(venta)) {
			precioDelProducto = venta.precioDelProducto(mermelada);
		} else {
			// no se realizo esa venta
		}
		return precioDelProducto;
	}

	public Double totalDeFactura(Venta venta) {
		Double precioTotalDeFactura = 0.0;

		if (this.ventasRealizadas.contains(venta)) {
			precioTotalDeFactura = venta.precioTotal();
		} else {
			// no se realizo esa venta
		}
		return precioTotalDeFactura;
	}

	public void actualizarCantidadDeProductoEnInventario(Producto producto, Integer cantidad) {
		Integer cantidadActual = this.inventarioDeProductos.get(producto);
		if (cantidadActual != null) {
			Integer cantidadActualizada = cantidadActual + cantidad;
			this.inventarioDeProductos.put(producto, cantidadActualizada);
		} else {// NO EXISTE ESTE PRODUCTO EN INVENTARIO
		}
	}

	public Integer verStockDeProducto(Producto producto) throws ProductoInexistenteEnElInventario {
		Integer stockActual = this.inventarioDeProductos.get(producto);
		if(stockActual != null) {
			return stockActual;
		} else { 
			throw new ProductoInexistenteEnElInventario("No contamos con este producto en el inventario");
		}
	}
	

	public String generarFactura(Venta venta) {
		Factura nuevaFactura = new Factura(venta);
		venta.verProductosVendidos();
		nuevaFactura.setProductosVendidos(venta.verProductosVendidos());
		return reportar(nuevaFactura);
	}

	@Override
	public String reportar(Factura factura) {
		
		return factura.getProductosVendidos().toString() ;
	}
	
	
}
