using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.IO;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.BLL;
using NFCShoppingWebSite.Utils;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.WebPages
{
    public partial class DiscountItemAdd : System.Web.UI.Page
    {
        private DiscountItemBL mDiscountItems = new DiscountItemBL();

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            DiscountItem discountItem = new DiscountItem();

          
            discountItem.discountPercent = Convert.ToSingle(this.DiscountPercentTextBox.Text);
            discountItem.description = this.DescriptionTextBox.Text;
            discountItem.discountID = Convert.ToInt32(this.DiscountDropDownList.SelectedValue);

            mDiscountItems.InsertDiscountItem(discountItem);
            mDiscountItems.Dispose();
            Response.Redirect("~/WebPages/DiscountItems.aspx");

        }
    }
}