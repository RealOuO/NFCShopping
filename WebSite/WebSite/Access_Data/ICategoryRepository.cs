using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    interface ICategoryRepository
    {
        IEnumerable<Category> GetCategories();

        void InsertCategory(Category category);

        void UpdateCategory(Category newCategory, Category origCategory);

        void DeleteCategory(Category category);
    }
}
