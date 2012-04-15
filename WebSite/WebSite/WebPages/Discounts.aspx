<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Discounts.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Discounts" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    $(function () {
        $(".children:eq(2)").show();
        $("span:eq(2)").html("-");
        $("a:eq(8)").css({ "color": "red" });
        $(".head:eq(2)").toggle(function () {
            $(this).next().hide();
            $("span:eq(2)").html("+");
        }, function () {
            $(this).next().show();
            $("span:eq(2)").html("-");
        });
    });
</script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Size="XX-Large" ForeColor="Black"
        Text="优惠活动"></asp:Label>
    <asp:ObjectDataSource ID="DiscountsDataSource" runat="server" SelectMethod="GetDiscounts"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" UpdateMethod="UpdateDiscount">
        <UpdateParameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="DiscountsListView" runat="server" DataKeyNames="discountID" DataSourceID="DiscountsDataSource"
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
                            <a href='DiscountDetails.aspx?discountID=<%# Eval("discountID") %>'>
                                <asp:Label ID="DiscountLabel" runat="server" Text='<%# Eval("description") %>' ForeColor="Black" Font-Size="Large">
                                </asp:Label> <br />
                            </a>
                        </td>
                    </tr>
                </table>
            </td>
        </ItemTemplate>
    </asp:ListView>
</asp:Content>
