<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Users.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Users" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="UsersDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.User"
        DeleteMethod="DeleteUser" SelectMethod="GetUsers" TypeName="NFCShoppingWebSite.BLL.UserBL" OnDeleted="DepartmentsObjectDataSource_Deleted">
    </asp:ObjectDataSource>
     <asp:ValidationSummary ID="DepartmentsValidationSummary" runat="server" ShowSummary="true"
        DisplayMode="BulletList" Style="color: Red; width: 40em;" />
    <asp:GridView ID="GridView" runat="server" AllowPaging="True" AutoGenerateColumns="False"
        DataSourceID="UsersDataSource" CellPadding="4" ForeColor="#333333" GridLines="None">
        <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
        <Columns>
            <asp:CommandField ShowDeleteButton="True" />
            <asp:BoundField DataField="userID" HeaderText="userID" SortExpression="userID" 
                Visible="False" />
            <asp:BoundField DataField="userName" HeaderText="userName" 
                SortExpression="userName" />
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
</asp:Content>
