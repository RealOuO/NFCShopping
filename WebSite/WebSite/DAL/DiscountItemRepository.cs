using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountItemRepository : IDiscountItemRepository, IDisposable
    {

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public IEnumerable<DiscountItem> GetDiscountItems()
        {
            throw new NotImplementedException();
        }

        public DiscountItem GetDiscountItemByID(int id)
        {
            throw new NotImplementedException();
        }

        public void InsertDiscountItem(DiscountItem discountitem)
        {
            throw new NotImplementedException();
        }

        public void DeleteDiscountItem(DiscountItem discountitem)
        {
            throw new NotImplementedException();
        }

        public void UpdateDiscountItem(DiscountItem discountitem,DiscountItem origDiscountitem)
        {
            throw new NotImplementedException();
        }
    }
}