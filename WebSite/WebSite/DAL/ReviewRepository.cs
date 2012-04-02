using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class ReviewRepository : IReviewRepository
    {
        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();

        public IEnumerable<Review> GetReviews()
        {
            return mContext.Reviews.ToList();
        }

        public void InsertReview(Review review, bool isImmediateSave)
        {
            try
            {
                mContext.Reviews.AddObject(review);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void DeleteReview(Review review, bool isImmediateSave)
        {
            try
            {
                mContext.Reviews.Attach(review);
                mContext.Reviews.DeleteObject(review);

                if(isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void UpdateReview(Review review, Review origReview, bool isImmediateSave)
        {
            try
            {
                mContext.Reviews.Attach(origReview);
                mContext.Reviews.ApplyCurrentValues(review);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        protected virtual void Dispose(bool disposing)
        {
            if (!this.mIsDisposed)
            {
                if (disposing)
                {
                    mContext.SaveChanges();
                    mContext.Dispose();
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