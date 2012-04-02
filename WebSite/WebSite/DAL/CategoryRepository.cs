using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class CategoryRepository : ICategoryRepository
    {
        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();

        public IEnumerable<Category> GetCategories()
        {
            return mContext.Categories.Include("SecCategories").ToList();
        }

        public void InsertCategory(Category category, bool isImmediateSave)
        {
            try
            {
                mContext.Categories.AddObject(category);

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

        public void UpdateCategory(Category newCategory, Category origCategory, bool isImmediateSave)
        {
            try
            {
                mContext.Categories.Attach(origCategory);
                mContext.ApplyCurrentValues("Categories", newCategory);

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

        public void DeleteCategory(Category category, bool isImmediateSave)
        {
            try
            {
                mContext.Categories.Attach(category);
                mContext.Categories.DeleteObject(category);

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
            if(!mIsDisposed)
            {
                mContext.SaveChanges();

                if(isDisposing)
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