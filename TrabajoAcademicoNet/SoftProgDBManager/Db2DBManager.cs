using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Management.Instrumentation;
using System.Text;
using System.Threading.Tasks;
using PUCP.Inf30.SoftProg.DB;
using PUCP.Inf30.SoftProg.DB.Utils;

namespace PUCP.Inf30.SoftProg.DB
{
    public class Db2DBManager : DBManager
    {
        private static Db2DBManager instance;
        protected Db2DBManager() { }

        protected Db2DBManager(String host, int puerto, String esquema,
                              String usuario, String password) 
            : base(host, puerto, esquema, usuario, password, TipoDB.Db2)
        {
        }

        public static Db2DBManager getInstance(String host, int puerto,
                                                       String esquema,
                                                       String usuario,
                                                       String password)
        {
            if (instance == null)
            {   
                
                instance = new Db2DBManager(host, puerto, esquema, usuario,
                                               password);
            }
            return instance;
        }

       
        public override DbConnection GetConnection()
        {
            try {
                //Class.forName("com.ibm.db2.jcc.DB2Driver");
                String cadenaConexion = CadenaConexion();
                return new SqlConnection (cadenaConexion);
            }
            catch (Exception e) {
                Console.Error.WriteLine(e);
                throw e;
            }
        }
    }
}
