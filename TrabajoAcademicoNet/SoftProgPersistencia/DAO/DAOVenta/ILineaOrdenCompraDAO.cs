using PUCP.Inf30.SoftProg.Modelo.VentaModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAO.DAOVenta
{
    public interface ILineaOrdenCompraDAO: IPersistibleTransaccional<LineaOrdenCompra,int>
    {
        List<LineaOrdenCompra> listarPorIdOrdenCompra(int id);
        //List<LineaOrdenCompra> listarPorIdOrdenCompra(Connection conn, int id);

    }
}
