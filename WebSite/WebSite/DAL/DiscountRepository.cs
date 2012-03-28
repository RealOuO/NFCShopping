﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class DiscountRepository:IDiscountRepository,IDisposable
    {

        private bool mIsDisposed = false;

        private ShopEntities mContext = new ShopEntities();
        
        public IEnumerable<Discount> GetDiscounts()
        {
            return mContext.Discounts.ToList();
        }


        public void InsertDiscount(Discount discount)
        {
            try
            {
                mContext.Discounts.AddObject(discount);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }


        public void DeleteDiscount(Discount discount)
        {
            try
            {
                mContext.Discounts.Attach(discount);
                mContext.Discounts.DeleteObject(discount);

            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }
        
        public void UpdateDiscount(Discount newDiscount,Discount origDiscount)
        {
            try
            {
                mContext.Discounts.Attach(origDiscount);
                mContext.ApplyCurrentValues("Discounts", newDiscount);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }


        protected void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
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