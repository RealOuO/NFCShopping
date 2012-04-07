<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Text="优惠项目"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="DiscountIDTextBox" runat="server" Width="48px"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="优惠信息"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="DiscountDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="创于"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="CreatedTextBox" runat="server" Width="80px" Height="16px"></asp:TextBox>
    <div>
    </div>
    <asp:Button ID="Button1" runat="server" Height="29px" Text="确定" Width="73px" OnClick="EditButton_Click" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="Button2" runat="server" Height="29px" Text="删除" Width="73px" OnClick="DeleteButton_Click" />
    &nbsp;<asp:ObjectDataSource ID="DiscountEditDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
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
</asp:Content>
