using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    interface IProductRepository:IDisposable
    {
        IEnumerable<Product> GetProducts();

        Product GetProductByID(Int32 id);
        /*通过条形码编号获取商品信息*/
        Product GetProductByBarcode(String barcode);

        void InsertProduct(Product product);
        /*删除商品信息*/
        void DeleteProduct(Product product);

        void UpdateProduct(Product product);
    }
}
