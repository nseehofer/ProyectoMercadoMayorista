package ar.unlam.edu.pbII.mercadoMayorista;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Test;

public class MercadoMayoristaTest {


	@Test
	public void queSePuedePedirImpuestoSobreProducto() {
		MercadoMayorista mercado = new MercadoMayorista();
		Producto mermelada = new Mermelada(20.50);
		Producto jabonLiquido = new Limpieza(55.25);
		Producto cocaCola = new Bebida(30.00);
		Producto lataDeArveja = new AlimentoNoPerecedero(15.50);
		Producto perfumeArabe = new PerfumeDeLujo(350.00);

		mercado.añadirProductoAlInventario(mermelada, 5);
		mercado.añadirProductoAlInventario(jabonLiquido, 10);
		mercado.añadirProductoAlInventario(cocaCola, 25);
		mercado.añadirProductoAlInventario(lataDeArveja, 47);
		mercado.añadirProductoAlInventario(perfumeArabe, 3);

		Double costoAdicional = mercado.pedirImpuesto(jabonLiquido);
		Double resultadoEsperado = 55.25 * 0.21;
		Double resultadoObtenido = costoAdicional;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queSePuedePedirImpuestoSobreProductoDeLujo() {
		MercadoMayorista mercado = new MercadoMayorista();

		Producto perfumeArabe = new PerfumeDeLujo(350.00);
		mercado.añadirProductoAlInventario(perfumeArabe, 3);
		Double costoAdicional = mercado.pedirImpuesto(perfumeArabe);
		Double resultadoEsperado = 350.00 * 0.15 + 350.00 * 0.21;
		Double resultadoObtenido = costoAdicional;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queSePuedeCalcularElTotalPorDetalleYTotalDeFactura() {
		MercadoMayorista mercado = new MercadoMayorista();
		Producto mermelada = new Mermelada(20.50);
		Producto jabonLiquido = new Limpieza(55.25);
		Producto cocaCola = new Bebida(30.00);
		Producto lataDeArveja = new AlimentoNoPerecedero(15.50);
		Producto perfumeArabe = new PerfumeDeLujo(350.00);

		mercado.añadirProductoAlInventario(mermelada, 5);
		mercado.añadirProductoAlInventario(jabonLiquido, 10);
		mercado.añadirProductoAlInventario(cocaCola, 25);
		mercado.añadirProductoAlInventario(lataDeArveja, 47);
		mercado.añadirProductoAlInventario(perfumeArabe, 3);

		Venta venta = mercado.generarNuevaVenta();

		mercado.venderProducto(venta, mermelada, 2);
		mercado.venderProducto(venta, cocaCola, 3);
		mercado.venderProducto(venta, lataDeArveja, 4);

		Double totalPorDetalleDeVentaMermelada = mercado.totalPorDetalle(venta, mermelada);
		Double totalPorDetalleDeVenta = mercado.totalDeFactura(venta);

		Double resultadoEsperado = 41.0;
		Double resultadoEsperado2 = 193.0;
		Double resultadoObtenido = totalPorDetalleDeVentaMermelada;
		Double resultadoObtenido2 = totalPorDetalleDeVenta;

		assertEquals(resultadoEsperado, resultadoObtenido);
		assertEquals(resultadoEsperado2, resultadoObtenido2);

	}

	@Test
	public void queSePuedaActualizarLaCantidadDeUnProductoEnElInventario() throws ProductoInexistenteEnElInventario {
		MercadoMayorista mercado = new MercadoMayorista();
		Producto mermelada = new Mermelada(20.50);

		mercado.añadirProductoAlInventario(mermelada, 5);
		mercado.actualizarCantidadDeProductoEnInventario(mermelada, 24);

		Integer cantidadProducto = mercado.verStockDeProducto(mermelada);
		Integer resultadoEsperado = 29;
		Integer resultadoObtenido = cantidadProducto;
		assertEquals(resultadoEsperado, resultadoObtenido);

	}

	@Test(expected = ProductoInexistenteEnElInventario.class)
	public void queSeLanceUnaExcepcionCuandoSeIntenteAccederAUnProductoInexistenteEnElInventario()
			throws ProductoInexistenteEnElInventario {
		MercadoMayorista mercado = new MercadoMayorista();
		Producto mermelada = new Mermelada(20.50);
		mercado.verStockDeProducto(mermelada);
	}
	
	@Test
	public void generarFacturaDeVentaRealizada() {
		MercadoMayorista mercado = new MercadoMayorista();
		Producto mermelada = new Mermelada(20.50);
		Producto cocaCola = new Bebida(30.00);
		Producto lataDeArveja = new AlimentoNoPerecedero(15.50);
		
		mercado.añadirProductoAlInventario(mermelada, 5);
		mercado.añadirProductoAlInventario(cocaCola, 25);
		mercado.añadirProductoAlInventario(lataDeArveja, 47);
	

		Venta venta = mercado.generarNuevaVenta();

		mercado.venderProducto(venta, mermelada, 2);
		mercado.venderProducto(venta, cocaCola, 3);
		mercado.venderProducto(venta, lataDeArveja, 4);
		
		mercado.generarFactura(venta);
		
		
	}
	
	
}
