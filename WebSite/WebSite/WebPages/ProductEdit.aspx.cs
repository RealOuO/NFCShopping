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
    public partial class ProductEdit : System.Web.UI.Page
    {
        private ProductBL mProducts = new ProductBL();

        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            Product product = new Product();
            String imageSavePath = Server.MapPath("~/Images/Products/");
            
            product.productName = this.ProductNameTextBox.Text;
            product.price = Convert.ToDecimal(this.PriceTextBox.Text);
            product.location = this.LocationTextBox.Text;
            product.brand = this.BrandTextBox.Text;
            product.description = this.ProductDescriptionTextBox.Text;
            product.secCategoryID = Convert.ToInt32(this.SecCategoriesDropDownList.SelectedValue);
            product.barCode = this.BarcodeTextBox.Text;

            if (this.ProductPictureUpload.HasFile)
            {
                try
                {
                    this.ProductPictureUpload.SaveAs(imageSavePath + ProductPictureUpload.FileName);
                    product.imageURL = this.ProductPictureUpload.FileName;
                }
                catch (Exception ex)
                {
                    // Handling exception.
                }
            }

            mProducts.InsertProduct(product);
            mProducts.Dispose();
        }
    }
}