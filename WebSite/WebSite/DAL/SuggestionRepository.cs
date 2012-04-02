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

        public void InsertSuggestion(Suggestion suggestion, bool isImmediateSave)
        {
            try
            {
                mContext.Suggestions.AddObject(suggestion);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
                throw ex;
            }
        }

        public void DeleteSuggestion(Suggestion suggestion, bool isImmediateSave)
        {
            try
            {
                mContext.Suggestions.Attach(suggestion);
                mContext.Suggestions.DeleteObject(suggestion);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
                throw ex;
            }
        }

        public void UpdateSuggestion(Suggestion suggestion, Suggestion origSuggestion, bool isImmediateSave)
        {
            try
            {
                mContext.Suggestions.Attach(origSuggestion);
                mContext.Suggestions.ApplyCurrentValues(suggestion);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
                throw ex;
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