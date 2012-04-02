using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface ICategoryRepository : IDisposable
    {
        IEnumerable<Category> GetCategories();

        void InsertCategory(Category category, bool isImmediateSave);

        void UpdateCategory(Category newCategory, Category origCategory, bool isImmediateSave);

        void DeleteCategory(Category category, bool isImmediateSave);
    }
}
