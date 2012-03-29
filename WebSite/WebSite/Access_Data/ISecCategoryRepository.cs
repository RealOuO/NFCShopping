using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface ISecCategoryRepository : IDisposable
    {
        IEnumerable<SecCategory> GetSecCategories();

        void InsertSecCategory(SecCategory secondCategory);

        void UpdateSecCategory(SecCategory newSecondCategory, SecCategory origSecondCategory);

        void DeleteSecCategory(SecCategory secondCategory);
    }
}
