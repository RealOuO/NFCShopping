using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IDiscountRepository:IDisposable
    {
        IEnumerable<Discount> GetDiscounts();

        Discount GetDiscountByID(Int32 id);

        void InsertDiscount(User discount);

        void DeleteDiscount(Discount discount);

        void UpdateDiscount(Discount discount);
    }
}