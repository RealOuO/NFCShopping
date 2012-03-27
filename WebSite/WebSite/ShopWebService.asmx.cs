using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.BLL;

namespace NFCShoppingWebSite
{
    /// <summary>
    /// ShopWebService 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消对下行的注释。
    // [System.Web.Script.Services.ScriptService]
    public class ShopWebService : System.Web.Services.WebService
    {

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        public User FindUserByID(Int32 id)
        {
            UserBL bl = new UserBL();
            return bl.GetUserByID(id);
        }
        [WebMethod]
        public bool IsExisted(String userName)
        {
            UserBL bl = new UserBL();
            return bl.IsExisted(userName);
        }

        [WebMethod]
        public User Regist(User user)
        {
            UserBL bl = new UserBL();
            return bl.Regist(user);
        }

        [WebMethod]
        public void AddSuggestion(Suggestion suggestion)
        {
            SuggestionBL bl = new SuggestionBL();
            suggestion.date = DateTime.Now;
            bl.AddSuggestion(suggestion);
        }
    }
}
