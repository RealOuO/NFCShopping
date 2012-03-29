using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.DAL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class CategoryBL : IDisposable
    {
        private bool mIsDisposed = false;

        private ICategoryRepository mCategoryRepository;

        public CategoryBL()
        {
            mCategoryRepository = new CategoryRepository();
        }

        public CategoryBL(ICategoryRepository categoryRepository)
        {
            mCategoryRepository = categoryRepository;
        }

        public IEnumerable<Category> GetCategories()
        {
            return mCategoryRepository.GetCategories();
        }

        public Category GetCategory(Int32 id)
        {
            try
            {
                return GetCategories().Single(category => category.categoryID == id);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.

                return null;
            }
        }

        public void InsertCategory(Category category)
        {
            try
            {
                mCategoryRepository.InsertCategory(category);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteCategory(Category category)
        {
            try
            {
                mCategoryRepository.DeleteCategory(category);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void UpdateCategory(Category category, Category origCategory)
        {
            try
            {
                mCategoryRepository.UpdateCategory(category, origCategory);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        protected virtual void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
                if (isDisposing)
                {
                    mCategoryRepository.Dispose();
                }

                mIsDisposed = true;
            }
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}