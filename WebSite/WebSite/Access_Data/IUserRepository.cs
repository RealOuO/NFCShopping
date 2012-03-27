using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NFCShoppingWebSite.Access_Data
{
    public interface IUserRepository:IDisposable
    {
        /*查询用户信息调用*/
        IEnumerable<User> GetUsers();
        /*新增用户信息调用*/
        void InsertUser(User user);
        /*删除用户信息调用*/
        void DeleteUser(User user);
        /*更新用户信息调用*/
        void UpdateUser(User user, User origUser);
        
    }
}
