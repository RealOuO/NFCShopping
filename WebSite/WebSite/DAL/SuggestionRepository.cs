using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class SuggestionRepository:ISuggestionRepository,IDisposable
    {
        public IEnumerable<Suggestion> GetSuggestions()
        {
            throw new NotImplementedException();
        }

        public Suggestion GetSuggestionByID(int id)
        {
            throw new NotImplementedException();
        }

        public void InsertSuggestion(Suggestion suggestion)
        {
            throw new NotImplementedException();
        }

        public void DeleteSuggestion(Suggestion suggestion)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}