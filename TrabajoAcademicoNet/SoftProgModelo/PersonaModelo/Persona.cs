using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.PersonaModelo
{
    public class Persona : Registro
    {
        public String Dni {  get; set; }
        public String Nombre { get; set; }
        public String ApellidoPaterno { get; set; }
        public String ApellidoMaterno { get; set; }
        public Genero Genero;
        public DateTime FechaNacimiento { get; set; }
        public int Telefono { get; set; }
        
        public Persona() { }
        public Persona(String dni, String nombre, String apellidoPaterno, 
            String apellidoMaterno, Genero genero, DateTime fechaNacimiento, int telefono)
        {
            this.Dni = dni;
            this.Nombre = nombre;
            this.ApellidoPaterno = apellidoPaterno;
            this.ApellidoMaterno = apellidoMaterno;
            this.Genero = genero;
            this.FechaNacimiento = fechaNacimiento;
            this.Telefono = telefono;
        }
        public Persona(String dni, String nombre, String apellidoPaterno, 
            String apellidoMaterno, Genero genero, DateTime fechaNacimiento, int telefono,
            int id, bool activo) : base (id, activo)
        {
            
            this.Dni = dni;
            this.Nombre = nombre;
            this.ApellidoPaterno = apellidoPaterno;
            this.ApellidoMaterno = apellidoMaterno;
            this.Genero = genero;
            this.FechaNacimiento = fechaNacimiento;
            this.Telefono = telefono;
        }

        public String getGeneroString()
        {
            String cadena = "";

            switch (this.Genero)
            {
                case Genero.HOMBRE:
                    cadena = "HOMBRE";
                    break;
                case Genero.MUJER:
                    cadena = "MUJER";
                    break;
                case Genero.NO_ESPECIFICADO:
                    cadena = "NO_ESPECIFICADO";
                    break;
            }

            return cadena;
        }

        public void setGeneroString(String genero)
        {
            switch (genero)
            {
                case "HOMBRE":
                    this.Genero = Genero.HOMBRE;
                    break;
                case "MUJER":
                    this.Genero = Genero.MUJER;
                    break;
                case "NO_ESPECIFICADO":
                    this.Genero = Genero.NO_ESPECIFICADO;
                    break;
            }
        }

    }
    

}
