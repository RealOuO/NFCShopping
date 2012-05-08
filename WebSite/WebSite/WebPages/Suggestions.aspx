<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Suggestions.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Suggestions" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(function () {
            $("a:eq(13)").css({ "color": "red" });
        });
    </script>
    <link href="../css/Suggestion.css" rel="stylesheet" type="text/css" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="SuggestionDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Suggestion"
        DeleteMethod="DeleteSuggestion" SelectMethod="GetSuggestions" 
        TypeName="NFCShoppingWebSite.BLL.SuggestionBL" InsertMethod="InsertSuggestion">
    </asp:ObjectDataSource>
    <asp:ScriptManager ID="ScriptManager" runat="server">
    </asp:ScriptManager>
    <h1 style="font-size: X-Large; font-weight: bold; color: Black;">
        <b>意见反馈</b></h1>
    <asp:UpdatePanel ID="UpdatePanel" runat="server">
        <ContentTemplate>
            <asp:ListView ID="ListView1" runat="server" DataSourceID="SuggestionDataSource">
                <EmptyDataTemplate>
                    未返回数据。
                </EmptyDataTemplate>
                <ItemSeparatorTemplate>
                    <br />
                </ItemSeparatorTemplate>
                <ItemTemplate>
                    <li class="sugestion">用户名:
                        <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User.userName") %>' />
                        &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; 发布时间:
                        <asp:Label ID="dateLabel" runat="server" Text='<%# Eval("date") %>' />
                        <br />
                        反馈内容:<br />
                        <asp:Label ID="descriptionLabel" runat="server" Text='<%# Eval("description") %>' />
                        <br />
                    </li>
                </ItemTemplate>
                <LayoutTemplate>
                    <ul id="itemPlaceholderContainer" runat="server" style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                        <li runat="server" id="itemPlaceholder" />
                    </ul>
                    <div style="margin-left: 70px; font-family: Verdana, Arial, Helvetica, sans-serif;
                        color: #000000;">
                        <asp:DataPager ID="DataPager1" runat="server" PageSize="5">
                            <Fields>
                                <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" ShowLastPageButton="True" />
                            </Fields>
                        </asp:DataPager>
                    </div>
                </LayoutTemplate>
            </asp:ListView>
        </ContentTemplate>
    </asp:UpdatePanel>
    </b>
</asp:Content>
