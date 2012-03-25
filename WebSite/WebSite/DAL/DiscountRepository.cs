using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountRepository:IDiscountItemRepository,IDisposable
    {
        public IEnumerable<DiscountItem> GetDiscounts()
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

        public void UpdateDiscountItem(DiscountItem discountitem)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}