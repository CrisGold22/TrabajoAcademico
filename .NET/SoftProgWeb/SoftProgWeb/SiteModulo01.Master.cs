using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{

    public partial class SiteModulo01 : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SetActiveLink();
        }

        private void SetActiveLink()
        {
            string currentPage = Path.GetFileName(Request.Url.AbsolutePath);

            switch (currentPage)
            {
                case "GestionarProductos01.aspx":
                    lnkProductos.Attributes["class"] += " active";
                    break;
                case "GestionarClientes01.aspx":
                    lnkClientes.Attributes["class"] += " active";
                    break;
                case "GestionarPedidos01.aspx":
                    lnkPedidos.Attributes["class"] += " active";
                    break;
                case "Default.aspx":
                    lnkProductos.Attributes["class"] += " active";
                    break;
            }
        }
    }
}