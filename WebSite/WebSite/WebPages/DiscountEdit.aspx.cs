using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.BLL;
using NFCShoppingWebSite.Utils;

namespace NFCShoppingWebSite.WebPages
{
    public partial class DiscountEdit : System.Web.UI.Page
    {
        private DiscountBL mDiscounts = new DiscountBL();

        protected void Page_Load(object sender, EventArgs e)
        {
            String queryStr = Server.UrlDecode(ClientQueryString);
            var keyAndValuePairs = QueryStringDecoder.Decode(queryStr, new string[] { "&" });
            string result;

            if (keyAndValuePairs.TryGetValue("isNew", out result))
            {
                // Store the status indicating whether it's inserting or updating.
                ViewState.Add("IsNew", Convert.ToBoolean(result));

                if (Convert.ToBoolean(result))
                {
                    this.TitleLabel.Text = "新增优惠活动";
                }
                else
                {
                    Discount discount = GetDiscount();

                    this.TitleLabel.Text = "编辑优惠活动";
                    this.DiscountDescriptionTextBox.Text = discount.description;

                    /*if(Session["DiscountItemCollection"] == null)
                    {
                      */  
                }
            }
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
        }

        protected Discount GetDiscount()
        {
            var enumerator = DiscountsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Discount)enumerator.Current;
        }
    }
}