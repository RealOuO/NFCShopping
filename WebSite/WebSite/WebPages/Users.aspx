<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Users.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Users" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(function () {
            $(".children:eq(1)").show();
            $("span:eq(1)").html("-");
            $("a:eq(6)").css({ "color": "red" });
            $(".head:eq(1)").toggle(function () {
                $(this).next().hide();
                $("span:eq(1)").html("+");
            }, function () {
                $(this).next().show();
                $("span:eq(1)").html("-");
            });
        });

    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h1 style="font-size:X-Large;font-weight:bold; color:Black;">
        <b>查看用户</b></h1>
        <br />
    <asp:ObjectDataSource ID="UsersDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.User"
        DeleteMethod="DeleteUser" SelectMethod="GetUsers" TypeName="NFCShoppingWebSite.BLL.UserBL">
    </asp:ObjectDataSource>
    <asp:ValidationSummary ID="DepartmentsValidationSummary" runat="server" ShowSummary="true"
        DisplayMode="BulletList" Style="color: Red; width: 40em;" />
    <asp:GridView ID="GridView" runat="server" AllowPaging="True" AutoGenerateColumns="False"
        DataSourceID="UsersDataSource" DataKeyNames="userID" CellPadding="4" ForeColor="#333333"
        GridLines="None" Width="740px" OnRowDataBound="GridView_RowDataBound">
        <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
        <Columns>
            <asp:BoundField DataField="userID" HeaderText="userID" SortExpression="userID" Visible="False" />
            <asp:HyperLinkField DataNavigateUrlFields="userID" DataNavigateUrlFormatString="UserDetails.aspx?userID={0}"
                DataTextField="userName" HeaderText="用户名">
                <ItemStyle HorizontalAlign="center" />
            </asp:HyperLinkField>
            <asp:BoundField DataField="gender" HeaderText="性别" SortExpression="gender" />
            <asp:BoundField DataField="visitedTimes" HeaderText="有效访问次数" 
                SortExpression="visitedTimes" />
            <asp:BoundField DataField="lastVisitedDate" HeaderText="最后到访时间" 
                SortExpression="lastVisitedDate" />
            <asp:CommandField ShowDeleteButton="True" />
        </Columns>
        <EditRowStyle BackColor="#999999" />
        <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" HorizontalAlign="Right" />
        <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" HorizontalAlign="Center" />
        <PagerStyle BackColor="#284775" ForeColor="White" />
        <RowStyle BackColor="#F7F6F3" ForeColor="#333333" HorizontalAlign="Center" />
        <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
        <SortedAscendingCellStyle BackColor="#E9E7E2" />
        <SortedAscendingHeaderStyle BackColor="#506C8C" />
        <SortedDescendingCellStyle BackColor="#FFFDF8" />
        <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
    </asp:GridView>
</asp:Content>
