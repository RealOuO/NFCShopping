using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface ISuggestionRepository : IDisposable
    {
        IEnumerable<Suggestion> GetSuggestions();

        void InsertSuggestion(Suggestion suggestion, bool isImmediateSave);

        void DeleteSuggestion(Suggestion suggestion, bool isImmediateSave);

        void UpdateSuggestion(Suggestion suggestion, Suggestion origSuggestion, bool isImmediateSave);

    }
}