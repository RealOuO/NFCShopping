using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class SecCategoryRepository : ISecCategoryRepository
    {
        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();

        public IEnumerable<SecCategory> GetSecCategories()
        {
            return mContext.SecCategories.Include("Category").ToList();
        }

        public void InsertSecCategory(SecCategory secondCategory, bool isImmediateSave)
        {
            try
            {
                mContext.SecCategories.AddObject(secondCategory);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
                throw ex;
            }
        }

        public void UpdateSecCategory(SecCategory newSecondCategory, SecCategory origSecondCategory, bool isImmediateSave)
        {
            try
            {
                mContext.SecCategories.Attach(origSecondCategory);
                mContext.ApplyCurrentValues("SecCategories", newSecondCategory);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
                throw ex;
            }
        }

        public void DeleteSecCategory(SecCategory secondCategory, bool isImmediateSave)
        {
            try
            {
                mContext.SecCategories.Attach(secondCategory);
                mContext.SecCategories.DeleteObject(secondCategory);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
                throw ex;
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