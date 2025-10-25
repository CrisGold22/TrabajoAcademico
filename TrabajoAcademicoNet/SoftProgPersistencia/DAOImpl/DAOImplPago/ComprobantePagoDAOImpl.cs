using PUCP.Inf30.SoftProg.Modelo.PagoModelo;
using PUCP.Inf30.SoftProg.Modelo.PagoModelo.PagoUtils;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo.ProductoUtils;
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
    public class ComprobantePagoDAOImpl: 
        TransaccionalBaseDAO<ComprobantePago>, IComprobantePagoDAO
    {
        
        protected override DbCommand ComandoCrear(DbConnection conn, ComprobantePago comprobantePago) 
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idComprobante", DbType.Int32, comprobantePago.Id);
            this.AgregarParametroEntrada(cmd, "@p_fechaEmision", DbType.DateTime, comprobantePago.FechaEmision);
            this.AgregarParametroEntrada(cmd, "@p_RUC", DbType.Int32, comprobantePago.RUC);
            this.AgregarParametroEntrada(cmd, "@p_totalSinImpuestos", DbType.Double, comprobantePago.TotalSinImpuestos);
            this.AgregarParametroEntrada(cmd, "@p_Impuesto", DbType.String, comprobantePago.Impuestos);
            this.AgregarParametroEntrada(cmd, "@p_totalFinal", DbType.Double, comprobantePago.TotalFinal);
            this.AgregarParametroEntrada(cmd, "@p_metodoPago", DbType.String, comprobantePago.MetodoPago);
            //this.AgregarParametroEntrada(cmd, "@p_subTotal", DbType.Double, comprobantePago.SubTotal);
            this.AgregarParametroEntrada(cmd, "@p_activo", DbType.Int32, comprobantePago.Activo);
            this.AgregarParametroEntrada(cmd, "@p_OrdenCompra_IdPedido", DbType.Int32, comprobantePago.IdOrdenCompra);
        
            return cmd;
        }
        protected override DbCommand ComandoActualizar(DbConnection conn, ComprobantePago comprobantePago)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;
            this.AgregarParametroEntrada(cmd, "@p_idComprobante", DbType.Int32, comprobantePago.Id);
            this.AgregarParametroEntrada(cmd, "@p_fechaEmision", DbType.DateTime, comprobantePago.FechaEmision);
            this.AgregarParametroEntrada(cmd, "@p_RUC", DbType.Int32, comprobantePago.RUC);
            this.AgregarParametroEntrada(cmd, "@p_totalSinImpuestos", DbType.Double, comprobantePago.TotalSinImpuestos);
            this.AgregarParametroEntrada(cmd, "@p_Impuesto", DbType.String, comprobantePago.Impuestos);
            this.AgregarParametroEntrada(cmd, "@p_totalFinal", DbType.Double, comprobantePago.TotalFinal);
            this.AgregarParametroEntrada(cmd, "@p_metodoPago", DbType.String, comprobantePago.MetodoPago);
           // this.AgregarParametroEntrada(cmd, "@p_subTotal", DbType.Double, comprobantePago.SubTotal);
            this.AgregarParametroEntrada(cmd, "@p_activo", DbType.Int32, comprobantePago.Activo);
            this.AgregarParametroEntrada(cmd, "@p_OrdenCompra_IdPedido", DbType.Int32, comprobantePago.IdOrdenCompra);

            return cmd;
        }
        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idComprobante", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idComprobante", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarComprobantePago";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override ComprobantePago MapearModelo(DbDataReader reader)
        {
            return new ComprobantePago
            {
                Id = Convert.ToInt32(reader["idComprobante"]),
                FechaEmision = Convert.ToDateTime(reader["fechaEmision"]),
                RUC = Convert.ToInt32(reader["p_RUC"]),
                TotalSinImpuestos = Convert.ToDouble(reader["totalSinImpuestos"]),
                Impuestos = Convert.ToDouble(reader["Impuesto"]),
                TotalFinal = Convert.ToDouble(reader["totalFinal"]),
                MetodoPago = (MetodoPago)Enum.Parse(typeof(MetodoPago), Convert.ToString(reader["metodoPago"])),
               // SubTotal = Convert.ToDouble(reader["p_subTotal"]),
                Activo = Convert.ToBoolean(reader["activo"]),
                IdOrdenCompra = Convert.ToInt32(reader["OrdenCompra_IdPedido"])
            };
        }
    }
}
