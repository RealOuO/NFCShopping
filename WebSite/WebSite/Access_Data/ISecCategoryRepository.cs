using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface ISecCategoryRepository : IDisposable
    {
        IEnumerable<SecCategory> GetSecCategories();

        void InsertSecCategory(SecCategory secondCategory, bool isImmediateSave);

        void UpdateSecCategory(SecCategory newSecondCategory, SecCategory origSecondCategory, bool isImmediateSave);

        void DeleteSecCategory(SecCategory secondCategory, bool isImmediateSave);
    }
}
