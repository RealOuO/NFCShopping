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
            Product product = GetProduct();
            CategoryBL categoryBL = new CategoryBL();
            Category category = categoryBL.GetCategory(product.SecCategory.categoryID);

            TitleLabel.Text = product.productName;
            ProductNameTextBox.Text = product.productName;
            ProductDescriptionTextBox.Text = product.description;
            SecCategoryLabel.Text = product.SecCategory.secCategoryName;
            CategoryLabel.Text = category.categoryName;
            PriceTextBox.Text = product.price.ToString("{0:c}");
            LocationTextBox.Text = product.location;
            BrandTextBox.Text = product.brand;
            BarcodeTextBox.Text = product.barCode;
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

            Response.Redirect("ProductEdit.aspx?isNew=false&productID=" + product.productID.ToString());
        }

        protected Product GetProduct()
        {
            var enumerator = ProductsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Product)enumerator.Current;
        }
    }
}