using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface ISuggestionRepository:IDisposable
    {
        IEnumerable<Suggestion> GetSuggestions();

        Suggestion GetSuggestionByID(Int32 id);

        void InsertSuggestion(Suggestion suggestion);

        void DeleteSuggestion(Suggestion suggestion);

    }
}