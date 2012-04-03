using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IDiscountItemRepository : IDisposable
    {
        IEnumerable<DiscountItem> GetDiscountItems();

        void InsertDiscountItem(DiscountItem discountitem, bool isImmediateSave);

        void DeleteDiscountItem(DiscountItem discountitem, bool isImmediateSave);

        void UpdateDiscountItem(DiscountItem discountitem, DiscountItem origDiscountitem, bool isImmediateSave);
    }
}
