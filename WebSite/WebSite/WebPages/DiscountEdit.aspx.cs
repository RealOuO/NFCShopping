using System;
using System.Collections.Generic;
using System.Collections;
using System.Collections.Specialized;
using System.Linq;
using System.IO;
using System.Data;
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

        private bool mIsNew;

        protected void Page_Load(object sender, EventArgs e)
        {
            String queryStr = Server.UrlDecode(ClientQueryString);
            var keyAndValuePairs = QueryStringDecoder.Decode(queryStr, new string[] { "&" });
            string result;

            if (keyAndValuePairs.TryGetValue("isNew", out result))
            {
                // Store the status indicating whether it's inserting or updating.
                ViewState.Add("IsNew", Convert.ToBoolean(result));
                mIsNew = Convert.ToBoolean(result);

                if (mIsNew)
                {
                    this.TitleLabel.Text = "新增优惠活动";
                }
                else
                {
                    Discount discount = GetDiscount();

                    this.TitleLabel.Text = "编辑优惠活动";
                    this.DiscountDescriptionTextBox.Text = discount.description;
                    this.DiscountIDLabel.Text = discount.discountID.ToString();

                    /*if(Session["DiscountItemCollection"] == null)
                    {
                      */  
                }
            }

            // Create a buffer for the discount items.
            if (ViewState["DataSource"] == null)
            {
                DataTable source = new DataTable();           // Track all the data.
                DataTable filteredSource= new DataTable();    // For the gridview to display(Filtered all the deleting data).

                // Indicate whether a specific row's status. "NC" for no changes, "I" for inserted, "M" for modified and "D" for deleted.
                source.Columns.Add("status");
                source.Columns.Add("productID");
                source.Columns.Add("productName");
                source.Columns.Add("description");
                source.Columns.Add("discountPercent");
                source.Columns.Add("id");

                filteredSource.Columns.Add("status");
                filteredSource.Columns.Add("productID");
                filteredSource.Columns.Add("productName");
                filteredSource.Columns.Add("description");
                filteredSource.Columns.Add("discountPercent");
                filteredSource.Columns.Add("id");   

                // Fill it with the discount items of the current discount and 
                // bind the data from the data buffer only under the editing mode.
                if (!mIsNew)
                {
                    // For each discount item, construct two rows based on it. One for the source while the other one for the filtered source.
                    foreach (var discountItem in GetDiscount().DiscountItems)
                    {
                        DataRow row = source.NewRow();

                        row["status"] = "NC";
                        row["productID"] = discountItem.productID;
                        row["productName"] = discountItem.Product.productName;
                        row["description"] = discountItem.description;
                        row["discountPercent"] = discountItem.discountPercent;
                        row["id"] = discountItem.id;

                        DataRow row2 = filteredSource.NewRow();
                        row2["status"] = "NC";
                        row2["productID"] = discountItem.productID;
                        row2["productName"] = discountItem.Product.productName;
                        row2["description"] = discountItem.description;
                        row2["discountPercent"] = discountItem.discountPercent;
                        row2["id"] = discountItem.id;

                        source.Rows.Add(row);
                        filteredSource.Rows.Add(row2);
                    }
                }

                ViewState.Add("DataSource", source);
                ViewState.Add("FilteredDataSource", filteredSource);
            }

            DataTable filteredData = (DataTable)ViewState["FilteredDataSource"];

            // Skip all the deleting rows.

            this.DiscountItemsGridView.DataSource = filteredData;
            this.DiscountItemsGridView.DataBind();
        }

        protected void DeleteButton_Click(object sender, EventArgs e)
        {
            Discount discount = GetDiscount();
            DiscountBL discountBL = new DiscountBL();

            discountBL.DeleteDiscount(discount);
            Response.Redirect("~/WebPages/Discounts.aspx");

            discountBL.Dispose();
        }

        protected void OnDeleteItem(Object sender, CommandEventArgs e)
        {
            DataTable source = (DataTable)ViewState["DataSource"];
            DataTable filteredSource = (DataTable)ViewState["FilteredDataSource"];

            if(e.CommandName == "DeleteItem")
            {
                // Mark the deleting row's status as "D" and remove this row from the filtered source.
                foreach (DataRow row in source.Rows)
                {
                    if ((string)row["id"] == (string)e.CommandArgument)
                    {
                        row["status"] = "D";
                        foreach (DataRow tempRow in filteredSource.Rows)
                        {
                            if ((string)tempRow["id"] == (string)e.CommandArgument)
                            {
                                filteredSource.Rows.Remove(tempRow);

                                break;
                            }
                        }

                        break;
                    }
                }
            }

            // Data binding.
            DiscountItemsGridView.DataSource = filteredSource;
            DiscountItemsGridView.DataBind();
        }

        protected Discount GetDiscount()
        {
            var enumerator = DiscountsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Discount)enumerator.Current;
        }

        protected void DiscountItemsGridView_Load(object sender, EventArgs e)
        {
            // Assign an unique id for each button.
            foreach (GridViewRow row in DiscountItemsGridView.Rows)
            {
                Button deleteButton = (Button)row.Cells[3].Controls[1];

                deleteButton.ID = "Delete" + row.RowIndex.ToString();
            }
        }

        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            // Submit all the changes.
            Discount origDiscount = GetDiscount();
            Discount discount = new Discount();
            DiscountItemBL discountItemBL = new DiscountItemBL();
            DataTable source = (DataTable)ViewState["DataSource"];

            // First, the discount.
            discount.discountID = origDiscount.discountID;
            discount.description = DiscountDescriptionTextBox.Text;
            
            mDiscounts.UpdateDiscount(discount, 

            foreach (DataRow row in source.Rows)
            {
                DiscountItem item = discountItemBL.GetDiscountItem(Convert.ToInt32(row["id"]));

                if (row["status"] == "D")
                {
                    // Delete this row.
                    discount.DiscountItems.Remove(item);
                }
                else if(row["status"] == "M")
                {
                    // Update this row.
                    discount.DiscountItems.Single<DiscountItem>(
            }
        }
    }
}