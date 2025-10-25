using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using System;

namespace PUCP.Inf30.SoftProg.Modelo.PersonaModelo
{
    public class AdministradorSistema: Persona 
    {
        public Cargo Cargo;
        public double Sueldo { get; set; }
        public Jerarquia Rango;
        public CuentaUsuario CuentaUsuario { get; set; }

        public AdministradorSistema()
        {

        }

        public AdministradorSistema(Cargo cargo, double sueldo, Jerarquia rango, CuentaUsuario cuentaUsuario)
        {
            this.Cargo = cargo;
            this.Sueldo = sueldo;
            this.Rango = rango;
            this.CuentaUsuario = cuentaUsuario;
        }

        public AdministradorSistema(Cargo cargo, double sueldo, Jerarquia rango, CuentaUsuario cuentaUsuario, String dni, 
            String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, DateTime fechaNacimiento, int telefono): 
            base(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono)
        {
            this.Cargo = cargo;
            this.Sueldo = sueldo;
            this.Rango = rango;
            this.CuentaUsuario = cuentaUsuario;
        }

        public AdministradorSistema(Cargo cargo, double sueldo, Jerarquia rango, CuentaUsuario cuentaUsuario, String dni, String nombre,
            String apellidoPaterno, String apellidoMaterno, Genero genero, DateTime fechaNacimiento, int telefono, int id, bool activo):
            base(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, id, activo)
        {
            this.Cargo = cargo;
            this.Sueldo = sueldo;
            this.Rango = rango;
            this.CuentaUsuario = cuentaUsuario;
        }


        public String getCargoString()
        {
            String cadena = "";

            switch (this.Cargo)
            {
                case Cargo.ADMINISTRADOR:
                    cadena = "ADMINISTRADOR";
                    break;
                case Cargo.GESTOR_PEDIDOS:
                    cadena = "GESTOR_PEDIDOS";
                    break;
                case Cargo.GESTOR_PRODUCTOS:
                    cadena = "GESTOR_PRODUCTOS";
                    break;
            }

            return cadena;
        }

        public String getRangoString()
        {
            String cadena = "";

            switch (this.Rango)
            {
                case Jerarquia.COMPLETO:
                    cadena = "COMPLETO";
                    break;
                case Jerarquia.PARCIAL:
                    cadena = "PARCIAL";
                    break;
            }

            return cadena;
        }

        public void setCargoString(String cargo)
        {
            switch (cargo)
            {
                case "ADMINISTRADOR":
                    this.Cargo = Cargo.ADMINISTRADOR;
                    break;
                case "GESTOR_PEDIDOS":
                    this.Cargo = Cargo.GESTOR_PEDIDOS;
                    break;
                case "GESTOR_PRODUCTOS":
                    this.Cargo = Cargo.GESTOR_PRODUCTOS;
                    break;
            }
        }

        public void setRangoString(String rango)
        {
            switch (rango)
            {
                case "COMPLETO":
                    this.Rango = Jerarquia.COMPLETO;
                    break;
                case "PARCIAL":
                    this.Rango = Jerarquia.PARCIAL;
                    break;
            }
        }
    }
}
