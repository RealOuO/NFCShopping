using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountItemRepository : IDiscountItemRepository
    {
        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();

        protected void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
                mContext.SaveChanges();

                if (isDisposing)
                {
                    mContext.Dispose();
                }
            }

            mIsDisposed = true;
        }
        
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        public IEnumerable<DiscountItem> GetDiscountItems()
        {
            return mContext.DiscountItems.ToList(); 
        }

        public void InsertDiscountItem(DiscountItem discountItem, bool isImmediateSave)
        {
            try
            {
                mContext.DiscountItems.AddObject(discountItem);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void DeleteDiscountItem(DiscountItem discountItem, bool isImmediateSave)
        {
            try
            {
                mContext.DiscountItems.Attach(discountItem);
                mContext.DiscountItems.DeleteObject(discountItem);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }


        public void UpdateDiscountItem(DiscountItem newDiscountItem, DiscountItem origDiscountItem, bool isImmediateSave)

        {
            try
            {
                mContext.DiscountItems.Attach(origDiscountItem);
                mContext.ApplyCurrentValues("DiscountItems", newDiscountItem);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }

            
        }
    }
}