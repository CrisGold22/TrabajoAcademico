using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOProducto;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplProducto
{
    public class DescuentoDAOImpl :
        BaseDAOImpl<Descuento>, IDescuentoDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, Descuento modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "actualizarDescuento";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idReglaPrecioVolumen", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_precioPorVolumen", DbType.Double, modelo.PrecioPorVolumen);
            this.AgregarParametroEntrada(cmd, "@p_cantidadMax", DbType.Int32, modelo.CantidadMax);
            this.AgregarParametroEntrada(cmd, "@p_cantidadMin", DbType.Int32, modelo.CantidadMin);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto", DbType.Int32, modelo.IdProducto);

            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, Descuento modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarDescuento";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idReglaPrecioVolumen", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_precioPorVolumen", DbType.Double, modelo.PrecioPorVolumen);
            this.AgregarParametroEntrada(cmd, "@p_cantidadMax", DbType.Int32, modelo.CantidadMax);
            this.AgregarParametroEntrada(cmd, "@p_cantidadMin", DbType.Int32, modelo.CantidadMin);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_Producto_ID_Producto", DbType.Int32, modelo.IdProducto);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarDescuento";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idAdministrador", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarDescuentoPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idAdministrador", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarDescuentos";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override Descuento MapearModelo(DbDataReader reader)
        {
            return new Descuento
            {
                Id = Convert.ToInt32(reader["idReglaPrecioVolumen"]),
                PrecioPorVolumen = Convert.ToDouble(reader["precioPorVolumen"]),
                CantidadMax = Convert.ToInt32(reader["cantidadMax"]),
                CantidadMin = Convert.ToInt32(reader["cantidadMin"]),
                Activo = Convert.ToBoolean(reader["activo"]),
                IdProducto = Convert.ToInt32(reader["Producto_ID_Producto"])

            };
        }
    }
}
