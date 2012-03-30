using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.DAL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class DiscountBL:IDisposable
    {
        IDiscountRepository mRepository = new DiscountRepository();
        /*查询某一期所有的优惠信息*/
        public IEnumerable<Discount> GetDiscountByDiscountID(Int32 discountID)
        {
            return mRepository.GetDiscounts().Where(s => s.discountID == discountID);
        }
        /*查询所有的优惠信息*/
        public IEnumerable<Discount> GetDiscounts()
        {
            return mRepository.GetDiscounts();
        }

        private bool mIsDisposed = false;

        protected virtual void Dispose(bool disposing)
        {
            if (!this.mIsDisposed)
            {
                if (disposing)
                {
                    mRepository.Dispose();
                }
            }
            this.mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

    }
}