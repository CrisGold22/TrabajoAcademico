using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.DB
{
    public class MySQLDBManagerFactory : DBManagerFactory
    {
        public override DBManager CrearDBManager(string host, int puerto, string esquema,
                                                 string usuario, string password)
        {

            return MySQLDBManager.GetInstance(host, puerto, esquema, usuario,
                                              password);
        }
    }
}
