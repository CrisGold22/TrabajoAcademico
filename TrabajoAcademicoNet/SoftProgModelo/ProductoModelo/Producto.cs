using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo.ProductoUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.ProductoModelo
{
    public class Producto : Registro
    {
        public String Nombre { get; set; }
        public String SKU { get; set; }
        public String Descripcion { get; set; }
        public double PrecioUnitario { get; set; }
        public double PrecioAlMayor { get; set; }
        public UnidadMedida MedidaAlMayor;
        public int StockDisponible { get; set; }
        public int StockMinimo { get; set; }
        public int StockMaximo { get; set; }
        public int IdCategoria { get; set; }

        public Producto()
        {

        }
        public Producto(String nombre, String SKU, String descripcion, double precioUnitario,
            double precioAlMayor, UnidadMedida medidaAlMayor, int stockDisponible, int stockMinimo, int stockMaximo, int idCategoria)
        {
            this.Nombre = nombre;
            this.SKU = SKU;
            this.Descripcion = descripcion;
            this.PrecioUnitario = precioUnitario;
            this.PrecioAlMayor = precioAlMayor;
            this.MedidaAlMayor = medidaAlMayor;
            this.StockDisponible = stockDisponible;
            this.StockMinimo = stockMinimo;
            this.StockMaximo = stockMaximo;
            this.IdCategoria = idCategoria;
        }

        public Producto(String nombre, String SKU, String descripcion, double precioUnitario, double precioAlMayor,
            UnidadMedida medidaAlMayor, int stockDisponible, int stockMinimo, int stockMaximo, int idCategoria, int id, bool activo) :
            base(id, activo)
        {
            this.Nombre = nombre;
            this.SKU = SKU;
            this.Descripcion = descripcion;
            this.PrecioUnitario = precioUnitario;
            this.PrecioAlMayor = precioAlMayor;
            this.MedidaAlMayor = medidaAlMayor;
            this.StockDisponible = stockDisponible;
            this.StockMinimo = stockMinimo;
            this.StockMaximo = stockMaximo;
            this.IdCategoria = idCategoria;
        }
        public String getMedidaAlMayorString()
        {
            String Cadena = "";

            switch (this.MedidaAlMayor)
            {
                case UnidadMedida.BOTELLA:
                    Cadena = "BOTELLA";
                    break;
                case UnidadMedida.CAJA:
                    Cadena = "CAJA";
                    break;
                case UnidadMedida.GALON:
                    Cadena = "GALON";
                    break;
                case UnidadMedida.KILOGRAMO:
                    Cadena = "KILOGRAMO";
                    break;
                case UnidadMedida.LITRO:
                    Cadena = "LITRO";
                    break;
                case UnidadMedida.PACK:
                    Cadena = "PACK";
                    break;
                case UnidadMedida.PAQUETE:
                    Cadena = "PAQUETE";
                    break;
                case UnidadMedida.SACO:
                    Cadena = "SACO";
                    break;
            }
            return Cadena;
        }

        public void setMedidaAlMayorString(String medida)
        {
            switch (medida)
            {
                case "BOTELLA":
                    this.MedidaAlMayor = UnidadMedida.BOTELLA;
                    break;
                case "CAJA":
                    this.MedidaAlMayor = UnidadMedida.CAJA;
                    break;
                case "GALON":
                    this.MedidaAlMayor = UnidadMedida.GALON;
                    break;
                case "KILOGRAMO":
                    this.MedidaAlMayor = UnidadMedida.KILOGRAMO;
                    break;
                case "LITRO":
                    this.MedidaAlMayor = UnidadMedida.LITRO;
                    break;
                case "PACK":
                    this.MedidaAlMayor = UnidadMedida.PACK;
                    break;
                case "PAQUETE":
                    this.MedidaAlMayor = UnidadMedida.PAQUETE;
                    break;
                case "SACO":
                    this.MedidaAlMayor = UnidadMedida.SACO;
                    break;
            }
        }

    }
}