using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class PerfilCliente : System.Web.UI.Page
    {
        ClienteWSClient clienteWS = new ClienteWSClient();  // Aquí usas tu WS real
        EmpresaWSClient empresaWS = new EmpresaWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosCliente();  // lo que ya tengas
                CargarEmpresas();
            }
        }

        private void CargarEmpresas()
        {
            int idCliente = Convert.ToInt32(Session["idCliente"]);

            // 1. Traer TODAS las empresas desde el WS
            List<empresa> todas = empresaWS.listarEmpresa().ToList();

            // 2. Filtrar solo las del cliente
            var empresasCliente = todas
                .Where(emp => emp.cliente != null && emp.cliente.id == idCliente)
                .ToList();

            gvEmpresas.DataSource = empresasCliente;
            gvEmpresas.DataBind();
        }


        private void CargarDatosCliente()
        {
            // 👇 Ejemplo: obtienes el ID desde sesión
            int idCliente = Convert.ToInt32(Session["idCliente"]);

            cliente cliente = clienteWS.obtenerCliente(idCliente);

            if (cliente != null)
            {
                txtNombre.Text = cliente.nombre;
                txtApellido.Text = cliente.apellidoPaterno + " " + cliente.apellidoMaterno;
                txtDNI.Text = cliente.dni;
                txtCorreo.Text = cliente.cuenta.correo;
                txtDireccion.Text = "Hola";

                lblTituloNombre.InnerText = cliente.nombre + " " + cliente.apellidoPaterno + " " + cliente.apellidoMaterno;
            }
        }

        protected void btnEditar_Click(object sender, EventArgs e)
        {
            ActivarEdicion(true);
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            cliente actualizado = new cliente()
            {
                id = Convert.ToInt32(Session["idCliente"]),
                nombre = txtNombre.Text,
                apellidoMaterno = txtApellido.Text,
                dni = txtDNI.Text,
                //Correo = txtCorreo.Text,
                //Direccion = txtDireccion.Text
            };

            clienteWS.actualizarCliente(actualizado);   // <-- Llamas a tu WS REST o SOAP

            ActivarEdicion(false);
            CargarDatosCliente(); // refrescar
        }

        private void ActivarEdicion(bool activar)
        {
            txtNombre.ReadOnly = !activar;
            txtApellido.ReadOnly = !activar;
            txtDNI.ReadOnly = !activar;
            txtCorreo.ReadOnly = !activar;
            txtDireccion.ReadOnly = !activar;

            btnEditar.Visible = !activar;
            btnGuardar.Visible = activar;
        }
    }
}