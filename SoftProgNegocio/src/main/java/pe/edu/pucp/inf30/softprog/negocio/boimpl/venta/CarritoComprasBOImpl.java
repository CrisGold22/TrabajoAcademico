/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.CarritoComprasDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.CarritoComprasDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoComprasDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.CarritoComprasBO;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoComprasBOImpl implements CarritoComprasBO{
    private final CarritoComprasDAO carritoCompras;
    
    public CarritoComprasBOImpl(){
        carritoCompras = new CarritoComprasDAOImpl();
    }
    
    @Override
    public List<CarritoComprasDTO> listar() {  
       return this.carritoCompras.leerTodos();
    }

    @Override
    public void insertar(CarritoComprasDTO modelo) {
        this.carritoCompras.crear(modelo);
    }

    @Override
    public void actualizar(CarritoComprasDTO modelo) {
        this.carritoCompras.actualizar(modelo);
    }

    @Override
    public CarritoComprasDTO obtener(int id) {
        return this.carritoCompras.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.carritoCompras.eliminar(id);
    }
    
}
