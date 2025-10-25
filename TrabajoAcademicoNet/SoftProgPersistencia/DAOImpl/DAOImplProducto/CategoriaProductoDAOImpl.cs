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
    public class CategoriaProductoDAOImpl :
        BaseDAOImpl<CategoriaProducto>, ICategoriaProductoDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, CategoriaProducto modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "modificarCategoriaProducto";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCategoriaProducto", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_NombreCategoria", DbType.String, modelo.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_Descripcion", DbType.String, modelo.Descripcion);
            this.AgregarParametroEntrada(cmd, "@p_Catalogo_idCatalogo", DbType.Int32, modelo.IdCategoriaPadre);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
            return cmd;
        }

        protected override DbCommand ComandoCrear(DbConnection conn, CategoriaProducto modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "insertarCategoriaProducto";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCategoriaProducto", DbType.Int32, modelo.Id);
            this.AgregarParametroEntrada(cmd, "@p_NombreCategoria", DbType.String, modelo.Nombre);
            this.AgregarParametroEntrada(cmd, "@p_Descripcion", DbType.String, modelo.Descripcion);
            this.AgregarParametroEntrada(cmd, "@p_Catalogo_idCatalogo", DbType.Int32, modelo.IdCategoriaPadre);
            this.AgregarParametroEntrada(cmd, "@p_Activo", DbType.Boolean, modelo.Activo);
            return cmd;
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "eliminarCategoriaProducto";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCategoriaProducto", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "buscarCategoriaProductoPorId";
            cmd.CommandType = CommandType.StoredProcedure;

            this.AgregarParametroEntrada(cmd, "@p_idCategoriaProducto", DbType.Int32, id);

            return cmd;
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = "listarCategoriaProductos";
            cmd.CommandType = CommandType.StoredProcedure;
            return cmd;
        }

        protected override CategoriaProducto MapearModelo(DbDataReader reader)
        {
            return new CategoriaProducto
            {
                Id = Convert.ToInt32(reader["idCategoriaProducto"]),
                Nombre = (TipoCategoria)Enum.Parse(typeof(TipoCategoria), Convert.ToString(reader["NombreCategoria"])),
                Descripcion = Convert.ToString(reader["Descripcion"]),
                Activo = Convert.ToBoolean(reader["activo"]),
                IdCategoriaPadre = Convert.ToInt32(reader["Catalogo_idCatalogo"])
            };
        }
    }
}
