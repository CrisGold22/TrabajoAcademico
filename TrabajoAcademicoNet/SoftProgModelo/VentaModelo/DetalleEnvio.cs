using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using PUCP.Inf30.SoftProg.Modelo.Venta.VentaUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.VentaModelo
{
    public class DetalleEnvio:Registro
    {
        public String Descripcion {  get; set; }
        public DateTime FechaEntrega { get; set; }
        public DateTime HorarioEntrega { get; set; }
        public String Direccion { get; set; }
        public Distrito Distrito;

        public DetalleEnvio()
        {

        }

        public DetalleEnvio(String descripcion, DateTime fechaEntrega, DateTime horarioEntrega, String direccion, Distrito distrito)
        {
            this.Descripcion = descripcion;
            this.FechaEntrega = fechaEntrega;
            this.HorarioEntrega = horarioEntrega;
            this.Direccion = direccion;
            this.Distrito = distrito;
        }

        public DetalleEnvio(String descripcion, DateTime fechaEntrega, DateTime horarioEntrega, String direccion, Distrito distrito, int id, bool activo):
            base(id,activo)
        {
            this.Descripcion = descripcion;
            this.FechaEntrega = fechaEntrega;
            this.HorarioEntrega = horarioEntrega;
            this.Direccion = direccion;
            this.Distrito = distrito;
        }

        public String getDistritoString()
        {
            String cadena = "";

            switch (this.Distrito)
            {
                case Distrito.PUEBLO_LIBRE:
                    cadena = "PUEBLO_LIBRE";
                    break;
                case Distrito.SAN_MIGUEL:
                    cadena = "SAN_MIGUEL";
                    break;
                case Distrito.SAN_BORJA:
                    cadena = "SAN_BORJA";
                    break;
                case Distrito.SAN_LUIS:
                    cadena = "SAN_LUIS";
                    break;
                case Distrito.LINCE:
                    cadena = "LINCE";
                    break;
                case Distrito.CALLAO:
                    cadena = "CALLAO";
                    break;
                case Distrito.LA_VICTORIA:
                    cadena = "LA_VICTORIA";
                    break;
            }

            return cadena;
        }

        public void setDistritoString(String distrito)
        {
            switch (distrito)
            {
                case "PUEBLO_LIBRE":
                    this.Distrito = Distrito.PUEBLO_LIBRE;
                    break;
                case "SAN_MIGUEL":
                    this.Distrito = Distrito.SAN_MIGUEL;
                    break;
                case "SAN_BORJA":
                    this.Distrito = Distrito.SAN_BORJA;
                    break;
                case "SAN_LUIS":
                    this.Distrito = Distrito.SAN_LUIS;
                    break;
                case "LINCE":
                    this.Distrito = Distrito.LINCE;
                    break;
                case "CALLAO":
                    this.Distrito = Distrito.CALLAO;
                    break;
                case "LA_VICTORIA":
                    this.Distrito = Distrito.LA_VICTORIA;
                    break;
            }
        }
    }
}
