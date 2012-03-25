using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    interface IDiscountItemRepository:IDisposable
    {
        IEnumerable<DiscountItem> GetDiscounts();

        DiscountItem GetDiscountItemByID(Int32 id);

        void InsertDiscountItem(DiscountItem discountitem);

        void DeleteDiscountItem(DiscountItem discountitem);

        void UpdateDiscountItem(DiscountItem discountitem);
    }
}
