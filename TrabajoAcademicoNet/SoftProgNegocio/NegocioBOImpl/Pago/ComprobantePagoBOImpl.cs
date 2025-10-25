using PUCP.Inf30.SoftProg.DB;
using PUCP.Inf30.SoftProg.Modelo.PagoModelo;
using PUCP.Inf30.SoftProg.Negocio.NegocioBO.Pago;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOPago;
using PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplPago;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Reflection.Emit;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Negocio.NegocioBOImpl.Pago
{
    public class ComprobantePagoBOImpl : IComprobantePagoBO
    {
        private readonly IComprobantePagoDAO comprobantePagoDAO;
        private readonly ILineaComprobantePagoDAO lineaComprobantePagoDAO;
        
        public ComprobantePagoBOImpl()
        {
            this.comprobantePagoDAO = new ComprobantePagoDAOImpl();
            this.lineaComprobantePagoDAO = new LineaComprobantePagoDAOImpl();  
        }
        
        public void actualizar(ComprobantePago modelo)
        {
            throw new NotImplementedException();
        }

        public void eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public void insertar(ComprobantePago modelo)
        {
            throw new NotImplementedException();
        }

        public List<ComprobantePago> listar()
        {
            return this.comprobantePagoDAO.leerTodos();
        }

        public ComprobantePago obtener(int id)
        {
            return this.comprobantePagoDAO.leer(id);
        }
    }
}
