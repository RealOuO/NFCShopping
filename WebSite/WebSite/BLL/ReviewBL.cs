using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.DAL;

namespace NFCShoppingWebSite.BLL
{
    public class ReviewBL
    {

        #region 需求必要的业务逻辑
        /*获取某个用户的评论信息*/
        IEnumerable<Review> GetReviewsByUserID(Int32 uid) 
        {
            return new List<Review>();
        }
        /*获取某个商品的评论信息*/
        IEnumerable<Review> GetReviewsByProductID(Int32 pid)
        {
            return new List<Review>();
        }

        #endregion

    }
}