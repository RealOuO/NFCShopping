<%@ Page Title="主页" Language="C#" MasterPageFile="~/Styles/Site.master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="NFCShoppingWebSite._Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>
        签到用户TOP10</h2>
    <p>
        若要了解关于 ASP.NET 的详细信息，请访问 <a href="http://www.asp.net/cn" title="ASP.NET 网站">www.asp.net/cn</a>。
     
        <asp:ObjectDataSource ID="Top10UsersDataSource" runat="server" 
            SelectMethod="GetTop10Users" TypeName="NFCShoppingWebSite.BLL.UserBL">
        </asp:ObjectDataSource>
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            CellPadding="4" DataSourceID="Top10UsersDataSource" ForeColor="#333333" 
            GridLines="None" Width="808px">
            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
            <Columns>
                <asp:BoundField DataField="userID" HeaderText="userID" 
                    SortExpression="userID" />
                <asp:BoundField DataField="userName" HeaderText="userName" 
                    SortExpression="userName" />
                <asp:BoundField DataField="userPassword" HeaderText="userPassword" 
                    SortExpression="userPassword" />
                <asp:BoundField DataField="gender" HeaderText="gender" 
                    SortExpression="gender" />
                <asp:BoundField DataField="visitedTimes" HeaderText="visitedTimes" 
                    SortExpression="visitedTimes" />
                <asp:BoundField DataField="lastVisitedDate" HeaderText="lastVisitedDate" 
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
    <p>
        最受欢迎商品TOP10<asp:ObjectDataSource ID="Top10ProductsDataSource" runat="server" 
            SelectMethod="GetTOP10Products" TypeName="NFCShoppingWebSite.BLL.ReviewBL">
        </asp:ObjectDataSource>
        <asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" 
            DataSourceID="Top10ProductsDataSource">
        </asp:GridView>
    </p>
</asp:Content>
