using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.DAL;

namespace NFCShoppingWebSite.BLL
{
    public class UserBL:IDisposable
    {

        IUserRepository mRepository = new UserRepository();

        #region 需求必要的业务逻辑处理实现定义

        /*通过获取用户信息调用*/
        public User GetUserByID(Int32 id)
        {
            return mRepository.GetUsers().ToList().Find(u => u.userID == id);
        }

        /*注册的时候调用，判断用户是否已经被注册*/
        public bool IsExisted(String userName)
        {
            IEnumerable<User> users=mRepository.GetUsers().Where(u=>u.userName.Equals(userName));
            if (users.ToList().Count > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public User FindUserByUsername(String userName)
        {
            return mRepository.GetUsers().ToList().Find(u => u.userName.Equals(userName));
        }

        /*用户注册业务逻辑*/
        public User Regist(User user)
        {
            user.lastVisitedDate = DateTime.Now;
            user.visitedTimes = 0;
            mRepository.InsertUser(user);
            return FindUserByUsername(user.userName);
        }

        /*用户签到函数*/
        public bool AutoAddVisitedTime(User user)
        {
            return true;
        }

        #endregion

        private bool mIsDisposed = false; 

        protected virtual void Dispose(bool disposing)
        {
            if (!this.mIsDisposed)
            {
                if (disposing)
                {
                    mRepository.Dispose();
                }
            }
            this.mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}