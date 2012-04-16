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
    public partial class ProductDetails : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // Fill the page.
            Product product = GetProduct();
            CategoryBL categoryBL = new CategoryBL();
            Category category = categoryBL.GetCategory(product.SecCategory.categoryID);

            TitleLabel.Text = product.productName;
            ProductNameLable.Text = product.productName;
            ProductDescription.Text = product.description;
            SecCategoryLabel.Text = product.SecCategory.secCategoryName;
            CategoryLabel.Text = category.categoryName;
            Price.Text = decimal.Parse(product.price.ToString()).ToString("C");
            Location.Text = product.location;
            Brand.Text = product.brand;
            Barcode.Text = product.barCode;
            ProductImage.ImageUrl = VirtualPathUtility.ToAbsolute("~/Images/Products/" + product.imageURL);

            categoryBL.Dispose();
        }

        protected void DeleteButton_Click(object sender, EventArgs e)
        {
            Product product = GetProduct();
            ProductBL productBL = new ProductBL();

            try
            {
                // Delete the image.
                if (product.imageURL != "" && product.imageURL != null)
                {
                    File.Delete(Server.MapPath("~/Images/Products/") + product.imageURL);
                }
            }
            catch (Exception ex)
            {
                // TODO: Handle exception here.
            }

            // Delete the product itself.
            productBL.DeleteProduct(product);
            Response.Redirect("Products.aspx?secCategoryID=" + product.secCategoryID.ToString());

            productBL.Dispose();
        }

        protected void EditButton_Click(object sender, EventArgs e)
        {
            Product product = GetProduct();

            // Jump to the editing page.

            Response.Redirect("ProductEdit.aspx?isNew=false&productID=" + product.productID.ToString());
        }

        /**
         *  Get the product from the data source.
         *  @return The product from the data source.
         */
        protected Product GetProduct()
        {
            var enumerator = ProductsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Product)enumerator.Current;
        }

        protected void BarcodeTextBox_TextChanged(object sender, EventArgs e)
        {

        }
    }
}