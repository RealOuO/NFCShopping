<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="UserDetails.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.UserDetails" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="UserDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.User"
        DeleteMethod="DeleteUser" SelectMethod="GetUserByID" TypeName="NFCShoppingWebSite.BLL.UserBL">
        <SelectParameters>
            <asp:QueryStringParameter Name="id" QueryStringField="userID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <div>
    <asp:DetailsView ID="UserDetailsView" runat="server" AutoGenerateRows="False" BackColor="White"
        BorderColor="#E7E7FF" BorderStyle="None" BorderWidth="1px" CellPadding="3" DataSourceID="UserDataSource"
        GridLines="Horizontal" Height="50px" Width="125px">
        <AlternatingRowStyle BackColor="#F7F7F7" />
        <EditRowStyle BackColor="#738A9C" Font-Bold="True" ForeColor="#F7F7F7" />
        <Fields>
            <asp:BoundField DataField="userID" HeaderText="userID" SortExpression="userID" />
            <asp:BoundField DataField="userName" HeaderText="userName" SortExpression="userName" />
            <asp:BoundField DataField="gender" HeaderText="gender" SortExpression="gender" />
            <asp:BoundField DataField="visitedTimes" HeaderText="visitedTimes" SortExpression="visitedTimes" />
            <asp:BoundField DataField="lastVisitedDate" HeaderText="lastVisitedDate" SortExpression="lastVisitedDate" />
            <asp:CommandField ShowDeleteButton="True" />
        </Fields>
        <FooterStyle BackColor="#B5C7DE" ForeColor="#4A3C8C" />
        <HeaderStyle BackColor="#4A3C8C" Font-Bold="True" ForeColor="#F7F7F7" />
        <PagerStyle BackColor="#E7E7FF" ForeColor="#4A3C8C" HorizontalAlign="Right" />
        <RowStyle BackColor="#E7E7FF" ForeColor="#4A3C8C" />
    </asp:DetailsView>
    </div>
    <asp:ObjectDataSource ID="ReviewDataSource" runat="server" SelectMethod="GetReviewsByUserID"
        TypeName="NFCShoppingWebSite.BLL.ReviewBL">
        <SelectParameters>
            <asp:QueryStringParameter Name="uid" QueryStringField="userID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="ReviewListView" runat="server" 
    DataSourceID="ReviewDataSource">
        <AlternatingItemTemplate>
            <li style="">reviewID:
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
                <asp:Label ID="ProductReferenceLabel" runat="server" Text='<%# Eval("ProductReference") %>' />
                <br />
                User:
                <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User") %>' />
                <br />
                UserReference:
                <asp:Label ID="UserReferenceLabel" runat="server" Text='<%# Eval("UserReference") %>' />
                <br />
                EntityState:
                <asp:Label ID="EntityStateLabel" runat="server" Text='<%# Eval("EntityState") %>' />
                <br />
                EntityKey:
                <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                <br />
            </li>
        </AlternatingItemTemplate>
        <EditItemTemplate>
            <li style="">reviewID:
                <asp:TextBox ID="reviewIDTextBox" runat="server" Text='<%# Bind("reviewID") %>' />
                <br />
                userID:
                <asp:TextBox ID="userIDTextBox" runat="server" Text='<%# Bind("userID") %>' />
                <br />
                productID:
                <asp:TextBox ID="productIDTextBox" runat="server" Text='<%# Bind("productID") %>' />
                <br />
                comment:
                <asp:TextBox ID="commentTextBox" runat="server" Text='<%# Bind("comment") %>' />
                <br />
                rating:
                <asp:TextBox ID="ratingTextBox" runat="server" Text='<%# Bind("rating") %>' />
                <br />
                createAt:
                <asp:TextBox ID="createAtTextBox" runat="server" Text='<%# Bind("createAt") %>' />
                <br />
                Product:
                <asp:TextBox ID="ProductTextBox" runat="server" Text='<%# Bind("Product") %>' />
                <br />
                ProductReference:
                <asp:TextBox ID="ProductReferenceTextBox" runat="server" Text='<%# Bind("ProductReference") %>' />
                <br />
                User:
                <asp:TextBox ID="UserTextBox" runat="server" Text='<%# Bind("User") %>' />
                <br />
                UserReference:
                <asp:TextBox ID="UserReferenceTextBox" runat="server" Text='<%# Bind("UserReference") %>' />
                <br />
                EntityState:
                <asp:TextBox ID="EntityStateTextBox" runat="server" Text='<%# Bind("EntityState") %>' />
                <br />
                EntityKey:
                <asp:TextBox ID="EntityKeyTextBox" runat="server" Text='<%# Bind("EntityKey") %>' />
                <br />
                <asp:Button ID="UpdateButton" runat="server" CommandName="Update" Text="更新" />
                <asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="取消" />
            </li>
        </EditItemTemplate>
        <EmptyDataTemplate>
            未返回数据。
        </EmptyDataTemplate>
        <InsertItemTemplate>
            <li style="">reviewID:
                <asp:TextBox ID="reviewIDTextBox" runat="server" Text='<%# Bind("reviewID") %>' />
                <br />
                userID:
                <asp:TextBox ID="userIDTextBox" runat="server" Text='<%# Bind("userID") %>' />
                <br />
                productID:
                <asp:TextBox ID="productIDTextBox" runat="server" Text='<%# Bind("productID") %>' />
                <br />
                comment:
                <asp:TextBox ID="commentTextBox" runat="server" Text='<%# Bind("comment") %>' />
                <br />
                rating:
                <asp:TextBox ID="ratingTextBox" runat="server" Text='<%# Bind("rating") %>' />
                <br />
                createAt:
                <asp:TextBox ID="createAtTextBox" runat="server" Text='<%# Bind("createAt") %>' />
                <br />
                Product:
                <asp:TextBox ID="ProductTextBox" runat="server" Text='<%# Bind("Product") %>' />
                <br />
                ProductReference:
                <asp:TextBox ID="ProductReferenceTextBox" runat="server" Text='<%# Bind("ProductReference") %>' />
                <br />
                User:
                <asp:TextBox ID="UserTextBox" runat="server" Text='<%# Bind("User") %>' />
                <br />
                UserReference:
                <asp:TextBox ID="UserReferenceTextBox" runat="server" Text='<%# Bind("UserReference") %>' />
                <br />
                EntityState:
                <asp:TextBox ID="EntityStateTextBox" runat="server" Text='<%# Bind("EntityState") %>' />
                <br />
                EntityKey:
                <asp:TextBox ID="EntityKeyTextBox" runat="server" Text='<%# Bind("EntityKey") %>' />
                <br />
                <asp:Button ID="InsertButton" runat="server" CommandName="Insert" Text="插入" />
                <asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="清除" />
            </li>
        </InsertItemTemplate>
        <ItemSeparatorTemplate>
            <br />
        </ItemSeparatorTemplate>
        <ItemTemplate>
            <li style="">reviewID:
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
                <asp:Label ID="ProductReferenceLabel" runat="server" Text='<%# Eval("ProductReference") %>' />
                <br />
                User:
                <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User") %>' />
                <br />
                UserReference:
                <asp:Label ID="UserReferenceLabel" runat="server" Text='<%# Eval("UserReference") %>' />
                <br />
                EntityState:
                <asp:Label ID="EntityStateLabel" runat="server" Text='<%# Eval("EntityState") %>' />
                <br />
                EntityKey:
                <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                <br />
            </li>
        </ItemTemplate>
        <LayoutTemplate>
            <ul id="itemPlaceholderContainer" runat="server" style="">
                <li runat="server" id="itemPlaceholder" />
            </ul>
            <div style="">
            </div>
        </LayoutTemplate>
        <SelectedItemTemplate>
            <li style="">reviewID:
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
                <asp:Label ID="ProductReferenceLabel" runat="server" Text='<%# Eval("ProductReference") %>' />
                <br />
                User:
                <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User") %>' />
                <br />
                UserReference:
                <asp:Label ID="UserReferenceLabel" runat="server" Text='<%# Eval("UserReference") %>' />
                <br />
                EntityState:
                <asp:Label ID="EntityStateLabel" runat="server" Text='<%# Eval("EntityState") %>' />
                <br />
                EntityKey:
                <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                <br />
            </li>
        </SelectedItemTemplate>
    </asp:ListView>
</asp:Content>
