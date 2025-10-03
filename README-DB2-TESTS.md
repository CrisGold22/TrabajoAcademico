
## Cómo ejecutar las pruebas de procedimientos (DB2)

1. Asegúrate de tener la base de datos DB2 corriendo y el esquema `DBRESCOM` con **todas las tablas** y **procedures** creados.
2. Exporta variables de entorno para la conexión (según tu Db2DBManager):
   ```bash
   export DB_HOST=localhost
   export DB_PORT=50000
   export DB_USER=db2inst1
   export DB_PASS=tu_password
   export DB_SCHEMA=DBRESCOM
   ```
3. Desde la raíz del proyecto:
   ```bash
   mvn -pl SoftProgPruebas -am -DskipTests=false test
   ```
   Esto ejecutará `ProceduresIT` contra DB2 y validará `insertarCategoriaProducto`, `insertarProducto`, `buscarProductoPorId`, `listarProductos` y `eliminarProducto`.
