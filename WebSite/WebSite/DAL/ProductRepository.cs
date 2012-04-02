using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class ProductRepository : IProductRepository
    {
        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();

        public IEnumerable<Product> GetProducts()
        {
            return mContext.Products.Include("SecCategory").ToList();
        }

        public void InsertProduct(Product product, bool isImmediateSave)
        {
            try
            {
                mContext.Products.AddObject(product);

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

        public void DeleteProduct(Product product, bool isImmediateSave)
        {
            try
            {
                // Cascade deletion for reviews.
                IReviewRepository reviewRepository = new ReviewRepository();
                var reviews = reviewRepository.GetReviews().Where(review => review.productID == product.productID);

                foreach (Review review in reviews)
                {
                    reviewRepository.DeleteReview(review, false);
                }

                reviewRepository.Dispose();

                // Cascade deletion for discount items.
                IDiscountItemRepository discountItemRepository = new DiscountItemRepository();
                var discountItems = discountItemRepository.GetDiscountItems().Where(discountItem => discountItem.productID == product.productID);

                foreach (DiscountItem discountItem in discountItems)
                {
                    discountItemRepository.DeleteDiscountItem(discountItem, false);
                }

                discountItemRepository.Dispose();

                mContext.Products.Attach(product);
                mContext.Products.DeleteObject(product);

                if (isImmediateSave)
                {
                    mContext.SaveChanges();
                }
            }
            catch (Exception ex)
            {
                var message = ex.Message;
                // TODO: Add exception handling code here.
                throw ex;
            }
        }

        public void UpdateProduct(Product newProduct, Product origProduct, bool isImmediateSave)
        {
            try
            {
                mContext.Products.Attach(origProduct);
                mContext.ApplyCurrentValues("Products", newProduct);

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

        protected void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
                // Save the changes before disposing the object.
                mContext.SaveChanges();

                if (isDisposing)
                {
                    mContext.Dispose();
                }
            }

            mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}