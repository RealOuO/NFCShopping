using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IDiscountItemRepository : IDisposable
    {
        IEnumerable<DiscountItem> GetDiscountItems();

        void InsertDiscountItem(DiscountItem discountitem);

        void DeleteDiscountItem(DiscountItem discountitem);

        void UpdateDiscountItem(DiscountItem discountitem, DiscountItem origDiscountitem);
    }
}
