using PUCP.Inf30.SoftProg.Modelo.PagoModelo;
using PUCP.Inf30.SoftProg.Modelo.PagoModelo.PagoUtils;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOPersona;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Data.Odbc;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplPersona
{
    public class AdministradorSistemaDAOImpl :
        BaseDAOImpl<AdministradorSistema>, IAdministradorSistemaDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, AdministradorSistema administradorSistema)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarAdministrador";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idAdministrador", DbType.Int32, administradorSistema.Id);
            this.AgregarParametroEntrada(cmd, "@p_cargo", DbType.String, administradorSistema.Cargo);
            this.AgregarParametroEntrada(cmd, "@p_rango", DbType.String, administradorSistema.Rango);
            this.AgregarParametroEntrada(cmd, "@p_dni", DbType.String, administradorSistema.Dni);
            this.AgregarParametroEntrada(cmd, "@p_nombre", DbType.String, administradorSistema.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_apellidoPaterno", DbType.String, administradorSistema.ApellidoPaterno);
            this.AgregarParametroEntrada(cmd, "@p_apellidoMaterno", DbType.String, administradorSistema.ApellidoMaterno);
            this.AgregarParametroEntrada(cmd, "@p_genero", DbType.String, administradorSistema.Genero);
            this.AgregarParametroEntrada(cmd, "@p_fechaNacimiento", DbType.DateTime, administradorSistema.FechaNacimiento);
            this.AgregarParametroEntrada(cmd, "@p_telefono", DbType.Int32, administradorSistema.Telefono);
            this.AgregarParametroEntrada(cmd, "@p_Sueldo", DbType.Double, administradorSistema.Sueldo);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, administradorSistema.Activo);
            
            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, AdministradorSistema administradorSistema)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarAdministrador";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idAdministrador", DbType.Int32, administradorSistema.Id);
            this.AgregarParametroEntrada(cmd, "@p_cargo", DbType.String, administradorSistema.Cargo);
            this.AgregarParametroEntrada(cmd, "@p_rango", DbType.String, administradorSistema.Rango);
            this.AgregarParametroEntrada(cmd, "@p_dni", DbType.String, administradorSistema.Dni);
            this.AgregarParametroEntrada(cmd, "@p_nombre", DbType.String, administradorSistema.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_apellidoPaterno", DbType.String, administradorSistema.ApellidoPaterno);
            this.AgregarParametroEntrada(cmd, "@p_apellidoMaterno", DbType.String, administradorSistema.ApellidoMaterno);
            this.AgregarParametroEntrada(cmd, "@p_genero", DbType.String, administradorSistema.Genero);
            this.AgregarParametroEntrada(cmd, "@p_fechaNacimiento", DbType.DateTime, administradorSistema.FechaNacimiento);
            this.AgregarParametroEntrada(cmd, "@p_telefono", DbType.Int32, administradorSistema.Telefono);
            this.AgregarParametroEntrada(cmd, "@p_Sueldo", DbType.Double, administradorSistema.Sueldo);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, administradorSistema.Activo);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarAdministrador";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idAdministrador", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarAdministradorPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idAdministrador", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "leerAdministradores";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override AdministradorSistema MapearModelo(DbDataReader reader)
        {
            return new AdministradorSistema
            {
                Id = Convert.ToInt32(reader["idLineaComprobantePago"]),
                Rango = (Jerarquia)Enum.Parse(typeof(Jerarquia), Convert.ToString(reader["rango"])),
                Cargo = (Cargo)Enum.Parse(typeof(Cargo), Convert.ToString(reader["cargo"])),
                Dni = Convert.ToString(reader["dni"]),
                Nombre = Convert.ToString(reader["nombre"]),
                ApellidoPaterno = Convert.ToString(reader["apellidoPaterno"]),
                ApellidoMaterno = Convert.ToString(reader["apellidoMaterno"]),
                Genero = (Genero)Enum.Parse(typeof(Genero), Convert.ToString(reader["genero"])),
                FechaNacimiento = Convert.ToDateTime(reader["fechaNacimiento"]),
                Telefono = Convert.ToInt32(reader["telefono"]),
                Sueldo = Convert.ToDouble(reader["sueldo"]),
                Activo = Convert.ToBoolean(reader["activo"])

            };
        }
    }
}
