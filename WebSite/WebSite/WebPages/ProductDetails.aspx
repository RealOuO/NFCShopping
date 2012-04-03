<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductDetails.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.ProductDetails" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <style type="text/css">
        .style1
        {
            width: 430px;
        }
        .style2
        {
            width: 363px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
    <div style="height: 22px">
    </div>
    <div>
        <asp:Label ID="Label9" runat="server" Text="商品图片"></asp:Label>
    </div>
    <div>
    </div>
    <div>
        <asp:Image ID="ProductImage" runat="server" />
        <div>
        </div>
    </div>
    <asp:Label ID="Label1" runat="server" Text="商品名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductNameTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="商品描述"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label3" runat="server" Text="商品分类"></asp:Label>
    <div>
    </div>
    <div>
        <asp:Label ID="CategoryLabel" runat="server" Text="Label"></asp:Label>
        <div>
        </div>
    </div>
    <asp:Label ID="Label4" runat="server" Text="商品子分类"></asp:Label>
    <div>
    </div>
    <div>
        <asp:Label ID="SecCategoryLabel" runat="server" Text="Label"></asp:Label>
        <div>
        </div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="条形码"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BarcodeTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label6" runat="server" Text="价格"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="PriceTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label7" runat="server" Text="品牌"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BrandTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label8" runat="server" Text="产地"></asp:Label>
    <div>
    </div>
    <table style="width: 100%;">
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
            </td>
            <td>
                <asp:Button ID="DeleteButton" runat="server" Height="39px" PostBackUrl='~/WebPages/Products.aspx?secCategoryID=<%# Eval("secCategoryID") %>'
                    Text="删除" Width="105px" />
            </td>
        </tr>
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
    </table>
    <asp:TextBox ID="LocationTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="0" Name="id" QueryStringField="productID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="ReviewsDataSourse" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Review"
        DeleteMethod="DeleteReview" SelectMethod="GetReviewsByProductID" 
        TypeName="NFCShoppingWebSite.BLL.ReviewBL">
        <SelectParameters>
            <asp:QueryStringParameter Name="pid" QueryStringField="productID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="ListView1" runat="server" DataSourceID="ReviewsDataSourse" 
         AllowPaging ="true" DataKeyNames="reviewID">
        <EmptyDataTemplate>
            未返回数据。
        </EmptyDataTemplate>
        <ItemSeparatorTemplate>
            <br />
        </ItemSeparatorTemplate>
        <ItemTemplate>
            <li style="background-color: #DCDCDC;color: #000000;">reviewID:
                <asp:Label ID="reviewIDLabel" runat="server" Text='<%# Eval("reviewID") %>' />
                <br />
                userID:
                <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                <br />
                productID:
                <asp:Label ID="productIDLabel" runat="server" Text='<%# Eval("productID") %>' />
                <br />
                comment:
                <asp:Label ID="commentLabel" runat="server" Text='<%# Eval("comment") %>' />
                <br />
                rating:
                <asp:Label ID="ratingLabel" runat="server" Text='<%# Eval("rating") %>' />
                <br />
                createAt:
                <asp:Label ID="createAtLabel" runat="server" Text='<%# Eval("createAt") %>' />
                <br />
                Product:
                <asp:Label ID="ProductLabel" runat="server" Text='<%# Eval("Product") %>' />
                <br />
                ProductReference:
                <asp:Label ID="ProductReferenceLabel" runat="server" 
                    Text='<%# Eval("ProductReference") %>' />
                <br />
                User:
                <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User.userName") %>' />
                <br />
                UserReference:
                <asp:Label ID="UserReferenceLabel" runat="server" 
                    Text='<%# Eval("UserReference") %>' />
                <br />
                EntityState:
                <asp:Label ID="EntityStateLabel" runat="server" 
                    Text='<%# Eval("EntityState") %>' />
                <br />
                EntityKey:
                <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                <br />
                <asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="删除" />
            </li>
        </ItemTemplate>
        <LayoutTemplate>
            <ul ID="itemPlaceholderContainer" runat="server" 
                style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                <li runat="server" id="itemPlaceholder" />
            </ul>
            <div style="text-align: center;background-color: #CCCCCC;font-family: Verdana, Arial, Helvetica, sans-serif;color: #000000;">
            </div>
        </LayoutTemplate>
        <SelectedItemTemplate>
            <li style="background-color: #008A8C;font-weight: bold;color: #FFFFFF;">reviewID:
                <asp:Label ID="reviewIDLabel" runat="server" Text='<%# Eval("reviewID") %>' />
                <br />
                userID:
                <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                <br />
                productID:
                <asp:Label ID="productIDLabel" runat="server" Text='<%# Eval("productID") %>' />
                <br />
                comment:
                <asp:Label ID="commentLabel" runat="server" Text='<%# Eval("comment") %>' />
                <br />
                rating:
                <asp:Label ID="ratingLabel" runat="server" Text='<%# Eval("rating") %>' />
                <br />
                createAt:
                <asp:Label ID="createAtLabel" runat="server" Text='<%# Eval("createAt") %>' />
                <br />
                Product:
                <asp:Label ID="ProductLabel" runat="server" Text='<%# Eval("Product") %>' />
                <br />
                ProductReference:
                <asp:Label ID="ProductReferenceLabel" runat="server" 
                    Text='<%# Eval("ProductReference") %>' />
                <br />
                User:
                <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User") %>' />
                <br />
                UserReference:
                <asp:Label ID="UserReferenceLabel" runat="server" 
                    Text='<%# Eval("UserReference") %>' />
                <br />
                EntityState:
                <asp:Label ID="EntityStateLabel" runat="server" 
                    Text='<%# Eval("EntityState") %>' />
                <br />
                EntityKey:
                <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                <br />
                <asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="删除" />
            </li>
        </SelectedItemTemplate>
    </asp:ListView>
</asp:Content>
