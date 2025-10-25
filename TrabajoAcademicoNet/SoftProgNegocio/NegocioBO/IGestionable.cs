using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Negocio.NegocioBO
{
    public interface IGestionable<T>
    {
        List<T> listar();
        void insertar(T modelo);
        void actualizar(T modelo);
        T obtener(int id);
        void eliminar(int id);
    }
}
