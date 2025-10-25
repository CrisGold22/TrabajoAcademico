using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Data.Common;

namespace PUCP.Inf30.SoftProg.Persistencia
{
    public interface IPersistibleTransaccional<T,I>:IPersistible<T,I>
    {
        I crear(T modelo, DbTransaction transaccion);
        bool actualizar(T modelo, DbTransaction transaccion);
        bool eliminar(I id, DbTransaction transaccion);
    }
}
