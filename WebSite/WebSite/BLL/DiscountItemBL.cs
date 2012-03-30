using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.DAL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class DiscountItemBL:IDisposable
    {
        IDiscountItemRepository mRepository = new DiscountItemRepository();
        /*查询某一期所有的优惠信息*/
        public IEnumerable<DiscountItem> GetDiscountItemByDiscountID(Int32 discountID)
        {
            return mRepository.GetDiscountItems().Where(s => s.discountID == discountID);
        }
        /*查询某商品所有的优惠信息*/
        public IEnumerable<DiscountItem> GetDiscountItemByProductID(Int32 productID)
        {
            return mRepository.GetDiscountItems().Where(s => s.productID == productID);
        }
        /*查询某一期某商品所有的优惠信息*/
        public IEnumerable<DiscountItem> GetDiscountItemByDiscountIDProductID(Int32 discountID,Int32 productID)
        {
            return mRepository.GetDiscountItems().Where(s => (s.productID == productID && s.discountID == discountID));
        }
        /*查询所有的优惠信息*/
        public IEnumerable<DiscountItem> GetDiscountItems()
        {
            return mRepository.GetDiscountItems();
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