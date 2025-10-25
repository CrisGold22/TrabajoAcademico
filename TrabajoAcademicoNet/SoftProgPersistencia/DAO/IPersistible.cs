using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia
{
    public interface IPersistible<T,I>
    {
        I crear(T modelo);
        bool actualizar(T modelo);
        bool eliminar(I id);
        T leer(I id);
        List<T> leerTodos();
    }
}
