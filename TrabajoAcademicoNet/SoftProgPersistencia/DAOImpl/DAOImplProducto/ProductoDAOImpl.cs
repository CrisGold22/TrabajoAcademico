using PUCP.Inf30.SoftProg.Modelo.PersonaModelo;
using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo;
using PUCP.Inf30.SoftProg.Modelo.ProductoModelo.ProductoUtils;
using PUCP.Inf30.SoftProg.Persistencia.DAO.DAOProducto;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl.DAOImplProducto
{
    public class ProductoDAOImpl :
        BaseDAOImpl<Producto>, IProductoDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, Producto modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "actualizarProducto";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_ID_Producto", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_Nombre", DbType.String, modelo.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_SKU", DbType.String, modelo.SKU);
            this.AgregarParametroEntrada(cmd, "@p_Descripcion", DbType.String, modelo.Descripcion);
            this.AgregarParametroEntrada(cmd, "@p_precioRegular", DbType.Double, modelo.PrecioUnitario);
            this.AgregarParametroEntrada(cmd, "@p_precioAlMayor", DbType.Double, modelo.PrecioAlMayor);
            this.AgregarParametroEntrada(cmd, "@p_UnidadDeMedida", DbType.String, modelo.MedidaAlMayor);
            this.AgregarParametroEntrada(cmd, "@p_StockDisponible", DbType.Int32, modelo.StockDisponible);
            this.AgregarParametroEntrada(cmd, "@p_StockMaximo", DbType.Int32, modelo.StockMaximo);
            this.AgregarParametroEntrada(cmd, "@p_StockMinimo", DbType.Int32, modelo.StockMinimo);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_CategoriaProducto_idCategoriaProducto", DbType.Int32, modelo.IdCategoria);

            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, Producto modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarProducto";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_ID_Producto", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_Nombre", DbType.String, modelo.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_SKU", DbType.String, modelo.SKU);
            this.AgregarParametroEntrada(cmd, "@p_Descripcion", DbType.String, modelo.Descripcion);
            this.AgregarParametroEntrada(cmd, "@p_precioRegular", DbType.Double, modelo.PrecioUnitario);
            this.AgregarParametroEntrada(cmd, "@p_precioAlMayor", DbType.Double, modelo.PrecioAlMayor);
            this.AgregarParametroEntrada(cmd, "@p_UnidadDeMedida", DbType.String, modelo.MedidaAlMayor);
            this.AgregarParametroEntrada(cmd, "@p_StockDisponible", DbType.Int32, modelo.StockDisponible);
            this.AgregarParametroEntrada(cmd, "@p_StockMaximo", DbType.Int32, modelo.StockMaximo);
            this.AgregarParametroEntrada(cmd, "@p_StockMinimo", DbType.Int32, modelo.StockMinimo);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
            this.AgregarParametroEntrada(cmd, "@p_CategoriaProducto_idCategoriaProducto", DbType.Int32, modelo.IdCategoria);

            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
             DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarProducto";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_ID_Producto", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarProductoPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_ID_Producto", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarProductos";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override Producto MapearModelo(DbDataReader reader)
        {
            return new Producto
            {
                Id = Convert.ToInt32(reader["ID_Producto"]),
                Nombre = Convert.ToString(reader["nombre"]),
                SKU = Convert.ToString(reader["SKU"]),
                PrecioAlMayor = Convert.ToDouble(reader["precioAlMayor"]),
                PrecioUnitario = Convert.ToDouble(reader["precioRegular"]),
                MedidaAlMayor = (UnidadMedida)Enum.Parse(typeof(UnidadMedida), Convert.ToString(reader["UnidadMedida"])),
                StockDisponible = Convert.ToInt32(reader["StockDisponible"]),
                StockMaximo = Convert.ToInt32(reader["StockMaximo"]),
                StockMinimo = Convert.ToInt32(reader["StockMinimo"]),
                Activo = Convert.ToBoolean(reader["activo"]),
                IdCategoria = Convert.ToInt32(reader["CategoriaProducto_idCategoriaProducto"])
            };
        }
    }
}
