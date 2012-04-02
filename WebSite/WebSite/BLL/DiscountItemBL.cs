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

        public DiscountItem GetDiscountItem(Int32 id)
        {
            try
            {
                return GetDiscountItems().Single(discountItem => discountItem.id == id);
            }
            catch (Exception ex)
            {
                // TODO: Add handling code here.

                return null;
            }
        }

        public void InsertDiscountItem(DiscountItem discountItem)
        {
            try
            {
                mRepository.InsertDiscountItem(discountItem, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteDiscountItem(DiscountItem discountItem)
        {
            try
            {
                mRepository.DeleteDiscountItem(discountItem, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void UpdateDiscountItem(DiscountItem discountItem, DiscountItem origDiscountItem)
        {
            try
            {
                mRepository.UpdateDiscountItem(discountItem, origDiscountItem, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
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