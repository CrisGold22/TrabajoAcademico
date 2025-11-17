package pe.edu.pucp.inf30.softprogpruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.pago.ComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.ComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.LineaComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.AdministradorSistemaDAOImpl;
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
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Cargo;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.CategoriaCliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Jerarquia;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoCategoria;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoDescuento;
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
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.EmpresaBO;
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
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.EmpresaBOImpl;
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

        pruebaConexionConBaseDeDatos();
//        pruebasConDatos();
        
//        pruebasConProductos();
//          pruebaDesactivarOrdenCompra();
//        pruebaConEmpresa();
//        pruebaListar();
//        pruebaDemasFunciones();
    
//        pruebaFiltroPorPrecio();
    }
    
    public static void pruebaFiltroPorPrecio(){
        // PRUEBAS PARA EL FILTRADO DE PRODUCTOS SEGUN PRECIO
        // HECHO EL 17/11/2025
        // SE MODIFICARON EL DAO,DAO IMPL,BO , BOIMPL Y EL WS
        
        List<Producto> prueba1 = new ArrayList<>();
        ProductoBO productoBO = new ProductoBOImpl();
        
        System.out.println("Empieza la prueba de filtrar productos por precio Regular");
        
        prueba1 = productoBO.filtrarProductoPorPrecioRegular(1,18,30);
        
        for(var aux:prueba1){
            System.out.println("Producto: "+aux.getId()+"  "+aux.getDescripcion());
            System.out.println(""+aux.getPrecioRegular());
            System.out.println("-----------------------------------");
        }
        
       System.out.println("Prueba exitosa");
       System.out.println("Empieza la prueba de filtrar productos por precio Al Mayor");
       
       prueba1 = productoBO.filtrarProductoPorPrecioAlMayor(1, 18, 24);
       
        for(var aux:prueba1){
            System.out.println("Producto: "+aux.getId()+"  "+aux.getDescripcion());
            System.out.println(""+aux.getPrecioRegular());
            System.out.println("-----------------------------------");
        }       
    }

    public static void pruebaDemasFunciones(){
        CarritoCompras carrito = new CarritoComprasBOImpl().obtenerCarritoComprasPorIdCliente(1);
        
        System.out.println(carrito.getId() + "    " + carrito.getCliente().getNombre());
        
        
    }
    
    
    public static void pruebaListar(){
        List<AdministradorSistema> listaAdministradores = new AdministradorSistemaDAOImpl().leerTodos();
        
        for(AdministradorSistema a : listaAdministradores){
            System.out.println(a.getId() + "   " + a.getNombre() + "   " + a.getCargoString());
        }
        
    }
    
    public static void pruebaDesactivarOrdenCompra() {
        OrdenCompra ordenCompra;
        OrdenCompraBO ordenCompraBO = new OrdenCompraBOImpl();

        ordenCompra = ordenCompraBO.obtener(2);
        ordenCompraBO.desactivarOrdenCompra(2);

    }

    public static void pruebaConEmpresa() {

        CuentaUsuarioBO cuentaBO = new CuentaUsuarioBOImpl();
        CuentaUsuario cuenta = new CuentaUsuario("cristhianhoracio22@gmail.com", "Killzone2003", 1, true);
        cuentaBO.insertar(cuenta);
    
        //Cliente
        System.out.println("Ejecucion de procedures de Cliente");
        System.out.println("=====================================\n");
        Cliente cliente = new Cliente();
        ClienteBO clienteBO = new ClienteBOImpl();
        
        LocalDate nacimientoSQL = LocalDate.now();

        cliente.setId(1);
        cliente.setDni("72233478");
        cliente.setNombre("Cristhian Horacio");
        cliente.setApellidoMaterno("Olivares");
        cliente.setApellidoPaterno("Gupioc");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setFechaNacimiento(nacimientoSQL);
        cliente.setTelefono(981429641);
        cliente.setLineaCredito(10000);
        cliente.setCategoria(CategoriaCliente.CATERING);
        cliente.setNumeroPedidosHistorico(12);
        cliente.setNumeroPedidosMensualPro(10);
        cliente.setActivo(true);
        cliente.setCuenta(cuenta);

        clienteBO.insertar(cliente);
        System.out.println("Se inserto el cliente de id " + cliente.getId() + " creado exitosamente");

        Empresa empresa = new Empresa();
        EmpresaBO empresaBO = new EmpresaBOImpl();

        empresa.setId(1);

        empresa.setRUC("13212324");
        empresa.setRazonSocial("Empresa1");
        empresa.setDireccionFiscal("Av. Pacheco");
        empresa.setDepartamento("La Libertad");
        empresa.setProvincia("No lo se");
        empresa.setDistrito("LINCE");
        empresa.setTelefono("981238723");
        empresa.setEmail("pacheco@gmail.com");
        empresa.setCodigoPostal("61263");
        empresa.setCliente(cliente);

        empresaBO.insertar(empresa);
        System.out.println("Se inserto la empresa de id " + empresa.getId() + " creado exitosamente");
        empresa.setTelefono("981428641");
        empresaBO.actualizar(empresa);
        System.out.println("El empresa se actualizado exitosamente");
        Empresa empresaDuplicado = empresaBO.obtener(empresa.getId());
        System.out.println("El empresa se llamo al backend de manera correcta\n");
        empresaDuplicado.setId(2);
        empresaDuplicado.setRUC("132123223");
        empresaBO.insertar(empresaDuplicado);
        List<Empresa> listaEmpresas = empresaBO.listar();
        System.out.println("Se listo con exito todos las empresas");

        empresaBO.eliminar(2);
        System.out.println("Empresa de Id 2 a sido eliminada");

        Empresa empresa1 = new Empresa();

        empresa1.setId(2);
        empresa1.setRUC("20458732109");
        empresa1.setRazonSocial("Inversiones Los Andes S.A.C.");
        empresa1.setDireccionFiscal("Av. Los Próceres 245, Santa Anita");
        empresa1.setDepartamento("Lima");
        empresa1.setProvincia("Lima");
        empresa1.setDistrito("SAN_MIGUEL");
        empresa1.setTelefono("987654321");
        empresa1.setEmail("contacto@losandes.com");
        empresa1.setCodigoPostal("15088");
        empresa1.setCliente(cliente);
        empresa1.setActivoInt(1); // disponible
        empresaBO.insertar(empresa1);
        System.out.println("Se inserto la empresa de id " + empresa1.getId() + " creado exitosamente");
        
        Empresa empresa2 = new Empresa();

        empresa2.setId(3);
        empresa2.setRUC("20165897432");
        empresa2.setRazonSocial("Distribuidora El Sol E.I.R.L.");
        empresa2.setDireccionFiscal("Jr. Los Geranios 112, San Borja");
        empresa2.setDepartamento("Lima");
        empresa2.setProvincia("Lima");
        empresa2.setDistrito("SAN_BORJA");
        empresa2.setTelefono("991234567");
        empresa2.setEmail("ventas@elsol.com.pe");
        empresa2.setCodigoPostal("15036");
        empresa2.setCliente(cliente);
        empresa2.setActivoInt(0); // no disponible
        empresaBO.insertar(empresa2);
        System.out.println("Se inserto la empresa de id " + empresa2.getId() + " creado exitosamente");

        Empresa empresa3 = new Empresa();

        empresa3.setId(4);
        empresa3.setRUC("20654712380");
        empresa3.setRazonSocial("Tecnología Global S.A.");
        empresa3.setDireccionFiscal("Av. Colonial 3050, Callao");
        empresa3.setDepartamento("Callao");
        empresa3.setProvincia("Callao");
        empresa3.setDistrito("CALLAO");
        empresa3.setTelefono("936258147");
        empresa3.setEmail("info@tecnologiaglobal.pe");
        empresa3.setCodigoPostal("07001");
        empresa3.setCliente(cliente);
        empresa3.setActivoInt(1); // disponible
        empresaBO.insertar(empresa3);
        System.out.println("Se inserto la empresa de id " + empresa3.getId() + " creado exitosamente");

        Empresa empresa4 = new Empresa();

        empresa4.setId(5);
        empresa4.setRUC("20549876012");
        empresa4.setRazonSocial("Servicios Integrales del Hogar S.R.L.");
        empresa4.setDireccionFiscal("Av. Canadá 850, La Victoria");
        empresa4.setDepartamento("Lima");
        empresa4.setProvincia("Lima");
        empresa4.setDistrito("LA_VICTORIA");
        empresa4.setTelefono("987120450");
        empresa4.setEmail("servicios@hogarperu.com");
        empresa4.setCodigoPostal("15018");
        empresa4.setCliente(cliente);
        empresa4.setActivoInt(1); // disponible
        empresaBO.insertar(empresa4);
        System.out.println("Se inserto la empresa de id " + empresa4.getId() + " creado exitosamente");

        Empresa empresa5 = new Empresa();

        empresa5.setId(6);
        empresa5.setRUC("20876543219");
        empresa5.setRazonSocial("Alimentos del Norte S.A.C.");
        empresa5.setDireccionFiscal("Calle Los Laureles 120, Pueblo Libre");
        empresa5.setDepartamento("La Libertad");
        empresa5.setProvincia("Trujillo");
        empresa5.setDistrito("PUEBLO_LIBRE");
        empresa5.setTelefono("972540321");
        empresa5.setEmail("contacto@alimentosnorte.com");
        empresa5.setCodigoPostal("13007");
        empresa5.setCliente(cliente);
        empresa5.setActivoInt(0); // no disponible
        empresaBO.insertar(empresa5);
        System.out.println("Se inserto la empresa de id " + empresa5.getId() + " creado exitosamente");

        List<Empresa> listaEmpresasActivas = empresaBO.listarEmpresasActivos();
        List<Empresa> listaEmpresasNoActivas = empresaBO.listarEmpresasNoActivos();
        List<Empresa> listaEmpresasPorCliente = empresaBO.listarEmpresasPorCliente(1);
        List<Empresa> listaEmpresasPorClienteActivas = empresaBO.listarEmpresasPorClienteActivas(1);
        List<Empresa> listaEmpresasPorClienteNoActivas = empresaBO.listarEmpresasPorClienteNoActivas(1);

        System.out.println("============================================");
        System.out.println("REPORTE DE EMPRESAS ACTIVAS");
        System.out.println("============================================");
        for (Empresa e : listaEmpresasActivas) {
            System.out.println("ID: " + e.getId());
            System.out.println("Razón Social: " + e.getRazonSocial());
            System.out.println("RUC: " + e.getRUC());
            System.out.println("Dirección Fiscal: " + e.getDireccionFiscal());
            System.out.println("Distrito: " + e.getDistrito());
            System.out.println("--------------------------------------------");
        }

        System.out.println("\n============================================");
        System.out.println("REPORTE DE EMPRESAS NO ACTIVAS");
        System.out.println("============================================");
        for (Empresa e : listaEmpresasNoActivas) {
            System.out.println("ID: " + e.getId());
            System.out.println("Razón Social: " + e.getRazonSocial());
            System.out.println("RUC: " + e.getRUC());
            System.out.println("Dirección Fiscal: " + e.getDireccionFiscal());
            System.out.println("Distrito: " + e.getDistrito());
            System.out.println("--------------------------------------------");
        }

        System.out.println("\n============================================");
        System.out.println("REPORTE DE EMPRESAS POR CLIENTE (ID: 1)");
        System.out.println("============================================");
        for (Empresa e : listaEmpresasPorCliente) {
            System.out.println("ID: " + e.getId());
            System.out.println("Razón Social: " + e.getRazonSocial());
            System.out.println("RUC: " + e.getRUC());
            System.out.println("Departamento: " + e.getDepartamento());
            System.out.println("Activo: " + e.getActivo());
            System.out.println("--------------------------------------------");
        }

        System.out.println("\n============================================");
        System.out.println("REPORTE DE EMPRESAS POR CLIENTE ACTIVAS (ID: 1)");
        System.out.println("============================================");
        for (Empresa e : listaEmpresasPorClienteActivas) {
            System.out.println("ID: " + e.getId());
            System.out.println("Razón Social: " + e.getRazonSocial());
            System.out.println("RUC: " + e.getRUC());
            System.out.println("Dirección Fiscal: " + e.getDireccionFiscal());
            System.out.println("Activo: " + e.getActivo());
            System.out.println("--------------------------------------------");
        }

        System.out.println("\n============================================");
        System.out.println("REPORTE DE EMPRESAS POR CLIENTE NO ACTIVAS (ID: 1)");
        System.out.println("============================================");
        for (Empresa e : listaEmpresasPorClienteNoActivas) {
            System.out.println("ID: " + e.getId());
            System.out.println("Razón Social: " + e.getRazonSocial());
            System.out.println("RUC: " + e.getRUC());
            System.out.println("Departamento: " + e.getDepartamento());
            System.out.println("Activo: " + e.getActivo());
            System.out.println("--------------------------------------------");
        }
        
        //Eliminando las empresas
//        empresaBO.eliminar(1);
//        empresaBO.eliminar(2);
//        empresaBO.eliminar(3);
//        empresaBO.eliminar(4);
//        empresaBO.eliminar(5);
//        empresaBO.eliminar(6);
//        
        System.out.println("Empresas eliminadas :)");
        
//        clienteBO.eliminar(1);
        System.out.println("Cliente eliminado :) ");
        
        System.out.println("Proceso finalizado");
    }

    public static void pruebasConProductos() throws SQLException, ClassNotFoundException {
        System.out.println();

        System.out.println("=====================================");
        System.out.println("Empezando con el proceso de prueba");
        System.out.println("=====================================\n \n");

        System.out.println("Ejecucion de procedures de CategoriaProductos");
        System.out.println("=====================================\n");
        CategoriaProducto categoria = new CategoriaProducto();
        CategoriaProductoBO categoriaBO = new CategoriaProductoBOImpl();

//        categoria.setId(1);
//        categoria.setActivo(true);
//        categoria.setDescripcion("Una categoria");
//        categoria.setCategoriaPadre(null);
//        categoria.setNombre("Hogar");
//
//        categoriaBO.insertar(categoria);
//        System.out.println("Se inserto CategoriaProductos de id " + categoria.getId() + " creado exitosamente");
//        categoria.setDescripcion("Nueva descripcion");
//        categoriaBO.actualizar(categoria);
//        System.out.println("CategoriaProductos se ha actualizado exitosamente");
//        CategoriaProducto categoria2 = categoriaBO.obtener(categoria.getId());
//        System.out.println("CategoriaProductos se llamo al backend de manera correcta\n");

        System.out.println("Ejecucion de procedures de Productos");
        System.out.println("=====================================\n");
        Producto producto = new Producto();
        ProductoBO productoBO = new ProductoBOImpl();
        
        producto = productoBO.obtener(101);
        System.out.println("producto " + producto.getDescripcion());
        
//        producto.setId(1);
//        producto.setActivo(true);
//        producto.setNombre("Celular IPhone 20 X Giga Pro Max XXXL");
//        producto.setSKU("SEL-123");
//        producto.setDescripcion("Celular de ultima generacion");
//        producto.setPrecioAlMayor(3500);
//        producto.setPrecioRegular(4000);
//        producto.setMedidaAlMayor(UnidadMedida.BOTELLA);
//        producto.setStockDisponible(10);
//        producto.setStockMaximo(50);
//        producto.setCategoria(categoria);
//        producto.setMarca("APPLE");
//
//        productoBO.insertar(producto);
//        System.out.println("Se inserto el producto de id " + producto.getId() + " creado exitosamente");
//        producto.setDescripcion("Nueva descripcion");
//        productoBO.actualizar(producto);
//        System.out.println("El producto se ha actualizado exitosamente");
//        Producto producto2 = productoBO.obtener(producto.getId());
//        System.out.println("El producto se llamo al backend de manera correcta\n");
//
//        Producto producto3 = productoBO.obtenerPorSku(producto.getSKU());
//        System.out.println("El SKU del producto ha sido encontrado \n");
//
//        List<Producto> listaProducto = new ArrayList();
//        listaProducto = productoBO.filtrarProductoPorMarca(1, "APPLE");
//
//        List<Producto> listaProducto2 = new ArrayList();
//        listaProducto2 = productoBO.filtrarProductoPorPrecio(1, 3000, 6000);
//
//        Descuento descuento = new Descuento();
//        DescuentoBO descuentoBO = new DescuentoBOImpl();
//
//        descuento.setId(1);
//        descuento.setActivo(true);
//        descuento.setPrecioPorVolumen(2000);
//        descuento.setCantidadMax(200);
//        descuento.setCantidadMin(20);
//        descuento.setProducto(producto);
//
//        descuentoBO.insertar(descuento);
//
//        List<Producto> listaProducto3 = new ArrayList();
//        listaProducto3 = productoBO.filtrarProductoPorDescuento(1, "CON DESCUENTO");

    }

    public static void pruebaConexionConBaseDeDatos() throws SQLException, ClassNotFoundException {
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection connection = dbManager.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexion establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexion.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void pruebasConDatos() {
        System.out.println();

        System.out.println("=====================================");
        System.out.println("Empezando con el proceso de prueba");
        System.out.println("=====================================\n \n");

        //Cuenta Usuario
        System.out.println("Ejecucion de procedures de Cuenta de Usuario");
        System.out.println("=====================================\n");
        CuentaUsuario cuenta = new CuentaUsuario();
        CuentaUsuarioBO cuentaBO = new CuentaUsuarioBOImpl();

        cuenta.setId(1);
        cuenta.setActivo(true);
        cuenta.setUsername("CrisGold");
        cuenta.setPassword("Killzone2003");

        cuentaBO.insertar(cuenta);
        System.out.println("Se inserto la cuenta de id 1 creado exitosamente");
        cuenta.setPassword("CrisGold2003");
        cuentaBO.actualizar(cuenta);
        System.out.println("La cuenta se ha actualizado exitosamente");
        CuentaUsuario cuenta2 = cuentaBO.obtener(1);
        cuenta.setId(2);
        System.out.println("La cuenta se llamo al backend de manera correcta");
        cuentaBO.insertar(cuenta2);
        List<CuentaUsuario> listaCuentas = cuentaBO.listar();

        for (CuentaUsuario c : listaCuentas) {
            System.out.println(c.getId() + "   " + c.getUsername() + "   " + c.getPassword());
        }

        System.out.println("Se listo con exito todos las cuentas\n");

        //Administrador
        System.out.println("Ejecucion de procedures de Administrador");
        System.out.println("=====================================\n");
        AdministradorSistema admin = new AdministradorSistema();
        AdministradorSistemaBO adminBO = new AdministradorSistemaBOImpl();

        LocalDate nacimientoSQL = LocalDate.now();

        admin.setId(1);
        admin.setCargo(Cargo.ADMINISTRADOR);
        admin.setJerarquia(Jerarquia.PARCIAL);
        admin.setDni("72233478");
        admin.setNombre("Cristhian Horacio");
        admin.setApellidoMaterno("Olivares");
        admin.setApellidoPaterno("Gupioc");
        admin.setGenero(Genero.MASCULINO);
        admin.setFechaNacimiento(nacimientoSQL);
        admin.setTelefono(981429641);
        admin.setSueldo(5000);
        admin.setActivo(true);
        admin.setCuenta(cuenta);

        adminBO.insertar(admin);
        System.out.println("Se inserto el Administrador de id 1 creado exitosamente");
        admin.setTelefono(941993577);
        adminBO.actualizar(admin);
        System.out.println("El Administrador se ha actualizado exitosamente");
        AdministradorSistema admin2 = adminBO.obtener(1);
        System.out.println("El Administrador se llamo al backend de manera correcta");
        admin.setId(2);
        adminBO.insertar(admin2);
        List<AdministradorSistema> listaAdmin = adminBO.listar();
        System.out.println("Se listo con exito todos los admins\n");

        for (AdministradorSistema c : listaAdmin) {
            System.out.println(c.getId() + "   " + c.getNombre() + "   " + c.getCargoString());
        }

        //Cliente
        System.out.println("Ejecucion de procedures de Cliente");
        System.out.println("=====================================\n");
        Cliente cliente = new Cliente();
        ClienteBO clienteBO = new ClienteBOImpl();

        cliente.setId(1);
        cliente.setDni("72233478");
        cliente.setNombre("Cristhian Horacio");
        cliente.setApellidoMaterno("Olivares");
        cliente.setApellidoPaterno("Gupioc");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setFechaNacimiento(nacimientoSQL);
        cliente.setTelefono(981429641);
        cliente.setLineaCredito(10000);
        cliente.setCategoria(CategoriaCliente.CATERING);
        cliente.setNumeroPedidosHistorico(12);
        cliente.setNumeroPedidosMensualPro(10);
        cliente.setCuenta(cuenta);
        cliente.setActivo(true);

        clienteBO.insertar(cliente);
        System.out.println("Se inserto el cliente de id 1 creado exitosamente");
        cliente.setTelefono(23234234);
        clienteBO.actualizar(cliente);
        System.out.println("El cliente se actualizado exitosamente");
        Cliente cliente2 = clienteBO.obtener(1);
        System.out.println("El cliente se llamo al backend de manera correcta\n");
        cliente2.setId(2);
        cliente2.setDni("72233426");
        clienteBO.insertar(cliente2);
        List<Cliente> listaCliente = clienteBO.listar();
        System.out.println("Se listo con exito todos los clientes");
        
        for (Cliente c : listaCliente) {
            System.out.println(c.getId() + "   " + c.getNombre() + "   " + c.getGeneroString());
        }

//        Empresa
        Empresa empresa = new Empresa();
        EmpresaBO empresaBO = new EmpresaBOImpl();

        empresa.setId(1);
        empresa.setRUC("13233212324");
        empresa.setRazonSocial("Empresa1");
        empresa.setDireccionFiscal("Av. Pacheco");
        empresa.setDepartamento("La Libertad");
        empresa.setProvincia("No lo se");
        empresa.setDistrito("LINCE");
        empresa.setTelefono("981238723");
        empresa.setEmail("pacheco@gmail.com");
        empresa.setCodigoPostal("61263");
        empresa.setCliente(cliente);

        empresaBO.insertar(empresa);
        System.out.println("Se inserto la empresa de id 1 creado exitosamente");
        empresa.setTelefono("981428641");
        empresaBO.actualizar(empresa);
        System.out.println("El empresa se actualizado exitosamente");
        Empresa empresaDuplicado = empresaBO.obtener(empresa.getId());
        System.out.println("El empresa se llamo al backend de manera correcta\n");
        empresaDuplicado.setId(2);
        empresaDuplicado.setRUC("132123223");
        empresaBO.insertar(empresaDuplicado);
        List<Empresa> listaEmpresas = empresaBO.listar();
        System.out.println("Se listo con exito todos las empresas");

        for (Empresa c : listaEmpresas) {
            System.out.println(c.getId() + "   " + c.getRazonSocial() + "   " + c.getEmail());
        }

        //Carrito de Compras
        System.out.println("Ejecucion de procedures de Carrito de compras");
        System.out.println("=====================================\n");
        CarritoCompras carrito = new CarritoCompras();
        CarritoComprasBO carritoBO = new CarritoComprasBOImpl();

        carrito.setId(1);
        carrito.setSubtotal(2000);
        carrito.setEstado(EstadoCarrito.EXPIRADO);
        carrito.setDescuento(200);
        carrito.setMontoFinal(1800);
        carrito.setCliente(cliente);
        carrito.setActivo(true);

        CarritoComprasDAOImpl carritoDAO = new CarritoComprasDAOImpl();
        carritoDAO.crear(carrito);
        System.out.println("Se inserto la cuenta de id " + carrito.getId() + " creado exitosamente");
        carrito.setEstado(EstadoCarrito.COMPLETADO);
        carritoDAO.actualizar(carrito);
        System.out.println("El carrito de compras se ha actualizado exitosamente");
        CarritoCompras carrito2 = carritoDAO.leer(carrito.getId());
        System.out.println("El carrito de compras se llamo al backend de manera correcta\n");
        carrito2.setId(2);
        carrito2.setCliente(cliente2);
        carritoDAO.crear(carrito2);
        List<CarritoCompras> listaCarrito = carritoDAO.leerTodos();
        System.out.println("Se listo con exito todos los carritos");

        for (CarritoCompras c : listaCarrito) {
            System.out.println(c.getId() + "   " + c.getEstadoString() + "   " + c.getMontoFinal());
        }

        //Orden de compra
        System.out.println("Ejecucion de procedures de OrdenCompra");
        System.out.println("=====================================\n");
        OrdenCompra ordenCompra = new OrdenCompra();
        OrdenCompraDAO ordenCompraDAO = new OrdenCompraDAOImpl();

        LocalDate fechaSQLOrden = LocalDate.now();

        ordenCompra.setId(1);
        ordenCompra.setFechaCreacion(fechaSQLOrden);
        ordenCompra.setTotalFinal(200);
        ordenCompra.setTotalParcial(100);
        ordenCompra.setDescuentoTotal(200);
        ordenCompra.setEstado(EstadoOrdenCompra.ENVIADO);
        ordenCompra.setActivo(true);
        ordenCompra.setCarritoCompras(carrito);
        ordenCompra.setCliente(cliente);
        ordenCompra.setEmpresa(empresa);

        ordenCompraDAO.crear(ordenCompra);
        System.out.println("Se inserto la orden de compra de id " + ordenCompra.getId() + " creado exitosamente");
        ordenCompra.setTotalFinal(300);
        ordenCompraDAO.actualizar(ordenCompra);
        System.out.println("La orden de compra se ha actualizado exitosamente");
        OrdenCompra ordenCompra2 = ordenCompraDAO.leer(ordenCompra.getId());
        System.out.println("La orden de compra se llamo al backend de manera correcta\n");
        ordenCompra2.setEmpresa(empresaDuplicado);
        ordenCompra2.setCliente(cliente2);
        ordenCompraDAO.crear(ordenCompra2);
        List<OrdenCompra> listaOrdenCompra = ordenCompraDAO.leerTodos();
        System.out.println("Se listo con exito todos las ordenCompra");

        for (OrdenCompra c : listaOrdenCompra) {
            System.out.println(c.getId() + "   " + c.getEstadoString() + "   " + c.getTotalFinal());
        }

        //Detalle De Envio
        System.out.println("Ejecucion de procedures de Detalle de envio");
        System.out.println("=====================================\n");
        DetalleEnvio detalle = new DetalleEnvio();
        DetalleEnvioBO detalleEnvioBO = new DetalleEnvioBOImpl();

        LocalDate fechaSQL = LocalDate.now();

        detalle.setId(1);
        detalle.setDescripcion("Pago con exito");
        detalle.setDireccion("Av. Donde vive Galileo");
        detalle.setDistrito(Distrito.LINCE);
        detalle.setFechaEntrega(fechaSQL);
        detalle.setHorarioEntrega(fechaSQL);
        detalle.setOrdenCompra(ordenCompra);
        detalle.setActivo(true);

        detalleEnvioBO.insertar(detalle);
        System.out.println("Se inserto el detalle de envio de id " + detalle.getId() + " creado exitosamente");
        detalle.setDescripcion("hola :)");
        detalleEnvioBO.actualizar(detalle);
        System.out.println("Se actualizo el detalle de envio de id " + detalle.getId() + " creado exitosamente");
        DetalleEnvio detalle2 = detalleEnvioBO.obtener(1);
        System.out.println("Detalle de envio se llamo al backend de manera correcta\n");
        detalleEnvioBO.insertar(detalle2);
        List<DetalleEnvio> listaDetalle = detalleEnvioBO.listar();
        System.out.println("Se listo con exito todos los detalleEnvio");

        for (DetalleEnvio c : listaDetalle) {
            System.out.println(c.getId() + "   " + c.getDescripcion() + "   " + c.getDireccion());
        }

        //Categoria Producto
        System.out.println("Ejecucion de procedures de CategoriaProductos");
        System.out.println("=====================================\n");
        CategoriaProducto categoria = new CategoriaProducto();
        CategoriaProductoBO categoriaBO = new CategoriaProductoBOImpl();

        categoria.setId(1);
        categoria.setActivo(true);
        categoria.setDescripcion("Una categoria");
        categoria.setCategoriaPadre(null);
        categoria.setNombre("Hogar");

        categoriaBO.insertar(categoria);
        System.out.println("Se inserto CategoriaProductos de id " + categoria.getId() + " creado exitosamente");
        categoria.setDescripcion("Nueva descripcion");
        categoriaBO.actualizar(categoria);
        System.out.println("CategoriaProductos se ha actualizado exitosamente");
        CategoriaProducto categoria2 = categoriaBO.obtener(categoria.getId());
        System.out.println("CategoriaProductos se llamo al backend de manera correcta\n");
        categoria2.setId(2);
        categoriaBO.insertar(categoria2);
        List<CategoriaProducto> listaCategoria = categoriaBO.listar();
        System.out.println("Se listo con exito todos las categorias");

        for (CategoriaProducto c : listaCategoria) {
            System.out.println(c.getId() + "   " + c.getNombre() + "   " + c.getDescripcion());
        }

        //Producto
        System.out.println("Ejecucion de procedures de Productos");
        System.out.println("=====================================\n");
        Producto producto = new Producto();
        ProductoBO productoBO = new ProductoBOImpl();

        producto.setId(1);
        producto.setNombre("Celular IPhone 20 X Giga Pro Max XXXL");
        producto.setSKU("SEL-123");
        producto.setDescripcion("Celular de ultima generacion");
        producto.setPrecioAlMayor(3500);
        producto.setPrecioRegular(4000);
        producto.setMedidaAlMayor(UnidadMedida.BOTELLA);
        producto.setStockDisponible(10);
        producto.setStockMaximo(50);
        producto.setActivo(true);
        producto.setMarca("Apple");
        producto.setCategoria(categoria);

        productoBO.insertar(producto);
        System.out.println("Se inserto el producto de id " + producto.getId() + " creado exitosamente");
        producto.setDescripcion("Nueva descripcion");
        productoBO.actualizar(producto);
        System.out.println("El producto se ha actualizado exitosamente");
        Producto producto2 = productoBO.obtener(producto.getId());
        System.out.println("El producto se llamo al backend de manera correcta\n");
        producto2.setCategoria(categoria2);
        productoBO.insertar(producto2);
        List<Producto> listaProductos = productoBO.listar();
        System.out.println("Se listo con exito todos los productos");

        for (Producto c : listaProductos) {
            System.out.println(c.getId() + "   " + c.getNombre() + "   " + c.getDescripcion());
        }

        //Descuento
        System.out.println("Ejecucion de procedures de Descuento");
        System.out.println("=====================================\n");
        Descuento descuento = new Descuento();
        DescuentoBO descuentoBO = new DescuentoBOImpl();

        descuento.setId(1);
        descuento.setPrecioPorVolumen(2000);
        descuento.setCantidadMax(200);
        descuento.setCantidadMin(20);
        descuento.setActivo(true);
        descuento.setProducto(producto);

        descuentoBO.insertar(descuento);
        System.out.println("Se inserto el descuento de id " + descuento.getId() + " creado exitosamente");
        descuento.setCantidadMin(10);
        descuentoBO.actualizar(descuento);
        System.out.println("El descuento se ha actualizado exitosamente");
        Descuento descuento2 = descuentoBO.obtener(descuento.getId());
        System.out.println("El descuento se llamo al backend de manera correcta\n");
        descuento2.setId(2);
        descuento2.setProducto(producto2);
        descuentoBO.insertar(descuento2);
        List<Descuento> listaDescuento = descuentoBO.listar();
        System.out.println("Se listo con exito todos los descuentos");

        for (Descuento c : listaDescuento) {
            System.out.println(c.getId() + "   " + c.getPrecioPorVolumen() + "   " + c.getCantidadMax());
        }

        //Linea Carrito
        System.out.println("Ejecucion de procedures de Linea carrito");
        System.out.println("=====================================\n");
        LineaCarrito lineaCarrito = new LineaCarrito();
        LineaCarritoBO lineaCarritoBO = new LineaCarritoBOImpl();

        lineaCarrito.setId(1);
        lineaCarrito.setCantidad(2000);
        lineaCarrito.setPrecio(200);
        lineaCarrito.setSubTotal(2000);
        lineaCarrito.setCarritoCompras(carrito);
        lineaCarrito.setActivo(true);
        lineaCarrito.setProducto(producto);

        lineaCarritoBO.insertar(lineaCarrito);
        System.out.println("Se inserto Linea carrito de id " + lineaCarrito.getId() + " creado exitosamente");
        lineaCarrito.setCantidad(150);
        lineaCarritoBO.actualizar(lineaCarrito);
        System.out.println("Linea carrito se ha actualizado exitosamente");
        LineaCarrito lineaCarrito2 = lineaCarritoBO.obtener(lineaCarrito.getId());
        System.out.println("Linea carrito se llamo al backend de manera correcta\n");
        lineaCarrito2.setId(2);
        lineaCarrito2.setCarritoCompras(carrito2);
        lineaCarrito2.setProducto(producto2);
        lineaCarritoBO.insertar(lineaCarrito2);
        List<LineaCarrito> listaLineaCarrito = lineaCarritoBO.listar();
        System.out.println("Se listo con exito todos las lineaCarrito");

        for (LineaCarrito c : listaLineaCarrito) {
            System.out.println(c.getId() + "   " + c.getCantidad() + "   " + c.getPrecio());
        }

        //Linea Orden de compra
        System.out.println("Ejecucion de procedures de Linea orden de compra");
        System.out.println("=====================================\n");
        LineaOrdenCompra lineaOrdenCompra = new LineaOrdenCompra();
        LineaOrdenCompraBO lineaOrdenCompraBO = new LineaOrdenCompraBOImpl();

        lineaOrdenCompra.setId(1);
        lineaOrdenCompra.setCantidad(10);
        lineaOrdenCompra.setPrecio(200);
        lineaOrdenCompra.setSubTotal(200);
        lineaOrdenCompra.setProducto(producto);
        lineaOrdenCompra.setOrdenCompra(ordenCompra);
        lineaOrdenCompra.setActivo(true);

        lineaOrdenCompraBO.insertar(lineaOrdenCompra);
        System.out.println("Se inserto la orden de compra de id " + lineaOrdenCompra.getId() + " creado exitosamente");
        lineaOrdenCompra.setPrecio(300);
        lineaOrdenCompraBO.actualizar(lineaOrdenCompra);
        System.out.println("La orden de compra se ha actualizado exitosamente");
        LineaOrdenCompra lineaOrdenCompra2 = lineaOrdenCompraBO.obtener(lineaOrdenCompra.getId());
        System.out.println("La orden de compra se llamo al backend de manera correcta\n");
        lineaOrdenCompra2.setId(2);
        lineaOrdenCompra2.setProducto(producto2);
        lineaOrdenCompra2.setOrdenCompra(ordenCompra2);
        lineaOrdenCompraBO.insertar(lineaOrdenCompra2);
        List<LineaOrdenCompra> listaLineaOrdenCompra = lineaOrdenCompraBO.listar();
        System.out.println("Se listo con exito todos las LineaOrdenCompra");

        for (LineaOrdenCompra c : listaLineaOrdenCompra) {
            System.out.println(c.getId() + "   " + c.getCantidad() + "   " + c.getPrecio());
        }


// 1. LineaOrdenCompra
        lineaOrdenCompraBO.eliminar(1);
        lineaOrdenCompraBO.eliminar(2);
        System.out.println("Linea de Orden de compra eliminada correctamente");
        System.out.println("-----> Procedimientos LineaOrdenCompra FINALIZADO \n");

// 2. LineaCarrito
        lineaCarritoBO.eliminar(1);
        lineaCarritoBO.eliminar(2);
        System.out.println("Linea de carrito eliminada correctamente");
        System.out.println("-----> Procedimientos LineaCarrito FINALIZADO \n");

// 3. Descuento
        descuentoBO.eliminar(1);
        descuentoBO.eliminar(2);
        System.out.println("Descuento eliminado correctamente");
        System.out.println("-----> Procedimientos Descuento FINALIZADO \n");

// 4. DetalleEnvio
        detalleEnvioBO.eliminar(1);
        detalleEnvioBO.eliminar(2);
        System.out.println("Detalle de envío eliminado correctamente");
        System.out.println("-----> Procedimientos DetalleEnvio FINALIZADO \n");

// 5. OrdenCompra
        ordenCompraDAO.eliminar(1);
        ordenCompraDAO.eliminar(2);
        System.out.println("Orden de compra eliminada correctamente");
        System.out.println("-----> Procedimientos OrdenCompra FINALIZADO \n");

// 6. CarritoDeCompras
        carritoDAO.eliminar(1);
        carritoDAO.eliminar(2);
        System.out.println("Carrito de compras eliminado correctamente");
        System.out.println("-----> Procedimientos CarritoDeCompras FINALIZADO \n");

// 7. Producto
        productoBO.eliminar(1);
        productoBO.eliminar(2);
        System.out.println("Producto eliminado correctamente");
        System.out.println("-----> Procedimientos Producto FINALIZADO \n");

// 8. CategoriaProducto
        categoriaBO.eliminar(1);
        categoriaBO.eliminar(2);
        System.out.println("Categoría de producto eliminada correctamente");
        System.out.println("-----> Procedimientos CategoriaProducto FINALIZADO \n");

// 9. Administrador
        adminBO.eliminar(1);
        adminBO.eliminar(2);
        System.out.println("Administrador eliminado correctamente");
        System.out.println("-----> Procedimientos Administrador FINALIZADO \n");

// 10. Empresa (FALTABA)
        empresaBO.eliminar(1);
        empresaBO.eliminar(2);
        System.out.println("Empresa eliminada correctamente");
        System.out.println("-----> Procedimientos Empresa FINALIZADO \n");

// 11. Cliente
        clienteBO.eliminar(1);
        clienteBO.eliminar(2);
        System.out.println("Cliente eliminado correctamente");
        System.out.println("-----> Procedimientos Cliente FINALIZADO \n");

// 12. CuentaUsuario
        cuentaBO.eliminar(1);
        cuentaBO.eliminar(2);
        System.out.println("Cuenta de usuario eliminada correctamente");
        System.out.println("-----> Procedimientos CuentaUsuario FINALIZADO \n");



        System.out.println("TESTEO COMPLETO DE PERSISTENCIA, NEGOCIO, DBMANAGER Y MODELO FINALIZADO :)");

    }
}
