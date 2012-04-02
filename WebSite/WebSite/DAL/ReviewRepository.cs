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

        /*通过商品ID查询商品的时候可以用，user返回给webservice的时候已经忽略了password，
         * 不查询出商品的具体信息*/
        public IEnumerable<Review> GetReviewWithUser()
        {
            return mContext.Reviews.Include("User").ToList();
        }

        /*通过商品ID查询商品的时候可以用，不查询出用户的具体信息，但是所评论的商品的信息
         *会被连带查询出来*/
        public IEnumerable<Review> GetReviewWithProduct()
        {
            return mContext.Reviews.Include("Product").ToList();
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
                throw ex;
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
                throw ex;
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
                throw ex;
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