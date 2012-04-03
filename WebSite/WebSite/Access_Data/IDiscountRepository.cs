using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IDiscountRepository : IDisposable
    {
        IEnumerable<Discount> GetDiscounts();

        void InsertDiscount(Discount discount, bool isImmediateSave);

        void DeleteDiscount(Discount discount, bool isImmediateSave);

        void UpdateDiscount(Discount discount, Discount origDiscount, bool isImmediateSave);
    }
}