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
            return mContext.Reviews.Include("Products").ToList();
        }

        public void InsertReview(Review review)
        {
            try
            {
                mContext.Reviews.AddObject(review);
            }
            catch (Exception ex)
            {
            }
        }

        public void DeleteReview(Review review)
        {
            try
            {
                mContext.Reviews.Attach(review);
                mContext.Reviews.DeleteObject(review);
                mContext.SaveChanges();
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

        public void UpdateReview(Review review,Review origReview)
        {
            try
            {
                mContext.Reviews.Attach(origReview);
                mContext.Reviews.ApplyCurrentValues(review);
                mContext.SaveChanges();
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