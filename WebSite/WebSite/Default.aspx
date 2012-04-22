<%@ Page Title="主页" Language="C#" MasterPageFile="~/Styles/Site.master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="NFCShoppingWebSite._Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2 style="font-size:X-Large;font-weight:bold; color:Black;">
        签到用户TOP10</h2>
    <p>
        <asp:ObjectDataSource ID="Top10UsersDataSource" runat="server" SelectMethod="GetTop10Users"
            TypeName="NFCShoppingWebSite.BLL.UserBL"></asp:ObjectDataSource>
        <asp:GridView ID="Top10UsersGridView" runat="server" AutoGenerateColumns="False"
            CellPadding="4" DataSourceID="Top10UsersDataSource" ForeColor="#333333" GridLines="None"
            Width="703px">
            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
            <Columns>
                <asp:BoundField DataField="userID" HeaderText="用户ID" SortExpression="userID" />
                <asp:HyperLinkField DataNavigateUrlFields="userID" DataNavigateUrlFormatString="WebPages/UserDetails.aspx?userID={0}"
                    DataTextField="userName" HeaderText="用户名" />
                <asp:BoundField DataField="visitedTimes" HeaderText="访问次数" 
                    SortExpression="visitedTimes" />
                <asp:BoundField DataField="lastVisitedDate" HeaderText="最后到访时间" 
                    SortExpression="lastVisitedDate" />
            </Columns>
            <EditRowStyle BackColor="#999999" />
            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#E9E7E2" />
            <SortedAscendingHeaderStyle BackColor="#506C8C" />
            <SortedDescendingCellStyle BackColor="#FFFDF8" />
            <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
        </asp:GridView>
    </p>
    <h2  style="font-size:X-Large;font-weight:bold; color:Black;">
        最受欢迎商品TOP10
    </h2>
    <p>
        <asp:ObjectDataSource ID="Top10ProductsDataSource" runat="server" SelectMethod="GetTOP10Products"
            TypeName="NFCShoppingWebSite.BLL.ReviewBL"></asp:ObjectDataSource>
        <asp:GridView ID="Top10ProductsGridView" runat="server" AutoGenerateColumns="False"
            DataSourceID="Top10ProductsDataSource" DataKeyNames="Key" CellPadding="4" ForeColor="#333333"
            GridLines="None" Width="700px">
            <AlternatingRowStyle BackColor="White" />
            <Columns>
                <asp:BoundField DataField="Key" HeaderText="商品id" />
                <asp:HyperLinkField DataNavigateUrlFields="Key" DataNavigateUrlFormatString="WebPages/productDetails.aspx?productID={0}"
                    DataTextField="Name" HeaderText="商品名" />
                <asp:BoundField DataField="AverageRating" HeaderText="平均分" />
            </Columns>
            <EditRowStyle BackColor="#2461BF" />
            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#EFF3FB" />
            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#F5F7FB" />
            <SortedAscendingHeaderStyle BackColor="#6D95E1" />
            <SortedDescendingCellStyle BackColor="#E9EBEF" />
            <SortedDescendingHeaderStyle BackColor="#4870BE" />
        </asp:GridView>
    </p>
</asp:Content>
