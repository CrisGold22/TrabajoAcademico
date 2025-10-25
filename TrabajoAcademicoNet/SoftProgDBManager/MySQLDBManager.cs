using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;
using PUCP.Inf30.SoftProg.DB;
using PUCP.Inf30.SoftProg.DB.Utils;

namespace PUCP.Inf30.SoftProg.DB
{
    public class MySQLDBManager : DBManager
    {
        private static MySQLDBManager instancia;

        protected MySQLDBManager() { }

        protected MySQLDBManager(string host, int puerto, string esquema,
                                 string usuario, string password)
            : base(host, puerto, esquema, usuario, password, TipoDB.MySQL)
        {
        }

        public static MySQLDBManager GetInstance(string host,
                                                 int puerto,
                                                 string esquema,
                                                 string usuario,
                                                 string password)
        {
            if (instancia == null)
            {
                instancia = new MySQLDBManager(host, puerto, esquema, usuario, password);
            }
            return instancia;
        }
        public override DbConnection GetConnection()
        {
            try
            {
                /*
                Por ahora creamos una conexión cada vez que se necesita acceder
                a la base de datos. Para una aplicación académica es aceptable,
                pero en un sistema productivo se debe usar un pool de conexiones.
                */
                string cadenaConexion = CadenaConexion();
                return new MySqlConnection(cadenaConexion);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e);
                throw;
            }
        }
    }
}
