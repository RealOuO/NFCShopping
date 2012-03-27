using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountItemRepository : IDiscountItemRepository, IDisposable
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
            return mContext.Discounts.Include("Discounts").ToList();
        }

        public DiscountItem GetDiscountItemByID(int id)
        {
            return mContext.CompiledDepartmentsByIdQuery(id);
        }

        public void InsertDiscountItem(DiscountItem discountItem)
        {
            try
            {
                mContext.Categories.AddObject(discountItem);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void DeleteDiscountItem(DiscountItem discountItem)
        {
            try
            {
                mContext.DiscountItems.Attach(discountItem);
                mContext.Categories.DeleteObject(discountItem);

            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }


        public void UpdateDiscountItem(DiscountItem newDiscountItem, DiscountItem origDiscountItem)

        {
            try
            {
                mContext.DiscountItems.Attach(origDiscountItem);
                mContext.ApplyCurrentValues("DiscountItems", newDiscountItem);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }

            
        }
    }
}