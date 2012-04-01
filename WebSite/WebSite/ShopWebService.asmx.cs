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
        public User Regist(User user)
        {
            UserBL bl = new UserBL();
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
        public User Login(User user)
        {
            UserBL bl = new UserBL();
            return bl.Login(user);
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
        public List<Review> GetReviewsByUserID(Int32 uid)
        {
            ReviewBL bl = new ReviewBL();
            return bl.GetReviewsByUserID(uid).ToList();
        }

        [SoapRpcMethod, WebMethod]
        public List<Review> GetReviewsByProductID(Int32 pid)
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
