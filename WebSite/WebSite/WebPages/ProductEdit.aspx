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
    <br />
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" 
    Font-Size="X-Large"  ForeColor="black"
        Text="Label"></asp:Label>
        <br />
        <br />
        <div class="information">
        <div class="product">
    <table class="subproduct">
        <tr>
            <td>
                <asp:Label ID="Label3" runat="server" Text="商品分类:"></asp:Label>
           <asp:DropDownList ID="CategoriesDropDownList" CssClass="content" runat="server" AutoPostBack="True"
        DataSourceID="CategoriesDataSource" DataTextField="categoryName" 
        DataValueField="categoryID" onprerender="CategoriesDropDownList_PreRender">
          </asp:DropDownList>
              </td>
            <td>
                <asp:Label ID="Label4" runat="server" Text="商品子分类:"></asp:Label>
         
                <asp:DropDownList ID="SecCategoriesDropDownList" CssClass="content" runat="server" DataSourceID="SecCategoriesDataSource"
        DataTextField="secCategoryName" DataValueField="secCategoryID" 
        onprerender="SecCategoriesDropDownList_PreRender">
        </asp:DropDownList>
            </td>
               </tr>
             <tr>
            <td>
                <asp:Label ID="Label1" runat="server" Text="商品名称:"></asp:Label>
         
      <asp:TextBox ID="ProductNameTextBox" CssClass="content" runat="server" required="true"></asp:TextBox>*
            </td>
            <td>
                <asp:Label ID="Label6" runat="server" Text="价格:"></asp:Label>
        
    <asp:TextBox ID="PriceTextBox" CssClass="content" runat="server" required="true"></asp:TextBox>*
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="Label5" runat="server" Text="条形码:"></asp:Label>
        
               &nbsp;&nbsp;    <asp:TextBox ID="BarcodeTextBox"  CssClass="content" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:Label ID="Label7" runat="server" Text="品牌:"></asp:Label>
     
                <asp:TextBox ID="BrandTextBox" CssClass="content"  runat="server"></asp:TextBox>
            </td>
               </tr>
             <tr>
            <td>
                <asp:Label ID="Label8" runat="server" Text="产地:"></asp:Label>
   <asp:TextBox ID="LocationTextBox" CssClass="content1" runat="server"></asp:TextBox>
            </td>
        </tr>
    </table>
          <div class="describe">
            <br />
             
            <asp:Label ID="Label2" runat="server" Text="商品描述:"></asp:Label>
            <br />
          
            <asp:TextBox ID="ProductDescriptionTextBox"  runat="server"  Height="200px" TextMode="MultiLine"
        Width="460px"></asp:TextBox>
   </div>
     <table class="edit">
            <tr>
             
                <td>
                      <asp:Button ID="SubmitButton" runat="server" Height="25px" OnClick="SubmitButton_Click"
        Text="确定提交" Width="98px" />
                </td>
                <td>
            
                </td>
            </tr>
        
        </table>
   </div>
        <div class="picture1">
            <asp:Label ID="Label10" runat="server" Text="商品图片:"></asp:Label>
          <br />
    <asp:FileUpload ID="ProductPictureUpload" runat="server" />
    &nbsp;
    <asp:Button ID="UploadPictureButton" runat="server" 
        onclick="UploadPictureButton_Click" Text="上传图片" />
        <br />
        <br />
        <asp:Image ID="ProductImage" runat="server" Height="230px" ImageAlign="Middle" 
            Width="330px" />
        </div>
  </div>
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
