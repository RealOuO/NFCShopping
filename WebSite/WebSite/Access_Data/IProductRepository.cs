using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IProductRepository : IDisposable
    {
        IEnumerable<Product> GetProducts();

        void InsertProduct(Product product, bool isImmediateSave);
        /*删除商品信息*/
        void DeleteProduct(Product product, bool isImmediateSave);

        void UpdateProduct(Product newProduct, Product origProduct, bool isImmediateSave);
    }
}
