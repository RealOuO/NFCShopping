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
    public partial class DiscountAdd : System.Web.UI.Page
    {
        private DiscountBL mDiscounts = new DiscountBL();
        
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            Discount discount = new Discount();

            discount.description = this.DiscountDescriptionTextBox.Text;
            discount.createdAt = Convert.ToDateTime(this.CreatedTextBox.Text);
            mDiscounts.InsertDiscount(discount);
            mDiscounts.Dispose();
            Response.Redirect("~/WebPages/Discounts.aspx");
        }
    }
}