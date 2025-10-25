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
    public class DetalleEnvioDAOImpl :
        BaseDAOImpl<DetalleEnvio>, IDetalleEnvioDAO
    {

        protected override DbCommand ComandoActualizar(DbConnection conn, DetalleEnvio modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "actualizarDetalleDeEnvio";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_descripcion", DbType.String, modelo.Descripcion);
            this.AgregarParametroEntrada(cmd, "@p_fechaEntrega", DbType.DateTime, modelo.FechaEntrega);
            this.AgregarParametroEntrada(cmd, "@p_horarioEntrega", DbType.DateTime, modelo.HorarioEntrega);
            this.AgregarParametroEntrada(cmd, "@p_Direccion", DbType.String, modelo.Direccion);
            this.AgregarParametroEntrada(cmd, "@P_Distrito", DbType.String, modelo.Distrito);

            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, DetalleEnvio modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarDetalleDeEnvio";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_descripcion", DbType.String, modelo.Descripcion);
            this.AgregarParametroEntrada(cmd, "@p_fechaEntrega", DbType.DateTime, modelo.FechaEntrega);
            this.AgregarParametroEntrada(cmd, "@p_horarioEntrega", DbType.DateTime, modelo.HorarioEntrega);
            this.AgregarParametroEntrada(cmd, "@p_Direccion", DbType.String, modelo.Direccion);
            this.AgregarParametroEntrada(cmd, "@P_Distrito", DbType.String, modelo.Distrito);
      
            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarDetalleDeEnvio";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarDetalleDeEnvioPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_id_DetalleEnvio", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarDetallesDeEnvio";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override DetalleEnvio MapearModelo(DbDataReader reader)
        {
            return new DetalleEnvio
            {
                Id = Convert.ToInt32(reader["id_DetalleEnvio"]),
                Descripcion = Convert.ToString(reader["descripcion"]),
                FechaEntrega = Convert.ToDateTime(reader["fechaEntrega"]),
                HorarioEntrega = Convert.ToDateTime(reader["horarioEntrega"]),
                Direccion = Convert.ToString(reader["Direccion"]),
                Distrito = (Distrito)Enum.Parse(typeof(Distrito), Convert.ToString(reader["rango"]))
            };
        }

        public bool actualizar(DetalleEnvio modelo, DbTransaction transaccion)
        {
            throw new NotImplementedException();
        }

        public int crear(DetalleEnvio modelo, DbTransaction transaccion)
        {
            throw new NotImplementedException();
        }

        public bool eliminar(int id, DbTransaction transaccion)
        {
            throw new NotImplementedException();
        }

    }
}
