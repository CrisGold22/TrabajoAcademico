using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class InicioSesion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

            }
        }


        protected void btnSignIn_Click(object sender, EventArgs e)
        {
            //string email = txtEmail.Text.Trim();
            //string password = txtPassword.Text.Trim();

            string email = "admin1@example.com";
            string password = "admin123";

            //string email = "jperez@example.com";
            //string password = "pass123";

            if (string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                Response.Write("<script>alert('Por favor complete todos los campos');</script>");
                return;
            }

            CuentaUsuarioWSClient cuentaWS = new CuentaUsuarioWSClient();
            bool loginValido = cuentaWS.login(email, password);

            AdministradorSistemaWSClient administradorWS = new AdministradorSistemaWSClient();
            bool loginAdminValido = cuentaWS.login(email, password);

            if (!loginValido && !loginAdminValido)
            {
                Response.Write("<script>alert('Credenciales incorrectas');</script>");
                return;
            }

            ClienteWSClient clienteWS = new ClienteWSClient();
            var clientes = clienteWS.listarCliente();

            var cliente = clientes.FirstOrDefault(c => c.cuenta != null && c.cuenta.correo == email);

            if (cliente != null)
            {
                Session["IdCliente"] = cliente.id;
                Session["NombreCliente"] = cliente.nombre + " " + cliente.apellidoPaterno;
                Response.Redirect("Index.aspx");
                return;
            }


            var admins = administradorWS.listarTodosAdminstradores();
            var admin = admins.FirstOrDefault(a => a.cuenta != null && a.cuenta.correo == email);

            if (admin != null)
            {
                Session["IdAdministrador"] = admin.id;
                Session["NombreAdministrador"] = admin.nombre + " " + admin.apellidoPaterno;
                Response.Redirect("GestionarClientes01.aspx");
                return;
            }

            NotificacionWSClient notificacionWS = new NotificacionWSClient();



        }


    }
}