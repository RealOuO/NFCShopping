using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class ProductRepository:IProductRepository,IDisposable
    {
        public IEnumerable<Product> GetProducts()
        {
            throw new NotImplementedException();
        }

        public Product GetProductByID(int id)
        {
            throw new NotImplementedException();
        }

        public Product GetProductByBarcode(string barcode)
        {
            throw new NotImplementedException();
        }

        public void InsertProduct(Product product)
        {
            throw new NotImplementedException();
        }

        public void DeleteProduct(Product product)
        {
            throw new NotImplementedException();
        }

        public void UpdateProduct(Product product)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}