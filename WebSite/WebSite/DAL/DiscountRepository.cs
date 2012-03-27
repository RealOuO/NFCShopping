using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountRepository:IDiscountRepository,IDisposable
    {
        public IEnumerable<Discount> GetDiscounts()
        {
            throw new NotImplementedException();
        }

        public Discount GetDiscountByID(int id)
        {
            throw new NotImplementedException();
        }

        public void InsertDiscount(Discount discount)
        {
            throw new NotImplementedException();
        }

        public void DeleteDiscount(Discount discount)
        {
            throw new NotImplementedException();
        }

        public void UpdateDiscount(Discount discount, Discount origDiscount)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}