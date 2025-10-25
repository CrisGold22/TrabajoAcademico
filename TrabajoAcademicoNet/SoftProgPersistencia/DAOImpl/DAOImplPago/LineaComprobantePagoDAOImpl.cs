using PUCP.Inf30.SoftProg.Modelo.PagoModelo;
using PUCP.Inf30.SoftProg.Modelo.PagoModelo.PagoUtils;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOPago;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplPago
{
    public class LineaComprobantePagoDAOImpl :
        TransaccionalBaseDAO<LineaComprobantePago>, ILineaComprobantePagoDAO
    {
        public List<LineaComprobantePago> ListarPorIdComprobante(int id)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoActualizar(DbConnection conn, LineaComprobantePago modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarLineaComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaComprobantePago", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_montoPagado", DbType.DateTime, modelo.MontoPagado);
            this.AgregarParametroEntrada(cmd, "@p_montoImpuesto", DbType.Int32, modelo.MontoImpuesto);
            this.AgregarParametroEntrada(cmd, "@p_activo", DbType.Double, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_ComprobantePago_idComprobante", DbType.String, modelo.IdComprobantePago);
            this.AgregarParametroEntrada(cmd, "@p_codigo", DbType.Double, modelo.Codigo);
            this.AgregarParametroEntrada(cmd, "@p_cantidad", DbType.String, modelo.Cantidad);
            this.AgregarParametroEntrada(cmd, "@p_subtotal", DbType.String, modelo.SubTotal);

            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, LineaComprobantePago modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarLineaComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaComprobantePago", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_montoPagado", DbType.DateTime, modelo.MontoPagado);
            this.AgregarParametroEntrada(cmd, "@p_montoImpuesto", DbType.Int32, modelo.MontoImpuesto);
            this.AgregarParametroEntrada(cmd, "@p_activo", DbType.Double, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_ComprobantePago_idComprobante", DbType.String, modelo.IdComprobantePago);
            this.AgregarParametroEntrada(cmd, "@p_codigo", DbType.Double, modelo.Codigo);
            this.AgregarParametroEntrada(cmd, "@p_cantidad", DbType.String, modelo.Cantidad);
            this.AgregarParametroEntrada(cmd, "@p_subtotal", DbType.String, modelo.SubTotal);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarLineaComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaComprobantePago", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarLineaComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idLineaComprobantePago", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarLineaComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override LineaComprobantePago MapearModelo(DbDataReader reader)
        {
            return new LineaComprobantePago
            {
                Id = Convert.ToInt32(reader["idLineaComprobantePago"]),
                MontoPagado = Convert.ToDouble(reader["montoPagado"]),
                MontoImpuesto = Convert.ToDouble(reader["montoImpuesto"]),
                Activo = Convert.ToBoolean(reader["activo"]),
                IdComprobantePago = Convert.ToInt32(reader["ComprobantePago_idComprobante"]),
                Codigo = Convert.ToInt32(reader["codigo"]),
                Cantidad = Convert.ToInt32(reader["cantidad"]),
                SubTotal = Convert.ToDouble(reader["subtotal"])
            };
        }
        
    }
}
