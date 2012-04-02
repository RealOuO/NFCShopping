using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.DAL;

namespace NFCShoppingWebSite.BLL
{
    public class SuggestionBL:IDisposable
    {
        ISuggestionRepository mRepository = new SuggestionRepository();

        public Suggestion GetSuggestionByID(int id)
        {
            throw new NotImplementedException();
        }
        /*用户给超市发布反馈信息的业务逻辑*/
        public void AddSuggestion(Suggestion suggestion)
        {
            mRepository.InsertSuggestion(suggestion, true);
        }
        /*查询用户所有的反馈信息*/
        public IEnumerable<Suggestion> GetSuggestionsByUserID(Int32 userID)
        {
            return mRepository.GetSuggestions().Where(s => s.userID == userID);
        }
        /*查询所有的反馈信息*/
        public IEnumerable<Suggestion> GetSuggestions()
        {
            return mRepository.GetSuggestions();
        }

       
        private bool mIsDisposed = false;

        protected virtual void Dispose(bool disposing)
        {
            if (!this.mIsDisposed)
            {
                if (disposing)
                {
                    mRepository.Dispose();
                }
            }
            this.mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}