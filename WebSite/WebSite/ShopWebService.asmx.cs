using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.BLL;
using System.Web.Services.Protocols;
namespace NFCShoppingWebSite
{
    /// <summary>
    /// ShopWebService 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [SoapRpcService]
    //[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消对下行的注释。
    // [System.Web.Script.Services.ScriptService]
    public class ShopWebService : System.Web.Services.WebService
    {

        [SoapRpcMethod,WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [SoapRpcMethod, WebMethod]
        public User FindUserByID(Int32 id)
        {
            UserBL bl = new UserBL();
            return bl.GetUserByID(id);
        }

        [SoapRpcMethod, WebMethod]
        public double GetAverageRatingByProductID(int id)
        {
            ReviewBL bl = new ReviewBL();
            return bl.GetAverageRatingByProductID(id);
        }

        [SoapRpcMethod, WebMethod]
        public Product FindProductByID(Int32 id)
        {
            ProductBL bl = new ProductBL();
            return bl.GetProduct(id);
        }

        [SoapRpcMethod, WebMethod]
        public Product FindProductByBarcode(String barcode)
        {
            ProductBL bl = new ProductBL();
            return bl.GetProductByBarcode(barcode);
        }

        [SoapRpcMethod, WebMethod]
        public List<DiscountItem> FindDiscountItemsByDiscountID(Int32 discountID)
        {
            DiscountItemBL bl = new DiscountItemBL();
            return bl.GetDiscountItemsByDiscountID(discountID).ToList();
        }

        [SoapRpcMethod, WebMethod]
        public List<Discount> GetDiscounts()
        {
            DiscountBL bl = new DiscountBL();
            return bl.GetDiscounts().ToList();
        }

        [SoapRpcMethod, WebMethod]
        public bool IsExisted(String userName)
        {
            UserBL bl = new UserBL();
            return bl.IsExisted(userName);
        }


        [SoapRpcMethod, WebMethod]
        public User FindUserByUserName(String userName)
        {
            UserBL bl = new UserBL();
            return bl.FindUserByUsername(userName);
        }

        [SoapRpcMethod, WebMethod]
        public User Regist(String userName,String password,int gender)
        {
            UserBL bl = new UserBL();
            User user = new User();
            user.userName = userName;
            user.userPassword = password;
            user.gender = gender;
            try
            {
                return bl.Regist(user);
            }
            catch 
            {
                return null;
            }
        }

        [SoapRpcMethod, WebMethod]
        public bool AddVisitTimes(Int32 id)
        {
            UserBL bl = new UserBL();
            User user = bl.GetUserByID(id);
            return bl.AutoAddVisitedTime(user);
        }

        [SoapRpcMethod, WebMethod]
        public User Login(String userName, String password)
        {
            UserBL bl = new UserBL();
            User user = new User();
            user.userName = userName;
            user.userPassword = password;
            return bl.Login(user);
        }

        [SoapRpcMethod, WebMethod]
        public List<Review> FindReviewsByUserID(Int32 uid)
        {
            ReviewBL bl = new ReviewBL();
            return bl.GetReviewsByUserID(uid).ToList();
        }

        [SoapRpcMethod, WebMethod]
        public List<Review> FindReviewsByProductID(Int32 pid)
        {
            ReviewBL bl = new ReviewBL();
            return bl.GetReviewsByProductID(pid).ToList();
            
        }

        [SoapRpcMethod, WebMethod]
        public bool RemoveReview(Review review)
        {
            ReviewBL bl = new ReviewBL();
            try
            {
                bl.DeleteReview(review);
                return true;
            }
            catch
            {
                return false;
            }
        }

        [SoapRpcMethod, WebMethod]
        public bool AddReview(Review review)
        {
            ReviewBL bl = new ReviewBL();
            try
            {
                bl.InsertReview(review);
                return true;
            }
            catch
            {
                return false;
            }
        }

        [SoapRpcMethod, WebMethod]
        public bool AddSuggestion(Suggestion suggestion)
        {
            SuggestionBL bl = new SuggestionBL();
            try
            {
                bl.InsertSuggestion(suggestion);
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
