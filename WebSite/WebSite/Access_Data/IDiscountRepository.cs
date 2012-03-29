using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IDiscountRepository : IDisposable
    {
        IEnumerable<Discount> GetDiscounts();

        void InsertDiscount(Discount discount);

        void DeleteDiscount(Discount discount);

        void UpdateDiscount(Discount discount,Discount origDiscount);
    }
}