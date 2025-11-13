using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.CuentaUsuarioWS;

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
            string email = txtEmail.Text.Trim();
            string password = txtPassword.Text.Trim();

            if (string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                Response.Write("<script>alert('Por favor complete todos los campos');</script>");
                return;
            }

            CuentaUsuarioWSClient cuentaWS = new CuentaUsuarioWSClient();
            bool loginValido = cuentaWS.login(email, password);

            if (loginValido)
            {
                // Obtener los datos completos del usuario
                var cuentas = cuentaWS.listarCuentaUsuario();
                var cuenta = cuentas.FirstOrDefault(c => c.username == email);

                if (cuenta != null)
                {
                    if (cuenta.cliente != null)
                    {
                        Session["IdCliente"] = cuenta.cliente.id;
                        Session["NombreCliente"] = cuenta.cliente.nombre + " " + cuenta.cliente.apellidoPaterno;

                        Response.Redirect("Index.aspx");
                    }
                    else if (cuenta.administrador != null)
                    {
                        Session["NombreAdmin"] = cuenta.administrador.nombre;
                        Response.Redirect("GestionarPedidos01.aspx");
                    }
                    else
                    {
                        Response.Write("<script>alert('No se pudo identificar el tipo de usuario');</script>");
                    }
                }
                else
                {
                    Response.Write("<script>alert('No se encontró la cuenta');</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('Credenciales incorrectas');</script>");
            }
        }
    }
}