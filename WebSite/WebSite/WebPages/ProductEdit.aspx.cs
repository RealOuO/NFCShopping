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

        private const string DEFAULT_IMAGE = "SampleProductImage.gif";
        private bool mIsNew;

        /**
         *  The directory path to store temporary images.
         */
        private String tempPath;

        /**
         *  The directory path to store persistence images.
         */
        private String imageSavePath;

        protected void Page_Load(object sender, EventArgs e)
        {
            tempPath = Server.MapPath("~\\Images\\Temp\\");
            imageSavePath = Server.MapPath("~\\Images\\Products\\");
        }

        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            // The new product to insert.
            Product product = new Product();
            
            product.productName = this.ProductNameTextBox.Text;
            product.price = Convert.ToDecimal(this.PriceTextBox.Text);
            product.location = this.LocationTextBox.Text;
            product.brand = this.BrandTextBox.Text;
            product.description = this.ProductDescriptionTextBox.Text;
            product.secCategoryID = Convert.ToInt32(this.SecCategoriesDropDownList.SelectedValue);
            product.barCode = this.BarcodeTextBox.Text;

            // Set the image url and copy the cooresponding image from temporary directory to persistence directory
            // only when the user has uploaded an image.
            if (this.ProductImage.ImageUrl != null && this.ProductImage.ImageUrl != "")
            {
                try
                {
                    // Get the file name for the image.
                    String fileName = ProductImage.ImageUrl.Substring(ProductImage.ImageUrl.LastIndexOf("/") + 1);
                    String tempFile = tempPath + fileName;

                    if (File.Exists(tempFile))
                    {
                        File.Copy(tempFile, imageSavePath + fileName);
                    }

                    product.imageURL = fileName;
                }
                catch (Exception ex)
                {
                    // TODO: Handling exception.
                    product.imageURL = DEFAULT_IMAGE;
                }
            }
            else
            {
                product.imageURL = DEFAULT_IMAGE;
            }
            
            // For insertion.
            if((bool)ViewState["IsNew"])
            {
                mProducts.InsertProduct(product); 
            }
            else // For updating.
            {
                Product origProduct = GetProduct();

                if (File.Exists(imageSavePath + origProduct.imageURL))
                {
                    File.Delete(imageSavePath + origProduct.imageURL);
                }

                product.productID = origProduct.productID;
                mProducts.UpdateProduct(product, origProduct);
            }
            Response.Write("<script type='text/javascript'>alert('成功');window.location ='Categories.aspx';</script>");
            mProducts.Dispose();

            // Jump to the product list which the edited product is in.
          
      
        }

        protected void Page_PreRender(object sender, EventArgs e)
        {
            String queryStr = Server.UrlDecode(this.ClientQueryString);
            var keyAndValuePairs = QueryStringDecoder.Decode(queryStr, new string[] { "&" });
            string result;

            if (keyAndValuePairs.TryGetValue("isNew", out result))
            {
                // Store the status indicating whether it's inserting or updating.
                ViewState.Add("IsNew", Convert.ToBoolean(result));
                mIsNew = Convert.ToBoolean(result);

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

                    // Fetch the image only when the image url is valid.
                    if (ProductImage.ImageUrl == null || ProductImage.ImageUrl == "")
                    {
                        this.ProductImage.ImageUrl = VirtualPathUtility.ToAbsolute("~\\Images\\Products\\" + product.imageURL);
                    }

                    this.PriceTextBox.Text = product.price.ToString();
                    this.LocationTextBox.Text = product.location;
                    this.BrandTextBox.Text = product.brand;
                    this.BarcodeTextBox.Text = product.barCode;
                }
            }
        }

        /**
         *  Get the product from the data source.
         *  @return The product from the data source.
         */
        protected Product GetProduct()
        {
            if (mIsNew)
                return null;

            var enumerator = ProductsDataSource.Select().GetEnumerator();
            enumerator.MoveNext();

            return (Product)enumerator.Current;
        }

        protected void CategoriesDropDownList_PreRender(object sender, EventArgs e)
        {
            // Only when it's updating a product, set the selected category.
            if (!(bool)ViewState["IsNew"])
            {
                Product product = GetProduct();

                this.CategoriesDropDownList.SelectedValue = product.SecCategory.categoryID.ToString();
            }
        }

        protected void SecCategoriesDropDownList_PreRender(object sender, EventArgs e)
        {
            // Only when it's updating a product, set the selected secondary category.
            if (!(bool)ViewState["IsNew"])
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
                    List<string> tempImages = (List<string>)Session["TempImages"];

                    // Get the file extension.
                    string extension = ProductPictureUpload.FileName.Substring(ProductPictureUpload.FileName.LastIndexOf("."));

                    // Generate an unique id for the file.
                    string fileName = Guid.NewGuid().ToString() + extension;

                    ProductPictureUpload.SaveAs(tempPath + fileName);
                    ProductImage.ImageUrl = VirtualPathUtility.ToAbsolute("~\\Images\\Temp\\" + fileName);

                    // Add the temporary file to a field in the session for future deletion.
                    tempImages.Add(tempPath + fileName);
                }
                catch (Exception ex)
                {
                    // TODO: Handle exception here.
                }
            }
        }
    }
}