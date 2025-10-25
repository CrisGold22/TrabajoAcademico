using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl
{
    public interface IComandoDAO<R>
    {
        R ejecutar(DbCommand command);
    }
}
