using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class ReviewRepository:IReviewRepository,IDisposable
    {

        public IEnumerable<Review> GetReviews()
        {
            throw new NotImplementedException();
        }

        public Review GetReviewByID(int id)
        {
            throw new NotImplementedException();
        }

        public void InsertReview(Review review)
        {
            throw new NotImplementedException();
        }

        public void DeleteReview(Review review)
        {
            throw new NotImplementedException();
        }

        public void UpdateReview(Review review)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}