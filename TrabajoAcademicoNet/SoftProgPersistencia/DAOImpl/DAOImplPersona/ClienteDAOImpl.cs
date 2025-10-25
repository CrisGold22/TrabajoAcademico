using MySqlX.XDevAPI;
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
    public class ClienteDAOImpl : BaseDAOImpl<Cliente>, IClienteDAO
    {
        

        protected override DbCommand ComandoActualizar(DbConnection conn, Cliente cliente)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "actualizarCliente";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCliente", DbType.Int32, cliente.Id);
            this.AgregarParametroEntrada(cmd, "@p_lineaCredito", DbType.Double, cliente.LineaCredito);
            this.AgregarParametroEntrada(cmd, "@p_Categoria", DbType.String, cliente.Categoria);
            this.AgregarParametroEntrada(cmd, "@p_numeroDePedido_Historico", DbType.Int32, cliente.NumeroPedidosHistorico);
            this.AgregarParametroEntrada(cmd, "@p_numeroDePedido_MensualPro", DbType.Int32, cliente.NumeroPedidosMensualPro);
            this.AgregarParametroEntrada(cmd, "@p_dni", DbType.String, cliente.Dni);
            this.AgregarParametroEntrada(cmd, "@p_nombre", DbType.String, cliente.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_apellidoPaterno", DbType.String, cliente.ApellidoPaterno);
            this.AgregarParametroEntrada(cmd, "@p_apellidoMaterno", DbType.String, cliente.ApellidoMaterno);
            this.AgregarParametroEntrada(cmd, "@p_genero", DbType.String, cliente.Genero);
            this.AgregarParametroEntrada(cmd, "@p_fechaNacimiento", DbType.DateTime, cliente.FechaNacimiento);
            this.AgregarParametroEntrada(cmd, "@p_telefono", DbType.Int32, cliente.Telefono);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, cliente.Activo);

            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, Cliente cliente)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarCliente";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCliente", DbType.Int32, cliente.Id);
            this.AgregarParametroEntrada(cmd, "@p_lineaCredito", DbType.Double, cliente.LineaCredito);
            this.AgregarParametroEntrada(cmd, "@p_Categoria", DbType.String, cliente.Categoria);
            this.AgregarParametroEntrada(cmd, "@p_numeroDePedido_Historico", DbType.Int32, cliente.NumeroPedidosHistorico);
            this.AgregarParametroEntrada(cmd, "@p_numeroDePedido_MensualPro", DbType.Int32, cliente.NumeroPedidosMensualPro);
            this.AgregarParametroEntrada(cmd, "@p_dni", DbType.String, cliente.Dni);
            this.AgregarParametroEntrada(cmd, "@p_nombre", DbType.String, cliente.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_apellidoPaterno", DbType.String, cliente.ApellidoPaterno);
            this.AgregarParametroEntrada(cmd, "@p_apellidoMaterno", DbType.String, cliente.ApellidoMaterno);
            this.AgregarParametroEntrada(cmd, "@p_genero", DbType.String, cliente.Genero);
            this.AgregarParametroEntrada(cmd, "@p_fechaNacimiento", DbType.DateTime, cliente.FechaNacimiento);
            this.AgregarParametroEntrada(cmd, "@p_telefono", DbType.Int32, cliente.Telefono);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, cliente.Activo);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarCliente";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCliente", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarCliente";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCliente", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarClientes";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override Cliente MapearModelo(DbDataReader reader)
        {
            return new Cliente
            {
                Id = Convert.ToInt32(reader["id"]),
                Categoria = (CategoriaCliente)Enum.Parse(typeof(CategoriaCliente), Convert.ToString(reader["categoria"])),
                NumeroPedidosHistorico = Convert.ToInt32(reader["numeroPedidosHistorico"]),
                NumeroPedidosMensualPro = Convert.ToInt32(reader["numeroDePedido_MensualPro"]),
                Dni = Convert.ToString(reader["p_dni"]),
                Nombre = Convert.ToString(reader["nombre"]),
                ApellidoPaterno = Convert.ToString(reader["apellidoPaterno"]),
                ApellidoMaterno = Convert.ToString(reader["apellidoMaterno"]),
                Genero = (Genero)Enum.Parse(typeof(Genero), Convert.ToString(reader["genero"])),
                FechaNacimiento = Convert.ToDateTime(reader["fechaNacimiento"]),
                Telefono = Convert.ToInt32(reader["telefono"]),
                Activo = Convert.ToBoolean(reader["activo"]),
                LineaCredito = Convert.ToDouble(reader["lineaCredito"])
            };
        }

        protected DbCommand ComandoBuscarPorDni(DbConnection conn, string dni)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarClientePorDni";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCliente", DbType.Int32, dni);

            return cmd;
        }

        public Cliente buscarPorDNI(string dni)
        {
            return EjecutarComando(conn => {
                using (DbCommand cmd = this.ComandoBuscarPorDni(conn, dni))
                using (DbDataReader reader = cmd.ExecuteReader())
                {
                    if (!reader.HasRows)
                    {
                        Console.Error.WriteLine("No se encontro el cliente con dni: " + dni);
                        return default;
                    }

                    reader.Read();
                    return this.MapearModelo(reader);
                }
            });
        }


    }
}
