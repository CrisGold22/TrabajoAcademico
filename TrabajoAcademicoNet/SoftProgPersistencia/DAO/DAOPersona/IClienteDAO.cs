using MySqlX.XDevAPI;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAO.DAOPersona
{
    public interface IClienteDAO: IPersistible<Cliente, int>
    {
        Cliente buscarPorDNI(String dni);
    }
}
