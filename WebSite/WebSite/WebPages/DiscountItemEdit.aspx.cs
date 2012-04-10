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
    public partial class DiscountItemEdit : System.Web.UI.Page
    {
        private DiscountItemBL mDiscountItems = new DiscountItemBL(); 

        protected void Page_Load(object sender, EventArgs e)
        {
            DiscountItem discountItem = GetDiscountItem();

            //Label1.Text = Convert.ToString(discountItem.discountID);
            //Label2.Text = Convert.ToString(discountItem.productID);
            //DiscountPercentTextBox.Text = Convert.ToString(discountItem.discountPercent);
            //DiscountItemDescriptionTextBox.Text = discountItem.description;
            //StartDateTextBox.Text = Convert.ToString(discountItem.startDate);
            //EndDateTextBox.Text = Convert.ToString(discountItem.endDate);

        }

        protected DiscountItem GetDiscountItem()
        {
            var enumerator = DiscountItemEditDataSource.Select().GetEnumerator();
            enumerator.MoveNext();
            return (DiscountItem)enumerator.Current;
        }
        
        protected void EditButton_Click(object sender, EventArgs e)
        {
            DiscountItem discountItem = new DiscountItem();

            discountItem.discountPercent = Convert.ToSingle(this.DiscountPercentTextBox.Text);
            discountItem.description = this.DiscountItemDescriptionTextBox.Text;
            
            DiscountItem origDiscountItem = GetDiscountItem();
            discountItem.id = origDiscountItem.id;
            mDiscountItems.UpdateDiscountItem(discountItem, origDiscountItem);
            mDiscountItems.Dispose();

            Response.Redirect("~/WebPages/DiscountItems.aspx");
        }

        protected void DeleteButton_Click(object sender, EventArgs e)
        {
            DiscountItem discountItem = GetDiscountItem();
            DiscountItemBL discountItemBL = new DiscountItemBL();
            
            discountItemBL.DeleteDiscountItem(discountItem);
            Response.Redirect("~/WebPages/DiscountItems.aspx");

            discountItemBL.Dispose();
        }
    }
}