using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using PUCP.Inf30.SoftProg.Modelo.Venta.VentaUtils;
using PUCP.Inf30.SoftProg.Modelo.VentaModelo;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOVenta;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplVenta
{
    public class CarritoComprasDAOImpl :
        TransaccionalBaseDAO<CarritoCompras>, ICarritoComprasDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, CarritoCompras modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarCarritoDeCompras";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_Id_CarritoDeCompras", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_Total_Parcial", DbType.Double, modelo.TotalParcial);
            this.AgregarParametroEntrada(cmd, "@p_Estado", DbType.String, modelo.Estado);
            this.AgregarParametroEntrada(cmd, "@p_total_con_descuento", DbType.Double, modelo.TotalConDescuento);
            this.AgregarParametroEntrada(cmd, "@p_cliente_idCliente", DbType.Int32, modelo.IdCliente);
            
            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, CarritoCompras modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarCarritoDeCompras";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_Id_CarritoDeCompras", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_Total_Parcial", DbType.Double, modelo.TotalParcial);
            this.AgregarParametroEntrada(cmd, "@p_Estado", DbType.String, modelo.Estado);
            this.AgregarParametroEntrada(cmd, "@p_total_con_descuento", DbType.Double, modelo.TotalConDescuento);
            this.AgregarParametroEntrada(cmd, "@p_cliente_idCliente", DbType.Int32, modelo.IdCliente);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarCarritoDeCompras";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_Id_CarritoDeCompras", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarCarritoDeComprasPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_Id_CarritoDeCompras", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarCarritosDeCompras";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override CarritoCompras MapearModelo(DbDataReader reader)
        {
            return new CarritoCompras
            {
                Id = Convert.ToInt32(reader["Id_CarritoDeCompras"]),
                TotalParcial = Convert.ToDouble(reader["Total_Parcial"]),
                Estado = (EstadoCarrito)Enum.Parse(typeof(EstadoCarrito), Convert.ToString(reader["Estado"])),
                TotalConDescuento = Convert.ToDouble(reader["total_con_descuento"]),
                IdCliente = Convert.ToInt32(reader["cliente_idCliente"])
            };
        }
    }
}
