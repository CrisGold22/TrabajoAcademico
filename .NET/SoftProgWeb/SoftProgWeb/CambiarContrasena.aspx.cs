using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class CambiarContrasena : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            NotificacionWSClient notificacion = new NotificacionWSClient();



            notificacion.enviarMensajeGenerico("Prueba", "Este es un mensaje de prueba", "cristhianhoracio22@gmail.com");

        }


        protected void btnCambiar_Click(object sender, EventArgs e)
        {
            if (!Page.IsValid) return;

            string pwdActual = txtPasswordActual.Text.Trim();
            string pwdNueva = txtPasswordNueva.Text.Trim();

            //if(new CuentaUsuarioWSClient().)



            // 1. Obtener el usuario logueado desde Session (por ejemplo username o idCuenta)
            // 2. Llamar a tu WS de login/verificación para comprobar que pwdActual es correcta (BCrypt)
            // 3. Si es correcta, llamar a tu WS REST/SOAP para actualizar la contraseña con el nuevo hash
            // 4. Mostrar mensaje:
            lblMensaje.Text = "La contraseña se cambió correctamente.";
            lblMensaje.Visible = true;
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("PerfilCliente.aspx");
        }


    }
}