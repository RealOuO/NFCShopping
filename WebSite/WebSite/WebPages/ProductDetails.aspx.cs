using System;
using System.Collections.Generic;
using System.Linq;
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
            var enumerator = ProductsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            Product product = (Product)enumerator.Current;
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
    }
}