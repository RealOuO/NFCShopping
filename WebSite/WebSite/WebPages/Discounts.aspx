<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Discounts.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Discounts" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Size="XX-Large" ForeColor="Black"
        Text="优惠"></asp:Label>
    <a href='DiscountAdd.aspx'>添加优惠项 </a>
    <asp:ObjectDataSource ID="DiscountDataSource" runat="server" SelectMethod="GetDiscounts"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" UpdateMethod="UpdateDiscount">
        <UpdateParameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="ListView1" runat="server" DataKeyNames="discountID" DataSourceID="DiscountDataSource"
        GroupItemCount="2">
        <EmptyDataTemplate>
            <table id="Table1" runat="server">
                <tr>
                    <td>
                        该类中没有任何优惠。
                    </td>
                </tr>
            </table>
        </EmptyDataTemplate>
        <EmptyItemTemplate>
            <td id="Td1" runat="server" />
        </EmptyItemTemplate>
        <GroupTemplate>
            <tr id="itemPlaceholderContainer" runat="server">
                <td id="itemPlaceholder" runat="server">
                </td>
            </tr>
        </GroupTemplate>
        <ItemTemplate>
            <td id="Td2" runat="server">
                <table border="0" width="300">
                    <tr>
                        <td style="width: 25px;">
                            &nbsp
                        </td>
                        <td style="width: 250px; vertical-align: middle;">
                            <%# Eval("discountID") %><br />
                            <a href='DiscountItems.aspx?discountID=<%# Eval("discountID") %>'>
                                <%# Eval("description", "{0:c}") %><br />
                            </a><a href='DiscountEdit.aspx?discountID=<%# Eval("discountID") %>'>编辑优惠<br />
                        </td>
                    </tr>
                </table>
            </td>
        </ItemTemplate>
    </asp:ListView>
</asp:Content>
