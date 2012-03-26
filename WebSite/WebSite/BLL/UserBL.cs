using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class UserBL
    {


        #region 需求必要的业务逻辑处理实现定义

        /*注册的时候调用，判断用户是否已经被注册*/
        bool IsExisted(User user)
        {
            return true;
        }
        /*用户签到函数*/
        bool AutoAddVisitedTime(User user)
        {
            return true;
        }

        #endregion
    }
}