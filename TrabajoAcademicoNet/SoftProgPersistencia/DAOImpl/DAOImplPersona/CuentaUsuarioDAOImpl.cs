using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOPersona;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplPersona
{
    public class CuentaUsuarioDAOImpl :
        BaseDAOImpl<CuentaUsuario>, ICuentaUsuarioDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, CuentaUsuario cuentaUsuario)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarCuentaUsuario";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCuentaUsuario", DbType.Int32, cuentaUsuario.Id);
            this.AgregarParametroEntrada(cmd, "@p_userName", DbType.String, cuentaUsuario.Username);
            this.AgregarParametroEntrada(cmd, "@p_password", DbType.String, cuentaUsuario.Password);
            this.AgregarParametroEntrada(cmd, "@p_Administrador_idAdministrador", DbType.Int32, cuentaUsuario.IdAdministrador);
            this.AgregarParametroEntrada(cmd, "@p_cliente_idCliente", DbType.Int32, cuentaUsuario.IdCliente);
          
            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, CuentaUsuario cuentaUsuario)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarCuentaUsuario";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCuentaUsuario", DbType.Int32, cuentaUsuario.Id);
            this.AgregarParametroEntrada(cmd, "@p_userName", DbType.String, cuentaUsuario.Username);
            this.AgregarParametroEntrada(cmd, "@p_password", DbType.String, cuentaUsuario.Password);
            this.AgregarParametroEntrada(cmd, "@p_Administrador_idAdministrador", DbType.Int32, cuentaUsuario.IdAdministrador);
            this.AgregarParametroEntrada(cmd, "@p_cliente_idCliente", DbType.Int32, cuentaUsuario.IdCliente);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarCuentaUsuario";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCuentaUsuario", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarCuentaUsuario";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCuentaUsuario", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarCuentaUsuarios";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override CuentaUsuario MapearModelo(DbDataReader reader)
        {
            return new CuentaUsuario
            {
                Id = Convert.ToInt32(reader["idCuentaUsuario"]),
                Username = Convert.ToString(reader["userName"]),
                Password = Convert.ToString(reader["password"]),
                IdCliente = Convert.ToInt32(reader["Cliente_idCliente"]),
                IdAdministrador = Convert.ToInt32(reader["Administrador_idAdministrador"]),

            };
        }
    }
}
