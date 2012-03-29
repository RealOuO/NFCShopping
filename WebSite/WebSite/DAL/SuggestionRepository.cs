using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class SuggestionRepository : ISuggestionRepository
    {
        private ShopEntities mContext = new ShopEntities();

        public IEnumerable<Suggestion> GetSuggestions()
        {
            return mContext.Suggestions.Include("Product").ToList();
        }

        public void InsertSuggestion(Suggestion suggestion)
        {
            try
            {
                mContext.Suggestions.AddObject(suggestion);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void DeleteSuggestion(Suggestion suggestion)
        {
            try
            {
                mContext.Suggestions.Attach(suggestion);
                mContext.Suggestions.DeleteObject(suggestion);
                mContext.SaveChanges();
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void UpdateSuggestion(Suggestion suggestion, Suggestion origSuggestion)
        {
            try
            {
                mContext.Suggestions.Attach(origSuggestion);
                mContext.Suggestions.ApplyCurrentValues(suggestion);
                mContext.SaveChanges();
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        private bool mDisposedValue = false;
        protected virtual void Dispose(bool disposing)
        {
            if (!this.mDisposedValue)
            {
                if (disposing)
                {
                    mContext.SaveChanges();
                    mContext.Dispose();
                }
            }
            this.mDisposedValue = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}