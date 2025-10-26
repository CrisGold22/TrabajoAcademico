package pe.edu.pucp.inf30.softprogpruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import pe.edu.pucp.inf30.softprog.dao.pago.ComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.ComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.LineaComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.CarritoComprasDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaOrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.OrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.dbmanager.*;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.pago.utils.MetodoPago;
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Cargo;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.CategoriaCliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Jerarquia;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoCategoria;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.UnidadMedida;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoCarrito;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.LineaComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.AdministradorSistemaBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.ClienteBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.CategoriaProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.DescuentoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.ProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.CarritoComprasBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.DetalleEnvioBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaCarritoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaOrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.ComprobantePagoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.LineaComprobantePagoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.AdministradorSistemaBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.ClienteBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.CuentaUsuarioBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.CategoriaProductoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.DescuentoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.ProductoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.CarritoComprasBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.DetalleEnvioBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.LineaCarritoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.LineaOrdenCompraBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.OrdenCompraBOImpl;

/**
 *
 * @author Cristhian Horacio
 */
public class SoftProgPruebas {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        DBManager dbManager = DBFactoryProvider.getManager(); 
        try(Connection connection = dbManager.getConnection()){
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexion establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexion.");
            }
        }
        catch(SQLException e){
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        System.out.println();
        
        System.out.println("=====================================");
        System.out.println("Empezando con el proceso de prueba");
        System.out.println("=====================================\n \n");
        
        
        //Detalle De Envio
        System.out.println("Ejecucion de procedures de Detalle de envio");
        System.out.println("=====================================\n");
        DetalleEnvio detalle = new DetalleEnvio();
        DetalleEnvioBO detalleEnvioBO = new DetalleEnvioBOImpl();
        
        Date fecha = new Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        
        detalle.setId(1);
        detalle.setDescripcion("Pago con exito");
        detalle.setFechaEntrega(fechaSQL);
        detalle.setHorarioEntrega(fechaSQL);
        detalle.setDistrito(Distrito.LINCE);
        detalle.setDireccion("Av. Donde vive Galileo");
        
        detalleEnvioBO.insertar(detalle);
        System.out.println("Se inserto el detalle de envio de id " + detalle.getId() + "creado exitosamente");
        detalle.setDescripcion("hola :)");
        detalleEnvioBO.actualizar(detalle);
        System.out.println("Se actualizo el detalle de envio de id " + detalle.getId() + "creado exitosamente");
        DetalleEnvio detalle2 = detalleEnvioBO.obtener(1);
        System.out.println("Detalle de envio se llamo al backend de manera correcta\n");
        
        
		
	//Cliente
        System.out.println("Ejecucion de procedures de Cliente");
        System.out.println("=====================================\n");
        Cliente cliente = new Cliente();
        ClienteBO clienteBO = new ClienteBOImpl();
        Date fechaNacimiento = new Date();
        java.sql.Date nacimientoSQL = new java.sql.Date(fechaNacimiento.getTime());
        
        cliente.setId(1);
        cliente.setDni("72233478");
        cliente.setNombre("Cristhian Horacio");
        cliente.setApellidoMaterno("Olivares");
        cliente.setApellidoPaterno("Gupioc");
        cliente.setGenero(Genero.HOMBRE);
        cliente.setFechaNacimiento(nacimientoSQL);
        cliente.setTelefono(981429641);
        cliente.setLineaCredito(10000);
        cliente.setCategoria(CategoriaCliente.CATERING);
        cliente.setNumeroPedidosHistorico(12);
        cliente.setNumeroPedidosMensualPro(10);
        cliente.setActivo(true);
        
        clienteBO.insertar(cliente);
        System.out.println("Se inserto el cliente de id " + cliente.getId() + " creado exitosamente");
        cliente.setTelefono(941993577);
        clienteBO.actualizar(cliente);
        System.out.println("El cliente se actualizado exitosamente");
        Cliente cliente2 = clienteBO.obtener(cliente.getId());
        System.out.println("El cliente se llamo al backend de manera correcta\n");

        
        
        
	//Administrador
        System.out.println("Ejecucion de procedures de Administrador");
        System.out.println("=====================================\n");
        AdministradorSistema admin = new AdministradorSistema();
        AdministradorSistemaBO adminBO = new AdministradorSistemaBOImpl();
        
        admin.setId(1);
        admin.setDni("72233478");
        admin.setNombre("Cristhian Horacio");
        admin.setApellidoMaterno("Olivares");
        admin.setApellidoPaterno("Gupioc");
        admin.setGenero(Genero.HOMBRE);
        admin.setFechaNacimiento(nacimientoSQL);
        admin.setTelefono(981429641);        
        admin.setActivo(true);
        
        admin.setCargo(Cargo.ADMINISTRADOR);
        admin.setSueldo(5000);
        admin.setRango(Jerarquia.PARCIAL);
        
        adminBO.insertar(admin);
        System.out.println("Se inserto el Administrador de id " + admin.getId() + " creado exitosamente");
        admin.setTelefono(941993577);
        adminBO.actualizar(admin);
        System.out.println("El Administrador se ha actualizado exitosamente");
        AdministradorSistema admin2 = adminBO.obtener(admin.getId());
        System.out.println("El Administrador se llamo al backend de manera correcta\n");
        
		
        
        
        
        //Cuenta Usuario
        System.out.println("Ejecucion de procedures de Cuenta de Usuario");
        System.out.println("=====================================\n");
        CuentaUsuario cuenta = new CuentaUsuario();
        CuentaUsuarioBO cuentaBO = new CuentaUsuarioBOImpl();
        
        cuenta.setId(1);
        cuenta.setActivo(true);
        cuenta.setUsername("CrisGold");
        cuenta.setPassword("Killzone2003");
        cuenta.setIdAdministrador(1);
        cuenta.setIdCliente(1);
        
        cuentaBO.insertar(cuenta);
        System.out.println("Se inserto la cuenta de id " + cuenta.getId() + " creado exitosamente");
        cuenta.setPassword("CrisGold2003");
        cuentaBO.actualizar(cuenta);
        System.out.println("La cuenta se ha actualizado exitosamente");
        CuentaUsuario cuenta2 = cuentaBO.obtener(cuenta.getId());
        System.out.println("La cuenta se llamo al backend de manera correcta\n");
        	
		
        
        
	//Carrito de Compras
        System.out.println("Ejecucion de procedures de Carrito de compras");
        System.out.println("=====================================\n");
        CarritoCompras carrito = new CarritoCompras();
        CarritoComprasBO carritoBO = new CarritoComprasBOImpl();
        
        carrito.setId(1);
        carrito.setActivo(true);
        carrito.setEstado(EstadoCarrito.PENDIENTE);
        carrito.setTotalParcial(100);
        carrito.setTotalConDescuento(80);
        carrito.setIdCliente(1);
        
        CarritoComprasDAOImpl carritoDAO = new CarritoComprasDAOImpl();
        carritoDAO.crear(carrito);
        System.out.println("Se inserto la cuenta de id " + carrito.getId() + " creado exitosamente");
        carrito.setEstado(EstadoCarrito.EXPIRADO);
        carritoDAO.actualizar(carrito);
        System.out.println("El carrito de compras se ha actualizado exitosamente");
        CarritoCompras carrito2 = carritoDAO.leer(carrito.getId());
        System.out.println("El carrito de compras se llamo al backend de manera correcta\n");
        
        		
		
		
        
	//Categoria Producto
        System.out.println("Ejecucion de procedures de CategoriaProductos");
        System.out.println("=====================================\n");
	CategoriaProducto categoria = new CategoriaProducto();
        CategoriaProductoBO categoriaBO = new CategoriaProductoBOImpl();
        
        categoria.setId(1);
        categoria.setActivo(true);
        categoria.setDescripcion("Una categoria");
        categoria.setIdCategoriaPadre(0);
        categoria.setNombre(TipoCategoria.HOGAR);
        
        categoriaBO.insertar(categoria);
        System.out.println("Se inserto CategoriaProductos de id " + categoria.getId() + " creado exitosamente");
        categoria.setDescripcion("Nueva descripcion");
        categoriaBO.actualizar(categoria);
        System.out.println("CategoriaProductos se ha actualizado exitosamente");
        CategoriaProducto categoria2 = categoriaBO.obtener(categoria.getId());
        System.out.println("CategoriaProductos se llamo al backend de manera correcta\n");
        
        	
		
		
        
	//Producto
        System.out.println("Ejecucion de procedures de Productos");
        System.out.println("=====================================\n");
        Producto producto = new Producto();
        ProductoBO productoBO = new ProductoBOImpl();
        
        producto.setId(1);
        producto.setActivo(true);
        producto.setNombre("Celular IPhone 20 X Giga Pro Max XXXL");
        producto.setSKU("SEL-123");
        producto.setDescripcion("Celular de ultima generacion");
        producto.setPrecioAlMayor(3500);
        producto.setPrecioUnitario(4000);
        producto.setMedidaAlMayor(UnidadMedida.BOTELLA);
        producto.setStockDisponible(10);
        producto.setStockMaximo(50);
        producto.setStockMinimo(2);
        producto.setIdCategoria(1);
        
        productoBO.insertar(producto);
        System.out.println("Se inserto el producto de id " + producto.getId() + " creado exitosamente");
        producto.setDescripcion("Nueva descripcion");
        productoBO.actualizar(producto);
        System.out.println("El producto se ha actualizado exitosamente");
        Producto producto2 = productoBO.obtener(producto.getId());
        System.out.println("El producto se llamo al backend de manera correcta\n");
        
        	
		
		
        
	//Descuento
        System.out.println("Ejecucion de procedures de Descuento");
        System.out.println("=====================================\n");
	Descuento descuento = new Descuento();
        DescuentoBO descuentoBO = new DescuentoBOImpl();
        
        descuento.setId(1);
        descuento.setActivo(true);
        descuento.setPrecioPorVolumen(2000);
        descuento.setCantidadMax(200);
        descuento.setCantidadMin(20);
        descuento.setIdProducto(1);
        
        descuentoBO.insertar(descuento);
        System.out.println("Se inserto el descuento de id " + descuento.getId() + " creado exitosamente");
        descuento.setCantidadMin(10);
        descuentoBO.actualizar(descuento);
        System.out.println("El descuento se ha actualizado exitosamente");
        Descuento descuento2 = descuentoBO.obtener(descuento.getId());
        System.out.println("El descuento se llamo al backend de manera correcta\n");
        
        	
		
        
        
        
        //Linea Carrito
        System.out.println("Ejecucion de procedures de Linea carrito");
        System.out.println("=====================================\n");
	LineaCarrito lineaCarrito = new LineaCarrito();
        LineaCarritoBO lineaCarritoBO = new LineaCarritoBOImpl();
        
        lineaCarrito.setId(1);
        lineaCarrito.setActivo(true);
        lineaCarrito.setCantidad(2000);
        lineaCarrito.setPrecioVolumen(200);
        lineaCarrito.setIdCarritoCompras(1);
        lineaCarrito.setIdProducto(1);
        lineaCarrito.setSubTotal(2000);
        
        lineaCarritoBO.insertar(lineaCarrito);
        System.out.println("Se inserto Linea carrito de id " + lineaCarrito.getId() + " creado exitosamente");
        lineaCarrito.setCantidad(150);
        lineaCarritoBO.actualizar(lineaCarrito);
        System.out.println("Linea carrito se ha actualizado exitosamente");
        LineaCarrito lineaCarrito2 = lineaCarritoBO.obtener(lineaCarrito.getId());
        System.out.println("Linea carrito se llamo al backend de manera correcta\n");
        	
        
        
        
        //Orden de compra
        System.out.println("Ejecucion de procedures de OrdenCompra");
        System.out.println("=====================================\n");
	OrdenCompra ordenCompra = new OrdenCompra();
        OrdenCompraBO ordenCompraBO = new OrdenCompraBOImpl();
        OrdenCompraDAO ordenCompraDAO = new OrdenCompraDAOImpl();
        
        Date fechaCreacionOrden = new Date();
        java.sql.Date fechaSQLOrden = new java.sql.Date(fechaCreacionOrden.getTime());
        
        ordenCompra.setId(1);
        ordenCompra.setActivo(true);
        ordenCompra.setFechaCreacion(fechaSQLOrden);
        ordenCompra.setTotalFinal(200);
        ordenCompra.setTotalParcial(100);
        ordenCompra.setDescuentoTotal(200);
        ordenCompra.setEstado(EstadoOrdenCompra.ENVIADO);
        ordenCompra.setIdDetalleEnvio(1);
        
        ordenCompraDAO.crear(ordenCompra);
        System.out.println("Se inserto la orden de compra de id " + ordenCompra.getId() + " creado exitosamente");
        ordenCompra.setTotalFinal(300);
        ordenCompraDAO.actualizar(ordenCompra);
        System.out.println("La orden de compra se ha actualizado exitosamente");
        OrdenCompra ordenCompra2 = ordenCompraDAO.leer(ordenCompra.getId());
        System.out.println("La orden de compra se llamo al backend de manera correcta\n");
        
        		
	
        //Linea Orden de compra
        System.out.println("Ejecucion de procedures de Linea orden de compra");
        System.out.println("=====================================\n");
	LineaOrdenCompra lineaOrdenCompra = new LineaOrdenCompra();
        LineaOrdenCompraBO lineaOrdenCompraBO = new LineaOrdenCompraBOImpl();
        
        
        lineaOrdenCompra.setId(1);
        lineaOrdenCompra.setActivo(true);
        lineaOrdenCompra.setCantidad(10);
        lineaOrdenCompra.setPrecioUnitario(200);
        lineaOrdenCompra.setSubTotal(200);
        lineaOrdenCompra.setIdProducto(producto.getId());
        lineaOrdenCompra.setIdOrdenCompra(ordenCompra.getId());
        lineaOrdenCompra.setIdCarrito(carrito.getId());
        
        lineaOrdenCompraBO.insertar(lineaOrdenCompra);
        System.out.println("Se inserto la orden de compra de id " + lineaOrdenCompra.getId() + " creado exitosamente");
        lineaOrdenCompra.setPrecioUnitario(300);
        lineaOrdenCompraBO.actualizar(lineaOrdenCompra);
        System.out.println("La orden de compra se ha actualizado exitosamente");
        LineaOrdenCompra lineaOrdenCompra2 = lineaOrdenCompraBO.obtener(lineaOrdenCompra.getId());
        System.out.println("La orden de compra se llamo al backend de manera correcta\n");
        
        
	//Comprobante de Pago 
        System.out.println("Ejecucion de procedures de Comprobante de pago");
        System.out.println("=====================================\n");
	ComprobantePago comprobantePago = new ComprobantePago();
        ComprobantePagoBO comprobantePagoBO = new ComprobantePagoBOImpl();
        ComprobantePagoDAO comprobantePagoDAO = new ComprobantePagoDAOImpl();
        
        Date fechaCreacionComprobante = new Date();
        java.sql.Date fechaSQLComprobante = new java.sql.Date(fechaCreacionComprobante.getTime());
        
        comprobantePago.setId(1);
        comprobantePago.setActivo(true);
        comprobantePago.setFechaEmision(fechaSQLComprobante);
        comprobantePago.setRUC(20038433);
        comprobantePago.setTotalSinImpuestos(1000);
        comprobantePago.setTotalFinal(2000);
        comprobantePago.setImpuestos(1000);
        comprobantePago.setMetodoPago(MetodoPago.VIRTUAL);
        comprobantePago.setIdOrdenCompra(1);
        
        comprobantePagoDAO.crear(comprobantePago);
        System.out.println("Se inserto el comprobante de pago de id " + comprobantePago.getId() + " creado exitosamente");
        comprobantePago.setTotalFinal(300);
        comprobantePagoDAO.actualizar(comprobantePago);
        System.out.println("El comprobante de pago se ha actualizado exitosamente");
        ComprobantePago comprobantePago2 = comprobantePagoDAO.leer(comprobantePago.getId());
        System.out.println("El comprobante de pago se llamo al backend de manera correcta\n");
        
        
		
	
        
	//Linea comprobante de pago
        System.out.println("Ejecucion de procedures de Linea de Comprobante");
        System.out.println("=====================================\n");
	LineaComprobantePago lineaComprobante = new LineaComprobantePago();
        LineaComprobantePagoBO lineaComprobanteBO = new LineaComprobantePagoBOImpl();
        LineaComprobantePagoDAOImpl lineaComprobanteDAO = new LineaComprobantePagoDAOImpl();
        
        
        lineaComprobante.setId(1);
        lineaComprobante.setActivo(true);
        lineaComprobante.setMontoImpuesto(2000);
        lineaComprobante.setMontoPagado(2000);
        lineaComprobante.setIdComprobantePago(1);
        lineaComprobante.setCodigo(20216635);
        lineaComprobante.setCantidad(10);
        lineaComprobante.setSubTotal(1000);
        
        lineaComprobanteBO.insertar(lineaComprobante);
        System.out.println("Se inserto la Linea de Comprobante de id " + lineaComprobante.getId() + " creado exitosamente");
        lineaComprobante.setCantidad(20);
        lineaComprobanteBO.actualizar(lineaComprobante);
        System.out.println("La Linea de Comprobante se ha actualizado exitosamente");
        LineaComprobantePago lineaComprobante2 = lineaComprobanteBO.obtener(lineaComprobante.getId());
        System.out.println("La Linea de Comprobante se llamo al backend de manera correcta\n");
        
        
        
        
        //Limpiaza de datos
        // 1. LineaComprobantePago
        lineaComprobanteBO.eliminar(1);
        System.out.println("La Linea de Comprobante se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Linea de Comprobante FINALIZADO \n");

        // 2. ComprobantePago
        comprobantePagoDAO.eliminar(1);
        System.out.println("El comprobante de pago se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Comprobante de pago FINALIZADO \n");

        // 3. LineaOrdenCompra
        lineaOrdenCompraBO.eliminar(1);
        System.out.println("La linea de orden de compra se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Linea de Orden de compra FINALIZADO \n");

        // 4. LineaCarrito
        lineaCarritoBO.eliminar(1);
        System.out.println("Linea carrito se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Linea carrito FINALIZADO \n");

        // 5. Descuento
        descuentoBO.eliminar(1);
        System.out.println("El descuento se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Descuento FINALIZADO \n");

        // 6. OrdenCompra
        ordenCompraDAO.eliminar(1);
        System.out.println("La orden de compra se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Orden de compra FINALIZADO \n");

        // 7. CarritoDeCompras
        carritoDAO.eliminar(1);
        System.out.println("El carrito de compras se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Carrito de compras FINALIZADO \n");

        // 8. Producto
        productoBO.eliminar(1);
        System.out.println("El producto se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Producto FINALIZADO \n");

        // 9. CategoriaProducto
        categoriaBO.eliminar(1);
        System.out.println("CategoriaProducto se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de CategoriaProducto FINALIZADO \n");

        // 10. CuentaUsuario
        cuentaBO.eliminar(1);
        System.out.println("La cuenta se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Cuenta de Usuario FINALIZADO \n");

        // 11. Administrador
        adminBO.eliminar(1);
        System.out.println("El Administrador se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Administrador FINALIZADO \n");

        // 12. Cliente
        clienteBO.eliminar(1);
        System.out.println("El cliente se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Cliente FINALIZADO \n");

        // 13. DetalleEnvio
        detalleEnvioBO.eliminar(2);
        System.out.println("El detalle de envio seleccionado se elimino correctamente");
        System.out.println("-----> Ejecucion de procedures de Detalle de envio FINALIZADO \n");
        
        System.out.println("Testeo del proyecto de persistancia, negocio, dbManagner y Modelo FINALIZADO :)");
        
        
    }
}
