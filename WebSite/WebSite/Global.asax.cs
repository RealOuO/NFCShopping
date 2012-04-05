using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Web;
using System.Web.Security;
using System.Web.SessionState;
using NFCShoppingWebSite.Utils;

namespace NFCShoppingWebSite
{
    public class Global : System.Web.HttpApplication
    {

        void Application_Start(object sender, EventArgs e)
        {
            // 在应用程序启动时运行的代码
            DirectoryCleaner.CleanDirectory(Server.MapPath("Images/Temp/"));
        }

        void Application_End(object sender, EventArgs e)
        {
            //  在应用程序关闭时运行的代码
            DirectoryCleaner.CleanDirectory(Server.MapPath("Images/Temp/"));
        }

        void Application_Error(object sender, EventArgs e)
        {
            // 在出现未处理的错误时运行的代码

        }

        void Session_Start(object sender, EventArgs e)
        {
            // 在新会话启动时运行的代码
            Session.Add("TempImages", new List<string>());
            Session.Timeout = 1;
        }

        void Session_End(object sender, EventArgs e)
        {
            // 在会话结束时运行的代码。 
            // 注意: 只有在 Web.config 文件中的 sessionstate 模式设置为
            // InProc 时，才会引发 Session_End 事件。如果会话模式设置为 StateServer 
            // 或 SQLServer，则不会引发该事件。

            // Delete all the temporary images uploaded by a user in this session.
            try
            {
                List<string> tempImages = (List<string>)Session["TempImages"];

                foreach (string tempImage in tempImages)
                {
                    File.Delete(tempImage);
                }
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.
            }
        }

    }
}
