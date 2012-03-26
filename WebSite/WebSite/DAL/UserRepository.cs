using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.DAL
{
    public class UserRepository:IUserRepository,IDisposable
    {

        #region  IUserRepository接口具体实现

        IEnumerable<User> IUserRepository.GetUsers()
        {
            throw new NotImplementedException();
        }

        User IUserRepository.GetUserByID(int id)
        {
            throw new NotImplementedException();
        }

        void IUserRepository.InsertUser(User user)
        {
            throw new NotImplementedException();
        }

        void IUserRepository.DeleteUser(User user)
        {
            throw new NotImplementedException();
        }

        void IUserRepository.UpdateUser(User user)
        {
            throw new NotImplementedException();
        }

        #endregion

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}