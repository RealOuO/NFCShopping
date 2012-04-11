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

        private Boolean mIsNew;

        protected void Page_Load(object sender, EventArgs e)
        {
            String queryStr = Server.UrlDecode(ClientQueryString);
            var keyAndValuePairs = QueryStringDecoder.Decode(queryStr, new string[] { "&" });
            string result;

            // Add a variable to the ViewState indicating whether the controls need to be initialized or not.
            if (ViewState["NeedInit"] == null)
            {
                ViewState.Add("NeedInit", true);
            }
            else
            {
                ViewState["NeedInit"] = false;
            }

            if (keyAndValuePairs.TryGetValue("isNew", out result))
            {
                // Store the status indicating whether it's inserting or updating.
                ViewState.Add("IsNew", Convert.ToBoolean(result));
                mIsNew = Convert.ToBoolean(result);

                if ((bool)ViewState["NeedInit"])
                {
                    // Try to get the current discount information from the SessionState.
                    Discount discount = (Discount)Session["CurrentState"];

                    if (discount == null)
                    {
                        // Failed, get it from the database.
                        discount = GetDiscount();
                    }
                    else
                    {
                        // Succeeded, remove it from the SessionStete in case we will get old data next time.
                        Session.Remove("CurrentState");
                    }

                    if (mIsNew)
                    {
                        this.TitleLabel.Text = "新增优惠活动";
                    }
                    else
                    {
                        this.TitleLabel.Text = "编辑优惠活动";
                    }

                    if (discount != null)
                    {
                        this.DiscountDescriptionTextBox.Text = discount.description;
                        this.DiscountIDLabel.Text = discount.discountID.ToString();
                        this.StartDateTextBox.Text = discount.startDate.ToShortDateString();
                        this.EndDateTextBox.Text = discount.endDate.ToShortDateString();
                    }
                }
            }

            // Create 2 buffers for the discount items.
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

                // Try to fill it with the temporary buffers from the SessionState if they exist.
                if(Session["IsEditMode"] != null)
                {
                    source = (DataTable)Session["DataSource"];
                    filteredSource = (DataTable)Session["FilteredDataSource"];

                    // Remove the buffers in the SessionState.
                    Session.Remove("IsEditMode");
                    Session.Remove("DataSource");
                    Session.Remove("FilteredDataSource");
                }
                else if (!mIsNew) // Else fill them with the discount items of the current discount only under the editing mode.
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

            // Data binding.
            DataTable filteredData = (DataTable)ViewState["FilteredDataSource"];

            this.DiscountItemsGridView.DataSource = filteredData;
            this.DiscountItemsGridView.DataBind();
        }

        protected void OnOperateItem(Object sender, CommandEventArgs e)
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
            else if (e.CommandName == "EditItem")
            {
                // Mark the deleting row's status as "M" and jump to the item editing page.
                foreach (DataRow row in source.Rows)
                {
                    if ((string)row["id"] == (string)e.CommandArgument)
                    {
                        // Change its status to "M" only when this item is not a new inserted item.
                        if ((string)row["status"] != "I")
                        {
                            row["status"] = "M";
                        }

                        // Store the data in the ViewState into the SessionState temporarily.
                        Session.Add("DataSource", source);
                        Session.Add("FilteredDataSource", filteredSource);

                        // Add a variable in the SessionState to indicate the current mode.
                        Session.Add("IsEditMode", !mIsNew);

                        // Store the current editing state into the SessionState temporarily.
                        Discount discount = new Discount();

                        discount.discountID = Convert.ToInt32(DiscountIDLabel.Text);
                        discount.description = DiscountDescriptionTextBox.Text;
                        discount.startDate = Convert.ToDateTime(StartDateTextBox.Text);
                        discount.endDate = Convert.ToDateTime(EndDateTextBox.Text);

                        Session.Add("CurrentState", discount);

                        // Jump to the discount item editing page.
                        Response.Redirect("~/WebPages/DiscountItemEdit.aspx?isNew=false&discountItemID=" + (string)row["id"]);
                    }
                }
            }
            else if (e.CommandName == "InsertItem")
            {
                // Store the data in the ViewState into the SessionState temporarily.
                Session.Add("DataSource", source);
                Session.Add("FilteredDataSource", filteredSource);

                // Add a variable in the SessionState to indicate the current mode.
                Session.Add("IsEditMode", !mIsNew);

                // Store the current editing state into the SessionState temporarily.
                Discount discount = new Discount();

                if (!mIsNew)
                {
                    discount.discountID = Convert.ToInt32(DiscountIDLabel.Text);
                }
                else
                {
                    discount.discountID = -1;
                }

                discount.description = DiscountDescriptionTextBox.Text;
                discount.startDate = Convert.ToDateTime(StartDateTextBox.Text);
                discount.endDate = Convert.ToDateTime(EndDateTextBox.Text);

                Session.Add("CurrentState", discount);

                // Jump to the discount item editing page.
                Response.Redirect("~/WebPages/DiscountItemEdit.aspx?isNew=true");
            }

            // Data binding.
            DiscountItemsGridView.DataSource = filteredSource;
            DiscountItemsGridView.DataBind();
        }

        protected Discount GetDiscount()
        {
            if (mIsNew)
                return null;

            var enumerator = DiscountsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Discount)enumerator.Current;
        }

        protected void DiscountItemsGridView_Load(object sender, EventArgs e)
        {
            // Assign an unique id for each button.
            foreach (GridViewRow row in DiscountItemsGridView.Rows)
            {
                Button deleteButton = (Button)row.Cells[3].Controls[3];
                Button editButton = (Button)row.Cells[3].Controls[1];

                deleteButton.ID = "Delete" + row.RowIndex.ToString();
                editButton.ID = "Edit" + row.RowIndex.ToString();
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
            discount.description = DiscountDescriptionTextBox.Text;
            discount.startDate = Convert.ToDateTime(StartDateTextBox.Text);
            discount.endDate = Convert.ToDateTime(EndDateTextBox.Text);

            if (mIsNew)
            {
                mDiscounts.InsertDiscount(discount);
            }
            else
            {
                discount.discountID = origDiscount.discountID;
                mDiscounts.UpdateDiscount(discount, origDiscount);
            }

            // Then, the corresponding discount items.
            foreach (DataRow row in source.Rows)
            {
                DiscountItem item = new DiscountItem();
                DiscountItem origItem = discountItemBL.GetDiscountItem(Convert.ToInt32(row["id"]));

                if ((string)row["status"] == "D")
                {
                    // Delete this row.
                    discountItemBL.DeleteDiscountItem(origItem);
                }
                else if ((string)row["status"] == "M")
                {
                    // Update this row.
                    item.id = origItem.id;
                    item.productID = Convert.ToInt32(row["productID"]);
                    item.description = row["description"].ToString();
                    item.discountID = discount.discountID;
                    item.discountPercent = (float)Convert.ToDouble(row["discountPercent"]);

                    discountItemBL.UpdateDiscountItem(item, origItem);
                }
                else if ((string)row["status"] == "I")
                {
                    // Insert this row.
                    item.productID = Convert.ToInt32(row["productID"]);
                    item.description = row["description"].ToString();
                    item.discountID = discount.discountID;
                    item.discountPercent = (float)Convert.ToDouble(row["discountPercent"]);

                    discountItemBL.InsertDiscountItem(item);
                }
            }

            // Jump back to the discount list page.
            Response.Redirect("~/WebPages/Discounts.aspx");
        }
    }
}