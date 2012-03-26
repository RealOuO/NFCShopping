using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    interface IProductRepository:IDisposable
    {
        IEnumerable<Product> GetProducts();

        void InsertProduct(Product product);
        /*删除商品信息*/
        void DeleteProduct(Product product);

        void UpdateProduct(Product newProduct, Product origProduct);
    }
}
