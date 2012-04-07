using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

namespace NFCShoppingWebSite.WebPages
{
    public partial class Users : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void DepartmentsObjectDataSource_Deleted(object sender, ObjectDataSourceStatusEventArgs e)
        {
            if (e.Exception != null)
            {
                CheckForOptimisticConcurrencyException(e, "delete");
            }
        }

        private void CheckForOptimisticConcurrencyException(ObjectDataSourceStatusEventArgs e, string function)
        {
            if (e.Exception.InnerException is OptimisticConcurrencyException)
            {
                var concurrencyExceptionValidator = new CustomValidator();
                concurrencyExceptionValidator.IsValid = false;
                concurrencyExceptionValidator.ErrorMessage =
                    "The record you attempted to edit or delete was modified by another " +
                    "user after you got the original value. The edit or delete operation was canceled " +
                    "and the other user's values have been displayed so you can " +
                    "determine whether you still want to edit or delete this record.";
                Page.Validators.Add(concurrencyExceptionValidator);
                e.ExceptionHandled = true;
            }
        }

        protected void GridView_RowDataBound(object sender, GridViewRowEventArgs e)
        {
             if (e.Row.RowType == DataControlRowType.DataRow)
             {
            //将班级类别编号转换为班级类别名称
                 if (e.Row.Cells[2].Text.Equals( "1"))  //5是要转换的列的序号，从0开始
                 {
                     e.Row.Cells[2].Text = "先生";
                 }
                 else if (e.Row.Cells[2].Text.Equals("2"))
                 {
                     e.Row.Cells[2].Text = "女士";
                 }
                 else
                 {
                     e.Row.Cells[2].Text = "保密";
                 }
             }
     }
    }
    
}