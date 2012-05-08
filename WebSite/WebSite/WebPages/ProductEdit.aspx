<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.ProductEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
          <script type="text/javascript">
              $(function () {
                  $(".children:eq(0)").show();
                  $("span:eq(2)").html("-");
                  $("a:eq(5)").css({ "color": "red" });
                  $(".head:eq(0)").toggle(function () {
                      $(this).next().hide();
                      $("span:eq(2)").html("+");
                  }, function () {
                      $(this).next().show();
                      $("span:eq(2)").html("-");
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
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
    <div style="height: 22px">
    </div>
    <div>
        <asp:Label ID="Label10" runat="server" Text="商品图片"></asp:Label>
        <div>
        </div>
    </div>
    <div>
        <asp:Image ID="ProductImage" runat="server" Height="300px" ImageAlign="Middle" Width="450px" />
        <div>
        </div>
    </div>
    <asp:Label ID="Label1" runat="server" Text="商品名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductNameTextBox" runat="server">
    </asp:TextBox>
    <asp:RequiredFieldValidator runat="server" id="reqProductName" controltovalidate="ProductNameTextBox"
        errormessage="请输入商品名称" />
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="商品描述"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px">
    </asp:TextBox>
    <asp:RequiredFieldValidator runat="server" id="reqProductDescription" controltovalidate="ProductDescriptionTextBox"
        errormessage="请输入商品描述" />
    <div>
    </div>
    <asp:Label ID="Label3" runat="server" Text="商品分类"></asp:Label>
    <div>
    </div>
    <asp:DropDownList ID="CategoriesDropDownList" runat="server" AutoPostBack="True"
        DataSourceID="CategoriesDataSource" DataTextField="categoryName" DataValueField="categoryID"
        onprerender="CategoriesDropDownList_PreRender">
    </asp:DropDownList>
    <div>
    </div>
    <asp:Label ID="Label4" runat="server" Text="商品子分类"></asp:Label>
    <div>
    </div>
    <asp:DropDownList ID="SecCategoriesDropDownList" runat="server" DataSourceID="SecCategoriesDataSource"
        DataTextField="secCategoryName" DataValueField="secCategoryID" onprerender="SecCategoriesDropDownList_PreRender">
    </asp:DropDownList>
    <div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="条形码"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BarcodeTextBox" runat="server">
    </asp:TextBox>
    <asp:RequiredFieldValidator runat="server" id="reqBarcode" controltovalidate="BarcodeTextBox"
        errormessage="请输入商品条形码" />
    <div>
    </div>
    <asp:Label ID="Label6" runat="server" Text="价格"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="PriceTextBox" runat="server">
    </asp:TextBox>
    <asp:RequiredFieldValidator runat="server" id="reqPrice" controltovalidate="PriceTextBox"
        errormessage="请输入商品价格信息" />
    <div>
        <asp:RangeValidator ID="RangePriceValidator" runat="server" ErrorMessage="请输入一个合理的价格"
            ControlToValidate="PriceTextBox" MaximumValue="10000.0" MinimumValue="0.0" Type="Double">
        </asp:RangeValidator>
        <br />
    </div>
    <asp:Label ID="Label7" runat="server" Text="品牌"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BrandTextBox" runat="server">
    </asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label8" runat="server" Text="产地"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="LocationTextBox" runat="server">
    </asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label9" runat="server" Text="商品图片"></asp:Label>
    <div>
    </div>
    <asp:FileUpload ID="ProductPictureUpload" runat="server" />
    <asp:Button ID="UploadPictureButton" runat="server" onclick="UploadPictureButton_Click"
        Text="上传图片" />
    <div>
    </div>
    <asp:Button ID="SubmitButton" runat="server" Height="25px" OnClick="SubmitButton_Click"
        Text="确定提交" Width="98px" />
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <selectparameters>
            <asp:QueryStringParameter DefaultValue="0" Name="id" 
                QueryStringField="productID" Type="Int32" />
        </selectparameters>
        <updateparameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </updateparameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="CategoriesDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Category"
        DeleteMethod="DeleteCategory" InsertMethod="InsertCategory" SelectMethod="GetCategories"
        TypeName="NFCShoppingWebSite.BLL.CategoryBL" UpdateMethod="UpdateCategory">
        <updateparameters>
            <asp:Parameter Name="category" Type="Object" />
            <asp:Parameter Name="origCategory" Type="Object" />
        </updateparameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="SecCategoriesDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.SecCategory"
        DeleteMethod="DeleteSecCategory" InsertMethod="InsertSecCategory" SelectMethod="GetSecCategoriesByCategory"
        TypeName="NFCShoppingWebSite.BLL.SecCategoryBL" UpdateMethod="UpdateSecCategory">
        <selectparameters>
            <asp:ControlParameter ControlID="CategoriesDropDownList" DefaultValue="0" Name="categoryID"
                PropertyName="SelectedValue" Type="Int32" />
        </selectparameters>
        <updateparameters>
            <asp:Parameter Name="secCategory" Type="Object" />
            <asp:Parameter Name="origSecCategory" Type="Object" />
        </updateparameters>
    </asp:ObjectDataSource>
</asp:Content>
