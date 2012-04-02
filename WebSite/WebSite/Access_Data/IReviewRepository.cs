using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IReviewRepository : IDisposable
    {
        IEnumerable<Review> GetReviews();

        void InsertReview(Review review, bool isImmediateSave);

        void DeleteReview(Review review, bool isImmediateSave);

        void UpdateReview(Review review, Review origReview, bool isImmediateSave);
    }
}