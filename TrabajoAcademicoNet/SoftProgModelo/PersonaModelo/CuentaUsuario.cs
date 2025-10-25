using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.PersonaModelo
{
    public class CuentaUsuario: Registro
    {
        public String Username { get; set; }
        public String Password { get; set; }
        public int IdCliente { get; set; }
        public int IdAdministrador { get; set; }

        public CuentaUsuario() { }
        public CuentaUsuario(String username, String password, int idCliente, 
                                int idAdministrador)
        {
            this.Username = username;
            this.Password = password;
            this.IdCliente = idCliente;
            this.IdAdministrador = idAdministrador;
        }

        public CuentaUsuario(String username, String password, int idCliente, 
                            int idAdministrador, int id, bool activo): base(id,activo)
        {
            
            this.Username = username;
            this.Password = password;
            this.IdCliente = idCliente;
            this.IdAdministrador = idAdministrador;
        }
    }
}
