<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountItems.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountItems" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <a href='DiscountItemAdd.aspx?discountID=<%# Eval("discountID") %>'>增加优惠 </a>
    <asp:ObjectDataSource ID="DiscountItemDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.DiscountItem"
        DeleteMethod="DeleteDiscountItem" InsertMethod="InsertDiscountItem" SelectMethod="GetDiscountItemsByDiscountID"
        TypeName="NFCShoppingWebSite.BLL.DiscountItemBL" UpdateMethod="UpdateDiscountItem">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="1" Name="discountID" QueryStringField="discountID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discountItem" Type="Object" />
            <asp:Parameter Name="origDiscountItem" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="ListView1" runat="server" DataKeyNames="id" DataSourceID="DiscountItemDataSource"
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
                            <%# Eval("productID") %><br />                           
                            <%# Eval("description", "{0:c}") %><br />
                            <%# Eval("discountPercent") %><br />
                            <%# Eval("startDate") %><br />
                            <%# Eval("endDate") %><br />
                            <a href='DiscountItemEdit.aspx?productID=<%# Eval("productID") %>'>编辑优惠 </a>
                        </td>
                    </tr>
                </table>
            </td>
        </ItemTemplate>
    </asp:ListView>
</asp:Content>
