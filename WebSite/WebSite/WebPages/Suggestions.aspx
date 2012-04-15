<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Suggestions.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Suggestions" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(function () {
            $("a:eq(11)").css({ "color": "red" });
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="SuggestionDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Suggestion"
        DeleteMethod="DeleteSuggestion" SelectMethod="GetSuggestions" TypeName="NFCShoppingWebSite.BLL.SuggestionBL">
    </asp:ObjectDataSource>
    <h1 style="color: Black;">
        <b>意见反馈</b></h1>
    <div>
        <asp:ListView ID="ListView1" runat="server" DataSourceID="SuggestionDataSource">
            <EmptyDataTemplate>
                未返回数据。
            </EmptyDataTemplate>
            <ItemSeparatorTemplate>
                <br />
            </ItemSeparatorTemplate>
            <ItemTemplate>
                <li>User:
                    <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User.userName") %>' />
                    <br />
                    description:
                    <asp:Label ID="descriptionLabel" runat="server" Text='<%# Eval("description") %>' />
                    <br />
                    date:
                    <asp:Label ID="dateLabel" runat="server" Text='<%# Eval("date") %>' />
                    <br />
                </li>
            </ItemTemplate>
            <LayoutTemplate>
                <ul id="itemPlaceholderContainer" runat="server" style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                    <li runat="server" id="itemPlaceholder" />
                </ul>
                <div style="text-align: center; background-color: #CCCCCC; font-family: Verdana, Arial, Helvetica, sans-serif;
                    color: #000000;">
                    <asp:DataPager ID="DataPager1" runat="server" PageSize="5">
                        <Fields>
                            <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" ShowLastPageButton="True" />
                        </Fields>
                    </asp:DataPager>
                </div>
            </LayoutTemplate>
        </asp:ListView>
    </div>
</asp:Content>
