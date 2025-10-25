using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PUCP.Inf30.SoftProg.DB.Utils;

namespace PUCP.Inf30.SoftProg.DB
{
    public abstract class DBManagerFactory
    {
        public abstract DBManager CrearDBManager(string host, int puerto,
                                                 string esquema, string usuario,
                                                 string password);
    }
}
