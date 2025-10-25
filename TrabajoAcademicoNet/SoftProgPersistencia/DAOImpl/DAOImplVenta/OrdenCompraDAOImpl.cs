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
    public class OrdenCompraDAOImpl : TransaccionalBaseDAO<OrdenCompra>, IOrdenCompraDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, OrdenCompra modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "actualizarOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_IdPedido", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_FechaCreacion", DbType.String, modelo.FechaCreacion);
            this.AgregarParametroEntrada(cmd, "@p_total_parcial", DbType.String, modelo.TotalParcial);
            this.AgregarParametroEntrada(cmd, "@p_total_final", DbType.String, modelo.TotalFinal);
            this.AgregarParametroEntrada(cmd, "@p_descuentoTotal", DbType.String, modelo.DescuentoTotal);
            this.AgregarParametroEntrada(cmd, "@p_Estado", DbType.String, modelo.Estado);
            this.AgregarParametroEntrada(cmd, "@p_DetalleDeEnvio_id_DetalleEnvio", DbType.String, modelo.IdDetalleEnvio);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
    
            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, OrdenCompra modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_IdPedido", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_FechaCreacion", DbType.String, modelo.FechaCreacion);
            this.AgregarParametroEntrada(cmd, "@p_total_parcial", DbType.String, modelo.TotalParcial);
            this.AgregarParametroEntrada(cmd, "@p_total_final", DbType.String, modelo.TotalFinal);
            this.AgregarParametroEntrada(cmd, "@p_descuentoTotal", DbType.String, modelo.DescuentoTotal);
            this.AgregarParametroEntrada(cmd, "@p_Estado", DbType.String, modelo.Estado);
            this.AgregarParametroEntrada(cmd, "@p_DetalleDeEnvio_id_DetalleEnvio", DbType.String, modelo.IdDetalleEnvio);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarOrdenCompra";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_IdPedido", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarOrdenCompraPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_IdPedido", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarOrdenesCompra";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override OrdenCompra MapearModelo(DbDataReader reader)
        {
            return new OrdenCompra
            {
                Id = Convert.ToInt32(reader["IdPedido"]),
                FechaCreacion = Convert.ToDateTime(reader["FechaCreacion"]),
                TotalParcial = Convert.ToDouble(reader["total_parcial"]),
                TotalFinal = Convert.ToDouble(reader["total_final"]),
                DescuentoTotal = Convert.ToDouble(reader["descuentoTotal"]),
                Estado = (EstadoOrdenCompra)Enum.Parse(typeof(EstadoOrdenCompra), Convert.ToString(reader["Estado"])),
                IdDetalleEnvio = Convert.ToInt32(reader["DetalleDeEnvio_id_DetalleEnvio"]),
                Activo = Convert.ToBoolean(reader["activo"])

            };
        }
    }
}
