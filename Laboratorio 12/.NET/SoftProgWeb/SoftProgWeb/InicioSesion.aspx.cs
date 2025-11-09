using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

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

            // Validar credenciales base de datos
            // Si es correcto:
            hdnUsuarioNombre.Value = "Juan Perez"; // Nombre
            ScriptManager.RegisterStartupScript(this, GetType(),
                "showWelcome",
                $"mostrarBienvenida('{hdnUsuarioNombre.Value}');",
                true);
        }
    }
}