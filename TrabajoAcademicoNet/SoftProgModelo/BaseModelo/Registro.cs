using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.BaseModelo 
{
    public class Registro
    {
        public int Id {  get; set; }
        public bool Activo { get; set; }

        public Registro() { }
        public Registro(int id, bool activo)
        {
            this.Id = id;
            this.Activo = activo;
        }

        public void setActivo(int activo)
        {
            if (activo == 1)
                this.Activo = true;
            else 
                this.Activo = false;
        }
    }
}
