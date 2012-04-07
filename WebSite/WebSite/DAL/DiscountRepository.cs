using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountRepository : IDiscountRepository
    {

        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();
        
        public IEnumerable<Discount> GetDiscounts()
        {
            return mContext.Discounts.ToList();
        }


        public void InsertDiscount(Discount discount, bool isImmediateSave)
        {
            try
            {
                mContext.Discounts.AddObject(discount);

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


        public void DeleteDiscount(Discount discount, bool isImmediateSave)
        {
            try
            {
                IDiscountItemRepository discountItemRepository = new DiscountItemRepository();
                var discountItems = discountItemRepository.GetDiscountItems().Where(discountItem => discountItem.discountID == discount.discountID);

                foreach (DiscountItem discountItem in discountItems)
                {
                    discountItemRepository.DeleteDiscountItem(discountItem, false);
                }

                discountItemRepository.Dispose();
                
                mContext.Discounts.Attach(discount);
                mContext.Discounts.DeleteObject(discount);

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

        public void UpdateDiscount(Discount newDiscount, Discount origDiscount, bool isImmediateSave)
        {
            try
            {
                mContext.Discounts.Attach(origDiscount);
                mContext.ApplyCurrentValues("Discounts", newDiscount);

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
    }
}