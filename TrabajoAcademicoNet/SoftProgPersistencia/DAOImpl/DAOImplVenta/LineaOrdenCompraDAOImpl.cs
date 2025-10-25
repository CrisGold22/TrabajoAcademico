using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
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
    public class LineaOrdenCompraDAOImpl :
        TransaccionalBaseDAO<LineaOrdenCompra>, ILineaOrdenCompraDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, LineaOrdenCompra modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "actualizarLineaOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaOrdenCompra", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_cantidad", DbType.Int32, modelo.Cantidad);
            this.AgregarParametroEntrada(cmd, "@p_precioUnitario", DbType.Double, modelo.PrecioUnitario);
            this.AgregarParametroEntrada(cmd, "@p_subtotal", DbType.Double, modelo.SubTotal);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto", DbType.Int32, modelo.IdProducto);
            this.AgregarParametroEntrada(cmd, "@p_OrdenCompra_IdPedido", DbType.Int32, modelo.IdOrdenCompra);
            this.AgregarParametroEntrada(cmd, "@p_CarritoDeCompras_Id", DbType.Int32, modelo.IdCarrito);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);

            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, LineaOrdenCompra modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarLineaOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaOrdenCompra", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_cantidad", DbType.Int32, modelo.Cantidad);
            this.AgregarParametroEntrada(cmd, "@p_precioUnitario", DbType.Double, modelo.PrecioUnitario);
            this.AgregarParametroEntrada(cmd, "@p_subtotal", DbType.Double, modelo.SubTotal);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto", DbType.Int32, modelo.IdProducto);
            this.AgregarParametroEntrada(cmd, "@p_OrdenCompra_IdPedido", DbType.Int32, modelo.IdOrdenCompra);
            this.AgregarParametroEntrada(cmd, "@p_CarritoDeCompras_Id", DbType.Int32, modelo.IdCarrito);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarLineaOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaOrdenCompra", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarLineaOrdenCompraPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaOrdenCompra", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarLineasOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override LineaOrdenCompra MapearModelo(DbDataReader reader)
        {
            return new LineaOrdenCompra
            {
                Id = Convert.ToInt32(reader["idLineaOrdenCompra"]),
                Cantidad = Convert.ToInt32(reader["cantidad"]),
                PrecioUnitario = Convert.ToDouble(reader["precioUnitario"]),
                SubTotal = Convert.ToDouble(reader["subtotal"]),
                IdProducto = Convert.ToInt32(reader["Producto_ID_Producto"]),
                IdOrdenCompra = Convert.ToInt32(reader["OrdenCompra_IdPedido"]),
                IdCarrito = Convert.ToInt32(reader["CarritoDeCompras_Id"]),
                Activo = Convert.ToBoolean(reader["activo"])

            };
        }
        public List<LineaOrdenCompra> listarPorIdOrdenCompra(int id)
        {
            throw new NotImplementedException();
        }
    }
}
