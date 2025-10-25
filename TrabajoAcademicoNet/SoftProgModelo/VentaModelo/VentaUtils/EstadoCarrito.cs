using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.Venta.VentaUtils
{
    public enum EstadoCarrito
    {
        VACIO, EN_PROCESO, 
        EN_REVISION, 
        CONFIRMADO, 
        CANCELADO, 
        EXPIRADO, 
        COMPLETADO, 
        PENDIENTE, 
        PROCESADO
    }
}
