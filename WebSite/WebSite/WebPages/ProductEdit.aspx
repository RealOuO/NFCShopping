<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.ProductEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
           <script type="text/javascript">
               $(function () {
                   $(".children:eq(0)").show();
                   $("span:eq(0)").html("-");
                   $("a:eq(4)").css({ "color": "red" });
                   $(".head:eq(0)").toggle(function () {
                       $(this).next().hide();
                       $("span:eq(0)").html("+");
                   }, function () {
                       $(this).next().show();
                       $("span:eq(0)").html("-");
                   });
               });
    </script>

    <script  type="text/javascript">
        function delete_confirm(e) {
            if (event.srcElement.type == "submit" && document.all(event.srcElement.name).value == "删除")
                event.returnValue = confirm("确认是否删除？");
        }
        document.onclick = delete_confirm; 
</script> 
    <link href="../css/ProductEdit.css" rel="stylesheet" type="text/css" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" 
    Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
        <br />
        
          <table>
          <tr>
          <td>
         <asp:Label ID="Label3" runat="server" Text="商品分类:"></asp:Label>
         </td>
         <td>
    <asp:DropDownList ID="CategoriesDropDownList" runat="server" AutoPostBack="True"
        DataSourceID="CategoriesDataSource" DataTextField="categoryName" 
        DataValueField="categoryID" onprerender="CategoriesDropDownList_PreRender">
    </asp:DropDownList>
    </td>
    <td>
     <asp:Label ID="Label4" runat="server" Text="商品子分类:"></asp:Label>
          </td>
         <td>
    <asp:DropDownList ID="SecCategoriesDropDownList" runat="server" DataSourceID="SecCategoriesDataSource"
        DataTextField="secCategoryName" DataValueField="secCategoryID" 
        onprerender="SecCategoriesDropDownList_PreRender">
    </asp:DropDownList>
    </td>
    <td>
    <asp:Label ID="Label1" runat="server" Text="商品名称:" ></asp:Label>
         </td>
         <td>
    <asp:TextBox ID="ProductNameTextBox" runat="server" autofocus="true"></asp:TextBox>
    </td>
    <td>
     <asp:Label ID="Label6" runat="server" Text="价格:"></asp:Label>
        </td>
         <td>
    <asp:TextBox ID="PriceTextBox" runat="server" required="true"></asp:TextBox>
    </td>
    </tr>
    <tr>
    <td>
      <asp:Label ID="Label5" runat="server" Text="条形码:"></asp:Label>
       </td>
         <td>
    <asp:TextBox ID="BarcodeTextBox" runat="server"></asp:TextBox>
    </td>
     <td>
    <asp:Label ID="Label7" runat="server" Text="品牌:"></asp:Label>
       </td>
         <td>
    <asp:TextBox ID="BrandTextBox" runat="server"></asp:TextBox>
  </td>
  <td>
    <asp:Label ID="Label8" runat="server" Text="产地:"></asp:Label>
      </td>
         <td>
    <asp:TextBox ID="LocationTextBox" runat="server"></asp:TextBox>
    </td>
    </tr>
    </table>
    <div style="height: 12px">
    </div>
   <div style="width:1000; height:230;">
       <div class="describe">
    <br />
    <asp:Label ID="Label2" runat="server" Text="商品描述:"></asp:Label>
          <br />       <br />
    <asp:TextBox ID="ProductDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="402px"></asp:TextBox>
        </div>
    <div class="picture">
        <asp:Label ID="Label10" runat="server" Text="商品图片:"></asp:Label>
      


    <asp:FileUpload ID="ProductPictureUpload" runat="server" />
    <asp:Button ID="UploadPictureButton" runat="server" 
        onclick="UploadPictureButton_Click" Text="上传图片" />
        <br />
        <br />
        <asp:Image ID="ProductImage" runat="server" Height="230px" ImageAlign="Middle" 
            Width="330px" />
        </div>

    
     </div>     

    <div>
    </div>
   
    <div>
    </div>
   
    <div>
    </div>
  
  

    <div>
    </div>
 <br />
  <br />
    <br />
    <asp:Button ID="SubmitButton" runat="server" Height="25px" OnClick="SubmitButton_Click"
        Text="确定提交" Width="98px" />
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="0" Name="id" 
                QueryStringField="productID" Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="CategoriesDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Category"
        DeleteMethod="DeleteCategory" InsertMethod="InsertCategory" SelectMethod="GetCategories"
        TypeName="NFCShoppingWebSite.BLL.CategoryBL" UpdateMethod="UpdateCategory">
        <UpdateParameters>
            <asp:Parameter Name="category" Type="Object" />
            <asp:Parameter Name="origCategory" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="SecCategoriesDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.SecCategory"
        DeleteMethod="DeleteSecCategory" InsertMethod="InsertSecCategory" SelectMethod="GetSecCategoriesByCategory"
        TypeName="NFCShoppingWebSite.BLL.SecCategoryBL" UpdateMethod="UpdateSecCategory">
        <SelectParameters>
            <asp:ControlParameter ControlID="CategoriesDropDownList" DefaultValue="0" Name="categoryID"
                PropertyName="SelectedValue" Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="secCategory" Type="Object" />
            <asp:Parameter Name="origSecCategory" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
</asp:Content>
