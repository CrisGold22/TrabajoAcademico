using PUCP.Inf30.SoftProg.Modelo.PagoModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAO.DAOPago
{
    public interface ILineaComprobantePagoDAO: IPersistibleTransaccional<LineaComprobantePago, int>
    {
        List<LineaComprobantePago> ListarPorIdComprobante(int id);
        /*List<LineaComprobantePago> ListarPorIdComprobante(Connection conn, int id);*/
    }
}
