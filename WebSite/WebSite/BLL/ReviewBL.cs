using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.DAL;

namespace NFCShoppingWebSite.BLL
{
    public class ReviewBL:IDisposable
    {
        IReviewRepository mRepository = new ReviewRepository();

        #region 需求必要的业务逻辑

        /*获取某个用户的评论信息*/
        public IEnumerable<Review> GetReviewsByUserID(Int32 uid) 
        {
            return mRepository.GetReviews().Where(r => r.userID == uid);
        }

        /*获取某个商品的评论信息*/
        public IEnumerable<Review> GetReviewsByProductID(Int32 pid)
        {
            return mRepository.GetReviews().Where(r => r.productID == pid);
        }

        public IEnumerable<Review> GetReviews()
        {
            return mRepository.GetReviews();
        }

        public void InsertReview(Review review)
        {
            review.createAt = DateTime.Now;
            try
            {
                mRepository.InsertReview(review);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteReview(Review review)
        {
            try
            {
                mRepository.DeleteReview(review);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /*用户更新商品的评论信息*/
        public void UpdateReview(Review review, Review origReview)
        {
            try
            {
                mRepository.UpdateReview(review, origReview);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        #endregion

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