using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IReviewRepository : IDisposable
    {
        IEnumerable<Review> GetReviews();

        void InsertReview(Review review);

        void DeleteReview(Review review);

        void UpdateReview(Review review,Review origReview);
    }
}