using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class SecCategoryRepository : ISecCategoryRepository, IDisposable
    {
        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();

        public IEnumerable<SecCategory> GetSecCategories()
        {
            return mContext.SecCategories.Include("Category").ToList();
        }

        public void InsertSecCategory(SecCategory secondCategory)
        {
            try
            {
                mContext.SecCategories.AddObject(secondCategory);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void UpdateSecCategory(SecCategory newSecondCategory, SecCategory origSecondCategory)
        {
            try
            {
                mContext.SecCategories.Attach(origSecondCategory);
                mContext.ApplyCurrentValues("SecCategories", newSecondCategory);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void DeleteSecCategory(SecCategory secondCategory)
        {
            try
            {
                mContext.SecCategories.Attach(secondCategory);
                mContext.SecCategories.DeleteObject(secondCategory);
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