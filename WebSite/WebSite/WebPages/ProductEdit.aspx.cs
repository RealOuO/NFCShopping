using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.IO;
using System.Web.UI;
using System.Web.UI.WebControls;
using NFCShoppingWebSite.BLL;
using NFCShoppingWebSite.Utils;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.WebPages
{
    public partial class ProductEdit : System.Web.UI.Page
    {
        private ProductBL mProducts = new ProductBL();

        private String tempPath;

        private String imageSavePath;

        protected void Page_Load(object sender, EventArgs e)
        {
            tempPath = Server.MapPath("~/Images/Temp/");
            imageSavePath = Server.MapPath("~/Images/Products/");
        }

        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            Product product = new Product();
            
            product.productName = this.ProductNameTextBox.Text;
            product.price = Convert.ToDecimal(this.PriceTextBox.Text);
            product.location = this.LocationTextBox.Text;
            product.brand = this.BrandTextBox.Text;
            product.description = this.ProductDescriptionTextBox.Text;
            product.secCategoryID = Convert.ToInt32(this.SecCategoriesDropDownList.SelectedValue);
            product.barCode = this.BarcodeTextBox.Text;

            if (this.ProductImage.ImageUrl != null && this.ProductImage.ImageUrl != "")
            {
                try
                {
                    String fileName = ProductImage.ImageUrl.Substring(ProductImage.ImageUrl.LastIndexOf("/") + 1);
                    String tempFile = tempPath + fileName;
                    String newFile = imageSavePath + fileName;

                    if (File.Exists(tempFile))
                    {
                        int i = 1;

                        while (File.Exists(newFile))
                        {
                            fileName = i.ToString() + fileName;
                            newFile = imageSavePath + fileName;
                            ++i;
                        }

                        File.Copy(tempFile, newFile);
                    }

                    product.imageURL = fileName;
                }
                catch (Exception ex)
                {
                    // TODO: Handling exception.
                }
            }

            if(this.TitleLabel.Text == "增加新商品")
            {
                mProducts.InsertProduct(product); 
            }
            else
            {
                Product origProduct = GetProduct();

                if (File.Exists(imageSavePath + origProduct.imageURL))
                {
                    File.Delete(imageSavePath + origProduct.imageURL);
                }

                product.productID = origProduct.productID;
                mProducts.UpdateProduct(product, origProduct);
            }

            mProducts.Dispose();

            Response.Redirect("~/WebPages/Products.aspx?secCategoryID=" + product.secCategoryID.ToString());
        }

        protected void Page_PreRender(object sender, EventArgs e)
        {
            String queryStr = Server.UrlDecode(this.ClientQueryString);
            var keyAndValuePairs = QueryStringDecoder.Decode(queryStr, new string[] { "&" });
            string result;

            if (keyAndValuePairs.TryGetValue("isNew", out result))
            {
                if (Convert.ToBoolean(result))
                {
                    this.TitleLabel.Text = "增加新商品";
                }
                else
                {
                    Product product = GetProduct();

                    this.TitleLabel.Text = "编辑商品";
                    this.ProductNameTextBox.Text = product.productName;
                    this.ProductDescriptionTextBox.Text = product.description;
                    if (ProductImage.ImageUrl == null || ProductImage.ImageUrl == "")
                    {
                        this.ProductImage.ImageUrl = VirtualPathUtility.ToAbsolute("~/Images/Products/" + product.imageURL);
                    }
                    this.PriceTextBox.Text = product.price.ToString();
                    this.LocationTextBox.Text = product.location;
                    this.BrandTextBox.Text = product.brand;
                    this.BarcodeTextBox.Text = product.barCode;
                }
            }
        }

        protected Product GetProduct()
        {
            var enumerator = ProductsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Product)enumerator.Current;
        }

        protected void CategoriesDropDownList_PreRender(object sender, EventArgs e)
        {
            if (this.TitleLabel.Text == "编辑商品")
            {
                Product product = GetProduct();

                this.CategoriesDropDownList.SelectedValue = product.SecCategory.categoryID.ToString();
            }
        }

        protected void SecCategoriesDropDownList_PreRender(object sender, EventArgs e)
        {
            if (this.TitleLabel.Text == "编辑商品")
            {
                Product product = GetProduct();

                this.SecCategoriesDropDownList.SelectedValue = product.secCategoryID.ToString();
            }
        }

        protected void UploadPictureButton_Click(object sender, EventArgs e)
        {
            if (this.ProductPictureUpload.HasFile)
            {
                try
                {
                    ProductPictureUpload.SaveAs(tempPath + ProductPictureUpload.FileName);
                    ProductImage.ImageUrl = VirtualPathUtility.ToAbsolute("~/Images/Temp/" + ProductPictureUpload.FileName);
                }
                catch (Exception ex)
                {
                    // TODO: Handle exception here.
                }
            }
        }
    }
}