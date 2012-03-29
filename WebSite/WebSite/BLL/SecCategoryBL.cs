using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.DAL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class SecCategoryBL : IDisposable
    {
        private bool mIsDisposed = false;

        private ISecCategoryRepository mSecCategoryRepository;

        public SecCategoryBL()
        {
            mSecCategoryRepository = new SecCategoryRepository();
        }

        public SecCategoryBL(ISecCategoryRepository secCategoryRepository)
        {
            mSecCategoryRepository = secCategoryRepository;
        }

        public IEnumerable<SecCategory> GetSecCategories()
        {
            return mSecCategoryRepository.GetSecCategories();
        }

        public SecCategory GetSecCategory(Int32 id)
        {
            try
            {
                return GetSecCategories().Single(secCategory => secCategory.SecCategoryID == id);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.

                return null;
            }
        }

        public IEnumerable<SecCategory> GetSecCategoriesByCategory(Int32 categoryID)
        {
            IEnumerable<SecCategory> totalSecCategories = GetSecCategories();

            return totalSecCategories.Where(secCategory => secCategory.CategoryID == categoryID);
        }

        public void InsertSecCategory(SecCategory secCategory)
        {
            try
            {
                mSecCategoryRepository.InsertSecCategory(secCategory);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteSecCategory(SecCategory secCategory)
        {
            try
            {
                mSecCategoryRepository.DeleteSecCategory(secCategory);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void UpdateSecCategory(SecCategory secCategory, SecCategory origSecCategory)
        {
            try
            {
                mSecCategoryRepository.UpdateSecCategory(secCategory, origSecCategory);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        protected void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
                if (isDisposing)
                {
                    mSecCategoryRepository.Dispose();
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