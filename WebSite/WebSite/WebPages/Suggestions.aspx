<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true" CodeBehind="Suggestions.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Suggestions" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="SuggestionDataSource" runat="server" 
    DataObjectTypeName="NFCShoppingWebSite.Access_Data.Suggestion" 
    DeleteMethod="DeleteSuggestion" SelectMethod="GetSuggestions" 
    TypeName="NFCShoppingWebSite.BLL.SuggestionBL"></asp:ObjectDataSource>
<div>
    <asp:ListView ID="ListView1" runat="server" DataSourceID="SuggestionDataSource">
        <AlternatingItemTemplate>
            <li style="background-color: #FFF8DC;">id:
                <asp:Label ID="idLabel" runat="server" Text='<%# Eval("id") %>' />
                <br />
                userID:
                <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                <br />
                description:
                <asp:Label ID="descriptionLabel" runat="server" 
                    Text='<%# Eval("description") %>' />
                <br />
                date:
                <asp:Label ID="dateLabel" runat="server" Text='<%# Eval("date") %>' />
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
            </li>
        </AlternatingItemTemplate>
        <EditItemTemplate>
            <li style="background-color: #008A8C; color: #FFFFFF;">id:
                <asp:TextBox ID="idTextBox" runat="server" Text='<%# Bind("id") %>' />
                <br />
                userID:
                <asp:TextBox ID="userIDTextBox" runat="server" Text='<%# Bind("userID") %>' />
                <br />
                description:
                <asp:TextBox ID="descriptionTextBox" runat="server" 
                    Text='<%# Bind("description") %>' />
                <br />
                date:
                <asp:TextBox ID="dateTextBox" runat="server" Text='<%# Bind("date") %>' />
                <br />
                User:
                <asp:TextBox ID="UserTextBox" runat="server" Text='<%# Bind("User") %>' />
                <br />
                UserReference:
                <asp:TextBox ID="UserReferenceTextBox" runat="server" 
                    Text='<%# Bind("UserReference") %>' />
                <br />
                EntityState:
                <asp:TextBox ID="EntityStateTextBox" runat="server" 
                    Text='<%# Bind("EntityState") %>' />
                <br />
                EntityKey:
                <asp:TextBox ID="EntityKeyTextBox" runat="server" 
                    Text='<%# Bind("EntityKey") %>' />
                <br />
                <asp:Button ID="UpdateButton" runat="server" CommandName="Update" Text="更新" />
                <asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="取消" />
            </li>
        </EditItemTemplate>
        <EmptyDataTemplate>
            未返回数据。
        </EmptyDataTemplate>
        <InsertItemTemplate>
            <li style="">id:
                <asp:TextBox ID="idTextBox" runat="server" Text='<%# Bind("id") %>' />
                <br />userID:
                <asp:TextBox ID="userIDTextBox" runat="server" Text='<%# Bind("userID") %>' />
                <br />description:
                <asp:TextBox ID="descriptionTextBox" runat="server" 
                    Text='<%# Bind("description") %>' />
                <br />date:
                <asp:TextBox ID="dateTextBox" runat="server" Text='<%# Bind("date") %>' />
                <br />User:
                <asp:TextBox ID="UserTextBox" runat="server" Text='<%# Bind("User") %>' />
                <br />UserReference:
                <asp:TextBox ID="UserReferenceTextBox" runat="server" 
                    Text='<%# Bind("UserReference") %>' />
                <br />EntityState:
                <asp:TextBox ID="EntityStateTextBox" runat="server" 
                    Text='<%# Bind("EntityState") %>' />
                <br />EntityKey:
                <asp:TextBox ID="EntityKeyTextBox" runat="server" 
                    Text='<%# Bind("EntityKey") %>' />
                <br />
                <asp:Button ID="InsertButton" runat="server" CommandName="Insert" Text="插入" />
                <asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="清除" />
            </li>
        </InsertItemTemplate>
        <ItemSeparatorTemplate>
<br />
        </ItemSeparatorTemplate>
        <ItemTemplate>
            <li style="background-color: #DCDCDC; color: #000000;">id:
                <asp:Label ID="idLabel" runat="server" Text='<%# Eval("id") %>' />
                <br />
                userID:
                <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                <br />
                description:
                <asp:Label ID="descriptionLabel" runat="server" 
                    Text='<%# Eval("description") %>' />
                <br />
                date:
                <asp:Label ID="dateLabel" runat="server" Text='<%# Eval("date") %>' />
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
            </li>
        </ItemTemplate>
        <LayoutTemplate>
            <ul ID="itemPlaceholderContainer" runat="server" 
                style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                <li runat="server" id="itemPlaceholder" />
            </ul>
            <div style="text-align: center;background-color: #CCCCCC; font-family: Verdana, Arial, Helvetica, sans-serif;color: #000000;">
                <asp:DataPager ID="DataPager1" runat="server" PageSize="5">
                    <Fields>
                        <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" 
                            ShowLastPageButton="True" />
                    </Fields>
                </asp:DataPager>
            </div>
        </LayoutTemplate>
        <SelectedItemTemplate>
            <li style="background-color: #008A8C; font-weight: bold;color: #FFFFFF;">id:
                <asp:Label ID="idLabel" runat="server" Text='<%# Eval("id") %>' />
                <br />
                userID:
                <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                <br />
                description:
                <asp:Label ID="descriptionLabel" runat="server" 
                    Text='<%# Eval("description") %>' />
                <br />
                date:
                <asp:Label ID="dateLabel" runat="server" Text='<%# Eval("date") %>' />
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
            </li>
        </SelectedItemTemplate>
    </asp:ListView>
</div>
</asp:Content>
