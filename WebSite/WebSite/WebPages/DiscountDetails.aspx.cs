using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.BLL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.WebPages
{
    public partial class DiscountDetails : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected Discount GetDiscount()
        {
            var enumerator = this.DiscountsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Discount)enumerator.Current;
        }

        protected void EditButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/WebPages/DiscountEdit.aspx?isNew=false&discountID=" + GetDiscount().discountID);
        }
    }
}