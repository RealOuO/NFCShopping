using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace NFCShoppingWebSite.WebPages
{
    public partial class UserDetails : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void UserDetailsView_DataBound(object sender, EventArgs e)
        {
            if (UserDetailsView.Rows[1].Cells[1].Text.Equals("1"))
            {
                UserDetailsView.Rows[1].Cells[1].Text = "先生";
            }
            else if (UserDetailsView.Rows[2].Cells[1].Text.Equals("2"))
            {
                UserDetailsView.Rows[1].Cells[1].Text = "女士";
            }
            else
            {
                UserDetailsView.Rows[1].Cells[1].Text = "保密";
            }
        }

    }
}