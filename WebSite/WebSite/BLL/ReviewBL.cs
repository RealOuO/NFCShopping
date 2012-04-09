using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.DAL;
using System.Collections;

namespace NFCShoppingWebSite.BLL
{
    public class ReviewBL:IDisposable
    {
        IReviewRepository mRepository = new ReviewRepository();

        IProductRepository mProductRepository = new ProductRepository();

        ShopEntities entities = new ShopEntities();

        #region 需求必要的业务逻辑

      
        /*获取某个用户的评论信息*/
        public ICollection<Review> GetReviewsByUserID(Int32 uid) 
        {
            return mRepository.GetReviews().Where(r => r.userID == uid).ToList();
        }

        /*获取某个商品的评论信息*/
        public ICollection<Review> GetReviewsByProductID(Int32 pid)
        {
            return mRepository.GetReviews().Where(r => r.productID == pid).ToList();
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
                mRepository.InsertReview(review,true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        /*删除商品的评论信息*/
        public void DeleteReview(Review review)
        {
            try
            {
                mRepository.DeleteReview(review,true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /**
         * 
         * 获取商品的平均得分
         * 返回0表示没数据或者分数就为0
         * 
         */
        public double GetAverageRatingByProductID(Int32 id)
        {
            IEnumerable<Review> reviews=mRepository.GetReviews().Where(r=>r.productID==id);
            if(reviews.ToList().Count==0)
                return 0.0;
            else
                return reviews.Average(r => r.rating)/10;
        }

        /*返回评分排列前十的商品*/
        public IEnumerable GetTOP10Products()
        {
            var rate = (from r in mRepository.GetReviews()  group r by r.productID into g from p in mProductRepository.GetProducts() where g.Key==p.productID select new { g.Key, AverageRating = g.Average(r => r.rating) } ).ToList().OrderByDescending(a=>a.AverageRating).Take(10) ;
            return rate;

        }


        /*用户更新商品的评论信息*/
        public void UpdateReview(Review review, Review origReview)
        {
            try
            {
                mRepository.UpdateReview(review, origReview,true);
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