using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo.ProductoUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.ProductoModelo
{
    public class CategoriaProducto:Registro
    {
        public TipoCategoria Nombre;
        public String Descripcion {  get; set; }
        public int IdCategoriaPadre { get; set; }

        public CategoriaProducto()
        {

        }

        public CategoriaProducto(TipoCategoria nombre, String descripcion, int idCategoriaPadre)
        {
            this.Nombre = nombre;
            this.Descripcion = descripcion;
            this.IdCategoriaPadre = idCategoriaPadre;
        }

        public CategoriaProducto(TipoCategoria nombre, String descripcion, int idCategoriaPadre, int id, bool activo):base(id,activo) 
        {
            
            this.Nombre = nombre;
            this.Descripcion = descripcion;
            this.IdCategoriaPadre = idCategoriaPadre;
        }

        public String getNombreString()
        {
            String cadena = "";

            switch (this.Nombre)
            {
                case TipoCategoria.ABARROTES:
                    cadena = "ABARROTES";
                    break;
                case TipoCategoria.BEBIDAS:
                    cadena = "BEBIDAS";
                    break;
                case TipoCategoria.CUIDADO_PERSONAL:
                    cadena = "CUIDADO_PERSONA";
                    break;
                case TipoCategoria.ELECTRONICA:
                    cadena = "ELECTRONICA";
                    break;
                case TipoCategoria.HOGAR:
                    cadena = "HOGAR";
                    break;
                case TipoCategoria.LIMPIEZA:
                    cadena = "LIMPIEZA";
                    break;
                case TipoCategoria.ROPA:
                    cadena = "ROPA";
                    break;
            }

            return cadena;
        }

        public void setNombreString(String nombre)
        {
            switch (nombre)
            {
                case "ABARROTES":
                    this.Nombre = TipoCategoria.ABARROTES;
                    break;
                case "BEBIDAS":
                    this.Nombre = TipoCategoria.BEBIDAS;
                    break;
                case "CUIDADO_PERSONAL":
                    this.Nombre = TipoCategoria.CUIDADO_PERSONAL;
                    break;
                case "ELECTRONICA":
                    this.Nombre = TipoCategoria.ELECTRONICA;
                    break;
                case "HOGAR":
                    this.Nombre = TipoCategoria.HOGAR;
                    break;
                case "LIMPIEZA":
                    this.Nombre = TipoCategoria.LIMPIEZA;
                    break;
                case "ROPA":
                    this.Nombre = TipoCategoria.ROPA;
                    break;
            }
        }
    }
}
