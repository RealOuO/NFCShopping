<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div>
        <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" 
            ForeColor="Black" Text="Label"></asp:Label>
    </div>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="优惠活动名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="DiscountDescriptionTextBox" runat="server" Height="23px"
        Width="109px"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="优惠内容"></asp:Label>
    <div>
    </div>
    <div>
        <div>
            <asp:GridView ID="DiscountItemsGridView" runat="server" CellPadding="4" 
                ForeColor="#333333" GridLines="None" Width="367px">
                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
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
        </div>
    </div>
    <div>
    </div>
    <asp:Button ID="SubmitButton" runat="server" Height="29px" Text="确定" 
        Width="73px" OnClick="EditButton_Click" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="InsertItemButton" runat="server" Height="29px" Text="增加优惠商品" 
        Width="103px" OnClick="DeleteButton_Click" />
    &nbsp;<asp:ObjectDataSource ID="DiscountsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" SelectMethod="GetDiscountByID"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL" UpdateMethod="UpdateDiscount">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="1" Name="discountID" QueryStringField="discountID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="DiscountItemsDataSource" runat="server" 
        DataObjectTypeName="NFCShoppingWebSite.Access_Data.DiscountItem" 
        DeleteMethod="DeleteDiscountItem" InsertMethod="InsertDiscountItem" 
        SelectMethod="GetDiscountItemsByDiscountID" 
        TypeName="NFCShoppingWebSite.BLL.DiscountItemBL" 
        UpdateMethod="UpdateDiscountItem">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="0" Name="discountID" 
                QueryStringField="discountID" Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discountItem" Type="Object" />
            <asp:Parameter Name="origDiscountItem" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
</asp:Content>
