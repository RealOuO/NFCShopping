<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.ProductEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" 
    Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
    <div style="height: 22px">
    </div>
    <asp:Label ID="Label1" runat="server" Text="商品名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductNameTextBox" runat="server"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="商品描述"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label3" runat="server" Text="商品分类"></asp:Label>
    <div>
    </div>
    <asp:DropDownList ID="CategoriesDropDownList" runat="server" AutoPostBack="True"
        DataSourceID="CategoriesDataSource" DataTextField="categoryName" DataValueField="categoryID">
    </asp:DropDownList>
    <div>
    </div>
    <asp:Label ID="Label4" runat="server" Text="商品子分类"></asp:Label>
    <div>
    </div>
    <asp:DropDownList ID="SecCategoriesDropDownList" runat="server" DataSourceID="SecCategoriesDataSource"
        DataTextField="secCategoryName" DataValueField="secCategoryID">
    </asp:DropDownList>
    <div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="条形码"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BarcodeTextBox" runat="server"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label6" runat="server" Text="价格"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="PriceTextBox" runat="server"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label7" runat="server" Text="品牌"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BrandTextBox" runat="server"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label8" runat="server" Text="产地"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="LocationTextBox" runat="server"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label9" runat="server" Text="商品图片"></asp:Label>
    <div>
    </div>
    <asp:FileUpload ID="ProductPictureUpload" runat="server" />
    <div>
    </div>
    <asp:Button ID="SubmitButton" runat="server" Height="48px" OnClick="SubmitButton_Click"
        Text="确定提交" Width="136px" />
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:SessionParameter DefaultValue="0" Name="id" SessionField="productID" Type="Int32" />
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
