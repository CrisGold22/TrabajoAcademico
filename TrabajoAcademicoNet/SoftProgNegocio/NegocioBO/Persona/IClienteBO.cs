using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Negocio.NegocioBO.Persona
{
    public interface IClienteBO: IGestionable<Cliente>
    {
        Cliente buscarPorDNI(String dni);
    }
}
