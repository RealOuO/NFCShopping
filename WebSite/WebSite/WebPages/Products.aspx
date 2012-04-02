<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true" CodeBehind="Products.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Products" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Size="XX-Large" 
        ForeColor="Black" Text="商品"></asp:Label>
    <div>
    </div>
    <asp:GridView ID="ProductsGridView" runat="server" AutoGenerateColumns="False" 
        DataSourceID="ProductsDataSource" DataKeyNames="productID">
        <Columns>
            <asp:CommandField ShowDeleteButton="True" />
            <asp:BoundField DataField="productID" HeaderText="productID" 
                SortExpression="productID" />
            <asp:BoundField DataField="secCategoryID" HeaderText="secCategoryID" 
                SortExpression="secCategoryID" />
            <asp:BoundField DataField="barCode" HeaderText="barCode" 
                SortExpression="barCode" />
            <asp:BoundField DataField="productName" HeaderText="productName" 
                SortExpression="productName" />
            <asp:BoundField DataField="price" HeaderText="price" SortExpression="price" />
            <asp:BoundField DataField="brand" HeaderText="brand" SortExpression="brand" />
            <asp:BoundField DataField="location" HeaderText="location" 
                SortExpression="location" />
            <asp:BoundField DataField="imageURL" HeaderText="imageURL" 
                SortExpression="imageURL" />
            <asp:BoundField DataField="description" HeaderText="description" 
                SortExpression="description" />
        </Columns>
    </asp:GridView>
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" 
        DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product" 
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" 
        SelectMethod="GetProductsBySecCategory" 
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:SessionParameter DefaultValue="4" Name="secCategoryID" 
                SessionField="secCategoryID" Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
</asp:Content>
