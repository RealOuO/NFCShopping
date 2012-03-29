using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface ISuggestionRepository : IDisposable
    {
        IEnumerable<Suggestion> GetSuggestions();

        void InsertSuggestion(Suggestion suggestion);

        void DeleteSuggestion(Suggestion suggestion);

        void UpdateSuggestion(Suggestion suggestion, Suggestion origSuggestion);

    }
}