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
    public class LineaCarritoDAOImpl :
        TransaccionalBaseDAO<LineaCarrito>, ILineaCarritoDAO
    {
       

        protected override DbCommand ComandoActualizar(DbConnection conn, LineaCarrito modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarLineaCarrito";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_cantidad", DbType.Int32, modelo.Cantidad);
            this.AgregarParametroEntrada(cmd, "@p_precioVolumen", DbType.Double, modelo.PrecioVolumen);
            this.AgregarParametroEntrada(cmd, "@p_subTotal", DbType.Double, modelo.SubTotal);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto1", DbType.Int32, modelo.IdProducto);
            this.AgregarParametroEntrada(cmd, "@p_CarritoDeCompras_Productos", DbType.Int32, modelo.IdCarritoCompras);
            this.AgregarParametroEntrada(cmd, "@p_activo", DbType.String, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto", DbType.Int32, modelo.IdProducto);
   
            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, LineaCarrito modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarLineaCarrito";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_cantidad", DbType.Int32, modelo.Cantidad);
            this.AgregarParametroEntrada(cmd, "@p_precioVolumen", DbType.Double, modelo.PrecioVolumen);
            this.AgregarParametroEntrada(cmd, "@p_subTotal", DbType.Double, modelo.SubTotal);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto1", DbType.Int32, modelo.IdProducto);
            this.AgregarParametroEntrada(cmd, "@p_CarritoDeCompras_Productos", DbType.Int32, modelo.IdCarritoCompras);
            this.AgregarParametroEntrada(cmd, "@p_activo", DbType.String, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto", DbType.Int32, modelo.IdProducto);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarLineaCarrito";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarLineaCarritoPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarLineasCarrito";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override LineaCarrito MapearModelo(DbDataReader reader)
        {
            return new LineaCarrito
            {
                Id = Convert.ToInt32(reader["idLineaCarrito"]),
                Cantidad = Convert.ToInt32(reader["cantidad"]),
                PrecioVolumen = Convert.ToDouble(reader["precioVolumen"]),
                SubTotal = Convert.ToDouble(reader["subTotal"]),
                IdProducto = Convert.ToInt32(reader["Producto_ID_Producto"]),
                IdCarritoCompras = Convert.ToInt32(reader["CarritoDeCompras_Productos"]),
                Activo = Convert.ToBoolean(reader["activo"])
            };
        } 
        
        public List<LineaCarrito> listarPorIdCarrito(int id)
        {
            throw new NotImplementedException();
        }
    }
}
