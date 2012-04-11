<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountItemEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountItemEdit" %>

<%@ Register assembly="DatePicker" namespace="DatePicker" tagprefix="picker" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="DiscountItemEditDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.DiscountItem"
        DeleteMethod="DeleteDiscountItem" InsertMethod="InsertDiscountItem" SelectMethod="GetDiscountItemByProductID"
        TypeName="NFCShoppingWebSite.BLL.DiscountItemBL" UpdateMethod="UpdateDiscountItem">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="1" Name="productID" QueryStringField="productID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discountItem" Type="Object" />
            <asp:Parameter Name="origDiscountItem" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
    <br />
    <br />
    <asp:Label ID="Label2" runat="server" Text="Label"></asp:Label>
    <br />
    <br />
    <asp:Label ID="Label3" runat="server" Text="折扣"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountPercentTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label4" runat="server" Text="描述信息"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountItemDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label5" runat="server" Text="开始日期"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="StartDateTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label6" runat="server" Text="截止日期"></asp:Label>
    <br />
    <br />
    <br />
    <asp:TextBox ID="EndDateTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Button ID="Button1" runat="server" Text="确定" OnClick="EditButton_Click"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="Button2" runat="server" Text="删除" OnClick="DeleteButton_Click"/>
    <br />
    <br />
</asp:Content>
