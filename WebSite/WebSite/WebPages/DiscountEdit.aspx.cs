using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.BLL;

namespace NFCShoppingWebSite.WebPages
{
    public partial class DiscountEdit : System.Web.UI.Page
    {
        private DiscountBL mDiscounts = new DiscountBL();

        protected void Page_Load(object sender, EventArgs e)
        {           
            Discount discount = GetDiscount();
                       
            DiscountIDTextBox.Text = Convert.ToString(discount.discountID);
            DiscountDescriptionTextBox.Text = discount.description;
            CreatedTextBox.Text = Convert.ToString(discount.createdAt);

        }

        protected void DeleteButton_Click(object sender, EventArgs e)
        {
            Discount discount = GetDiscount();
            DiscountBL discountBL = new DiscountBL();

            discountBL.DeleteDiscount(discount);
            Response.Redirect("~/WebPages/Discounts.aspx");

            discountBL.Dispose();
        }

        protected void EditButton_Click(object sender, EventArgs e)
        {
            Discount discount = new Discount();

            discount.description = this.DiscountDescriptionTextBox.Text;
            discount.createdAt = Convert.ToDateTime(this.CreatedTextBox.Text);

            Discount origDiscount = GetDiscount();
            discount.discountID = origDiscount.discountID;
            mDiscounts.UpdateDiscount(discount, origDiscount);
            mDiscounts.Dispose();

            Response.Redirect("~/WebPages/Discounts.aspx");
        }

        protected Discount GetDiscount()
        {
            var enumerator = DiscountEditDataSource.Select().GetEnumerator();
            enumerator.MoveNext();
            return (Discount)enumerator.Current;
        }
    }
}