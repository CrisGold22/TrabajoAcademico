using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Persistencia.DAOImpl
{
    public abstract class TransaccionalBaseDAO<T> :BaseDAOImpl<T>, 
        IPersistibleTransaccional<T,int>
    {
        protected virtual DbCommand ComandoCrear(DbTransaction transaccion, T modelo)
        {
            DbConnection conn = transaccion.Connection;
            DbCommand cmd = this.ComandoCrear(conn, modelo);
            cmd.Transaction = transaccion;
            return cmd;
        }

        protected virtual DbCommand ComandoActualizar(DbTransaction transaccion, T modelo)
        {
            DbConnection conn = transaccion.Connection;
            DbCommand cmd = this.ComandoActualizar(conn, modelo);
            cmd.Transaction = transaccion;
            return cmd;
        }

        protected virtual DbCommand ComandoEliminar(DbTransaction transaccion, int id)
        {
            DbConnection conn = transaccion.Connection;
            DbCommand cmd = this.ComandoEliminar(conn, id);
            cmd.Transaction = transaccion;
            return cmd;
        }

        public bool Actualizar(T modelo, DbTransaction transaccion)
        {
            return EjecutarComandoActualizar(transaccion.Connection, modelo);
        }

        public int Crear(T modelo, DbTransaction transaccion)
        {
            return EjecutarComandoCrear(transaccion.Connection, modelo);
        }

        public bool Eliminar(int id, DbTransaction transaccion)
        {
            return EjecutarComandoEliminar(transaccion.Connection, id);
        }

        public int crear(T modelo, DbTransaction transaccion)
        {
            throw new NotImplementedException();
        }

        public bool actualizar(T modelo, DbTransaction transaccion)
        {
            throw new NotImplementedException();
        }

        public bool eliminar(int id, DbTransaction transaccion)
        {
            throw new NotImplementedException();
        }
    }
}
