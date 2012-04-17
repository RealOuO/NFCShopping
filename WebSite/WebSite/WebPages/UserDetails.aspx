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
            GridLines="Horizontal" Height="50px" Width="922px" DataKeyNames="userID" OnDataBound="UserDetailsView_DataBound">
            <AlternatingRowStyle BackColor="#F7F7F7" />
            <EditRowStyle BackColor="#738A9C" Font-Bold="True" ForeColor="#F7F7F7" />
            <Fields>
                <asp:BoundField DataField="userName" HeaderText="用户名" SortExpression="userName" />
                <asp:BoundField DataField="gender" HeaderText="性别" SortExpression="gender" />
                <asp:BoundField DataField="visitedTimes" HeaderText="访问次数" SortExpression="visitedTimes" />
                <asp:BoundField DataField="lastVisitedDate" HeaderText="最后来访时间" SortExpression="lastVisitedDate" />
                <asp:CommandField ShowDeleteButton="True" />
            </Fields>
            <FooterStyle BackColor="#B5C7DE" ForeColor="#4A3C8C" />
            <HeaderStyle BackColor="#4A3C8C" Font-Bold="True" ForeColor="#F7F7F7" />
            <PagerStyle BackColor="#E7E7FF" ForeColor="#4A3C8C" HorizontalAlign="Right" />
            <RowStyle BackColor="#E7E7FF" ForeColor="#4A3C8C" />
        </asp:DetailsView>
    </div>
    <asp:ObjectDataSource ID="ReviewsDataSource" runat="server" SelectMethod="GetReviewsByUserID"
        TypeName="NFCShoppingWebSite.BLL.ReviewBL">
        <SelectParameters>
            <asp:QueryStringParameter Name="uid" QueryStringField="userID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="ReviewListView" runat="server" DataSourceID="ReviewsDataSource">
        <EmptyDataTemplate>
            未返回数据。
        </EmptyDataTemplate>
        <ItemSeparatorTemplate>
            <br />
        </ItemSeparatorTemplate>
        <ItemTemplate>
            <li style="background-color: #FFFBD6; color: #333333;">评论过的商品:
                <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("Product.productName") %>' />
                <br />
                商品评论内容:<br />
                <asp:Label ID="commentLabel" runat="server" Text='<%# Eval("comment") %>' />
                <br />
                商品评分:
                <asp:Label ID="ratingLabel" runat="server" Text='<%# Eval("rating") %>' />
                <br />
                发布时间:
                <asp:Label ID="createAtLabel" runat="server" Text='<%# Eval("createAt") %>' />
                <br />
                <asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="删除" />
            </li>
        </ItemTemplate>
        <LayoutTemplate>
            <ul id="itemPlaceholderContainer" runat="server" style="">
                <li runat="server" id="itemPlaceholder" />
            </ul>
             <div style="text-align: center; background-color: #FFCC66; font-family: Verdana, Arial, Helvetica, sans-serif;
                            color: #333333;">
                            <asp:DataPager ID="DataPager" runat="server" PageSize="4">
                                <Fields>
                                    <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" ShowLastPageButton="True" />
                                </Fields>
                            </asp:DataPager>
                        </div>
        </LayoutTemplate>
    </asp:ListView>
</asp:Content>
