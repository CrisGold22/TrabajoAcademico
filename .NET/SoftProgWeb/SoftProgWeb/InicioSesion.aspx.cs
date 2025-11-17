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
            string username = txtEmail.Text.Trim();
            string password = txtPassword.Text.Trim();

            if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(password))
            {
                Response.Write("<script>alert('Por favor complete todos los campos');</script>");
                return;
            }

            CuentaUsuarioWSClient cuentaWS = new CuentaUsuarioWSClient();
            bool loginValido = cuentaWS.login(username, password);

            if (!loginValido)
            {
                Response.Write("<script>alert('Credenciales incorrectas');</script>");
                return;
            }

            ClienteWSClient clienteWS = new ClienteWSClient();
            var clientes = clienteWS.listarCliente();

            var cliente = clientes.FirstOrDefault(c => c.cuenta != null && c.cuenta.username == username);

            if (cliente != null)
            {
                Session["IdCliente"] = cliente.id;
                Session["NombreCliente"] = cliente.nombre + " " + cliente.apellidoPaterno;
                Response.Redirect("Index.aspx");
                return;
            }
        }
    }
}