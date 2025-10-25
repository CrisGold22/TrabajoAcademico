using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.DB
{
    public class Db2DBManagerFactory : DBManagerFactory
    {
        public override DBManager CrearDBManager(String host, int puerto, String esquema,
            String usuario, String password)
        {
            return Db2DBManager.getInstance(host, puerto, esquema, usuario, password);
        }
    }
}
