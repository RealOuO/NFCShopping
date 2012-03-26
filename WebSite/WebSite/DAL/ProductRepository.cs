using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class ProductRepository : IProductRepository, IDisposable
    {
        private bool mIsDisposed = false;

        private Sys_DBEntities mContext = new Sys_DBEntities();

        public IEnumerable<Product> GetProducts()
        {
            return mContext.Products.Include("SecCategory").ToList();
        }

        public void InsertProduct(Product product)
        {
            try
            {
                mContext.Products.AddObject(product);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void DeleteProduct(Product product)
        {
            try
            {
                mContext.Products.Attach(product);
                mContext.Products.DeleteObject(product);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void UpdateProduct(Product newProduct, Product origProduct)
        {
            try
            {
                mContext.Products.Attach(origProduct);
                mContext.ApplyCurrentValues("Products", newProduct);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        protected void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
                // Save the changes before disposing the object.
                mContext.SaveChanges();

                if (isDisposing)
                {
                    mContext.Dispose();
                }
            }

            mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}