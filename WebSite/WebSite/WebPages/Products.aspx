<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Products.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Products" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Size="XX-Large" ForeColor="Black"
        Text="商品"></asp:Label>
    <div>
    </div>
    <asp:ListView ID="ProductsListView" runat="server" DataKeyNames="productID" DataSourceID="ProductsDataSource"
        GroupItemCount="2">
        <EmptyDataTemplate>
            <table runat="server">
                <tr>
                    <td>
                        该类中没有任何商品。
                    </td>
                </tr>
            </table>
        </EmptyDataTemplate>
        <EmptyItemTemplate>
            <td runat="server" />
        </EmptyItemTemplate>
        <GroupTemplate>
            <tr id="itemPlaceholderContainer" runat="server">
                <td id="itemPlaceholder" runat="server">
                </td>
            </tr>
        </GroupTemplate>
        <ItemTemplate>
            <td runat="server">
                <table border="0" width="300">
                    <tr>
                        <td style="width: 25px;">
                            &nbsp
                        </td>
                        <td style="vertical-align: middle; text-align: right;">
                            <a href='ProductDetails.aspx?productID=<%# Eval("productID") %>'>
                                <image src='../Images/Products/<%# Eval("imageURL") %>' width="100" height="75" border="0">
                            </a>&nbsp;&nbsp
                        </td>
                        <td style="width: 250px; vertical-align: middle;">
                            <a href='ProductDetails.aspx?productID=<%# Eval("productID") %>'>
                                <%# Eval("productName") %><br />
                            </a>
                            <%# Eval("price", "{0:c}") %>元<br />
                            <a href='EditDiscountItem.aspx?productID=<%# Eval("productID") %>'>
                            设置优惠
                            </a>
                        </td>
                    </tr>
                </table>
            </td>
        </ItemTemplate>
    </asp:ListView>
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProductsBySecCategory"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="4" Name="secCategoryID" QueryStringField="secCategoryID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
</asp:Content>
