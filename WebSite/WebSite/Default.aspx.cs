using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.Utils;

namespace NFCShoppingWebSite
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DirectoryCleaner.CleanDirectory(Server.MapPath("Images/Temp/"));
        }
    }
}
