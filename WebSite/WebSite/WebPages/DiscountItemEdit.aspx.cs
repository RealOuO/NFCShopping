using System;
using System.Collections.Generic;
using System.Linq;
using System.Data;
using System.IO;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.BLL;
using NFCShoppingWebSite.Utils;

namespace NFCShoppingWebSite.WebPages
{
    public partial class DiscountItemEdit : System.Web.UI.Page
    {
        private DiscountItemBL mDiscountItems = new DiscountItemBL();
        private DiscountBL mDiscounts = new DiscountBL();
        private ProductBL mProducts = new ProductBL();
        private bool mIsNew;
        private bool mIsStored;                      // Whether this item has been stored in the database.

        protected void Page_Load(object sender, EventArgs e)
        {
            // Add a variable to the ViewState indicating whether the controls need to be initialized or not.
            if (ViewState["NeedInit"] == null)
            {
                ViewState.Add("NeedInit", true);
            }
            else
            {
                ViewState["NeedInit"] = false;
            }

            // Store the data from the SessionState into the ViewState if there is any.
            if (Session["IsEditMode"] != null)
            {
                ViewState.Add("IsEditMode", Session["IsEditMode"]);
                ViewState.Add("DataSource", Session["DataSource"]);
                ViewState.Add("FilteredDataSource", Session["FilteredDataSource"]);
                ViewState.Add("CurrentState", Session["CurrentState"]);

                // Remove them from the SessionState.
                Session.Remove("IsEditMode");
                Session.Remove("DataSource");
                Session.Remove("FilteredDataSource");
                Session.Remove("CurrentState");
            }

            // Initialize all the controls.
            String queryStr = Server.UrlDecode(ClientQueryString);
            var keyAndValuePairs = QueryStringDecoder.Decode(queryStr, new string[] { "&" });
            string result;

            if (keyAndValuePairs.TryGetValue("discountItemID", out result))
            {
                // Only when the id for this item is valid(id != -1), it's stored in the database.
                if (Convert.ToInt32(result) == -1)
                {
                    mIsStored = false;
                }
                else
                {
                    mIsStored = true;
                }
            }

            if (keyAndValuePairs.TryGetValue("isNew", out result))
            {
                // Store the status indicating whether it's inserting or updating.
                mIsNew = Convert.ToBoolean(result);

                if ((bool)ViewState["NeedInit"])
                {
                    if (mIsNew)
                    {
                        this.TitleLabel.Text = "新增优惠商品";

                        // Set the selected product if there is a product id in the query string.
                        if (keyAndValuePairs.TryGetValue("productID", out result))
                        {
                            Product product = mProducts.GetProduct(Convert.ToInt32(result));

                            this.ProductsDropDownList.DataSource = mProducts.GetProductsByName(product.productName);
                            this.ProductsDropDownList.DataBind();
                            this.ProductsDropDownList.SelectedValue = product.productID.ToString();

                            this.ProductNameTextBox.Text = product.productName;
                        }
                    }
                    else
                    {
                        DataTable filteredSource = (DataTable)ViewState["FilteredDataSource"];

                        this.TitleLabel.Text = "编辑优惠商品";

                        if (keyAndValuePairs.TryGetValue("discountItemID", out result))
                        {
                            foreach (DataRow item in filteredSource.Rows)
                            {
                                if ((string)item["id"] == result)
                                {
                                    Discount discount = (Discount)ViewState["CurrentState"];

                                    this.ProductNameTextBox.Text = item["productName"].ToString();
                                    this.DiscountPercentTextBox.Text = item["discountPercent"].ToString();
                                    this.DiscountItemDescriptionTextBox.Text = item["description"].ToString();

                                    this.ProductsDropDownList.DataSource = mProducts.GetProductsByName(item["productName"].ToString());
                                    this.ProductsDropDownList.DataBind();
                                    this.ProductsDropDownList.SelectedValue = item["productID"].ToString();

                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        protected DiscountItem GetDiscountItem()
        {
            if (mIsNew)
                return null;

            var enumerator = DiscountItemsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (DiscountItem)enumerator.Current;
        }
        
        protected void SearchButton_Click(object sender, EventArgs e)
        {
            this.ProductsDropDownList.DataSource = mProducts.GetProductsByName(this.ProductNameTextBox.Text);
            this.ProductsDropDownList.DataBind();
        }

        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            // Try to retrive data from the ViewState.
            DataTable source = (DataTable)ViewState["DataSource"];
            DataTable filteredSource = (DataTable)ViewState["FilteredDataSource"];
            bool isEditMode;
            Discount discount = (Discount)ViewState["CurrentState"];

            // If there is no data in the ViewState, allocate space for the buffers.
            // This happens when you click the "设置优惠" button in the product list page to set the discount item.
            if (source == null)
            {
                source = new DataTable();
                filteredSource = new DataTable();
                int discountID = Convert.ToInt32(DiscountsDropDownList.SelectedValue);

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

                discount = mDiscounts.GetDiscountByID(discountID);

                // Check the target discount, -1 for new one and otherwise, existing one.
                if (discountID == -1)
                {
                    isEditMode = false;
                }
                else
                {
                    foreach (DiscountItem item in discount.DiscountItems)
                    {
                        DataRow row1, row2;

                        row1 = source.NewRow();
                        row1["status"] = "NC";
                        row1["productID"] = item.productID;
                        row1["productName"] = item.Product.productName;
                        row1["description"] = item.description;
                        row1["discountPercent"] = item.discountPercent;
                        row1["id"] = item.id;

                        row2 = filteredSource.NewRow();
                        row2["status"] = "NC";
                        row2["productID"] = item.productID;
                        row2["productName"] = item.Product.productName;
                        row2["description"] = item.description;
                        row2["discountPercent"] = item.discountPercent;
                        row2["id"] = item.id;

                        source.Rows.Add(row1);
                        filteredSource.Rows.Add(row2);
                    }

                    isEditMode = true;
                }
            }
            else
            {
                isEditMode = (bool)ViewState["IsEditMode"];
            }

            if (mIsNew)
            {
                // Inserting a new item.
                DataRow item = source.NewRow();

                item["description"] = DiscountItemDescriptionTextBox.Text;
                item["status"] = "I";
                item["discountPercent"] = DiscountPercentTextBox.Text;
                item["productID"] = ProductsDropDownList.SelectedValue;
                item["id"] = "-1";
                item["productName"] = mProducts.GetProduct(Convert.ToInt32(ProductsDropDownList.SelectedValue)).productName;

                source.Rows.Add(item);

                DataRow item2 = filteredSource.NewRow();

                item2["description"] = DiscountItemDescriptionTextBox.Text;
                item2["status"] = "I";
                item2["discountPercent"] = DiscountPercentTextBox.Text;
                item2["productID"] = ProductsDropDownList.SelectedValue;
                item2["id"] = "-1";
                item2["productName"] = mProducts.GetProduct(Convert.ToInt32(ProductsDropDownList.SelectedValue)).productName;

                filteredSource.Rows.Add(item2);
            }
            else
            {
                // Updating an item.
                string id;

                if (mIsStored)
                {
                    // Call this method only when this item exists in the database.
                    id = GetDiscountItem().id.ToString();
                }
                else
                {
                    id = "-1";
                }

                foreach (DataRow row in source.Rows)
                {
                    if ((string)row["id"] == id)
                    {
                        row["description"] = DiscountItemDescriptionTextBox.Text;
                        row["discountPercent"] = DiscountPercentTextBox.Text;
                        row["productID"] = ProductsDropDownList.SelectedValue;
                        row["productName"] = mProducts.GetProduct(Convert.ToInt32(ProductsDropDownList.SelectedValue)).productName;

                        break;
                    }
                }

                foreach (DataRow row in filteredSource.Rows)
                {
                    if ((string)row["id"] == id)
                    {
                        row["description"] = DiscountItemDescriptionTextBox.Text;
                        row["discountPercent"] = DiscountPercentTextBox.Text;
                        row["productID"] = ProductsDropDownList.SelectedValue;
                        row["productName"] = mProducts.GetProduct(Convert.ToInt32(ProductsDropDownList.SelectedValue)).productName;

                        break;
                    }
                }
            }

            // Store the data in the ViewState into the SessionState temporarily.
            Session.Add("DataSource", source);
            Session.Add("FilteredDataSource", filteredSource);
            Session.Add("IsEditMode", isEditMode);
            Session.Add("CurrentState", discount);
            Response.Write("<script type='text/javascript'>alert('成功');window.location ='DiscountEdit.aspx?isNew="+ (!isEditMode).ToString() +"&discountID="+ DiscountsDropDownList.SelectedValue+"';</script>");
          
        }

        protected void CancelButton_Click(object sender, EventArgs e)
        {
            // Try to retrive data from the ViewState.
            DataTable source = (DataTable)ViewState["DataSource"];
            DataTable filteredSource = (DataTable)ViewState["FilteredDataSource"];
            Boolean isEditMode = (Boolean)ViewState["IsEditMode"];
            Discount discount = (Discount)ViewState["CurrentState"];

            // No data? Return to the category list page.
            if (source == null)
            {
                Response.Redirect("~/WebPages/Categories.aspx");
            }
            else
            {
                // Store the data in the ViewState into the SessionState temporarily.
                Session.Add("DataSource", source);
                Session.Add("FilteredDataSource", filteredSource);
                Session.Add("IsEditMode", isEditMode);
                Session.Add("CurrentState", discount);

                Response.Redirect("~/WebPages/DiscountEdit.aspx?isNew=" + (!isEditMode).ToString() + "&discountID=" +
                   DiscountsDropDownList.SelectedValue);
            }
        }

        protected void DiscountsDropDownList_DataBound(object sender, EventArgs e)
        {
            if ((bool)ViewState["NeedInit"])
            {
                // Add a default value to the discount drop-down-list.
                this.DiscountsDropDownList.Items.Add(new ListItem("新优惠", "-1"));

                // Set the selected discount if there is one stored in the ViewState.
                Discount discount = (Discount)ViewState["CurrentState"];

                if (discount != null)
                {
                    this.DiscountsDropDownList.SelectedValue = discount.discountID.ToString();
                }
                else
                {
                    this.DiscountsDropDownList.SelectedValue = "-1";
                }
            }
        }
    }
}